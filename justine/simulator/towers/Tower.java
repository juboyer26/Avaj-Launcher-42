package justine.simulator.towers;

import justine.simulator.aircraft.Flyable;
import java.util.*;

public class Tower {
    private List<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable flyable) {
        if (observers.contains(flyable))
            return;
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionChanged() {
        for(int i = 0; i < observers.size(); i++)
        {
            // System.out.println(observers.size());
            observers.get(i).updateCondtions();
        }
    }
}
