package recomendation_system;

import java.io.Serializable;
import java.util.HashMap;
public class SourceDataModel implements Serializable{
	private static final long serialVersionUID = -12L;
	private int[][] matrix;
	private HashMap<Integer,Integer> sourceDataToIndex_Map = new HashMap<>();
	
	SourceDataModel(int[][] matrix, HashMap<Integer,Integer> sourceDataToIndex_Map){
		setSourceDataMatrix(matrix, sourceDataToIndex_Map);
	}
	
	SourceDataModel(){
		
	}
	
	public void setSourceDataMatrix(int[][] matrix, HashMap<Integer,Integer> sourceDataToIndex_Map){
		this.matrix = matrix;
		this.sourceDataToIndex_Map = sourceDataToIndex_Map;
	}
	
	public int[][] getSourceDataMatrix(){
		return this.matrix;
	}
	
	public HashMap<Integer,Integer> getSourceDataToIndex_Map(){
		return sourceDataToIndex_Map;
	}

}
