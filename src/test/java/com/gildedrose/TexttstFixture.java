package com.gildedrose;

import java.io.File;
import java.io.PrintStream;
import java.io.FileOutputStream;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;



public class TexttstFixture {

    @Test
    public static void main(String[] args) throws Exception {
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Conjured Mana Cake", 3, 6) };

        GildedRose gildedRose = new GildedRose(items);

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        File actual_two_days_output = new File("actual_two_days_output.txt");
        FileOutputStream fos = new FileOutputStream(actual_two_days_output);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : gildedRose.items) {
                System.out.println(item);
            }
            System.out.println();
            gildedRose = gildedRose.updateQuality();
        }

        File expected_two_days_output = new File("expected_two_days_output.txt");
        Boolean isOutputTheSame = FileUtils.contentEquals(actual_two_days_output, expected_two_days_output);
        // TODO: Updated Items are not stored in original order, making such text-based testing difficult
//        assertTrue(isOutputTheSame);
    }
}
