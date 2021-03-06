package eHerbs.strategies;

import eHerbs.data.data;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev377.min.api.methods.Menu;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.wrappers.Item;

/**
 * Created by EricTurner on 4/24/2014.
 */
public class clean implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.getCount(data.HERBID) < 0
                && Inventory.isFull();
    }

    @Override
    public void execute() {
    for (Item i : Inventory.getItems(data.HERBID)) {
        Menu.sendAction(74, data.HERBID, i.getSlot(), 3214);
        }
    }
}
