package amg.net.filewalker;

import static org.junit.Assert.*;

import org.junit.Test;

public class SingleFileWalkerTest {

	@Test
	public void testSingletonCreating() {
		SingletonFileWalker firstInstance=SingletonFileWalker.getInstance();
		SingletonFileWalker secondInstance=SingletonFileWalker.getInstance();
		assertEquals(firstInstance,secondInstance);
	}
}
