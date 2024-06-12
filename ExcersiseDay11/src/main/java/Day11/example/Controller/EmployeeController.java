package Day11.example.Controller;



import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import Day11.example.DAO.EmployeeDAO;
import Day11.example.DTO.EmployeeFilterDto;
import Day11.example.DTO.EmployeeDTO;
import Day11.example.Models.Employees;
import java.net.URI;
import java.util.ArrayList;


@Path("/employees")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class EmployeeController {

    EmployeeDAO dao = new EmployeeDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;


    @GET
    public Response getAllEmployees(
            @BeanParam EmployeeFilterDto filter
    ) {

        try {
            GenericEntity<ArrayList<Employees>> employees = new GenericEntity<ArrayList<Employees>>(dao.SELECT_ALL_EMPLOYEES(filter.getJob_id())) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(employees)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(employees)
                        .type("text/csv")
                        .build();
            }
            return Response
                    .ok(employees, MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @POST
    public Response insertEmployee(@PathParam("job_id") Integer job_id, Employees emp) {
        try {
            dao.INSERT_EMPLOYEE(emp);
            return Response
                    .ok(emp)
                    .status(Response.Status.CREATED)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addLinks(EmployeeDTO dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI empUri = uriInfo.getAbsolutePathBuilder()
                .path(EmployeeController.class).build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(empUri.toString(), "employees");

    }
}