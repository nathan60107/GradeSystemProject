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
	 * 確認輸入的ID是否在GradeSystems裡(是否有這個學生)
	 * @param ID 要確認的ID
	 * @return 一個boolean值，true代表有，false代表沒有這個學生
	 * 
	 * Pseudo code:
	 * 1. 呼叫 GradeSystem 的 containsID
	 * 2. return containsID 的回傳值
	 * 
	 * Time estimate: O(n)
	 * Example: UI物件.checkID("955002056"); return true
	 */
	
	public boolean checkID(String ID) {
		return aGradeSystem.containsID(ID);
	}
	
	/* method promptCommand
	 * 要求使用者輸入指令
	 * @param 無
	 * @return 一個int，依照輸入指令回傳不同的值
	 * 
	 * Pseudo code:
	 * 1. 印出要求輸入指令的字串到UI上
	 * 2. 讀取使用者輸入
	 * 3. 判斷使用者輸入哪種指令，分別回傳不同的int值
	 * 
	 * Time estimate: O(1)
	 * Example: UI物件.promptCommand(); 使用者輸入G，return 1
	 */
	
	public int promptCommand() {
		displayArea.append("輸入指令\n");
		displayArea.append("1) G 顯示成績 (Grade)\n");
		displayArea.append("2) R 顯示排名 (Rank)\n");
		displayArea.append("3) A 顯示平均 (Average)\n");
		displayArea.append("4) W 更新配分 (Weight)\n");
		displayArea.append("5) E 離開選單 (Exit)\n");
		getInput();
		if(userInput.equals("G") || userInput.equals("g") ) return 0;
		else if(userInput.equals("R") || userInput.equals("r") ) return 1;
		else if(userInput.equals("A") || userInput.equals("a") ) return 2;
		else if(userInput.equals("W") || userInput.equals("w") ) return 3;
		else if(userInput.equals("E") || userInput.equals("e") ) return 4;
		return 5;
	}
	
	/* method promptID
	 * 要求使用者輸入ID
	 * @param 無
	 * @return 一個int，依照輸入回傳不同的值
	 * 
	 * Pseudo code:
	 * 1. 印出要求輸入的字串
	 * 2. 讀取輸入
	 * 3. 依照輸入回傳不同的int值
	 * 
	 * Time estimate: O(1)
	 * Example: UI物件.promptID(); 使用者輸入Q，return 0
	 */
	
	public int promptID() {
		displayArea.append("輸入ID或 Q(結束使用)?\n");
		getInput();
		if(userInput.equals("Q") || userInput.equals("q") ) return 0;
		else if(!checkID(userInput)) return 1;
		return 2;
	}
	
	/* method showFinishMsg
	 * 當使用者輸入Q時，印出結束訊息
	 * @param 無
	 * @return 無
	 * 
	 * Pseudo code:
	 * 1. 印出"結束了"
	 * 2. 將輸入欄設為不能再輸入
	 * 
	 * Time estimate: O(1)
	 * Example: UI物件.showFinishMsg(); 印出"結束了"
	 */
	
	public void showFinishMsg() {
		displayArea.setText("結束了\n");
		textArea.setEditable(false);
		textArea.getCaret().setVisible(false);
	}
	
	/* method showWelcomeMsg
	 * 當使用者輸入正確ID時，印出該學生姓名並歡迎他
	 * @param 該學生ID，用來找姓名
	 * @return 無
	 * 
	 * Pseudo code:
	 * 1. 遍歷list去找到該學生的Grade物件
	 * 2. 印出"Welcome + 姓名"
	 * 
	 * Time estimate: O(n)
	 * Example: UI物件.showWelcomeMsg("955002056"); 印出"Welcome 許文馨"
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
	 * 用來讀取使用者在textArea的輸入
	 * @param 無
	 * @return 無
	 * 
	 * Pseudo code:
	 * 1. 無限迴圈，當使用者按下enter時會跳出迴圈
	 * 2. 讀取輸入
	 * 3. 將textArea清空
	 * 
	 * Time estimate: O(1)
	 * Example: UI物件.getInput(); 使用者輸入Q，讀到Q，清空textArea
	 */
	
	public void getInput() {
		while(!enterPressed) textArea.requestFocus();
		enterPressed = false;
		userInput = textArea.getText();
		textArea.setText("");
	}
}
