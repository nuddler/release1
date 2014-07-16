package amg.net.filewalker;

import java.util.ArrayList;

import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.SizeFileFilter;

import amg.net.filewalker.processors.IProcessor;
import amg.net.filewalker.processors.LettersCountProcessor;
import amg.net.filewalker.processors.LineCountProcessor;
import amg.net.filewalker.processors.REGEXCountProcessor;

public class WalkingController {
	
	public void run(String path) {
		FileWalker newFileWalker = new FileWalker();
		setup(newFileWalker);
		newFileWalker.setReportHolder(new Report());
		newFileWalker.walk(path);
		System.out.println("File list in path "+path+":");
		ShowMachine.showPath(newFileWalker.getFileList());
		System.out.println("Filtred: ");
		ShowMachine.showPath(newFileWalker.getFiltredList());
	}
	
	
	private void setup(FileWalker worker){
		ArrayList<IOFileFilter> filters=new ArrayList<IOFileFilter>();
		filters.add(new SizeFileFilter(17));
		filters.add(new RegexFileFilter(".*3.*"));
		ArrayList<IProcessor> processors = new ArrayList<IProcessor>();
		processors.add(new LineCountProcessor());
		processors.add(new LettersCountProcessor());
		processors.add(new REGEXCountProcessor(".*1.*"));
		worker.orderFileFilterBuild(filters, AndOrEnum.AND);
		worker.setProcessorList(processors);
	}
}