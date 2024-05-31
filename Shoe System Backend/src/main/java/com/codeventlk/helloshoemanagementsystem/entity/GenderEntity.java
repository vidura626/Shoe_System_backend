package com.codeventlk.helloshoemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table (name = "gender")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenderEntity {
    @Id
    private String genderCode;
    private String genderDesc;
    @OneToMany(mappedBy = "genderEntity",cascade = CascadeType.ALL)
    private List<ItemEntity> itemEntities;

}
