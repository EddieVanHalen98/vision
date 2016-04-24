package com.evh98.vision.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.evh98.vision.Vision;

public class Update {

	/*
	 * Checks if there is an update available for Vision
	 */
	public static boolean isAvailable() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new URL("http://www.evh98.com/vision/version.txt").openStream()));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				int i = Integer.parseInt(sCurrentLine);
				
				if (i != Vision.BUILD_NUMBER) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	/*
	 * Downloads the latest Vision.jar from the server
	 */
	public static void downloadUpdate() {
		try {
			FileUtils.copyURLToFile(new URL("http://www.evh98.com/vision/vision.jar"), new File(java.lang.System.getProperty("user.home") + "/Vision/vision-latest.jar"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}