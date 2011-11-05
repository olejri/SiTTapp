/** 
 * Activity to add a workout to the Custom Plan.
 * @author André S. Hansen
 *
 */

package sittapp.presentationlayer.plan;

import sittapp.presentationlayer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddWorkoutActivity extends Activity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workout_add_custom);
	}
	
    //onClick methods
    public void workoutTypeClick(View v) {
        Intent myIntent = new Intent(v.getContext(), WorkoutTypeActivity.class);
        startActivity(myIntent);   
    }
    
    public void workoutRepeatClick(View v) {
        Intent myIntent = new Intent(v.getContext(), WorkoutRepeatActivity.class);
        startActivity(myIntent);   
    }
    
    public void workoutAlertClick(View v) {
        Intent myIntent = new Intent(v.getContext(), WorkoutAlertActivity.class);
        startActivity(myIntent);   
    }
    
    public void addWorkoutClick(View v) {
    	finish();
    }

}
