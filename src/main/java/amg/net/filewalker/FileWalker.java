package amg.net.filewalker;

import static org.apache.commons.io.filefilter.FileFilterUtils.and;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import amg.net.filewalker.errors.BadFileException;
import amg.net.filewalker.processors.AbstractFileProcessor;
import amg.net.filewalker.processors.IProcessor;

public class FileWalker {
	private List<FileBean> fileList = new ArrayList<FileBean>();

	private List<IOFileFilter> filterList = new ArrayList<IOFileFilter>();
	
	private List<IProcessor> processorList = new ArrayList<IProcessor>();

	private List<FileBean> filtredList = new ArrayList<FileBean>();
	
	static final Logger logger = LogManager.getLogger(FileWalker.class);


	public void walk(String path) {
		//logger.
		logger.debug("Starting walking in :"+path);
		Collection<File> fileArray;
		fileList.clear();
		filtredList.clear();
		
		fileArray=FileUtils.listFiles(readFile(path),and(filterList.get(0),filterList.get(1)) , TrueFileFilter.INSTANCE);
		for (File readFile : fileArray) {
			FileBean readFileBean= new FileBean(readFile);
			for (IProcessor processor : processorList) {
				((AbstractFileProcessor)processor).process(readFileBean);
			}
			filtredList.add(readFileBean);
			fileList.add(readFileBean);
		}
	}

	private File readFile(String path) {
		File readFile = new File(path);
		if(readFile==null || !readFile.isDirectory()){
			logger.error("File error or not directory (maybe bad path? Path="+path+")");
			throw new BadFileException("Nie ma katalogu o sciezce "+path);
		}
		return readFile;
	}

	public List<FileBean> getFileList() {
		return fileList;
	}

	public List<IOFileFilter> getFilterList() {
		return filterList;
	}

	public List<FileBean> getFiltredList() {
		return filtredList;
	}

	public void setFilterList(List<IOFileFilter> filterList) {
		this.filterList = filterList;
	}

    public List<IProcessor> getProcessorList() {
		return processorList;
	}
    
    public void setProcessorList(List<IProcessor> processorList) {
		this.processorList = processorList;
	}
}