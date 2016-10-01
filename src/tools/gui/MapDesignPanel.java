/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.gui;

import game.Map.GameMap;
import game.Map.Tile;
import game.Map.Tileset;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author BotNaEasyEnv
 */
public class MapDesignPanel extends javax.swing.JPanel implements MouseMotionListener, MouseListener{

    private Tile tile;
    private Point properPoint;
    private GameMap map;
    
    public int DEFAULT_MAP_HIGHT = 30;
    public int DEFAULT_MAP_WEIGHT = 30;
    /**
     * Creates new form MapDesignPanel
     */
    public MapDesignPanel() {
        initComponents();
        addMouseMotionListener(this);
        addMouseListener(this);
        initializeMap();
    }
    
    private void initializeMap(){
        map = new GameMap(DEFAULT_MAP_HIGHT, DEFAULT_MAP_WEIGHT);
        doEmptyMap();
    }
    
    public void doEmptyMap(){
        map.doEmptyMap();
        repaint();
    }
    
    public void saveMap(File parent){
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();
        String fileName = dateFormat.format(date)+".map";
        File mapFile = new File(parent.getAbsolutePath()+"/"+fileName);
        doSerialization(mapFile);
    }
    
    private void doSerialization(File file){
        try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(map);
            out.close();
            JOptionPane.showMessageDialog(this, "Your map is now safe", "Good Save!", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error while saving!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void loadMap(File mapFile){
        try{
            FileInputStream fis = new FileInputStream(mapFile);
            ObjectInputStream in = new ObjectInputStream(fis);
            map = (GameMap) in.readObject();
            in.close();
            loadImages();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error while loading!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadImages() throws Exception{
      List<Long> idsTileset = getAllNeededTilesetIds();
      String query = prepareQueryIdsTileset(idsTileset);
      List<Tileset> neededTilesets = Tileset.loadAll4ids(MapCreator.getDB(), query);
      List<BufferedImage> tilesetImage = fillTilesetsImage(neededTilesets);
      fillMapTiles(idsTileset, tilesetImage);
      repaint();
    }
    
    private void fillMapTiles(List<Long> idsTileset, List<BufferedImage> imgsTileset){
        for(int i=0;i<map.getMapWeight();i++){
            for(int j=0;j<map.getMapHeight();j++){
                Tile tile = map.getMapField()[i][j];
                int index = idsTileset.indexOf(tile.getIdTileset());
                if(index>=0){
                    tile.setTile(imgsTileset.get(index).getSubimage(tile.getxTileset(), tile.getyTileset(), tile.getA(), tile.getA()));
                }
            }
        }
    }
    
    private List<BufferedImage> fillTilesetsImage(List<Tileset> tilesets) throws IOException{
        List<BufferedImage> images = new ArrayList<BufferedImage>();
        for(Tileset tileset : tilesets){
            images.add(ImageIO.read(MapDesignPanel.class.getResourceAsStream(tileset.getResourcePath())));
        }
        return images;
    }
    
    private String prepareQueryIdsTileset(List<Long> ids){
        String query = "";
        for(int i=0;i<ids.size();i++){
            query+=""+ids.get(i);
            if(ids.size()-1>i){
                query+=",";
            }
        }
        return query;
    }
    
    private List<Long> getAllNeededTilesetIds(){
        List<Long> idsTileset = new ArrayList<Long>();
        for(int i=0;i<map.getMapWeight();i++){
          for(int j=0;j<map.getMapHeight();j++){
              Long idTileset = map.getMapField()[i][j].getIdTileset();
              if(!idsTileset.contains(idTileset)&&idTileset!=0){
                  idsTileset.add(idTileset);
              }
          }
      }
      return idsTileset;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void mouseDragged(MouseEvent me) {
        mouseActivation(me.getX(), me.getY());
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        if(tile!=null){
            grphcs.drawRect(0,0, tile.getA()*map.getMapHeight(), tile.getA()*map.getMapWeight());
        }
            //grphcs.drawImage(tile.getTile(), (int)properPoint.getX(), (int)properPoint.getY(), null);
            
        for(int i=0;i<map.getMapField().length;i++){
            for(int j=0;j<map.getMapField()[i].length;j++){
                Tile tile = map.getMapField()[i][j];
                grphcs.drawImage(tile.getTile(),tile.getA()*i ,tile.getA()*j, null);
            }
        }
        
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }
    
    private void setGameMapPoint(){
        map.getMapField()[(int)(properPoint.getX()/tile.getA())][(int)(properPoint.getY()/tile.getA())] = tile;
    }
    
    private boolean goodLocation(int x, int y){
        return x>=0&&y>=0&&x<map.getMapWeight()*tile.getA()&&y<map.getMapHeight()*tile.getA();
    }
    
     private void mouseActivation(int x, int y){
         if(tile!=null&&goodLocation(x,y)){
            properPoint = new Point(getProperX(x), getProperY(y));
            setGameMapPoint();
            repaint();
         }
     }
     
     private int getProperX(int x){
        int r = tile.getA();
        int w = tile.getTile().getWidth(null);
        return (((w/r)*x)/w)*r;
    }
    
    private int getProperY(int y){
        int r = tile.getA();
        int h = tile.getTile().getHeight(null);
        return (((h/r)*y)/h)*r;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        mouseActivation(me.getX(), me.getY());
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
