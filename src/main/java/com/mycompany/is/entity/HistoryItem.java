/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.is.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Escritorio
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "history_item")
public class HistoryItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    Item item;
    LocalDateTime date;
    @Column(name="pcName")
    String pcName;
    @Column(name="oldAmount")
    Integer oldAmount;
    @Column(name="newAmount")
    Integer newAmount;
    String action;
}
