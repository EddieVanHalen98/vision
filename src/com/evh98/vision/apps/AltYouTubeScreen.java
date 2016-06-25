package com.evh98.vision.apps;

import com.evh98.vision.Vision;
import com.evh98.vision.screens.Screen;
import com.evh98.vision.util.*;
import com.evh98.vision.util.Graphics;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.teamdev.jxbrowser.chromium.SavePageType;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.javafx.IconNode;

import java.awt.*;
import java.io.IOException;
import java.util.*;

import static com.evh98.vision.Vision.WIDTH;
import static com.evh98.vision.Vision.HEIGHT;
import static com.evh98.vision.Vision.canvas;
import static java.awt.SystemColor.text;

/**
 * Created by danha on 25/06/2016.
 */
public class AltYouTubeScreen extends Screen {
    AnchorPane root;
    TextField search;

    TilePane tilePane;

    BrowserView browserView;
    Robot robot;

    YouTube youtube;
    String KEY = "AIzaSyC6YdzinsZbyrHbPFtnEujJk8y77jdo_aM";
    String term = "";

    ArrayList<SearchResult> results;

    public AltYouTubeScreen(GraphicsContext gc) {
        super(gc);
    }

    @Override
    public void start() {
        root = new AnchorPane();
        root.getStylesheets().add("file:assets/style/ui.css");

        search = new TextField();
        search.getStyleClass().addAll("text", "red", "search");
        search.setPromptText("Search");
        setSize(search, WIDTH, 50);
        setPos(search, 0, 0);

        tilePane = new TilePane();
        tilePane.setHgap(5);
        tilePane.setVgap(5);
        tilePane.setPrefColumns(5);
        tilePane.setPrefColumns(2);

        root.getChildren().addAll(gc.getCanvas(), search);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(null);

        Vision.main_stage.setScene(scene);
        Vision.main_stage.show();

        youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest httpRequest) throws IOException {
            }
        }).setApplicationName("evh98-vision").build();
        results = new ArrayList<SearchResult>();

        browserView = new BrowserView(Vision.browser);

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void searchVideos() {
        try {
            YouTube.Search.List search = youtube.search().list("id,snippet");
            search.setKey(KEY);
            search.setQ(term);
            search.setType("video");
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults((long) 24);

            SearchListResponse searchResponse = search.execute();
            results = new ArrayList<SearchResult>(searchResponse.getItems());

        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }

        for(SearchResult result : results) {
            System.out.println(result.getSnippet().getTitle());
        }
    }

    @Override
    public void render() {
        Graphics.drawBackground(gc, Graphics.background_red);
    }

    @Override
    public void update(Scene scene) {

    }
}