/**
 * Vision - Created and owned by Muhammad Saeed (EddieVanHalen98)
 * 
 * YouTubeScreen.java
 * Screen for Vision YouTube app
 * 
 * File created on 21st April 2016
 */

package com.evh98.vision.apps;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.evh98.vision.Vision;
import com.evh98.vision.screens.Screen;
import com.evh98.vision.ui.YouTubePane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.CertificateErrorParams;
import com.teamdev.jxbrowser.chromium.LoadHandler;
import com.teamdev.jxbrowser.chromium.LoadParams;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class YouTubeScreen extends Screen {
	
	Font font;
	int x = 0;
	int y = 0;
	String input = "";
    
    ArrayList<YouTubePane> panes;
    int[][] panesPos = {{1, 2}, {2, 2}, {3, 2}, {4, 2}, {1, 3}, {2, 3}, {3, 3}, {4, 3}};
	
	Browser browser;
    BrowserView browserView;
    Robot robot;
    
    YouTube youtube;
    String KEY = "AIzaSyC6YdzinsZbyrHbPFtnEujJk8y77jdo_aM";
    
	public YouTubeScreen(GraphicsContext gc) {
		super(gc);
	}
	
	@Override
	public void start() {
		font = Font.font("Roboto Thin", 192 * Vision.SCALE);
		
		panes = new ArrayList<YouTubePane>();
		
		// Sets up YouTube API
		youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("evh98-vision").build();
		
		browser = new Browser();
		browserView = new BrowserView(browser);
		
		try {
			robot = new Robot();
		} catch(AWTException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render() {
		Graphics.drawBackground(gc, Graphics.background_red);
		
		// Detects whether or not search box is selected
		if (y == 1) {
			gc.setFill(Palette.RED);
		} else {
			gc.setFill(Palette.DARK_GRAY);
		}
		
		Graphics.fillRect(gc, -1920, -1080, 3840, 256);

		gc.setFont(font);
		gc.setFill(Palette.LIGHT_GRAY);
		Graphics.text(gc, input, 0, -952);
		
		if (!panes.isEmpty()) {
			for (int i = 0; i < 8; i++) {
				if (panesPos[i][0] == x && panesPos[i][1] == y) {
					panes.get(i).renderAlt(gc);
				} else {
					panes.get(i).render(gc);
				}
			}
		}
	}
	
	@Override
	public void update(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				if (y == 1) {
					if (e.getCode() == KeyCode.ESCAPE) {
						// If no videos, go up, if there are, go down
						if (panes.isEmpty()) {
							y = 0;
						} else {
							y = 2;
						}
					}
					// Search and go down
					if (e.getCode() == KeyCode.ENTER) {
						searchVideos();
						y = 2;
					}
					// Enter letter
					else if (e.getCode().isLetterKey()) {
						input = input + e.getCode().name().toLowerCase();
					}
					else if (e.getCode().isDigitKey()) {
						input = input + e.getCode().name().substring(5, e.getCode().name().length());
					}
					// Space
					else if (e.getCode() == KeyCode.SPACE) {
						input = input + " ";
					}
					// Backspace
					else if (e.getCode() == KeyCode.BACK_SPACE) {
						input = input.substring(0, input.length() - 1);
					}
					// Directional support
					else if (e.getCode() == KeyCode.UP) {
						if (y == 2 || y == 3) {
							y--;
						}
					}
					else if (e.getCode() == KeyCode.DOWN) {
						if (y == 0 || y == 1 || y == 2) {
							y++;
							if (x == 0) {
								x = 1;
							}
						}
					}
				} else {
					if (Controller.isLeft(e)) {
						if (x >= 2 && x <= 4) {
							x--;
						}
					}
					if (Controller.isRight(e)) {
						if (x >= 0 && x <= 3) {
							x++;
							if (y == 0) {
								y = 1;
							}
						}
					}
					if (Controller.isUp(e)) {
						if (y == 2 || y == 3) {
							y--;
						}
					}
					if (Controller.isDown(e)) {
						if (y == 0 || y == 1 || y == 2) {
							y++;
							if (x == 0) {
								x = 1;
							}
						}
					}
					if (Controller.isRed(e)) {
						browser.goBack();
						browser.stop();
						Vision.main_stage.getScene().setRoot(Vision.root);
						Vision.setScreen(Vision.media_screen);
						x = 0;
						y = 0;
					}
					if (Controller.isGreen(e)) {
						String URL = "";
						for (int i = 0; i < 8; i++) {
							if (panesPos[i][0] == x && panesPos[i][1] == y) {
								URL = panes.get(i).getUrl();
							}
						}
						Vision.main_stage.getScene().setRoot(new BorderPane(browserView));
						browser.loadURL(URL);
						browser.setLoadHandler(new LoadHandler(){
							// Idea by Andre Mendes because I'm fucking stupid
							@Override
							public boolean onLoad(LoadParams arg0) {
								  robot.mouseMove((int)(1920 * Vision.SCALE), (int)(1080 * Vision.SCALE));
								  robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
								  robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
								return false;
							}
							@Override public boolean canNavigateOnBackspace() {return false;}
							@Override public boolean onCertificateError(CertificateErrorParams arg0) {return false;}
						});
					}
				}
			}
		});
	}
	
	/*
	 * Search YouTube for top 8 videos matching the search
	 */
	public void searchVideos() {
		try {
			YouTube.Search.List search = youtube.search().list("id,snippet");
			search.setKey(KEY);
	        search.setQ(input);
	        search.setType("video");
	        search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
	        search.setMaxResults((long) 8);
	        
	        SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null) {
                renderResults(searchResultList.iterator(), input);
            }
		} catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}
	
	public void renderResults(Iterator<SearchResult> iteratorSearchResults, String query) {
        int k = 0;
	        while (iteratorSearchResults.hasNext()) {
	            SearchResult singleVideo = iteratorSearchResults.next();
	            ResourceId rId = singleVideo.getId();
	
	            if (rId.getKind().equals("youtube#video")) {
	                int i = 0;
	                int j = 0;
	                if (k > 3) {
	                	i = (k - 4); 
	                	j = 1;
	                } else {
	                	i = k;
	                	j = 0;
	                }
	                panes.add(new YouTubePane("https://www.youtube.com/embed/" + rId.getVideoId(), singleVideo.getSnippet().getTitle(), "https://i.ytimg.com/vi/" + rId.getVideoId() + "/mqdefault.jpg", -1728 + (912 * i), -566 + (780 * j)));
	            }
	            k++;
	        }
	}
}