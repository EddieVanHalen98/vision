/**
 * Vision
 * 
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.media;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.evh98.vision.Vision;
import com.evh98.vision.util.Graphics;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Movie {
	
	private final String title;
	private final String year;
	private final File file;
	
	private Sprite poster;
	private String genre;
	private String cert;
	private String release;
	private String runtime;
	private String plot;
	private String rating;
	
	public Movie (String title, String year, File file) {
		this.title = title;
		this.year = year;
		this.file = file;
		
		scanPoster();
	}
	
	public void scanPoster() {
		File f = new File(System.getProperty("user.home") + "/Vision/assets/posters/" + title + ".png");
		
		if(!f.exists()) {
			String sURL = "http://www.omdbapi.com/?t=" + title + "&plot=short&r=json";
			sURL = sURL.replace(" ", "%20");
			
			try {
				URL url = new URL(sURL);
				HttpURLConnection request = (HttpURLConnection) url.openConnection();
				request.connect();
				JsonParser jp = new JsonParser();
				JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
				JsonObject rootobj = root.getAsJsonObject();
				String temp = rootobj.get("Poster").getAsString();
				FileUtils.copyURLToFile(new URL(temp), f);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		
		if (f.exists()) {
			poster = Graphics.createSprite(Gdx.files.absolute(f.getPath().toString()));
		} else {
			poster = Graphics.default_movie;
		}
	}
	
	public void getMeta() {
		String sURL = "http://www.omdbapi.com/?t=" + title + "&plot=short&r=json";
		sURL = sURL.replace(" ", "%20");

		try {
			URL url = new URL(sURL);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.connect();
			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			JsonObject rootobj = root.getAsJsonObject();
			
			genre = rootobj.get("Genre").getAsString();
			cert = rootobj.get("Rated").getAsString();
			release = rootobj.get("Released").getAsString();
			runtime = rootobj.get("Runtime").getAsString();
			plot = rootobj.get("Plot").getAsString();
			rating = rootobj.get("imdbRating").getAsString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public String getTitle() {
		return title;
	}

	public String getYear() {
		return year;
	}

	public File getFile() {
		return file;
	}

	public Sprite getPoster() {
		return poster;
	}

	public String getGenre() {
		return genre;
	}

	public String getCert() {
		return cert;
	}

	public String getRelease() {
		return release;
	}

	public String getRuntime() {
		return runtime;
	}

	public String getPlot() {
		return plot;
	}

	public String getRating() {
		return rating;
	}
	
	public void open() {
		try {
			Desktop.getDesktop().open(file);
			
			Thread.sleep(2000);
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_WINDOWS);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_WINDOWS);
			robot.mouseMove((int) Vision.WIDTH / 2, (int) Vision.HEIGHT / 2);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		} catch (IOException | InterruptedException | AWTException e) {
			e.printStackTrace();
		}
	}
}