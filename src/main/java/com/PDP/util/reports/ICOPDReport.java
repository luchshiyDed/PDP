package com.PDP.util.reports;

import com.PDP.DTO.AWPAndEmployeeDTO;
import com.PDP.model.Employee;
import com.PDP.model.ICOPD;
import com.PDP.model.PDProcess;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ICOPDReport {
    final String name="ICOPD";
    String subdivision;
    List<PDProcess> pdProcesses;
    List<AWPAndEmployeeDTO> employees;
    public static ReportInfo getReportInfo(){
        ArrayList<ReportProperties> arrayList=new ArrayList<>();
        arrayList.add(new ReportProperties("subdivision","подразделение"));
        return new ReportInfo("ICOPD","/PDP/reports/icopd",arrayList);
    }
}
