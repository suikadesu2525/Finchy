package assignment_3;


public class ValidationLogic extends InputStorage {
private int numOfBacktrack;
private char option;

	public char getOption()	{
		//hard-coded logic to accept and return lowercase command
		this.option = getSplittedInput()[0].charAt(0);
		if (Character.isLowerCase(this.option) == true) 
		{
			this.option = Character.toUpperCase(this.option);
		}
		return this.option;
	}

	public void setNumOfBacktrack(int log_num) 
	{
		this.numOfBacktrack = log_num;
	}
	
	public int getNumOfBacktrack() 
	{
		return this.numOfBacktrack;
	}
	
	//handles the logic for commands.
	public void validateOption() 
	{ 
		if (getChecker() == false) {} //do nothing.
	else {	
		try {
	        switch (getOption()) {
	        case 'F':
	        	parseInput(getSplittedInput());
	        		if (getSplittedInput().length == 3) 
	        		{
	        			validateMovement();
	        		}
	        			else 
	        			{
	        				System.out.println("Forward command format is not as specified, "
	        									+ "please re-enter command.");
	        				setChecker(false);
	        				
	        			}
	                 	break;
	        case 'L': case 'R':
	        	parseInput(getSplittedInput());
	        		if (getSplittedInput().length == 4) {
	        			validateTurn(getOption());
	        		}
	        			
	        			else {
	        				System.out.println("Turn command format is not as specified, "
	        									+ "please re-enter command.");
	        				setChecker(false);
	        		}
	                 	break;
	          	
	        case 'B':
	        		if (getSplittedInput().length == 2) {
		        		parseInput(getSplittedInput());
		        		setNumOfBacktrack(getParsedInput()[0]);
		        		setChecker(true);
	        		}
	        			else {
		        			System.out.println("Backtrack command is not as specified, "
		        								+ "please re-enter command");
		        			setChecker(false);
	        		}
	        			break;
	        
	        case 'S': 	System.out.println("System is quitting now...");
	        			System.exit(0);
	        			break;
	        			
	        case 'C': 	displayInputLog();
	        			setChecker(false);
	        			break;
	        default: 	System.out.println("Command does not exist, please try again.");
	        			setChecker(false);
	                 	break;
	        } //end of switch statement
		} //end of try
		catch (NumberFormatException e) {
			System.out.println("digit(s) cannot be processed, "
								+ "please re-enter valid digit(s)");
			setChecker(false);
		}
		catch (StringIndexOutOfBoundsException e) {
			System.out.println("An error has been detected in your input," 
								+ " please make sure command is typed as specified format.");
			setChecker(false);
		}
	}
}
	//used for turning command only.
	private void validateTurn(char option) {
		
		if (getParsedInput()[1] == getParsedInput()[2]) {
			System.out.println("Both speeds cannot be equal,"
					+ " please re-enter command");
			setChecker(false);
	}
	
		else if (option == 'L') { 
			if (getParsedInput()[1] > getParsedInput()[2]) 
			{
				validateMovement();
			} else {
					System.out.println("Left wheel speed cannot be larger than right"
						+ " wheel speed, please re-enter command");
					setChecker(false);
					}
		}
		
		else if (option == 'R') 
		{
			if (getParsedInput()[2] > getParsedInput()[1]) 
			{
				validateMovement();
			}
			else {
				
				System.out.println("Right wheel speed cannot be larger than left"
						+ " wheel speed, please re-enter command");
				setChecker(false);
			}
	}
		
}
	
	//general logic used for all movement commands. correct input will be stored in a separate deque
	private void validateMovement() 
	{
		
			if ((getParsedInput()[1]  > 100) 
			|| (getParsedInput()[1] < -100)) {
			System.out.println("left speed value is not valid (-100 to 100),"
								+ " please re-enter value"
								+ "\n" + "left speed value was " + getParsedInput()[1]);
			setChecker(false);
		}
			else if ((getParsedInput()[2] > 100) 
			|| (getParsedInput()[2]  < -100)) {
			System.out.println("right speed value is not valid (-100 to 100),"
								+ " please re-enter value");
			setChecker(false);
		}
			else if ((getParsedInput()[0] < 1)
			|| (getParsedInput()[0] > 6)) {
				System.out.println("duration value is not valid (1-6),"
								+ " please re-enter value.");
			setChecker(false);
		}		
		else { 
			setChecker(true);
			addBacktrackLog(getUserInput());
		}		
	}
}