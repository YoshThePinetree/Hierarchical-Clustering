
public class Clustering {
	public double[] group(double[][] Z, int nd, double cut){
		double [] C = new double [nd];
		int gp;
		int nc=2;
		for(int i=Z.length-1; i>0; i--) {	// to find the point cutting cluster tree
			if(cut>Z[i][3] && cut>Z[i-1][3]) {
				gp=i;
				break;
			}
			nc = nc+1;
		}
		
		///// grouping /////
		//while() {
			
		//}
		
		return C;	// building cluster tree
		
	}
	
}
