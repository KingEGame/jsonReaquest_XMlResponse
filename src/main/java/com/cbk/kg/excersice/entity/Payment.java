package com.cbk.kg.excersice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "payments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long paymentId;
    
    private Long requestId;

    private Integer supplierId;

    private String account;

    private Double amount;

    private Integer status;

    private String command;

    private Timestamp date;
}
