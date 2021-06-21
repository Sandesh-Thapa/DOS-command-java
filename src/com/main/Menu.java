package com.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.commands.Command;
import com.exceptions.NotRecognizedException;

public class Menu {
	private String mainPath = System.getProperty("user.dir");
	private List<String> commands = new ArrayList<String>();

	public void displayMenu() throws NotRecognizedException {
		while (true) {

			Scanner scan = new Scanner(System.in);
			System.out.println();
			System.out.print(this.mainPath + " ");
			String cmd = scan.nextLine();

			String[] command = cmd.split(" ");
			for (String c : command) {
				commands.add(c);
			}
			try {
				menu(commands.get(0));
			} catch (NotRecognizedException e) {
				this.commands.clear();
				System.out.println(e.getMessage());
			} catch (IOException e) {
				this.commands.clear();
				System.out.println(e.getMessage());
			}
		}
	}

	void menu(String cmd) throws NotRecognizedException, IOException {
		switch (cmd) {
			case "touch":
				if (this.commands.size() == 1) {
					displayException("The syntax of the command is incorrect.");
				} else {
					Command makeFile = new Command(this.mainPath, this.commands);
					makeFile.createFile();
					this.commands.clear();
				}
				break;
			case "mkdir":
				if (this.commands.size() == 1) {
					displayException("The syntax of the command is incorrect.");
				} else {
					Command makedir = new Command(this.mainPath, this.commands);
					makedir.makeDirectory();
					this.commands.clear();
				}
				break;
			case "cd":
				if (this.commands.size() > 2) {
					displayException("'cd' is not recognized as an internal or external command,\r\n"
							+ "operable program or batch file.");
				} else {
					Command changedir = new Command(this.mainPath, this.commands);
					String newPath = changedir.changeDirectory();

					if (newPath == "Error") {
						displayException("'cd' is not recognized as an internal or external command,\r\n"
								+ "operable program or batch file.");
					} else if (newPath == "Not Found") {
						displayException("The system cannot find the path specified.");
					} else if (newPath == "The directory name is invalid.") {
						displayException("The directory name is invalid.");
					}

					else {
						this.mainPath = newPath;
						this.commands.clear();
					}
				}
				break;
			case "ls":
				Command listitem = new Command(this.mainPath, this.commands);
				boolean success = listitem.listDirectory();
				if (!success) {
					displayException("'ls' is not recognized as an internal or external command,\r\n"
							+ "operable program or batch file.");
				} else {
					this.commands.clear();
				}
				break;
			case "del":
				if (this.commands.size() == 1) {
					displayException("The syntax of the command is incorrect.");
				} else {
					Command delItem = new Command(this.mainPath, this.commands);
					delItem.deleteDirectory();
					this.commands.clear();
				}
				break;
			default:
				String temp = cmd;
				displayException("'" + temp + "' is not recognized as an internal or external command,\r\n"
						+ "operable program or batch file.");
		}
	}

	void displayException(String message) {
		this.commands.clear();
		throw new NotRecognizedException(message);
	}
}
