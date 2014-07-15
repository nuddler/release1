package amg.net.filewalker.processors;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import amg.net.filewalker.FileBean;
import amg.net.filewalker.processors.errors.FileBeanNullException;


public abstract class AbstractFileProcessor implements IProcessor {
	
	protected Scanner openedFile;
	static final Logger logger = LogManager.getLogger(AbstractFileProcessor.class);

	private void openFile(FileBean readFileBean) throws FileNotFoundException {
			setOpenedFile(new Scanner(readFileBean.getFile()));
	}
	
    protected void initProcessing(FileBean readFileBean) {
		try {
			openFile(readFileBean);
			if(logger.isDebugEnabled()){
			logger.debug("Opening file "+readFileBean.getFile().getAbsolutePath());
			}
		} catch (FileNotFoundException e) {
			logger.error("File("+readFileBean.getFile().getAbsolutePath()+" dosen't exist",new FileNotFoundException());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("File ("+readFileBean.getFile().getAbsolutePath()+") was opened");
		}
	}

	public Scanner getOpenedFile() {
		return openedFile;
	}

	public void setOpenedFile(Scanner openedFile) {
		this.openedFile = openedFile;
	}
}