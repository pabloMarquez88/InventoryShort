/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.is.gui;

import com.mycompany.is.Parent;
import ch.qos.logback.core.CoreConstants;
import com.mycompany.is.entity.Item;
import com.mycompany.is.enums.ActionEnum;
import com.mycompany.is.service.ItemService;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Escritorio
 */
public class ItemRetrievetFrm extends javax.swing.JInternalFrame {

    private static ItemRetrievetFrm INSTANCE;
    private JFrame frame;
    
    public static ItemRetrievetFrm getInstance(JFrame parent){
        if (INSTANCE == null) {
            return new ItemRetrievetFrm(parent);
        }        
        return INSTANCE;
    }
    
    public ItemRetrievetFrm(JFrame parent) {
        initComponents();
        this.frame = parent;
        loadAllData();
    }
    
    public JFrame getFrame(){
        return this.frame;
    }
    
    public ItemRetrievetFrm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblItems = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtFilter = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnRetirar = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Elementos");

        tblItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblItems);

        jLabel1.setText("Filtro");

        btnSearch.setText("Filtrar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnRetirar.setText("Retirar");
        btnRetirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarActionPerformed(evt);
            }
        });

        btnLoad.setText("Recargar");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 281, Short.MAX_VALUE)
                .addComponent(btnRetirar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnRetirar)
                    .addComponent(btnLoad))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Sacar Elementos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarActionPerformed
       Integer rowId = tblItems.getSelectedRow();
        if (rowId != -1){
            Item item = Item.builder()
                    .id((Long) tblItems.getModel().getValueAt(rowId, 0))
                    .code((String) tblItems.getModel().getValueAt(rowId, 1))
                    .name((String) tblItems.getModel().getValueAt(rowId, 2))
                    .count((Integer)tblItems.getModel().getValueAt(rowId, 3))
                    .description((String) tblItems.getModel().getValueAt(rowId, 4))
                    .build();
            ItemFormRetrieveFrm itemForm = new ItemFormRetrieveFrm(this.frame, true, item, ActionEnum.EDIT);
            itemForm.setVisible(true);
            this.loadAllData();
        } else {
           JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento del inventario"); 
        }   
    }//GEN-LAST:event_btnRetirarActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        this.loadAllData();
        this.txtFilter.setText("");
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        loadFiltered();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void loadFiltered(){
        ItemService itemService = (ItemService)this.getSpringBean("itemService");
        List<Item> items = itemService.getfilteredItems(txtFilter.getText());
        loadData(items);
    }
    
    private void loadData(List<Item> items){
        DefaultTableModel tm = new DefaultTableModel();
        tm.addColumn("Id");
        tm.addColumn("Codigo");
        tm.addColumn("Nombre");
        tm.addColumn("Cantidad");
        tm.addColumn("Descripción");
        for (Item item: items){
            Object[] row = {
                item.getId(),
                item.getCode(),
                item.getName(),                
                item.getCount(),
                item.getDescription()
            };
            tm.addRow(row);
        }
        tblItems.setModel(tm);
        tblItems.getColumnModel().getColumn(0).setPreferredWidth(5);
    }
    
    private void loadAllData(){
        ItemService itemService = (ItemService)this.getSpringBean("itemService");
        List<Item> items = itemService.getItems();
        loadData(items);
    
    }
    
    private Object getSpringBean(String beanName){
        return ((Parent) this.frame).getSpringBean(beanName);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnRetirar;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblItems;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables
}