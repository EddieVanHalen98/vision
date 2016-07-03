/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 *
 * MediaScreen.java
 * Media screen
 *
 * File created on 24th April 2016
 */

package com.evh98.vision.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.FileUtils;

import com.evh98.vision.Vision;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Update {

	private static int LATEST_NUMBER = 0;
	
	/**
	 * Checks if there is an update available for Vision
	 */
	public static boolean isAvailable() {
		try {                                                                                                                                                                                                                                 
			final URL url = new URL("http://www.google.com");
	        final URLConnection conn = url.openConnection();
	        conn.connect();
		} catch (MalformedURLException e) {
	    	throw new RuntimeException(e);
	    } catch (IOException e) {
	    	return false;
	    }
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new URL("http://www.evh98.com/vision/version").openStream()));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				int i = Integer.parseInt(sCurrentLine);
				if (i != Vision.BUILD_NUMBER) {
					LATEST_NUMBER = i;
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	/**
	 * Downloads the latest Vision.jar from the server
	 */
	public static void downloadUpdate() {
		String home = System.getProperty("user.home");
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("windows")) {
			os = "win";
		}
		else if (os.contains("mac")) {
			os = "mac";
		}
		else if (os.contains("linux")) {
			os = "linux";
		}
		
		for (int i = (Vision.BUILD_NUMBER + 1); i <= LATEST_NUMBER; i++) {
			// Downloads the patch package
			try {
				FileUtils.copyURLToFile(new URL("http://www.evh98.com/vision/releases/" + i + "-os.zip"), new File(home + "/Vision-Latest.zip"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Extract the package
			try {
				ZipFile file = new ZipFile(home + "/Vision-Latest.zip");
				file.extractAll(home + "/Vision-Latest");
			} catch (ZipException e) {
			 	e.printStackTrace();
			}
			
			// Delete the package
			File f = new File(home + "/Vision-Latest.zip");
			f.delete();
		}
	}
}