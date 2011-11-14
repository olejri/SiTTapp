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
        	returnWorkout(Activity.RESULT_CANCELED);
        }
        return super.onKeyDown(keyCode, event);
    }
	
	//onClick methods
	public void alertSelectClick(View v) {
	   Button clickedButton = (Button) v;
	   if (clickedButton.getId() == R.id.alertSelectButton1) {workout.alert = "5 min før";}
	   else if (clickedButton.getId() == R.id.alertSelectButton2) {workout.alert = "15 min før";}
	   else if (clickedButton.getId() == R.id.alertSelectButton3) {workout.alert = "30 min før";}
	   else if (clickedButton.getId() == R.id.alertSelectButton4) {workout.alert = "1 time før";}
	   else if (clickedButton.getId() == R.id.alertSelectButton5) {workout.alert = "2 timer før";}
	   else if (clickedButton.getId() == R.id.alertSelectButton6) {workout.alert = "1 dag før";}
	   else if (clickedButton.getId() == R.id.alertSelectButton7) {workout.alert = "2 dager før";}
	   returnWorkout(Activity.RESULT_OK);
	   finish();
	}

    private void returnWorkout(int resultCode) {
		Intent resultIntent = new Intent(context, WorkoutTypeActivity.class);
		resultIntent.putExtra("workout", workout);
		setResult(resultCode, resultIntent);
		finish();
    }
}
