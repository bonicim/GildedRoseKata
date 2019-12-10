package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    private Item[] items;
    private GildedRose gildedRose;

    private static final String VEST = "+5 Dexterity Vest";
    private static final String BRIE = "Aged Brie";
    private static final String ELIXIR = "Elixir of the Mongoose";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED = "Conjured Mana Cake";

    @BeforeEach
    void setUp() throws Exception {
        items = new Item[] {
                new Item(VEST, 10, 20),
                new Item(BRIE, 2, 0),
                new Item(ELIXIR, 5, 7),
                new Item(SULFURAS, 0, 80),
                new Item(SULFURAS, -1, 80),
                new Item(BACKSTAGE_PASSES, 15, 20),
                new Item(BACKSTAGE_PASSES, 11, 5),
                new Item(BACKSTAGE_PASSES, 10, 42),
                new Item(BACKSTAGE_PASSES, 5, 23),
                new Item(BACKSTAGE_PASSES, 6, 15),
                new Item(BACKSTAGE_PASSES, 1, 49),
                new Item(CONJURED, 3, 6) };

        gildedRose = new GildedRose(items);
    }


    @Test
    void vestSellInAndQualityShouldDecreaseAfterOneDay() throws Exception {
        Item expectedItem = new Item(VEST, 9, 19);

        GildedRose updatedGildedRose = gildedRose.updateQuality();

        assertTrue(updatedGildedRose.hasItem(expectedItem));
    }

    @Test
    void vestQualityShouldDecreaseBy2WhenSellinDateIsZero() throws Exception {
        Item expectedItem = new Item(VEST, 0, 10);

        GildedRose updatedGildedRose = gildedRose.updateQuality();
        for (int i = 0; i < 9; i++) {
            updatedGildedRose = updatedGildedRose.updateQuality();
        }

        assertTrue(updatedGildedRose.hasItem(expectedItem));


        expectedItem.sellIn = -1;
        expectedItem.quality = 8;

        updatedGildedRose = updatedGildedRose.updateQuality();

        assertTrue(updatedGildedRose.hasItem(expectedItem));

        expectedItem.sellIn = -2;
        expectedItem.quality = 6;

        updatedGildedRose = updatedGildedRose.updateQuality();

        assertTrue(updatedGildedRose.hasItem(expectedItem));
    }

    @Test
    void vestQualityShouldNeverBeNegative() throws Exception {
        Item expectedItem = new Item(VEST, -10, 0);

        GildedRose updatedGildedRose = gildedRose.updateQuality();
        for (int i = 0; i < 19; i++) {
            updatedGildedRose = updatedGildedRose.updateQuality();
        }

        assertTrue(updatedGildedRose.hasItem(expectedItem));
    }

    @Test
    void itemsShouldNotHaveNegativeQuality() {
        assertFalse(gildedRose.hasNegativeQualityItems());
    }

    @Test
    void itemsShouldNotHaveQualityGreaterThan50() {
        assertFalse(gildedRose.hasItemQualityOver50());
    }

    @Test
    void sulfurasQualityShouldRemainAt80AfterAnyUpdate() {
        assertTrue(gildedRose.areAllSulfurasQualityAt80());
    }

    @Test
    void brieShouldIncreaseQualityAsItGetsOlder() throws Exception {
        Item expectedBrieItem = new Item(BRIE, 1,1);

        GildedRose updatedGildedRose = gildedRose.updateQuality();

        assertTrue(updatedGildedRose.hasItem(expectedBrieItem));
    }

    @Test
    void sulfurasSellinNotOnSale() {
        assertTrue(gildedRose.areAllSulfurasNotOnSale());
    }

    @Test
    void sulfurasQualityShouldNotDecreaseAfterUpdate() throws Exception {
        Item sulfurasItem1 = new Item(SULFURAS, 0, 80);
        Item sulfurasItem2 = new Item(SULFURAS, -1, 80);

        GildedRose updatedGildedRose = gildedRose.updateQuality();

        assertTrue(updatedGildedRose.hasItem(sulfurasItem1));
        assertTrue(updatedGildedRose.hasItem(sulfurasItem2));
    }

    @Test
    void backstagePassQualityShouldIncreaseBy1ForSellInDatesGreaterThan10() throws Exception {
        Item expectedBackstagePass = new Item(BACKSTAGE_PASSES, 14, 21);

        GildedRose updatedGildedRose = gildedRose.updateQuality();

        assertTrue(updatedGildedRose.hasItem(expectedBackstagePass));
    }

    @Test
    void backstagePassQualityShouldBeZeroWhenSellinIsNegativeOneAKADayAfterConcert() throws Exception {
        Item expectedBackstagePass = new Item(BACKSTAGE_PASSES, -2, 0);

        GildedRose updatedGildedRose = gildedRose.updateQuality();
        for (int i = 0; i < 16; i++) {
            updatedGildedRose = updatedGildedRose.updateQuality();
        }

        assertTrue(updatedGildedRose.hasItem(expectedBackstagePass));
    }

    @Test
    void backstagePassQualityShouldIncreaseByOneOnDayOfConcert() throws Exception {
        Item exectedBackstagePass = new Item(BACKSTAGE_PASSES, 0, 50);

        GildedRose updatedGildedRose = gildedRose.updateQuality();

        assertTrue(updatedGildedRose.hasItem(exectedBackstagePass));
    }

    @Test
    void backstagePassQualityShouldIncreaseBy2ForSellInDateLessThanOrEqualTo10() throws Exception {
        Item exectedBackstagePass = new Item(BACKSTAGE_PASSES, 9, 44);

        GildedRose updatedGildedRose = gildedRose.updateQuality();

        assertTrue(updatedGildedRose.hasItem(exectedBackstagePass));
    }

    @Test
    void backstagePassQualityShouldIncreaseBy3ForSellInDateLessThanOrEqualTo5() throws Exception {
        Item exectedBackstagePass = new Item(BACKSTAGE_PASSES, 9, 44);

        GildedRose updatedGildedRose = gildedRose.updateQuality();

        assertTrue(updatedGildedRose.hasItem(exectedBackstagePass));
    }

    @Test
    void backstagePassQualityShouldIncreaseBy1ThenBy2() throws Exception {
        // Test after one update
        Item exectedBackstagePass = new Item(BACKSTAGE_PASSES, 10, 6);

        GildedRose updatedGildedRose = gildedRose.updateQuality();

        assertTrue(updatedGildedRose.hasItem(exectedBackstagePass));

        // Test after two updates
        exectedBackstagePass.sellIn = 9;
        exectedBackstagePass.quality = 8;

        updatedGildedRose = updatedGildedRose.updateQuality();

        assertTrue(updatedGildedRose.hasItem(exectedBackstagePass));
    }

    @Test
    void backstagePassQualityShouldIncreaseBy2ThenBy3() throws Exception {
        // Test after one update
        Item exectedBackstagePass = new Item(BACKSTAGE_PASSES, 5, 17);

        GildedRose updatedGildedRose = gildedRose.updateQuality();

        assertTrue(updatedGildedRose.hasItem(exectedBackstagePass));

        // Test after two updates
        exectedBackstagePass.sellIn = 4;
        exectedBackstagePass.quality = 20;

        updatedGildedRose = updatedGildedRose.updateQuality();

        assertTrue(updatedGildedRose.hasItem(exectedBackstagePass));
    }

    @Test
    void conjuredQualityShouldDecreaseBy2() throws Exception {
        Item exectedBackstagePass = new Item(CONJURED, 2, 4);

        GildedRose updatedGildedRose = gildedRose.updateQuality();

        assertTrue(updatedGildedRose.hasItem(exectedBackstagePass));
    }

}