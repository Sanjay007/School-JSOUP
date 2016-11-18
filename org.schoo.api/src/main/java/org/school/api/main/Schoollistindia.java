package org.school.api.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.schoo.api.model.School;
import org.schoo.api.model.School2;
import org.schoo.api.model.SchoolDetail;
//http://www.schoollistindia.com/cbse-schools-india.html

public class Schoollistindia {
	
	private static Set<String> tobeVstd=new HashSet<String>();
	private static ArrayList<String> alreadyVstd=new ArrayList<String>();
	private static String baseurl="http://www.schoollistindia.com/cbse-schools-india.html";
	private static ArrayList<School2> allList=new ArrayList<School2>();
	public static void main( String[] args) throws IOException
	  {
	  
		//getLinksFromPage(baseurl);
		
		/*ArrayList<School2> list=getSchoolFromLink(baseurl);
		System.out.println(getSqsl(list));
		for(School2 MySchool:list){
			System.out.println(MySchool.toString());
		}
		
		
		
		System.out.println("Done!");*/
		
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
    System.out.println("Parsing URL For Link Crawl :" + doc.baseUri());

    for(Element info : doc.select("div#ctl00_ContentPlaceHolder1_school_filter_pagination"))
    {
      System.out.println("Retrieving Links ");
      for(Element a : info.select("a[href]"))
      {
        //System.out.println("Prev Size :" + tobeVstd.size());
    	  System.out.println(a.absUrl("href"));
        tobeVstd.add(a.absUrl("href"));
       // System.out.println("After Size :" + tobeVstd.size());
      }
      System.out.println("Completed Retrieving Links ");
    }
    return tobeVstd;
  }

  public static ArrayList<School2> getSchoolFromLink( String  link) throws IOException
  {
    Document doc = Jsoup.connect(link).timeout(0).get();
    // File input = new File("C://new.html");
    // ArrayList<School> S = new ArrayList<School>();
    // Document doc = Jsoup.parse(input, "UTF-8");
   // for(Element table : doc.select("table"))
    //{
     // if(table.id().equals("ctl00_ContentPlaceHolder1_school_filter_gv_School_List"))
      //{
        for(Element row : doc.select("div#divtbl"))
        {School2 S=new School2();
        S.setType("CBSE");
         for( Element tds : row.select("table")){
        	 for(Element row2 : tds.select("tr"))
             {
        		for( Element td : row.select("td")){
        			
        			for(Element span:td.select("span")){
        				//System.out.println(span.text());
        				String textSpan=span.text();
        				String arr[] =textSpan.split(":");
        				if(arr[0].equals("District")){
        					S.setDistrict(arr[1].trim());
        					
        				}else if(arr[0].equalsIgnoreCase("State")){
        					S.setState(arr[1]);
        				}
        				
        			}
        			for(Element a : td.select("a[href]"))
        			{
        				S.setName(a.text());;
        				S.setSchoolDet(getSchoolDetfromink(a.absUrl("href")));
        			
        			// System.out.println(a.text());
           			// System.out.println(a.absUrl("href")); 
           			 //System.out.println(getSchoolDetfromink(a.absUrl("href")).toString());
           		 
        			}
        			
        		
        			 
        		}
        		 
        		 
        		
             }
         }
          /*School Sc = new School();
          if(!tds.isEmpty())
          {
        	  
            Sc.setName(tds.get(0).text().replace("'", "\\'"));
            Sc.setType(tds.get(1).text());
            Sc.setDistrict(tds.get(2).text());
            Sc.setState(tds.get(3).text().toUpperCase());
            System.out.println(Sc.toString());
            allList.add(Sc);
          }*/
         allList.add(S);
        }
     // }

     // System.out.println(getSqsl(allList));
   // }
    return allList;
  }

	  public static String getSqsl( ArrayList<School2> ins)
	  {
	    StringBuffer Sb = new StringBuffer();
	    for(School2 in : ins)
	    {
	    	SchoolDetail loDet=in.getSchoolDet();
	    	
			Sb.append("insert into School (name,type,district,state,ADDRESS,YEAR_ESTD,BOARD,CLASS,CATEGORY,TYPE_CO_ED,PRE_PRIMARY,MEDIUM) values('"
					+ in.getName() != null?in.getName():null
					+ "','"
					+ in.getType()!=null?in.getType():null
					+ "','"
					+ in.getDistrict().trim()!=null?in.getDistrict().trim():null
					+ "','"
					+ in.getState().trim()!=null?in.getState():null
					+ "','"
					+ loDet.getAddress()!=null?loDet.getAddress():null
					+ "','"
					+ loDet.getYearofEstab()!=null?loDet.getYearofEstab():null
					+ "','"
					+ loDet.getBoard()!=null?loDet.getBoard():null
					+ "','"
					+ loDet.getClasses()!=null?loDet.getClasses():null
					+ "','"
					+ loDet.getCategory()!=null?loDet.getCategory():null
					+ "','"
					+ loDet.getType().toUpperCase()!=null?loDet.getType().toUpperCase():null
					+ "','"
					+ loDet.getPrePrimary()!=null?loDet.getPrePrimary():null
					+ "','"
					+ loDet.getMedium()!=null?loDet.getMedium():null
					+ "') ; \n");
	    }
	    return Sb.toString();
	  }

	  public static SchoolDetail getSchoolDetfromink(String link) throws IOException{
		  
		ArrayList<String> list=new ArrayList<String>();
		SchoolDetail Det=new SchoolDetail();
		
		 Document doc = Jsoup.connect(link).timeout(0).get();
		 for(Element div : doc.select("div#ctl00_ContentPlaceHolder1_TabContainer1_TabPanel1"))
	        {
			
				
			for (Element li : div.select("li")) {			
				if (!li.text().isEmpty()) {
				
					list.add(li.text().replace(":", ""));
					//System.out.println(li.text().replace(":", ""));

				}
			}
			 
	        }
		// System.out.println("List Size"+list.size()+"::::");
/*		 
		 for(int j=0;j<20;j=j+2){
			 System.out.println(j);
		 }
		 */
		 for(int i=0;i<list.size();i++){
			 if(i%2!=0){
				 System.out.println(list.get(i));
				 switch(i){
				 case 1:
					 Det.setAddress(list.get(i));
					 break;
				 case 3:
					 Det.setYearofEstab(list.get(3));
					 break;
				 case 5:
					 Det.setBoard(list.get(5));
					 break;
				 case 7:
					 Det.setClasses(list.get(7));
					 break;
				 case 9:
					 Det.setCategory(list.get(9));
					 break;
				 case 11:
					 Det.setResidential(list.get(11));
					 break;
				 case 13:
					 System.out.println("TEST GET 13"+list.get(13));
					 Det.setType(list.get(13));
					 break;
				 case 15:
					 Det.setPrePrimary(list.get(15));
					 break;
				 case 17:
					 Det.setManagement(list.get(17));
					 break;
				 case 19:
					 Det.setMedium(list.get(19));
					 break;
				 default:
					
					 
				 }
			 }
		 }
		 System.out.println("School_Detail"+Det.toString());
		return Det;
	  }

}
