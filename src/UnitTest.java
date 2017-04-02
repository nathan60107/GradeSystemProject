import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitTest {

	Grades aGrade = null;
	UI aUI = null;
	GradeSystems aGradeSystem = null;
	
	/* method setUp
	 * 初始化 UnitTest 會用到的物件
	 */
	
	@Before
	public void setUp() throws Exception {
		aUI = new UI();
		aGradeSystem = new GradeSystems(aUI);
		aGrade = new Grades();
	}

	/* method tearDown
	 * 在結束處理動作後， delete UnitTest 會用到的物件
	 */
	
	@After
	public void tearDown() throws Exception {
		aUI = null;
		aGradeSystem = null;
		aGrade = null;
	}

	/* testCheckID
	 * case1: 輸入正確ID ， 應該 return true
	 * case2: 輸入錯誤ID ， 應該 return false
	 */
 
	@Test
	public void testCheckID1() {
		assertEquals(true, aUI.checkID("955002056"));
	}
	 
	@Test
	public void testCheckID2() {
		assertEquals(false, aUI.checkID("888888888"));
	}

	/* testPromptCommand
	* promptCommand 輸入G回傳0 ， 輸入R回傳1 ， 輸入A回傳2 ， 輸入W回傳3 ， 輸入E回傳4 ， 其他則回傳5
	* case1: 輸入G ， 應該 return 0
	* case2: 輸入R ， 應該 return 1
	* case3: 輸入A ， 應該 return 2
	* case4: 輸入W ， 應該 return 3
	* case5: 輸入E ， 應該 return 4
	* case6: 輸入123456789 ，  應該 return 5
	*/
	 
	@Test
	public void testPromptCommand1() {
		aUI.enterPressed = true;
		aUI.textArea.setText("G");
		assertEquals(0, aUI.promptCommand());
	}
	
	@Test
	public void testPromptCommand2() {
		aUI.enterPressed = true;
		aUI.textArea.setText("R");
		assertEquals(1, aUI.promptCommand());
	}
	 
	@Test
	public void testPromptCommand3() {
		aUI.enterPressed = true;
		aUI.textArea.setText("A");
		assertEquals(2, aUI.promptCommand());
	}
	 
	@Test
	public void testPromptCommand4() {
		aUI.enterPressed = true;
		aUI.textArea.setText("W");
		assertEquals(3, aUI.promptCommand());
	}
	 
	@Test
	public void testPromptCommand5() {
		aUI.enterPressed = true;
		aUI.textArea.setText("E");
		assertEquals(4, aUI.promptCommand());
	}
	 
	@Test
	public void testPromptCommand6() {
		aUI.enterPressed = true;
		aUI.textArea.setText("123456789");
		assertEquals(5, aUI.promptCommand());
	}
	 
	/* testPromptID
	 * promptID 如果輸入Q會回傳0 ， 輸入錯誤ID會回傳1 ， 輸入正確ID會回傳2
	 * case1: 輸入"Q" ， 應該 return 0
	 * case2: 輸入錯誤ID ， 應該 return 1
	 * case3: 輸入正確ID ， 應該 return 2
	 */
	
	@Test
	public void testPromptID1() {
		aUI.enterPressed = true;
		aUI.textArea.setText("Q");
		assertEquals(0, aUI.promptID());
	}
	 
	@Test
	public void testPromptID2() {
		aUI.enterPressed = true;
		aUI.textArea.setText("888888888");
		assertEquals(1, aUI.promptID());
	}
	 
	@Test
	public void testPromptID3() {
		aUI.enterPressed = true;
		aUI.textArea.setText("955002056");
		assertEquals(2, aUI.promptID());
	}
	
	/* testShowFinishMsg
	 * 由於結果都一樣，只是印出 "結束了" 到displayArea上，所以只用一個case
	 * case1: 應該印出 "結束了" 到displayArea上
	 */
	 
	@Test
	public void testShowFinishMsg() {
		aUI.showFinishMsg();
		assertEquals("結束了\n", aUI.displayArea.getText());
	}
	
	/* testShowWelcomeMsg
	 * case1: ID:955002056 ， 應該印出 "Welcome  許文馨" 到displayArea上
	 * case2: ID:962001044 ， 應該印出 "Welcome  凌宗廷" 到displayArea上
	 */
	
	@Test
	public void testShowWelcomeMsg1() {
		aUI.showWelcomeMsg("955002056");
		assertEquals("Welcome  許文馨\n\n", aUI.displayArea.getText());
	}
	
	@Test
	public void testShowWelcomeMsg2() {
		aUI.showWelcomeMsg("962001044");
		assertEquals("Welcome  凌宗廷\n\n", aUI.displayArea.getText());
	}
	
	/* testGetInput
	 * 先設定好textArea的輸入，然後呼叫getInput，看userInput是否有吃到
	 * case1: 輸入"Q"
	 * case2: 輸入"955002056"
	 */
	
	@Test
	public void testGetInput1() {
		aUI.enterPressed = true;
		aUI.textArea.setText("Q");
		aUI.getInput();
		assertEquals("Q", aUI.userInput);
	}
	
	@Test
	public void testGetInput2() {
		aUI.enterPressed = true;
		aUI.textArea.setText("955002056");
		aUI.getInput();
		assertEquals("955002056", aUI.userInput);
	}
	
	
	
	/* testContainsID
	 * case1: ID:955002056 ， 有這個ID，應回傳true
	 * case2: ID:888888888 ， 沒這個ID，應回傳false
	 */
	
	@Test
	public void testContainsID1() {
		assertEquals(true, aGradeSystem.containsID("955002056"));
	}
	
	@Test
	public void testContainsID2() {
		assertEquals(false, aGradeSystem.containsID("888888888"));
	}
	 
	/* testShowGrade
	 * case1: ID:955002056 ， 應該印出許文馨的五個成績和totalGrade
	 * lab1:88, lab2:92, lab3:88, midTerm:98, finalExam:91, totalGrade:93
	 * 
	 * case2: ID:962001051 ， 應該印出李威廷的五個成績和totalGrade
	 * lab1:81, lab2:32, lab3:50, midTerm:90, finalExam:93, totalGrade:81
	 */
	
	@Test
	public void testShowGrade1() {
		aGradeSystem.showGrade("955002056");
		String temp = "許文馨成績:"
					+ "\nlab1:                88"
					+ "\nlab2:                92"
					+ "\nlab3:                88"
					+ "\nmid-term:       98"
					+ "\nfinal exam:     91"
					+ "\ntotal grade:    93\n\n";
		assertEquals(temp, aUI.displayArea.getText());
	}
	 
	@Test
	public void testShowGrade2() {
		aGradeSystem.showGrade("962001051");
		String temp = "李威廷成績:"
					+ "\nlab1:                81"
					+ "\nlab2:                32*"
					+ "\nlab3:                50*"
					+ "\nmid-term:       90"
					+ "\nfinal exam:     93"
					+ "\ntotal grade:    81\n\n";
		assertEquals(temp, aUI.displayArea.getText());
	}
	 
	/* testShowRank
	 * case1: ID:955002056 ， 應該印出 "許文馨排名第9"  到displayArea上
	 * case2: ID:962001044 ， 應該印出 "李威廷排名第63" 到displayArea上
	 */
	
	@Test
	public void testShowRank1() {
		aGradeSystem.showRank("955002056");
		assertEquals("許文馨排名第9\n\n", aUI.displayArea.getText());
	}
	
	@Test
	public void testShowRank2() {
		aGradeSystem.showRank("962001051");
		assertEquals("李威廷排名第63\n\n", aUI.displayArea.getText());
	}
	 
	/* testShowAverage
	 * 因為全班平均的結果都一樣，所以只用一個case
	 * case1: 應該印出全班五個成績的個別平均
	 * lab1:90, lab2:87, lab3:89, midTerm:89, finalExam:89
	 */
	
	@Test
	public void testAverage() {
		aGradeSystem.showAverage();
		String temp = "全班平均:\n"
					+ "lab1:                90\n"
					+ "lab2:                87\n"
					+ "lab3:                89\n"
					+ "mid-term:       89\n"
					+ "final exam:     89\n\n";
		assertEquals(temp, aUI.displayArea.getText());
	}
	
	/* testCheckWeights
	 * case1: 輸入"Y"，表示確認，應該return true
	 * case2: 輸入"N"，表示否定，應該return false
	 */
	
	@Test
	public void testCheckWeights1() {
		aUI.enterPressed = true;
		aUI.textArea.setText("Y");
		assertEquals(true, aGradeSystem.checkWeights());
	}
	
	@Test
	public void testCheckWeights2() {
		aUI.enterPressed = true;
		aUI.textArea.setText("N");
		assertEquals(false, aGradeSystem.checkWeights());
	}
	
	/* testUpdateWeights
	 * case1: 輸入"18 19 20 21 22"，應該把這五個數字暫存到weights[5~9]
	 * case2: 輸入"22 21 20 19 18"，應該把這五個數字暫存到weights[5~9]
	 */
	 
	@Test
	public void testUpdateWeights1() {
		aUI.enterPressed = true;
		aUI.textArea.setText("18 19 20 21 22");
		aGradeSystem.updateWeights();
		assertEquals(18, aGradeSystem.weights[5]);
		assertEquals(19, aGradeSystem.weights[6]);
		assertEquals(20, aGradeSystem.weights[7]);
		assertEquals(21, aGradeSystem.weights[8]);
		assertEquals(22, aGradeSystem.weights[9]);
	}
	 
	@Test
	public void testUpdateWeights2() {
		aUI.enterPressed = true;
		aUI.textArea.setText("22 21 20 19 18");
		aGradeSystem.updateWeights();
		assertEquals(22, aGradeSystem.weights[5]);
		assertEquals(21, aGradeSystem.weights[6]);
		assertEquals(20, aGradeSystem.weights[7]);
		assertEquals(19, aGradeSystem.weights[8]);
		assertEquals(18, aGradeSystem.weights[9]);
	}
	 
	 /* testCalculateTotalGrade
	  * case 1: lab1:83(20%), lab2:77(30%), lab3:90(10%), midTerm:94(10%), finalExam:88(30%)
	  * 手算totalGrade為84.5，四捨五入後應為85
	  * 
	  * case 2: lab1:70(18%), lab2:65(19%), lab3:88(20%), midTerm:99(21%), finalExam:77(22%)
	  * 手算totalGrade為80.28，四捨五入後應為80
	  */
	 
	@Test
	public void testCalculateTotalGrade1() {
		aGrade.lab1 = 83;
		aGrade.lab2 = 77;
		aGrade.lab3 = 90;
		aGrade.midTerm = 94;
		aGrade.finalExam = 88;
		int[] weights = {20, 30, 10, 10, 30};
		aGrade.calculateTotalGrade(weights);
		assertEquals(85, aGrade.totalGrade);
	}
	
	@Test
	public void testCalculateTotalGrade2() {
		aGrade.lab1 = 70;
		aGrade.lab2 = 65;
		aGrade.lab3 = 88;
		aGrade.midTerm = 99;
		aGrade.finalExam = 77;
		int[] weights = {18, 19, 20, 21, 22};
		aGrade.calculateTotalGrade(weights);
		assertEquals(80, aGrade.totalGrade);
	}
}
