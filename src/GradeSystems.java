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
	 * �ΨӽT�{�Y��ID�O�_�b�o��GradeSystem��
	 * 
	 * @param ID �ΨӬd�ߪ�ID
	 * @return �@��boolean�Atrue�N���Afalse�h�N��o��GradeSystem�̨S���H�O�o��ID
	 * 
	 * Pseudo code:
	 * 1. �M�����Grade��List�A�d�ݨC�ӤH��ID�O�_�M�ǤJ���ۦP
	 * 2. �p�G�ۦP�N����return true
	 * 3. �M�������S���h�^��false
	 * 
	 * Time estimate: O(n), n��List�����סA�]�N�O�ǥͪ��ƶq
	 * Example: GradeSystems����.containsID("955002056"); �Ǧ^���G��true
	 */
	
	public boolean containsID(String ID) {
		for(int i=0; i<aList.size(); i++) 
			if(ID.equals(aList.get(i).ID)) 
				return true;
			return false;
	}
	
	/* method showGrade
	 * �ҨϥΪ̿�J���OG�ɡA�L�X�ثe�ǥͪ����Ӧ��Z�MtotalGrade
	 * @param ID �ثe�ǥͪ�ID
	 * @return �L�A�]���u�O�n�L�F���UI�W
	 * 
	 * Pseudo code:
	 * 1. �ζǤJ��ID���������ǥ͡A�ĥιM��list���覡
	 * 2. �L�X���Ӿǥͪ����Ӧ��Z�MtotalGrade�A�Y�C��60�h�n�е�*
	 * 
	 * Time estimate: O(n), n��list�����סA�]�N�O�ǥͪ��ƶq
	 * Example: GradeSystems����.showGrade("955002056"); UI�W�L�X"�\���ɦ��Z
	 * lab1:88 lab2:92 lab3:88 midTerm: 98 finalExam:91 totalGrade:93"
	 */
	
	public void showGrade(String ID) {
		Grades aGrade = null;
		for(int i=0; i<aList.size(); i++) 
			if(ID.equals(aList.get(i).ID))
				aGrade = aList.get(i);
				aUI.displayArea.setText(aGrade.name + "���Z:"
						+ "\nlab1:                " + aGrade.lab1 + ((aGrade.lab1 < 60)?"*":"")
						+ "\nlab2:                " + aGrade.lab2 + ((aGrade.lab2 < 60)?"*":"")
						+ "\nlab3:                " + aGrade.lab2 + ((aGrade.lab3 < 60)?"*":"")
						+ "\nmid-term:       " + aGrade.midTerm + ((aGrade.midTerm < 60)?"*":"")
						+ "\nfinal exam:     " + aGrade.finalExam + ((aGrade.finalExam < 60)?"*":"")
						+ "\ntotal grade:    " + aGrade.totalGrade + ((aGrade.totalGrade < 60)?"*":"")+"\n\n");
	}
	
	/* method showRank
	 * ��ϥΪ̿�J���OR�ɡA�L�X�ثe�ǥͪ�Rank(�ƦW)
	 * @param ID �ثe�ǥͪ�ID
	 * @return �L�A�]���u�O�n�L�F���UI�W
	 * 
	 * Pseudo code:
	 * 1. ���ŧi�ܼ�rank�A�ó]��1�A����i��|�C�C�[�W�h
	 * 2. �ζǤJ��ID���������ǥ͡A�ĥιM��list���覡
	 * 3. �N�ثe�ǥͪ�totalGrade�M��L�ǥͪ�totalGrade������A�P�˱ĥιM��list���覡
	 * 4. �p�G�ثe�ǥͪ�totalGrade����p�A�N��rank(�ƦW)�n+1
	 * 5. �̫�bUI�W�L�X�ǥͩm�W�Mrank(�ƦW)
	 * 
	 * Time estimate: O(n), n��list�����סA�]�N�O�ǥͪ��ƶq
	 * Example: GradeSystems����.showRank("955002056"); UI�W�L�X"�\���ɱƦW��9"
	 */
	
	public void showRank(String ID) {
		int rank=1;
		Grades aGrade = null;
		for(int i=0; i<aList.size(); i++) if(ID.equals(aList.get(i).ID)) aGrade = aList.get(i);
		for(int i=0; i<aList.size(); i++) if(aList.get(i).totalGrade > aGrade.totalGrade) rank++;
		aUI.displayArea.setText(aGrade.name + "�ƦW��" + rank + "\n\n");
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
	 * ��ϥΪ̿�J���OA�ɡA�L�X���Z���Ӧ��Z���U�O����
	 * @param �L�A�������wID
	 * @return �L�A�]���u�O�n�L�F���UI�W
	 * 
	 * Pseudo code:
	 * 1. ���I�stakeAverage�A�o�줭�ӭn�L�����Z
	 * 2. �L�X���Ӧ��Z��UI�W
	 * 
	 * Time estimate: O(n), �]��takeAverage�n�M��list�An��list�����סA�]�N�O�ǥͪ��ƶq
	 * Example: GradeSystems����.showAverage(); UI�W�L�X"���Z����:
	 * lab1:90 lab2:88 lab3:89 midTerm:90 finalExam:90"
	 */
	
	public void showAverage() {
		takeAverage();
		aUI.displayArea.setText("���Z����:\n");
		aUI.displayArea.append("lab1:                " + Math.round(average1/aList.size()) + "\n");
		aUI.displayArea.append("lab2:                " + Math.round(average2/aList.size()) + "\n");
		aUI.displayArea.append("lab3:                " + Math.round(average3/aList.size()) + "\n");
		aUI.displayArea.append("mid-term:       " + Math.round(average4/aList.size()) + "\n");
		aUI.displayArea.append("final exam:     " + Math.round(average5/aList.size()) + "\n\n");
	}
	
	private void printWeight(int para, int w1, int w2, int w3, int w4, int w5){
		if (para==0)aUI.displayArea.setText("�°t��:\n");
		else if(para==1)aUI.displayArea.append("\n�нT�{�s�t��:\n");
		aUI.displayArea.append("lab1:                " + w1 + "%\n");
		aUI.displayArea.append("lab2:                " + w2 + "%\n");
		aUI.displayArea.append("lab3:                " + w2 + "%\n");
		aUI.displayArea.append("mid-term:       " + w4 + "%\n");
		aUI.displayArea.append("final exam:     " + w5 + "%\n\n");
		if (para==0)aUI.displayArea.append("��J�s�t��:(���Ӿ��)\n");
		else if(para==1)aUI.displayArea.append("�H�W���T��? Y(Yes) ��  N(No):\n");
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
			else aUI.displayArea.append("\n�п�JY��N:\n");
		}
	}
	
	public void updateWeights() {
		do{
			printWeight(0, weights[0], weights[1], weights[2], weights[3], weights[4]);
			while(!getNewWeight()){
				aUI.displayArea.append("�t���`�M�ݬ�100\n");
			}
			printWeight(1, weights[5], weights[6], weights[7], weights[8], weights[9]);
		}
		while(!checkWeight());
	}
}
