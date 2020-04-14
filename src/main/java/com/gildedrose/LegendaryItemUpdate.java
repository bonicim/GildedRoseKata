package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
public class LegendaryItemUpdate implements ItemUpdateInterface {
    private final Item item;

    @Override
    public Item updateQuality() {
        Integer updatedQuality = item.quality;

        if (updatedQuality < 50) updatedQuality++;

        return new Item(item.name, item.sellIn, updatedQuality);
    }
}
