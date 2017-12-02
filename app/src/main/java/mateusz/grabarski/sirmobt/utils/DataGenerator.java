package mateusz.grabarski.sirmobt.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mateusz.grabarski.sirmobt.R;
import mateusz.grabarski.sirmobt.models.MainItem;

/**
 * Created by MGrabarski on 30.11.2017.
 */

public class DataGenerator {

    private static final int START_NUMBER_OF_ELEMENTS = 100;
    private static final int LOAD_NUMBER_OF_ELEMENTS = 20;

    private static final Random RANDOM = new Random();

    public static List<MainItem> getMainViewData() {
        return generateItems(START_NUMBER_OF_ELEMENTS);
    }

    public static List<MainItem> getLoadedItems() {
        return generateItems(LOAD_NUMBER_OF_ELEMENTS);
    }

    @NonNull
    private static List<MainItem> generateItems(int number) {
        List<MainItem> mainItems = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            MainItem item = new MainItem("item", getImage(), false);
            mainItems.add(item);
        }

        return mainItems;
    }

    private static int getImage() {
        switch (RANDOM.nextInt(3)) {
            default:
            case 0:
                return R.drawable.zom_scratch_animation_04;
            case 1:
                return R.drawable.zombie_pig_01;
            case 2:
                return R.drawable.zombie_pig_throw_04;
        }
    }
}
