package amg.net.filewalker;

import java.io.File;

public class FileBean {
	private File file;
	private int lineCount=0;
	private int REGEXCount=0;
	private int lettersCount=0;
	
	public FileBean(File file) {
		super();
		this.file=(file);
	}

	public File getFile() {
		return file;
	}

	public int getLineCount() {
		return lineCount;
	}

	public void incrementLineCount() {
		lineCount++;
	}

	public int getREGEXCount() {
		return REGEXCount;
	}

	public void incrementREGEXCount() {
		REGEXCount++;
	}

	public int getLettersCount() {
		return lettersCount;
	}

	public void incrementLettersCount() {
		lettersCount++;
	}
}
