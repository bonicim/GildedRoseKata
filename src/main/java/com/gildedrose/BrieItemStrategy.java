package com.gildedrose;

public class BrieItemStrategy implements ItemStrategy {
    private Item item;

    public BrieItemStrategy(Item item) {
        this.item = item;
    }

    @Override
    public Item updateQuality() {
        Integer updatedQuality = item.quality;
        Integer updatedSellin = item.sellIn;

        if (updatedQuality < 50) updatedQuality++;

        updatedSellin--;

        if (updatedSellin < 0 && updatedQuality < 50) updatedQuality++;

        return new Item(item.name, updatedSellin, updatedQuality);
    }
}
