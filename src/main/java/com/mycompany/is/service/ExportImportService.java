/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.is.service;

import com.mycompany.is.dao.HistoryItemRepo;
import com.mycompany.is.dao.ItemRepo;
import com.mycompany.is.dto.ExportImportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Escritorio
 */
@Service
public class ExportImportService {
    
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private HistoryItemRepo historyItemRepo;

    
    public void loadAllData(ExportImportDTO dto) {
        this.loadItems(dto);
        this.loadHistoty(dto);
    }
    
    @Transactional
    public void loadItems(ExportImportDTO dto) {
        itemRepo.deleteAll();        
        dto.getItem().forEach(item -> {
            item.setOldId(item.getId());
            item.setId(null);
        });
        itemRepo.saveAll(dto.getItem());     
        
    }
    
    @Transactional
    public void loadHistoty(ExportImportDTO dto) {
        historyItemRepo.deleteAll();    
        
        dto.getHistory().forEach(hi -> {
            dto.getItem().stream().filter(
                    i -> (hi.getItem().getId().equals(i.getOldId()))
            ).forEachOrdered(
                    i -> {
                hi.getItem().setId(i.getId());
            });
        });
        
        historyItemRepo.saveAll(dto.getHistory());
    }
}
