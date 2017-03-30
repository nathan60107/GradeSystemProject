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
	
	/* method takeAverage
	 * �Ψӭp����Z���Ӧ��Z���ӧO����
	 * @param �L
	 * @return �L
	 * 
	 * Pseudo code:
	 * 1. �ϥΤ@�Ӱj��A�M�����Grade��list
	 * 2. �j�餺�A�C�����Nlab1�[��average1�Alab2�[��average2�A
	 * 	  lab3�[��average3�AmidTerm�[��average4�AfinalExam�[��average5
	 * 3. �̫�o�줭�Ӧ��Z���ӧO�[�`
	 * (��: �b�L��UI���ɭԦA���Hlist���סA�N�O�����F)
	 * 
	 * Time estimate: O(n)
	 * Example: this.takeAverage();
	 * �o��average1=5690, average2=5526, average3=5613, average4=5640, average5=5653
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
	
	/* method printWeight
	 * �L�X�t����UI�W
	 * @param para 0�N��n�L���O�°t���A1�N��n�L���O�s�t��
	 * @param w1 lab1���t��
	 * @param w2 lab2���t��
	 * @param w3 lab3���t��
	 * @param w4 midTerm���t��
	 * @param w5 finalExam���t��
	 * @return �L
	 * 
	 * Pseudo code:
	 * 1. �P�_�n�L���٬O�s�t���A�ª��L�X"�°t��:"�A�s���L�X"�нT�{�s�t��:"
	 * 2. �L�X�ǤJ�����Ӱt��
	 * 3. �P�_�n�L���٬O�s�t���A�ª��L�X"��J�s�t��:"�A�s���L�X"�H�W���T��?"
	 * 
	 * Time estimate: O(1)
	 * Example: this.printWeight(0, 10, 10, 10, 30, 40);
	 * �L�X �°t��: lab1:10% lab2:10% lab3:10% midTerm:30% finalExam:40%
	 */
	
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
	
	/* method getNewWeight
	 * �Ψӱ����ϥΪ̿�J���s�t��
	 * @param �L
	 * @return �@��boolean�ȡAtrue�N���J���T(�t���[�_�ӬO100)�Afalse�N���J���~(�[�_�Ӥ��O100)
	 * 
	 * Pseudo code:
	 * 1. ���ϥ�getInput�o��Ҧ�����J
	 * 2. �N�Ҧ���Jparse�����ӼƦr
	 * 3. �⤭�ӼƦr�Ȧs�_��
	 * 3. �P�_���ӼƦr�[�`�O�_��100�A�O�h�^��true�A�_�h�^��false
	 * 
	 * Time estimate: O(1)
	 * Example: this.getNewWeight(); �ϥΪ̿�J 40 30 10 10 10
	 * �^��true (�]���[�_�ӵ���100)
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
	 * �ШϥΪ̽T�{�s�t���AŪ��Y��N
	 * @param �L
	 * @return �@��boolean�ȡAtrue�N��ϥΪ̿�JY�Afalse�N��ϥΪ̿�JN
	 * 
	 * Pseudo code:
	 * 1. �~�h���j��A�@�ά��ϥΪ̿�JY��N�H�~���F��ɡA�n�n�D�A����J
	 * 2. Ū����J
	 * 3. �p�G��Y�A��Ȧs���t���s�J�t���A�o��s�t���A
	 * 	     �åB��Ҧ�Grade�I�scalculateTotalGrade�A��X�Ҧ��ǥͷs��totalGrade
	 *    return true
	 * 4. �p�G��N�Areturn false
	 * 5. �p�G�����O�A�L�X"�п�JY��N:"�A�~��j��
	 * 
	 * Time estimate: ���JY�ɬ�O(n)�A��JN�ɬ�O(1)
	 * Example: this.checkWeight(); �ϥΪ̿�J Y
	 * �t����s����~��J���s�t���A�åB���s�p��C�Ӿǥͪ�totalWeight�Areturn true
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
			else aUI.displayArea.append("\n�п�JY��N:\n");
		}
	}
	
	/* method updateWeights
	 * ��ϥΪ̿�J���OW�ɡA���ϥΪ̬ݨ�ثe�t���A�H�έn�D��J�s�t���A��J��n�D�ϥΪ̽T�{
	 * @param �L
	 * @return �L
	 * 
	 * Pseudo code:
	 * 1. �I�sprintWeight�L�X�ثe�t��
	 * 2. �I�sgetNewWeight()�AŪ���ϥΪ̿�J�A�çP�_�O�_�X�k
	 * 	     ���X�k�N�L�X"�t���`�M�ݬ�100"�A�~��j�鵥�ݿ�J
	 * 3. �I�sprintWeight�L�X�s�t��
	 * 4. �I�scheckWeight()�AŪ���ϥΪ̽T�{�A�p�G��J��Y�h�����A
	 *    ��JN�h�~��j��A���ϥΪ̭��s��J�L�n���t��
	 *    
	 * Time estimate: O(n)
	 * Example: GradeSystems����.updateWeights();
	 * �L�X�°t���A�H�έn�D��J�s�t���A��J��L�X�s�t���A�ín�D�T�{
	 */
	
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
