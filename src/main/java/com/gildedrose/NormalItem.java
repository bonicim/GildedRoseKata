package com.gildedrose;

public class NormalItem extends Item {

    public NormalItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        // TODO: setup validation to prevent items qualityu over 50
    }

    public void updateQuality() {
        if (quality > 0) {
            quality-=1;
        }
    }
}