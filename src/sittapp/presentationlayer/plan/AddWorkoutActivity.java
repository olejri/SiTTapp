/** 
 * Activity to add a workout to the Custom Plan.
 * @author André S. Hansen
 *
 */

package sittapp.presentationlayer.plan;

import sittapp.model.Workout;
import sittapp.presentationlayer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AddWorkoutActivity extends Activity {
	final static private String TAG = "AddWorkout";
	private Workout workout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workout_add_custom);
		workout = new Workout();
		Log.d(TAG, "11"+workout.toString());
	}
	
	@Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) {     
	  super.onActivityResult(requestCode, resultCode, data); 
	  workout = (Workout) data.getSerializableExtra("workout");
	  Log.d(TAG, "22"+workout.toString());
	  TextView desc = (TextView) this.findViewById(R.id.workout_cust_desc);
	  desc.setText(workout.toString());
	  
	}

	
    //onClick methods
    public void workoutTypeClick(View v) {
        Intent i = new Intent(v.getContext(), WorkoutTypeActivity.class);
        i.putExtra("workout", workout);
        startActivityForResult(i,0);   
    }
    
    public void workoutRepeatClick(View v) {
        Intent i = new Intent(v.getContext(), WorkoutRepeatActivity.class);
        i.putExtra("workout", workout);
        startActivityForResult(i,0);   
    }
    
    public void workoutAlertClick(View v) {
        Intent i = new Intent(v.getContext(), WorkoutAlertActivity.class);
        i.putExtra("workout", workout);
        startActivityForResult(i,0);  
    }
    
    public void addWorkoutClick(View v) {
    	finish();
    }

}
