/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Prezent.db;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author BotNaEasyEnv
 */

/*
*Abstrakcyjny obiekt bazo danowy
*/
public abstract class BaseObject  implements Serializable{
    
    /*
    *Pola które powinny znajdować się w każdej tabeli w BD
    */
    protected long id;
    protected Timestamp startTime;
    protected Long startUser;
    protected Timestamp upTime;
    protected Long upUser;
    protected String description;
    
    /*
    *Nazwa tabeli
    */
    public abstract String getTableName();
    /*
    *Nazwa Zwykła
    */
    public abstract String getHumanName();
    
    /*
    *Parametry do zapisu i aktualizacji danych
    */
    public abstract ParametersBuilder saveOrUpdateParameters();

    public BaseObject() {
    }
    
    public BaseObject(long id) {
        this.id = id;
    }
    
    /*
    *Ładowanie wszystkich obieków z tabeli zgodnie z podanym warunkiem
    */
    
    public static <T extends BaseObject> List<T>  loadAllWithCondition(Class<T> clazz, String condition, DB db) throws Exception{
        List<T> readyList = new ArrayList<>();
        T justTempInstance = clazz.newInstance();
        String query = justTempInstance.getSelectStatement() + " WHERE "+condition;
        db.query(query);
        while(db.next()){
            T object = clazz.newInstance();
            ((BaseObject)object).setFields(db.getRow(), object);
            readyList.add(object);
        }
        return readyList;
    }
    
    /*
    *Parametry zapisu do BD
    */
    
    protected ParametersBuilder getSaveParameters(){
        return new ParametersBuilder(
                saveOrUpdateParameters(),
                getStartUser(),
                getDescription()
        );
    }
    
    /*
    *Parametry aktualizacji do DB
    */
    
    protected ParametersBuilder getUpdateParameters(){
        return new ParametersBuilder(
                getId(),
                saveOrUpdateParameters(),
                getUpUser(),
                getDescription()
        );
    }
    
    /*
    *Parametr do usunięcia z BD
    */
    
    protected ParametersBuilder getDeleteParameters(){
        return new ParametersBuilder(getId());
    }
    
    /*
    *Odpowiednio nazwy funkcji istniejących na BD do zapisu, aktualizacji i usuwania danych
    */
    
    protected String getSaveFunctionName(){
        return "add_"+getTableName();
    }
    
    protected String getUpdateFunctionName(){
        return "update_"+getTableName();
    }
    
    protected String getDeleteFunctionName(){
        return "delete_"+getTableName();
    }
    
    /*
    *Usunięcie obiektu z BD
    */
    
    public void deleteData(DB db) throws SQLException{
        if(getId()<=0){
            throw new SQLException("This object not exists in DB!");
        }
        
        db.query("SELECT "+getDeleteFunctionName()+"("+getDeleteParameters()+")");
        
        db.next();
        
        setId(0);
    }
    
    /*
    *Aktualizacja obiektu BD
    */
    
    public void updateData(DB db, boolean fillData) throws SQLException{
        if(getId() <= 0){
            throw new SQLException("This object not exists in DB!");
        }
        
        db.query("SELECT "+getUpdateFunctionName()+"("+getUpdateParameters()+") as result");
        
        db.next();
        
        if(fillData){
            loadData(db);
        }
    }
    
    /*
    *Zapis obiektu BD
    */
    
    public void saveData(DB db, boolean fillData) throws SQLException{
        if(getId() > 0){
            throw new SQLException("This object exists already in DB!");
        }
        
        db.query("SELECT "+getSaveFunctionName()+"("+getSaveParameters()+") as result");
        
        db.next();
        
        setId(db.getLong("result"));
        
        if(fillData){
            loadData(db);
        }
        
    }
    
    /*
    *Załadowanie obiektu BD
    */
    public void loadData(DB db) throws SQLException{
        if(getId()<=0){
            throw new SQLException("Impossible id for "+getHumanName());
        }
        
        String query = getSelectStatement() + " "+getWhereStatementForThis();
        
        db.query(query);
        db.next();
        
        Map<String, Object> object = db.getRow();
        
        setFields(object, this);
    }
    
    private void setFields(Map<String, Object> row, Object object){
        List<Field> fields = new ArrayList<>();
        fillFields(fields, getClass());
        
        for(Field field : fields){
            String fieldName = field.getName();
            String seterName = getSeterName(fieldName);
            try{
                Method method = getMethod(getClass(), seterName, field.getType());
                Object fieldValue = getFieldValue(fieldName, row);
                method.invoke(object, fieldValue);
            }catch(Exception e){
                System.out.println("Something went wrong to set field "+fieldName);
            }
        }
    }
    
    private Method getMethod(Class clazz, String seterName, Class type){
        Method method = null;
        try{
            method = clazz.getDeclaredMethod(seterName, type);
        }catch(Exception e){
            if(clazz.getSuperclass()!=null){
                return getMethod(clazz.getSuperclass(),seterName, type);
            }
        }
        return method;
    }
    
    private void fillFields(List<Field> fields, Class clazz){
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        
        if(clazz.getSuperclass()!=null){
            fillFields(fields, clazz.getSuperclass());
        }
    }
    
    
    private Object getFieldValue(String fieldName, Map<String, Object> currentObject){
        String key = "";
        for(int i=0;i<fieldName.length();i++){
            char letter = fieldName.charAt(i);
            if(Character.isLowerCase(letter)){
                key+=letter;
            }else{
                key+="_"+Character.toLowerCase(letter);
            }
        }
        return currentObject.get(key);
    }
    
    private String getSeterName(String fieldName){
        String name = "set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1, fieldName.length());
        return name;
    }
    
    
    /*
    *Można nadpisywać jeśli to nie jedna tabela a wiele (uzywać joinów)
    */
    
    public String getSelectStatement(){
        return "SELECT * FROM "+getTableName();
    }
    
    protected String getWhereStatementForThis(){
        return "WHERE id = "+getId();
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public long getStartUser() {
        return startUser;
    }

    public void setStartUser(long startUser) {
        this.startUser = startUser;
    }

    public Timestamp getUpTime() {
        return upTime;
    }

    public void setUpTime(Timestamp upTime) {
        this.upTime = upTime;
    }

    public long getUpUser() {
        return upUser;
    }

    public void setUpUser(long upUser) {
        this.upUser = upUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
