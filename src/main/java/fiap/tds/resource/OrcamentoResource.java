package fiap.tds.resource;

import fiap.tds.model.dao.OrcamentoDAOImpl;
import fiap.tds.model.vo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("orcamento")
public class OrcamentoResource {

    private OrcamentoDAOImpl orcamentoDAO = new OrcamentoDAOImpl();

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @GET
    @Path("/idUsuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrcamento(@PathParam("id") int id) {
        List<Map<String, Object>> orcamentos = orcamentoDAO.buscarOrcamentosPorUsuario(id);

        if (orcamentos != null && !orcamentos.isEmpty()) {
            return Response.ok(orcamentos).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhum orçamento encontrado para o usuário.")
                    .build();
        }
    }

    @POST
    @Path("/cadastro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastro(Map<String, Object> info){
        String idUsuario = (String) info.get("idUsuario");
        String idVeiculo = (String) info.get("idVeiculo");
        String idOficina = "1";
        String tipoOrcamento = (String) info.get("tipoOrcamento");
        String status = "Aguardando visita do Cliente";
        String diagnostico = (String) info.get("diagnostico");
        String solucao = (String) info.get("solucao");
        String preco = (String) info.get("preco");

        boolean resp = orcamentoDAO.cadastro(idUsuario, idVeiculo, idOficina, tipoOrcamento, status, diagnostico, solucao, preco);

        if (resp){
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            return Response.ok(response).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Erro").build();
        }
    }
}
