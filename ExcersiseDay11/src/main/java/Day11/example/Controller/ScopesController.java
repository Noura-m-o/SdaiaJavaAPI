package Day11.example.Controller;

import Day11.example.services.ApplicationService;
import Day11.example.services.RequestService;
import Day11.example.services.SessionService;
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