package amg.net.filewalker;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.IOFileFilter;

import amg.net.filewalker.processors.IProcessor;

public class DataBaseImitator {

	private List<IOFileFilter> filterList = new ArrayList<IOFileFilter>();

	private AndOrEnum filterFlag;

	private List<IProcessor> processorList = new ArrayList<IProcessor>();

	private List<FileBean> filtredList = new ArrayList<FileBean>();
	
	public List<IOFileFilter> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<IOFileFilter> filterList) {
		this.filterList = filterList;
	}

	public AndOrEnum getFilterFlag() {
		return filterFlag;
	}

	public void setFilterFlag(AndOrEnum filterFlag) {
		this.filterFlag = filterFlag;
	}

	public List<IProcessor> getProcessorList() {
		return processorList;
	}

	public void setProcessorList(List<IProcessor> processorList) {
		this.processorList = processorList;
	}

	public List<FileBean> getFiltredList() {
		return filtredList;
	}

	public void setFiltredList(List<FileBean> filtredList) {
		this.filtredList = filtredList;
	}


}
