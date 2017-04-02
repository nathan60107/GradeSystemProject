import java.io.IOException;

public class Main {
	static UI aUI;
	static String ID;
	
	/* method main
	 * 創建UI物件，並開始要求輸入
	 * @param args command line的引數
	 * @return 無
	 * @throws IOException 由於aUI為static，因此需要IOException
	 * 
	 * Pseudo code:
	 * 1. 創建UI
	 * 2. 進入迴圈，當輸入Q時才結束
	 * 3. 進入第二個迴圈，要求輸入ID，如果輸入Q則結束程式，輸入正確ID則離開迴圈，錯誤則再次要求輸入
	 * 4. 用得到的正確ID呼叫showWelcomeMsg
	 * 5. 進入loop，要求輸入指令
	 * 
	 * Time estimate: O(n)
	 * Example: Main.main(); 開始程式
	 */
	
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
				else if(retval == 1) aUI.displayArea.setText("ID錯了!\n");
				else break;
			}
			ID = aUI.userInput;
			aUI.showWelcomeMsg(ID);
			loop();
		}
	}
	
	/* method loop
	 * 使用者輸入正確ID後，進入要求輸入指令的loop，並依照不同指令呼叫不同的method
	 * @param 無
	 * @return 無
	 * 
	 * Pseudo code:
	 * 1. 外層為迴圈，只有當輸入為E時才離開
	 * 2. 其他指令的處理，G就呼叫showGrade，R就呼叫showRank，A就呼叫showAverage
	 *    W就呼叫updateWeights，並呼叫checkWeights要求確認，E就離開迴圈，輸入錯誤則印出"指令錯了"，繼續迴圈
	 *    
	 * Time estimate: O(n)
	 * Example: Main.loop(); 使用者輸入G，印出該學生的成績
	 */
	
	private static void loop(){
		int retval;
		while(true){
			retval = aUI.promptCommand();
			if(retval == 0) aUI.aGradeSystem.showGrade(ID);
			else if(retval == 1) aUI.aGradeSystem.showRank(ID);
			else if(retval == 2) aUI.aGradeSystem.showAverage();
			else if(retval == 3) {
				do aUI.aGradeSystem.updateWeights();
				while(!aUI.aGradeSystem.checkWeights());
			}
			else if(retval == 4) break;
			else aUI.displayArea.setText("指令錯了!\n");
		}
	}
}
