/**
 * 
 */
package sittapp.presentationlayer.network;

import java.util.ArrayList;
import sittapp.com.HttpCom;
import sittapp.model.Gang;
import sittapp.model.GangMember;
import sittapp.model.User;
import sittapp.presentationlayer.R;
import sittapp.presentationlayer.front.QuickInfoActivity;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
    Button invite;
    TextView gangName;
    StringAdapter sA;
    private Context mContext = TrainingNetworkActivity.this;
 



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tnlayout);
        name = (TextView)findViewById(R.id.name);
        invite = (Button)findViewById(R.id.invite);
        login();


    }
    public void makeList() {
        ListView lv = (ListView)findViewById(android.R.id.list);
        ArrayList <Gang> gang = user.getGangs();
        for (Gang g : gang) {
            Log.i("NAVN!", "" +g.getName());
        }
        this.cA = new ContactAdapter(this, R.layout.list_contact, gang);
        setListAdapter(this.cA);
    }

    public void showGangInv(View v) {
        acceptInv();

    }

    public void addGang(View v) {
        final Dialog dialog = new Dialog(this.mContext);
        dialog.setTitle("Lag gjeng");
        dialog.setContentView(R.layout.pmlayout);
        final EditText name = (EditText)dialog.findViewById(R.id.editText1);
        final EditText gangM = (EditText)dialog.findViewById(R.id.editText2);
        final ArrayList<String> invs = new ArrayList<String>();
        Button add = (Button)dialog.findViewById(R.id.button2);
        Button inv = (Button)dialog.findViewById(R.id.button1);
        this.sA = new StringAdapter(this, R.layout.list_contact, invs);
        ListView list = (ListView)dialog.findViewById(R.id.listView1);
        list.setAdapter(this.sA);
        list.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                invs.remove(arg2);
                TrainingNetworkActivity.this.sA.notifyDataSetChanged();

            }

        });
        add.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String m = "" + gangM.getText();
                invs.add(m);
                gangM.setText("");
                TrainingNetworkActivity.this.sA.notifyDataSetChanged();
            }
        });

        inv.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String n = "" + name.getText();
                Gang gang = com.gangCreate(user.getName(), n);

                for (String s : invs) {
                    com.gangInvite(gang.getId(), s);

                }
                dialog.cancel();
                TrainingNetworkActivity.this.cA.notifyDataSetChanged();
            }

        });

        dialog.show();
    }


    public void acceptInv() {
        final ArrayList<Gang> gangInv = user.getGangInvites();
        int size = gangInv.size();


        boolean checked[] = new boolean[size];
        String names[] = new String[size];
        for (int i = 0; i < size; i++) {
            names[i] = gangInv.get(i).getName();
            checked[i] = true;
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Bli med i gjengen");  
        alert.setMultiChoiceItems(names, checked,
                new DialogInterface.OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialog, int whichButton, boolean isChecked) {
                if (isChecked) {
                    gangInv.get(whichButton).setInv(true);
                }
                else{
                    gangInv.get(whichButton).setInv(false);
                }
                /* User clicked on a check box do some stuff */
            }
        });
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                for (Gang g : gangInv) {
                    if (g.getInv()) {
                        com.gangAccept(g.getId(), user.getName());
                    }
                    else {
                        com.gangDecline(g.getId(), user.getName());
                    }
                }
            }    
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alert.show();
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
            invite.setText("" + gangInvs.size());
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
        ArrayList<Gang> gangs = user.getGangs();

        Gang gang = gangs.get(position);
        ArrayList<GangMember> members = gang.getMembers();
        ArrayList<String> sendMembers = new ArrayList<String>();
        for(GangMember m : members) {
            sendMembers.add(m.getName());

        }
        String name = user.getName();
        String gName = gang.getName();
        long gangId= gang.getId();
        Intent myIntent = new Intent(v.getContext(), GangActivity.class);
        myIntent.putExtra("name", name);
        myIntent.putExtra("gName", gName);
        myIntent.putExtra("id", gangId);
        myIntent.putStringArrayListExtra("members", sendMembers);
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
                v = vi.inflate(R.layout.list_contact, null);
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
