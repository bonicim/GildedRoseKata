package com.gildedrose;

public class LegendaryItemStrategy implements ItemStrategy {
    private Item item;

    public LegendaryItemStrategy(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        if (item.quality < 50) {
            item.quality++;
        }
    }
}
