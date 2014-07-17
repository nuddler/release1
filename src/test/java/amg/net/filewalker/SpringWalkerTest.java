package amg.net.filewalker;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.IOFileFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import amg.net.filewalker.processors.IProcessor;
import amg.net.filewalker.processors.LineCountProcessor;
import amg.net.filewalker.processors.REGEXCountProcessor;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/test-context.xml" })
public class SpringWalkerTest {

	private static final String FILE_PATH = "/home/praktykant/test";

	@Autowired
	FileWalker fileWalker;
	int MIN_FILE_NO = 1;
	
	@Autowired
	AppConfiguration cfg;
	
	@Autowired
	List<IOFileFilter> filterList;

	private List<IProcessor> processorList=new ArrayList<IProcessor>();
	
	@Test
	public void positiveTest()  {
		fileWalker.orderFileFilterBuild(filterList, cfg.getType());
		
		processorList.add(new LineCountProcessor());
		processorList.add(new REGEXCountProcessor(".*1.*"));
		fileWalker.setProcessorList(processorList);
		fileWalker.walk(FILE_PATH);
		ShowMachine.showPath(fileWalker.getFiltredList());
		List<FileBean> testList=fileWalker.getFiltredList();
		assertEquals(1,testList.size());
	}
}
