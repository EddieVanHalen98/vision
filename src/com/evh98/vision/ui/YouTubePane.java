package com.evh98.vision.ui;

import com.evh98.vision.Vision;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
public class YouTubePane {
	String text;
	Image image;
	String[] styles;
	float[] vals;

	Pane pane;

	public YouTubePane(String text, Image image, String[] styles, float[] vals) {
		this.text = text;
		this.image = image;
		this.styles = styles;
		this.vals = vals;

		create();
	}

	public void create() {
		pane = new Pane();
		pane.getStyleClass().addAll(styles);
		setSize(pane, vals[0], vals[1]);

		float size = 15 * Vision.CONVERTER;

		Label title = new Label(text);
		title.setStyle("-fx-font-size:" + size + "px;");
		title.getStyleClass().add("header");
		title.setAlignment(Pos.CENTER);
		setSize(title, (float) pane.getPrefWidth(), (float) pane.getPrefHeight() / 4);
		setPos(title, 0, (float) pane.getPrefHeight() - (float) pane.getPrefHeight() / 4);

		VBox imageBox = new VBox();
		imageBox.setAlignment(Pos.CENTER);
		setSize(imageBox, (float) pane.getPrefWidth(), (float)(pane.getPrefHeight() / 4) * 3);

		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth((float) pane.getPrefWidth());
		imageView.setFitHeight((float) (pane.getPrefHeight() / 4) * 3);

		imageBox.getChildren().add(imageView);

		pane.getChildren().addAll(imageBox, title);

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
