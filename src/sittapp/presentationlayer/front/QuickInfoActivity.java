package sittapp.presentationlayer.front;

import java.util.ArrayList;

import sittapp.presentationlayer.R;
import sittapp.presentationlayer.R.layout;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Nelich
 *
 */
public class QuickInfoActivity extends Activity {
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ilayout);
        TextView text = (TextView) findViewById(R.id.testview);
        
        int contents = getIntent().getIntExtra("pos", 0);
        text.setText(""+ contents);
        
        
  }
 

}
