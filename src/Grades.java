
public class Grades {
	String ID, name;
	int lab1, lab2, lab3, midTerm, finalExam, totalGrade;
	public Grades() { 
		
	}
	
	/* method calculateTotalGrade
	 * �p�⤭�Ӧ��Z�g�Ѥ��P��Ҫ��t���[�v�C�̫�o��totalGrade
	 * 
	 * @param weights �N��t�����}�C�A10�N�N��10%�A���ӭȥ[�_�ӭn�O100�A�]�N�O100%
	 * @return �L�A�]��������p�⵲�G�s��o��object��totalGrade�N�n
	 * 
	 * Pseudo code:
	 * 1. �p��lab1*weights1 + lab2*weights2 + lab3*weights3 + midTerm*weights4 + finalExam*weights5
	 * 2. �|�ˤ��J�h�O�ݤQ��ƩM�Ӧ�Ʀ��S���j�󵥩�50�A�����ܴN�i��(�[100)
	 * 3. �ѩ��weight�]����ơA�ҥH�[�v�����٭n���H100�~�O���T����
	 * 4. ��ȥᵹtotalGrade�A�õ���method
	 * 
	 * Time estimate: O(1)
	 * Example: �Y lab1=88, lab2=92, lab3=88, midTerm=98, finalExam=91
	 *          weights[5] = {10, 10, 10, 30, 40}
	 * �h Grades����.calculateTotalGrade(weights[5]);
	 * �|��XtotalGrade=92.6, �|�ˤ��J�ᬰ93�A�⧹��s�J��object��totalGrade
	 */
	
	public void calculateTotalGrade(int[] weights) {
		totalGrade = lab1*weights[0] + lab2*weights[1] + lab3*weights[2] + midTerm*weights[3] + finalExam*weights[4];
		if(totalGrade%100 >= 50) totalGrade += 100;
		totalGrade /= 100;
	}
}
