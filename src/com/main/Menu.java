package com.main;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.commands.Command;
import com.exceptions.NotRecognizedException;

public class Menu {
	private String mainPath = System.getProperty("user.dir");
	private List<String> commands = new ArrayList<String>();

	public void displayMenu() throws NotRecognizedException {
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			System.out.println();
			System.out.print(this.mainPath + " ");
			String cmd = scan.nextLine();
			
			String[] command = cmd.split(" ");
			for (String c:command) {
				commands.add(c);
			}	
			try {
				menu(commands.get(0));
			}catch (NotRecognizedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	void menu(String cmd) throws NotRecognizedException{
		switch (cmd) {
		case "mkdir":
			System.out.println("Valid Command");
			this.commands.clear();
			break;
		case "cd":
			if (this.commands.size() > 2) {
				this.commands.clear();
				throw new NotRecognizedException("'cd' is not recognized as an internal or external command,\r\n"
						+ "operable program or batch file.");
			}else {
				Command changedir = new Command(this.mainPath, this.commands);
				String newPath = changedir.changeDirectory();
				this.mainPath = newPath;
				this.commands.clear();
			}
			break;
		case "ls":
			Command listitem = new Command(this.mainPath, this.commands);
			listitem.listDirectory();
			this.commands.clear();
			break;
		default:
			String temp = cmd;
			this.commands.clear();
			throw new NotRecognizedException("'"+temp+"' is not recognized as an internal or external command,\r\n"
					+ "operable program or batch file.");			
		}
	}
}
