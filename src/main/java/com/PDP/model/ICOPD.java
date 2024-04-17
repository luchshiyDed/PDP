package com.PDP.model;

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
@Table(name = "icopd")
public class ICOPD extends Nameable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name="";
    @OneToOne
    private Subdivision subdivision;
    @ManyToMany
    private List<AWP> awps=new ArrayList<>();
    private Boolean isActive=false;
    private Date activationDate=new Date();

}
