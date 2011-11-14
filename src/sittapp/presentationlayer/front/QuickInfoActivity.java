package sittapp.presentationlayer.front;

import java.util.ArrayList;

import sittapp.model.Exercise;

import sittapp.model.Quickworkout;
import sittapp.presentationlayer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.TextView;

/**
 * @author Nelich
 *
 */
public class QuickInfoActivity extends ListActivity {
    Chronometer meter;
    ExerciseAdapter eA;

    private Context mContext = QuickInfoActivity.this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qlayout);
        TextView text = (TextView) findViewById(R.id.testview);
        makeList();
        meter = (Chronometer)findViewById(R.id.chronometer1);
        meter.setOnChronometerTickListener(new OnChronometerTickListener() {

            public void onChronometerTick(Chronometer chronometer) {


            }

        });




    }


    public void onStart(View v) {
        meter.start();
    }

    public void makeList() {
        Quickworkout out = new Quickworkout();
        ListView lv = (ListView)findViewById(android.R.id.list);
        ArrayList <Exercise> exe= out.getDemo();
        this.eA = new ExerciseAdapter(this, R.layout.list_contact, exe);
        setListAdapter(this.eA);
    }

    private class ExerciseAdapter extends ArrayAdapter<Exercise> {
        private ArrayList<Exercise> items;
        int teller = 0;
        /**
         * 
         * @param context
         * @param textViewResourceId
         * @param items
         */
        public ExerciseAdapter(Context context, int textViewResourceId, ArrayList<Exercise> items) {
            super(context, textViewResourceId, items);
            this.items = items;

        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_exercise, null);
            }        

            final Exercise exe = this.items.get(position);
            if (exe != null) {
                TextView exeName = (TextView) v.findViewById(R.id.exercisename);
                if (exeName != null) {
                    exeName.setText(exe.getName()); 
                }
                final TextView antKg = (TextView) v.findViewById(R.id.antKg);
                if (antKg != null) {
                    antKg.setText(""+exe.getKg()); 
                }
                final TextView antRep = (TextView) v.findViewById(R.id.antRep);
                if (antRep != null) {
                    antRep.setText(""+exe.getRep()); 
                }
                final TextView antSet = (TextView) v.findViewById(R.id.antSet);
                if (antSet != null) {
                    antSet.setText(""+exe.getSet()); 
                }
                TextView time = (TextView) v.findViewById(R.id.exerciseTime);
                if (time != null) {
                    time.setText(exe.getTime()); 
                }


                final CheckBox done = (CheckBox)v.findViewById(R.id.exercisedone);
                done.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                       if (done.isChecked()) {
                           Toast.makeText(mContext, "Ferdig med øvelse: " + exe.getName(), Toast.LENGTH_SHORT).show();
                       }
                        
                    }
                    
                });

                Button plussKg = (Button) v.findViewById(R.id.pluss);
                if (plussKg != null) {
                    plussKg.setOnClickListener(new OnClickListener(){
                        public void onClick(View arg0) {
                            int her = exe.getKg();
                            her++;
                            exe.setKg(her);
                            antKg.setText("" +her);
                        }
                    });
                }
                Button minusKg = (Button) v.findViewById(R.id.minus);
                if (minusKg != null) {
                    minusKg.setOnClickListener(new OnClickListener(){
                        public void onClick(View arg0) {
                            int her = exe.getKg();
                            her--;
                            exe.setKg(her);
                            antKg.setText("" + her);
                        }
                    });
                }

                Button plussRep = (Button) v.findViewById(R.id.repPluss);
                if (plussRep != null) {
                    plussRep.setOnClickListener(new OnClickListener(){
                        public void onClick(View arg0) {
                            int her = exe.getRep();
                            her++;
                            exe.setRep(her);
                            antRep.setText(""+ her);

                        }
                    });
                }
                Button minusRep = (Button) v.findViewById(R.id.repMinus);
                if (minusRep != null) {
                    minusRep.setOnClickListener(new OnClickListener(){
                        public void onClick(View arg0) {
                            int her = exe.getRep();
                            her--;
                            exe.setRep(her);
                            antRep.setText(""+ her);

                        }
                    });
                }

                Button plussSet = (Button) v.findViewById(R.id.setPluss);
                if (plussSet != null) {
                    plussSet.setOnClickListener(new OnClickListener(){
                        public void onClick(View arg0) {
                            int her = exe.getSet();
                            her++;
                            exe.setSet(her);
                            antSet.setText("" + her);
                        }
                    });
                }
                Button minusSet = (Button) v.findViewById(R.id.setMinus);
                if (minusSet != null) {
                    minusSet.setOnClickListener(new OnClickListener(){
                        public void onClick(View arg0) {
                            int her = exe.getSet();
                            her--;
                            exe.setSet(her);                            
                            antSet.setText("" +her);

                        }
                    });
                }
            }
            return v;

        }

    }


}
