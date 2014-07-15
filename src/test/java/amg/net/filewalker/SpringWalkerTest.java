package amg.net.filewalker;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.io.filefilter.IOFileFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/test-context.xml" })
public class SpringWalkerTest {

	private static final String FILE_PATH = "/home/praktykant/test";

	@Autowired
	FileWalker fileWalker;
	int MIN_FILE_NO = 1;
	@Autowired
	AndOrEnum filterFlag;
	@Autowired
	List<IOFileFilter> filterList;
	
	@Test
	public void positiveTest()  {
		fileWalker.orderFileFilterBuild(filterList, filterFlag);
		fileWalker.walk(FILE_PATH);
		ShowMachine.showPath(fileWalker.getFiltredList());
		List<FileBean> testList=fileWalker.getFiltredList();
		assertTrue(testList.size() == 1);
	}
}
