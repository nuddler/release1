package amg.net.filewalker.filters;
import java.io.File;


public  class FilterBySize implements IFileFilter {

	private int size;
	
	public FilterBySize(int size) {
		super();
		this.size = size;
	}
	
	@Override
	public boolean filtr(File readFile) {
		return readFile.length()>size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "by size (more than "+size+")";
	}

}
