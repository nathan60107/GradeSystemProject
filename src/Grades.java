
public class Grades {
	String ID, name;
	int lab1, lab2, lab3, midTerm, finalExam, totalGrade;
	public Grades() { 
		
	}
	
	/* method calculateTotalGrade
	 * 計算五個成績經由不同比例的配分加權。最後得到totalGrade
	 * 
	 * @param weights 代表配分的陣列，10就代表10%，五個值加起來要是100，也就是100%
	 * @return 無，因為直接把計算結果存到這個object的totalGrade就好
	 * 
	 * Pseudo code:
	 * 1. 計算lab1*weights1 + lab2*weights2 + lab3*weights3 + midTerm*weights4 + finalExam*weights5
	 * 2. 四捨五入則是看十位數和個位數有沒有大於等於50，有的話就進位(加100)
	 * 3. 由於把weight設為整數，所以加權完後還要除以100才是正確的值
	 * 4. 把值丟給totalGrade，並結束method
	 * 
	 * Time estimate: O(1)
	 * Example: 若 lab1=88, lab2=92, lab3=88, midTerm=98, finalExam=91
	 *          weights[5] = {10, 10, 10, 30, 40}
	 * 則 Grades物件.calculateTotalGrade(weights[5]);
	 * 會算出totalGrade=92.6, 四捨五入後為93，算完後存入此object的totalGrade
	 */
	
	public void calculateTotalGrade(int[] weights) {
		totalGrade = lab1*weights[0] + lab2*weights[1] + lab3*weights[2] + midTerm*weights[3] + finalExam*weights[4];
		if(totalGrade%100 >= 50) totalGrade += 100;
		totalGrade /= 100;
	}
}
