package com.evh98.vision.apps;

import com.evh98.vision.Vision;
import com.evh98.vision.screens.Screen;
import com.evh98.vision.ui.YouTubePane;
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
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.javafx.IconNode;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.IOException;
import java.util.*;

import static com.evh98.vision.Vision.WIDTH;
import static com.evh98.vision.Vision.HEIGHT;

/**
 * Created by danha on 25/06/2016.
 */
public class YouTubeScreen extends Screen {
    TextField search;
    GridPane grid;

    BrowserView browserView;
    Robot robot;

    YouTube youtube;
    String KEY = "AIzaSyC6YdzinsZbyrHbPFtnEujJk8y77jdo_aM";
    String term = "";

    ArrayList<SearchResult> results;
    ArrayList<YouTubePane> panes;

    int x = 1;
    int y = 0;

    public YouTubeScreen(GraphicsContext gc) {
        super(gc);
    }

    @Override
    public void start() {
        super.start();
        root.getStylesheets().addAll("file:assets/style/panes.css", "file:assets/style/ui.css");

        search = new TextField();
        search.getStyleClass().addAll("text", "red", "search");
        search.setPromptText("Search");
        setSize(search, WIDTH, 50);
        setPos(search, 0, 0);

        root.getChildren().addAll(search);

        grid = new GridPane();
        root.getChildren().add(grid);

        youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest httpRequest) throws IOException {
            }
        }).setApplicationName("evh98-vision").build();

        results = new ArrayList<SearchResult>();
        panes = new ArrayList<YouTubePane>();

        browserView = new BrowserView(Vision.browser);

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        EventHandler<KeyEvent> safeHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.LEFT) {
                    x--;
                }
                if (e.getCode() == KeyCode.RIGHT) {
                    x++;
                }
                if (e.getCode() == KeyCode.UP) {
                    y--;
                }
                if (e.getCode() == KeyCode.DOWN) {
                    y++;
                }

                if (y == 3) y = 2;
                if (y == -1) y = 0;
                if (x < 1) x = 1;
                if (x > 4 && y != 1) x = 4;
                if (x > 4 && y == 1) {
                    y = 2;
                    x = 1;
                }
                if (x > 4 && y == 2) x = 4;

                if (y == 0) search.requestFocus();
                else root.requestFocus();

                if (e.getCode() == KeyCode.ENTER) {
                    if (y == 0) {
                        searchVideos();
                        y++;
                    } else {

                    }
                }

                if (e.getCode() == KeyCode.ESCAPE ) {
                    Vision.setScreen(Vision.main_screen);
                }

                updateUI();
            }
        };

        EventHandler<KeyEvent> handler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (Controller.isLeft(e)) {
                    x--;
                }
                if (Controller.isRight(e)) {
                    x++;
                }
                if (Controller.isUp(e)) {
                    y--;
                }
                if (Controller.isDown(e)) {
                    y++;
                }

                if (y == 3) y = 2;
                if (y == -1) y = 0;
                if (x < 1) x = 1;
                if (x > 4 && y != 1) x = 4;
                if (x > 4 && y == 1) {
                    y = 2;
                    x = 1;
                }
                if (x > 4 && y == 2) x = 4;

                if (y == 0) search.requestFocus();
                else root.requestFocus();

                if (Controller.isGreen(e)) {
                    if (y == 0) {
                        searchVideos();
                        y++;
                    } else {

                    }
                }

                if (Controller.isRed(e)) {
                    Vision.setScreen(Vision.main_screen);
                }

                updateUI();
            }
        };

        scene.setOnKeyPressed(handler);
        search.setOnKeyPressed(safeHandler);
    }

    public void gridify() {
        if(root.getChildren().contains(grid)) root.getChildren().remove(grid);

        grid = new GridPane();
        grid.setPadding(new Insets(50 + ((HEIGHT - 50) * 0.2) / 3, (float)WIDTH * 0.04, ((HEIGHT - 50) * 0.2) / 3, (float)WIDTH * 0.04));
        grid.setHgap((float)WIDTH * 0.04);
        grid.setVgap((float)((HEIGHT - 50) * 0.2) / 3);

        panes.clear();

        for(SearchResult result : results) {
            panes.add(new YouTubePane(result.getSnippet().getTitle(), new Image("https://i.ytimg.com/vi/" + result.getId().getVideoId() + "/mqdefault.jpg"), new String[]{"media-pane"}, new float[]{(float) (WIDTH * 0.2), (float)((HEIGHT - 50) * 0.4)}));
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 2; j++) {
                int index = i + (j * 4);
                if(panes.get(index) != null) {
                    grid.add(panes.get(index).getPane(), i, j);
                }
            }
        }

        root.getChildren().add(grid);
    }

    public void searchVideos() {
        term = search.getText();

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

        gridify();
    }

    public void updateUI() {
        System.out.println(x + "/" + y);

        for(YouTubePane p : panes) {
            p.getPane().getStyleClass().remove("selected");
        }

        for(Node n : grid.getChildren()) {
            if(grid.getRowIndex(n) == (y - 1) && grid.getColumnIndex(n) == (x - 1)) {
                System.out.println("hello there");
                n.getStyleClass().add("selected");
            }
        }
    }

    @Override
    public void render() {
        Graphics.drawBackground(gc, Graphics.background_red);
    }

    @Override
    public void update() {

    }
}