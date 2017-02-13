/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Icons {

	public static Sprite ACCOUNT;
	public static Sprite APPS;
	public static Sprite BULB;
	public static Sprite COLLECTIONS;
	public static Sprite FACEBOOK;
	public static Sprite FEEDBACK;
	public static Sprite FOLDER;
	public static Sprite GAMES;
	public static Sprite INFO;
	public static Sprite MOVIES;
	public static Sprite MOVIES_ALT;
	public static Sprite MUSIC;
	public static Sprite NETFLIX;
	public static Sprite PHONE;
	public static Sprite PLEX;
	public static Sprite SEARCH;
	public static Sprite SETTINGS;
	public static Sprite SPOTIFY;
	public static Sprite TV_GUIDE;
	public static Sprite TWITTER;
	public static Sprite WHATSAPP;
	public static Sprite WWE;
	public static Sprite YOUTUBE;
	
	public static void loadAll() {
		Icons.ACCOUNT = Graphics.createSprite(Gdx.files.internal("icons/account.png"));
    	Icons.APPS = Graphics.createSprite(Gdx.files.internal("icons/apps.png"));
    	Icons.BULB = Graphics.createSprite(Gdx.files.internal("icons/bulb.png"));
    	Icons.COLLECTIONS = Graphics.createSprite(Gdx.files.internal("icons/collections.png"));
    	Icons.FACEBOOK = Graphics.createSprite(Gdx.files.internal("icons/facebook.png"));
    	Icons.FEEDBACK = Graphics.createSprite(Gdx.files.internal("icons/feedback.png"));
    	Icons.FOLDER = Graphics.createSprite(Gdx.files.internal("icons/folder.png"));
    	Icons.GAMES = Graphics.createSprite(Gdx.files.internal("icons/games.png"));
    	Icons.INFO = Graphics.createSprite(Gdx.files.internal("icons/info.png"));
    	Icons.MOVIES = Graphics.createSprite(Gdx.files.internal("icons/movies.png"));
    	Icons.MOVIES_ALT = Graphics.createSprite(Gdx.files.internal("icons/movies_alt.png"));
    	Icons.MUSIC = Graphics.createSprite(Gdx.files.internal("icons/music.png"));
    	Icons.NETFLIX = Graphics.createSprite(Gdx.files.internal("icons/netflix.png"));
    	Icons.PHONE = Graphics.createSprite(Gdx.files.internal("icons/phone.png"));
    	Icons.PLEX = Graphics.createSprite(Gdx.files.internal("icons/plex.png"));
    	Icons.SEARCH = Graphics.createSprite(Gdx.files.internal("icons/search.png"));
    	Icons.SETTINGS = Graphics.createSprite(Gdx.files.internal("icons/settings.png"));
    	Icons.SPOTIFY = Graphics.createSprite(Gdx.files.internal("icons/spotify.png"));
    	Icons.TV_GUIDE = Graphics.createSprite(Gdx.files.internal("icons/tv_guide.png"));
    	Icons.TWITTER = Graphics.createSprite(Gdx.files.internal("icons/twitter.png"));
    	Icons.WHATSAPP = Graphics.createSprite(Gdx.files.internal("icons/whatsapp.png"));
    	Icons.WWE = Graphics.createSprite(Gdx.files.internal("icons/wwe.png"));
    	Icons.YOUTUBE = Graphics.createSprite(Gdx.files.internal("icons/youtube.png"));
	}
}