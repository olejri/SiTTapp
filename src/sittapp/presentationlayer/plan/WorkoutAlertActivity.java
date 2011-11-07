/**
 * @author André S. Hansen
 *
 */
package sittapp.presentationlayer.plan;

import sittapp.presentationlayer.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorkoutAlertActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workout_alert);
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
	   finish();
	}
}
