// Linkage class
public class Linkage {
	
	public double[][] Feat2Dist(double[][] data, int nd, int nf, String dist){
		double[][] D = new double[nd][nd];
		
		switch (dist) {
		case "Euclid":
			int count=1;
			double xsum=0;
			
			for(int i=0; i<nd; i++) {
				for(int j=count; j<nd; j++) {					
					for(int k=0; k<nf; k++) {
						xsum = xsum + Math.pow((data[i][k]-data[j][k]),2);
					}
					D[i][j]=Math.sqrt(xsum);
				}
				count=count+1;
			}
			D = Linkage.matUcopy2L(D);
			
			break;
		case "City":
			break;		
		case "Minkowski":
			break;
		case "Mahalanobis":
			break;
		case "Cosine":
			break;
		}
		return D;
	}
	
	private static double[][] matrixTranspose(double[][] matrix){
	  int y = matrix.length;
	  int x = matrix[0].length;
	   
	  double[][] newMatrix = new double[x][y];
	   
	  for(int i=0; i<y; i++){
	    for(int j=0; j<x; j++){
	      newMatrix[j][i] = matrix[i][j];
	    }
	  }
	   
	  return newMatrix;
	}
	
	private static double[][] matUcopy2L(double[][] matrix){
	  int y = matrix.length;
	  int x = matrix[0].length;
	   
	  double[][] newMatrix = matrix;
	   
	  for(int i=0; i<y; i++){
	    for(int j=0; j<x; j++){
	      newMatrix[j][i] = matrix[i][j];
	    }
	  }
	   
	  return newMatrix;
	}
}
