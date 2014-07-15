package amg.net.filewalker;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.SizeFileFilter;
import org.junit.Before;
import org.junit.Test;

import amg.net.filewalker.errors.BadFileException;
import amg.net.filewalker.processors.AbstractFileProcessor;
import amg.net.filewalker.processors.LettersCountProcessor;
import amg.net.filewalker.processors.LineCountProcessor;
import amg.net.filewalker.processors.REGEXCountProcessor;

public class FileWalkerTest {

	//loger ERROR amg, drugi INFO nizej niz amg addytywność
	private static final String BAD_PATH = "\\/g/hdgh/h/ome/praktykant/test";
	private static final String PATH = "/home/praktykant/test";
	private static final String FILE_PATH = "/home/praktykant/test/1";
	
	private static FileWalker newFileWalker;
	
	private static AbstractFileProcessor processor;
	private static ArrayList<IOFileFilter> filterTestList;
	
	@Before
	public void before() {
		newFileWalker = new FileWalker();
		filterTestList = new ArrayList<IOFileFilter>();
	}
	@Test
	public void testEndToEnd() {
		WalkingController controller=new WalkingController();
		controller.run(PATH);
	}
	
	@Test(expected=BadFileException.class)
	public void testWalkBadPath() throws NullPointerException{
		newFileWalker.setFilterFlag(AndOrEnum.AND);
		newFileWalker.walk(BAD_PATH);
	}

	@Test
	public void testBySizeFiltring() {
		filterTestList.add(new SizeFileFilter(17));
		IOFileFilter mockFilter=mock(IOFileFilter.class);
		when(mockFilter.accept(any(File.class))).thenReturn(true);
		filterTestList.add(mockFilter);
		newFileWalker.orderFileFilterBuild(filterTestList, AndOrEnum.AND);
		newFileWalker.walk(PATH);
	    assertEquals(5, newFileWalker.getFiltredList().size());
	}
	
	@Test
	public void testByREGEXFiltring() {
		filterTestList = new ArrayList<IOFileFilter>();
		filterTestList.add(new RegexFileFilter(".*3.*"));
		IOFileFilter mockFilter=mock(IOFileFilter.class);
		when(mockFilter.accept(any(File.class))).thenReturn(true);
		filterTestList.add(mockFilter);
		newFileWalker.orderFileFilterBuild(filterTestList, AndOrEnum.AND);
		newFileWalker.walk(PATH);
		assertEquals(4, newFileWalker.getFiltredList().size());
	}

	@Test
	public void testListFromWalk() {
		filterTestList.add(new RegexFileFilter(".*3.*"));
		filterTestList.add(new SizeFileFilter(17));
		newFileWalker.orderFileFilterBuild(filterTestList, AndOrEnum.AND);
		newFileWalker.walk(PATH);
		assertEquals(3, newFileWalker.getFiltredList().size());
	}

	@Test
	public void testLineCounting(){
		processor = new LineCountProcessor();
		FileBean source= new FileBean(new File(FILE_PATH));
		processor.process(source);
		assertEquals(3, source.getLineCount());
	}

	@Test
	public void testLettersCounting() {
		processor = new LettersCountProcessor();
		FileBean source= new FileBean(new File(FILE_PATH));
		processor.process(source);
		assertEquals(3, source.getLettersCount());
	}
	
	@Test
	public void testREGEXCounting() {
		processor = new REGEXCountProcessor(".*1.*");
		FileBean source= new FileBean(new File(FILE_PATH));
		processor.process(source);
		assertEquals(1, source.getREGEXCount());
	}
}
