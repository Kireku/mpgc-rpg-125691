package it.unicam.cs.mpgc.rpg125691.view;

import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class LogView {
    private final TextArea logArea;
    public LogView(){
        this.logArea = new TextArea();
        this.logArea.setEditable(false);
        this.logArea.setPrefRowCount(6);
    }

    public Node getRoot(){
        return logArea;
    }

    public void appendMessage(String message){
        logArea.appendText(message + System.lineSeparator());
    }

    public void clear(){
        logArea.clear();
    }
}
