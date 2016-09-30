/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Map;

import game.Prezent.db.BaseObject;
import game.Prezent.db.DB;
import game.Prezent.db.ParametersBuilder;
import java.util.List;

/**
 *
 * @author BotNaEasyEnv
 */
public class Tileset extends BaseObject{
    private String resourcePath;
    private int sideSize;


    public static List<Tileset> loadAll(DB db) throws Exception{
        return loadAllWithCondition(Tileset.class, "true", db);
    }
    
    @Override
    public String getTableName() {
        return "map_tileset";
    }

    @Override
    public String getHumanName() {
        return "tileset";
    }

    @Override
    public ParametersBuilder saveOrUpdateParameters() {
        return new  ParametersBuilder();
    }
    
    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public int getSideSize() {
        return sideSize;
    }

    public void setSideSize(int sideSize) {
        this.sideSize = sideSize;
    }  
    
}
