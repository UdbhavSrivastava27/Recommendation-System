package recomendation_system;

import java.util.*;
public class SourceMatrix {
	
	int[][] sourceMatrix;
	
	SourceMatrix(ArrayList<ArrayList<String>> dataArr, ArrayList<String> sourceArr){
		setSourceMatrix(dataArr,sourceArr);
	}
	
	private int[][] setSourceMatrix(ArrayList<ArrayList<String>> dataArr, ArrayList<String> sourceArr){
		sourceMatrix = new int[dataArr.size()][sourceArr.size()];
		for(int i = 0;i<dataArr.size();i++) {
			sourceMatrix[i] = getSingleRow(dataArr.get(i),sourceArr);
		}
		return sourceMatrix;
	}
	
	public int[][] getSourceMatrix(){
		return sourceMatrix;
	}
	
	private int[] getSingleRow(ArrayList<String> dataArray, ArrayList<String> sourceArray) {
		int[] resultRow = new int[sourceArray.size()];
		
		for(int i=0;i<sourceArray.size();i++) {
//			System.out.println(sourceArray.indexOf(dataArray.get(i).toLowerCase()));
			if(dataArray.contains(sourceArray.get(i))) {
				resultRow[i] = 1;
			} else {
				resultRow[i] = 0;
			}
		}
		
		return resultRow;
	}
	
}
