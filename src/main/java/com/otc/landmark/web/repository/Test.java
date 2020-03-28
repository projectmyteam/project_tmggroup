package com.otc.landmark.web.repository;

import java.io.File;

public class Test {
	public static void main(String[] args) {
		 try { 
			  
	            // Create a file object 
	            File f = new File("program.txt"); 
			/*
			 * if(!f.exists()) { f.createNewFile(); }
			 */
	  
	            // Get the absolute path of file f 
	            String absolute = f.getAbsolutePath(); 
	  
	            // Display the file path of the file object 
	            // and also the file path of absolute file 
	            System.out.println("Original  path: "
	                               + f.getPath()); 
	            System.out.println("Absolute  path: "
	                               + absolute); 
	        } 
	        catch (Exception e) { 
	            System.err.println(e.getMessage()); 
	        } 
	}
}
