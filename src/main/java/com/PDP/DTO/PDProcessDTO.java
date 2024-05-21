package com.PDP.DTO;

import com.PDP.model.ISPDN;
import com.PDP.model.PDProcess;
import com.PDP.model.PDSubject;
import com.PDP.model.PDType;
import lombok.Data;

@Data
public class PDProcessDTO {
    private String document;
    private String types;
    private String regDocument;
    private String target;
    private String ispdns="";
    private String subjects;
    private String deleteCondition;
    public PDProcessDTO(PDProcess pdProcess){
        document=pdProcess.getPdDocument()==null?"":pdProcess.getPdDocument().getName();
        target=pdProcess.getPdTargets()==null?"":pdProcess.getPdTargets().getName();
        regDocument=pdProcess.getPdRegDoc()==null?"":pdProcess.getPdRegDoc().getName();
        deleteCondition=pdProcess.getDeleteCondition()==null?"":pdProcess.getDeleteCondition().getName();
        if(pdProcess.getPdType()!=null){
            StringBuilder strBuilder = new StringBuilder("");
            for (PDType i : pdProcess.getPdType() ) {
                strBuilder.append(i.getName());
                strBuilder.append(",");
            }
            types=strBuilder.toString();
        }
        if(pdProcess.getPdSubjects()!=null){
            StringBuilder strBuilder = new StringBuilder("");
            for (PDSubject i : pdProcess.getPdSubjects() ) {
                strBuilder.append(i.getName());
                strBuilder.append(",");
            }
            subjects=strBuilder.toString();
        }

    }
    }



