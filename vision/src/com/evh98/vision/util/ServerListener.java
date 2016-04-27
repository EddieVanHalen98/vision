/**
 * Vision - Created and owned by Muhammad Saeed (EddieVanHalen98)
 * 
 * RemoteListener.java
 * Handles the controls for Remote
 * 
 * File created on 22nd April 2016
 */

package com.evh98.vision.util;

import java.awt.Robot;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ServerListener extends Listener {
	
	Robot robot;
	
	public ServerListener() {
		
	}
	
	public void received(Connection connection, Object object) {
		if (object instanceof String) {
			String request = (String) object;
			
			if (request.equals("youtube")) {
				
			}
		}
	}
	
}