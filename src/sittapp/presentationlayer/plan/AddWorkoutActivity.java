/** 
 * Activity to add a workout to the Custom Plan.
 * @author Andr� S. Hansen
 *
 */

package sittapp.presentationlayer.plan;

import sittapp.model.*;
import sittapp.presentationlayer.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddWorkoutActivity extends Activity {
	final static private String TAG = "AddWorkout";
	private Workout workout;
	private Context context = AddWorkoutActivity.this;
	private Plan plan;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workout_add_custom);
		Intent i = getIntent();
		plan = (Plan) i.getSerializableExtra("plan");
		workout = new Workout();
		Log.d(TAG, "11"+workout.toString());
		findViewById(R.id.addWorkout_workrow).setVisibility(View.GONE);
		refreshOkButton();
	}

	@Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d(TAG, "codes: "+resultCode+","+requestCode);
		super.onActivityResult(requestCode, resultCode, data); 
		if (resultCode!=Activity.RESULT_CANCELED) {
			workout = (Workout) data.getSerializableExtra("workout");
			// Update context
			View rowView = findViewById(R.id.addWorkout_workrow);
			rowView.setVisibility(View.VISIBLE);
			View textView = rowView.findViewById(R.id.workrow_textlayout);
			View topView = textView.findViewById(R.id.workrow_toptexts);
			// Icon
			ImageView iconView = (ImageView) rowView.findViewById(R.id.workrow_icon);
			if (workout.type.equals("STRENGTH")) iconView.setImageResource(R.drawable.icon_dumbbell);
			else if (workout.type.equals("CARDIO")) iconView.setImageResource(R.drawable.icon_cardio);
			else if (workout.type.equals("JOINT")) iconView.setImageResource(R.drawable.icon_joint);
			iconView.setAdjustViewBounds(true);
			// When Text
			TextView daysText = (TextView) topView.findViewById(R.id.workrow_days);
			daysText.setText(workout.repeatToString());
			// Clock Text 
			TextView clockText = (TextView) topView.findViewById(R.id.workrow_clock);
			clockText.setText(workout.clock);
			// What Text
			TextView whatText = (TextView) textView.findViewById(R.id.workrow_what);
			whatText.setText(workout.selectedItemsString());
			Button b;
			if (requestCode==1) b = (Button) findViewById(R.id.workoutTypeButton);
			else if (requestCode==2) b = (Button) findViewById(R.id.workoutRepeatButton);
			else b = (Button) findViewById(R.id.workoutAlertButton);
			Drawable img = context.getResources().getDrawable(R.drawable.icon_checked);
			img.setBounds( 0, 0, 60, 60 );
			b.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null );
		}
		refreshOkButton();
	}


	//onClick methods
	public void workoutTypeClick(View v) {
		Intent i = new Intent(v.getContext(), WorkoutTypeActivity.class);
		i.putExtra("workout", workout);
		startActivityForResult(i,1);
	}

	public void workoutRepeatClick(View v) {
		Intent i = new Intent(v.getContext(), WorkoutRepeatActivity.class);
		i.putExtra("workout", workout);
		startActivityForResult(i,2);
	}

	public void workoutAlertClick(View v) {
		Intent i = new Intent(v.getContext(), WorkoutAlertActivity.class);
		i.putExtra("workout", workout);
		startActivityForResult(i,3);
	}

	public void addWorkoutClick(View v) {
		Intent resultIntent = new Intent(context, AddWorkoutActivity.class);
		plan.workouts.add(workout);
		resultIntent.putExtra("plan", plan);
		setResult(Activity.RESULT_OK, resultIntent);
		Toast.makeText(context, "Ukeplan oppdatert", Toast.LENGTH_SHORT).show();
		finish();
	}
	
	private void refreshOkButton() {
		Button b = (Button) findViewById(R.id.workoutAddButton);
		if (workout.isValid()) b.setVisibility(View.VISIBLE);
		else b.setVisibility(View.GONE);
	}
}