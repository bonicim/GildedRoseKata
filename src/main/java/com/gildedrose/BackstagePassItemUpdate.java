package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
public class BackstagePassItemUpdate implements ItemUpdateInterface {
    private final Item item;

    @Override
    public Item updateQuality() {
        Integer updatedQuality = item.quality;
        Integer updatedSellin = item.sellIn;

        if (updatedQuality < 50) updatedQuality++;

        if (updatedSellin < 11 && updatedQuality < 50) updatedQuality++;

        if (updatedSellin < 6 && updatedQuality < 50) updatedQuality++;

        updatedSellin--;

        if (item.sellIn < 0) updatedQuality = 0;

        return new Item(item.name, updatedSellin, updatedQuality);
    }
}
