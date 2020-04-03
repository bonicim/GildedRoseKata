package com.gildedrose;

import java.util.HashMap;
import java.util.Map;

public class ItemStrategies {

  private final Map<Item, ItemStrategyInterface> strategies;

  public ItemStrategies() {
    this(new HashMap<>());
  }

  public ItemStrategies(Map<Item, ItemStrategyInterface> strategies) {
    this.strategies = strategies;
  }

  public ItemStrategies build(Item[] items) throws Exception {
    Map<Item, ItemStrategyInterface> updatedStrategies = new HashMap<>();
    for (Item item: items) {
      if (item.name.equals("+5 Dexterity Vest")) {
        updatedStrategies.put(item, new NormalItemStrategy(item));
      } else if (item.name.equals("Elixir of the Mongoose")) {
        updatedStrategies.put(item, new NormalItemStrategy(item));
      } else if (item.name.equals("Aged Brie")) {
        updatedStrategies.put(item, new BrieItemStrategy(item));
      } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
        updatedStrategies.put(item, new LegendaryItemStrategy(item));
      } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
        updatedStrategies.put(item, new BackstagePassItemStrategy(item));
      } else if (item.name.equals("Conjured Mana Cake")) {
        updatedStrategies.put(item, new ConjuredItemStrategy(item));
      } else {
        throw new Exception("Unmapped Item to ItemStrategy");
      }
    }
    return new ItemStrategies(updatedStrategies);
  }

  public Item update(Item item) {
    return strategies.get(item).updateQuality();
  }
}
