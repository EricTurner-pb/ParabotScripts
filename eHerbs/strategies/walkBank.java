package eHerbs.strategies;

import eHerbs.data.data;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.Inventory;

/**
 * Created by EricTurner on 4/25/2014.
 */
public class walkBank implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.getCount(data.HERBID) < 0
                && Inventory.getCount(data.CLEANID) > 0
                && Inventory.isFull()
                && !data.toTheBank.hasReached()
                && data.DOB == 1;
    }

    @Override
    public void execute() {
        data.toTheBank.traverse();
        Time.sleep(3000);
    }
}
