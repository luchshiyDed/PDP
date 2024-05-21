package com.PDP.model;

import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "employee")
public class Employee extends SubdivisionData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name="";
    private String secondName="";
    private String fatherName="";
    private String email="";
    @ManyToOne
    private Job job;
    private Boolean auto=false;
    @OneToOne
    @JoinColumn(name="awp_id")
    private AWP awp;
    @ManyToOne
    private Subdivision subdivision;

    @Override
    public String getSubdivisionName() {
        if(subdivision==null){
            return "";
        }
        return subdivision.getName();
    }
}
