package sittapp.model;

import java.io.Serializable;

public class WorkoutRepeat implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String day;
	public boolean on;
	
	public WorkoutRepeat(String day) {
		this.day = day;
		this.on = false;
	}
}
