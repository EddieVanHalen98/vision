package com.evh98.vision.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.evh98.vision.Vision;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Update {

	/**
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
	
	/**
	 * Downloads the latest Vision.jar from the server
	 */
	public static void downloadUpdate() {
		// Download zip
		try {
			FileUtils.copyURLToFile(new URL("http://www.evh98.com/vision/vision.zip"), new File(java.lang.System.getProperty("user.home") + "/Vision/vision.zip"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Extract zip
		try {
			ZipFile file = new ZipFile(java.lang.System.getProperty("user.home") + "/Vision/vision.zip");
			file.extractAll(java.lang.System.getProperty("user.home") + "/Vision");
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
}