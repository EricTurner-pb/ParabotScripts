package eHerbs.strategies;

import eHerbs.data.data;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev377.min.api.methods.Menu;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Npcs;
import org.soulsplit.api.wrappers.Npc;

/**
 * Created by EricTurner on 4/24/2014.
 */
public class buy implements Strategy {
    @Override
    public boolean activate() {
        return !Inventory.isFull()
                && data.toHerb[0].distanceTo() <= 10;
    }

    @Override
    public void execute() {
        for (final Npc i : Npcs.getNearest(436)) {
            if (i.getLocation().distanceTo() > 9) {
                i.getLocation().walkTo();
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return i.distanceTo() < 8;
                    }
                }, 2000);
            }else{
                Menu.sendAction(20, 436, 0, 0);
                Time.sleep(1200);
                Menu.sendAction(53, data.HERBID, data.HERB, 3900);
                Keyboard.getInstance().sendKeys("27");
                Time.sleep(750);
                Menu.sendAction(200, -1, -1, 3902);
                /*
                [index: 4, action1: 436, action2: 0, action3: 0, id: 20]
                [index: 2, action1: 199, action2: 0, action3: 3900, id: 53]
                [index: 2, action1: 201, action2: 1, action3: 3900, id: 53]
                [index: 2, action1: 203, action2: 2, action3: 3900, id: 53]
                [index: 2, action1: 205, action2: 3, action3: 3900, id: 53]
                [index: 2, action1: 14836, action2: 4, action3: 3900, id: 53]

                herb to bank
                2354, 3796 ; 2350, 3801 ; 2344, 3802 ; 2336, 3804 ; 2336, 3807
                bank to herb
                2336, 3807 ; 2336, 3804 ; 2344, 3802 ; 2350, 3801 ; 2354 3796
                 */
            }
        }
    }
}
