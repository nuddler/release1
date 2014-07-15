package amg.net.filewalker.processors;

import java.util.regex.Pattern;

import amg.net.filewalker.FileBean;

public class REGEXCountProcessor extends AbstractFileProcessor {

	private Pattern pattern;

	public REGEXCountProcessor(String pattern) {
		this.pattern =Pattern.compile(pattern);
	}

	@Override
	public void process(FileBean readFileBean) {
		initProcessing(readFileBean);
		
		while (openedFile.hasNext(pattern)) {
			readFileBean.incrementREGEXCount();
			openedFile.next(pattern);
		}
		openedFile.hasNext(pattern);
	}

	public void setPattern(String paterrn) {
		pattern=Pattern.compile(paterrn);
	}

}
