package com.gildedrose;

public class NormalItemStrategy implements ItemStrategy {
    protected Item item;
    protected static final Integer QUALITY_DEGRADE_RATE = 1;

    public NormalItemStrategy(Item item) {
        this.item = item;
    }

    @Override
    public Item updateQuality() {
        Integer updatedQuality = item.quality;
        Integer updatedSellin = item.sellIn;

        if (updatedQuality > 0) updatedQuality = updatedQuality - QUALITY_DEGRADE_RATE;

        updatedSellin--;

        if (updatedSellin < 0 && updatedQuality > 0) updatedQuality--;

        return new Item(item.name, updatedSellin, updatedQuality);
    }
}
