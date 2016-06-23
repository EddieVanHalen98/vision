/**
 * Vision - Created and owned by Muhammad Saeed (EddieVanHalen98)
 * 
 * Controller.java
 * Handles universal Vision controls
 * 
 * File created on 19th April 2016
 */

package com.evh98.vision.util;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {
	
	/*
	 * Checks if any of the applicable Vision 'Green' keys have been detected
	 */
	public static boolean isGreen(KeyEvent e) {
		if (e.getCode() == KeyCode.E || e.getCode() == KeyCode.SPACE || e.getCode() == KeyCode.ENTER) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Checks if any of the applicable Vision 'Red' keys have been detected
	 */
	public static boolean isRed(KeyEvent e) {
		if (e.getCode() == KeyCode.R || e.getCode() == KeyCode.ESCAPE || e.getCode() == KeyCode.BACK_SPACE) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Checks if any of the applicable Vision 'Blue' keys have been detected
	 */
	public static boolean isBlue(KeyEvent e) {
		if (e.getCode() == KeyCode.F) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Checks if any of the applicable Vision 'Yellow' keys have been detected
	 */
	public static boolean isYellow(KeyEvent e) {
		if (e.getCode() == KeyCode.Q) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Checks if any of the applicable Vision 'Up' keys have been detected
	 */
	public static boolean isUp(KeyEvent e) {
		if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Checks if any of the applicable Vision 'Down' keys have been detected
	 */
	public static boolean isDown(KeyEvent e) {
		if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Checks if any of the applicable Vision 'Right' keys have been detected
	 */
	public static boolean isRight(KeyEvent e) {
		if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Checks if any of the applicable Vision 'Left' keys have been detected
	 */
	public static boolean isLeft(KeyEvent e) {
		if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Checks if any of the applicable Vision 'Search' keys have been detected
	 */
	public static boolean isSearch(KeyEvent e) {
		if (e.getCode() == KeyCode.TAB) {
			return true;
		} else {
			return false;
		}
	}
}