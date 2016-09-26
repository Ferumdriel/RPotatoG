package Units;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Binio on 2016-07-26.
 */
public class Unit {
    /**
     * protected, a nie private, bo te cechy beda dziedziczone przez klase Klasa/Profession
     **/
    protected String name;
    protected int maxHealth;
    protected int health;
    protected int ad;
    protected Inventory inventory;
    protected int exp;

    /**
     * LVL 1
     **/
    public Unit(String name) {
        this.name = name;
        health = 150;
        maxHealth = health;
        ad = 10;
    }

    /**
     * FOR TESTING
     **/
    public Unit(String name, int health, int ad) {
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.ad = ad;
    }

    public void hit(Unit enemy) {
        if (!isDead() && !enemy.isDead()) { //if attacker or attacked is dead he can't attack
            int dmg = calcDmg(enemy);
            hitInfo(dmg, enemy);
        }
    }

    private void hitInfo(int dmg, Unit enemy) {
        String s = name + " uderzyl " + enemy.name + " za: " + dmg + ", pozostalo HP (" + enemy.name + "): " + (enemy.health -= dmg);
        if (enemy.isDead()) {   //if attacked is dead send info about this
            s += "\n" + deathInfo(enemy);
        }
        System.out.println(s);
    }

    protected int calcDmg(Unit enemy) {
        Random r = new Random();
        return Math.min(r.nextInt(ad + 1), enemy.health);
    }

    public void heal() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which potion should be used? (50/100/150/300/700)");
        int i = sc.nextInt();
        boolean used = false;
        do {
            switch (i) {
                case (50):
                    usePotion(50);
                    used = true;
                    break;
                default:
                    System.out.println("Nie ma takiego eliksiru.");
                    i = sc.nextInt();
            }
        } while (!used);
        sc.close();
    }

    private boolean usePotion(int regen) {
        if (health != maxHealth) {
            int previousHP = health;
            health = Math.min(maxHealth, health + regen);
            System.out.println("Przywrocono " + (Math.min(regen, maxHealth - previousHP)) + " HP. " + toString());
            return true;
        } else return false;
    }

    public boolean isDead() {
        return health == 0;
    }

    protected String deathInfo(Unit u) {
        return u.name + " nie zyje.\n";
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getAd() {
        return ad;
    }

    public String toString() {
        return name + ", HP: " + health + "/" + maxHealth + ", avg. dmg: " + (double) ad / 2;
    }
}
