/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.User.User;

import game.Prezent.db.BaseObject;
import game.Prezent.db.DB;
import game.Prezent.db.ParametersBuilder;
import java.sql.SQLException;

/**
 *
 * @author BotNaEasyEnv
 */
public class User extends BaseObject{
    
    private String login;
    private String password;
    private boolean active;
    
    public static User getCurrentUser(DB db) throws SQLException{
        String query = "select id "
                             + "from bd_user "
                             + "where login = current_user";
        db.query(query);
        db.next();
        User currentUser = new User(db.getLong("id"));
        currentUser.loadData(db);
        return currentUser;
    }

    public User() {
    }

    public User(long id) {
        super(id);
    }

    
    @Override
    public String getTableName() {
        return "bd_user";
    }

    @Override
    public String getHumanName() {
        return "user";
    }

    @Override
    public ParametersBuilder saveOrUpdateParameters() {
        return new ParametersBuilder();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
