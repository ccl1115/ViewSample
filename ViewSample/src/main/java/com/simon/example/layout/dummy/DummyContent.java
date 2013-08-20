package com.simon.example.layout.dummy;

import com.simon.example.layout.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        addItem(new DummyItem("1", "RectLayout", R.layout.fragment_rect_layout));
        addItem(new DummyItem("2", "FloatingLayout", R.layout.fragment_floating_layout));
        addItem(new DummyItem("3", "NineBlockLayout", R.layout.fragment_block_layout));
        addItem(new DummyItem("4", "DoodleView", R.layout.fragment_doodle_layout));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;
        public int layout;

        public DummyItem(String id, String content, int layout) {
            this.id = id;
            this.content = content;
            this.layout = layout;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
