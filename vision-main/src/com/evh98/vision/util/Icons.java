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
	public static Sprite BACK;
	public static Sprite BULB;
	public static Sprite COLLECTIONS;
	public static Sprite CONFIRM;
	public static Sprite FACEBOOK;
	public static Sprite FEEDBACK;
	public static Sprite FOLDER;
	public static Sprite GAMES;
	public static Sprite INFO;
	public static Sprite MEDIA;
	public static Sprite MOVIES;
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
		ACCOUNT = Graphics.createSprite(Gdx.files.internal("icons/account.png"));
    	APPS = Graphics.createSprite(Gdx.files.internal("icons/apps.png"));
    	BACK = Graphics.createSprite(Gdx.files.internal("icons/back.png"));
    	BULB = Graphics.createSprite(Gdx.files.internal("icons/bulb.png"));
    	COLLECTIONS = Graphics.createSprite(Gdx.files.internal("icons/collections.png"));
    	CONFIRM = Graphics.createSprite(Gdx.files.internal("icons/confirm.png"));
    	FACEBOOK = Graphics.createSprite(Gdx.files.internal("icons/facebook.png"));
    	FEEDBACK = Graphics.createSprite(Gdx.files.internal("icons/feedback.png"));
    	FOLDER = Graphics.createSprite(Gdx.files.internal("icons/folder.png"));
    	GAMES = Graphics.createSprite(Gdx.files.internal("icons/games.png"));
    	INFO = Graphics.createSprite(Gdx.files.internal("icons/info.png"));
    	MEDIA = Graphics.createSprite(Gdx.files.internal("icons/media.png"));
    	MOVIES = Graphics.createSprite(Gdx.files.internal("icons/movies.png"));
    	MUSIC = Graphics.createSprite(Gdx.files.internal("icons/music.png"));
    	NETFLIX = Graphics.createSprite(Gdx.files.internal("icons/netflix.png"));
    	PHONE = Graphics.createSprite(Gdx.files.internal("icons/phone.png"));
    	PLEX = Graphics.createSprite(Gdx.files.internal("icons/plex.png"));
    	SEARCH = Graphics.createSprite(Gdx.files.internal("icons/search.png"));
    	SETTINGS = Graphics.createSprite(Gdx.files.internal("icons/settings.png"));
    	SPOTIFY = Graphics.createSprite(Gdx.files.internal("icons/spotify.png"));
    	TV_GUIDE = Graphics.createSprite(Gdx.files.internal("icons/tv_guide.png"));
    	TWITTER = Graphics.createSprite(Gdx.files.internal("icons/twitter.png"));
    	WHATSAPP = Graphics.createSprite(Gdx.files.internal("icons/whatsapp.png"));
    	WWE = Graphics.createSprite(Gdx.files.internal("icons/wwe.png"));
    	YOUTUBE = Graphics.createSprite(Gdx.files.internal("icons/youtube.png"));
	}
}