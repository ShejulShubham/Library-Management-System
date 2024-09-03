package com.discovery.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "fines")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Fine extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "borrow_id",referencedColumnName ="id")
    private Borrow borrow;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User_Account user;
    
    @Column(name = "fine_amount", nullable = false)
    private double fineAmount;

    @Column(name = "fine_date", nullable = false)
    private LocalDate fineDate;

    @Column(name = "paid", nullable = false)
    private boolean paid;
   
}
