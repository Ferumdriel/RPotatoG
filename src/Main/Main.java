package Main;

import Actions.Event;
import Units.Unit;

/**
 * Created by Binio on 2016-07-26.
 */
public class Main {

    public static void main(String[] args){
        Unit ally = new Unit("Andrzej", 170, 15);
        Unit enemy = new Unit("Bombur", 100, 25);

        Event e1 = new Event(ally,enemy);
        e1.fight();
        System.out.println(ally);
        ally.heal();



    }
}
