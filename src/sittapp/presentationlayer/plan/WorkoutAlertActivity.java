/**
 * @author Andr� S. Hansen
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

public class WorkoutAlertActivity extends Activity {
	final static private String TAG = "WorkoutAlert";
	private Context context = WorkoutAlertActivity.this;
	private Workout workout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workout_alert);
		Intent i = getIntent();
		workout = (Workout) i.getSerializableExtra("workout");
	}
	
	/**
	 * Make sure that the workout object is returned if back button pressed
	 */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
        	returnWorkout();
        }
        return super.onKeyDown(keyCode, event);
    }
	
	//onClick methods
	public void alertSelectClick(View v) {
	   Button clickedButton = (Button) v;
	   if (clickedButton.getId() == R.id.alertSelectButton1) {
	   }
	   else if (clickedButton.getId() == R.id.alertSelectButton1) {
	   }
	   else if (clickedButton.getId() == R.id.alertSelectButton2) {
	   }
	   else if (clickedButton.getId() == R.id.alertSelectButton3) {
	   }
	   else if (clickedButton.getId() == R.id.alertSelectButton4) {
	   }
	   else if (clickedButton.getId() == R.id.alertSelectButton5) {
	   }
	   else if (clickedButton.getId() == R.id.alertSelectButton6) {
	   }
	   else if (clickedButton.getId() == R.id.alertSelectButton7) {
	   }
	   //TODO: Store the choice.
	   returnWorkout();
	   finish();
	}

    private void returnWorkout() {
		Log.d(TAG, workout.toString());
		Intent resultIntent = new Intent(context, WorkoutTypeActivity.class);
		resultIntent.putExtra("workout", workout);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();    	
    }
}
