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
public class Map implements Serializable{
    private long id;
    private Tile[][] mapField;
    
    public static final int MAP_HEIGHT = 10;
    public static final int MAP_WEIGHT = 10;
    
    public Map(){
        mapField = new Tile[MAP_HEIGHT][MAP_WEIGHT];
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
    
}
