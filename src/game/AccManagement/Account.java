package game.AccManagement;

import game.Units.Unit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Binio on 2016-09-01.
 */
public class Account {
    private String login;
    private String pass;
    private ArrayList<String> charName = new ArrayList<>();
    private Unit currLoggedChar;


    public Account(String login, String pass) {
        this.login = bigLogin(login);
        this.pass = pass;
        while (!checkIfFolderCreated()) {
            System.out.println("Account already exists. Try another name");
            Scanner sc = new Scanner(System.in);
            this.login = bigLogin(sc.nextLine());
        }
        System.out.println("Account created succesfully");
    }

    private String bigLogin(String login) {
        String[] tmp = login.split("");
        tmp[0] = tmp[0].toUpperCase();
        String tmp1 = "";
        for (String x : tmp) {
            tmp1 += x;
        }
        return tmp1;
    }

    /************************************************************
     * DISPLAYING ACC INFO
     ************************************************************/

    public void displayCharList() {
        System.out.println("Viable characters:");
        for (String x : charName) {
            System.out.println(x);
        }
    }



    /****************************************** FILE MANAGEMENT ****************************************/


    /*****************************************
     * Risky way to make folders, might overwrite existing important folder. Better to create new one.
     *******************************************/

    /***************CHARACTER MANAGEMENT *****************************/

    /********************************************************
     * CHARATER CREATION
     ********************************************************/

    public void createChar(String name) {
        if (!charAlreadyExists(name)) {
            charName.add(name);
            currLoggedChar = new Unit(name);
            System.out.println(name + " created.");
        } else {
            System.out.println(name + " already exists");
        }
    }

    private boolean charAlreadyExists(String name) {
        boolean found = false;
        for (String x : charName) {
            if (x.equals(name)) {
                found = true;
            }
        }
        return found;
    }

    /******************** CHARACTER SAVING + METHODS CONNECTED ********************/
    public boolean saveChar() {
        checkIfFolderCreated();
        File save = new File(getProperAccPath() + "/" + currLoggedChar.getName() + ".txt");
        save.getParentFile().mkdirs();
        return printCharInfo(save);
    }

    private boolean printCharInfo(File save){
        try {
            Unit saved = currLoggedChar;
            PrintWriter print = new PrintWriter(save);
            print.println(saved.getName());
//            print.println(saved.getExp());
//            print.println(saved.getLevel());
            print.println(saved.getAd());
            print.close();
            System.out.println("Character saved.");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return false;
        }
    }
    /***********************************************************************/

    private boolean createBaseDirectory() {
        File acc = new File(getProperAccPath());
        return acc.mkdirs();
    }

    private boolean checkIfFolderCreated() {
        File acc = new File(getProperAccPath());
        return acc.mkdirs();
    }

//    private boolean checkIfAlreadyExists(){
//        File acc = new File(System.getProperty("user.dir") + "/ACCOUNTS/" + login.charAt(0) + "/" + login);
//        return acc.exists();
//    }

    private String getProperAccPath(){
        return System.getProperty("user.dir") + "/ACCOUNTS/" + login.charAt(0) + "/" + login;
    }




}
