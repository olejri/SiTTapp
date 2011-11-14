package sittapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Plan implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public int likes = 0;
	//public ArrayList<String> tags = new ArrayList<String>();
	public ArrayList<Workout> workouts = new ArrayList<Workout>();
	public String name;

	public Plan() {
		// Creates some random data
		Random gen = new Random();
		this.likes = gen.nextInt(100);
		String[] names = {
				"Rock your world", 
				"Whoha!", 
				"Gjør susen", 
				"5star king", 
				"Owyea!", 
				"Passer deg garr",
				"SiT 2012",
				"SiT 2033",
				"Juleøktene",
				"Sommer",
				"Kanskje for deg?"
		};
		this.name = names[gen.nextInt(names.length)];
		String[] types = {"STRENGTH", "CARDIO", "JOINT"};
		int workoutCount = gen.nextInt(5)+2;
		for (int i=0; i<workoutCount; i++) {
			String type = types[gen.nextInt(types.length)];
			Workout w = new Workout();
			w.randomize(type);
			workouts.add(w);
		}
	}
}
