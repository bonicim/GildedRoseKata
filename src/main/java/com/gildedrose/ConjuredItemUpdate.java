package com.gildedrose;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ConjuredItemUpdate extends NormalItemUpdate {
    public ConjuredItemUpdate(Item item) {
        super(item);
    }

    @Override
    public Item updateQuality() {
        Integer updatedQuality = item.quality;
        Integer updatedSellin = item.sellIn;

        if (updatedQuality > 0) updatedQuality = updatedQuality - QUALITY_DEGRADE_RATE * 2;

        updatedSellin--;

        if (updatedSellin < 0 && updatedQuality > 0) updatedQuality--;

        return new Item(item.name, updatedSellin, updatedQuality);
    }
}
