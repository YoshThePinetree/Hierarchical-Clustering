import java.util.ArrayList;
import java.util.List;

// class for clustering from the cluster tree
public class Clustering {
	public int[] group(double[][] Z, int nd, double cut){
		int [] C = new int [nd];
		int gp = 0;	// the place cluster tree is cut
		int nc=1;	// the number of clusters
		for(int i=Z.length-1; i>0; i--) {	// to find the point cutting cluster tree
			if(cut>Z[i][2] && cut>Z[i-1][2]) {
				gp=i;
				break;
			}
			nc = nc+1;
		}
		
		///// grouping /////
		int n1, n2, m=0;
		int [] Y = new int [nd];	// root vector
		int [] Y1 = new int[nc];
		double [] Z1 = new double [Z.length];
		double [] Z2 = new double [Z.length];
		Z1=getCul(Z,0);
		Z2=getCul(Z,1);
		//int i=0;
		for(int i=0; i<nd; i++) {  // loop for cluster grouping (numbering): i is the data number
			n1=findvecsingle(Z1,i);	
			n2=findvecsingle(Z2,i);
			m = n1+n2-Z1.length;	// the address of i
			
			if(m>gp){	// if the address if out of cluster cutting point
				Y[i]=i;
				
			}else{
				while(m<=gp){
					n1=findvecsingle(Z1,(int)Z[m][3]);	
					n2=findvecsingle(Z2,(int)Z[m][3]);
					m = n1+n2-Z1.length;	// the address of i
				}
				if(n1==Z1.length){	// the element is in n2 side
					Y[i]=(int)Z[m][1];
				}else if(n2==Z1.length) {
					Y[i]=(int)Z[m][0];
				}
				
			}	
		}
			
		Y1=unique(Y);	// unique element: the length is the number of clusters 
		for(int i=0; i<nd; i++){
			for(int j=1; j<=nc; j++) {
				if(Y[i]==Y1[j-1]){
					C[i]=j;
				}
			}
			
		}
		
		return C;	// building cluster tree
	}
	
	private static int findvecsingle(double[] vec, int n) {	// find single element from a vector
		int a=vec.length;	// if there is no element n in vec, return the length of vec
		for(int i=0; i<vec.length; i++) {
			if(vec[i]==n) {
				a = i;
			}
		}
		return a;	// return a = the element number
	}
	
	// Get a column from a matrix
	private static double[] getCul(double[][] matrix, int cul) {
		double[] X = new double[matrix.length];
		for(int i=0; i<matrix.length; i++) {
			X[i] = matrix[i][cul];
		}
		return X;
	}
	
	// Get unique elements from a vector
	private static int[] unique(int[] nums) {
		List<Integer> list = new ArrayList<>();
		for(int num:nums) {
			if(!list.contains(num)) {
				list.add(num);
			}
		}
		
		int[] newNums = new int[list.size()];
		for(int i=0; i<newNums.length; i++) {
			newNums[i]=list.get(i);
		}
		
		return newNums;
	}
	
}
