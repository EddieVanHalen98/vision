/**
 * Vision
 * 
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.media;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.scene.image.Image;

public class Movie {

	private final String title;
	private final String year;
	private final File file;
	
	private Image poster;
	private String cert;
	private String release;
	private String runtime;
	private String plot;
	private String rating;
	
	public Movie (String title, String year, File file) {
		this.title = title;
		this.year = year;
		this.file = file;
		
		getMeta();
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

			File f = new File(System.getProperty("user.home") + "/Vision/assets/posters/" + title + ".png");
			if (!f.exists()) {
				String temp = rootobj.get("Poster").getAsString();
				FileUtils.copyURLToFile(new URL(temp), f);
			}
			poster = new Image("file:" + f.getPath().toString());
			
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

	public File getFile() {
		return file;
	}

	public String getTitle() {
		return title;
	}

	public String getYear() {
		return year;
	}

	public Image getPoster() {
		return poster;
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
}