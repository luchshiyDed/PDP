package com.PDP.util.reports;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportProperties {
    String type;
    String dictName;
    String name;
    String fetchAddress;
    public ReportProperties(String dictName,String name){
        this.dictName=dictName;
        this.name=name;
        fetchAddress="";
        type="text";
    }
}
