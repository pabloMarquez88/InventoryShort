/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.is.dao;

import com.mycompany.is.entity.HistoryItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Escritorio
 */
@Repository
public interface HistoryItemRepo extends JpaRepository<HistoryItem, Long>{

    
    @Query("SELECT i FROM HistoryItem i WHERE "
            + "UPPER(i.pcName) LIKE %:filter% "
            + "OR UPPER(i.action) LIKE %:filter% "
            + "OR UPPER(i.item.name) LIKE %:filter% ")
    public List<HistoryItem> getfiltered(@Param("filter") String filter);
    
    
}
