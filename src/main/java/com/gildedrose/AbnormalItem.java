package com.gildedrose;

public abstract class AbnormalItem extends Item {
    public AbnormalItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public abstract void updateQuality();
}
