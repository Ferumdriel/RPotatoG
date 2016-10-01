/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Map;

import java.awt.Image;
import java.io.Serializable;

/**
 *
 * @author BotNaEasyEnv
 */
public class Tile implements Serializable {
    private long id;
    private int xTileset; //wspolrzedna x
    private int yTileset; //wspolrzedna y
    private int a; //dlugosc boku
    private long idTileset;
    private transient Image tile;
    
    
    public Tile(){
        id = 0;
        idTileset = 0;
    }
    
    public Tile(int xTileset, int yTileset, int a, long idTileset, Image tile){
        this.xTileset = xTileset;
        this.yTileset = yTileset;
        this.a = a;
        this.idTileset = idTileset;
        this.tile = tile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getxTileset() {
        return xTileset;
    }

    public void setxTileset(int xTileset) {
        this.xTileset = xTileset;
    }

    public int getyTileset() {
        return yTileset;
    }

    public void setyTileset(int yTileset) {
        this.yTileset = yTileset;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public long getIdTileset() {
        return idTileset;
    }

    public void setIdTileset(long idTileset) {
        this.idTileset = idTileset;
    }

    public Image getTile() {
        return tile;
    }

    public void setTile(Image tile) {
        this.tile = tile;
    }
    
}
