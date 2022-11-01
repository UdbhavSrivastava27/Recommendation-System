package recomendation_system;

import java.util.*;

public class Recomender {
	JSONParser jp;
	FileHandler fh;
	
	public void init(String sourceData, String baseData) {
//		setup base data
		ArrayList<String> baseArray = jp.getBaseArray(baseData); 
		BaseDataModel bdm = new BaseDataModel(baseArray);

		System.out.println(fh.writeDataToFile(bdm, "base_data"));

//		setup source data
//		//Get the skills array of all the jobs
		ArrayList<ArrayList<String>> sourceArray = jp.getSourceArray(sourceData);
//		// Generate the source matrix
		SourceMatrix sm = new SourceMatrix(sourceArray, baseArray);
		int[][] matrix = sm.getSourceMatrix();
//		//Generate a map to store data relative to the index
		HashMap<Integer,Integer> sourceDataToIndexMap = jp.getSourceMap();
//		
		SourceDataModel sdm = new SourceDataModel(matrix,sourceDataToIndexMap);
		
		System.out.println(fh.writeDataToFile(sdm, "source_data"));
		
		
//		nullify object for garbage collection
		bdm = null;
		sdm = null;
		sm = null;
		matrix = null;
		sourceDataToIndexMap = null;
	}
	
	public int[] recommend(String targetData) {
//		Get Source Data
		SourceDataModel sdmff = (SourceDataModel)fh.readDataFromFile(new Object(), "source_data");

//		Get Base Data
		BaseDataModel bdmff = (BaseDataModel)fh.readDataFromFile(new Object(), "base_data");

//		Setup Target data
		ArrayList<String> userSkills = new ArrayList<>();
		userSkills = jp.getUserSkillsArray(targetData);
		TargetMatrix tm = new TargetMatrix(userSkills,bdmff.getBaseDataArray());

//		get recommendation form recomendation engine
		RecomendationEngine re = new RecomendationEngine(tm.getTargetMatrix(),sdmff.getSourceDataMatrix());
		
//		nullify object for garbage collection
		sdmff = null;
		bdmff = null;
		userSkills = null;
		tm = null;
		
//		return recomended array
		return re.getRecomendation();
	}
	
	public Recomender(){
		jp = new JSONParser();
		fh = new FileHandler();
	}
}