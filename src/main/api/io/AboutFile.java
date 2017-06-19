package api.io;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class AboutFile { 
	public static void main(String[] args) throws IOException{
		
		/*
		 * 
		 */
//		File f = new File("1.txt");  // 这种方式无法 getParent()
		File f = new File("/home/leslie/MyProject/java/MyJava/1.txt");  
		if(f.exists()){
			f.delete();
			System.out.println("delete success.");
		}else{
			f.createNewFile();
			System.out.println("getAbsolutePath:"+f.getAbsolutePath());
			System.out.println("getCanonicalPath:"+f.getCanonicalPath());
			System.out.println("getFreeSpace:"+f.getFreeSpace());
			System.out.println("getName:"+f.getName());
			System.out.println("getPath:"+f.getPath());
			System.out.println("getTotalSpace:"+f.getTotalSpace());
			System.out.println("getUsableSpace:"+f.getUsableSpace());
			System.out.println("getAbsoluteFile:"+f.getAbsoluteFile());
			System.out.println("getCanonicalFile:"+f.getCanonicalFile());
			System.out.println("getParentFile:"+f.getParentFile());
			System.out.println("getParent:"+f.getParent());
			System.out.println("getName:"+f.getParentFile().getName());
			System.out.println("isDirectory:"+f.isDirectory());
			System.out.println("isFile:"+f.isFile());
			System.out.println("canExecute:"+f.canExecute());
			System.out.println("canRead:"+f.canRead());
			System.out.println("canWrite:"+f.canWrite());
			System.out.println("lastModified:"+new Date(f.lastModified()));
		}
		
	}

}
