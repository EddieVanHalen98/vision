/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.apps;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.evh98.vision.Vision;
import com.evh98.vision.screens.VisionScreen;
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

public class YouTubeScreen extends VisionScreen {
	
	BitmapFont font;
	int x = 0, y = -1;
	public String input = "";
	
	ArrayList<YouTubePane> panes;
    int[][] panesPos = {{1, 2}, {2, 2}, {3, 2}, {4, 2}, {1, 3}, {2, 3}, {3, 3}, {4, 3}};
    
    Robot robot;

    YouTube youtube;
    String KEY = "AIzaSyC6YdzinsZbyrHbPFtnEujJk8y77jdo_aM";
    List<SearchResult> searchResults;
	
	public YouTubeScreen(Vision vision) {
		super(vision);

		font = Graphics.createFont(Graphics.font_roboto_thin, 176);
		
		panes = new ArrayList<YouTubePane>();

		try {
			robot = new Robot();
		} catch(AWTException e) {
			e.printStackTrace();
		}
		
		// Sets up YouTube API
		youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("evh98-vision").build();
		searchResults = null;
	}

	@Override
	public void show() {
		start(Palette.RED, "youtube");
	}
	
	@Override
	public void draw(float delta) {
		// Search box
		shape_renderer.begin(ShapeType.Filled);
		if (y == 1) {
			shape_renderer.setColor(Palette.RED);
		} else {
			shape_renderer.setColor(Palette.DARK_GRAY);
		}
		Graphics.drawRect(shape_renderer, -1920, -1080, 3840, 256);
		shape_renderer.end();
		
		// Search input
		sprite_batch.begin();
		Graphics.drawText(sprite_batch, font, input, 0, -952);
		sprite_batch.end();
		
		// Panes
		if (!panes.isEmpty()) {
			for (int i = 0; i < 8; i++) {
				if (panesPos[i][0] == x && panesPos[i][1] == y) {
					panes.get(i).renderAlt(sprite_batch, shape_renderer);
				} else {
					panes.get(i).render(sprite_batch, shape_renderer);
				}
			}
		}
	}
	
	@Override
	public void update() {
		if (Controller.isSearch()) Vision.search.toggleSearch();
		
		if (y == 1) {
			if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				// If no videos, go up, if there are, go down
				if (panes.isEmpty()) {
					y = 0;
				} else {
					y = 2;
				}
			}
			// Search and go down
			else if (Controller.isEnter()) {
				Vision.loading.setActive();
				new Thread() {
					public void run() {
						searchResults = searchVideos(input, 8);
						
						Gdx.app.postRunnable(new Runnable() {
							@Override
							public void run() {
					            if (searchResults != null) {
					                renderResults(searchResults.iterator(), input);
					            }
					    		
					    		Vision.loading.setInactive();
							}
						});
					}
				}.start();
				
				y = 2;
			}
			else if (Controller.letterPressed() != "/") input += Controller.letterPressed();
			else if (Controller.digitPressed() != "/") input += Controller.digitPressed();
			else if (Controller.isSpace()) input += " ";
			else if (Controller.isBackspace() && input.length() != 0) input = input.substring(0, input.length() - 1);
			
			// Directional stuff
			else if (Gdx.input.isKeyJustPressed(Keys.UP)) {
				if (y == 2 || y == 3) {
					y--;
				}
			}
			else if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
				if (y == 0 || y == 1 || y == 2) {
					y++;
					if (x == 0) {
						x = 1;
					}
				}
			}
		} else {
			if (Controller.isRed()) vision.setScreen(vision.media_screen);
			else if (Controller.isLeft() && x >= 2 && x <= 4) x--;
			else if (Controller.isRight() && x >= 0 && x <= 3) x++; if (y == 0) y = 1;
			else if (Controller.isUp() && (y == 2 || y == 3)) y--;
			else if (Controller.isDown() && y >= 0 && y <= 2) y++; if (x == 0) x = 1;
			else if (Controller.isGreen()) {
				String URL = "";
				for (int i = 0; i < 8; i++) {
					if (panesPos[i][0] == x && panesPos[i][1] == y) {
						URL = panes.get(i).getUrl();
					}
				}
				Gdx.net.openURI(URL);
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				// Fullscreen video
				robot.keyPress(KeyEvent.VK_F);
				robot.keyRelease(KeyEvent.VK_F);
			}
		}
	}
	
	/*
	 * Search YouTube for top specified amount of videos matching the search
	 */
	public List<SearchResult> searchVideos(String input, int amount) {
		panes.clear();
		
		try {
			YouTube.Search.List search = youtube.search().list("id,snippet");
			search.setKey(KEY);
	        search.setQ(input);
	        search.setType("video");
	        search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
	        search.setMaxResults((long) amount);
	        
	        SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            
            return searchResultList;
		} catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
		
		return null;
	}
	
	/*
	 * Provide the first 8 search results
	 */
	private void renderResults(Iterator<SearchResult> iteratorSearchResults, String query) {
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
        		panes.add(new YouTubePane("https://youtu.be/" + rId.getVideoId(), singleVideo.getSnippet().getTitle(), "https://i.ytimg.com/vi/" + rId.getVideoId() + "/mqdefault.jpg", -1728 + (912 * i), -566 + (780 * j)));
        	}
        	k++;
        }
	}

	@Override
	public void dispose() {
		sprite_batch.dispose();
		shape_renderer.dispose();
		font.dispose();
	}
}