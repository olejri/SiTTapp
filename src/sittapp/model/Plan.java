package sittapp.model;

import java.util.ArrayList;
import java.util.Random;

public class Plan {
	public int likes = 0;
	public ArrayList<String> tags = new ArrayList<String>();

	public Plan() {
		// Creates some random data
		Random gen = new Random();
		this.likes = gen.nextInt(100);
		String[] types = {"DUMB", "CARDIO", "JOINT"};
		for (int i=0; i<3; i++) {
			if (gen.nextBoolean()) {
				int count = gen.nextInt(4)+1;
				if (count>1) {
					tags.add(""+count);
				}
				tags.add(types[i]);
			}
		}
	}
}
