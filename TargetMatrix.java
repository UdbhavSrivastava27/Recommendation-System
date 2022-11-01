package recomendation_system;

import java.util.*;
public class TargetMatrix {

	int[] targetMatrix;
	
	TargetMatrix(ArrayList<String> targetArr,ArrayList<String> baseArr){
		setTargetMatrix(targetArr,baseArr);
	}
	
	private void setTargetMatrix(ArrayList<String> targetArr, ArrayList<String> baseArr) {
		targetMatrix = new int[baseArr.size()];
		for(int i=0;i<baseArr.size();i++) {
			if(targetArr.contains(baseArr.get(i)))
				targetMatrix[i] = 1;
		}
	}
	
	public int[] getTargetMatrix() {
		return targetMatrix;
	}
}

