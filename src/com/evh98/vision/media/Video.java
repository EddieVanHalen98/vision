package com.evh98.vision.media;

public class Video {

	private final String title;
	private final int year;
	private final float rating;
	private final String poster;
	
	public Video(String title, int year, float rating, String poster) {
		this.title = title;
		this.year = year;
		this.rating = rating;
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	public float getRating() {
		return rating;
	}

	public String getPoster() {
		return poster;
	}
}