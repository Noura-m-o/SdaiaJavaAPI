package Day10.example.Controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;


@Path("secures")
public class SecureController {


    @GET
    public String getIt() {
        return "Got it: you are in secure controller";
    }
}