package com.codeventlk.helloshoemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table (name = "Size")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeEntity {
    @Id
    private String sizeCode;
    private String sizeDesc;
    @OneToMany(mappedBy = "sizeEntity",cascade = CascadeType.ALL)
    private List<StockSizeEntity> stockSizeEntities;
}
