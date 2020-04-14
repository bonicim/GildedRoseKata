package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
public class BrieItemUpdate implements ItemUpdateInterface {
    private final Item item;

    @Override
    public Item updateQuality() {
        Integer updatedQuality = item.quality;
        Integer updatedSellin = item.sellIn;

        if (updatedQuality < 50) updatedQuality++;

        updatedSellin--;

        if (updatedSellin < 0 && updatedQuality < 50) updatedQuality++;

        return new Item(item.name, updatedSellin, updatedQuality);
    }
}
