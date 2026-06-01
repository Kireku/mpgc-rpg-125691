package it.unicam.cs.mpgc.rpg125691.view;

import it.unicam.cs.mpgc.rpg125691.model.Inventory;
import it.unicam.cs.mpgc.rpg125691.model.Item;
import it.unicam.cs.mpgc.rpg125691.service.GameService;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InventoryView {
    private final VBox root;

    public InventoryView(Inventory inventory) {
        this.root = new VBox(10);
        this.root.setPadding(new Insets(15));

        root.getChildren().add(new Label("Inventory"));

        if(inventory.isEmpty()){
            root.getChildren().add(new Label("No Items in Inventory"));
            return;
        }

        for(Item item : inventory.getItems()){
            root.getChildren().add(new Label(item.getName() + " - " + item.getDescription() + " - Value " + item.getValue()));
        }
    }

    public Node getRoot() {
        return root;
    }
}
