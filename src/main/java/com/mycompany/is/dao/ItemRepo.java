/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.is.dao;

import com.mycompany.is.entity.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Escritorio
 */@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE "
            + "UPPER(i.status) = 'ALIVE' AND ("
            + "UPPER(i.name) LIKE %:filter% "
            + "OR UPPER(i.code) LIKE %:filter% "
            + "OR UPPER(i.description) LIKE %:filter% "
            + "OR i.count = :filter "
            + "OR id = :filter )")
    List<Item> findByFilterAndIds(@Param("filter") String filter); 
    
    List<Item> findByStatus (String status);
    
}
