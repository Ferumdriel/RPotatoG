package Actions;

import Units.Unit;

/**
 * Created by Binio on 2016-08-08.
 */
public class Event {
    Unit ally;
    Unit enemy;

    public Event(Unit ally, Unit enemy) {
        this.ally = ally;
        this.enemy = enemy;
    }

    public void fight() {
        while (!ally.isDead() && !enemy.isDead()) {
            ally.hit(enemy);
            enemy.hit(ally);
        }
        if(ally.isDead()){System.out.println(ally + " nie zyje, zwyciezyl " + enemy);}
        else{System.out.println(enemy + " nie zyje, zwyciezyl " + ally);}
    }
    /** Probowalem stworzyc metode 'hit" w klasie Event, jednak wymuszaloby to na mnie stworzenie 2 identycznych metod,
     * pierwsza, gdy ally bije enemy i druga, gdy enemy bije ally
     */
}