package recomendation_system;
import org.json.*;
import java.util.*;

public class JSONParser {
	
	private HashMap<Integer,Integer> sourceIdtoIndex_Map = new HashMap<>();
	
	public HashMap<Integer,Integer> getSourceMap(){
		return sourceIdtoIndex_Map;
	}
	
	//convert skills of a single job to array
	private ArrayList<String> stringToArray(String data){		
		ArrayList<String> resultArr = new ArrayList<>();
		
		String[] arr = data.split(",");
		for(String s:arr) {
			resultArr.add(s.trim().toLowerCase());
		}
		
		return resultArr;
	}
	

	public ArrayList<ArrayList<String>> getSourceArray(String sourceData){		
		
		ArrayList<ArrayList<String>> resultSourceArr = new ArrayList<>();
		
		try{
	    	JSONArray sourceArr = new JSONArray(sourceData);
	    		
			for(int i = 0;i<sourceArr.length();i++) {
				JSONObject singleSource = (JSONObject)sourceArr.get(i);
				resultSourceArr.add(stringToArray(singleSource.get("data").toString()));
				
				sourceIdtoIndex_Map.put(i, Integer.parseInt(singleSource.get("id").toString()));
			}
			
			
	    }catch(JSONException e) {
				System.out.println(e);
		}
		
		return resultSourceArr;
		
	}
	
	public ArrayList<String> getBaseArray(String baseData) {
		
		ArrayList<String> resultBaseArr = new ArrayList<>();
		
		JSONArray arr = new JSONArray(baseData);
		
		for(int i =0;i<arr.length();i++) {
			resultBaseArr.add(arr.getString(i).toString().toLowerCase());
		}
		
		return resultBaseArr;
	}
	
	public ArrayList<String> getUserSkillsArray(String targetData) {
		
		ArrayList<String> resultArr = new ArrayList<>();
		
		JSONObject obj = new JSONObject(targetData);
		resultArr = stringToArray(obj.getString("data").toString());
		
		return resultArr;
	}
	
}
