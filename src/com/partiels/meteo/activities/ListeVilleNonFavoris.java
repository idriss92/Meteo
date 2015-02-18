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


public class ListeVilleNonFavoris extends ListActivity{
	
	
	static String [] lesVillesStatiques = {
		"Londres",
		"Moscou",
		"Shanghai",
		"Pekin",
		"Karachi",
		"Lagos",
		"Istanbul",
		"Canton",
		"Mumbai",
		"Sao Paulo",
		"Lahore",
		"Delhi",
		"Shenzen",
		"Seoul",
		"Kinshasa",
		"Tokyo",
		"Dacca",
		"Le Caire",
		"New York",
		"Teheran",
		"Bogota",
		"Bagdad",
		"Riyad",
		"Luanda",
		"Singapour",
		"Abidjan",
		"Sidney",
		"Alexandrie",
		"Bangkok",
		"Mexico"
		};

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste_ville_non_favoris);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lesVillesStatiques);
		setListAdapter(adapter);
	}

	
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO Auto-generated method stub
    	super.onListItemClick(l, v, position, id);
    	String city = this.lesVillesStatiques[position];//.getItem(position);
    	Intent intent = new Intent();
    	intent.putExtra("city", city);
    	setResult(RESULT_OK, intent);
    	finish();
    }
	
	
}
