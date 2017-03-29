import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class GradeSystems {
	int[] weights;
	List<Grades> aList = new ArrayList<>();
	UI aUI;
	int average1, average2, average3, average4, average5;
	GradeSystems(UI in) throws IOException{
		weights = new int[10];
		weights[0] = 10;
		weights[1] = 10;
		weights[2] = 10;
		weights[3] = 30;
		weights[4] = 40;
		aUI = in;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("gradeinput.txt"),"UTF-8")); 
        String temp = null;
        boolean first = true;
        while ((temp = br.readLine()) != null) {  
        	if(first)
        	{
        		temp = temp.substring(1, temp.length());
        		first = false;
        	}
            Grades aGrade = new Grades();
        	aGrade.ID = temp.substring(0, 9);
        	aGrade.name = temp.substring(10, 13);
        	aGrade.lab1 = Integer.valueOf(temp.substring(14, 16));
        	aGrade.lab2 = Integer.valueOf(temp.substring(17, 19));
        	aGrade.lab3 = Integer.valueOf(temp.substring(20, 22));
        	aGrade.midTerm = Integer.valueOf(temp.substring(23, 25));
        	aGrade.finalExam = Integer.valueOf(temp.substring(26, 28));
        	aGrade.calculateTotalGrade(weights);
        	aList.add(aGrade);
        }
        br.close();
	}
	
	/* method containsID
	 * 用來確認某個ID是否在這個GradeSystem裡
	 * 
	 * @param ID 用來查詢的ID
	 * @return 一個boolean，true代表有，false則代表這個GradeSystem裡沒有人是這個ID
	 * 
	 * Pseudo code:
	 * 1. 遍歷整個Grade的List，查看每個人的ID是否和傳入的相同
	 * 2. 如果相同就直接return true
	 * 3. 遍歷完仍沒找到則回傳false
	 * 
	 * Time estimate: O(n), n為List的長度，也就是學生的數量
	 * Example: GradeSystems物件.containsID("955002056"); 傳回結果為true
	 */
	
	public boolean containsID(String ID) {
		for(int i=0; i<aList.size(); i++) 
			if(ID.equals(aList.get(i).ID)) 
				return true;
			return false;
	}
	
	/* method showGrade
	 * 黨使用者輸入指令G時，印出目前學生的五個成績和totalGrade
	 * @param ID 目前學生的ID
	 * @return 無，因為只是要印東西到UI上
	 * 
	 * Pseudo code:
	 * 1. 用傳入的ID找到對應的學生，採用遍歷list的方式
	 * 2. 印出那個學生的五個成績和totalGrade，若低於60則要標註*
	 * 
	 * Time estimate: O(n), n為list的長度，也就是學生的數量
	 * Example: GradeSystems物件.showGrade("955002056"); UI上印出"許文馨成績
	 * lab1:88 lab2:92 lab3:88 midTerm: 98 finalExam:91 totalGrade:93"
	 */
	
	public void showGrade(String ID) {
		Grades aGrade = null;
		for(int i=0; i<aList.size(); i++) 
			if(ID.equals(aList.get(i).ID))
				aGrade = aList.get(i);
				aUI.displayArea.setText(aGrade.name + "成績:"
						+ "\nlab1:                " + aGrade.lab1 + ((aGrade.lab1 < 60)?"*":"")
						+ "\nlab2:                " + aGrade.lab2 + ((aGrade.lab2 < 60)?"*":"")
						+ "\nlab3:                " + aGrade.lab2 + ((aGrade.lab3 < 60)?"*":"")
						+ "\nmid-term:       " + aGrade.midTerm + ((aGrade.midTerm < 60)?"*":"")
						+ "\nfinal exam:     " + aGrade.finalExam + ((aGrade.finalExam < 60)?"*":"")
						+ "\ntotal grade:    " + aGrade.totalGrade + ((aGrade.totalGrade < 60)?"*":"")+"\n\n");
	}
	
	/* method showRank
	 * 當使用者輸入指令R時，印出目前學生的Rank(排名)
	 * @param ID 目前學生的ID
	 * @return 無，因為只是要印東西到UI上
	 * 
	 * Pseudo code:
	 * 1. 先宣告變數rank，並設為1，之後可能會慢慢加上去
	 * 2. 用傳入的ID找到對應的學生，採用遍歷list的方式
	 * 3. 將目前學生的totalGrade和其他學生的totalGrade做比較，同樣採用遍歷list的方式
	 * 4. 如果目前學生的totalGrade比較小，代表rank(排名)要+1
	 * 5. 最後在UI上印出學生姓名和rank(排名)
	 * 
	 * Time estimate: O(n), n為list的長度，也就是學生的數量
	 * Example: GradeSystems物件.showRank("955002056"); UI上印出"許文馨排名第9"
	 */
	
	public void showRank(String ID) {
		int rank=1;
		Grades aGrade = null;
		for(int i=0; i<aList.size(); i++) if(ID.equals(aList.get(i).ID)) aGrade = aList.get(i);
		for(int i=0; i<aList.size(); i++) if(aList.get(i).totalGrade > aGrade.totalGrade) rank++;
		aUI.displayArea.setText(aGrade.name + "排名第" + rank + "\n\n");
	}
	
	private void takeAverage(){
		for(int i=0; i<aList.size(); i++){
			average1 += aList.get(i).lab1;
			average2 += aList.get(i).lab2;
			average3 += aList.get(i).lab3;
			average4 += aList.get(i).midTerm;
			average5 += aList.get(i).finalExam;
		}
	}
	
	/* method showAverage
	 * 當使用者輸入指令A時，印出全班五個成績的各別平均
	 * @param 無，不須指定ID
	 * @return 無，因為只是要印東西到UI上
	 * 
	 * Pseudo code:
	 * 1. 先呼叫takeAverage，得到五個要印的成績
	 * 2. 印出五個成績到UI上
	 * 
	 * Time estimate: O(n), 因為takeAverage要遍歷list，n為list的長度，也就是學生的數量
	 * Example: GradeSystems物件.showAverage(); UI上印出"全班平均:
	 * lab1:90 lab2:88 lab3:89 midTerm:90 finalExam:90"
	 */
	
	public void showAverage() {
		takeAverage();
		aUI.displayArea.setText("全班平均:\n");
		aUI.displayArea.append("lab1:                " + Math.round(average1/aList.size()) + "\n");
		aUI.displayArea.append("lab2:                " + Math.round(average2/aList.size()) + "\n");
		aUI.displayArea.append("lab3:                " + Math.round(average3/aList.size()) + "\n");
		aUI.displayArea.append("mid-term:       " + Math.round(average4/aList.size()) + "\n");
		aUI.displayArea.append("final exam:     " + Math.round(average5/aList.size()) + "\n\n");
	}
	
	private void printWeight(int para, int w1, int w2, int w3, int w4, int w5){
		if (para==0)aUI.displayArea.setText("舊配分:\n");
		else if(para==1)aUI.displayArea.append("\n請確認新配分:\n");
		aUI.displayArea.append("lab1:                " + w1 + "%\n");
		aUI.displayArea.append("lab2:                " + w2 + "%\n");
		aUI.displayArea.append("lab3:                " + w2 + "%\n");
		aUI.displayArea.append("mid-term:       " + w4 + "%\n");
		aUI.displayArea.append("final exam:     " + w5 + "%\n\n");
		if (para==0)aUI.displayArea.append("輸入新配分:(五個整數)\n");
		else if(para==1)aUI.displayArea.append("以上正確嗎? Y(Yes) 或  N(No):\n");
	};
	
	private boolean getNewWeight(){
		aUI.getInput();
		String[] ss = aUI.userInput.split(" ");
		for(int i=0; i<ss.length && i <= 4; i++){
			if(!ss[i].equals("")){
				weights[i+5] = Integer.valueOf(ss[i]);
			}
		}
		if (weights[5]+weights[6]+weights[7]+weights[8]+weights[9]==100)return true;
		else return false;
	}
	
	private boolean checkWeight(){
		while(true)
		{
			aUI.getInput();
			if(aUI.userInput.equals("Y"))
			{
				for(int i=0; i<5; i++) weights[i] = weights[i+5];
				for(int i=0; i<aList.size(); i++) aList.get(i).calculateTotalGrade(weights);
				aUI.displayArea.setText(null);
				return true;
			}
			else if(aUI.userInput.equals("N"))return false;
			else aUI.displayArea.append("\n請輸入Y或N:\n");
		}
	}
	
	public void updateWeights() {
		do{
			printWeight(0, weights[0], weights[1], weights[2], weights[3], weights[4]);
			while(!getNewWeight()){
				aUI.displayArea.append("配分總和需為100\n");
			}
			printWeight(1, weights[5], weights[6], weights[7], weights[8], weights[9]);
		}
		while(!checkWeight());
	}
}
