package com.nokia.mupe.server;

public class ItemsList extends Room {

	private static final long serialVersionUID = 1L;

	public ItemsList(User caller, Base location, String name)
			throws MupeException {
		super(caller, location, name);
		// TODO Auto-generated constructor stub
	}
	
	public String clientGetList(User caller, String name) {
		String msg = "";
		if(name.equals("Kitchen")) {
			msg += "<clear group='objects' />";
			msg += "<settext id='title' text='Kitchen' />";
			msg += "<additem group='objects' name='Stove' text='Stove' />";
			msg += "<additem group='objects' name='MaidsBed' text=\"Maid's bed\" />";
			return msg;
		}
		else if(name.equals("Bedroom")) {
			msg += "<clear group='objects' />";
			msg += "<settext id='title' text='Bedroom' />";
			msg += "<additem group='objects' name='Bed' text='Bed' />";
			msg += "<additem group='objects' name='Wardrobe' text='Wardrobe' />";
			return msg;	
		}
		System.out.println(name);
		return ""; 
	}
	
	public String clientGetObject(User caller, String name) {
		String msg = "";
		msg += "<clear group='objects' />";
		msg += "<settext id='title' text='Information about " + name + "' />";
		return msg;
	}

}
