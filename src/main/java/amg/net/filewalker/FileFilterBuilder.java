package amg.net.filewalker;

import static org.apache.commons.io.filefilter.FileFilterUtils.and;
import static org.apache.commons.io.filefilter.FileFilterUtils.or;

import java.util.List;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileFilterBuilder {

	private List<IOFileFilter> filterList;
	private AndOrEnum filterFlag;

	private static final Logger logger = LogManager
			.getLogger(FileFilterBuilder.class);

	public IOFileFilter build() {
		if (logger.isDebugEnabled()) {
			logger.debug("Starting building filtr");
		}
		if (filterFlag == null) {
			logger.error("Filter Flags was not set",
					new IllegalArgumentException());
		}
		IOFileFilter buildedFilter = (filterFlag == AndOrEnum.AND) ? FileFilterUtils
				.trueFileFilter() : FileFilterUtils.falseFileFilter();

		for (IOFileFilter fileFilter : filterList) {
			if (filterFlag == AndOrEnum.AND) {
				buildedFilter = and(buildedFilter, fileFilter);
			} else {
				buildedFilter = or(buildedFilter, fileFilter);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Filtr was created");
		}
		return buildedFilter;
	}

	public AndOrEnum getFilterFlag() {
		return filterFlag;
	}

	public void setFilterFlag(AndOrEnum filterFlag) {
		this.filterFlag = filterFlag;
	}

	public List<IOFileFilter> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<IOFileFilter> filterList) {
		this.filterList = filterList;
	}

}
