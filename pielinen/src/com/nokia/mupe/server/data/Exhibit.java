package com.nokia.mupe.server.data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Exhibit implements IExhibit {
	
	private int id;
	private String title;
	private String text;
	private String pictureName;

	Exhibit(int id, String title, String text, String pictureName) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.pictureName = pictureName;
	}
	
	static IExhibit query(int id) {
		try {
			Connection connection = Museum.getConnection();
			PreparedStatement statement = connection.prepareStatement(
				"SELECT TITLE, EXHIBIT_DESC, IMG_URL FROM EXHIBITS WHERE EXHIBIT_ID = ?");
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			while(results.next())
				return new Exhibit(
					id, 
					results.getString("TITLE"),
					results.getString("EXHIBIT_DESC"),
					results.getString("IMG_URL"));
		}
		catch(SQLException e) {
			System.err.println(e);
		}
		return null;
	}
	
	static List<IExhibit> queryByGroup(String id) {
		try {
			Connection connection = Museum.getConnection();
			PreparedStatement statement = connection.prepareStatement(
				"SELECT EXHIBIT_ID, TITLE, EXHIBIT_DESC, IMG_URL FROM EXHIBITS WHERE FK_GROUP_ID = ?");
			statement.setString(1, id);
			ResultSet results = statement.executeQuery();
			List<IExhibit> exhibitList = new ArrayList<IExhibit>();
			while(results.next()) {
				exhibitList.add(
					new Exhibit(
						results.getInt("EXHIBIT_ID"), 
						results.getString("TITLE"),
						results.getString("EXHIBIT_DESC"),
						results.getString("IMG_URL")
					)
				);
			}
			return exhibitList;
		}
		catch(SQLException e) {
			System.err.println(e);
		}
		return null;
	}
	
	static IExhibit queryIndexInGroup(String groupID, int indexInGroup) {
		try {
			Connection connection = Museum.getConnection();
			PreparedStatement statement = connection.prepareStatement(
				"SELECT EXHIBIT_ID, TITLE, EXHIBIT_DESC, IMG_URL FROM EXHIBITS WHERE FK_GROUP_ID = ? LIMIT ?,1");
			statement.setString(1, groupID);
			statement.setInt(2, indexInGroup);
			ResultSet results = statement.executeQuery();
			while(results.next())
				return new Exhibit(
					results.getInt("EXHIBIT_ID"), 
					results.getString("TITLE"),
					results.getString("EXHIBIT_DESC"),
					results.getString("IMG_URL"));
		}
		catch(SQLException e) {
			System.err.println(e);
		}
		return null;
	}
	
	// Index starts at 0 !!!
	static IExhibit queryPreviewIndexInGroup(String groupID, int indexInGroup) {
		try {
			Connection connection = Museum.getConnection();
			PreparedStatement statement = connection.prepareStatement(
				"SELECT EXHIBIT_ID, TITLE, IMG_URL FROM EXHIBITS WHERE FK_GROUP_ID = ? LIMIT ?,1");
			statement.setString(1, groupID);
			statement.setInt(2, indexInGroup);
			ResultSet results = statement.executeQuery();
			while(results.next())
				return new Exhibit(
					results.getInt("EXHIBIT_ID"), 
					results.getString("TITLE"),
					null,
					results.getString("IMG_URL"));
		}
		catch(SQLException e) {
			System.err.println(e);
		}
		return null;
	}
	
	static int queryCountInGroup(String groupID) {
		try {
			Connection connection = Museum.getConnection();
			PreparedStatement statement = connection.prepareStatement(
				"SELECT COUNT(*) FROM EXHIBITS WHERE FK_GROUP_ID = ?");
			statement.setString(1, groupID);
			ResultSet results = statement.executeQuery();
			while(results.next())
				return results.getInt("COUNT(*)");
		}
		catch(SQLException e) {
			System.err.println(e);
		}
		return -1;
	}

	public int getID() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}
	
	public String getPictureName() {
		return pictureName;
	}
	
	public String getPictureName(int width, int height) {
		try {
			String pictureName = ExhibitImage.getName(this.pictureName, width, height);
			System.out.println("getPictureName: " + pictureName);
			return pictureName;
		}
		catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}
}
