package org.school.api.main;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.xerces.parsers.DOMParser;
import org.schoo.api.model.School;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class WebData {
	public static  String restPost(Boolean enableFiddler, String urlStr, String XMLString, String param1Name, String param1Value, String param2Name, String param2Value ) throws Exception {

		 
		HttpClient httpclient = new DefaultHttpClient();
		try {
		  
		  if (enableFiddler) {
		   HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
		   httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		  }

		 
		  HttpPost httpPost = new HttpPost(urlStr);
		  httpPost.addHeader("Accept", "application/xml");
		  httpPost.addHeader("Content-Type", "application/xml");
		  
		  if (!param1Name.equals("")) {
		   httpPost.addHeader(param1Name, param1Value);
		  }
		  if (!param2Name.equals("")) {
		   httpPost.addHeader(param2Name, param2Value);
		  }
		  
		  httpPost.setEntity(new StringEntity(XMLString));
		   
		  HttpResponse response = httpclient.execute(httpPost);
		  int statusCode = response.getStatusLine().getStatusCode();
		  HttpEntity entity = response.getEntity();
		  String responseBody = (String)EntityUtils.toString(entity);
		  return(responseBody);
		} finally {
		  // When HttpClient instance is no longer needed,
		        // shut down the connection manager to ensure
		        // immediate deallocation of all system resources
		        httpclient.getConnectionManager().shutdown();
		}
		}
		
		
	public static  String restGet1(Boolean enableFiddlers, String urlStrs, String param1Names, String param1Values, String param2Names, String param2Values ) throws Exception 
	{

		System.out.println("pinging..."+urlStrs);
		HttpClient httpclient = new DefaultHttpClient();

		 
		try {
		  if (enableFiddlers) {
		   HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
		   httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		  }

		  HttpGet httpget = new HttpGet(urlStrs);
		  
		  httpget.addHeader("Accept", "application/xml");
		  httpget.addHeader("Content-Type", "application/xml");
		  
		  if (!param1Names.equals("")) {
		   httpget.addHeader(param1Names, param1Values);
		  }
		  if (!param2Names.equals("")) {
		   httpget.addHeader(param2Names, param2Values);
		  }
		  
		  HttpResponse response = httpclient.execute(httpget);
		  int statusCode = response.getStatusLine().getStatusCode();
		  HttpEntity entity = response.getEntity();
		  String responseBody = (String)EntityUtils.toString(entity);
		  return(responseBody);
		}catch(Exception Ex)
		{
			Ex.printStackTrace();
			throw new Exception();
		}
		finally {
		  // When HttpClient instance is no longer needed,
		        // shut down the connection manager to ensure
		        // immediate deallocation of all system resources
		        httpclient.getConnectionManager().shutdown();
		}
	} // End restGet
	
	
	
	// Allow for two parameters to be passed to the post request along with the XML string.
		// The most common one will be the "X-SAP-LogonToken" parameter
		public static String restPut(Boolean enableFiddler, String urlStr, String XMLString, String param1Name, String param1Value, String param2Name, String param2Value ) throws Exception {

		 
		HttpClient httpclient = new DefaultHttpClient();
		try {
		  
		  if (enableFiddler) {
		   HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
		   httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		  }

		 
		  HttpPut httpPut = new HttpPut(urlStr);
		  httpPut.addHeader("Accept", "application/xml");
		  httpPut.addHeader("Content-Type", "application/xml");
		  
		  if (!param1Name.equals("")) {
		   httpPut.addHeader(param1Name, param1Value);
		  }
		  if (!param2Name.equals("")) {
		   httpPut.addHeader(param2Name, param2Value);
		  }
		  
		  httpPut.setEntity(new StringEntity(XMLString));
		   
		  HttpResponse response = httpclient.execute(httpPut);
		  int statusCode = response.getStatusLine().getStatusCode();
		  HttpEntity entity = response.getEntity();
		  String responseBody = (String)EntityUtils.toString(entity);
		  return(responseBody);
		} finally {
		  // When HttpClient instance is no longer needed,
		        // shut down the connection manager to ensure
		        // immediate deallocation of all system resources
		        httpclient.getConnectionManager().shutdown();
		}
		}
	
		public static Document convertStringToDom(String domXMLSTring) throws Exception {
			
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(domXMLSTring)));
			
			return (parser.getDocument());
			}
		
		public static String getLogonTokenFromXML(String xmlString) throws Exception {
			Document doc = convertStringToDom(xmlString);
			NodeList nodes = doc.getElementsByTagName("attr");

			for (int i = 0; i < nodes.getLength(); i++) {
			  Element element = (Element) nodes.item(i);
			  
			  // Is this the correct XML token
			  if (element.getAttribute("name").equals("logonToken")) {
			   return(element.getTextContent());
			  }
			}
			return("");
			}
	
		public  String restGet(Boolean enableFiddler, String urlStr, String param1Name, String param1Value, String param2Name, String param2Value ) throws Exception 
		{

			HttpClient httpclient = new DefaultHttpClient();

			 
			try {
			  if (enableFiddler) {
			   HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
			   httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			  }

			  HttpGet httpget = new HttpGet(urlStr);
			  
			  httpget.addHeader("Accept", "application/xml");
			  httpget.addHeader("Content-Type", "application/xml");
			  
			  if (!param1Name.equals("")) {
			   httpget.addHeader(param1Name, param1Value);
			  }
			  if (!param2Name.equals("")) {
			   httpget.addHeader(param2Name, param2Value);
			  }
			  
			  HttpResponse response = httpclient.execute(httpget);
			  int statusCode = response.getStatusLine().getStatusCode();
			  HttpEntity entity = response.getEntity();
			  String responseBody = (String)EntityUtils.toString(entity);
			  return(responseBody);
			}catch(Exception Ex)
			{
				Ex.printStackTrace();
				throw new Exception();
			}
			finally {
			  // When HttpClient instance is no longer needed,
			        // shut down the connection manager to ensure
			        // immediate deallocation of all system resources
			        httpclient.getConnectionManager().shutdown();
			}
		} // End restGet

		
		private static Document convertStringToDocument(String xmlStr) {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	        DocumentBuilder builder;  
	        try 
	        {  
	            builder = factory.newDocumentBuilder();  
	            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
	            return doc;
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	        return null;
	    }
		
		public static ArrayList<School> printNote(NodeList nodeList) {

			ArrayList<School> s=new ArrayList<School>();
			
		    for (int count = 0; count < nodeList.getLength(); count++) {

		    	
			Node tempNode = nodeList.item(count);
			
			System.out.println("creating obj");
			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				 School ss=new School();
				    ss.setName(tempNode.getNodeName()=="name"?tempNode.getTextContent():"nonanme");
					s.add(ss);
			   
					
				if (tempNode.hasChildNodes()) {
					 School ss2=new School();
					    ss2.setName(tempNode.getNodeName()=="name"?tempNode.getTextContent():"nonanme");
						s.add(ss2);
					// loop again if has child nodes
					printNote(tempNode.getChildNodes());

				}

				//System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

			}

			
		
		    }
		    
		    
		    return s;
		}
}
