// Linkage class
public class Linkage {
	public double[][] ltree(double[][] data, int nd, String lmtd){	// building cluster tree
		double[][] Z = new double[nd-1][4];	// cluster linkage tree: output
		int ndx = nd;
		double mind;	// the minimum distance among data
		int count=1;
		int i1=0, j1=0;
		double[] vr = new double[nd];
		double[] vc = new double[nd];
		double[] v1 = new double[nd];
		int l=1;	// counter
		int k=nd-1;	
		int [] vstd = new int [nd+nd-1];	// vector already visited by merging considering future size
		double [][] dataS = new double [nd+nd-1][nd+nd-1];	// data storage
		double[] vr1 = new double[nd+nd-1];
		double[] vc1 = new double[nd+nd-1];
		double[] v11 = new double[nd+nd-1];		
		
		switch (lmtd) {
			case "Single":	// Linkage: the nearest neighbor distance
				mind=1000;
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
				Z[0][3]=ndx;
				vr = getCul(data,i1);
				vc = getCul(data,j1);
				v1 = compVecs(vr,vc, "min");	// clustered vector
				
				// loop
				
				dataS = MatrixCopy(dataS,data,nd);
				for(int i=0; i<nd; i++) {
					dataS[i][nd] = v1[i];
					dataS[nd][i] = v1[i];
				}
				
				vstd[i1] = 1;
				vstd[j1] = 1;
				
				while(k>1) {
					nd=nd+1; // extended dimension
					
					mind=1000;
					count=1;
					i1=0;
					j1=0;
					for(int i=0; i<nd; i++) {
						if(vstd[i]!=1) {
							for(int j=count; j<nd; j++) {
								if(vstd[j]!=1) {
									if(dataS[i][j]<mind) {
										mind=dataS[i][j];							
										i1=i;
										j1=j;
									}								
								}
							}
						}
						count=count+1;
					}
					Z[l][0]=(double)i1;
					Z[l][1]=(double)j1;
					Z[l][2]=mind;
					ndx=ndx+1;
					Z[l][3]=ndx;
					
					vr1 = getCul(dataS,i1);
					vc1 = getCul(dataS,j1);
					v11 = compVecs(vr1,vc1, "min");	// clustered vector

					for(int i=0; i<dataS.length; i++) {
						dataS[i][nd] = v11[i];
						dataS[nd][i] = v11[i];
					}
										
					vstd[i1] = 1;	// newly merged ones
					vstd[j1] = 1;
					
					k=k-1;
					l=l+1;
				}				
			break;
			
			case "Complete":	// Linkage: the farthest neighbor distance
				mind=-1;
				for(int i=0; i<nd; i++) {
					for(int j=count; j<nd; j++) {
						if(data[i][j]>mind) {
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
				Z[0][3]=ndx;
				vr = getCul(data,i1);
				vc = getCul(data,j1);
				v1 = compVecs(vr,vc, "max");	// clustered vector
				
				// copy v1 to data
				
				// loop
				
				dataS = MatrixCopy(dataS,data,nd);
				for(int i=0; i<nd; i++) {
					dataS[i][nd] = v1[i];
					dataS[nd][i] = v1[i];
				}
								
				vstd[i1] = 1;
				vstd[j1] = 1;
				
				while(k>1) {
					nd=nd+1; // extended dimension
					
					mind=-1;
					count=1;
					i1=0;
					j1=0;
					for(int i=0; i<nd; i++) {
						if(vstd[i]!=1) {
							for(int j=count; j<nd; j++) {
								if(vstd[j]!=1) {
									if(dataS[i][j]>mind) {
										mind=dataS[i][j];							
										i1=i;
										j1=j;
									}								
								}
							}
						}
						count=count+1;
					}
					Z[l][0]=(double)i1;
					Z[l][1]=(double)j1;
					Z[l][2]=mind;
					ndx=ndx+1;
					Z[l][3]=ndx;
					
					vr1 = getCul(dataS,i1);
					vc1 = getCul(dataS,j1);
					v11 = compVecs(vr1,vc1, "max");	// clustered vector

					for(int i=0; i<dataS.length; i++) {
						dataS[i][nd] = v11[i];
						dataS[nd][i] = v11[i];
					}
										
					vstd[i1] = 1;	// newly merged ones
					vstd[j1] = 1;
					
					k=k-1;
					l=l+1;
				}							
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
	
	private static double[] shrinkvec(double[] vec, int n) {	// object matrix: matrix, deleting element number: n
		double[] X = new double[vec.length-1];
		for(int i=0; i<vec.length; i++) {
			if(i<n) {
				X[i] = vec[i];
			}else if(i>n) {
				X[i-1] = vec[i];
			}
		}
		return X;
	}
	
	private static int judgevecsingle(double[] vec, int n) {	// find single element from a vector
		int a=0;
		int b=0;
		for(int i=0; i<vec.length; i++) {
			if(vec[i]==n) {
				b=1;
			}
		}
		return b;	// return b=1 if there is n in vec, otherwise 0

	}
	
	private static int findvecsingle(double[] vec, int n) {	// find single element from a vector
		int a=0;
		for(int i=0; i<vec.length; i++) {
			if(vec[i]==n) {
				a = i;
			}
		}
		return a;	// return a = the element number

	}
	
	private static double[][] MatrixCopy(double[][] X, double[][] Y, int n) {	// 2-D matrix copy
		// Copy Y to X till nth element
		double[][] Z = new double [X.length][X.length];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				Z[i][j]=Y[i][j];
			}
		}
		return Z;

	}
	
	
}