package com.PDP.util.reports;

import com.PDP.DTO.AWPAndEmployeeDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class EmployeeReport {
    final String name="Перечень сотрудников обрабатывающих персональные данные";

    List<AWPAndEmployeeDTO> rData;
    List<ReportProperties> rowNames;

    public EmployeeReport (List<AWPAndEmployeeDTO> employees){

        this.rData=employees;
        rowNames=new ArrayList<>();
        rowNames.add(new ReportProperties("fullName","ФИО"));
        rowNames.add(new ReportProperties("job","должность"));
        rowNames.add(new ReportProperties("subdivision","подразделение"));
        rowNames.add(new ReportProperties("awpName","Наименование рабочего места"));


    }

    public static ReportInfo getReportInfo(){
        ArrayList<ReportProperties> arrayList=new ArrayList<>();
        return new ReportInfo("Перечень сотрудников обрабатывающих персональные данные","/PDP/reports/employee",arrayList);
    }
}
