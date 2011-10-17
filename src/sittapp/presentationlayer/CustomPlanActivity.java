/**
 * 
 */
package sittapp.presentationlayer;


import java.util.ArrayList;





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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @author Nelich
 *
 */
public class CustomPlanActivity extends ListActivity {
    ContactAdapter cA;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cplayout);
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
 
 
 public void toInput(View v) {
     final CharSequence[] items = {"Gruppetime", "Styrke", "Kondisjon"};

     AlertDialog.Builder builder = new AlertDialog.Builder(this);
     builder.setTitle("Velg type");
     builder.setItems(items, new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int item) {
             Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
         }
     });
     AlertDialog alert = builder.create();
     alert.show();
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
            String s = items.get(position);
            if (s != null) {
                TextView tt = (TextView) v.findViewById(R.id.listcontact);
                if (tt != null) {
                    tt.setText(s);                
                }

            }
            return v;

        }

    }

}
