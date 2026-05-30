package it.unicam.cs.mpgc.rpg125691.service;

import it.unicam.cs.mpgc.rpg125691.model.Item;
import it.unicam.cs.mpgc.rpg125691.model.ItemType;
import it.unicam.cs.mpgc.rpg125691.model.PlayerCharacter;

import java.util.Optional;

public class InventoryService {

    public Optional<Item> findPotion(PlayerCharacter player) {
        if(player == null){
            throw new IllegalArgumentException("player cannot be null");
        }
        return player.getInventory().getItemsByType(ItemType.CONSUMABLE).stream().filter(item -> item.getName().equalsIgnoreCase("Potion")).findFirst();
    }

    public boolean usePotion(PlayerCharacter player, int healingAmount) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        if (healingAmount < 0) {
            throw new IllegalArgumentException("Healing amount cannot be negative.");
        }

        Optional<Item> potion = findPotion(player);

        if (potion.isEmpty()) {
            return false;
        }

        player.getInventory().removeItem(potion.get());
        player.heal(healingAmount);
        return true;
    }
}
