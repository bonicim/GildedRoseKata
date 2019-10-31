package com.gildedrose;

public class BackstagePassItemStrategy implements ItemStrategy {
    private Item item;

    public BackstagePassItemStrategy(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        if (item.quality < 50) {
            item.quality++;
        }

        if (item.sellIn < 11) {
            if (item.quality < 50) {
                item.quality++;
            }
        }

        if (item.sellIn < 6) {
            if (item.quality < 50) {
                item.quality++;
            }
        }

        item.sellIn--;

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }
}
