import java.io.File;
import java.io.FilenameFilter;

public class Test {
	public static void main(String[] args) {
		File dir = new File("C:\\Users\\QGM\\Desktop");
		String[] fileNames =  dir.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				if(name.contains(".")) return true;
				return false;
			}
		});
		for (String name : fileNames) {
			System.out.println(name);
		}
	}
}
