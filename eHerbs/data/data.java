package eHerbs.data;

import javafx.stage.Stage;
import org.soulsplit.api.wrappers.Tile;
import org.soulsplit.api.wrappers.TilePath;

/**
 * Created by EricTurner on 4/24/2014.
 */
public class data {
    public static int HERB;
    public static int DOB;
    public static int HERBID;
    public static int CLEANID = 249 | 251 | 253 | 255;
    public static Tile[] toBank = {new Tile(2354, 3796, 0), new Tile(2350, 3801, 0), new Tile(2344, 3802, 0), new Tile(2336, 3804, 0), new Tile(2336 , 3807)};
    public static Tile[] toHerb = {new Tile(2336 , 3807), new Tile(2336, 3804, 0), new Tile(2344, 3802, 0), new Tile(2350, 3801, 0), new Tile(2354, 3796, 0)};
    public static TilePath toTheBank = new TilePath(toBank);
    public static TilePath toTheShop = new TilePath(toHerb);



/*
    herb to bank
    2354, 3796 ; 2350, 3801 ; 2344, 3802 ; 2336, 3804 ; 2336, 3807
    bank to herb
    2336, 3807 ; 2336, 3804 ; 2344, 3802 ; 2350, 3801 ; 2354 3796
            */
}
