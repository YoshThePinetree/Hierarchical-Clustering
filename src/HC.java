import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

// Hierarchical Clustering
public class HC {
	public static void main (String arg[]) throws EncryptedDocumentException, IOException {
		
		////////////////////
		// Initial Config //
		////////////////////		
		// Data read
		Workbook excel = WorkbookFactory.create(new File("C:\\result\\HC\\fisheriris.xlsx"));
		Sheet sheet =excel.getSheet("Sheet1");
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);

		int nd = 150;	// the number of data
		int nf = 4;		// the number of features
		String[] spc = new String[nd];	// the data label
		double[][] meas = new double[nd][nf];	// the data feature
		
		// load from excel file
		for(int i=0; i<nd; i++) {
			row = sheet.getRow(i);
			cell = row.getCell(0);
			spc[i] = cell.getStringCellValue();
		}
		for(int i=0; i<nd; i++) {
			for(int j=0; j<nf; j++) {
				row = sheet.getRow(i);
				cell = row.getCell(j+1);
				meas[i][j] = cell.getNumericCellValue();
			}
		}
		
		/////////////////////////////////
		// Making Dissimilarity Matrix //
		/////////////////////////////////
		String dist = "Chebyshev";
		Linkage link = new Linkage();
		double[][] D = new double[nd][nd];
		D=link.Feat2Dist(meas, nd, nf, dist);
		
		
		
		// write out the objective function value & search log
		String pathname = "C:\\result\\HC\\";
		String sufi= ".csv";
		String fnameF = "D";
		
		WriteResult.Output(D, nd, nd, pathname + fnameF + sufi);
		System.out.println(pathname + "D" + sufi); 
	}
}
