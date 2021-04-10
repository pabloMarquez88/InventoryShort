/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.is.service;

import com.mycompany.is.dao.HistoryItemRepo;
import com.mycompany.is.dao.ItemRepo;
import com.mycompany.is.entity.HistoryItem;
import com.mycompany.is.entity.Item;
import com.mycompany.is.enums.ActionEnum;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Escritorio
 */
@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private HistoryItemRepo historyItemRepo;

    
    @Transactional
    public void saveItem(Item item, ActionEnum actionEnum) {
        String action = actionEnum.toString();
        
        Integer oldAmount = item.getCount();
        if (item.getId() != null){
            Item originalItem = itemRepo.findById(item.getId()).orElse(null);
            if (originalItem != null){
                oldAmount = originalItem.getCount();
            }
        }
        
        item.setStatus("ALIVE");
        itemRepo.save(item);        
        
        HistoryItem hi = HistoryItem.builder()
                .oldAmount(oldAmount)
                .newAmount(item.getCount())
                .action(action)
                .date(LocalDateTime.now())
                .item(item)
                .pcName(getPcName())
                .build();
        historyItemRepo.save(hi);

    }
    
    @Transactional
    public void saveItem(Item item) {
        String action = item.getId() != null ? ActionEnum.EDIT.toString() : ActionEnum.CREATE.toString();
        
        Integer oldAmount = item.getCount();
        if (item.getId() != null){
            Item originalItem = itemRepo.findById(item.getId()).orElse(null);
            if (originalItem != null){
                oldAmount = originalItem.getCount();
            }
        }
        
        item.setStatus("ALIVE");
        itemRepo.save(item);        
        
        HistoryItem hi = HistoryItem.builder()
                .oldAmount(oldAmount)
                .newAmount(item.getCount())
                .action(action)
                .date(LocalDateTime.now())
                .item(item)
                .pcName(getPcName())
                .build();
        historyItemRepo.save(hi);

    }

    @Transactional
    public void deleteItem(Item item) {
        item.setStatus("DELETED");
        itemRepo.save(item);
        HistoryItem hi = HistoryItem.builder()
                .oldAmount(item.getCount())
                .newAmount(item.getCount())
                .action(ActionEnum.DELETE.toString())
                .date(LocalDateTime.now())
                .item(item)
                .pcName(getPcName())
                .build();
        historyItemRepo.save(hi);
        
    }

    public List<Item> getItems() {
        return itemRepo.findByStatus("ALIVE");
    }

    public List<Item> getfilteredItems(String filter) {
        if (filter != null) {
            if (filter.trim().equals("")) {
                return itemRepo.findByStatus("ALIVE");
            }
            filter = filter.toUpperCase();
        }
        return itemRepo.findByFilterAndIds(filter);
    }
    
    public Item getItemById (Long id){
        return this.itemRepo.findById(id).orElse(null);
    }

    private String getPcName() {
        String hostname = "unknown";
        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        } catch (UnknownHostException ex) {
            System.out.println("Hostname can not be resolved");
        }
        return hostname;
    }

}
