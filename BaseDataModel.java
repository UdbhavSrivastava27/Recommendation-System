package recomendation_system;
import java.io.Serializable;
import java.util.ArrayList;
public class BaseDataModel implements Serializable {
	private static final long serialVersionUID = -11L;
	ArrayList<String> allSkillsArray;
	
	BaseDataModel(ArrayList<String> allSkillsArray){
		setBaseDataArray(allSkillsArray);
	}
	
	BaseDataModel(){
		
	}
	
	void setBaseDataArray(ArrayList<String> allSkillsArray){
		this.allSkillsArray = allSkillsArray;
	}
	
	ArrayList<String> getBaseDataArray(){
		return this.allSkillsArray;
	}
}
