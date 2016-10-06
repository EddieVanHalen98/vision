package com.evh98.vision.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Preferences {

	public static String movies_path;
	
	public static void create() {
		
	}
	
	public static void save() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			
			Element rootElement = doc.createElement("prefs");
	        doc.appendChild(rootElement);
		} catch (Exception e) {
			
		}
	}
	
	public static void load() {
		
	}
}