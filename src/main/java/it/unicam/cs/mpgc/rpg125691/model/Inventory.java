package it.unicam.cs.mpgc.rpg125691.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Inventory {
    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(Objects.requireNonNull(item, "Item cannot be null"));
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public List<Item> getItemsByType(ItemType type) {
        Objects.requireNonNull(type, "Type cannot be null");

        return items.stream().filter(item -> item.getType() == type).collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }
}
