package recomendation_system;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecomendationEngine {
	private HashMap<Integer,Integer> resultMap = new HashMap<>();
	private int[] resultIdArr;
	
	@SuppressWarnings("unchecked")
	RecomendationEngine(int[] targetMatrix, int[][] sourceMatrix){
		resultMap = matrixMultiplier(targetMatrix,sourceMatrix);
		resultMap = sortByValues(resultMap);
		createResultIdArr();
	}
	
	@SuppressWarnings("rawtypes")
	private void createResultIdArr() {
		FileHandler fh = new FileHandler();
		SourceDataModel sdm = new SourceDataModel();
		SourceDataModel sdmff = (SourceDataModel)fh.readDataFromFile(sdm, "source_data");
		HashMap<Integer,Integer> sdtim = sdmff.getSourceDataToIndex_Map();
		
		resultIdArr = new int[resultMap.size()];
		
		
		Set set = resultMap.entrySet();
        Iterator iterator = set.iterator();
        int i = 0;
        while(iterator.hasNext()) {
              Map.Entry me = (Map.Entry)iterator.next();
//              System.out.print(me.getKey() + ": ");
//              System.out.println(me.getValue());
              resultIdArr[i] = sdtim.get(me.getKey());
              i++;
        }
		
	}
	
	private HashMap<Integer,Integer> matrixMultiplier(int[] targetMatrix, int[][] sourceMatrix) {
		for(int i=0;i<sourceMatrix.length;i++) {
			int rowSum = 0;
			int totalJSkill = calculateTotalJSkill(sourceMatrix[i]);
			for(int j=0;j<sourceMatrix[i].length;j++) {
				rowSum += (targetMatrix[j] * sourceMatrix[i][j]);
			}
						
//			use jSkill[i][0] instead of totalJSkill
			double percent = ((double)rowSum/(double)totalJSkill) * 100;

			resultMap.put(i,(int)percent);
			
		}
		
		return resultMap;
	}
	
	
	private int calculateTotalJSkill(int[] jSkill){
		int sum = 0;
		for(int i=0;i<jSkill.length;i++) {
			sum+=jSkill[i];
		}
		return sum;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap sortByValues(HashMap map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o2)).getValue())
	                  .compareTo(((Map.Entry) (o1)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
	
	public int[] getRecomendation() {
		return resultIdArr;
	}
}
