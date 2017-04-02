import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IntegrationTest {

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

	/* Integration test
	 * case1: ��JID "955002056"�A��J���OG�A�ˬd���Z�O�_���T�A��J���OR�A�ˬd�ƦW�O�_���T�A��J���OA�A�ˬd�����O�_���T�A
	 * ��J���OW�ÿ�J�s�t���A�ˬd�O�_���Ȧs�A�A��JY�A�ˬd�O�_����s�t���A��J���OE�A�A��J���OQ����
	 * case2: ��JID "962001044"�A��J���OG�A�ˬd���Z�O�_���T�A��J���OR�A�ˬd�ƦW�O�_���T�A��J���OA�A�ˬd�����O�_���T�A
	 * ��J���OW�ÿ�J�s�t���A�ˬd�O�_���Ȧs�A�A��JN�A�ˬd�O�_������t���A��J���OE�A�A��J���OQ����
	 */
	
	@Test
	public void integrationTest1() {
		String temp;
		aUI.enterPressed = true;
		aUI.textArea.setText("955002056");
		assertEquals(2, aUI.promptID());
		aUI.enterPressed = true;
		aUI.textArea.setText("G");
		assertEquals(0, aUI.promptCommand());
		aGradeSystem.showGrade("955002056");
		temp = "�\���ɦ��Z:"
				+ "\nlab1:                88"
				+ "\nlab2:                92"
				+ "\nlab3:                88"
				+ "\nmid-term:       98"
				+ "\nfinal exam:     91"
				+ "\ntotal grade:    93\n\n";
		assertEquals(temp, aUI.displayArea.getText());
		aUI.enterPressed = true;
		aUI.textArea.setText("R");
		assertEquals(1, aUI.promptCommand());
		aGradeSystem.showRank("955002056");
		assertEquals("�\���ɱƦW��9\n\n", aUI.displayArea.getText());
		aUI.enterPressed = true;
		aUI.textArea.setText("A");
		assertEquals(2, aUI.promptCommand());
		aGradeSystem.showAverage();
		temp = "���Z����:\n"
				+ "lab1:                90\n"
				+ "lab2:                87\n"
				+ "lab3:                89\n"
				+ "mid-term:       89\n"
				+ "final exam:     89\n\n";
		assertEquals(temp, aUI.displayArea.getText());
		aUI.enterPressed = true;
		aUI.textArea.setText("W");
		assertEquals(3, aUI.promptCommand());
		aUI.enterPressed = true;
		aUI.textArea.setText("18 19 20 21 22");
		aGradeSystem.updateWeights();
		assertEquals(18, aGradeSystem.weights[5]);
		assertEquals(19, aGradeSystem.weights[6]);
		assertEquals(20, aGradeSystem.weights[7]);
		assertEquals(21, aGradeSystem.weights[8]);
		assertEquals(22, aGradeSystem.weights[9]);
		aUI.enterPressed = true;
		aUI.textArea.setText("Y");
		assertEquals(true, aGradeSystem.checkWeights());
		assertEquals(18, aGradeSystem.weights[0]);
		assertEquals(19, aGradeSystem.weights[1]);
		assertEquals(20, aGradeSystem.weights[2]);
		assertEquals(21, aGradeSystem.weights[3]);
		assertEquals(22, aGradeSystem.weights[4]);
		aUI.enterPressed = true;
		aUI.textArea.setText("E");
		assertEquals(4, aUI.promptCommand());
		aUI.enterPressed = true;
		aUI.textArea.setText("Q");
		assertEquals(0, aUI.promptID());
	}

	@Test
	public void integrationTest2() {
		String temp;
		aUI.enterPressed = true;
		aUI.textArea.setText("962001044");
		assertEquals(2, aUI.promptID());
		aUI.enterPressed = true;
		aUI.textArea.setText("G");
		assertEquals(0, aUI.promptCommand());
		aGradeSystem.showGrade("962001044");
		temp = "��v�ʦ��Z:"
				+ "\nlab1:                87"
				+ "\nlab2:                86"
				+ "\nlab3:                98"
				+ "\nmid-term:       88"
				+ "\nfinal exam:     87"
				+ "\ntotal grade:    88\n\n";
		assertEquals(temp, aUI.displayArea.getText());
		aUI.enterPressed = true;
		aUI.textArea.setText("R");
		assertEquals(1, aUI.promptCommand());
		aGradeSystem.showRank("962001044");
		assertEquals("��v�ʱƦW��37\n\n", aUI.displayArea.getText());
		aUI.enterPressed = true;
		aUI.textArea.setText("A");
		assertEquals(2, aUI.promptCommand());
		aGradeSystem.showAverage();
		temp = "���Z����:\n"
				+ "lab1:                90\n"
				+ "lab2:                87\n"
				+ "lab3:                89\n"
				+ "mid-term:       89\n"
				+ "final exam:     89\n\n";
		assertEquals(temp, aUI.displayArea.getText());
		aUI.enterPressed = true;
		aUI.textArea.setText("W");
		assertEquals(3, aUI.promptCommand());
		aUI.enterPressed = true;
		aUI.textArea.setText("18 19 20 21 22");
		aGradeSystem.updateWeights();
		assertEquals(18, aGradeSystem.weights[5]);
		assertEquals(19, aGradeSystem.weights[6]);
		assertEquals(20, aGradeSystem.weights[7]);
		assertEquals(21, aGradeSystem.weights[8]);
		assertEquals(22, aGradeSystem.weights[9]);
		aUI.enterPressed = true;
		aUI.textArea.setText("N");
		assertEquals(false, aGradeSystem.checkWeights());
		assertEquals(10, aGradeSystem.weights[0]);
		assertEquals(10, aGradeSystem.weights[1]);
		assertEquals(10, aGradeSystem.weights[2]);
		assertEquals(30, aGradeSystem.weights[3]);
		assertEquals(40, aGradeSystem.weights[4]);
		aUI.enterPressed = true;
		aUI.textArea.setText("E");
		assertEquals(4, aUI.promptCommand());
		aUI.enterPressed = true;
		aUI.textArea.setText("Q");
		assertEquals(0, aUI.promptID());
	}
}
