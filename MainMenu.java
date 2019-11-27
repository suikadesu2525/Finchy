package assignment_3;

public class MainMenu {
	
		public static void main (String[] args) {
		FinchSetting finchset = new FinchSetting();
		while (true) {
			finchset.promptUserInput();
			finchset.validateOption();
			finchset.moveFinch();
		}
	}
}