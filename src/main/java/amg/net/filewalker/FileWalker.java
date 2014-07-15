package amg.net.filewalker;

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

//	private List<IOFileFilter> filterList = new ArrayList<IOFileFilter>();
	
	private AndOrEnum filterFlag;
	
	private List<IProcessor> processorList = new ArrayList<IProcessor>();

	private List<FileBean> filtredList = new ArrayList<FileBean>();
	
	static final Logger logger = LogManager.getLogger(FileWalker.class);
	private FileFilterBuilder builder;
	

	public void orderFileFilterBuild(List<IOFileFilter> componentsList, AndOrEnum settings){
		if (logger.isDebugEnabled()) {
			logger.debug("FileFilter order received");
		}
		builder=new FileFilterBuilder();
		if(componentsList==null || componentsList.size()==0 || settings==null){
				logger.error("Bad order settings",new IllegalArgumentException());
		}
		builder.setFilterList(componentsList);
		builder.setFilterFlag(settings);
		if (logger.isDebugEnabled()) {
			logger.debug("FileFilter order prepared");
		}
	}
	
	public void walk(String path) {
		if (logger.isDebugEnabled()) {
			logger.debug("Starting walking in :"+path);
		}
		Collection<File> fileArray;
		fileList.clear();
		filtredList.clear();
		if(builder==null){
			logger.error("Filter was not orderd",new NullPointerException());
		}
		fileArray=FileUtils.listFiles(readFile(path),builder.build(), TrueFileFilter.INSTANCE);
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


	public List<FileBean> getFiltredList() {
		return filtredList;
	}

    public List<IProcessor> getProcessorList() {
		return processorList;
	}
    
    public void setProcessorList(List<IProcessor> processorList) {
		this.processorList = processorList;
	}

	
    public AndOrEnum getFilterFlag() {
		return filterFlag;
	}

	
    public void setFilterFlag(AndOrEnum filterFlag) {
		this.filterFlag = filterFlag;
	}
}