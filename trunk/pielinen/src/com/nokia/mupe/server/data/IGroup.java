package com.nokia.mupe.server.data;

import java.util.List;

public interface IGroup {
	public String getID();
	public String getTitle();
	public String getText();
	public IExhibit getExhibit(int index);
	public IExhibit getExhibitPreview(int index);
	public int getExhibitCount();
	public List<IExhibit> getExhibitList();
}
