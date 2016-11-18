package org.school.api.main;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.schoo.api.model.School;
import org.schoo.api.model.School2;

public class School_API {
	final static Logger logger = Logger.getLogger(School_API.class);
	private static Set<String> tobeVstd=new HashSet<String>();
	private static ArrayList<String> alreadyVstd=new ArrayList<String>();
	private static String baseurl="http://www.studyguideindia.com/Schools/public-schools-india.html";
	private static HashSet<School>  allList=new HashSet<School>();
	public static void main( String[] args) throws IOException
	  {
		
		long lStartTime = new Date().getTime();
		getSchoolFromLink(baseurl);
		
	
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
			       .timeout(0).get();
			       */
	 
		Collections.synchronizedSet(tobeVstd);
	  
    if(tobeVstd.isEmpty()&& alreadyVstd.isEmpty())
    {
      
      getLinksFromPage(baseurl);
    }
    
    
    	 int count=0;
    	 
    	 while(alreadyVstd.size()!=tobeVstd.size())
    	 {
    		 
    	System.out.println(tobeVstd);
    	String url = null ;
    	
    	  
    	//access via new for-loop
    	  for(Object object : tobeVstd) {
    	       url=(String) object;
				if (!alreadyVstd.contains(url)) {
					alreadyVstd.add(url);
					count++;
				}
    	  }
    	  
    	  for(String S:alreadyVstd){
    		  getLinksFromPage(S);
    	  }
    	  
    	  System.out.println("Count"+count);
    	  System.out.println("tobe"+tobeVstd.size());
    	  System.out.println("already"+alreadyVstd.size());
    	  System.out.println(tobeVstd);

    	 }
     
    	 for(String S:alreadyVstd){
    		 getSchoolFromLink(S);
   		
   	  }
    	riteToFile(getSqsl(allList));
    	 long endtime = new Date().getTime();
    	 long op=endtime-lStartTime;
    	 System.out.println("Elapsed time in milliseconds: " + op);
	  }
	 
static void riteToFile(String s){
		
		try {

			

			File file = new File("D://sa2.sql");

			
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file,true);
	    	  BufferedWriter bw = new BufferedWriter(fw);
	    	  PrintWriter pw = new PrintWriter(bw);
	          //This will add a new line to the file content
	    	  pw.println("");
	          /* Below three statements would add three 
	           * mentioned Strings to the file in new lines.
	           */
	    	  pw.println(s);
	    	 
	    	  pw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
  public static Set<String> getLinksFromPage( String  Url) throws IOException
  {
   Document doc = Jsoup.connect(Url).timeout(0).get();
   logger.debug("Parsing URL For Link Crawl :" + doc.baseUri());
   System.out.println("Parsing URL For Link Crawl :" + doc.baseUri());

    for(Element info : doc.select("div#ctl00_ContentPlaceHolder1_pagination"))
    {
      System.out.println("Retrieving Links ");
      for(Element a : info.select("a[href]"))
      {
        //System.out.println("Prev Size :" + tobeVstd.size());
        tobeVstd.add(a.absUrl("href"));
       // System.out.println("After Size :" + tobeVstd.size());
      }
      logger.debug("Completed Retrieving Links ");
      System.out.println(("Completed Retrieving Links "));
    }
    return tobeVstd;
  }

  public static HashSet<School> getSchoolFromLink( String  link) throws IOException
  {
    Document doc = Jsoup.connect(link).timeout(0).get();
    // File input = new File("C://new.html");
    // ArrayList<School> S = new ArrayList<School>();
    // Document doc = Jsoup.parse(input, "UTF-8");
    int count=0;
    for(Element table : doc.select("table"))
    {
      if(table.id().equals("ctl00_ContentPlaceHolder1_School_grid"))
      {
        for(Element row : table.select("tr"))
        {
          Elements tds = row.select("td");
          School Sc = new School();
          count=count+1;
          if(!tds.isEmpty())
          {
        	  String s=tds.get(0).text().replace("'", "\\'");
        	  Charset charset = Charset.forName("UTF-8");
        	  s = charset.decode(charset.encode(s)).toString();
        	 s= s.replace("\\xEF \\xBF \\xBD", "");
            Sc.setName(s);
            Sc.setType(tds.get(1).text());
            Sc.setDistrict(tds.get(2).text());
            Sc.setState(tds.get(3).text().toUpperCase());
            //System.out.println(Sc.toString());
            
          }
          for(Element a : tds.select("a[href]"))
			{
        	  System.out.println(a.absUrl("href"));
        	  Sc=getSchoolDetail(a.absUrl("href"),Sc);
			}
          
        	  allList.add(Sc);
          
          
        }
      }

     
    }
    logger.debug("Link:"+link +"School Count "+allList.size());
    return allList;
  }

  
  public static School getSchoolDetail(String url,School schol) throws IOException{
	  long startTime = System.nanoTime();
	  logger.debug("Detail :Link "+url);
	  Document doc = Jsoup.connect(url).timeout(0).get();
	  for(Element row : doc.select("div#clg_dtl"))
      {
		  for(Element trs:row.select("tr")){
			  
			 /* for(Element tds:trs.select("td")){
				  if(tds.text().equals("Address")){
					  
				  }
			  }*/
			  Elements tds=trs.select("td");
			  logger.debug("Row Traversed Getting TD values");
			  logger.debug("1st Element"+tds.get(0));
			  logger.debug("2nd Element"+tds.get(1));
			  
			  if(tds.size()>0 )
			  {
				  
				  switch(tds.get(0).text()){
				  case "Address":
					  schol.setAddress(tds.get(1).text().trim().replace("'", "\\'"));
					  String s=schol.getAddress();
					  String pattern = "(\\d{6})";
					  Pattern r = Pattern.compile(pattern);

				      // Now create matcher object.
				      Matcher m = r.matcher(s);
				      if (m.find( )) {
				          schol.setPincode(m.group(1));
				       }else{
				    	   schol.setPincode("999");
				       }
				    		  
					  break;
				  case "Phone":
					  schol.setPhone(tds.get(1).text().trim());
					  break;
				  case "E-mail":
					  schol.setEmail(tds.get(1).text().trim());
					  break;
				  case "Website":
					  schol.setWebsite(tds.get(1).text().trim());
					  break;
				  case "Gender":
					  schol.setCoed(tds.get(1).text().trim());
					  default:
						  logger.debug("N/A"+tds.get(0).text()+":"+tds.get(1).text());
				  }
				  
			  }
			 //System.out.println( tds.get(0).text());
			  //System.out.println(tds.get(1).text());
			  
		  }
      }
	  
	  long endTime = System.nanoTime();
	  logger.debug("Detail "+(endTime - startTime) + " ns"); 
	  return schol;
  }
  
	  public static String getSqsl( HashSet<School> ins)
	  {
	    StringBuffer Sb = new StringBuffer();
	    for(School in : ins)
	    {
if(in!=null || in.getName()!=null){
	Sb.append("insert into School (name,type,district,state,address,pin,phone,email,website,coed) values('" + in.getName() + "','" + in.getType() + "','" + in.getDistrict()+ "','"+ in.getState()+ "','"	+ in.getAddress()+ "','"+in.getPincode()+"','"+ in.getPhone()	+ "','"	+ in.getEmail()+ "','"+ in.getWebsite()	+ "','"	+ in.getCoed() + "') ;\n");	
}
	      
	    }
	    return Sb.toString();
	  }

}
