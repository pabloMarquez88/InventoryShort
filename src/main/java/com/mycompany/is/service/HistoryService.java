/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.is.service;

import com.mycompany.is.dao.HistoryItemRepo;
import com.mycompany.is.entity.HistoryItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Escritorio
 */
@Service
public class HistoryService {
    
    @Autowired
    private HistoryItemRepo historyItemRepo;
    
    public List<HistoryItem> getHistoryFiltered(String filter){        
        if (filter != null){
            if (!filter.trim().equals("")){
                return historyItemRepo.getfiltered(filter.toUpperCase());
            }            
        }
        return historyItemRepo.findAll();
    }
    
    public List<HistoryItem> getAllHistoryItems(){
        return historyItemRepo.findAll();
    }
    
}
