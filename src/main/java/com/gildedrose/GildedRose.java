package com.gildedrose;

import com.google.common.collect.ImmutableMap;

public class GildedRose {

    Item[] items;

    private final ImmutableMap<Item, ItemStrategy> itemStrategy;

    public GildedRose(Item[] items) throws Exception {
        this.items = items;

        ImmutableMap.Builder<Item, ItemStrategy> builder = new ImmutableMap.Builder<>();

        for (Item item: this.items) {
            if (item.name.equals("+5 Dexterity Vest")) {
                builder = builder.put(item, new NormalItemStrategy(item));
            } else if (item.name.equals("Elixir of the Mongoose")) {
                builder = builder.put(item, new NormalItemStrategy(item));
            } else if (item.name.equals("Aged Brie")) {
                builder = builder.put(item, new BrieItemStrategy(item));
            } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                builder = builder.put(item, new LegendaryItemStrategy(item));
            } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                builder = builder.put(item, new BackstagePassItemStrategy(item));
            } else if (item.name.equals("Conjured Mana Cake")) {
                builder = builder.put(item, new ConjuredItemStrategy(item));
            } else {
                throw new Exception("Unmapped Item to ItemStrategy");
            }
        }
        this.itemStrategy = builder.build();
    }


    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            ItemStrategy strategy = itemStrategy.get(items[i]);
            strategy.updateQuality();
        }
    }
}
