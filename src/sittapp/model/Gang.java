package sittapp.model;

import java.util.ArrayList;

public class Gang {
	// VARIABLES
	private Long id;
    private String name;
    private ArrayList<GangMember> members = new ArrayList<GangMember>();
    
    // CONSTRUCTOR
    public Gang(Long id, String name) {
    	this.id = id;
    	this.name = name;
    }
    
	// GETTERS AND SETTERS
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<GangMember> getMembers() {
		return members;
	}
    
    // OTHER METHODS
	
	public void addMember(GangMember m) {
		members.add(m);
	}
	
	public String toString() {
		String str = name +"("+ id +") - users:";
		for (GangMember u : members) {
			str += " "+u.getName();
		}
		return str;
	}
}