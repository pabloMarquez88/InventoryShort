/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.is.dto;

import com.mycompany.is.entity.HistoryItem;
import com.mycompany.is.entity.Item;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Escritorio
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportImportDTO {
    List<HistoryItem> history;
    List<Item> item;
}
