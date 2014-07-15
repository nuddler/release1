package amg.net.filewalker;
import java.util.List;


public class ShowMachine {

	   public static void showPath(List<FileBean> listOfFile){
	    	for (FileBean readFileBin : listOfFile) {
	    	System.out.println(readFileBin.getFile().getAbsolutePath());
			}
	    }
}