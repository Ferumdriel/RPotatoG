/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prezent.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author BotNaEasyEnv
 */

/*
 * klasa bazy danych. Do działania lokalnego potrzeba silnik postgresa: http://www.enterprisedb.com/products-services-training/pgdownload#windows
 * Ewentualnie postawić baze na jakimś zewnętrznym serwisie np. http://www.heliohost.org/ - ale tego serwisu nie polecam bo chujowy
*/
public class DB {
    
    public static  String DRIVER_CLASS = "org.postgresql.Driver";
    private static String DBURL ="jdbc:postgresql://localhost:5432/testdb";
    private static String DBUSER = "postgres";
    private static String DBPASSWORD = "";
    
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private Map<String, Object> row;
    private static final Object lock = new Object();
    private String query;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
         DB db = new DB(DRIVER_CLASS,DBURL,  DBUSER, DBPASSWORD);
         
         DBTest testZapis = new DBTest();
         testZapis.setTestColumn("test zapisu");
         testZapis.saveData(db, false);
         
         DBTest test = new DBTest(3);
         test.loadData(db);
         System.out.println(test);
         
         List<DBTest> list = DBTest.loadAll(db);
         System.out.println(list);
         
         test.setTestColumn("test update");
         test.updateData(db, true);
         System.out.println(test);
         
         test.deleteData(db);
       
    }
    public DB(String user, String password) throws Exception{
        this(DRIVER_CLASS, DBURL, user, password);
    }
    
    public DB(String driver, String url, String user, String password) throws Exception{
        if(!prepareDB(driver, url, user, password)){
            throw new Exception("DB connection exception ;(");
        }
    }
    
    private DB(Connection connection){
        this.connection = connection;
        createStatement();
    }
    
    /*
    * Klonowanie obiektu bazy danych w przypadku częstych zapytań
    */
    
    public static DB cloneDB(DB db) throws Exception{
        DB db2 = null;
        synchronized(lock){
            db2 = new DB(db.getConnection());
        }
        return db2;
    }
    
    private Connection getConnection(){
        return connection;
    }
    
    /*
    *Zapytanie do BD
    */
    
    public void query(String query) throws SQLException{
        try {
            setQuery(query);
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Error in "+getQuery());
            ex.printStackTrace();
            connection.rollback();
            throw ex;
        }
        connection.commit();
    }
    
    /*
    *Wynik zapytania z query
    */
    public boolean next() throws SQLException{
         boolean next =  resultSet.next();
         if(next){
             setRow();
         }
         return next;
    }
    
    public Map<String, Object> getRow(){
        return row;
    }
    
    private void setRow() throws SQLException{
        row.clear();
        ResultSetMetaData meta = resultSet.getMetaData();
        for(int i =1;i<=meta.getColumnCount();i++){
            row.put(meta.getColumnLabel(i), resultSet.getObject(meta.getColumnLabel(i)));
        }
    }

    private boolean prepareDB(String driver, String url, String user, String password){
        if(connect(driver, url, user, password)&&createStatement()){
            return true;
        }
        return false;
    }
    
    private boolean createStatement(){
        try {
            statement = connection.createStatement();
            row = new HashMap<String, Object>();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    private boolean disconnect(){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    private boolean connect(String driver, String url, String user, String password){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("Can't find postgres drivers!");
            return false;
        }
        try {
            connection = DriverManager.getConnection(url,user, password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't connect!");
            return false;
        }
        if(connection==null){
            System.out.println("Something went wrong, can't find connection!");
            return false;
        }
        return true;
    }
    
     public Long getLong(String column) throws SQLException{
        return resultSet.getLong(column);
    }
    
    public Integer getInt(String column) throws SQLException{
        return resultSet.getInt(column);
    }
    public String getString(String column) throws SQLException{
        return resultSet.getString(column);
    }
    
    public Date getDate(String column) throws SQLException{
        return resultSet.getDate(column);
    }
    
    public Timestamp getTimestamp(String column) throws SQLException{
        return resultSet.getTimestamp(column);
    }
    
    public Double getDouble(String column) throws SQLException{
        return resultSet.getDouble(column);
    }
    
    public Boolean getBoolean(String column) throws SQLException{
        return resultSet.getBoolean(column);
    }
    
    public Object getObject(String column) throws SQLException{
        return resultSet.getObject(column);
    }
    
    public BigDecimal getBigDecimal(String column) throws SQLException{
        return resultSet.getBigDecimal(column);
    }
    
    public Float getFloat(String column) throws SQLException{
        return resultSet.getFloat(column);
    }
    
    public Byte getByte(String column) throws SQLException{
        return resultSet.getByte(column);
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    
    
}
