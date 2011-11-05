/**
 * 
 */
package sittapp.presentationlayer.plan;

import sittapp.presentationlayer.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * @author André S. Hansen
 *
 */
public class WorkoutTypeActivity extends Activity {
	final static private String TAG = "WorkoutTYPE";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workout_type);
	}
	
    //onClick methods
    public void weightWorkoutClick(View v) {
    	Log.d(TAG, "weight click");
    	CharSequence[] items = {"Benk", "Triceps", "Biceps"};
    	createListDialog(items);
    }
    
    public void cardioWorkoutClick(View v) {
    	Log.d(TAG, "cardio click");
    	CharSequence[] items = {"Sykkel", "Tredemølle"};
    	createListDialog(items);    	
    }
    
    public void jointWorkoutClick(View v) {
    	// TODO Add a Joint Workout selection
    }
    
    private void createListDialog(CharSequence[] items) {
    	boolean[] list = {false, false, false};
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Treningsinnhold");
    	builder.setMultiChoiceItems(items, list, new DialogInterface.OnMultiChoiceClickListener() {
    		@Override
    		public void onClick(DialogInterface dialog, int which, boolean isChecked) {
    			// TODO Update a reference table what are selected
    		}
    	});
    	builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int id) {
    			dialog.cancel();
    			finish();
    		}
    	});
    	Log.d(TAG, "About to create...");
    	builder.create();
    	builder.show();
    }
}
