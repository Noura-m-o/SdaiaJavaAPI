
import Day11.example.DAO.JobsDAO;
import Day11.example.DTO.JobFilterDto;
import Day11.example.DTO.JobsDTO;
import Day11.example.Models.Jobs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)


public class TestJobsDao {

    @Mock
    JobFilterDto filter;

    @InjectMocks
    JobsDAO dao;

    @Test
    public void testUpdateJob() throws SQLException, ClassNotFoundException {

        Jobs j = new Jobs(50000.0,1500.0,"Manager",1);
        JobsDAO dao = new JobsDAO();


        Assertions.assertDoesNotThrow(() -> dao.updateJob(j));


        Jobs Newj = dao.selectJob(j.getJob_id());


        Assertions.assertNotNull(Newj);
        Assertions.assertEquals(Newj.getJob_title(),j.getJob_title());
        Assertions.assertEquals(Newj.getJob_salary(),j.getJob_salary());


    }

    @Test
    public void testSelectJob() throws SQLException, ClassNotFoundException {
        JobsDAO dao = new JobsDAO();

        Assertions.assertDoesNotThrow(() -> dao.selectJob(1));

        Jobs j = dao.selectJob(1);

        Assertions.assertNotNull(j);
        Assertions.assertEquals(1, j.getJob_id());

    }

//    @Test
//    public void testSelectAllJobss() throws SQLException, ClassNotFoundException {
////        DepartmentDAO dao = new DepartmentDAO();
////        DepartmentFilterDto filter = new DepartmentFilterDto();
////        filter.setLocId(1500);
//
//        when(filter.getMin_salary()).thenReturn(1500.0);
//        when(filter.getLimit()).thenReturn(null);
//
//        Assertions.assertDoesNotThrow(() -> dao.SELECT_ALL_JOBS(1500.0));
//
//        ArrayList<Jobs> jobs = dao.SELECT_ALL_JOBS(filter.getMin_salary());
//
//        Assertions.assertNotNull(jobs);
//        Assertions.assertTrue(jobs.size() != 0);
//        for (Jobs j : jobs) {
//            Assertions.assertEquals(1500, j.getJob_id());
//        }


    }





