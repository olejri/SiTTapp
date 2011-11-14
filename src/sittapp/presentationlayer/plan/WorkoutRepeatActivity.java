/**
 * @author Andrï¿½ S. Hansen
 *
 */
package sittapp.presentationlayer.plan;

import sittapp.model.Workout;
import sittapp.presentationlayer.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class WorkoutRepeatActivity extends Activity {
	final static private String TAG = "WorkoutAlert";
	private Context context = WorkoutRepeatActivity.this;
	private Workout workout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workout_repeat);
		Intent i = getIntent();
		workout = (Workout) i.getSerializableExtra("workout");
	}
	
	/**
	 * Make sure that the workout object is returned if back button pressed
	 */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
        	returnWorkout(Activity.RESULT_CANCELED);
        }
        return super.onKeyDown(keyCode, event);
    }
	
	public void okClick(View v) {
	    returnWorkout(Activity.RESULT_OK);
	}
	
	public void checkedClick(View v) {
		CheckBox cb = (CheckBox) v;
		if (cb.getId() == R.id.repeatCheckbox1) {workout.repeatClick("MAN");}
		else if (cb.getId() == R.id.repeatCheckbox2) {workout.repeatClick("TIR");}
		else if (cb.getId() == R.id.repeatCheckbox3) {workout.repeatClick("ONS");}
		else if (cb.getId() == R.id.repeatCheckbox4) {workout.repeatClick("TOR");}
		else if (cb.getId() == R.id.repeatCheckbox5) {workout.repeatClick("FRE");}
		else if (cb.getId() == R.id.repeatCheckbox6) {workout.repeatClick("LØR");}
		else if (cb.getId() == R.id.repeatCheckbox7) {workout.repeatClick("SØN");}
	}
	
    private void returnWorkout(int resultCode) {
		Intent resultIntent = new Intent(context, WorkoutTypeActivity.class);
		resultIntent.putExtra("workout", workout);
		setResult(resultCode, resultIntent);
		finish();
    }
}
