/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.gui;

import game.Map.Tile;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;

/**
 *
 * @author BotNaEasyEnv
 */
public class MapPanel extends javax.swing.JPanel{

    
    private MapDesignPanel designMap = new MapDesignPanel();
    private TilesetPanel tilePanel = new TilesetPanel();
    /**
     * Creates new form MapPanel
     */
    public MapPanel() {
        initComponents();
        designMapPanel.add(new JScrollPane(designMap));
        tilesetPanel.add(new JScrollPane(tilePanel));
        addListeners();
        setVisible(true);
    }
    
    public void addListeners(){
        tilePanel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                designMap.setTile((Tile) ae.getSource());
            }
        });
    }
    
    public MapDesignPanel getMapDesign(){
        return designMap;
    }
    
    public TilesetPanel getTilePanel(){
        return tilePanel;
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        designMapPanel = new javax.swing.JPanel();
        tilesetPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(500);

        designMapPanel.setLayout(new java.awt.BorderLayout());
        jSplitPane1.setLeftComponent(designMapPanel);

        tilesetPanel.setLayout(new java.awt.BorderLayout());
        jSplitPane1.setRightComponent(tilesetPanel);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel designMapPanel;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel tilesetPanel;
    // End of variables declaration//GEN-END:variables

}
