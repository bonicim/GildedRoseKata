package com.gildedrose;

public class ConjuredItemStrategy extends NormalItemStrategy{

    public ConjuredItemStrategy(Item item) {
        super(item);
    }

    @Override
    public void updateQuality() {
        if (item.quality > 0) {
            item.quality-=QUALITY_DEGRADE_RATE * 2;
        }

        item.sellIn--;

        if (item.sellIn < 0 && item.quality > 0) {
            item.quality--;
        }
    }
}
