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
public class MediaPane {
	String text;
	IconCode iconCode;
	String[] styles;
	float[] vals;

	Pane pane;
	IconNode iconNode;

	public MediaPane(String text, IconCode iconCode, String[] styles, float[] vals) {
		this.text = text;
		this.iconCode = iconCode;
		this.styles = styles;
		this.vals = vals;

		create();
	}

	public void create() {
		pane = new Pane();

		pane.getStyleClass().addAll(styles);
		setSize(pane, vals[0], vals[1]);

		float size = 30 * Vision.CONVERTER;

		Label title = new Label(text);
		title.setStyle("-fx-font-size:" + size + "px;");
		title.getStyleClass().add("title");
		title.setAlignment(Pos.CENTER);
		setSize(title, (float) pane.getPrefWidth(), (float) pane.getPrefHeight() / 4);
		setPos(title, 0, (float) pane.getPrefHeight() - (float) pane.getPrefHeight() / 4);

		iconNode = new IconNode(iconCode);
		iconNode.setIconSize(size * 2.5);
		iconNode.setFill(Color.WHITE);
		Label l = new Label();
		l.setAlignment(Pos.CENTER);
		l.setGraphic(iconNode);
		setSize(l, (float) pane.getPrefWidth(), (float) (pane.getPrefHeight() / 4) * 3);

		pane.getChildren().addAll(l, title);

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
}
