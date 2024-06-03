package org.example.Controller;

import org.example.Models.Jobs;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.DAO.JobsDAO;


import java.sql.SQLException;
import java.util.ArrayList;

@Path("/jobs")

public class JobsController {

    JobsDAO dao = new JobsDAO();

    @GET
    public ArrayList<Jobs> SELECT_ALL_JOBS() {

        try {
            return dao.SELECT_ALL_JOBS();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GET
    @Path("{JobsId}")
    public Jobs SELECT_ONE_JOBS(@PathParam("JobsId") int JobsId) {

        try {
            return dao.selectJob(JobsId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public void INSERT_JOBS(Jobs jobs) {

        try {
        dao.InsertJobs(jobs);
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
