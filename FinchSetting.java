package assignment_3;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class FinchSetting extends ValidationLogic{
private Finch myfinch;
	
	private Finch getFinch() {
		if (this.myfinch == null) 
		{	
			this.myfinch = new Finch();
		}
		return this.myfinch;
	}
	
	public void moveFinch() { 
		
		if (getChecker() == false) {} //if any error occurs in other classes, command is not executed.
		else 
		{
			if (getOption() == 'B' && getChecker() == true) {
				processBacktrackMovement();
			}
	
			else if (getChecker() == true) {	
				//getting values and setting for Finch, time stored is converted to miliseconds.
				getFinch().setWheelVelocities(
						getParsedInput()[2], 
						getParsedInput()[1], 
						getParsedInput()[0] * 1000);
				getFinch().setLED(255, 255, 0); //hard-coded yellow LED for normal movement (F,L,R).
			}
		}
		displayBacktrackLog();
	}

	private void processBacktrackMovement() {
		
 			if (getNumOfBacktrack() > getBacktrackLogSize()) 
			{
				System.out.println("There are not enough commands in "
        						+ "the log, please reinput command." + "\n");
			}
 			
			else if ((getNumOfBacktrack() < 0) 
				||	(getNumOfBacktrack() == 0)) {
				System.out.println("Number of backtrack movement is invalid,"
								+ " please re-enter command." + "\n");
			}
 			
			else if ((getNumOfBacktrack() > 0) 
				&& ((getNumOfBacktrack()) <= (getBacktrackLogSize()))) {
				for (int i = 0; i < getNumOfBacktrack(); i++) {	//parsing input in deque that was previously stored as a String.
					parseInput(getBacktrackLog().split(" ")); 		
					//storing converted input in a temporary array
					int [] backtrack = getParsedInput();
					getFinch().setLED(255, 0, 0); //hard-coded red value for LED
				//Speed values are inversed, time value is not affected.
					getFinch().setWheelVelocities(-backtrack[2], -backtrack[1], backtrack[0] * 1000);
				}	
			}
	}
}