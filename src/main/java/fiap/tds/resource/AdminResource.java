package fiap.tds.resource;

import fiap.tds.model.dao.AdminDAOImpl;
import fiap.tds.model.vo.Credentials;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Path("admin")
public class AdminResource {

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private AdminDAOImpl adminDAO = new AdminDAOImpl();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials){
        int id = adminDAO.login(credentials.getLogin(), credentials.getSenha());

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
}
