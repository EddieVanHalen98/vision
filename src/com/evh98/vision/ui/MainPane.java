package com.evh98.vision.ui;

import com.evh98.vision.Vision;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import jiconfont.IconCode;
import javafx.scene.layout.Pane;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.javafx.IconNode;

import static com.evh98.vision.Vision.HEIGHT;
import static com.evh98.vision.Vision.WIDTH;

/**
 * Created by danha on 25/06/2016.
 */
public class MainPane {
    String text;
    IconCode iconCode;
    String[] styles;
    float[] vals;

    Pane pane;
    IconNode iconNode;

    public MainPane(String text, IconCode iconCode, String[] styles, float[] vals) {
        this.text = text;
        this.iconCode = iconCode;
        this.styles = styles;
        this.vals = vals;

        create();
    }

    public void create() {
        pane = new Pane();

        pane.getStyleClass().addAll(styles);
        setSize(pane, vals[2], vals[3]);
        setPos(pane, vals[0], vals[1]);

        float size = 45 * Vision.CONVERTER;

        Label lGames = new Label(text);
        lGames.setStyle("-fx-font-size:" + size + "px;");
        lGames.setAlignment(Pos.TOP_CENTER);
        setSize(lGames, (float) pane.getPrefWidth(), (float) pane.getPrefHeight() / 2);
        setPos(lGames, 0, (float) pane.getPrefHeight() - (float) pane.getPrefHeight() / 2);

        iconNode = new IconNode(iconCode);
        iconNode.setIconSize(size * 1.15);
        iconNode.setFill(Color.WHITE);
        Label l = new Label();
        l.setAlignment(Pos.BOTTOM_CENTER);
        l.setGraphic(iconNode);
        setSize(l, (float) pane.getPrefWidth(), (float) pane.getPrefHeight() / 2);

        pane.getChildren().addAll(l, lGames);

    }

    public void setPos(Region c, float x, float y) {
        c.setLayoutX(x);
        c.setLayoutY(y);
    }

    public void setSize(Region c, float w, float h) {
        c.setPrefWidth(w);
        c.setPrefHeight(h);
    }

    public Pane getPane() {
        return pane;
    }

    public void setIconFill(Color c) {
        iconNode.setFill(c);
    }
}
