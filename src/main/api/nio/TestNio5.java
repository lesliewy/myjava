/**
 * 
 */
package api.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Pipe 是2个线程之间的单向数据连接。 Pipe有一个source通道和一个sink通道。 数据会从source通道读取，写到sink通道.
 * 
 * 
 * @author leslie
 *
 */
public class TestNio5 {
	
	public static void main(String[] args){

		Pipe pipe = null;
		ExecutorService exec = Executors.newFixedThreadPool(2);
		try {
			pipe = Pipe.open();
			final Pipe pipeTemp = pipe;
			exec.submit(new Callable<Object>() {
				@Override
				public Object call() throws Exception {
					Pipe.SinkChannel sinkChannel = pipeTemp.sink(); // 向通道中写数据.
					while (true) {
						TimeUnit.SECONDS.sleep(5);
						String newData = "Pipe Test At Time "
								+ System.currentTimeMillis();
						ByteBuffer buf = ByteBuffer.allocate(1024);
						buf.clear();
						buf.put(newData.getBytes());
						buf.flip();

						while (buf.hasRemaining()) {
							System.out.println(buf);
							sinkChannel.write(buf);
						}
					}
				}
			});

			exec.submit(new Callable<Object>() {
				@Override
				public Object call() throws Exception {
					Pipe.SourceChannel sourceChannel = pipeTemp.source(); // 从通道中读数据.
					while (true) {
						TimeUnit.SECONDS.sleep(5);
						ByteBuffer buf = ByteBuffer.allocate(1024);
						buf.clear();
						int bytesRead = sourceChannel.read(buf);
						System.out.println("bytesRead= " + bytesRead);
						while (bytesRead > 0) {
							buf.flip();
							byte b[] = new byte[bytesRead];
							int i = 0;
							while (buf.hasRemaining()) {
								b[i] = buf.get();
								System.out.printf("%X", b[i]);
								i++;
							}
							String s = new String(b);
							System.out.println("====================||" + s);
							bytesRead = sourceChannel.read(buf);
						}
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			exec.shutdown();
		}
	
	}
}
