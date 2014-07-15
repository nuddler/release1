package amg.net.filewalker;

public class SingletonFileWalker extends FileWalker {
	
		private static SingletonFileWalker instance;
		
		private SingletonFileWalker(){
			
		}
		
		public static SingletonFileWalker getInstance(){
			if(instance==null){
				instance=new SingletonFileWalker();
			}
			return instance;
		}
}
