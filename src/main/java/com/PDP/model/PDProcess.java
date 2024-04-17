package com.PDP.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper=false)
@Table(name = "process")
public class PDProcess extends Nameable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name="";
    @ManyToMany
    private List<PDTarget> pdTargets=new ArrayList<>();
    @ManyToMany
    private List<PDSubject> pdSubjects=new ArrayList<>();
    @ManyToMany
    private List<PDProcessAction> pdProcessActions=new ArrayList<>();
    private Date deleteDate=new Date();
    private String source="";
    private String destination="";
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name="regDocument_id")
    private PDRegDoc pdRegDoc;
    private Boolean thirdPeople=false;
    @ManyToOne
    @JoinColumn(name = "processType_id")
    private PDProcessType pdProcessType;
    @ManyToOne
    @JoinColumn(name="storage_id")
    private PDStorage pdStorage;
    @ManyToOne
    @JoinColumn(name="document_id")
    private PDDocument pdDocument;
    @ManyToOne
    @JoinColumn(name="type_id")
    private PDType pdType;
    @ManyToOne
    @JoinColumn(name="ispdn_id")
    private ISPDN ispdn;
    @ManyToOne
    @JoinColumn(name="icopd_id")
    private ICOPD icopd;
    @JsonIgnore
    public String getSubdivision(){
        if (icopd==null){
            return null;
        }
        if(icopd.getSubdivision()==null)
            return null;
        return icopd.getSubdivision().getName();
    }
}
