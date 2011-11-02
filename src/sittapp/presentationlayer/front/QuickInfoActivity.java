package sittapp.presentationlayer.front;

import java.util.ArrayList;

import sittapp.presentationlayer.R;
import sittapp.presentationlayer.R.layout;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

/**
 * @author Nelich
 *
 */
public class QuickInfoActivity extends Activity {
    Chronometer meter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qlayout);
        TextView text = (TextView) findViewById(R.id.testview);
        meter = (Chronometer)findViewById(R.id.chronometer1);

       


    }
    
    
    public void onStart(View v) {
        meter.start();
    }


}
