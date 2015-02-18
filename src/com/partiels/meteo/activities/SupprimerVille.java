package com.partiels.meteo.activities;

import android.app.ListActivity;
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
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.partiels.meteo.R;


public class SupprimerVille extends ListActivity{

	//ListVille liste = new ListVille();

	ArrayAdapter<String> listeVille;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_supprimer_ville);

		// listeVille = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
	     //setListAdapter(listeVille);
	     //liste.restoreVille();
	}
	
	
	  @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.

	        return true;
	    }
	  


	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
			return false;
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.

	    }
	 
	    
	    protected void onListItemClick(ListView l, View v, int position, long id) {
	    	// TODO Auto-generated method stub
	    	super.onListItemClick(l, v, position, id);
	    	String city = this.listeVille.getItem(position);
	    	Intent intent = new Intent();
	    	intent.removeExtra(city);
	    	//setResult(RESULT_CANCELED, intent);
	    	//setResult(RESULT_OK, intent);
	    	//finish();
	    }
	
	
}
