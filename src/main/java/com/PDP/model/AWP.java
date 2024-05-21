package com.PDP.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
@Table(name="awp")
public class AWP extends SubdivisionData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name="";
    private String location="";
    private String type="";
    @ManyToOne
    private Subdivision subdivision;

    @ManyToMany
    private List<ISPDN> ispdns=new ArrayList<>();
    @Override
    public String getSubdivisionName() {
        if(subdivision==null)
            return "";
        return subdivision.getName();
    }
}
