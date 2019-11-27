package assignment_3;
import java.util.Scanner;
import java.util.ArrayDeque;

public class InputStorage {

	private Scanner user_input = new Scanner(System.in);
	private String input;
	private String [] splittedInput;
	private int [] parsedInput;
	private boolean checker;
	private ArrayDeque<String>backtrackLog = new ArrayDeque<String>();  
	private ArrayDeque<String>inputLog = new ArrayDeque<String>();
	/*	Deque is preferred as the documentation for 
		Stack indicates that Stack is obsolete and replaced
		with similarly-functioning Deque (SE8).*/

	public void promptUserInput() {
		printUserInstructions();
		this.input = user_input.nextLine();
		System.out.println("Your input was: " + input);
		setUserInput(input);
		addInputLog(input);
		setChecker(true);
		prevalidateInputData();
	}
	
	public void prevalidateInputData() {
		
		String [] inputarr = getUserInput().split(" ");
		
		  if (getUserInput().isEmpty()) {
			   System.out.println("no input has been received,"
			  						+ " please try again.");
			   setChecker(false);
		  }
		  else if ((getUserInput().matches("^[\\s]+"))) {
				System.out.println("input is only spaces,"
									+ " please enter a character.");
				setChecker(false);
		  }
		  
		  else if (inputarr[0].length() > 1) {
				System.out.println("command is not valid,"
									+ " please only enter a single character.");
				setChecker(false);
		  }


		  
		  else if (inputarr[0].matches("[0-9]")) { 
				System.out.println("command is a digit,"
									+ " please enter a character.");
				setChecker(false);
		  }
			 
		  else if (inputarr[0].matches(" ")) {
			  	System.out.println("You may have mistyped"
					  + " a space as command, please re-enter command");
			  	setChecker(false);
		  }
		  else {
				setSplittedInput(inputarr);
		  }			
	}
	
	public void parseInput(String[] inputarr) {
		int[] parsedvalue;
		//String to Integer converter for forward
			if (inputarr.length == 3) {
				parsedvalue = new int [inputarr.length];
				 for (int i = 0, j = 1; i < parsedvalue.length-1; i++) 
				 {
					parsedvalue[i] = Integer.parseInt(inputarr[j]);
					j++;
				 }
				 parsedvalue[2] = parsedvalue[1];
			}
		//String to Integer converter for rest of commands (turning, backtrack)
			else {
				parsedvalue = new int [inputarr.length-1];
				for (int i = 0, j = 1; i < parsedvalue.length; i++) 
				{
					parsedvalue[i] = Integer.parseInt(inputarr[j]);
					j++;
				}
			}
		setParsedInput(parsedvalue);
	}

	public void displayInputLog() {
		System.out.println("----------------");
		System.out.println("Your previous input(s) were:");
		for (String i : inputLog) {
			System.out.println(i);
		}
		System.out.println("----------------" + "\n");
	}
	
	public void displayBacktrackLog() {
		System.out.println("-----------------");
		System.out.println("Currently, you have " + this.backtrackLog.size() 
							+ " commands available for backtracking." 
							+ "\n" + "List of commands:");
		for (String i : this.backtrackLog) {
			System.out.println(i);
		}
		System.out.println("-----------------" + "\n");
	}
	
	private void printUserInstructions() {
		//prints list of commands.
		System.out.println("-----------------");
		System.out.println("FINCHMEISTER 9000" + "\n" + "Family-friendly Finch navigator. "
							+ "Please note that speed value -30 to 30 is very unnoticable "
							+ "and may lead to unwanted results.");
		System.out.println("GUIDELINES:" + "\n" + "Enter your command in a single line, separated with space." );
		System.out.println("List of command options (Options are not case sensitive): ");
		System.out.println("F: duration (0 to 6) and speed (-100 to 100)"  
							 + "\n" + " Ex: F 1 100 / f 1 -80");
		System.out.println("L / R: duration (0-6), right wheel speed (-100 to 100) "
						   	+ "and left wheel speed (-100 to 100) "
							+ "\n" + "Ex: L 1 100 -50 or R 1 -50 100");
		System.out.println("B: Retraces previous correct commands." + "\n"
							+ "Follow the command with a value to set how many commands will be retraced."
							+ "\n" + "Ex: B 2 (You need to have enough commands to do this).");
		System.out.println("C: Displays a history of your inputs. Ex: C");
		System.out.println("S: ends program ");
		System.out.println("-----------------" + "\n");
	}
	
	public void setUserInput(String userInput) {
		this.input = userInput;
	}
	
	public String getUserInput() {
		return this.input;
	}
	
	private void setParsedInput(int[] intinput) {
		this.parsedInput = intinput;
	}
	
	public int[] getParsedInput() {
		return this.parsedInput;
	}	
	private void setSplittedInput(String[] inputArray) {
		this.splittedInput = inputArray;
	}
	
	public String[] getSplittedInput() {
		return this.splittedInput;
	}
	
	public void setChecker(boolean checker) {
		this.checker = checker;
	}
	
	public boolean getChecker() {
		return this.checker;
	}
	
	public String getBacktrackLog() {
		System.out.println("Now backtracking: " + this.backtrackLog.peek());
		return this.backtrackLog.pop();
	}
	
	public void addBacktrackLog(String input) {
		this.backtrackLog.push(input);
	}
		
	private void addInputLog(String input) {
		this.inputLog.add(input);
	}
		
	public int getBacktrackLogSize() {
		return this.backtrackLog.size();
	}
}