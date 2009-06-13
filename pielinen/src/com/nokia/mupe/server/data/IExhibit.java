package com.nokia.mupe.server.data;

import java.util.List;

public interface IExhibit {
	public int getID();
	public String getTitle();
	public String getText();
	public String getPictureName();
	public String getPictureName(int width, int height);
}
