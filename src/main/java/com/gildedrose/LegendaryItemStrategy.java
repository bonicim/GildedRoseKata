package com.gildedrose;

public class LegendaryItemStrategy implements ItemStrategyInterface {
    private Item item;

    public LegendaryItemStrategy(Item item) {
        this.item = item;
    }

    @Override
    public Item updateQuality() {
        Integer updatedQuality = item.quality;

        if (updatedQuality < 50) updatedQuality++;

        return new Item(item.name, item.sellIn, updatedQuality);
    }
}
