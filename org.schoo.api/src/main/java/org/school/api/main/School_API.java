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
	private static ArrayList<String> tobeVstd=new ArrayList<String>();
	private static ArrayList<String> alreadyVstd=new ArrayList<String>();
	private static String baseurl="http://www.studyguideindia.com/Schools/public-schools-india.html";
	private static ArrayList<School> allList=new ArrayList<School>();
	public static void main( String[] args) throws IOException
	  {
	  /*
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
	 Document doc = Jsoup
			       .connect("http://www.studyguideindia.com/Schools/public-schools-india.html")
			       .timeout(0).get();*/
	  
	  
    if(tobeVstd.isEmpty()&& alreadyVstd.isEmpty())
    {
      
      getLinksFromPage(baseurl);
    }
    else
    {
      while(!tobeVstd.isEmpty())
      {
        if(!alreadyVstd.contains(tobeVstd.get(0))){
          
          getLinksFromPage(tobeVstd.get(0));
          
          alreadyVstd.add(tobeVstd.get(0));
          tobeVstd.remove(tobeVstd.get(0));
          
        }
       
      }

    }
	  }
	 
  public static ArrayList<String> getLinksFromPage( String  Url) throws IOException
  {
   Document doc = Jsoup.connect(Url).timeout(0).get();
    System.out.println("Parsing URL For Link Crawl :" + doc.baseUri());

    for(Element info : doc.select("div#ctl00_ContentPlaceHolder1_pagination"))
    {
      System.out.println("Retrieving Links ");
      for(Element a : info.select("a[href]"))
      {
        System.out.println("Prev Size :" + tobeVstd.size());
        tobeVstd.add(a.absUrl("href"));
        System.out.println("After Size :" + tobeVstd.size());
      }
      System.out.println("Completed Retrieving Links ");
    }
    return tobeVstd;
  }

  public static ArrayList<School> getSchoolFromLink( String  link) throws IOException
  {
    Document doc = Jsoup.connect(link).timeout(0).get();
    // File input = new File("C://new.html");
    // ArrayList<School> S = new ArrayList<School>();
    // Document doc = Jsoup.parse(input, "UTF-8");
    for(Element table : doc.select("table"))
    {
      if(table.id().equals("ctl00_ContentPlaceHolder1_School_grid"))
      {
        for(Element row : table.select("tr"))
        {
          Elements tds = row.select("td");
          School Sc = new School();
          if(!tds.isEmpty())
          {
            Sc.setName(tds.get(0).text());
            Sc.setType(tds.get(1).text());
            Sc.setDistrict(tds.get(2).text());
            Sc.setState(tds.get(3).text().toUpperCase());
            System.out.println(Sc.toString());
            allList.add(Sc);
          }

        }
      }

      System.out.println(getSqsl(allList));
    }
    return allList;
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
