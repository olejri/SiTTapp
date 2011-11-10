/**
 * @author André S. Hansen
 *
 */
package sittapp.presentationlayer.plan;

import java.util.ArrayList;
import sittapp.presentationlayer.R;
import sittapp.model.*;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StandardizedPlanActivity extends ListActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plan_stand_main);
		generatePlans();
	}

	public void generatePlans() {
		ArrayList<Plan> plans = new ArrayList<Plan>();
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
			Log.d("PLANSsize", ""+plans.size());
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
			Log.d("CHILDCOUNT",""+((LinearLayout) rightView).getChildCount());
			TextView likeCount = (TextView) rightView.findViewById(R.id.planrow_likeCount);
			likeCount.setText(""+p.likes);
			TextView name = (TextView) topView.findViewById(R.id.planrow_name);
			Log.d("NAMESHIZ", p.name);
			name.setText(p.name);
			for (String tag : plans.get(position).tags) {
				LinearLayout iconLayout;
				ImageView image = new ImageView(context);
				if (tag.equals("DUMB")) {
					iconLayout = (LinearLayout) leftView.findViewById(R.id.planrow_bumbbell_layout);
					image.setImageResource(R.drawable.icon_dumbbell);
				}
				else if (tag.equals("CARDIO")) {
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
