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
			Log.d("PLANSsizeINSIDE", ""+plans.size());
			LayoutInflater inflater = context.getLayoutInflater();
			View rowView = inflater.inflate(R.layout.plan_stand_rowlayout, null, true);
			View leftIcons = rowView.findViewById(R.id.leftIconLayout);
			View rightIcons = rowView.findViewById(R.id.rightIconLayout);
			// Append Plan data
			TextView likeCount = (TextView) rightIcons.findViewById(R.id.likeCountLabel);
			likeCount.setText(""+plans.get(position).likes);
			LinearLayout left = (LinearLayout) leftIcons;
			for (String tag : plans.get(position).tags) {
				try { // TAG is a workout count
					Integer.parseInt(tag);
					TextView tv = new TextView(context);
					tv.setText(tag);
					left.addView(tv);
				}
				catch (NumberFormatException e) { // TAG is a icon name
					ImageView image = new ImageView(context);
					image.setImageResource(R.drawable.icon);
					left.addView(image);
				}
			}
			return rowView;
		}
	}
}
