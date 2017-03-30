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
	
	/* method takeAverage
	 * 用來計算全班五個成績的個別平均
	 * @param 無
	 * @return 無
	 * 
	 * Pseudo code:
	 * 1. 使用一個迴圈，遍歷整個Grade的list
	 * 2. 迴圈內，每次都將lab1加到average1，lab2加到average2，
	 * 	  lab3加到average3，midTerm加到average4，finalExam加到average5
	 * 3. 最後得到五個成績的個別加總
	 * (註: 在印到UI的時候再除以list長度，就是平均了)
	 * 
	 * Time estimate: O(n)
	 * Example: this.takeAverage();
	 * 得到average1=5690, average2=5526, average3=5613, average4=5640, average5=5653
	 */
	
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
	
	/* method printWeight
	 * 印出配分到UI上
	 * @param para 0代表要印的是舊配分，1代表要印的是新配分
	 * @param w1 lab1的配分
	 * @param w2 lab2的配分
	 * @param w3 lab3的配分
	 * @param w4 midTerm的配分
	 * @param w5 finalExam的配分
	 * @return 無
	 * 
	 * Pseudo code:
	 * 1. 判斷要印舊還是新配分，舊的印出"舊配分:"，新的印出"請確認新配分:"
	 * 2. 印出傳入的五個配分
	 * 3. 判斷要印舊還是新配分，舊的印出"輸入新配分:"，新的印出"以上正確嗎?"
	 * 
	 * Time estimate: O(1)
	 * Example: this.printWeight(0, 10, 10, 10, 30, 40);
	 * 印出 舊配分: lab1:10% lab2:10% lab3:10% midTerm:30% finalExam:40%
	 */
	
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
	
	/* method getNewWeight
	 * 用來接受使用者輸入的新配分
	 * @param 無
	 * @return 一個boolean值，true代表輸入正確(配分加起來是100)，false代表輸入錯誤(加起來不是100)
	 * 
	 * Pseudo code:
	 * 1. 先使用getInput得到所有的輸入
	 * 2. 將所有輸入parse成五個數字
	 * 3. 把五個數字暫存起來
	 * 3. 判斷五個數字加總是否為100，是則回傳true，否則回傳false
	 * 
	 * Time estimate: O(1)
	 * Example: this.getNewWeight(); 使用者輸入 40 30 10 10 10
	 * 回傳true (因為加起來等於100)
	 */
	
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
	
	/* method checkWeight
	 * 請使用者確認新配分，讀取Y或N
	 * @param 無
	 * @return 一個boolean值，true代表使用者輸入Y，false代表使用者輸入N
	 * 
	 * Pseudo code:
	 * 1. 外層為迴圈，作用為使用者輸入Y或N以外的東西時，要要求再次輸入
	 * 2. 讀取輸入
	 * 3. 如果為Y，把暫存的配分存入配分，得到新配分，
	 * 	     並且對所有Grade呼叫calculateTotalGrade，算出所有學生新的totalGrade
	 *    return true
	 * 4. 如果為N，return false
	 * 5. 如果都不是，印出"請輸入Y或N:"，繼續迴圈
	 * 
	 * Time estimate: 當輸入Y時為O(n)，輸入N時為O(1)
	 * Example: this.checkWeight(); 使用者輸入 Y
	 * 配分更新為剛才輸入的新配分，並且重新計算每個學生的totalWeight，return true
	 */
	
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
	
	/* method updateWeights
	 * 當使用者輸入指令W時，讓使用者看到目前配分，以及要求輸入新配分，輸入後要求使用者確認
	 * @param 無
	 * @return 無
	 * 
	 * Pseudo code:
	 * 1. 呼叫printWeight印出目前配分
	 * 2. 呼叫getNewWeight()，讀取使用者輸入，並判斷是否合法
	 * 	     不合法就印出"配分總和需為100"，繼續迴圈等待輸入
	 * 3. 呼叫printWeight印出新配分
	 * 4. 呼叫checkWeight()，讀取使用者確認，如果輸入為Y則結束，
	 *    輸入N則繼續迴圈，讓使用者重新輸入他要的配分
	 *    
	 * Time estimate: O(n)
	 * Example: GradeSystems物件.updateWeights();
	 * 印出舊配分，以及要求輸入新配分，輸入後印出新配分，並要求確認
	 */
	
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
