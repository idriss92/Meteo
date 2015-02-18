package com.partiels.meteo.algo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
//import org.json.JSONObject;

public class WeatherM {

  private Document doc = null;
  private Element root = null;
  private DecimalFormat df;
  //private JSONObject current = null;

  public WeatherM(String xml) {
      df = new DecimalFormat ( ) ;
      df.setMaximumFractionDigits ( 2 ) ; //arrondi à 2 chiffres apres la virgules
      df.setMinimumFractionDigits ( 2 ) ;

      try {
          InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
          SAXBuilder sb=new SAXBuilder();
          doc = sb.build(is);
          root = doc.getRootElement();
          
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public String getDegrees() {
      return "" + df.format(Double.valueOf(root.getChild("temperature").getAttributeValue("value")));
  }

  public String getHumidity() {
      return root.getChild("humidity").getAttributeValue("value");
  }

  public String getWind() {
      return root.getChild("wind").getChild("speed").getAttributeValue("value");
  }
  
  public String getWeather() {
      return root.getChild("weather").getAttributeValue("icon");
  }

  static void affiche(Document docy)
  {
      try {
          XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
          sortie.output(docy, System.out);
      }
      catch (IOException e){
          System.err.println("Erreur lors de l'affichage : " + e);
      }
  }
}
