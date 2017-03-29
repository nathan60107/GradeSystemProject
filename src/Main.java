import java.io.IOException;

public class Main {
	static UI aUI;
	//static int retval;
	static String ID;

	public static void main(String[] args) throws IOException {
		aUI = new UI();
		int retval;
		while(true){
			aUI.displayArea.setText(null);
			while(true){
				retval = aUI.promptID();
				if(retval == 0){
					aUI.showFinishMsg();
					return;
				}
				else if(retval == 1) aUI.displayArea.setText("ID¿ù¤F!\n");
				else break;
			}
			ID = aUI.userInput;
			aUI.showWelcomeMsg(ID);
			loop();
		}
	}
	
	private static void loop(){
		int retval;
		while(true){
			retval = aUI.promptCommand();
			if(retval == 5 ) aUI.displayArea.setText("«ü¥O¿ù¤F!\n");
			else if(retval == 4) break;
			else if(retval == 3) aUI.aGradeSystem.updateWeights();
			else if(retval == 2) aUI.aGradeSystem.showAverage();
			else if(aUI.checkID(ID))
			{
				if(retval == 0) aUI.aGradeSystem.showGrade(ID);
				else aUI.aGradeSystem.showRank(ID);
			}
		}
	}

}
