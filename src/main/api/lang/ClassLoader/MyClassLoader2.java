/**
 * Project Name:MyJava  
 * File Name:MyClassLoader2.java  
 * Package Name:api.lang.ClassLoader  
 * Date:Oct 8, 20134:59:41 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.lang.ClassLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.logging.Logger;

public class MyClassLoader2 extends ClassLoader {
    private String baseDir;
    private static final Logger LOG = Logger.getLogger(MyClassLoader2.class.getName());

    public MyClassLoader2(ClassLoader parent, String baseDir) {
        super(parent);
        this.baseDir = baseDir;
    }

    protected Class findClass(String name) throws ClassNotFoundException {
        LOG.info("findClass " + name);
        byte[] bytes = loadClassBytes(name);
        Class theClass = defineClass(name, bytes, 0, bytes.length);//A
        if (theClass == null)
            throw new ClassFormatError();
        return theClass;
    }

    private byte[] loadClassBytes(String className) throws ClassNotFoundException {
        try {
            String classFile = getClassFile(className);
            FileInputStream fis = new FileInputStream(classFile);
            FileChannel fileC = fis.getChannel();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            WritableByteChannel outC = Channels.newChannel(baos);
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (true) {
                int i = fileC.read(buffer);
                if (i == 0 || i == -1) {
                    break;
                }
                buffer.flip();
                outC.write(buffer);
                buffer.clear();
            }
            fis.close();
            return baos.toByteArray();
        } catch (IOException fnfe) {
            throw new ClassNotFoundException(className);
        }
    }

    private String getClassFile(String name) {
        StringBuffer sb = new StringBuffer(baseDir);
        name = name.replace('.', File.separatorChar) + ".class";
        sb.append(File.separator + name);
        return sb.toString();
    }
}
