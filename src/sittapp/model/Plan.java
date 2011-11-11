package sittapp.model;

import java.util.ArrayList;
import java.util.Random;

public class Plan {
	public int likes = 0;
	public ArrayList<String> tags = new ArrayList<String>();
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
		String[] types = {"DUMB", "CARDIO", "JOINT"};
		int workoutCount = gen.nextInt(5)+2;
		for (int i=0; i<workoutCount; i++) {
			tags.add(types[gen.nextInt(types.length)]);
		}
	}
}
