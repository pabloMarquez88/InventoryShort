/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.is.gui.misc;

import com.mycompany.is.Parent;
import com.mycompany.is.entity.HistoryItem;
import com.mycompany.is.entity.Item;
import com.mycompany.is.service.HistoryService;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Escritorio
 */
public class ItemExportImportFrm extends javax.swing.JInternalFrame {
    
    private static ItemExportImportFrm INSTANCE;
    private JFrame frame;
    
    public static ItemExportImportFrm getInstance(JFrame parent){
        if (INSTANCE == null) {
            return new ItemExportImportFrm(parent);
        }        
        return INSTANCE;
    }
    
    public ItemExportImportFrm(JFrame parent) {
        initComponents();
        this.frame = parent;
        loadFiltered();
    }
    
    private Object getSpringBean(String beanName){
        return ((Parent) this.frame).getSpringBean(beanName);
    }
    
    /**
     * Creates new form ItemListFrm
     */
    public ItemExportImportFrm() {
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

        jLabel1 = new javax.swing.JLabel();
        txtFilter = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        scrollpane = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consulta Historial");

        jLabel1.setText("Filtro");

        btnFiltrar.setText("filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollpane.setViewportView(tblHistory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFiltrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        loadFiltered();
    }//GEN-LAST:event_btnFiltrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane scrollpane;
    private javax.swing.JTable tblHistory;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables

    private void loadFiltered() {
        HistoryService historyService = (HistoryService) this.getSpringBean("historyService");
        List<HistoryItem> items = historyService.getHistoryFiltered(this.txtFilter.getText());
        DefaultTableModel tm = new DefaultTableModel();
        tm.addColumn("Id");
        tm.addColumn("Item");
        tm.addColumn("Fecha");
        tm.addColumn("Cantidad anterior");
        tm.addColumn("Cantidad posterior");
        tm.addColumn("Accion");
        tm.addColumn("PC");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        for (HistoryItem item: items){
            Object[] row = {
                item.getId(),
                item.getItem().getName(),
                format.format(item.getDate()),                
                item.getOldAmount(),
                item.getNewAmount(),
                item.getAction(),
                item.getPcName()
            };
            tm.addRow(row);
        }
        this.tblHistory.setModel(tm);
        this.tblHistory.getColumnModel().getColumn(0).setPreferredWidth(5);
        
    }
}
