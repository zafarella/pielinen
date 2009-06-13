package com.nokia.mupe.server.data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Group implements IGroup {
	
	private String id;	
	private String title;
	private String text;	

	Group(String id, String title, String text) {
		this.id = id;
		this.title = title;
		this.text = text;		
	}
	
	static IGroup query(String id) {
		try {
			Connection connection = Museum.getConnection();
			PreparedStatement sqlGetGroup = connection.prepareStatement("SELECT TITLE, GROUP_DESCRIPTION FROM GROUPS WHERE GROUP_ID = ?");
			sqlGetGroup.setString(1, id);
			ResultSet results = sqlGetGroup.executeQuery();
			while(results.next())
				return new Group(id, results.getString("TITLE"), results.getString("GROUP_DESCRIPTION"));
		}
		catch(SQLException e) {
			System.err.println(e);
		}
		return null;
	}
	
	// Index starts at 0!
	public IExhibit getExhibit(int indexInGroup) {
		return Exhibit.queryIndexInGroup(this.id, indexInGroup);
	}
	
	// Index starts at 0!
	public IExhibit getExhibitPreview(int indexInGroup) {
		return Exhibit.queryPreviewIndexInGroup(this.id, indexInGroup);
	}
	
	public int getExhibitCount() {
		return Exhibit.queryCountInGroup(this.id);
	}
	
	public List<IExhibit> getExhibitList() {
		return Exhibit.queryByGroup(id);
	}

	public String getID() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}
}
