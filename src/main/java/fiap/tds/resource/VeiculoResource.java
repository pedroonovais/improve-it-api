package fiap.tds.resource;

import fiap.tds.model.dao.VeiculoDAOImpl;
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

@Path("veiculo")
public class VeiculoResource {

    private VeiculoDAOImpl veiculoDAO = new VeiculoDAOImpl();

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @GET
    @Path("/idUsuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVeiculos(@PathParam("id") int id) {
        List<Map<String, Object>> veiculos = veiculoDAO.buscarVeiculosPorUsuario(id);

        if (veiculos != null && !veiculos.isEmpty()) {
            return Response.ok(veiculos).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhum orçamento encontrado para o usuário.")
                    .build();
        }
    }

    @PUT
    @Path("/atualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarVeiculo(Map<String, Object> veiculo) {
        try {
            String idVeiculo = (String) veiculo.get("idVeiculo");
            String marca = (String) veiculo.get("marcaVeiculo");
            String modelo = (String) veiculo.get("modeloVeiculo");
            String ano = (String) veiculo.get("anoVeiculo");
            String quilometragem = (String) veiculo.get("quilometragemVeiculo");

            boolean updated = veiculoDAO.atualizar(idVeiculo, marca, modelo, ano, quilometragem);

            if (updated) {
                return Response.ok("Veículo atualizado com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Veículo não encontrado.")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar veículo: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/cadastro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastro(Map<String, Object> info){
        String idUsuario = (String) info.get("idUsuario");
        String ano = (String) info.get("ano");
        String marca = (String) info.get("marca");
        String modelo = (String) info.get("modelo");
        String quilometragem = (String) info.get("quilometragem");

        int id = veiculoDAO.cadastro(idUsuario, ano, marca, modelo, quilometragem);

        if (id > 0){
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("idVeiculo", String.valueOf(id));
            return Response.ok(response).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Erro").build();
        }
    }

}
