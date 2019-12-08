// Linkage class
public class Linkage {
	public double[][] ltree(double[][] data, int nd, String lmtd){	// building cluster tree
		double[][] Z = new double[nd-1][3];	// cluster linkage tree: output
		
		switch (lmtd) {
			case "Single":	// Linkage: the nearest neighbor distance
				double mind=1000;	// the minimum distance among data
				int count=1;
				int i1=0, j1=0;
				for(int i=0; i<nd; i++) {
					for(int j=count; j<nd; j++) {
						if(data[i][j]<mind) {
							mind=data[i][j];
							i1=i;
							j1=j;
						}
					}
					count=count+1;
				}
				
				// initial value
				Z[0][0]=(double)i1;
				Z[0][1]=(double)j1;
				Z[0][2]=mind;
				double[] vr = new double[nd];
				double[] vc = new double[nd];
				double[] v1 = new double[nd];
				vr = getCul(data,i1);
				vc = getCul(data,j1);
				v1 = compVecs(vr,vc, "min");	// clustered vector
				
				// loop
				/*
				while(nd>0) {
					nd=nd-1;
					double[][] data1 = new double[nd][nd];	// new dissimilarity matrix
					
				}
					
				for(int i=0; i<nd; i++) {
					for(int j=count; j<nd; j++) {
						if(data[i][j]<mind) {
							mind=data[i][j];
							i1=i;
							j1=j;
						}
					}
					count=count+1;
				}
				*/
				
			break;
			case "Complete":	// Linkage: the farthest neighbor distance
				
			break;
			case "Average":	// Linkage: Average distance
				
			break;
			case "Centroid":	// Linkage: Centroid distance
				
			break;
			case "Ward":	// Linkage: Ward's distance
				
			break;
		}
		
		return Z;
	}
	
	// Get a column from a matrix
	private static double[] getCul(double[][] matrix, int cul) {
		double[] X = new double[matrix.length];
		for(int i=0; i<matrix.length; i++) {
			X[i] = matrix[i][cul];
		}
		return X;
	}
	
	// Conjecting two vectors in terms of "how"
	private static double[] compVecs(double[] vector1, double[] vector2, String how) {
		int len = vector1.length;
		double[] vector = new double[len];
		switch(how) {
			case "min":
				for(int i=0; i<len; i++) {
					if(vector1[i]<vector2[i]) {
						vector[i]=vector1[i];
					}else if(vector1[i]>vector2[i]) {
						vector[i]=vector2[i];
					}else {
						vector[i]=vector1[i];
					}
				}
			break;
			case "max":
				for(int i=0; i<len; i++) {
					if(vector1[i]>vector2[i]) {
						vector[i]=vector1[i];
					}else if(vector1[i]<vector2[i]) {
						vector[i]=vector2[i];
					}else {
						vector[i]=vector1[i];
					}
				}
			break;
		}
		
	return vector;
	}
	
	// Get a column from a matrix
	private static double[][] shrink(double[][] matrix, int n) {	// object matrix: matrix, deleting element number: n
		double[][] X = new double[matrix.length-1][matrix.length-1];
		for(int i=0; i<matrix.length; i++) {
			if(i<n) {
				for(int j=0; j<matrix.length; j++) {
					if(j<n) {
						X[i][j] = matrix[i][j];
					}else if(j>n) {
						X[i][j-1] = matrix[i][j];
					}
				}
			}else if(i>n) {
				for(int j=0; j<matrix.length; j++) {
					if(j<n) {
						X[i-1][j] = matrix[i][j];
					}else if(j>n) {
						X[i-1][j-1] = matrix[i][j];
					}
				}
			}
			
		}
		return X;
	}
}