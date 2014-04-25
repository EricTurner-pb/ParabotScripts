package eHerbs.strategies;

import eHerbs.data.data;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev377.min.api.methods.Menu;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.wrappers.Item;

/**
 * Created by EricTurner on 4/24/2014.
 */
public class drop implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.isFull()
                && Inventory.getCount(data.HERB) == 0
                && data.DOB == 0;
    }

    @Override
    public void execute() {
        for (Item i : Inventory.getItems(data.CLEANID)) {
            if (Inventory.getCount(data.CLEANID) < 0) {
                Menu.sendAction(847, data.CLEANID, i.getSlot(), 3214);
            }
        }
    }
}
