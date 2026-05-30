package it.unicam.cs.mpgc.rpg125691.model;

import java.util.Objects;

public class Item {
    private final String name;
    private final String description;
    private final ItemType type;
    private final int value;

    public Item(String name, String description, ItemType type, int value) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Item name cannot be empty");
        }
        if (value < 0){
            throw new IllegalArgumentException("Item value cannot be negative");
        }
        this.name = name;
        this.description = description == null ? "" : description;
        this.type = Objects.requireNonNull(type, " type cannot be null");
        this.value = value;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}
