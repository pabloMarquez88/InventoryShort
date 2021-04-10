/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.is.service;

import com.mycompany.is.dao.ItemRepo;
import com.mycompany.is.entity.Item;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Escritorio
 */
@Service
public class ItemService {
    @Autowired
    private ItemRepo itemRepo;
        
    public void saveItem(Item item){
        item.setStatus("ALIVE");
        itemRepo.save(item);
    }
    
    public void deleteItem(Item item){
        item.setStatus("DELETED");
        itemRepo.save(item);
    }
    
    public List<Item> getItems(){
        return itemRepo.findByStatus("ALIVE");
    }
    
    public List<Item> getfilteredItems(String filter){
        if (filter != null){
            if (filter.trim().equals("")) {
                return itemRepo.findByStatus("ALIVE");
            }
            filter = filter.toUpperCase();
        } 
        return itemRepo.findByFilterAndIds(filter);
    }
    
}
