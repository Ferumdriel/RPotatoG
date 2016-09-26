package Units;

/**
 * Created by Binio on 2016-09-22.
 */
public class Hero extends Unit{
    private int [] expRange = {0, 80, 100, 150, 200, 230, 250};
    protected int level;

    public Hero(String name){
        super(name);
        this.level = 1;
        this.exp = 0;
    }

    public Hero(String name, int health, int ad){
        super(name, health, ad);
        this.level = 1;
        this.exp = 0;
    }

    protected boolean checkExp(){
        return exp > flatExpToNext();
    }

    void lvlUp(){
        while(checkExp()){
            exp -= flatExpToNext();
            level++;
        }
    }

    public int addExp(int x){
        System.out.println("Dodano " + x + " expa.");
        return exp += x;
    }

    int flatExpToNext(){
        return expRange[level];
    }

    void print(){
        System.out.println(toString());
    }

    public String toString(){
        return name + " LVL: " + level + ", EXP: " + exp;
    }


}
