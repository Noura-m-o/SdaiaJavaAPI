package Day10.example.Controller;

import Day10.example.services.ApplicationService;
import Day10.example.services.RequestService;
import Day10.example.services.SessionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;


@Path("scopes")
public class ScopesController {

    @Inject
    RequestService reqServ;
    @Inject
    SessionService sessServ;
    @Inject
    ApplicationService appServ;



    @GET
    public String getIt() {


        return "Got it: App: " + appServ.getCount() + ", Session: " + sessServ.getCount() + ", Request: " + reqServ.getCount();
    }
}