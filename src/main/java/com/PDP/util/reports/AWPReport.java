package com.PDP.util.reports;

import com.PDP.DTO.AWPAndEmployeeDTO;
import com.PDP.model.AWP;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class AWPReport {
    final String name="Перечень АРМ";
    String subdivision;
    List<AWPAndEmployeeDTO> rData;
    List<ReportProperties> rowNames;

    public AWPReport (String subdivision,List<AWPAndEmployeeDTO> awps){
        this.subdivision=subdivision;
        this.rData=awps;
        rowNames=new ArrayList<>();
        rowNames.add(new ReportProperties("awpName","имя"));
        rowNames.add(new ReportProperties("location","локация"));
        rowNames.add(new ReportProperties("type","тип"));
        rowNames.add(new ReportProperties("job","должность"));
        rowNames.add(new ReportProperties("fullName","ФИО"));
        rowNames.add(new ReportProperties("ispdns","доступ к каким ИСПДн"));

    }

    public static ReportInfo getReportInfo(){

        ArrayList<ReportProperties> arrayList=new ArrayList<>();
        arrayList.add(new ReportProperties("text","subdivision","подразделение",""));
        return new ReportInfo("Перечень АРМ","/PDP/reports/awp",arrayList);
    }
}
