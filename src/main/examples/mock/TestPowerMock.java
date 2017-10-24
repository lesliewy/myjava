package examples.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * powermock 可以mock new、static、final、private。
 * @RunWith(PowerMockRunner.class)
   @PrepareForTest(IntentionScoreBatch.class)
   如果new、static、final、private等在IntentionScoreBatch.java中，则需要添加以上两个.

   @spy 注解必须要new, 这个和Mockito中不同:
 @InjectMocks
 @Spy
 private IntentionScoreBatch      intentionScoreBatch = new IntentionScoreBatch();


 * Created by leslie on 2017/7/8.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Service.class)
public class TestPowerMock {

    @InjectMocks
    private Service service;

    @Test
    public void test1(){

    }
}
