/**
 * @author Andr� S. Hansen
 *
 */
package sittapp.presentationlayer.plan;

import java.util.ArrayList;
import sittapp.presentationlayer.R;
import sittapp.model.*;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StandardizedPlanActivity extends ListActivity {
    Context context;
    ArrayList<Plan> plans;
	@Override
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plan_stand_main);
		context = this;
		generatePlans();
	}
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
    		Intent resultIntent = new Intent(context, StandardizedPlanActivity.class);
    		setResult(Activity.RESULT_CANCELED, resultIntent);
    		finish();
        }
        return super.onKeyDown(keyCode, event);
    }
	
	@Override
    protected void onListItemClick(ListView lv, View v, int position, long id) {
        Toast.makeText(context, "Du har valgt: " +plans.get(position).name, Toast.LENGTH_SHORT).show();
		Intent resultIntent = new Intent(context, StandardizedPlanActivity.class);
		resultIntent.putExtra("plan", plans.get(position));
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
    }

	public void generatePlans() {
		plans = new ArrayList<Plan>();
		for(int i=0;i < 10;i++) {
			plans.add(new Plan());
		}
		StandardPlanAdapter adapter = new StandardPlanAdapter(this, plans);
		setListAdapter(adapter);
	}

	/* ListAdapter Widget for Standardized plan activity */

	private class StandardPlanAdapter extends ArrayAdapter<Plan> {
		private final Activity context;
		private final ArrayList<Plan> plans;
		

		public StandardPlanAdapter(Activity context, ArrayList<Plan> plans) {
			super(context, R.layout.plan_stand_rowlayout, plans);
			this.context = context;
			this.plans = plans;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Load layouts/views
			LayoutInflater inflater = context.getLayoutInflater();
			View rowView = inflater.inflate(R.layout.plan_stand_rowlayout, null, true);
			View topView = rowView.findViewById(R.id.planrow_top);
			View leftView = rowView.findViewById(R.id.planrow_bot).findViewById(R.id.planrow_left);
			View rightView = rowView.findViewById(R.id.planrow_bot).findViewById(R.id.planrow_right);
			// Append Plan data
			Plan p = plans.get(position);
			TextView likeCount = (TextView) rightView.findViewById(R.id.planrow_likeCount);
			likeCount.setText(""+p.likes);
			TextView name = (TextView) topView.findViewById(R.id.planrow_name);
			name.setText(p.name);
			for (Workout w : plans.get(position).workouts) {
				LinearLayout iconLayout;
				ImageView image = new ImageView(context);
				if (w.type.equals("STRENGTH")) {
					iconLayout = (LinearLayout) leftView.findViewById(R.id.planrow_bumbbell_layout);
					image.setImageResource(R.drawable.icon_dumbbell);
				}
				else if (w.type.equals("CARDIO")) {
					iconLayout = (LinearLayout) leftView.findViewById(R.id.planrow_cardio_layout);
					image.setImageResource(R.drawable.icon_cardio);
				}
				else {
					iconLayout = (LinearLayout) leftView.findViewById(R.id.planrow_joint_layout);
					image.setImageResource(R.drawable.icon_joint);
				}
				image.setAdjustViewBounds(true);
				iconLayout.addView(image);
			}
			return rowView;
		}
	}
}
