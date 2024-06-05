package Day7.example.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import Day7.example.DTO.JobsDTO;
import Day7.example.DTO.JobsFilterDTO;
import Day7.example.Models.Jobs;
import jakarta.ws.rs.core.MediaType;
import Day7.example.DAO.JobsDAO;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import Day7.example.exceptions.DataNotFoundException;



@Path("/jobs")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class JobsController {

    JobsDAO dao = new JobsDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

//ArrayList<Jobs>
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response SELECT_ALL_JOBS(
       // @QueryParam("min_salary") Double min_salary
        @BeanParam JobsFilterDTO filter
){
        try {
            GenericEntity<ArrayList<Jobs>> jobs = new GenericEntity<ArrayList<Jobs>>(dao.SELECT_ALL_JOBS(filter.getMin_salary())) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(jobs)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            return Response .ok(jobs, MediaType.APPLICATION_JSON)
                    .build();

          //  return dao.SELECT_ALL_JOBS(min_salary);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
   // @GET
   // @Path("{JobsId}")
  //  public Jobs SELECT_ONE_JOBS (@PathParam("JobsId") int JobsId) {

     //  try {
         //   return dao.selectJob(JobsId);
          /*  dao.selectJob(JobsId);
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(jobs)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            return Response .ok(jobs, MediaType.APPLICATION_JSON)
                    .build();
*/

   //     } catch (Exception e) {
        //    throw new RuntimeException(e);
      //  }
   // }
    public Response  SELECT_ONE_JOBS (@PathParam("JobsId") int JobsId)throws SQLException {

        try {
            Jobs job = dao.selectJob(JobsId);
            if(job == null ){

                throw new DataNotFoundException("jobs " + JobsId + "Not found");
            }
            //headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML) {

            JobsDTO dto = new JobsDTO();
            dto.setJob_id(job.getJob_id());
            dto.setJob_title(job.getJob_title());
            dto.setMin_salary(job.getJob_salary());
            dto.setMax_salary(job.getMax_salary());

            addLinks(dto);
            return Response.ok(dto).build();
            /* return Response
                    .ok(dto)
                    .type(MediaType.APPLICATION_JSON)
                    .build(); */

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addLinks(JobsDTO dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI empUri = uriInfo.getAbsolutePathBuilder()
                .path(JobsController.class).build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(empUri.toString(), "employees");

    }
    @DELETE
    @Path("{JobsId}")
    public void DELETE_JOBS(@PathParam("JobsId") int JobsId) {

        try {
            dao.deleteJob(JobsId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    public Response INSERT_JOBS(Jobs jobs) {

        try {
        dao.InsertJobs(jobs);
        return Response
                .ok(jobs)
                .status(Response.Status.CREATED)
                .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PUT
    @Path("{JobsId}")
    public void UPDATE_JOBS(@PathParam("JobsId") int JobsId, Jobs jobs) {

        try {
            jobs.setJob_id(JobsId);
            dao.updateJob(jobs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
