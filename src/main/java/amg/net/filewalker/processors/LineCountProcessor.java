package amg.net.filewalker.processors;

import amg.net.filewalker.FileBean;

public class LineCountProcessor extends AbstractFileProcessor {

	@Override
	public void process(FileBean readFileBean) {
		initProcessing(readFileBean);
		while(openedFile.hasNextLine()){
        	readFileBean.incrementLineCount();
        	openedFile.nextLine();
        }
	}
}
