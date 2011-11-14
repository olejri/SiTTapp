/**
 * @author Andr� S. Hansen
 */
package sittapp.presentationlayer.plan;

import java.util.ArrayList;
import sittapp.model.*;
import sittapp.presentationlayer.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TrainingBookActivity extends ListActivity {
	private Context context = TrainingBookActivity.this;
	private Plan plan;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plan);
		plan = new Plan();
		generateWorkouts(plan.workouts);
	}

	/**
	 * A Plan have been returned from a child activity
	 */
	@Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) {     
	  super.onActivityResult(requestCode, resultCode, data); 
	  if (resultCode!=Activity.RESULT_CANCELED) {
		  plan = (Plan) data.getSerializableExtra("plan");
		  generateWorkouts(plan.workouts);
		  TextView activityTitle = (TextView) findViewById(R.id.plan_title);
		  activityTitle.setText("Ukeplan ("+plan.name+")");
	  }
	}

	//onClick methods
	public void useStandPlanClick(View v) {
		Intent myIntent = new Intent(v.getContext(), StandardizedPlanActivity.class);
		startActivityForResult(myIntent, 0);        
	}
	
	public void removeWorkouts(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Fjern alt fra planen?");

		builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				generateWorkouts(new ArrayList<Workout>());
				dialog.cancel();
			}
		});
		builder.setNegativeButton("Nei STOP!", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		builder.create();
		builder.show();	
	}
	
	public void addWorkoutClick(View v) {
		Intent i = new Intent(v.getContext(), AddWorkoutActivity.class);
		i.putExtra("plan", plan);
		startActivityForResult(i,0);   
	}

	private void generateWorkouts(ArrayList<Workout> workouts) {
		CustomPlanAdapter adapter = new CustomPlanAdapter(this, workouts);
		setListAdapter(adapter);
	}

	/* ListAdapter Widget for Custom Workout activity */

	private class CustomPlanAdapter extends ArrayAdapter<Workout> {
		private final Activity context;
		private final ArrayList<Workout> workouts;

		public CustomPlanAdapter(Activity context, ArrayList<Workout> workouts) {
			super(context, R.layout.workout_rowlayout, workouts);
			this.context = context;
			this.workouts = workouts;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View rowView = inflater.inflate(R.layout.workout_rowlayout, null, true);
			View textView = rowView.findViewById(R.id.workrow_textlayout);
			View topView = textView.findViewById(R.id.workrow_toptexts);
			// Set row data
			Workout w = workouts.get(position);
			// Icon
			ImageView iconView = (ImageView) rowView.findViewById(R.id.workrow_icon);
			if (w.type.equals("STRENGTH")) iconView.setImageResource(R.drawable.icon_dumbbell);
			else if (w.type.equals("CARDIO")) iconView.setImageResource(R.drawable.icon_cardio);
			else if (w.type.equals("JOINT")) iconView.setImageResource(R.drawable.icon_joint);
			iconView.setAdjustViewBounds(true);
			// When Text
			TextView daysText = (TextView) topView.findViewById(R.id.workrow_days);
			daysText.setText(w.repeatToString());
			// Clock Text 
			TextView clockText = (TextView) topView.findViewById(R.id.workrow_clock);
			clockText.setText(w.clock);
			// What Text
			TextView whatText = (TextView) textView.findViewById(R.id.workrow_what);
			whatText.setText(w.selectedItemsString());
			return rowView;
		}
	}
}
