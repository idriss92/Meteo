package com.partiels.meteo.activities;

import java.util.Map;

import com.partiels.meteo.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListVille extends ListActivity{
	
	
	ArrayAdapter<String> listeVille;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_villes);
        System.out.print("Fuck");
        listeVille = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        setListAdapter(listeVille);
        restoreVille();
        // get action bar   
        //ActionBar actionBar = getActionBar();
 
        // Enabling Up / Back navigation
        //actionBar.setDisplayHomeAsUpEnabled(true);
    }

	public void restoreVille(){
		SharedPreferences preferences = this.getSharedPreferences("meteo", 0);
		Map<String, String> map = (Map<String, String>) preferences.getAll();
		this.listeVille.clear();
		for (String city : map.values()) {
			this.listeVille.add(city);
		}
	}
	
	public void saveVille(String city) {
		SharedPreferences preferences = this.getSharedPreferences("meteo", 0);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(city, city);
		editor.commit();
	}
	
	
	  @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        //getMenuInflater().inflate(R.menu.liste_villes_actions, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	    	
	    	return false;
	    	
	    }
	    
	    @Override
	    protected void onListItemClick(ListView l, View v, int position, long id) {
	    	// TODO Auto-generated method stub
	    	super.onListItemClick(l, v, position, id);
	    	String city = this.listeVille.getItem(position);
	    	Intent intent = new Intent();
	    	intent.putExtra("city", city);
	    	setResult(RESULT_OK, intent);
	    	finish();
	    }
	    
	    
	    /**
	     * Launching new Search
	     */
	    
	    public void AjouterVille(View view){
	    	Log.d("fuck", "error");
	    	Intent i = new Intent(ListVille.this,AjouterVille.class);
	    	startActivityForResult(i, 1);
	    }
	    
	    public void SupprimerFavoris(View view){
	    	Intent i = new Intent(ListVille.this,SupprimerVille.class);
	    	startActivityForResult(i, 2);
	    }
	    
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	// TODO Auto-generated method stub
	    	super.onActivityResult(requestCode, resultCode, data);
	    	if (requestCode == 1) {
	    		if (resultCode == RESULT_OK) {
	    			String city = (String) data.getExtras().get("city");
	    			listeVille.add(city);
	    			this.saveVille(city);
	    			Toast.makeText(getApplicationContext(), city, Toast.LENGTH_LONG).show();
	    		}
	    	}
	    	if(requestCode == 2){
	    		this.restoreVille();
	    	}
	    }
}
