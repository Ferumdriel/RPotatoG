package Main;

import Actions.Event;
import Prezent.db.DB;
import Units.Unit;
import User.User.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Binio on 2016-07-26.
 */
public class Main {
    
    public static Main main;
    private final Properties properties = new Properties();
    private DB db;
    private User loggedUser;
    
    private String login;
    private String password;
    private boolean autoLogin = false;
    
    public Main() throws Exception{
        this(null);
    }
    
    public Main(String propertiesFilePath) throws Exception{
        loadProperties(propertiesFilePath);
        if(autoLogin){
            loadDB(login, password);
        }
    }
    
    private void loadDB(String user, String password) throws Exception{
        db = new DB(user, password);
        try{
            loggedUser = User.getCurrentUser(db);
        }catch(SQLException e){
            throw new SQLException("Error while logging to database!!!");
        }
    }
    
    private void loadProperties(String propertiesFilePath) throws IOException{
        if(propertiesFilePath!=null){
            System.setProperty("file.encoding", "UTF-8");
            properties.load(new FileInputStream(new File(propertiesFilePath)));
            login = properties.getProperty("login");
            password = properties.getProperty("password");
            autoLogin = Boolean.parseBoolean(properties.getProperty("autoLogin"));
        }
    }

    public DB getDb() {
        return db;
    }
    
    public DB getClonedDB() throws Exception{
        DB dbCloned = DB.cloneDB(getDb());
        return dbCloned;
    }
    
    public User getLoggedUser(){
        return loggedUser;
    }

    public static void main(String[] args) throws Exception{
        
        main = new Main(args[0]);
        
        main.getLoggedUser();
        
        Unit ally = new Unit("Andrzej", 170, 15);
        Unit enemy = new Unit("Bombur", 100, 25);

        Event e1 = new Event(ally,enemy);
        e1.fight();
        System.out.println(ally);
        ally.heal();



    }
}
