package game.AccManagement;

/**
 * Created by Binio on 2016-09-05.
 */
public class CreateFile {

    private String path;

    public CreateFile(String login, String unit){
        this.path = System.getProperty("user.dir") + "/ACCOUNTS/" + login + "/";
    }
}
