package sittapp.presentationlayer.log;

import java.util.ArrayList;

import sittapp.presentationlayer.R;
import sittapp.presentationlayer.R.layout;
import android.app.Activity;
import android.app.ListActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

/**
 * @author Nelich
 *
 */
public class LogActivity extends TabActivity {
    TextView test1;
    TextView test2;
    TextView test3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llayout);
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
        TabSpec secondTabSpec = tabHost.newTabSpec("tid1");
        TabSpec thirdTabSpec = tabHost.newTabSpec("tid1");
        test1 = (TextView)findViewById(R.id.test1);
        test2 = (TextView)findViewById(R.id.test2);
        test3 = (TextView)findViewById(R.id.test3);
        
        firstTabSpec.setIndicator("Dag").setContent(new Intent(this,DayTab.class));
        secondTabSpec.setIndicator("Uke").setContent(new Intent(this,WeekTab.class));
        thirdTabSpec.setIndicator("Måned").setContent(new Intent(this,MonthTab.class));
        tabHost.addTab(firstTabSpec);
        tabHost.addTab(secondTabSpec);
        tabHost.addTab(thirdTabSpec);
        
  }
    
    
    public void showJoint(View v) {
        test1.setText("gruppe");
        test2.setText("gruppe");
        test3.setText("gruppe");
        
    }
 
    public void showStrength(View v) {
        test1.setText("styrke");
        test2.setText("styrke");
        test3.setText("styrke");
    }
 
    public void showEndurance(View v) {
        test1.setText("kondis");
        test2.setText("kondis");
        test3.setText("kondis");
 }
}
