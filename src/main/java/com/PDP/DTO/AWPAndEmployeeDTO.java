package com.PDP.DTO;

import com.PDP.model.Employee;
import com.PDP.model.ISPDN;
import lombok.Data;

@Data
public class AWPAndEmployeeDTO {
    public AWPAndEmployeeDTO(Employee employee) {
        email = employee.getEmail();
        if (employee.getJob() != null)
            job = employee.getJob().getName();
        if (employee.getAwp() != null) {
            type = employee.getAwp().getType();
            location=employee.getAwp().getLocation();
            awpName=employee.getAwp().getName();
            subdivision=employee.getSubdivisionName();
            if(employee.getAwp().getIspdns()!=null){
                StringBuilder strBuilder = new StringBuilder(ispdns);
                for (ISPDN i : employee.getAwp().getIspdns() ) {
                    strBuilder.append(i.getName());
                    strBuilder.append(",");
                }
                ispdns=strBuilder.toString();
            }
            fullName=employee.getSecondName()+" "+employee.getName()+" "+employee.getFatherName();
        }
    }
    String subdivision="";
    String fullName="";
    String email = "";
    String job = "";
    String type = "";
    String location = "";
    String awpName = "";
    String ispdns = "";
}
