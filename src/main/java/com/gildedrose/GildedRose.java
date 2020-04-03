package com.gildedrose;

import java.util.ArrayList;
import java.util.List;

public class GildedRose {
    Item[] items;
    private final Item[] itemsV2;
    private final ItemStrategies strategies;

    public GildedRose(Item[] items) throws Exception {
        this(items, items, new ItemStrategies());
    }

    public GildedRose(Item[] items, ItemStrategies strategies) throws Exception {
        this(items, items, strategies);
    }

    public GildedRose(Item[] items, Item[] itemsV2, ItemStrategies strategies) {
        this.items = items;
        this.itemsV2 = itemsV2;
        this.strategies = strategies;
    }

    public GildedRose updateQuality() throws Exception {
        Item[] updatedItems = updateItems();
        return new GildedRose(
            updatedItems,
            new ItemStrategies().build(updatedItems));
    }

    private Item[] updateItems() {
        List<Item> tempItems = new ArrayList<>();
        for (Item item: itemsV2) {
            tempItems.add(strategies.update(item));
        }
        return tempItems.toArray(new Item[tempItems.size()]);
    }

    public Boolean hasItem(Item item) {
        for (Item currItem: itemsV2) {
            if (currItem.name.equals(item.name) && currItem.quality == item.quality && currItem.sellIn == item.sellIn) {
                return true;
            }
        }
        return false;
    }

    public Boolean hasNegativeQualityItems() {
        for (Item item: itemsV2) {
            if (item.quality < 0) {
                return true;
            }
        }
        return false;
    }

    public Boolean hasItemQualityOver50() {
        for (Item item: itemsV2) {
            if (!item.name.equals("Sulfuras, Hand of Ragnaros") && item.quality > 50) {
                return true;
            }
        }
        return false;
    }

    public Boolean areAllSulfurasQualityAt80() {
        for (Item item: itemsV2) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros") && item.quality != 80) {
                return false;
            }
        }
        return true;
    }

    public Boolean areAllSulfurasNotOnSale() {
        for (Item item: itemsV2) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros") && item.sellIn > 0) {
                return false;
            }
        }
        return true;
    }

}
