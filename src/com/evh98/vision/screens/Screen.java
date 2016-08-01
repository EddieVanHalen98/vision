/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 * 
 * Screen.java
 * The superclass for Screen objects
 * 
 * File created on 19th April 2016
 */

package com.evh98.vision.screens;

import com.evh98.vision.util.Controller;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class Screen {

	protected GraphicsContext gc;
	
	public Screen(GraphicsContext gc) {
		this.gc = gc;
	}
	
	public void start() {
		
	}
	
	public void render() {
        
	}
	
	public void update(Scene scene) {
		
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
	public int[] getNewXY(KeyEvent e, int x, int y, int columns, int rows, int totalItems){
		if(x == -1 && y == -1){
			return new int[] {1,1};
		}
		if(x == -1)
			x = 0;
		if(y == -1)
			y = 0;
		if(Controller.isLeft(e)){
			x--;
		}
		if(Controller.isRight(e)){
			x++;
		}
		if(Controller.isUp(e)){
			y--;
		}
		if(Controller.isDown(e)){
			y++;
		}
		if(x > columns){
			y++;
			x = 1;
		}
		if(y > rows){
			y = 1;
		}
		if(x == 0){
			x = columns;
			y--;
		}
		if(y==0)
			y = rows;
		
		int leftOver = totalItems % columns;
		
		if(leftOver != 0 && x > leftOver && y == rows){
			//the selected item doesn't exist. So just do whatever we were again until it does
			return getNewXY(e, x, y, columns, rows, totalItems);
		}
		
		return new int[] {x, y};
	}
}