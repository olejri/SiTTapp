package sittapp.presentationlayer;

import java.util.ArrayList;
import android.app.Activity;
import android.app.ListActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

/**
 * @author Nelich
 *
 */
public class LogActivity extends TabActivity {
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llayout);
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
        TabSpec secondTabSpec = tabHost.newTabSpec("tid1");
        TabSpec thirdTabSpec = tabHost.newTabSpec("tid1");
        
        firstTabSpec.setIndicator("Dag").setContent(new Intent(this,DayTab.class));
        secondTabSpec.setIndicator("Uke").setContent(new Intent(this,WeekTab.class));
        thirdTabSpec.setIndicator("Måned").setContent(new Intent(this,MonthTab.class));
        tabHost.addTab(firstTabSpec);
        tabHost.addTab(secondTabSpec);
        tabHost.addTab(thirdTabSpec);
        
  } 
}
