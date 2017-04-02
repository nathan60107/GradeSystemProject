import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitTest {

	Grades aGrade = null;
	UI aUI = null;
	GradeSystems aGradeSystem = null;
	
	/* method setUp
	 * ��l�� UnitTest �|�Ψ쪺����
	 */
	
	@Before
	public void setUp() throws Exception {
		aUI = new UI();
		aGradeSystem = new GradeSystems(aUI);
		aGrade = new Grades();
	}

	/* method tearDown
	 * �b�����B�z�ʧ@��A delete UnitTest �|�Ψ쪺����
	 */
	
	@After
	public void tearDown() throws Exception {
		aUI = null;
		aGradeSystem = null;
		aGrade = null;
	}

	/* testCheckID
	 * case1: ��J���TID �A ���� return true
	 * case2: ��J���~ID �A ���� return false
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
	* promptCommand ��JG�^��0 �A ��JR�^��1 �A ��JA�^��2 �A ��JW�^��3 �A ��JE�^��4 �A ��L�h�^��5
	* case1: ��JG �A ���� return 0
	* case2: ��JR �A ���� return 1
	* case3: ��JA �A ���� return 2
	* case4: ��JW �A ���� return 3
	* case5: ��JE �A ���� return 4
	* case6: ��J123456789 �A  ���� return 5
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
	 * promptID �p�G��JQ�|�^��0 �A ��J���~ID�|�^��1 �A ��J���TID�|�^��2
	 * case1: ��J"Q" �A ���� return 0
	 * case2: ��J���~ID �A ���� return 1
	 * case3: ��J���TID �A ���� return 2
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
	 * �ѩ󵲪G���@�ˡA�u�O�L�X "�����F" ��displayArea�W�A�ҥH�u�Τ@��case
	 * case1: ���ӦL�X "�����F" ��displayArea�W
	 */
	 
	@Test
	public void testShowFinishMsg() {
		aUI.showFinishMsg();
		assertEquals("�����F\n", aUI.displayArea.getText());
	}
	
	/* testShowWelcomeMsg
	 * case1: ID:955002056 �A ���ӦL�X "Welcome  �\����" ��displayArea�W
	 * case2: ID:962001044 �A ���ӦL�X "Welcome  ��v��" ��displayArea�W
	 */
	
	@Test
	public void testShowWelcomeMsg1() {
		aUI.showWelcomeMsg("955002056");
		assertEquals("Welcome  �\����\n\n", aUI.displayArea.getText());
	}
	
	@Test
	public void testShowWelcomeMsg2() {
		aUI.showWelcomeMsg("962001044");
		assertEquals("Welcome  ��v��\n\n", aUI.displayArea.getText());
	}
	
	/* testGetInput
	 * ���]�w�ntextArea����J�A�M��I�sgetInput�A��userInput�O�_���Y��
	 * case1: ��J"Q"
	 * case2: ��J"955002056"
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
	 * case1: ID:955002056 �A ���o��ID�A���^��true
	 * case2: ID:888888888 �A �S�o��ID�A���^��false
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
	 * case1: ID:955002056 �A ���ӦL�X�\���ɪ����Ӧ��Z�MtotalGrade
	 * lab1:88, lab2:92, lab3:88, midTerm:98, finalExam:91, totalGrade:93
	 * 
	 * case2: ID:962001051 �A ���ӦL�X���§ʪ����Ӧ��Z�MtotalGrade
	 * lab1:81, lab2:32, lab3:50, midTerm:90, finalExam:93, totalGrade:81
	 */
	
	@Test
	public void testShowGrade1() {
		aGradeSystem.showGrade("955002056");
		String temp = "�\���ɦ��Z:"
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
		String temp = "���§ʦ��Z:"
					+ "\nlab1:                81"
					+ "\nlab2:                32*"
					+ "\nlab3:                50*"
					+ "\nmid-term:       90"
					+ "\nfinal exam:     93"
					+ "\ntotal grade:    81\n\n";
		assertEquals(temp, aUI.displayArea.getText());
	}
	 
	/* testShowRank
	 * case1: ID:955002056 �A ���ӦL�X "�\���ɱƦW��9"  ��displayArea�W
	 * case2: ID:962001044 �A ���ӦL�X "���§ʱƦW��63" ��displayArea�W
	 */
	
	@Test
	public void testShowRank1() {
		aGradeSystem.showRank("955002056");
		assertEquals("�\���ɱƦW��9\n\n", aUI.displayArea.getText());
	}
	
	@Test
	public void testShowRank2() {
		aGradeSystem.showRank("962001051");
		assertEquals("���§ʱƦW��63\n\n", aUI.displayArea.getText());
	}
	 
	/* testShowAverage
	 * �]�����Z���������G���@�ˡA�ҥH�u�Τ@��case
	 * case1: ���ӦL�X���Z���Ӧ��Z���ӧO����
	 * lab1:90, lab2:87, lab3:89, midTerm:89, finalExam:89
	 */
	
	@Test
	public void testAverage() {
		aGradeSystem.showAverage();
		String temp = "���Z����:\n"
					+ "lab1:                90\n"
					+ "lab2:                87\n"
					+ "lab3:                89\n"
					+ "mid-term:       89\n"
					+ "final exam:     89\n\n";
		assertEquals(temp, aUI.displayArea.getText());
	}
	
	/* testCheckWeights
	 * case1: ��J"Y"�A��ܽT�{�A����return true
	 * case2: ��J"N"�A��ܧ_�w�A����return false
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
	 * case1: ��J"18 19 20 21 22"�A���ӧ�o���ӼƦr�Ȧs��weights[5~9]
	 * case2: ��J"22 21 20 19 18"�A���ӧ�o���ӼƦr�Ȧs��weights[5~9]
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
	  * ���totalGrade��84.5�A�|�ˤ��J������85
	  * 
	  * case 2: lab1:70(18%), lab2:65(19%), lab3:88(20%), midTerm:99(21%), finalExam:77(22%)
	  * ���totalGrade��80.28�A�|�ˤ��J������80
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
