package com.PDP.util.reports;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
@Data
@AllArgsConstructor
public class ReportInfo {
    String name;
    String address;
    ArrayList<ReportProperties> reportProperties;
}
