/**
 * 
 */
package sittapp.presentationlayer.network;

import java.util.ArrayList;
import sittapp.com.HttpCom;
import sittapp.model.Contacts;
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
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;


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
    ConAdapter sA;
    private Context mContext = TrainingNetworkActivity.this;
    ArrayList<Contacts> invs;
    String username;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tnlayout);
        name = (TextView)findViewById(R.id.name);
        invite = (Button)findViewById(R.id.invite);

        invs = new ArrayList<Contacts>();
        invs.add(new Contacts ("Ole Jorgen Rishoff", false));
        invs.add(new Contacts ("Andre S. Hansen", false));
        invs.add(new Contacts ("Linn Johansson", false));
        invs.add(new Contacts ("Anne Karen Aanonli", false));
        invs.add(new Contacts ("Lars Hagen", false));
        invs.add(new Contacts ("Silje Nissen", false));
        invs.add(new Contacts ("Lise S. Hansen", false));
        invs.add(new Contacts ("Truls Braaten", false));
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
    
    @Override
    public void onResume() {
        super.onResume();
        if (username != null) {
            user = TrainingNetworkActivity.this.com.login(username);
            makeList();
            
        }
    }
    
    public void showGangInv(View v) {
        acceptInv();

    }

    public void addGang(View v) {
        final Dialog dialog = new Dialog(this.mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pmlayout);
        final EditText name = (EditText)dialog.findViewById(R.id.editText1);
        name.setHint("Gjeng navn");


        Button inv = (Button)dialog.findViewById(R.id.button1);
        this.sA = new ConAdapter(this, R.layout.list_contact_check, invs);
        ListView list = (ListView)dialog.findViewById(R.id.listView1);
        list.setAdapter(this.sA);




        //        add.setOnClickListener(new OnClickListener() {
        //            public void onClick(View v) {
        //                String m = "" + gangM.getText();
        //                invs.add(m);
        //                gangM.setText("");
        //                TrainingNetworkActivity.this.sA.notifyDataSetChanged();
        //            }
        //        });

        inv.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String n = "" + name.getText();
                Gang gang = com.gangCreate(user.getName(), n);

                for (Contacts s : invs) {
                    Log.i("Prøver", "name" + n);
                    Log.i("Her", s.getName() + s.getAdd());
                    if (s.getAdd()) {
                        com.gangInvite(gang.getId(), s.getName());
                    }
                }
                if (username != null) {
                    user = TrainingNetworkActivity.this.com.login(username);
                    makeList();
                                   }
                dialog.cancel();
                
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
                username = value;
                user = TrainingNetworkActivity.this.com.login(value);
                // name.setText(user.getName());
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
            int m = g.getMembers().size();
            if (g != null) {
                TextView gangName = (TextView) v.findViewById(R.id.listcontact);
                if (gangName != null) {
                    gangName.setText(g.getName()); 
                }
                TextView gangNr = (TextView) v.findViewById(R.id.howMany);
                if (gangNr != null) {
                    gangNr.setText("(" +m+")"); 
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
         * @return 
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
                v = vi.inflate(R.layout.list_contact_check, null);
            }        

            String  g = this.items.get(position);
            if (g != null) {
                TextView gangName = (TextView) v.findViewById(R.id.listcontact_check);
                if (gangName != null) {
                    gangName.setText(g.toString()); 
                }

            }
            return v;

        }

    }

    private class ConAdapter extends ArrayAdapter<Contacts> {
        private ArrayList<Contacts> items;
        int teller = 0;
        /**
         * 
         * @param context
         * @param textViewResourceId
         * @param items
         * @return 
         */

        public ConAdapter(Context context, int textViewResourceId, ArrayList<Contacts> items) {
            super(context, textViewResourceId, items);
            this.items = items;

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_contact_check, null);
            }        

            Contacts  g = this.items.get(position);
            if (g != null) {
                TextView gangName = (TextView) v.findViewById(R.id.listcontact_check);
                if (gangName != null) {
                    gangName.setText(g.getName()); 
                }

            }
            if (g != null) {
                final CheckBox checked = (CheckBox)v.findViewById(R.id.invcheck);
                if (checked != null) {
                    checked.setChecked(false);
                }
                checked.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        boolean ch = checked.isChecked();
                        // TODO Auto-generated method stub
                        if (!ch) {
                            Log.i("adder", "her" + invs.get(position).getName());
                            invs.get(position).setAdd(true);       
                        }
                        else {
                            invs.get(position).setAdd(false);   
                        }


                    }

                });


            }

            return v;

        }

    }

}
