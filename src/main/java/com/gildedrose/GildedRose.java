package com.gildedrose;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GildedRose {

    Item[] items;

    private final ImmutableList<Item> itemsV2;
    private final ImmutableMap<Item, ItemStrategy> itemStrategy;

    public GildedRose(Item[] items) throws Exception {
        this.items = items;

        ImmutableList.Builder<Item> builderItemNameToItem = new ImmutableList.Builder<>();
        ImmutableMap.Builder<Item, ItemStrategy> builder = new ImmutableMap.Builder<>();
        for (Item item: items) {
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
            builderItemNameToItem.add(item);
        }
        this.itemsV2 = builderItemNameToItem.build();
        this.itemStrategy = builder.build();
    }


    public GildedRose updateQuality() throws Exception {
        List<Item> updatedItems = new ArrayList<>();

        itemStrategy.values().forEach(strategy -> updatedItems.add(strategy.updateQuality()));

        return new GildedRose(updatedItems.toArray(new Item[0]));
    }

    public Boolean hasItem(Item item) {
        List<Item> itemsV2FilteredForTargetItem = itemsV2.stream().filter(itemToBeFiltered -> itemToBeFiltered.name.equals(itemToBeFiltered.name)).collect(Collectors.toList());

        for (Item itemV2: itemsV2FilteredForTargetItem) {
            if (itemV2.quality == item.quality && itemV2.sellIn == item.sellIn) {
                return true;
            }
        }
        return false;
    }

    public Boolean hasNegativeQualityItems() {
        for (Item item: itemsV2) {
            if (item.quality < 0) {
                return true;
            }
        }
        return false;
    }

    public Boolean hasItemQualityOver50() {
        for (Item item: itemsV2) {
            if (!item.name.equals("Sulfuras, Hand of Ragnaros") && item.quality > 50) {
                return true;
            }
        }
        return false;
    }

    public Boolean areAllSulfurasQualityAt80() {
        for (Item item: itemsV2) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros") && item.quality != 80) {
                return false;
            }
        }
        return true;
    }

    public Boolean areAllSulfurasNotOnSale() {
        for (Item item: itemsV2) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros") && item.sellIn > 0) {
                return false;
            }
        }
        return true;
    }

}
