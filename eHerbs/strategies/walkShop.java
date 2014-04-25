package eHerbs.strategies;

import eHerbs.data.data;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.Loader;
import org.soulsplit.api.methods.Inventory;

/**
 * Created by EricTurner on 4/25/2014.
 */
public class walkShop implements Strategy {
    @Override
    public boolean activate() {
        return !Inventory.isFull()
                && !data.toTheShop.hasReached();
    }

    @Override
    public void execute() {
        if (!data.toTheShop.hasReached()) {
            data.toTheShop.traverse();
        Time.sleep(3000);
        }
    }
}
