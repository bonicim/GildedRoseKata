package com.gildedrose;

public class NormalItemStrategy implements ItemStrategy {
    private Item item;

    public NormalItemStrategy(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        if (item.quality > 0) {
            item.quality-=1;
        }

        item.sellIn--;

        if (item.sellIn < 0 && item.quality > 0) {
            item.quality--;
        }
    }
}
