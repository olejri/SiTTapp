/**
 * 
 */
package sittapp.presentationlayer.network;

import java.util.ArrayList;
import sittapp.com.HttpCom;
import sittapp.model.Gang;
import sittapp.model.User;
import sittapp.presentationlayer.R;
import sittapp.presentationlayer.joint.JointTrainingActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
public class GangActivity extends ListActivity {
    ContactAdapter cA;
    HttpCom com = new HttpCom();
    User user;
    TextView name;
    Button invite;
    TextView gangName;
    StringAdapter sA;
    private Context mContext = GangActivity.this;
    ArrayList<String> members;
    long gandId;
    String iName ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glayout);
        name = (TextView)findViewById(R.id.gangName);
        invite = (Button)findViewById(R.id.invite);
        //login();
        iName = getIntent().getStringExtra("name");
        name.setText(getIntent().getStringExtra("gName"));
        gandId = getIntent().getLongExtra("id", 0L);
        members = getIntent().getStringArrayListExtra("members");
        makeList();

    }
    public void makeList() {
        ListView lv = (ListView)findViewById(android.R.id.list);
        this.sA = new StringAdapter(this, R.layout.list_contact_onsite, members);
        setListAdapter(this.sA);
    }


    public void onLeave(View v) {
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(mContext);
        alt_bld.setMessage("Forlate gruppen?")
        .setCancelable(false)
        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                com.gangLeave(gandId, iName);
                finish();
            }
        })
        .setNegativeButton("Nei", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alt_bld.show();
    }

    public void onInvGang(View v) {
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(mContext);
        final EditText input = new EditText(this);
        alt_bld.setMessage("Inviter en venn?")
        .setView(input)
        .setCancelable(false)
        .setPositiveButton("Inviter", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String name = "" + input.getText();
                boolean join = com.gangInvite(gandId, name);
                if (join) {
                    Toast.makeText(mContext, "Sendt invitasjon til " + name, Toast.LENGTH_SHORT).show();    
                }
                else Toast.makeText(mContext, "Finner ikke bruker ved navnet " + name, Toast.LENGTH_SHORT).show();
            }
        })
        .setNegativeButton("Tilbake", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alt_bld.show();
    }



    @Override
    protected void onListItemClick(ListView lv, View v, int position, long id) {
        String name = members.get(position);
        Intent myIntent = new Intent(v.getContext(), GangMemberActivity.class);
        myIntent.putExtra("name", name);
        startActivityForResult(myIntent, 0);



    }


    private class ContactAdapter extends ArrayAdapter<Gang> {
        private ArrayList<Gang> items;
        int teller = 0;
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

            Gang g = this.items.get(position);
            if (g != null) {
                TextView gangName = (TextView) v.findViewById(R.id.listcontact);
                if (gangName != null) {
                    gangName.setText(g.getName()); 
                }

            }
            return v;

        }

    }

    private class StringAdapter extends ArrayAdapter<String> {
        private ArrayList<String> items;
        int teller = 0;
        /**
         * 
         * @param context
         * @param textViewResourceId
         * @param items
         */
        public StringAdapter(Context context, int textViewResourceId, ArrayList<String> items) {
            super(context, textViewResourceId, items);
            this.items = items;

        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_contact_onsite, null);
            }        

            String  g = this.items.get(position);
            if (g != null) {
                TextView gangName = (TextView) v.findViewById(R.id.listcontact);
                if (gangName != null) {
                    gangName.setText(g.toString()); 
                }

            }
            return v;

        }

    }

}
