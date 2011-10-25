package sittapp.model;

import java.util.ArrayList;

public class User {
	// VARIABLES
    private String name;
	private ArrayList<Gang> gangs = new ArrayList<Gang>();
	private ArrayList<Gang> gangInvites = new ArrayList<Gang>();
	
	// CONSTRUCTOR
	public User(String name) {
		this.name = name;
	}
	
	// GETTERS AND SETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Gang> getGangs() {
		return gangs;
	}

	public ArrayList<Gang> getGangInvites() {
		return gangInvites;
	}
	
	// OTHER METHODS
	
	public void addGang(Gang g) {
		gangs.add(g);
	}
	
	public void addGangInvite(Gang g) {
		gangInvites.add(g);
	}
	
	public void removeGangInvite(Gang g) {
		gangInvites.remove(g);
	}
	
	public String toString() {
		String str = name+" - Gangs:";
		for (Gang g : gangs) {
			str += " "+g.getName();
		}
		str += " - Invites:";
		for (Gang g : gangInvites) {
			str += " "+g.getName();
		}
		return str;
	}
}