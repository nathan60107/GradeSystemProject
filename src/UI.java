import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
	public boolean checkID(String ID) {
		return aGradeSystem.containsID(ID);
	}
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
	public int promptID() {
		displayArea.append("輸入ID或 Q(結束使用)?\n");
		getInput();
		if(userInput.equals("Q") || userInput.equals("q") ) return 0;
		else if(!checkID(userInput)) return 1;
		return 2;
	}
	public void showFinishMsg() {
		displayArea.setText("結束了\n");
		displayArea.setEditable(false);
		displayArea.getCaret().setVisible(false);
	}
	public void showWelcomeMsg(String ID) {
		for(int i=0; i<aGradeSystem.aList.size(); i++)
		{
			if(ID.equals(aGradeSystem.aList.get(i).ID))
			{
				displayArea.setText("Welcome  " + aGradeSystem.aList.get(i).name + "\n\n");
			}
		}
	}
	
	public void getInput() {
		int start = textArea.getText().length(), end = textArea.getText().length();
		while(!enterPressed)
		{
			end = textArea.getText().length();
			//textArea.setCaretPosition(end);
		}
		enterPressed = false;
		userInput = textArea.getText().substring(start, end);
		textArea.setText("");
	}
}
