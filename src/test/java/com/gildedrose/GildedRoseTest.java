package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GildedRoseTest {

    private Item[] items;
    private GildedRose gildedRose;

    private final String VEST = "+5 Dexterity Vest";
    private final String BRIE = "Aged Brie";
    private final String ELIXIR = "Elixir of the Mongoose";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private final String CONJURED = "Conjured Mana Cake";

    @BeforeEach
    void setUp() {
        items = new Item[] {
                new NormalItem(VEST, 10, 20),
                new BrieItem(BRIE, 2, 0),
                new NormalItem(ELIXIR, 5, 7),
                new LegendaryItem(SULFURAS, 0, 80),
                new LegendaryItem(SULFURAS, -1, 80),
                new BackstagePassItem(BACKSTAGE_PASSES, 15, 20),
                new BackstagePassItem(BACKSTAGE_PASSES, 11, 5),
                new BackstagePassItem(BACKSTAGE_PASSES, 10, 42),
                new BackstagePassItem(BACKSTAGE_PASSES, 5, 23),
                new BackstagePassItem(BACKSTAGE_PASSES, 6, 15),
                new BackstagePassItem(BACKSTAGE_PASSES, 1, 49),
                // this conjured item does not work properly yet
                new Item(CONJURED, 3, 6) };

        gildedRose = new GildedRose(items);
    }


    @Test
    void vestQualitySellInAndQualityShouldDecreaseAfterOneDay() {
        gildedRose.updateQuality();
        Item vest = getItem(VEST);
        assertEquals(9, vest.sellIn);
        assertEquals(19, vest.quality);
    }

    @Test
    void vestQualityShouldDecreaseBy2XAfterSellinDate() {
        for (int i = 0; i < 10; i++) {
            gildedRose.updateQuality();
        }
        Item vest = getItem("+5 Dexterity Vest");

        assertEquals(0, vest.sellIn);
        assertEquals(10, vest.quality);

        gildedRose.updateQuality();

        assertEquals(vest.sellIn, -1);
        assertEquals(8, vest.quality);

       gildedRose.updateQuality();

       assertEquals(vest.sellIn, -2);
       assertEquals(6, vest.quality);
    }

    @Test
    void vestQualityNeverBecomesNegative() {
        for (int i = 0; i < 20; i++) {
            gildedRose.updateQuality();
        }
        Item vest = getItem("+5 Dexterity Vest");

        assertEquals(vest.sellIn, -10);
        assertEquals(0, vest.quality);

        gildedRose.updateQuality();
        assertTrue(vest.quality >= 0 );
    }

    @Test
    void itemQualityNotNegative() {
        for (Item item: gildedRose.items) {
            assertTrue(item.quality > -1, "The Quality of an item is never negative: " + item.toString());
        }
    }

    @Test
    void itemQualityNotMoreThan50ExceptSulfuras() {
        for (Item item: gildedRose.items) {
            if (!item.name.equals(SULFURAS)) {
                assertTrue(item.quality < 51, "The Quality of an item is never more than 50: " + item.toString());
            }
        }
    }

    @Test
    void agedBrieShouldIncreaseQualityAsItGetsOlder() {
        Item agedBrieInitial = getItem("Aged Brie");
        Integer originalQuality = agedBrieInitial.quality;

        gildedRose.updateQuality();

        assertTrue(agedBrieInitial.quality > originalQuality);
    }

    @Test
    void sulfurasSellinIsZeroOrLess() {
        Item sulfuras = getItem(SULFURAS);
        assertTrue(sulfuras.sellIn <= 0);
    }

    @Test
    void sulfurasShouldNotDecreaseQualityAfterEndOfDay() {
        Item sulfuras = getItem("Sulfuras, Hand of Ragnaros");
        Integer originalQuality = sulfuras.quality;

        gildedRose.updateQuality();

        assertTrue(originalQuality == sulfuras.quality);
    }

    @Test
    void backstagePassesShouldIncreaseQualityBy1ForSellInDatesAtGreaterThan10() {
        Item item = getBackStagePass(BACKSTAGE_PASSES, 15);
        Integer originalQuality = item.quality;
        gildedRose.updateQuality();

        assertTrue(originalQuality < item.quality);
        assertEquals(21, item.quality);
        gildedRose.updateQuality();
        assertEquals(22, item.quality);
    }

    @Test
    void backstagePassesShouldBeZeroQualityAfterConcert() {
        Item item = getBackStagePass(BACKSTAGE_PASSES, 15);
        Integer originalQuality = item.quality;
        for (int i = 0; i < 16; i++) {
            gildedRose.updateQuality();
        }

        assertTrue(item.sellIn == -1);
        assertTrue(originalQuality > item.quality);
        assertEquals(0, item.quality);
    }

    @Test
    void backstagePassesShouldIncreaseQualityBy1ForSellInDatesAt1() {
        Item item = getBackStagePass(BACKSTAGE_PASSES, 1);
        Integer originalQuality = item.quality;
        gildedRose.updateQuality();

        assertTrue(originalQuality < item.quality);
        assertEquals(50, item.quality);
    }

    @Test
    void backstagePassesShouldIncreaseQualityBy2ForSellInDateAt10() {
        Item item = getBackStagePass(BACKSTAGE_PASSES, 10);
        Integer originalQuality = item.quality;
        gildedRose.updateQuality();

        assertTrue(originalQuality < item.quality);
        assertEquals(44, item.quality);
    }

    @Test
    void backstagePassesShouldIncreaseQualityBy3ForSellInDateIs5OrLess() {
        Item item = getBackStagePass(BACKSTAGE_PASSES, 5);
        Integer originalQuality = item.quality;

        gildedRose.updateQuality();

        assertTrue(originalQuality < item.quality);
        assertEquals(26, item.quality);
    }

    @Test
    void backstagePassesShouldIncreaseQualityBy1ThenBy2() {
        Item item = getBackStagePass(BACKSTAGE_PASSES, 11);
        Integer originalQuality = item.quality;

         gildedRose.updateQuality();

        assertTrue(originalQuality < item.quality);
        assertEquals(6, item.quality);

        gildedRose.updateQuality();

        assertTrue(originalQuality < item.quality);
        assertEquals(8, item.quality);
    }

    @Test
    void backstagePassesShouldIncreaseQualityBy2ThenBy3() {
        Item item = getBackStagePass(BACKSTAGE_PASSES, 6);
        Integer originalQuality = item.quality;

        gildedRose.updateQuality();

        assertTrue(originalQuality < item.quality);
        assertEquals(17, item.quality);

        gildedRose.updateQuality();

        assertTrue(originalQuality < item.quality);
        assertEquals(20, item.quality);
    }

    @Test
    @Disabled
    void conjuredShouldDecreaseBy2() {
        Item item = getItem(CONJURED);
        Integer originalQuality = item.quality;

        gildedRose.updateQuality();

        assertTrue(originalQuality > item.quality);
        assertEquals(4, item.quality);
    }

    Item getItem(String name) {
        for (Item item: gildedRose.items) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }

    Item getBackStagePass(String name, Integer sellin) {
        for (Item item: gildedRose.items) {
            if (item.name.equals(BACKSTAGE_PASSES) && item.sellIn == sellin) {
                return item;
            }
        }
        return null;
    }
}