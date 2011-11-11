package sittapp.presentationlayer.log;

import java.util.ArrayList;

import sittapp.presentationlayer.R;
import sittapp.presentationlayer.R.layout;
import android.app.Activity;
import android.app.ListActivity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

/**
 * @author Nelich
 *
 */
public class LogActivity extends TabActivity {
    ImageView day;
 //   ImageView week;
  //  ImageView month;
    TabHost tabHost;
    Button button1;
    Button button2;
    Button button3;
    int knapp = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.YourTheme);
        setContentView(R.layout.llayout);
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
//        TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
//        TabSpec secondTabSpec = tabHost.newTabSpec("tid1");
//        TabSpec thirdTabSpec = tabHost.newTabSpec("tid1");
        day = (ImageView)findViewById(R.id.dayImage);
//        week = (ImageView)findViewById(R.id.weekImage);
//        month = (ImageView)findViewById(R.id.monthImage);

        setupTab(new TextView(this), "Dag", new Intent(this,DayTab.class));
        setupTab(new TextView(this), "Uke", new Intent(this,WeekTab.class));
        setupTab(new TextView(this), "Måned", new Intent(this,MonthTab.class));
        tabHost.getTabWidget().setStripEnabled(false);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            public void onTabChanged(String tabId) {
                if (knapp == 0) {
//                if (tabId.equals("Dag"))day.setImageResource(R.drawable.lggtil_knapp);
//                else if (tabId.equals("Uke"))day.setImageResource(R.drawable.button_network);
//                else if(tabId.equals("Måned"))day.setImageResource(R.drawable.standar_knapp);
                }
                else if (knapp ==1) {
                    if (tabId.equals("Dag"))day.setImageResource(R.drawable.styrke_log);
                    else if (tabId.equals("Uke"))day.setImageResource(R.drawable.styrke_log);
                    else if(tabId.equals("Måned"))day.setImageResource(R.drawable.styrke_log);
                    
                }
                else if (knapp ==2) {
                    if (tabId.equals("Dag"))day.setImageResource(R.drawable.kondis_log);
                    else if (tabId.equals("Uke"))day.setImageResource(R.drawable.kondis_log);
                    else if(tabId.equals("Måned"))day.setImageResource(R.drawable.kondis_log);
                    
                }
                }
                
           
            
        });
    }

    private void setupTab(final View view, final String tag, Intent in) {
        View tabview = createTabView(tabHost.getContext(), tag);
        TabSpec setContent = tabHost.newTabSpec(tag).setIndicator(tabview).setContent(in);
        tabHost.addTab(setContent);
    }

    private static View createTabView(final Context context, final String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
        tv.setText(text);
        return view;

    }
    public void showJoint(View v) {
        knapp = 0;
        button1.setBackgroundResource(R.drawable.gruppe_knapp_farge);
        button2.setBackgroundResource(R.drawable.styrke_knapp);
        button3.setBackgroundResource(R.drawable.kondis_knapp);
        
        
      //  day.setImageResource(R.color.lgreen);
//        week.setImageResource(R.color.lgreen);
//        month.setImageResource(R.color.lgreen);
        int tab = tabHost.getCurrentTab();
        switch (tab) {
        case 0 : day.setImageResource(R.drawable.gruppetrening_log);break;
        case 1: day.setImageResource(R.drawable.gruppetrening_log);break;
        case 2: day.setImageResource(R.drawable.gruppetrening_log);break;
        }
    }
    public void showStrength(View v) {
        knapp = 1;
        button1.setBackgroundResource(R.drawable.gruppe_knapp);
        button2.setBackgroundResource(R.drawable.styrke_knapp_farge);
        button3.setBackgroundResource(R.drawable.kondis_knapp);
       // day.setImageResource(R.color.lgreen);
//        week.setImageResource(R.color.lgreen);
//        month.setImageResource(R.color.lgreen);
        int tab = tabHost.getCurrentTab();
        switch (tab) {
        case 0 : day.setImageResource(R.drawable.styrke_log); break;
        case 1:  day.setImageResource(R.drawable.styrke_log);break;
        case 2:  day.setImageResource(R.drawable.styrke_log);break;
        }
  }
    public void showEndurance(View v) {
        knapp =2;
        button1.setBackgroundResource(R.drawable.gruppe_knapp);
        button2.setBackgroundResource(R.drawable.styrke_knapp);
        button3.setBackgroundResource(R.drawable.kondis_knapp_farge);
      //  day.setImageResource(R.color.lgreen);
//        week.setImageResource(R.color.lgreen);
//        month.setImageResource(R.color.lgreen);
        int tab = tabHost.getCurrentTab();
        switch (tab) {
        case 0 : day.setImageResource(R.drawable.kondis_log);break;
        case 1:  day.setImageResource(R.drawable.kondis_log);break;
        case 2: day.setImageResource(R.drawable.kondis_log);break;
        } 
       
        
    }
}
