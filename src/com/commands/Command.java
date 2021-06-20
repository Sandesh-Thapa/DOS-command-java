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
		File file = new File(this.mainPath+ "/" +this.commands.get(1));
		if (!file.exists()) {
            file.mkdir();
        } else {
            System.out.println("A subdirectory or file "+ this.commands.get(1) +" already exists.");
        }
	}
	
	public void createFile() throws IOException {
		File file = new File(this.mainPath+ "/" +this.commands.get(1));
		// Create file
        if (!file.exists()) {
            if (!file.createNewFile()) {
                System.out.println("Something went wrong");
            } 
        } else {
            System.out.println("File already exists");
        }
	}
	
	public String changeDirectory() {
		String newPath = "";
		if (this.commands.size() == 1) {
			return this.mainPath;
		} else if (this.commands.size() == 2){
			if (this.commands.get(1) == "..") {
				String trimPath = "";
				for (int i = this.mainPath.length() - 1; i >= 0; i--) {
		            if (this.mainPath.charAt(i) == '\\') {
		            	trimPath += this.mainPath.charAt(i);
		                break;
		            } else {
		            	trimPath += this.mainPath.charAt(i);
		            }
		        }
		        

		        for (int j = 0; j <= (this.mainPath.length() - trimPath.length())-3; j++) {
		            newPath += this.mainPath.charAt(j);
		        }
		     return newPath;
			}
			else {
				File checkFile = new File(this.mainPath+ "/" +this.commands.get(1));
		        if (checkFile.isFile()) {
		        	newPath = "The directory name is invalid.";
					return newPath;
		        }
				
				
				newPath = this.mainPath + "\\" + this.commands.get(1);
				
				File file = new File(newPath);
				if (file.exists()) {
					return newPath;
				}else {
					newPath = "Not Found";
					return newPath;
				}
			}
		}		
		else {
			newPath = "Error";
			return newPath;
		}
	}
	
	public boolean listDirectory() throws NotRecognizedException {
		boolean success = false;
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
				 success = true;
			 }else {
				 System.out.println("Empty Folder");
				 success = true;
			 }
		} else {
			success = false;
		}
		return success;
		 
	}
	
	public void deleteDirectory() {
		File file = new File(this.mainPath+ "/" +this.commands.get(1));
		
		if (file.isFile()) {
			file.delete();
		}else {
			deleteFolder(file);
		}
		
	}
	
	void deleteFolder(File file) {
		for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                deleteFolder(f);
            } else {
                f.delete();
            }
        }
        file.delete();
     }
	
	
}
