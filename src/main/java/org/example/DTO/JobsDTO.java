package org.example.DTO;


import jakarta.ws.rs.QueryParam;

public class JobsDTO {
    private @QueryParam("min_salary") Double min_salary;

    public Double getMin_salary(){

        return min_salary;

    }

    public void setMin_salary(){

        this.min_salary = min_salary;
    }

}
