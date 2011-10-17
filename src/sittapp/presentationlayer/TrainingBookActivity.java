/**
 * 
 */
package sittapp.presentationlayer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 * @author Nelich
 *
 */
public class TrainingBookActivity extends Activity {
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tbalayout);
        

    }
    
    
    
    public void toCustomPlan(View v) {
        Intent myIntent = new Intent(v.getContext(), CustomPlanActivity.class);
        startActivityForResult(myIntent, 0);        
    }  

}
