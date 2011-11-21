package sittapp.presentationlayer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import sittapp.com.HttpCom;
import sittapp.presentationlayer.front.QuickInfoActivity;
import sittapp.presentationlayer.info.InfoActivity;
import sittapp.presentationlayer.joint.JointTrainingActivity;
import sittapp.presentationlayer.log.LogActivity;
import sittapp.presentationlayer.network.TrainingNetworkActivity;
import sittapp.presentationlayer.plan.TrainingBookActivity;
import sittapp.model.*;

public class MainActivity extends Activity {
    HttpCom com = new HttpCom();
    TextView date;
    TextView people;
    TextView eName;
    Button index1;
    Button index2;
    Button index3;
    Button index4;
    Button index5;
    ArrayList<String> dates = new ArrayList<String>();


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Translucent);
        setContentView(R.layout.main);
        makeDates();
        Gallery gallery = (Gallery) findViewById(R.id.window);
        gallery.setAdapter(new ImageAdapter(this));
        date = (TextView)findViewById(R.id.textView3);
        people =(TextView)findViewById(R.id.people);
        eName = (TextView)findViewById(R.id.textView2);
        index1 = (Button)findViewById(R.id.index1);
        index2 = (Button)findViewById(R.id.index2);
        index3 = (Button)findViewById(R.id.index3);
        index4 = (Button)findViewById(R.id.index4);
        index5 = (Button)findViewById(R.id.index5);
        ranStatus();
        gallery.setCallbackDuringFling(true);
        gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                date.setText(dates.get(arg2));
                eName.setText(dates.get(arg2+5));
                index(arg2);


            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        //        Calendar cal = Calendar.getInstance();
        //        cal.set(Calendar.YEAR, 2011);
        //        cal.set(Calendar.DAY_OF_YEAR, 312);
        //        int weekday = cal.get(Calendar.DAY_OF_WEEK);
        //        switch (weekday) {
        //        case 1 : date.setText("S�ndag");break;
        //        case 2: date.setText("Mandag");break;
        //        case 3: date.setText("Tirsdag");break;
        //        case 4: date.setText("Onsdag");break;
        //        case 5: date.setText("Torsdag");break;
        //        case 6: date.setText("Fredag");break;
        //        case 7: date.setText("L�rdag");break;
        //        }



        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent myIntent = new Intent(v.getContext(), QuickInfoActivity.class);
                myIntent.putExtra("pos", position);
                startActivityForResult(myIntent, 0); 
            }
        });
    }

    public void index(int i) {
        switch (i) {
        case 0 : index1.setText("M");index1.setBackgroundResource(R.drawable.grey_dot);index2.setText("");index2.setBackgroundResource(R.drawable.grey_dot_small);index3.setText("");index3.setBackgroundResource(R.drawable.grey_dot_small);index4.setText("");index4.setBackgroundResource(R.drawable.grey_dot_small);index5.setText("");index5.setBackgroundResource(R.drawable.grey_dot_small);break;
        case 1 : index1.setText("");index1.setBackgroundResource(R.drawable.grey_dot_small);index2.setText("T");index2.setBackgroundResource(R.drawable.grey_dot);index3.setText("");index3.setBackgroundResource(R.drawable.grey_dot_small);index4.setText("");index4.setBackgroundResource(R.drawable.grey_dot_small);index5.setText("");index5.setBackgroundResource(R.drawable.grey_dot_small);break;
        case 2 : index1.setText("");index1.setBackgroundResource(R.drawable.grey_dot_small);index2.setText("");index2.setBackgroundResource(R.drawable.grey_dot_small);index3.setText("O");index3.setBackgroundResource(R.drawable.grey_dot);index4.setText("");index4.setBackgroundResource(R.drawable.grey_dot_small);index5.setText("");index5.setBackgroundResource(R.drawable.grey_dot_small);break;
        case 3 : index1.setText("");index1.setBackgroundResource(R.drawable.grey_dot_small);index2.setText("");index2.setBackgroundResource(R.drawable.grey_dot_small);index3.setText("");index3.setBackgroundResource(R.drawable.grey_dot_small);index4.setText("T");index4.setBackgroundResource(R.drawable.grey_dot);index5.setText("");index5.setBackgroundResource(R.drawable.grey_dot_small);break;
        case 4 : index1.setText("");index1.setBackgroundResource(R.drawable.grey_dot_small);index2.setText("");index2.setBackgroundResource(R.drawable.grey_dot_small);index3.setText("");index3.setBackgroundResource(R.drawable.grey_dot_small);index4.setText("");index4.setBackgroundResource(R.drawable.grey_dot_small);index5.setText("F");index5.setBackgroundResource(R.drawable.grey_dot);break;
        }

    }
    public void ranStatus() {
        int ran = 0;
        Random generator = new Random();
        ran = generator.nextInt(3);
        Log.i("random", ""+ran);
        if(ran == 0) {
            people.setText("230");
            people.setTextColor(getResources().getColor(R.color.super_green));
        }
        else if(ran == 1) {
            people.setText("540");
            people.setTextColor(getResources().getColor(R.color.super_yellow));
        }
        else {
            people.setText("980");
            people.setTextColor(getResources().getColor(R.color.super_red));
        }
    }

    public void makeDates() {
        dates.add("I dag");
        dates.add("I morra");
        dates.add("Om to dager");
        dates.add("Om tre dager");
        dates.add("Om fire dager");
        dates.add("Start styrkeøkt");
        dates.add("Start styrkeøkt");
        dates.add("Lag hurtig trening");
        dates.add("Lag hurtig trening");
        dates.add("Start styrkeøkt");

    }


    /**
     * Navigate to TrainingBookActivty
     * @param v
     */
    //onClick methods
    public void toTrainingBook(View v) {
        Intent myIntent = new Intent(v.getContext(), TrainingBookActivity.class);
        startActivityForResult(myIntent, 0);        
    } 

    public void toJointTraining(View v) {
        Intent myIntent = new Intent(v.getContext(), JointTrainingActivity.class);
        startActivityForResult(myIntent, 0);        
    }

    public void toTrainingNetwork(View v) {
        Intent myIntent = new Intent(v.getContext(), TrainingNetworkActivity.class);
        startActivityForResult(myIntent, 0);        
    }

    public void toLog(View v) {
        Intent myIntent = new Intent(v.getContext(), LogActivity.class);
        startActivityForResult(myIntent, 0);        
    }

    public void toInfo(View v) {
        Intent myIntent = new Intent(v.getContext(), InfoActivity.class);
        startActivityForResult(myIntent, 0);        
    }

    //testing Gallery widget
    public class ImageAdapter extends BaseAdapter {
        int mGalleryItemBackground;
        private Context mContext;

        private Integer[] mImageIds = {
                R.drawable.start_trening,
                R.drawable.start_trening,
                R.drawable.hurtig_trening,
                R.drawable.hurtig_trening,
                R.drawable.start_trening
        };

        public ImageAdapter(Context c) {
            mContext = c;
            TypedArray attr = mContext.obtainStyledAttributes(R.styleable.HelloGallery);
            mGalleryItemBackground = attr.getResourceId(R.styleable.HelloGallery_android_galleryItemBackground, 0);
            attr.recycle();
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);

            imageView.setImageResource(mImageIds[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setBackgroundResource(0);

            return imageView;
        }
    }
}