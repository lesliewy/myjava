package wy.test1;

import org.junit.Test;

/**
 * Created by leslie on 2017/8/11.
 */
public class Test2 {

    private static int COUNT_BITS = Integer.SIZE - 3;
    private static int CAPACITY   = (1 << COUNT_BITS) - 1;

    @Test
    public void test1() {
        int RUNNING = -1 << COUNT_BITS;
        int SHUTDOWN = 0 << COUNT_BITS;
        int STOP = 1 << COUNT_BITS;
        int TIDYING = 2 << COUNT_BITS;
        int TERMINATED = 3 << COUNT_BITS;

        System.out.println("COUNT_BITS: " + COUNT_BITS + "; RUNNING: " + RUNNING + "; SHUTDOWN: " + SHUTDOWN
                           + "; STOP: " + STOP + "; TIDYING:" + TIDYING + "; TERMINATED: " + TERMINATED);
        System.out.println("CAPACITY: " + CAPACITY);
        System.out.println("CAPACITY binary: " + Integer.toBinaryString(CAPACITY));
        System.out.println("~CAPACITY: " + ~CAPACITY);
        System.out.println("~CAPACITY binary: " + Integer.toBinaryString(~CAPACITY));
        System.out.println("runStateOf(20): " + runStateOf(20));
        System.out.println("workerCountOf(20): " + workerCountOf(20));
        System.out.println("1:  " + Integer.toBinaryString(1));
        System.out.println("-1: " + Integer.toBinaryString(-1));
    }

    private static int runStateOf(int c)     {
        System.out.println(Integer.toBinaryString(c));
        return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    @Test
    public void test2(){
        int i = 0;
        int j = 0;
        System.out.println(i++);
        System.out.println(++j);
    }

}
