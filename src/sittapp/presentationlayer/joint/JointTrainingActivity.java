package sittapp.presentationlayer.joint;

import java.util.ArrayList;

import sittapp.model.Gang;
import sittapp.model.JointTraining;
import sittapp.model.Lesson;
import sittapp.presentationlayer.R;
import sittapp.presentationlayer.R.layout;
import sittapp.presentationlayer.network.GangActivity;
import sittapp.presentationlayer.network.TrainingNetworkActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Nelich
 *
 */
public class JointTrainingActivity extends ListActivity  {
    ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    TextView dayName;
    TextView dayDate;
    JointTraining jT = new JointTraining();
    ContactAdapter cA;
    int whichDay = 5;
    private Context mContext = JointTrainingActivity.this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jtlayout);
        jT.testData();
        makeList();


    }


    public void makeList() {
        Log.i("RUN", "KJ�RER MAKELIST");
        this.lessons = this.jT.getF();
        this.dayName = (TextView)findViewById(R.id.dayView);
        this.dayDate = (TextView)findViewById(R.id.dateView);
        this.dayName.setText("Fredag");
        this.dayDate.setText(this.lessons.get(0).getDate());
        this.cA = new ContactAdapter(this, R.layout.list_item, this.lessons);
        setListAdapter(this.cA);
    }

    public void nextDay(View v) {
        Log.i("DAY", "HVILKENDAGERET?" + this.whichDay);
        if (this.whichDay == 7) {
            this.whichDay = 1;

        }
        else this.whichDay++;
        update(this.whichDay);


    }

    public void previousDay(View v ) {
        if(this.whichDay == 1) {
            this.whichDay = 7;
        }else this.whichDay--;
        update(this.whichDay);


    }

    public void update(int day) {
        int whichDay = day;
        switch (whichDay) {
        case 1: this.lessons = this.jT.getM();
        this.dayName.setText("Mandag");
        this.dayDate.setText(this.lessons.get(0).getDate());
        this.cA = new ContactAdapter(this, R.layout.list_item, this.lessons);
        setListAdapter(this.cA);
        cA.notifyDataSetChanged();
        break;
        case 2: this.lessons = this.jT.getTu();
        this.dayName.setText("Tirsdag");
        this.dayDate.setText(this.lessons.get(0).getDate());
        this.cA = new ContactAdapter(this, R.layout.list_item, this.lessons);
        setListAdapter(this.cA);
        cA.notifyDataSetChanged();
        break;
        case 3: this.lessons = this.jT.getW();
        this.dayName.setText("Onsdag");
        this.dayDate.setText(this.lessons.get(0).getDate());
        this.cA = new ContactAdapter(this, R.layout.list_item, this.lessons);
        setListAdapter(this.cA);
        cA.notifyDataSetChanged();
        break;
        case 4: this.lessons = this.jT.getTh();
        this.dayName.setText("Torsdag");
        this.dayDate.setText(this.lessons.get(0).getDate());
        this.cA = new ContactAdapter(this, R.layout.list_item, this.lessons);
        setListAdapter(this.cA);
        cA.notifyDataSetChanged();
        break;
        case 5: this.lessons = this.jT.getF();
        this.dayName.setText("Fredag");
        this.dayDate.setText(this.lessons.get(0).getDate());
        this.cA = new ContactAdapter(this, R.layout.list_item, this.lessons);
        setListAdapter(this.cA);
        cA.notifyDataSetChanged();
        break;
        case 6: this.lessons = this.jT.getSa();
        this.dayName.setText("L�rdag");
        this.dayDate.setText(this.lessons.get(0).getDate());
        this.cA = new ContactAdapter(this, R.layout.list_item, this.lessons);
        setListAdapter(this.cA);
        cA.notifyDataSetChanged();
        break;
        case 7: this.lessons = this.jT.getSu();
        this.dayName.setText("S�ndag");
        this.dayDate.setText(this.lessons.get(0).getDate());
        this.cA = new ContactAdapter(this, R.layout.list_item, this.lessons);
        setListAdapter(this.cA);
        cA.notifyDataSetChanged();
        break;
        }


    }


    @Override
    protected void onListItemClick(ListView lv, View v, int position, long id) {
        Log.i("TEST1235", "KOMEMR DETR");
        Lesson l = this.lessons.get(position);
        String info = l.getInfo();
        Dialog dialog = new Dialog(this.mContext);
        dialog.setContentView(R.layout.list_info);
        dialog.setTitle("Info");
        TextView tInfo = (TextView)dialog.findViewById(R.id.listinfo);
        tInfo.setText(info);
        dialog.show();
    }

    private class ContactAdapter extends ArrayAdapter<Lesson> {
        private ArrayList<Lesson> items;

        /**
         * 
         * @param context
         * @param textViewResourceId
         * @param items
         */
        public ContactAdapter(Context context, int textViewResourceId, ArrayList<Lesson> items) {
            super(context, textViewResourceId, items);
            this.items = items;

        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_item, null);
            }        

            final Lesson l = this.items.get(position);
            if (l != null) {
                TextView lessonName = (TextView) v.findViewById(R.id.lessonname);
                TextView lessonTime = (TextView) v.findViewById(R.id.lessonTime);
                final TextView spotsLeft = (TextView)v.findViewById(R.id.spotsLeft);
                final CheckBox joined = (CheckBox)v.findViewById(R.id.joinedTrue);
                joined.setChecked(l.getJoin());
                if (lessonName != null) {
                    lessonName.setText(l.getName()); 
                }
                if (lessonTime != null) {
                    lessonTime.setText(l.getTime()); 
                }

                if (spotsLeft != null) {
                    spotsLeft.setTextColor(R.color.green);
                    int spots = l.getFull();
                    if (spots < 1) {
                        spotsLeft.setTextColor(R.color.red);
                        spotsLeft.setText(""+spots);
                    }
                    else {
                        spotsLeft.setText("+"+spots);                        
                    }
                }
                if (joined != null) {
                    final int spots = l.getFull();
                    joined.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            AlertDialog.Builder alt_bld = new AlertDialog.Builder(JointTrainingActivity.this.mContext);
                            if (spots <=0) {
                                alt_bld.setMessage("Sett deg på venteliste for " + l.getName()+ " ?");
                            }
                            else {
                                alt_bld.setMessage("Meld deg på " + l.getName()+ " ?");
                            }

                            alt_bld.setCancelable(false)
                            .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    l.setJoin(true);
                                    if (spots <=0) {
                                        l.setFull(spots-1);
                                        spotsLeft.setText("" +l.getFull());
                                    }
                                    else {
                                        l.setFull(spots-1);
                                        spotsLeft.setText("+" +l.getFull());
                                    }
                                }
                            })
                            .setNegativeButton("Nei", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    joined.setChecked(false);
                                    dialog.cancel();
                                }
                            });

                            alt_bld.show();
                        }

                    });
                }

            }
            return v;

        }

    }
}
