package api.io;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class AboutFileInputStream {
	/*  FileInputStream FileOutputStream 中的输入输出时针对程序来说的，而不是文件.
	 * 1,FileInputStream  用来创建磁盘文件的输入流对象，通过构造函数指定文件路径和文件名.  
	 * 2,FileInputStream 创建实例对象时，指定的文件应当存在且可读.
	 * 3,创建FileInputStream的两种方式：
	 *    a,FileInputStream inOne = new FileInputStream("hello.txt");
	 *    b,File f = new File("hello.txt");
	 *      FileInputStream inTwo = new FileInputStream(f);
	 * 也可以用 new FileInputStream(FileDescriptor)来创建, FileDescriptor: handle to the open file  具体不知道这种方式和上面的有什么不同.
	 * 另外: FileInputStream 还有一个 FileChannel getChannel().
	 */
    @Test
    public void test1(){
        FileInputStream in = null;
        int c = 0;
        try {
            in = new FileInputStream("c:\\xml.dat");
            System.out.println("avaliable:" + in.available());
            while((c = in.read()) != -1){
                System.out.print((char) c);
            }
            System.out.println("==========");
            FileDescriptor fd = in.getFD();
            in = new FileInputStream(fd);
            System.out.println("avaliable:" + in.available());
            
            System.out.println("==========");
            in = new FileInputStream("c:\\xml.dat");
            while((c = in.read()) != -1){
                System.out.print((char) c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        } catch (IOException e) {
            e.printStackTrace();  
        } finally {
            try{
                if ( in != null){
                    in.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
    /*
     * 1, 如果FileInputStream 已经close, 用 in = new FileInputStream( FileDescriptor) 构建的 in 不能使用.
     * 
     */
    @Test
    public void test2(){
        FileInputStream in = null;
        FileDescriptor fd = null;
        int c = 0;
        try {
            in = new FileInputStream("c:\\xml.dat");
            System.out.println("avaliable:" + in.available());
            while((c = in.read()) != -1){
                System.out.print((char) c);
            }
            System.out.println("==========");
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        } catch (IOException e) {
            e.printStackTrace();  
        } finally {
            try{
                if ( in != null){
                    in.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        /*
         * runtime exception: The handle is invalid
         */
//        try {
//            fd = in.getFD();
//            in = new FileInputStream(fd);
//            System.out.println("avaliable:" + in.available());
//        } catch (IOException e) {
//            e.printStackTrace();  
//        }
    }
    
    /*
     * FileInputStream 不支持mark, 如果使用的话报错: java.io.IOException: mark/reset not supported
     *    Buffered 以后再使用.
     * 
     */
    @Test
    public void test3(){
        FileInputStream fin = null;
        int c = 0;
        boolean ismarked = false;
        try {
            fin = new FileInputStream("c:\\xml.dat");
            fin.skip(5);
            if (fin.markSupported()){
                fin.mark(Integer.MAX_VALUE);
                ismarked = true;
            }else{
                System.out.println("FileInputStream do not support mark.");
            }
            while ((c = fin.read()) != -1){
                System.out.print((char) c);
            }
            if (ismarked){
                fin.reset();
            }
            System.out.println("======================");
            while ((c = fin.read()) != -1){
                System.out.print((char) c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        } catch (IOException e) {
            e.printStackTrace();  
        } finally {
            try {
                if (fin != null){
                    fin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();  
            }
        } 
    }
}
