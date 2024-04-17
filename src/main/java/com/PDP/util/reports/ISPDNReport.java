package com.PDP.util.reports;

import com.PDP.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ISPDNReport {
    public ISPDNReport(){
        pdTypes=new ArrayList<>();
        pdSubjects=new ArrayList<>();
        pdProcessTypes=new ArrayList<>();
        pdProcessActions=new ArrayList<>();
    }
    String ispdnLocation;
    Boolean ispdnTransBorder;
    List<PDType> pdTypes;
    List<PDSubject> pdSubjects;
    List<PDProcessType> pdProcessTypes;
    List<PDProcessAction> pdProcessActions;
}
