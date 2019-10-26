package com.gildedrose;

public class BackstagePassItem extends ItemV2 {
    public BackstagePassItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        if (quality < 50) {
            quality++;
        }

        if (sellIn < 11) {
            if (quality < 50) {
                quality++;
            }
        }

        if (sellIn < 6) {
            if (quality < 50) {
                quality++;
            }
        }
    }
}
