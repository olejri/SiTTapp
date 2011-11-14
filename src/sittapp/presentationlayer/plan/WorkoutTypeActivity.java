/**
 * @author André S. Hansen
 *
 */
package sittapp.presentationlayer.plan;

import sittapp.model.Workout;
import sittapp.presentationlayer.R;
import sittapp.presentationlayer.network.GangMemberActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class WorkoutTypeActivity extends Activity {
	final static private String TAG = "WorkoutTYPE";
	private Context context = WorkoutTypeActivity.this;
	private Workout workout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workout_type);
		Intent i = getIntent();
		workout = (Workout) i.getSerializableExtra("workout");
		Log.d(TAG, workout.toString());
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
    public void strengthWorkoutClick(View v) {
    	workout.type = "STRENGTH";
    	createListDialog(workout.strengthItems, workout.strengthSelected);
    }
    
    public void cardioWorkoutClick(View v) {
    	workout.type = "CARDIO";
    	createListDialog(workout.cardioItems, workout.cardioSelected);
    }
    
    public void jointWorkoutClick(View v) {
    	// TODO Add a Joint Workout selection
    }
    
    private void createListDialog(CharSequence[] items, boolean[] selected) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Treningsinnhold");
    	builder.setMultiChoiceItems(items, selected, new DialogInterface.OnMultiChoiceClickListener() {
    		public void onClick(DialogInterface dialog, int which, boolean isChecked) {
    			if (workout.type.equals("STRENGTH")) { 
    				workout.strengthSelected[which] = isChecked;
    			}
    			else if (workout.type.equals("CARDIO")) { 
    				workout.cardioSelected[which] = isChecked;
    			}
    		}
    	});
    	builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int id) {
    			dialog.cancel();
    			returnWorkout(Activity.RESULT_OK);
    		}
    	});
    	builder.create();
    	builder.show();
    }
    
    private void returnWorkout(int resultCode) {
		Intent resultIntent = new Intent(context, WorkoutTypeActivity.class);
		resultIntent.putExtra("workout", workout);
		setResult(resultCode, resultIntent);
		finish();
    }
}
