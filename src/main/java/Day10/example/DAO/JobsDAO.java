package Day10.example.DAO;


import Day10.example.Models.Jobs;


import java.sql.*;
import java.util.ArrayList;


public class JobsDAO {

    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\Day4\\src\\main\\java\\org\\example\\hr.db";
    private static final String SELECT_ALL_JOBS = "select * from Jobs";
    private static final String SELECT_ONE_JOBS = "select * from Jobs where job_id = ?";
    private static final String SELECT_JOBS_WITH_MIN_SALARY = "select * from jobs where min_salary = ?";
   // private static final String SELECT_JOBS_WITH_SALARY_PAGINATION = "select * from departments where location_id = ? order by department_id limit ? offset ?";
   // private static final String SELECT_JOBS_WITH_PAGINATION = "select * from departments order by department_id limit ? offset ?";
    private static final String INSERT_JOBS = "insert into Jobs values (?, ?, ?)";
    private static final String UPDATE_JOBS = "update departments set job_title = ?, location_id = ? where job_id = ?";
    private static final String DELETE_JOBS = "delete from departments where job_id = ?";


    public void InsertJobs(Jobs j) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_JOBS);
        st.setInt(1, j.getJob_id());
        st.setString(2, j.getJob_title());
        st.setDouble(3, j.getJob_salary());
        st.executeUpdate();
    }


    public void updateJob(Jobs j) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_JOBS);
        st.setInt(3, j.getJob_id());
        st.setString(1, j.getJob_title());
        st.setDouble(2, j.getJob_salary());
        st.executeUpdate();
    }

    public void deleteJob(int jobId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_JOBS);
        st.setInt(1, jobId);
        st.executeUpdate();
    }
    public Jobs selectJob(int jobId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_JOBS);
        st.setInt(1, jobId);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Jobs(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Jobs> SELECT_ALL_JOBS(Double min_salary ) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;

        if(min_salary != null) {
            st = conn.prepareStatement(SELECT_JOBS_WITH_MIN_SALARY);
            st.setDouble(1,min_salary );

        }
        else  {
            st = conn.prepareStatement(SELECT_ALL_JOBS);

        }
        ResultSet rs = st.executeQuery();
        ArrayList<Jobs> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new Jobs(rs));
        }

        return jobs;
    }
}
