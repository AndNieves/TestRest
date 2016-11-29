package example;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

@Path("/content/ftp-accounts")
public class FtpAccounts {

    private ObjectMapper mapper = new ObjectMapper();

    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) {

        String output = "Jersey say : " + msg;

        return Response.status(200).entity(output).build();

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrUpdateFtpAccount(String jsonIn) {
        // Initialize and set response as JSON as that is all that is supported.
        //init();
        //setMediaType(MediaType.APPLICATION_JSON);
        System.out.println("Received "+jsonIn);
        // Hydrate the incoming JSON to object
        HydratedFtpAccount hydratedFtpAccount = null;
        try {
            hydratedFtpAccount = mapper.readValue(jsonIn, HydratedFtpAccount.class);
            System.out.println("Received : "+hydratedFtpAccount);
            hydratedFtpAccount.setPassword("JERSEYPASSWORD");
            return Response.ok(mapper.writeValueAsString(hydratedFtpAccount), MediaType.APPLICATION_JSON).build();
        } catch (IOException e) {
            return Response.status(500).build();
        }
    }

}
