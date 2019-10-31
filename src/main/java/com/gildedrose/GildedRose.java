package com.gildedrose;

import java.util.HashMap;

public class GildedRose {

    Item[] items;

    private HashMap<Item, ItemStrategy> itemStrategy = new HashMap<>();

    private static final String VEST = "+5 Dexterity Vest";
    private static final String BRIE = "Aged Brie";
    private static final String ELIXIR = "Elixir of the Mongoose";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED = "Conjured Mana Cake";

    public GildedRose(Item[] items) {
        this.items = items;
        addItemsToItemStrategy();
    }

    private void addItemsToItemStrategy() {
        for (Item item: items) {
            if (item.name.equals(VEST)) {
                itemStrategy.put(item, new NormalItemStrategy(item));
            } else if (item.name.equals(ELIXIR)) {
                itemStrategy.put(item, new NormalItemStrategy(item));
            } else if (item.name.equals(BRIE)) {
                itemStrategy.put(item, new BrieItemStrategy(item));
            } else if (item.name.equals(SULFURAS)) {
                itemStrategy.put(item, new LegendaryItemStrategy(item));
            } else if (item.name.equals(BACKSTAGE_PASSES)) {
                itemStrategy.put(item, new BackstagePassItemStrategy(item));
            } else if (item.name.equals(CONJURED)) {
                itemStrategy.put(item, new NormalItemStrategy(item));
            }
        }
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            ItemStrategy strategy = itemStrategy.get(items[i]);
            strategy.updateQuality();
        }
    }
}
