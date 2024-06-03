package org.example.DAO;


import org.example.Models.Jobs;


import java.sql.*;
import java.util.ArrayList;


public class JobsDAO {

    private static final String URL = "jdbc:sqlite:D:\\Private\\SDAIA\\SDAIA Java Course\\JavaBasics\\src\\main\\java\\day4\\hr.db";
    private static final String SELECT_ALL_JOBS = "select * from Jobs";
    private static final String SELECT_ONE_JOBS = "select * from Jobs where job_id = ?";
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

    public ArrayList<Jobs> SELECT_ALL_JOBS() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ALL_JOBS);
        ResultSet rs = st.executeQuery();
        ArrayList<Jobs> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new Jobs(rs));
        }

        return jobs;
    }
}
