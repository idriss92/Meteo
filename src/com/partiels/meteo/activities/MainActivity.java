package com.partiels.meteo.activities;


import com.partiels.meteo.algo.WeatherM;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.partiels.meteo.R;
import com.partiels.meteo.R.layout;
import com.partiels.meteo.R.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar;
import com.partiels.meteo.algo.*;
public class MainActivity extends Activity {
	
	MeteoData meteo;
	View view;
	ImageView image;
	TextView city;
	TextView degres;
	TextView humidite;
	TextView vent;
	TextView date;
	
	String ville;
	
	public static final String MyCity = "MyFavoriteCity" ;
	public static final String City = "cityKey"; 
	
	public String weatherXML = null;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ville = "Paris";
		//new RequestTask().execute("http://www.openweathermap.org/data/2.5/weather?q=" + ville + "&units=metric&mode=xml");
		
		
		city = (TextView) findViewById(R.id.textViewCity);
		degres = (TextView) findViewById(R.id.textViewDegres);
		humidite = (TextView) findViewById(R.id.textViewHumidite);
		vent = (TextView) findViewById(R.id.textViewVent);
		date = (TextView) findViewById(R.id.textViewDate);

		city.setText((CharSequence) ville);
		image = (ImageView) findViewById(R.id.imageView1);
		this.loadVille(ville);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        
    	
    	
    	switch(item.getItemId())
    	{
    	case R.id.action_view_as_list :
    		ListVille();
    		return true;
    		case  R.id.action_refresh :
    			this.RefreshData();
    		return true;
    		
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    	
    }
    
	/**
     * Lauching ListVille Activity
     * 
     */
    
    private void ListVille(){
    	Intent i = new Intent(this, ListVille.class);
    	startActivityForResult(i,1);
    	
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	if (requestCode == 1) {
    		if (resultCode == RESULT_OK) {
    			String city = (String) data.getExtras().get("city");
    			this.loadVille(city);
    			//new RequestTask().execute("http://www.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&mode=xml");
    			
    			
    		}
    	}
    }
    
    
    public boolean StatusConnection(){
    	ConnectivityManager conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo actN = conn.getActiveNetworkInfo();
    	return actN != null && actN.isConnected();
    }
    
    //charger ville
    public void loadVille(String city){
    	meteo = new MeteoData(city,this);
    	if(meteo.exist()){
    		meteo.load();
    		degres.setText((CharSequence) meteo.getTemperature());
            humidite.setText((CharSequence) meteo.getHumidity());
            vent.setText((CharSequence) meteo.getSpeed());
            date.setText((CharSequence) meteo.getDate());
            this.city.setText((CharSequence) city);
            SetWeather(meteo.getWeather());
    	}
    	else{
    		if(StatusConnection()){
    		meteo = new MeteoData(city,this);
    		this.city.setText((CharSequence) city);
    		new RequestTask().execute("http://www.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&mode=xml");
    		}
    		else{
    			degres.setText((CharSequence) "Not Available");
                humidite.setText((CharSequence) "Not Available");
                vent.setText((CharSequence) "Not Available");
                date.setText((CharSequence) "Not Available");
                this.city.setText((CharSequence) city);
    		}
    	}
    }
    
    
public class RequestTask extends AsyncTask<String, String, String>{

    @Override
    protected String doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
            HttpGet request = new HttpGet(uri[0]);
            Log.e("uri[0]", request.getURI().toString());
            response = httpclient.execute(request);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                String content = EntityUtils.toString(response.getEntity());
                responseString = content;
                return responseString;
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Log.e("ClientProtocolException", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("IOException", e.getMessage());
        }
        return responseString;
    }

    
    
    @Override
    protected void onPostExecute(String result) {
        if(result != null) {

        	weatherXML = result;

            WeatherM wm = new WeatherM(weatherXML);

            degres.setText((CharSequence) wm.getDegrees());
            humidite.setText((CharSequence) wm.getHumidity());
            vent.setText((CharSequence) wm.getWind());
            //image.setImageResource(R.drawable.cloud);
            meteo.delete();
            //sauvegarde dans la bdd
            meteo.setHumidity(wm.getHumidity());
            meteo.setTemperature(wm.getDegrees());
            meteo.setSpeed(wm.getWind());
            meteo.setWeather(wm.getWeather());
            meteo.save();
            
            
            System.out.println(wm.getWeather());
            SetWeather(wm.getWeather());
            date.setText((CharSequence)meteo.getDate());
        }
    }
}

	public void SetWeather(String weah){
		   if(weah.equals("01d")){
           	image.setImageResource(R.drawable.sund);
           }else if(weah.equals( "01n")){
           	image.setImageResource(R.drawable.moonn);
           }else if(weah.equals("02d")){
           	image.setImageResource(R.drawable.suncloud);
           }

           else if(weah.equals("02n")){
           	image.setImageResource(R.drawable.mooncloud);
               }

       	else if(weah.equals("03d")){
           	image.setImageResource(R.drawable.cloud);
               }

   else if(weah.equals( "03n")){
           	image.setImageResource(R.drawable.cloud);
               }

   else  if(weah.equals("04d")){
           	image.setImageResource(R.drawable.darkcloud);
               }

   else  if(weah.equals("04n")){
           	image.setImageResource(R.drawable.darkcloud);
               }

   else    if(weah.equals( "09d")){
           	image.setImageResource(R.drawable.rain);
               }

       else       if(weah.equals( "09n")){
           	image.setImageResource(R.drawable.rain);
               }

   else       if(weah.equals( "10d")){
           	image.setImageResource(R.drawable.suncloudrain);
               }

else       if(weah.equals("10n")){
           	image.setImageResource(R.drawable.mooncloudrain);
               }

else         if(weah .equals( "11d")){
           	image.setImageResource(R.drawable.lightning);
               }

               else          if(weah.equals( "11n")){
           	image.setImageResource(R.drawable.lightning);
               }

               else          if(weah .equals( "13d")){
           	image.setImageResource(R.drawable.snow);
               }

               else if(weah .equals( "13n")){
           	image.setImageResource(R.drawable.snow);
               }
       		else if(weah .equals( "50d")){
           	image.setImageResource(R.drawable.fog);
               }

       		else if(weah .equals( "50n")){
           	image.setImageResource(R.drawable.fog);
               }
	}

	public void RefreshData(){
		new RequestTask().execute("http://www.openweathermap.org/data/2.5/weather?q=" + meteo.getName() + "&units=metric&mode=xml");
	
	}

}
        
    




