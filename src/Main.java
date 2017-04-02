import java.io.IOException;

public class Main {
	static UI aUI;
	static String ID;
	
	/* method main
	 * �Ы�UI����A�ö}�l�n�D��J
	 * @param args command line���޼�
	 * @return �L
	 * @throws IOException �ѩ�aUI��static�A�]���ݭnIOException
	 * 
	 * Pseudo code:
	 * 1. �Ы�UI
	 * 2. �i�J�j��A���JQ�ɤ~����
	 * 3. �i�J�ĤG�Ӱj��A�n�D��JID�A�p�G��JQ�h�����{���A��J���TID�h���}�j��A���~�h�A���n�D��J
	 * 4. �αo�쪺���TID�I�sshowWelcomeMsg
	 * 5. �i�Jloop�A�n�D��J���O
	 * 
	 * Time estimate: O(n)
	 * Example: Main.main(); �}�l�{��
	 */
	
	public static void main(String[] args) throws IOException {
		aUI = new UI();
		int retval;
		while(true){
			aUI.displayArea.setText(null);
			while(true){
				retval = aUI.promptID();
				if(retval == 0){
					aUI.showFinishMsg();
					return;
				}
				else if(retval == 1) aUI.displayArea.setText("ID���F!\n");
				else break;
			}
			ID = aUI.userInput;
			aUI.showWelcomeMsg(ID);
			loop();
		}
	}
	
	/* method loop
	 * �ϥΪ̿�J���TID��A�i�J�n�D��J���O��loop�A�è̷Ӥ��P���O�I�s���P��method
	 * @param �L
	 * @return �L
	 * 
	 * Pseudo code:
	 * 1. �~�h���j��A�u�����J��E�ɤ~���}
	 * 2. ��L���O���B�z�AG�N�I�sshowGrade�AR�N�I�sshowRank�AA�N�I�sshowAverage
	 *    W�N�I�supdateWeights�A�éI�scheckWeights�n�D�T�{�AE�N���}�j��A��J���~�h�L�X"���O���F"�A�~��j��
	 *    
	 * Time estimate: O(n)
	 * Example: Main.loop(); �ϥΪ̿�JG�A�L�X�Ӿǥͪ����Z
	 */
	
	private static void loop(){
		int retval;
		while(true){
			retval = aUI.promptCommand();
			if(retval == 0) aUI.aGradeSystem.showGrade(ID);
			else if(retval == 1) aUI.aGradeSystem.showRank(ID);
			else if(retval == 2) aUI.aGradeSystem.showAverage();
			else if(retval == 3) {
				do aUI.aGradeSystem.updateWeights();
				while(!aUI.aGradeSystem.checkWeights());
			}
			else if(retval == 4) break;
			else aUI.displayArea.setText("���O���F!\n");
		}
	}
}
