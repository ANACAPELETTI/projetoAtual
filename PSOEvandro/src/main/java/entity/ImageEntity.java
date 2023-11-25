package entity;

import java.util.ArrayList;
import java.util.List;

public class ImageEntity {

	private List<Double> data;
	private char label;

	public List<Double> getData() {
		return new ArrayList<Double>(this.data);
	}

	public char getLabel() {
		return label;
	}

	public ImageEntity(List<Double> data, char label) {
		this.data = data;
		this.label = label;
	}

	public void setData(List<Double> data) {
		this.data = data;
	}

	@Override
	public String toString() {

		String s = label + ", \n";

		for (int i = 0; i < data.size(); i++) {
			s += data.get(i) + ", ";
			
		}
		s += "\n";
		return s;
	}

}
