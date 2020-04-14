package com.gildedrose;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class GildedRose {
    Item[] items;
    private final Items itemsV1;

    public GildedRose(Item[] items) throws Exception {
        this(items, new Items(items));
    }

    GildedRose(Item[] items, Items itemsV1) throws Exception {
        this.items = items;
        this.itemsV1 = itemsV1;
    }

    public GildedRose updateQuality() throws Exception {
        return new GildedRose(itemsV1.updateAllItems());
    }

    public Boolean hasItem(Item item) {
        for (Item currItem: items) {
            if (currItem.name.equals(item.name) && currItem.quality == item.quality && currItem.sellIn == item.sellIn) {
                return true;
            }
        }
        return false;
    }

    public Boolean hasNegativeQualityItems() {
        for (Item item: items) {
            if (item.quality < 0) {
                return true;
            }
        }
        return false;
    }

    public Boolean hasItemQualityOver50() {
        for (Item item: items) {
            if (!item.name.equals("Sulfuras, Hand of Ragnaros") && item.quality > 50) {
                return true;
            }
        }
        return false;
    }

    public Boolean areAllSulfurasQualityAt80() {
        for (Item item: items) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros") && item.quality != 80) {
                return false;
            }
        }
        return true;
    }

    public Boolean areAllSulfurasNotOnSale() {
        for (Item item: items) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros") && item.sellIn > 0) {
                return false;
            }
        }
        return true;
    }

}
