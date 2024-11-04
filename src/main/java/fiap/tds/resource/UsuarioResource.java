package fiap.tds.resource;

import fiap.tds.model.dao.UsuarioDAOImpl;
import fiap.tds.model.vo.Credentials;
import fiap.tds.model.vo.Usuario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Path("usuario")
public class UsuarioResource {

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTarefas(){
        return "Olá";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@PathParam("id") int id) {
        Usuario usuario = usuarioDAO.listarPorId(id);

        if (usuario != null){
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("nome", usuario.getNomeUsuario());
            response.put("cpf_usuario", usuario.getCpfUsuario());
            response.put("cliente_porto", String.valueOf(usuario.isClientePorto()));
            return Response.ok(response).build();
        } else{
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials){
        int id = usuarioDAO.login(credentials.getLogin(), credentials.getSenha());

        if (id > 0){
            String jwt = Jwts.builder()
                    .setSubject(credentials.getLogin())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                    .signWith(KEY)
                    .compact();

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("token", jwt);
            response.put("id", String.valueOf(id));
            return Response.ok(response).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Email ou senha inválidos").build();
        }
    }

    @POST
    @Path("/cadastro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastro(Map<String, Object> info){
        String nome = (String) info.get("nome");
        String cpf = (String) info.get("cpf");
        String email = (String) info.get("email");
        String senha = (String) info.get("senha");

        int id = usuarioDAO.cadastro(nome, cpf, email, senha);

        String[] partes = email.split("@");

        if (id > 0){
            String jwt = Jwts.builder()
                    .setSubject(partes[0])
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                    .signWith(KEY)
                    .compact();

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("token", jwt);
            response.put("id", String.valueOf(id));
            return Response.ok(response).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Erro").build();
        }
    }

}
