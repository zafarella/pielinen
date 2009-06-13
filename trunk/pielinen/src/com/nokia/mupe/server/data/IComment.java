package com.nokia.mupe.server.data;

public interface IComment {
	// A constructor (you cannot specify constructors in interfaces,
	// but it should look like this):
	// public IComment(Date timeStamp, String userName, String text);
	// Important:
	public String getUserName();
	public String getText();
	// public Date getTimeStamp(); // Optional
}
