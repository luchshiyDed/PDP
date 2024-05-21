package com.PDP.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper=false)
@Table(name = "process")
public class PDProcess extends SubdivisionData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name="";
    @ManyToOne
    private PDTarget pdTargets;
    @ManyToMany
    private List<PDSubject> pdSubjects=new ArrayList<>();
    @ManyToMany
    private List<PDProcessAction> pdProcessActions=new ArrayList<>();
    @ManyToOne
    private DeleteCondition deleteCondition;
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
    private PDStorage pdProcessPlace;
    @ManyToOne
    @JoinColumn(name="document_id")
    private PDDocument pdDocument;
    @ManyToMany
    private List<PDType> pdType=new ArrayList<>();
//    @ManyToOne
//    @JoinColumn(name="ispdn_id")
//    private ISPDN ispdn;

    private Boolean auto=false;
    @Override
    @JsonIgnore
    public String getSubdivisionName() {
        if(employee==null)
            return "";
        return employee.getSubdivisionName();
    }
}
