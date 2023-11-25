package functions;

import java.util.ArrayList;
import java.util.List;

public class FullyConected {

	public static List<Double> fullyConected(List<Double> A, List<Double> B) {

		List<Double> C = new ArrayList<Double>();
		
		int nCols = B.size()/A.size();
	
		for(int i = 0; i < nCols; i++) {
			double sum = 0.0;
			for(int j = 0; j < A.size(); j++) {
				sum += A.get(j)*B.get(i+j*nCols);
			}
			
			C.add(sum);
		}
		
		return C;
	}
}
