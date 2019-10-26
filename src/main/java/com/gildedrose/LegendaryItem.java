package com.gildedrose;

public class LegendaryItem extends ItemV2 {
    public LegendaryItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        if (quality < 50) {
            quality++;
        }
    }
}
