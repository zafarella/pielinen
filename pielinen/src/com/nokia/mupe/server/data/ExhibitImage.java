package com.nokia.mupe.server.data;

import java.io.*;
import java.util.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.Dimension;
import javax.imageio.ImageIO;

public class ExhibitImage {

	private static final String imageDirectory = "media";
	private static Map<String, Dimension> imageDimensions = null;
	
	private ExhibitImage() {}
	
	public static void main(String[] args) {
		System.out.println("Testing ExhibitImage:");
		loadDimensions();
		Set<String> keys = imageDimensions.keySet();
		System.out.println("Loading image dimensions:");
		for(String key : keys)
			System.out.println(key);
		String name = null;
		try {
			name = getName("sample3.png", 100, 100);
		}
		catch(Exception e) {
			System.err.println("Bother.");
			System.err.println(e);
		}
		System.out.println("Name: " + name);
		System.out.println("Done.");
	}
	
	public static void initialize() {
		loadDimensions();
	}
	
	public static void loadDimensions() {
		File imageDirectory = new File(ExhibitImage.imageDirectory);
		File[] imageFiles = imageDirectory.listFiles(
			new FilenameFilter() {
				public boolean accept(File dir, String name) {
        			return (name.endsWith(".png"));
				}
			}
		);
		ExhibitImage.imageDimensions = new HashMap<String, Dimension>();
		for(File imageFile : imageFiles) {
			Image image = null;
			try {
				image = ImageIO.read(imageFile);
			}
			catch(Exception e) {
				System.err.println("Cannot read file: " + imageFile.getName());
				System.err.println(e);
			}
			ExhibitImage.imageDimensions.put(
				imageFile.getName(),
				new Dimension(image.getWidth(null), image.getHeight(null))
			);
		}
	}
	
	private static String stripExtension(String fileName) {
		int dotPosition = fileName.lastIndexOf('.');
		return fileName.substring(0, dotPosition);
	}
	
	private static String getExtension(String fileName) {
		int dotPosition = fileName.lastIndexOf('.');
		return fileName.substring(dotPosition + 1);
	}
	
	public static String getName(String imageName, int width, int height) throws Exception {
		if(imageDimensions == null) loadDimensions();
		
		Dimension dimension = imageDimensions.get(imageName);
		if(dimension == null) {
			System.err.println("Image" + imageName + "does not exist!");
			throw new Exception("Image" + imageName + "does not exist!");
		}
		System.out.println("DIM " + dimension.width + ", DIM " + dimension.height + ", W " + width + ", H " + height);
		int scaleIndex = 1;
		String currentName = imageName;
		System.out.println("ImageName: " + imageName);
		while(dimension.width > width || dimension.height > height) {
			currentName = stripExtension(imageName) + "_" + scaleIndex + "." + getExtension(imageName);
			System.out.println("CurrentName: " + currentName);
			Dimension oldDimension = dimension;
			dimension = imageDimensions.get(currentName);
			System.out.println("Dimension: " + dimension);
			if(dimension == null) {
				Image image = null;
				try {
					image = ImageIO.read(new File(imageDirectory + "/" + imageName));
				}
				catch(Exception e) {
					System.err.println("Cannot read file: " + imageDirectory + "/" + imageName);
					System.err.println(e);
				}
				int newWidth = -1;
				int newHeight = -1;
				if(oldDimension.width > width) {
					newWidth = width;
					newHeight = (int)(oldDimension.height * ((double)width / oldDimension.width));
					if(newHeight > height) {
						newHeight = height;
						newWidth = (int)(oldDimension.width * ((double)height / oldDimension.height));
					}
				}
				else if(oldDimension.height > height) {
					newHeight = height;
					newWidth = (int)(oldDimension.width * ((double)height / oldDimension.height));
					if(newWidth > width) {
						newWidth = width;
						newHeight = (int)(oldDimension.height * ((double)width / oldDimension.width));
					}
				}
				System.out.println("NW " + newWidth + ", NH " + newHeight);
				Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST);
				BufferedImage buffer = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
				buffer.createGraphics().drawImage(scaledImage, 0, 0, null);
				try {
					ImageIO.write(buffer, "png", new File(imageDirectory + "/" + currentName));
				}
				catch(Exception e) {
					System.err.println("Cannot write to: " + imageDirectory + "/" + currentName);
					System.err.println(e);
					return null;
				}
				imageDimensions.put(currentName, new Dimension(newWidth, newHeight));
				return currentName;
			}
			scaleIndex++;
		}
		return currentName;
	}
}
