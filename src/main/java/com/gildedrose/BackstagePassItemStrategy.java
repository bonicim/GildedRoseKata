package com.gildedrose;

public class BackstagePassItemStrategy implements ItemStrategyInterface {
    private Item item;

    public BackstagePassItemStrategy(Item item) {
        this.item = item;
    }

    @Override
    public Item updateQuality() {
        Integer updatedQuality = item.quality;
        Integer updatedSellin = item.sellIn;

        if (updatedQuality < 50) updatedQuality++;

        if (updatedSellin < 11 && updatedQuality < 50) updatedQuality++;

        if (updatedSellin < 6 && updatedQuality < 50) updatedQuality++;

        updatedSellin--;

        if (item.sellIn < 0) updatedQuality = 0;

        return new Item(item.name, updatedSellin, updatedQuality);
    }
}
