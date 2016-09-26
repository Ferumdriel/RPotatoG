package Units;

import Actions.Event;

/**
 * Created by Binio on 2016-09-22.
 */
public class UnitTest {

    public static void main(String[] args){
        Hero ally = new Hero("Andrzej", 170, 15);
        ally.print();
        ally.addExp(90);
        ally.print();
        ally.lvlUp();
        ally.print();
        ally.addExp(250);
        ally.print();
        ally.lvlUp();
        ally.print();
//        Unit ally = new Unit("Andrzej", 170, 15);
//        Unit enemy = new Unit("Bombur", 100, 25);
//
//        Event e1 = new Event(ally,enemy);
//        e1.fight();
//        System.out.println(ally);
//        ally.heal();
    }
}
