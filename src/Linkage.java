// Linkage class
public class Linkage {
	
	public double[][] Feat2Dist(double[][] data, int nd, int nf, String dist){
		double[][] D = new double[nd][nd];
		int count=1;
		double xsum=0;
		
		switch (dist) {
		case "Euclid":	// Metric: Euclidean distance
			
			for(int i=0; i<nd; i++) {
				for(int j=count; j<nd; j++) {					
					for(int k=0; k<nf; k++) {
						xsum = xsum + Math.pow((data[i][k]-data[j][k]),2);
					}
					D[i][j]=Math.sqrt(xsum);
					xsum = 0;
				}
				count = count+1;
			}
			D = Linkage.matUcopy2L(D);
			
			break;
		case "SEuclid":	// Metric: Scaled Euclidean distance
			double[] X = new double[nd];
			double[] sigma = new double[nf];
			double val;
			
			StatCalc stat = new StatCalc();
			for(int i=0; i<nf; i++) {
				X = getCul(data,i);
				sigma[i] = stat.Var(X);
			}
			
			for(int i=0; i<nd; i++) {
				for(int j=count; j<nd; j++) {					
					for(int k=0; k<nf; k++) {
						val = ((Math.pow((data[i][k]-data[j][k]),2)) / sigma[k]);
						if(Double.isNaN(val) == false) {
							xsum = xsum + val;
						}
					}
					D[i][j]=Math.sqrt(xsum);
					xsum = 0;
				}
				count = count+1;
			}
			D = Linkage.matUcopy2L(D);
			
			break;
		case "City":	// Metric: city block distance

			for(int i=0; i<nd; i++) {
				for(int j=count; j<nd; j++) {					
					for(int k=0; k<nf; k++) {
						xsum = xsum + Math.abs(data[i][k]-data[j][k]);
					}
					D[i][j] = xsum;
					xsum = 0;
				}
				count = count+1;
			}
			D = Linkage.matUcopy2L(D);
			
			break;		
		case "Chebyshev":	// Metric: Chebyshev distance
			double dmax = 0;
			double a = 0;
			
			for(int i=0; i<nd; i++) {
				for(int j=count; j<nd; j++) {					
					for(int k=0; k<nf; k++) {
						a = Math.abs(data[i][k]-data[j][k]);
						if(dmax < a) {
							dmax = a;
						}
					}
					D[i][j] = dmax;
					dmax=0;
				}
				count=count+1;
			}
			D = Linkage.matUcopy2L(D);
			
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
	
	private static double[] getCul(double[][] matrix, int cul) {
		double[] X = new double[matrix.length];
		for(int i=0; i<matrix.length; i++) {
			X[i] = matrix[i][cul];
		}
		return X;
	}
}
