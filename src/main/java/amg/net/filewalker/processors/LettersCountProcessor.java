package amg.net.filewalker.processors;

import amg.net.filewalker.FileBean;
import amg.net.filewalker.processors.errors.FileBeanNullException;

public class LettersCountProcessor extends AbstractFileProcessor {

	@Override
	public void process(FileBean readFileBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start processing (by +"+this.getClass().getSimpleName()+")");
		}
		if(readFileBean==null){
			logger.error("FileBean is null (in "+this.getClass().getSimpleName()+")", new FileBeanNullException());
		}
		initProcessing(readFileBean);
		while (openedFile.hasNext()) {
			readFileBean.incrementLettersCount();
			openedFile.next();
		}		
		if (logger.isDebugEnabled()) {
			logger.debug("Processing finished");
		}
	}
}