package eHerbs.strategies;

import eHerbs.data.data;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev377.min.api.methods.Menu;
import org.soulsplit.api.methods.Bank;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.SceneObjects;

/**
 * Created by EricTurner on 4/25/2014.
 */
public class bank implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.isFull()
                && data.toBank[0].distanceTo() <= 5
                && data.DOB == 1;
    }

    @Override
    public void execute() {
        Menu.sendAction(502, 1422745634, 21301, -1);
        Bank.open(SceneObjects.getClosest(21301));
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Bank.isOpen();
            }
        }, 2000);
        Bank.depositAllExcept(995);
        Bank.close();
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return !Bank.isOpen();
            }
        }, 750);
        /*
        [index: 1, action1: -1, action2: -1, action3: 5384, id: 200]
        [index: 3, action1: 1422745634, action2: 21301, action3: -1, id: 502]
         */
    }
}
