package Day7.example.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Jobs {

    private int job_id;
    private String job_title;
    private double min_salary;
    private double max_salary;

    public Jobs() {
    }

    public Jobs(double max_salary, double job_salary, String job_title, int job_id) {
        this.max_salary = max_salary;
        this.min_salary = job_salary;
        this.job_title = job_title;
        this.job_id = job_id;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public double getJob_salary() {
        return min_salary;
    }

    public void setJob_salary(double job_salary) {
        this.min_salary = job_salary;
    }

    public double getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(double max_salary) {
        this.max_salary = max_salary;
    }

    public Jobs(ResultSet rs)throws SQLException {
        job_id = rs.getInt("job_id");
        job_title = rs.getString("job_title");
        min_salary = rs.getDouble("min_salary");
        max_salary = rs.getDouble("max_salary");


    }

    @Override
    public String toString() {
        return "Jobs{" +
                "job_id=" + job_id +
                ", job_title='" + job_title + '\'' +
                ", min_salary=" + min_salary +
                ", max_salary=" + max_salary +
                '}';
    }
}
