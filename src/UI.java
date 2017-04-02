import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UI {
	GradeSystems aGradeSystem;
	JFrame frame;
	JTextArea displayArea, textArea;
	String userInput;
	boolean enterPressed;
	UI() throws IOException {
		aGradeSystem = new GradeSystems(this);
		enterPressed = false;
		frame = new JFrame();
		frame.setSize(500, 500);
		frame.setTitle("GradeSystem");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		displayArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(displayArea);
		scroll.setBounds(0, 0, 500, 350);
		displayArea.setEditable(false);		
		frame.getContentPane().add(scroll);
		
		textArea = new JTextArea();
		textArea.setBounds(0, 400, 500, 500);
		textArea.addKeyListener(new KeyListener(){
		    @Override
		    public void keyPressed(KeyEvent e){
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){
		        	e.consume();
		        	enterPressed = true;
		        }
		        /*if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
		        	e.consume();
		        }*/
		    }

		    @Override
		    public void keyTyped(KeyEvent e) {
		    }

		    @Override
		    public void keyReleased(KeyEvent e) {
		    }
		});
		frame.getContentPane().add(textArea);
		
		frame.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        textArea.requestFocus();
		    }
		});
		
		frame.setVisible(true);
	}
	
	/* method checkID
	 * �T�{��J��ID�O�_�bGradeSystems��(�O�_���o�Ӿǥ�)
	 * @param ID �n�T�{��ID
	 * @return �@��boolean�ȡAtrue�N���Afalse�N��S���o�Ӿǥ�
	 * 
	 * Pseudo code:
	 * 1. �I�s GradeSystem �� containsID
	 * 2. return containsID ���^�ǭ�
	 * 
	 * Time estimate: O(n)
	 * Example: UI����.checkID("955002056"); return true
	 */
	
	public boolean checkID(String ID) {
		return aGradeSystem.containsID(ID);
	}
	
	/* method promptCommand
	 * �n�D�ϥΪ̿�J���O
	 * @param �L
	 * @return �@��int�A�̷ӿ�J���O�^�Ǥ��P����
	 * 
	 * Pseudo code:
	 * 1. �L�X�n�D��J���O���r���UI�W
	 * 2. Ū���ϥΪ̿�J
	 * 3. �P�_�ϥΪ̿�J���ث��O�A���O�^�Ǥ��P��int��
	 * 
	 * Time estimate: O(1)
	 * Example: UI����.promptCommand(); �ϥΪ̿�JG�Areturn 1
	 */
	
	public int promptCommand() {
		displayArea.append("��J���O\n");
		displayArea.append("1) G ��ܦ��Z (Grade)\n");
		displayArea.append("2) R ��ܱƦW (Rank)\n");
		displayArea.append("3) A ��ܥ��� (Average)\n");
		displayArea.append("4) W ��s�t�� (Weight)\n");
		displayArea.append("5) E ���}��� (Exit)\n");
		getInput();
		if(userInput.equals("G") || userInput.equals("g") ) return 0;
		else if(userInput.equals("R") || userInput.equals("r") ) return 1;
		else if(userInput.equals("A") || userInput.equals("a") ) return 2;
		else if(userInput.equals("W") || userInput.equals("w") ) return 3;
		else if(userInput.equals("E") || userInput.equals("e") ) return 4;
		return 5;
	}
	
	/* method promptID
	 * �n�D�ϥΪ̿�JID
	 * @param �L
	 * @return �@��int�A�̷ӿ�J�^�Ǥ��P����
	 * 
	 * Pseudo code:
	 * 1. �L�X�n�D��J���r��
	 * 2. Ū����J
	 * 3. �̷ӿ�J�^�Ǥ��P��int��
	 * 
	 * Time estimate: O(1)
	 * Example: UI����.promptID(); �ϥΪ̿�JQ�Areturn 0
	 */
	
	public int promptID() {
		displayArea.append("��JID�� Q(�����ϥ�)?\n");
		getInput();
		if(userInput.equals("Q") || userInput.equals("q") ) return 0;
		else if(!checkID(userInput)) return 1;
		return 2;
	}
	
	/* method showFinishMsg
	 * ��ϥΪ̿�JQ�ɡA�L�X�����T��
	 * @param �L
	 * @return �L
	 * 
	 * Pseudo code:
	 * 1. �L�X"�����F"
	 * 2. �N��J��]������A��J
	 * 
	 * Time estimate: O(1)
	 * Example: UI����.showFinishMsg(); �L�X"�����F"
	 */
	
	public void showFinishMsg() {
		displayArea.setText("�����F\n");
		textArea.setEditable(false);
		textArea.getCaret().setVisible(false);
	}
	
	/* method showWelcomeMsg
	 * ��ϥΪ̿�J���TID�ɡA�L�X�Ӿǥͩm�W���w��L
	 * @param �Ӿǥ�ID�A�Ψӧ�m�W
	 * @return �L
	 * 
	 * Pseudo code:
	 * 1. �M��list�h���Ӿǥͪ�Grade����
	 * 2. �L�X"Welcome + �m�W"
	 * 
	 * Time estimate: O(n)
	 * Example: UI����.showWelcomeMsg("955002056"); �L�X"Welcome �\����"
	 */
	
	public void showWelcomeMsg(String ID) {
		for(int i=0; i<aGradeSystem.aList.size(); i++)
		{
			if(ID.equals(aGradeSystem.aList.get(i).ID))
			{
				displayArea.setText("Welcome  " + aGradeSystem.aList.get(i).name + "\n\n");
			}
		}
	}
	
	/* method getInput
	 * �Ψ�Ū���ϥΪ̦btextArea����J
	 * @param �L
	 * @return �L
	 * 
	 * Pseudo code:
	 * 1. �L���j��A��ϥΪ̫��Uenter�ɷ|���X�j��
	 * 2. Ū����J
	 * 3. �NtextArea�M��
	 * 
	 * Time estimate: O(1)
	 * Example: UI����.getInput(); �ϥΪ̿�JQ�AŪ��Q�A�M��textArea
	 */
	
	public void getInput() {
		while(!enterPressed) textArea.requestFocus();
		enterPressed = false;
		userInput = textArea.getText();
		textArea.setText("");
	}
}
