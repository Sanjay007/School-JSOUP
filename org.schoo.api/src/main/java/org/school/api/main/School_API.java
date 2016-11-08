package org.school.api.main;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.schoo.api.model.School;

public class School_API {
	 public static void main( String[] args) throws IOException
	  {/*
	   System.setProperty("http.proxySet", "true");
	   System.setProperty("java.net.useSystemProxies", "true");
	   System.setProperty("http.proxyHost", "143.199.237.62");
	   System.setProperty("http.proxyPort", "8080");
	   System.setProperty("http.proxyUser", "snjydv");
	   System.setProperty("http.proxyPassword", "#April007");
	   Document doc = Jsoup
	       .connect("http://www.studyguideindia.com/Schools/public-schools-india.html")
	       .timeout(0).get();
	   
	   // prints HTML data
	   */
		 String baseurl="http://www.studyguideindia.com/Schools/public-schools-india.html";
		 
		 ArrayList<String> tobeVstd=new ArrayList<String>();
		 
		 ArrayList<String> alreadyVstd=new ArrayList<String>();
		 
		 Document doc = Jsoup
			       .connect("http://www.studyguideindia.com/Schools/public-schools-india.html")
			       .timeout(0).get();
		 
		for( Element info : doc.select("div#ctl00_ContentPlaceHolder1_pagination")){
			for(Element a :info.select("a[href]")){
				tobeVstd.add(a.absUrl("href"));
			}
			
		}
		 
		 
	    //File input = new File("C://new.html");
	    ArrayList<School> S = new ArrayList<School>();
	    //Document doc = Jsoup.parse(input, "UTF-8");
	    for(Element table : doc.select("table"))
	    {
	    	if(table.id().equals("ctl00_ContentPlaceHolder1_School_grid")){
	      for(Element row : table.select("tr"))
	      {
	        Elements tds = row.select("td");
	        School Sc = new School();
	        if(!tds.isEmpty()){
	        Sc.setName(tds.get(0).text());
	        Sc.setType(tds.get(1).text());
	        Sc.setDistrict(tds.get(2).text());
	        Sc.setState(tds.get(3).text().toUpperCase());
	        System.out.println(Sc.toString());
	        S.add(Sc);
	        }

	      }
	    	}

	      System.out.println(getSqsl(S));
	    }
	  }

	  public static String getSqsl( ArrayList<School> ins)
	  {
	    StringBuffer Sb = new StringBuffer();
	    for(School in : ins)
	    {

	      Sb.append("insert into School (name,type,district,state) values('"
	                + in.getName() + "','" + in.getType() + "','" + in.getDistrict()
	                + "','" + in.getState() + "') ; \n");
	    }
	    return Sb.toString();
	  }

}
