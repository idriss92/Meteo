package com.partiels.meteo.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


import com.partiels.meteo.R;

public class AjouterVille extends Activity {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajouter_ville);
	}
	
	
	  @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.ajouter_ville_actions, menu);
		  	getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	  
	  public void valider (View view) {
		  Intent intent = new Intent();
		  intent.putExtra("city", ((EditText)findViewById(R.id.editText1)).getText().toString());
		  setResult(RESULT_OK, intent);
		  finish();
	  }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	        
	    	
	    	
	    	switch(item.getItemId())
	    	{
	    	case R.id.action_view_as_list :
	    	
	    		return true;
	    		
	    	case R.id.action_search :
	    		
	    	default:
	    		return super.onOptionsItemSelected(item);
	    	}
	    }
	    

}
