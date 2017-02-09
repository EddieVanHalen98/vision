/**
 * Vision
 * 
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Controller {
	
	/*
	 * Checks if any of the applicable Vision 'Green' keys have been detected
	 */
	public static boolean isGreen() {
		return (Gdx.input.isKeyJustPressed(Keys.E) || Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isKeyJustPressed(Keys.ENTER));
	}

	/*
	 * Checks if any of the applicable Vision 'Red' keys have been detected
	 */
	public static boolean isRed() {
		return (Gdx.input.isKeyJustPressed(Keys.R) || Gdx.input.isKeyJustPressed(Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Keys.BACKSPACE));
	}

	/*
	 * Checks if any of the applicable Vision 'Blue' keys have been detected
	 */
	public static boolean isBlue() {
		return (Gdx.input.isKeyJustPressed(Keys.F));
	}

	/*
	 * Checks if any of the applicable Vision 'Yellow' keys have been detected
	 */
	public static boolean isYellow() {
		return (Gdx.input.isKeyJustPressed(Keys.Q));
	}

	/*
	 * Checks if any of the applicable Vision 'Up' keys have been detected
	 */
	public static boolean isUp() {
		return (Gdx.input.isKeyJustPressed(Keys.W) || Gdx.input.isKeyJustPressed(Keys.UP));
	}
	
	/*
	 * Checks if any of the applicable Vision 'Down' keys have been detected
	 */
	public static boolean isDown() {
		return (Gdx.input.isKeyJustPressed(Keys.S) || Gdx.input.isKeyJustPressed(Keys.DOWN));
	}

	/*
	 * Checks if any of the applicable Vision 'Right' keys have been detected
	 */
	public static boolean isRight() {
		return (Gdx.input.isKeyJustPressed(Keys.D) || Gdx.input.isKeyJustPressed(Keys.RIGHT));
	}

	/*
	 * Checks if any of the applicable Vision 'Left' keys have been detected
	 */
	public static boolean isLeft() {
		return (Gdx.input.isKeyJustPressed(Keys.A) || Gdx.input.isKeyJustPressed(Keys.LEFT));
	}

	/*
	 * Checks if any of the applicable Vision 'Search' keys have been detected
	 */
	public static boolean isSearch() {
		return Gdx.input.isKeyJustPressed(Keys.TAB);
	}
	
	/*
	 * Checks if any of the applicable Vision navigation keys have been detected
	 */
	public static boolean isNavigationKey() {
		return isUp() || isDown() || isRight() || isLeft();
	}
	
	/*
	 * Checks if any of the applicable Vision keys have been detected
	 */
	public static boolean isAnyKey() {
		return isGreen() || isRed() || isBlue() || isYellow() || isUp() ||
				isDown() || isRight() || isLeft() || isSearch();
	}
	
	/*
	 * Checks if the space character is entered
	 */
	public static boolean isSpace() {
		return Gdx.input.isKeyJustPressed(Keys.SPACE);
	}
	
	/*
	 * Checks if the backspace character is entered
	 */
	public static boolean isBackspace() {
		return Gdx.input.isKeyJustPressed(Keys.BACKSPACE);
	}
	
	/*
	 * Checks if the enter character is entered
	 */
	public static boolean isEnter() {
		return Gdx.input.isKeyJustPressed(Keys.ENTER);
	}
	
	/*
	 * Checks what letter character is entered
	 */
	public static String letterPressed() {
		if (Gdx.input.isKeyJustPressed(Keys.A)) {
			return "a";
		} else if (Gdx.input.isKeyJustPressed(Keys.B)) {
			return "b";
		} else if (Gdx.input.isKeyJustPressed(Keys.C)) {
			return "c";
		} else if (Gdx.input.isKeyJustPressed(Keys.D)) {
			return "d";
		} else if (Gdx.input.isKeyJustPressed(Keys.E)) {
			return "e";
		} else if (Gdx.input.isKeyJustPressed(Keys.F)) {
			return "f";
		} else if (Gdx.input.isKeyJustPressed(Keys.G)) {
			return "g";
		} else if (Gdx.input.isKeyJustPressed(Keys.H)) {
			return "h";
		} else if (Gdx.input.isKeyJustPressed(Keys.I)) {
			return "i";
		} else if (Gdx.input.isKeyJustPressed(Keys.J)) {
			return "j";
		} else if (Gdx.input.isKeyJustPressed(Keys.K)) {
			return "k";
		} else if (Gdx.input.isKeyJustPressed(Keys.L)) {
			return "l";
		} else if (Gdx.input.isKeyJustPressed(Keys.M)) {
			return "m";
		} else if (Gdx.input.isKeyJustPressed(Keys.N)) {
			return "n";
		} else if (Gdx.input.isKeyJustPressed(Keys.O)) {
			return "o";
		} else if (Gdx.input.isKeyJustPressed(Keys.P)) {
			return "p";
		} else if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			return "q";
		} else if (Gdx.input.isKeyJustPressed(Keys.R)) {
			return "r";
		} else if (Gdx.input.isKeyJustPressed(Keys.S)) {
			return "s";
		} else if (Gdx.input.isKeyJustPressed(Keys.T)) {
			return "t";
		} else if (Gdx.input.isKeyJustPressed(Keys.U)) {
			return "u";
		} else if (Gdx.input.isKeyJustPressed(Keys.V)) {
			return "v";
		} else if (Gdx.input.isKeyJustPressed(Keys.W)) {
			return "w";
		} else if (Gdx.input.isKeyJustPressed(Keys.X)) {
			return "x";
		} else if (Gdx.input.isKeyJustPressed(Keys.Y)) {
			return "y";
		} else if (Gdx.input.isKeyJustPressed(Keys.Z)) {
			return "z";
		} else {
			return "/";
		}
	}
	
	/*
	 * Checks what numerical character is entered
	 */
	public static String digitPressed() {
		if (Gdx.input.isKeyJustPressed(Keys.NUM_0) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_0)) {
			return "0";
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_1) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_1)) {
			return "1";
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_2) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_2)) {
			return "2";
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_3) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_3)) {
			return "3";
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_4) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_4)) {
			return "4";
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_5) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_5)) {
			return "5";
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_6) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_6)) {
			return "6";
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_7) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_7)) {
			return "7";
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_8) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_8)) {
			return "8";
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_9) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_9)) {
			return "9";
		} else {
			return "/";
		}
	}
	
	/**
	 * get coordinates of new selected pane based on key event and current selected pane
	 * @param e key event
	 * @param x current x coordinate
	 * @param ycurrent y coordinate
	 * @param columns total columns
	 * @param rows total rows
	 * @param totalItems how many total panes are there
	 * @return int array [x,y] containing new coordinates
	 */
	public static int[] getNewXY(int x, int y, int columns, int rows, int totalItems){
		
		if (x == -1 && y == -1){
			return new int[] {1, 1};
		}
		if (x == -1) {
			x = 0;
		}
		if (y == -1) {
			y = 0;
		}
		if (isLeft()) {
			x--;
		}
		if (isDown()) {
			y++;
		}
		if (isRight()) {
			x++;
		}
		if (isUp()) {
			y--;
		}
		if (x > columns) {
			y++;
			x = 1;
		}
		if (y > rows) {
			y = 1;
		}
		if (x == 0) {
			x = columns;
			y--;
		}
		if (y == 0) {
			y = rows;
		}
		
		int leftOver = totalItems % columns;
		
		if(leftOver != 0 && x > leftOver && y == rows){
			//the selected item doesn't exist. So just do whatever we were again until it does
			return getNewXY(x, y, columns, rows, totalItems);
		}
		
		return new int[] {x, y};
	}
}