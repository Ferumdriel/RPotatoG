package game.AccManagement;

/**
 * Created by Binio on 2016-09-22.
 */
public class AccTest {

    public static void main(String[] args){
        Account ac = new Account("login", "pass");
        ac.createChar("ally");
        ac.createChar("enemy");
        ac.createChar("ally");
        ac.displayCharList();
        ac.saveChar();

        Account ac1 = new Account("login", "pass1");
        ac1.createChar("ally1");
        ac1.saveChar();
    }
}
