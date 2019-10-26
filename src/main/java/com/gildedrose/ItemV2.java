package com.gildedrose;

public abstract class ItemV2 extends Item {
    public ItemV2(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public abstract void updateQuality();
}
