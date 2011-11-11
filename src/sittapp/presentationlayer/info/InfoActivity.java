package sittapp.presentationlayer.info;

import java.util.ArrayList;

import sittapp.presentationlayer.R;
import sittapp.presentationlayer.R.layout;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;

/**
 * @author Nelich
 *
 */
public class InfoActivity extends Activity {
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Dialog);
        setContentView(R.layout.ilayout);
        
  }
 

}
