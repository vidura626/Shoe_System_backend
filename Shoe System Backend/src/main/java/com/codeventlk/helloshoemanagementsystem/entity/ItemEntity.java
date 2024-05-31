package com.codeventlk.helloshoemanagementsystem.entity;

import com.codeventlk.helloshoemanagementsystem.Enum.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Item")
@Data
public class ItemEntity {
    @Id
    private String itemCode;
    private String itemDesc;
    @Column(columnDefinition = "LONGTEXT")
    private String pic;
    @ManyToOne
    @JoinColumn(name = "genderCode",nullable = true)
    private GenderEntity genderEntity;

    @ManyToOne
    @JoinColumn(name = "occasionCode",nullable = true)
    private OccasionEntity occasionEntity;

    @ManyToOne
    @JoinColumn(name = "varietyCode",nullable = true)
    private VarietyEntity varietyEntity;

    @OneToMany(mappedBy = "itemEntity",cascade = CascadeType.ALL)
    private List<StockEntity> stockEntities;

}
