package amg.net.filewalker;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import amg.net.filewalker.filters.FilterByREGEX;
import amg.net.filewalker.filters.FilterBySize;
import amg.net.filewalker.filters.IFileFilter;
import amg.net.filewalker.processors.FileProcessorAbstract;
import amg.net.filewalker.processors.LettersCountProcessor;
import amg.net.filewalker.processors.LineCountProcessor;
import amg.net.filewalker.processors.REGEXCountProcessor;

public class FileWalkerTest {

	private static ArrayList<IFileFilter> filtersList = new ArrayList<IFileFilter>();
	
	private static final String BAD_PATH = "\\/g/hdgh/h/ome/praktykant/test";
	private static final String PATH = "/home/praktykant/test";
	private static final String FILE_PATH = "/home/praktykant/test/1";
//	private static final String PATH = "C:\\test";
//	private static final String FILE_PATH = "C:\\test\\1.txt";
	
	private static FileWalker newFileWalker;
	private static FileProcessorAbstract processor;
	
	
	@BeforeClass
	public static void init() {
		filtersList.add(new FilterBySize(17));
		filtersList.add(new FilterByREGEX(".*3.*"));
		newFileWalker = new FileWalker(filtersList);
	}
	
	@Test
	public void testEndToEnd() {
		newFileWalker.walk(PATH);
		ShowMachine.showPath(newFileWalker.getFileList());
		int i = 0;
		for (ArrayList<FileBean> fileList : newFileWalker.getFiltredLists()) {
			System.out.println(filtersList.get(i).toString());
			ShowMachine.showPath(fileList);
			i++;
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void testWalkBadPath() throws NullPointerException{
		newFileWalker.walk(BAD_PATH);
	}

	@Test
	public void testBySizeFiltr() {
		newFileWalker.walk(PATH);
		assertEquals(2, newFileWalker.getFiltredLists().get(0).size());
	}
	
	@Test
	public void testByREGEXFiltr() {
		newFileWalker.walk(PATH);
		ArrayList<FileBean> expectedList=new ArrayList<FileBean>();
		expectedList.add(new FileBean(new File(PATH+"/"+"13")));
		expectedList.add(new FileBean(new File(PATH+"/"+"1 (3rd copy)")));
		assertEquals(expectedList.size(), newFileWalker.getFiltredLists().get(1).size());
	}

	@Test
	public void testListFromWalk() {
		newFileWalker.walk(PATH);
		assertEquals(6, newFileWalker.getFileList().size());
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
		processor = new REGEXCountProcessor();
		((REGEXCountProcessor) processor).setPattern(".*1.*");
		FileBean source= new FileBean(new File(FILE_PATH));
		processor.process(source);
		assertEquals(1, source.getREGEXCount());
	}

}
