/**
 * @author André S. Hansen
 *
 */
package sittapp.presentationlayer.plan;

import sittapp.presentationlayer.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class WorkoutRepeatActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workout_repeat);
	}
	
	public void okClick(View v) {
		// TODO: Add logic to fetch which checkboxes are saved
		finish();
	}
}
