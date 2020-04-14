package com.gildedrose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Items {

  private final Map<Item, ItemUpdateInterface> itemUpdates;
  private final Item[] items;

  public Items(Item[] items) throws Exception {
    this.items = items;
    Map<Item, ItemUpdateInterface> updatedStrategies = new HashMap<>();
    for (Item item: items) {
      if (item.name.equals("+5 Dexterity Vest")) {
        updatedStrategies.put(item, new NormalItemUpdate(item));
      } else if (item.name.equals("Elixir of the Mongoose")) {
        updatedStrategies.put(item, new NormalItemUpdate(item));
      } else if (item.name.equals("Aged Brie")) {
        updatedStrategies.put(item, new BrieItemUpdate(item));
      } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
        updatedStrategies.put(item, new LegendaryItemUpdate(item));
      } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
        updatedStrategies.put(item, new BackstagePassItemUpdate(item));
      } else if (item.name.equals("Conjured Mana Cake")) {
        updatedStrategies.put(item, new ConjuredItemUpdate(item));
      } else {
        throw new Exception("Unmapped Item to ItemStrategy");
      }
    }
    this.itemUpdates = updatedStrategies;
  }

  public Item[] updateAllItems() {
    List<Item> tempItems = new ArrayList<>();
    itemUpdates.forEach((k,v) -> tempItems.add(v.updateQuality()));
    return tempItems.toArray(new Item[tempItems.size()]);
  }
}
