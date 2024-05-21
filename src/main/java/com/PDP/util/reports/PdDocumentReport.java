package com.PDP.util.reports;

import com.PDP.DTO.PDProcessDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PdDocumentReport {
    final String name="Перечень документов содержащих персональные данные";
    List<PDProcessDTO> rData;
    List<ReportProperties> rowNames;

    public PdDocumentReport(List<PDProcessDTO> rData) {
        this.rData = rData;
        this.rowNames = new ArrayList<>();
        rowNames.add(new ReportProperties("document","Документ"));
        rowNames.add(new ReportProperties("subjects","Субъекты ПД"));
        rowNames.add(new ReportProperties("target","Цель обработки"));
        rowNames.add(new ReportProperties("types","Наименование ПД"));
    }
    public static ReportInfo getReportInfo(){
        ArrayList<ReportProperties> arrayList=new ArrayList<>();
        return new ReportInfo("Перечень документов содержащих персональные данные","/PDP/reports/document",arrayList);
    }
}
