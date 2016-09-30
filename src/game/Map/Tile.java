/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Map;

/**
 *
 * @author BotNaEasyEnv
 */
public class Tile {
    private long id;
    private int xTileset; //wspolrzedna x
    private int yTileset; //wspolrzedna y
    private int a; //dlugosc boku
    private long idTileset;
    //private int xMap;
    //private int yMap;
    

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

    /*
    public int getxMap() {
        return xMap;
    }

    public void setxMap(int xMap) {
        this.xMap = xMap;
    }

    public int getyMap() {
        return yMap;
    }

    public void setyMap(int yMap) {
        this.yMap = yMap;
    }
*/
    

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
    
    
    
}
