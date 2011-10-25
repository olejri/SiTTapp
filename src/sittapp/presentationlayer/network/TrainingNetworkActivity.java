/**
 * 
 */
package sittapp.presentationlayer.network;


import java.util.ArrayList;

import sittapp.presentationlayer.R;








import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout;


/**
 * @author Nelich
 *
 */
public class TrainingNetworkActivity extends ListActivity {
    ContactAdapter cA;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tnlayout);
        makeList();
  }
 public void makeList() {
        ListView lv = (ListView)findViewById(android.R.id.list);
        ArrayList<String> listNames = new ArrayList<String>();
        
        for(int i=0;i < 10;i++) {
            listNames.add("test");
        }
        this.cA = new ContactAdapter(this, R.layout.list_contact, listNames);
        setListAdapter(this.cA);

    }
 
 
 public void toTest(View v) {
    
    
 }
 
 @Override
protected void onListItemClick(ListView lv, View v, int position, long id) {
     Toast.makeText(getApplicationContext(), "du trykket på" + position, Toast.LENGTH_SHORT).show();
 }
    
    
    private class ContactAdapter extends ArrayAdapter<String> {
        private ArrayList<String> items;
        /**
         * 
         * @param context
         * @param textViewResourceId
         * @param items
         */
        public ContactAdapter(Context context, int textViewResourceId, ArrayList<String> items) {
            super(context, textViewResourceId, items);
            this.items = items;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_contact, null);
            }            
            /*Button button= (Button)v.findViewById(R.id.testbutton);
            button.setTag(new String(""+position));
            button.setOnClickListener(new View.OnClickListener(){
                //Getting the row where a button is clicked.(used to reserve a product from the list)
                public void onClick(View v) {
                    RelativeLayout parentRow = (RelativeLayout)v.getParent();
                    Button btnChild = (Button)parentRow.getChildAt(3);
                    int resIndex = Integer.parseInt(btnChild.getTag().toString());
                    //Toast.makeText(getApplicationContext(), "du trykket på" + resIndex, Toast.LENGTH_SHORT).show();
                    
                }
                   
            });*/
            return v;

        }

    }

}
