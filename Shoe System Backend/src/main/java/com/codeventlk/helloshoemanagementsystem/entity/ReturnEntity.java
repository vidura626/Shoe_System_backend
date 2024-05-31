package com.codeventlk.helloshoemanagementsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table (name = "Returns")
public class ReturnEntity {
    @Id
    private String returnId;
    private Timestamp returnDate;
    @OneToOne(cascade = CascadeType.ALL)
    private StockSizeOrderDetailsEntity stockSizeOrderDetailsEntity;
    private int qty;
}
