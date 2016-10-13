package com.evh98.vision.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.evh98.vision.Vision;
import com.evh98.vision.media.Movie;

public class Data {
	
	static Document doc;
	
	public static String MOVIES_FILEPATH = System.getProperty("user.home") + "/Movies";

	public static void savePrefs() throws Exception {
		init();
		
		// Root element
		Element root = doc.createElement("prefs");
		doc.appendChild(root);
		
		// Resolution elements
		Element e = doc.createElement("width");
		e.appendChild(doc.createTextNode(String.valueOf(Vision.WIDTH)));
		root.appendChild(e);
		e = doc.createElement("height");
		e.appendChild(doc.createTextNode(String.valueOf(Vision.HEIGHT)));
		root.appendChild(e);
		// Fullscreen element
		e = doc.createElement("fullscreen");
		e.appendChild(doc.createTextNode(String.valueOf(Vision.FULLSCREEN)));
		root.appendChild(e);
		// Movies filepath element
		e = doc.createElement("movies-filepath");
		e.appendChild(doc.createTextNode(String.valueOf(MOVIES_FILEPATH)));
		
		save("prefs");
	}
	
	public static void loadPrefs() throws Exception {
		load("prefs");
        
        NodeList nList = doc.getElementsByTagName("prefs");
        
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            	Element e = (Element) nNode;
            	Vision.WIDTH = Integer.valueOf(e.getElementsByTagName("width").item(0).getTextContent());
            	Vision.HEIGHT = Integer.valueOf(e.getElementsByTagName("height").item(0).getTextContent());
            	Vision.FULLSCREEN = Boolean.valueOf(e.getElementsByTagName("fullscreen").item(0).getTextContent());
            	MOVIES_FILEPATH = e.getElementsByTagName("movies-filepath").item(0).getTextContent();
            }
        }
	}
	
	public static void saveMovies() throws Exception {
		init();
		
		// Root element
		Element root = doc.createElement("movies");
		doc.appendChild(root);
		
		for (Movie movie: Vision.movies) {
			// Movie element
			Element parent = doc.createElement("movie");
	        root.appendChild(parent);
	        
	        // Title
	        Attr attr = doc.createAttribute("title");
	        attr.setValue(movie.getTitle());
	        parent.setAttributeNode(attr);
	        
	        // Additional
	        Element e = doc.createElement("year");
	        e.appendChild(doc.createTextNode(movie.getYear()));
	        parent.appendChild(e);
	        e = doc.createElement("filepath");
	        e.appendChild(doc.createTextNode(movie.getFile().toString()));
	        parent.appendChild(e);
		}
		
		save("movies");
	}
	
	public static void loadMovies() throws Exception {
		String title, year, file;
		
		load("movies");
        
        NodeList nList = doc.getElementsByTagName("movie");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            	Element e = (Element) nNode;
            	title = e.getAttribute("title");
            	year = e.getElementsByTagName("year").item(0).getTextContent();
            	file = e.getElementsByTagName("filepath").item(0).getTextContent();
            	
            	Vision.movies.add(new Movie(title, year, new File(file)));
            }
        }
	}
	
	private static void init() throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder =  dbFactory.newDocumentBuilder();
		doc = builder.newDocument();
	}
	
	private static void save(String name) throws Exception {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(Vision.HOME + "data/" + name + ".dat"));
		transformer.transform(source, result);
	}
	
	private static void load(String name) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        doc = builder.parse(new File(Vision.HOME + "data/" + name + ".dat"));
        doc.getDocumentElement().normalize();
	}
}