package com.gildedrose;

public class NormalItemStrategy implements ItemStrategy {
    protected Item item;
    protected static final Integer QUALITY_DEGRADE_RATE = 1;

    public NormalItemStrategy(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        if (item.quality > 0) {
            item.quality-=QUALITY_DEGRADE_RATE;
        }

        item.sellIn--;

        if (item.sellIn < 0 && item.quality > 0) {
            item.quality--;
        }
    }
}
