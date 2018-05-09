package edu.fgu.dclab.murmur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SceneConnect {
    private Scene mScene = null;

    public SceneConnect(Murmur app) {
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("歡迎");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label ipAddress = new Label("IP：");
        grid.add(ipAddress, 0, 1);

        TextField ipField = new TextField();
        grid.add(ipField, 1, 1);

        Label portNumber = new Label("通訊：");
        grid.add(portNumber, 0, 2);

        TextField portField = new TextField();
        grid.add(portField, 1, 2);

        Button btn = new Button("連接");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                app.connect(
                    ipField.getText(),
                    Integer.parseInt(portField.getText())
                );
            }
        });

        mScene = new Scene(grid, 400, 200);
    } // SceneConnect()

    public Scene getScene() {
        return mScene;
    } // getScene()
}

// SceneConnect.java