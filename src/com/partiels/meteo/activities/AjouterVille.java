package com.partiels.meteo.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;



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
	        //MenuInflater inflater = getMenuInflater();
	        //inflater.inflate(R.menu.ajouter_ville_actions, menu);
		  	//getMenuInflater().inflate(R.menu.main, menu);
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
	        return false;

	    }
	    

}
