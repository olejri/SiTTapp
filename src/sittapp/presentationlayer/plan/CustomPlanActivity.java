/**
 * @author André S. Hansen
 *
 */
package sittapp.presentationlayer.plan;


import java.util.ArrayList;
import sittapp.presentationlayer.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomPlanActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workout_custom_main);
		generateWorkouts();
	}
	
    //onClick methods
    public void addWorkoutClick(View v) {
        Intent myIntent = new Intent(v.getContext(), AddWorkoutActivity.class);
        startActivity(myIntent);   
    } 
    
	public void generateWorkouts() {
		ArrayList<String> repeatStrings = new ArrayList<String>();
		for(int i=0;i < 10;i++) {
			repeatStrings.add("Hver mandag");
		}
		CustomPlanAdapter adapter = new CustomPlanAdapter(this, repeatStrings);
		setListAdapter(adapter);
	}
	
	
	
	/* ListAdapter Widget for Custom Workout activity */
	
	private class CustomPlanAdapter extends ArrayAdapter<String> {
		private final Activity context;
		private final ArrayList<String> repeat;

		public CustomPlanAdapter(Activity context, ArrayList<String> repeat) {
			super(context, R.layout.workout_rowlayout, repeat);
			this.context = context;
			this.repeat = repeat;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View rowView = inflater.inflate(R.layout.workout_rowlayout, null, true);
			View rowLabels = rowView.findViewById(R.id.workoutLabelsLayout);
			ImageView iconView = (ImageView) rowView.findViewById(R.id.icon);
			TextView repeatView = (TextView) rowLabels.findViewById(R.id.repeatLabel);
			TextView clockView = (TextView) rowLabels.findViewById(R.id.clockLabel);
			repeatView.setText(repeat.get(position));
			clockView.setText("7:00");
			iconView.setImageResource(R.drawable.icon);
			return rowView;
		}
	}
}