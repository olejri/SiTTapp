/**
 * 
 */
package sittapp.presentationlayer.network;

import java.util.ArrayList;
import sittapp.com.HttpCom;
import sittapp.model.Gang;
import sittapp.model.User;
import sittapp.presentationlayer.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * @author Nelich
 *
 */
public class TrainingNetworkActivity extends ListActivity {
    ContactAdapter cA;
    HttpCom com = new HttpCom();
    User user;
    TextView name;
    ImageButton invite;
    TextView gangName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tnlayout);
        name = (TextView)findViewById(R.id.name);
        invite = (ImageButton)findViewById(R.id.invite);
        
        login();


    }
    public void makeList() {
        ListView lv = (ListView)findViewById(android.R.id.list);
        ArrayList <Gang> gang = user.getGangs();
        this.cA = new ContactAdapter(this, R.layout.list_contact, gang);
        setListAdapter(this.cA);
    }

    public void showGangInv(View v) {
        alertDialogLoadFile();

    }
    
    public void alertDialogLoadFile() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Choose:");  
        CharSequence[] items = { "moshe", "yosi", "ee" };
        alert.setSingleChoiceItems(items , -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item){
                /* User clicked on a radio button do some stuff */
            }
        });
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
        }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog ad = alert.create();
        ad.show();

    }


    public void login(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Logg inn");
        // Set an EditText view to get user input 
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String checkName = prefs.getString("name", "");
        final EditText input = new EditText(this);
        input.setText(checkName);
        input.setHint("Brukernavn");
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = "" + input.getText();
                user = TrainingNetworkActivity.this.com.login(value);
                name.setText(user.getName());
                makeList();
                checkForInv();
                setId(user.getName());
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });
        alert.show();
    }

    public void checkForInv() {
        ArrayList<Gang> gangInvs = user.getGangInvites();
        if (gangInvs.size() > 0) {
            invite.setVisibility(View.VISIBLE);
        }
    }

    public void setId(String name) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor spe = prefs.edit();
        spe.putString("name", name);
        spe.commit();
    }




    @Override
    protected void onListItemClick(ListView lv, View v, int position, long id) {
        Toast.makeText(getApplicationContext(), "du trykket på" + position, Toast.LENGTH_SHORT).show();
    }


    private class ContactAdapter extends ArrayAdapter<Gang> {
        private ArrayList<Gang> items;
        /**
         * 
         * @param context
         * @param textViewResourceId
         * @param items
         */
        public ContactAdapter(Context context, int textViewResourceId, ArrayList<Gang> items) {
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
            
            Gang g = items.get(position);
            if (g != null) {
                TextView gangName = (TextView) findViewById(R.id.listcontact);
                if (gangName != null) {
                    gangName.setText(g.getName());             
                }
              
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
