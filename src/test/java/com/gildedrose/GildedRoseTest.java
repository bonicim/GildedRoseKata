package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GildedRoseTest {

    private  Item[] items;
    private GildedRose gildedRose;

    @BeforeEach
    void setUp() {
        items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6) };

        gildedRose = new GildedRose(items);
    }

    @Test
    public void checkItemQualityNotNegative() {
        for (Item item: gildedRose.items) {
            assertTrue(item.quality > -1, "The Quality of an item is never negative: " + item.toString());
        }
    }
}