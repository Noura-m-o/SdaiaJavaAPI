package Day11.example.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JobsDTO {

    private int job_id;
    private String job_title;
    private double min_salary;
    private double max_salary;
    private ArrayList<LinkDTO> links = new ArrayList<>();

    public JobsDTO() {
    }

    public JobsDTO(double max_salary, double min_salary, String job_title, int job_id) {
        this.max_salary = max_salary;
        this.min_salary = min_salary;
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

    public double getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(double min_salary) {
        this.min_salary = min_salary;
    }

    public double getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(double max_salary) {
        this.max_salary = max_salary;
    }

    public ArrayList<LinkDTO> getLinks() {
        return links;
    }

    public JobsDTO(ResultSet rs)throws SQLException {
       job_id = rs.getInt("job_id");
        job_title = rs.getString("job_title");
      min_salary = rs.getDouble("min_salary");
       max_salary = rs.getDouble("max_salary");


    }

    public void addLink(String url, String rel) {
        LinkDTO link = new LinkDTO();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
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
