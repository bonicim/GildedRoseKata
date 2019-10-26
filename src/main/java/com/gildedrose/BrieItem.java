package com.gildedrose;

public class BrieItem extends ItemV2 {
    public BrieItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        if (quality < 50) {
            quality++;
        }
    }
}
