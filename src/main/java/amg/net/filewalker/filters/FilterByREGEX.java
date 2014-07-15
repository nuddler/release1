package amg.net.filewalker.filters;
import java.io.File;


public class FilterByREGEX implements IFileFilter {

	private String regex;
	public FilterByREGEX(String regex) {
		this.regex=regex;
	}

	@Override
	public boolean filtr(File readFile) {
		return readFile.getName().matches(getRegex());
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
	@Override
	public String toString() {
		return "by REGEX ("+regex+")";
	}

}
