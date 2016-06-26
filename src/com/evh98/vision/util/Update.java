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
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import com.evh98.vision.Vision;

public class Update {

	public static ArrayList<File> netFiles;
	public static ArrayList<File> localFiles;
	
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
		boolean matchNotFound = false;
		
		netFiles = new ArrayList<File>();
		localFiles = new ArrayList<File>();
		
		// Gets all files from latest vision branch into an array
		File[] netFolder = null;
		try {
			netFolder = new File(new URL("http://www.evh98.com/vision-latest/").toURI()).listFiles();
		} catch (MalformedURLException | URISyntaxException e) {
			e.printStackTrace();
		}
		fileLoopNet(netFolder);
		
		// Gets all files from local vision branch into an array
		File[] localFolder;
		localFolder = new File(java.lang.System.getProperty("user.home") + "/Vision/").listFiles();
		fileLoopLocal(localFolder);
		
		for (File netFile: netFiles) {
			for (File localFile: localFiles) {
				String[] i = netFile.toPath().toString().split("/vision-latest/");
				String netFileAbs = i[i.length - 1];
				
				String[] j = localFile.toPath().toString().split("/Vision/");
				String localFileAbs = j[j.length - 1];
				
				if (!netFileAbs.equals(localFileAbs)) {
					 matchNotFound = true;
				} else {
					if (!(netFile.length() == localFile.length())) {
						matchNotFound = true;
					}
				}
 			}
			
			if (matchNotFound) {
				String[] i = netFile.toPath().toString().split("/vision-latest/");
				String netFileAbs = i[i.length - 1];
				
				try {
					FileUtils.copyURLToFile(new URL(netFile.getName()), new File(java.lang.System.getProperty("user.home") + "/Vision/" + netFileAbs));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void fileLoopNet(File[] files) {
		for (File file: files) {
			if (file.isDirectory()) {
				fileLoopNet(file.listFiles());
			} else {
				netFiles.add(file);
			}
		}
	}
	
	public static void fileLoopLocal(File[] files) {
		for (File file: files) {
			if (file.isDirectory()) {
				fileLoopLocal(file.listFiles());
			} else {
				localFiles.add(file);
			}
		}
	}
}