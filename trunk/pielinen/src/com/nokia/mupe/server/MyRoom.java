/*
TODO:
- groups with no exhibits
- image resizing
- i can't save the exhibit list while listing in the selection screen, because the $@*$)# needs to serialize the room!
*/

package com.nokia.mupe.server;

import com.nokia.mupe.server.data.*;

public class MyRoom extends Room {

	private static final long serialVersionUID = 1L;
	
	private String groupID = null;
	private int maxExhibitImageWidth = -1;
	private int maxExhibitImageHeight = -1;
	
	public MyRoom(User caller, Base location, String name) throws MupeException {
		super(caller, location, name);
	}
	
	public String clientSetMaxExhibitImageDimensions(User caller, String maxExhibitImageWidth, String maxExhibitImageHeight) throws MupeException {
		this.maxExhibitImageWidth = Integer.parseInt(maxExhibitImageWidth);
		this.maxExhibitImageHeight = Integer.parseInt(maxExhibitImageHeight);
		return "";
	}
	
	public String clientGetGroup(User caller, String groupID) throws MupeException {
		this.groupID = groupID;
		IGroup group = Museum.getGroup(groupID);
		if(group != null) {
			return getDynamicXML("get_grp.xml", caller, group.getTitle(), group.getText());
		}
		// Unknown ID -> no action
		return "";
	}
	
	public String clientGetExhibit(User caller, String exhibitIndex) throws MupeException {
		int index = Integer.parseInt(exhibitIndex);
		IGroup group = Museum.getGroup(groupID);
		if(group != null) {
			IExhibit exhibit = group.getExhibit(index);
			return getDynamicXML("get_ext.xml", caller, exhibit.getTitle(), exhibit.getText());
		}
		// Unknown ID -> no action
		return "";
	}
	
	public String clientGetSelection(User caller, String exhibitIndex) throws MupeException {
		int index = Integer.parseInt(exhibitIndex);
		IGroup group = Museum.getGroup(groupID); // DB Query
		if(group != null) {
			int exhibitCount = group.getExhibitCount(); // DB Query
			int maxIndex = exhibitCount - 1;
			boolean isNotFirst = index > 0;
			boolean isNotLast = index < maxIndex;
			String number = Integer.toString(index + 1) + " / " + Integer.toString(exhibitCount);
			IExhibit exhibit = group.getExhibitPreview(index); // DB Query
			String exhibitTitle = exhibit.getTitle();
			String pictureURL = "mupe://" + exhibit.getPictureName(maxExhibitImageWidth, maxExhibitImageHeight);
			return getDynamicXML("get_sel.xml", caller, group.getTitle(), number, exhibitTitle, Boolean.toString(isNotFirst), Boolean.toString(isNotLast), exhibitIndex, pictureURL);
		}
		// Unknown ID -> no action
		return "";
	}
}
