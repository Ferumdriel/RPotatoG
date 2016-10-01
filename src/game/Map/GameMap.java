/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Map;

import java.io.Serializable;

/**
 *
 * @author BotNaEasyEnv
 */
public class GameMap implements Serializable{
    private long id;
    private Tile[][] mapField;
    
    private int mapHeight;
    private int mapWeight;
    

    public GameMap(int h, int w){
        this.mapHeight = h;
        this.mapWeight = w;
        mapField = new Tile[mapHeight][mapWeight];
    }
    
    public void doEmptyMap(){
        for(int i=0;i<mapField.length;i++){
            for(int j=0;j<mapField[i].length;j++){
                mapField[i][j]=new Tile();
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tile[][] getMapField() {
        return mapField;
    }

    public void setMapField(Tile[][] mapField) {
        this.mapField = mapField;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWeight() {
        return mapWeight;
    }
    
    
    
}
