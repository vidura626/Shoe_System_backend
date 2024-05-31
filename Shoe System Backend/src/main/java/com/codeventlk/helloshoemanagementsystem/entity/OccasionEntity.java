package com.codeventlk.helloshoemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table (name = "Occassion")
@Data
public class OccasionEntity {
    @Id
    private String occasionCode;
    private String occasionDesc;
    @OneToMany(mappedBy = "occasionEntity",cascade = CascadeType.ALL)
    private List<ItemEntity> itemEntities;
}
