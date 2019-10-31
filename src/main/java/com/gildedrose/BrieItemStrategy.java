package com.gildedrose;

public class BrieItemStrategy implements ItemStrategy {
    private Item item;

    public BrieItemStrategy(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        if (item.quality < 50) {
            item.quality++;
        }

        item.sellIn--;

        if (item.sellIn < 0) {
            if (item.quality < 50) {
                item.quality++;
            }
        }
    }
}
