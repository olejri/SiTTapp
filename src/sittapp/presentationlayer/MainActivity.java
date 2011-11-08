package sittapp.presentationlayer;

import java.util.Calendar;
import java.util.Date;

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
import android.widget.BaseAdapter;
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


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        comExamples();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Gallery gallery = (Gallery) findViewById(R.id.window);
        gallery.setAdapter(new ImageAdapter(this));
        date = (TextView)findViewById(R.id.open);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2011);
        cal.set(Calendar.DAY_OF_YEAR, 312);
        int weekday = cal.get(Calendar.DAY_OF_WEEK);
        switch (weekday) {
        case 1 : date.setText("Søndag");break;
        case 2: date.setText("Mandag");break;
        case 3: date.setText("Tirsdag");break;
        case 4: date.setText("Onsdag");break;
        case 5: date.setText("Torsdag");break;
        case 6: date.setText("Fredag");break;
        case 7: date.setText("Lørdag");break;
        }
      


        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent myIntent = new Intent(v.getContext(), QuickInfoActivity.class);
                myIntent.putExtra("pos", position);
                startActivityForResult(myIntent, 0); 
            }
        });
    }

    public void comExamples() {
        User user = com.login("Andriod");
        //Gang newGang = com.gangCreate("Andriod", "Bottlebois");
        //Log.d("MainNewgang", newGang.toString());
        //boolean invited = com.gangInvite(newGang.getId(), "Nelich");
        //boolean accepted = com.gangAccept(newGang.getId(), "Nelich");
        //boolean declined = com.gangDecline(newGang.getId(), "Nelich");
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
                R.drawable.hurtigtrening,
                R.drawable.hurtigtrening_trykt_inn,
                R.drawable.pause,
                R.layout.pmlayout
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
            imageView.setBackgroundResource(mGalleryItemBackground);

            return imageView;
        }
    }
}