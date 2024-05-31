package com.codeventlk.helloshoemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Branch")
@Data
public class BranchEntity {
    @Id
    private String branchId;
    @Column(unique = true)
    private String branchName;
}
