package com.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.NotRecognizedException;

public class Command {
	private String mainPath;
	private List<String> commands = new ArrayList<String>();
	
	public Command(String mainPath, List<String> commands) {
		this.mainPath = mainPath;
		this.commands = commands;
	}
	
	public void makeDirectory() {
		
	}
	
	public void createFile() throws IOException {
		File file = new File(this.commands.get(1));
		
	}
	
	public String changeDirectory() {
		if (this.commands.size() == 1) {
			return this.mainPath;
		} else if (this.commands.size() == 2){
			String newPath = this.mainPath + "\\" + this.commands.get(1);
			
			File file = new File(newPath);
			if (file.exists()) {
				return newPath;
			}else {
				throw new NotRecognizedException("The system cannot find the path specified.");
			}
			
		}		
		else {
			throw new NotRecognizedException("'cd' is not recognized as an internal or external command,\r\n"
					+ "operable program or batch file.");
		}
	}
	
	public void listDirectory() throws NotRecognizedException {
		if (this.commands.size() == 1) {
			String path = this.mainPath; 
			File file = new File(path);
			 File[] ls = file.listFiles();
			 
			 if (ls.length > 0) {
				 System.out.println("Total Items: " + ls.length);
				 for (File s : ls) {
					 System.out.print(s.getName());
					 System.out.println(s.isDirectory() ? "	Dir" : "	File");
				 }
			 }else {
				 System.out.println("Empty Folder");
			 }
		} else {
			throw new NotRecognizedException("'ls' is not recognized as an internal or external command,\r\n"
					+ "operable program or batch file.");
		}
		 
	}
	
	
}
