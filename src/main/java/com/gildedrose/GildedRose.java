package com.gildedrose;

public class GildedRose {

    // Cannot be changed; thus is public
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    // thoughts: every item should have its own class which inherits from Item
    // each item has its own logic to update quality,
    public void updateQuality() {
        // go through each item and update the sellin and quality accordingly
        for (int i = 0; i < items.length; i++) {
            // update quality by reducing by 1 only for non-brie, non-backstage, non sulfuras, and quality is posite
            // to refactor, check if the class is a Normal class
            if (items[i].getClass() == NormalItem.class) {
                NormalItem item = (NormalItem) items[i];
                item.updateQuality();
            } else {
                // update quality for brie or backstage or sulfuras and quality is less than 50
                if (items[i].getClass() == BrieItem.class) {
                    BrieItem item = (BrieItem) items[i];
                    item.updateQuality();
                } else if (items[i].getClass() == BackstagePassItem.class) {
                    BackstagePassItem item = (BackstagePassItem) items[i];
                    item.updateQuality();
                } else if (items[i].getClass() == LegendaryItem.class) {
                    LegendaryItem item = (LegendaryItem) items[i];
                    item.updateQuality();
                }
            }
            // the above code blocks update quality based on original quality or sellin for backstage
            // the below code blocks update quality based on new sellin

            // update the sellin by reducing to 1 only for non-Sulfuras
            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            // update the quality based on the sellin values for certain products only when sellin is negative
            if (items[i].sellIn < 0) {
                // reduce quality by 1 for non-brie, non-backstage, quality is positive, item is not sulfuras
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        // for all other items, simply set the quality to zero
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    // update quality for items with 49 or less quality by increaseing to 1
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}
