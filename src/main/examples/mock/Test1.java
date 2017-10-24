package examples.mock;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.exceptions.verification.NoInteractionsWanted;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 * @Mock:   mock一个对象，该对象的所有方法都会被mock掉.  int 型返回0，list返回空list.
 * @Spy:    只有手动stub的方法才会被mock,  其他仍然调用以前的方法.
 * @InjectMocks:  将所有的mock注入到该对象中。  可以和 @Spy连用
 *
 *
 * Created by leslie on 2017/6/4.
 */
@RunWith(MockitoJUnitRunner.class)
public class Test1 {

    /**
     * 验证行为.
     */
    @Test
    public void verifyBehavior() {
        // 模拟创建一个List对象
        List mock = Mockito.mock(List.class);
        // 使用mock
        mock.add(1);
        mock.clear();
        // 验证add(1)和clear
        Mockito.verify(mock).add(1);
        // 会报错.
        // Mockito.verify(mock).add(2);
        Mockito.verify(mock).clear();
    }

    /**
     * 验证期望的结果.
     */
    @Test
    public void whenThenReturn() {
        // mock 一个Iterator
        Iterator iterator = Mockito.mock(Iterator.class);
        // 当iterator 第一次调用next()，返回hello, 第n次调用返回 world.
        when(iterator.next()).thenReturn("hello").thenReturn("world");

        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        Assert.assertEquals("hello world world", result);
    }

    /**
     * 验证异常
     */
    @Test(expected = IOException.class)
    public void whenThenThrow() throws IOException {
        OutputStream outputStream = Mockito.mock(OutputStream.class);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        // 预设当流关闭时抛出异常.
        Mockito.doThrow(new IOException()).when(outputStream).close();
        outputStream.close();
    }

    /**
     * 参数匹配
     */
    @Test
    public void withArguments(){
        Comparable comparable = Mockito.mock(Comparable.class);
        when(comparable.compareTo("Test")).thenReturn(1);
        when(comparable.compareTo("Omg")).thenReturn(2);
        Assert.assertEquals(1, comparable.compareTo("Test"));
    }

    /**
     * 匹配任意参数.
     */
    @Test
    public void withUnspecifiedArguments(){
        List list = Mockito.mock(List.class);
        when(list.get(Mockito.anyInt())).thenReturn(1);
        when(list.contains(Mockito.argThat(new IsValid()))).thenReturn(true);
        Assert.assertEquals(1, list.get(1));
        Assert.assertEquals(1, list.get(999));
        Assert.assertTrue(list.contains(1));
        Assert.assertTrue(!list.contains(3));
    }

    private class IsValid extends ArgumentMatcher<List>{
        @Override
        public boolean matches(Object o){
            return ((Integer)o).intValue() == 1 || ((Integer)o).intValue() == 2;
        }
    }

    /**
     * 验证确切的调用次数
     */
    @Test
    public void verifyingNumberOfInvocations(){
        List list = Mockito.mock(List.class);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(3);
        // 验证是否被调用一次，等效于 times(1)
        Mockito.verify(list).add(1);
        Mockito.verify(list, Mockito.times(1)).add(1);

        // 验证是否被调用2次
        Mockito.verify(list, Mockito.times(2)).add(2);

        // 验证是否被调用3次
        Mockito.verify(list, Mockito.times(3)).add(3);

        // 验证是否从未调用过.
        Mockito.verify(list, Mockito.never()).add(4);
        // 验证至少调用一次.
        Mockito.verify(list, Mockito.atLeastOnce()).add(1);
        // 验证至少调用2次
        Mockito.verify(list, Mockito.atLeast(2)).add(2);
        // 验证至多调用3次
        Mockito.verify(list, Mockito.atMost(3)).add(3);
    }

    /**
     * 模拟方法体抛出异常
     */
    @Test(expected = RuntimeException.class)
    public void doThrowWhen(){
        List list = Mockito.mock(List.class);
        Mockito.doThrow(new RuntimeException()).when(list).add(1);
        list.add(1);
    }

    /**
     * 验证执行顺序
     */
    @Test
    public void verificationInOrder(){
        List list = Mockito.mock(List.class);
        List list2 = Mockito.mock(List.class);
        list.add(1);
        list2.add("hello");
        list.add(2);
        list2.add("world");
        // 将需要排序的对象加入inOrder
        InOrder inOrder = Mockito.inOrder(list, list2);
        // 验证执行顺序，不能颠倒.
        inOrder.verify(list).add(1);
        inOrder.verify(list2).add("hello");
        inOrder.verify(list).add(2);
        inOrder.verify(list2).add("world");
    }

    /**
     * 确保模拟对象上无互动发生
     */
    @Test
    public void verifyInteraction(){
        List list = Mockito.mock(List.class);
        List list2 = Mockito.mock(List.class);
        List list3 = Mockito.mock(List.class);
        list.add(1);
        Mockito.verify(list).add(1);
        Mockito.verify(list, Mockito.never()).add(2);
        // 验证零互动
        Mockito.verifyZeroInteractions(list2, list3);
    }

    /**
     * 找出冗余的互动(即未被验证到的)
     */
    @Test(expected = NoInteractionsWanted.class)
    public void findRedundantInteractions(){
        List list = Mockito.mock(List.class);
        list.add(1);
        list.add(2);
        Mockito.verify(list, Mockito.times(2)).add(Mockito.anyInt());
        // 检查是否有未被验证的互动行为.
        Mockito.verifyNoMoreInteractions(list);

        List list2 = Mockito.mock(List.class);
        list2.add(1);
        list2.add(2);
        Mockito.verify(list2).add(1);
        // 仅为list2.add(2)没有被验证，所以下面代码会抛出异常.
        Mockito.verifyNoMoreInteractions(list2);

    }

    /**
     * 使用 @Mock, 必须要有初始化.  或者使用 @RunWith(MockitoJUnitRunner.class)
     */
    @Mock
    private List mockList;
/*
    public Test1(){
        MockitoAnnotations.initMocks(this);
    }
*/

    @Test
    public void shortHand(){
        mockList.add(1);
        Mockito.verify(mockList).add(1);
    }


    /**
     * 连续调用
     */
    @Test(expected = RuntimeException.class)
    public void consecutiveCalls(){
        // 模拟连续调用返回期望值，如果分开，则只有最后一个有效.
        when(mockList.get(0)).thenReturn(0);
        when(mockList.get(0)).thenReturn(1);
        when(mockList.get(0)).thenReturn(2);

        when(mockList.get(1)).thenReturn(0).thenReturn(1).thenThrow(new RuntimeException());
        Assert.assertEquals(2, mockList.get(0));
        Assert.assertEquals(2, mockList.get(0));
        Assert.assertEquals(0, mockList.get(1));
        Assert.assertEquals(1, mockList.get(1));
        // 第三次或更多调用都会抛出异常.
        mockList.get(1);
    }

    /**
     * 使用回调生成期望值
     */
    @Test
    public void answerWithCallback(){
        // 使用Answer来生成期望的返回.
        when(mockList.get(Mockito.anyInt())).thenAnswer(new Answer<Object>(){
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                return "hello world:" + args[0];
            }
        });

        Assert.assertEquals("hello world:0", mockList.get(0));
        Assert.assertEquals("hello world:999", mockList.get(999));
    }

    /**
     * 监控真实对象, 使用spy, 谨慎使用when-then, 改用do-then
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void spyOnRealObjects(){
        List list = new LinkedList();
        List spy = Mockito.spy(list);

        // 下面预设的spy.get(0)会报错，因为会调用真实对象的get(0), 抛出越界异常.
        when(spy.get(0)).thenReturn(3);

        // 使用doReturn-when 可以避免when-thenReturn调用真实对象.
        Mockito.doReturn(999).when(spy).get(999);

        // 预设size()期望值.
        Mockito.when(spy.size()).thenReturn(100);
        // 调用真实对象的api
        spy.add(1);
        spy.add(2);
        Assert.assertEquals(100, spy.size());
        Assert.assertEquals(1, spy.get(0));
        Assert.assertEquals(2, spy.get(1));
        Mockito.verify(spy).add(1);
        Mockito.verify(spy).add(2);
        Assert.assertEquals(999, spy.get(999));
        spy.get(2);
    }

    /**
     * 修改对未预设的调用返回默认期望值.
     */
    @Test
    public void unstubbedInvocations(){
        // mock对象使用Answer来对未预设的调用返回默认期望值.
        List mock = Mockito.mock(List.class, new Answer(){
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return 999;
            }
        });

        // 下面的get(1)没有预设，通常情况下回返回null, 但是使用Answer改变了默认期望值.
        Assert.assertEquals(999, mock.get(1));
        // size()没有预设，通常返回0，但是使用Answer改变了期望值.
        Assert.assertEquals(999, mock.size());
    }

    /**
     * 捕获参数来进一步断言
     */
    @Test
    public void capturingArgs(){
        PersonDao personDao = Mockito.mock(PersonDao.class);
        PersonService personService = new PersonService(personDao);

        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        personService.update(1, "jack");
        Mockito.verify(personDao).update(argument.capture());
        Assert.assertEquals(1, argument.getValue().getId());
        Assert.assertEquals("jack", argument.getValue().getName());
    }

    private class Person{
        private int id;
        private String name;

        Person(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    interface PersonDao{
        public void update(Person person);
    }

    private class PersonService{
        private PersonDao personDao;

        PersonService(PersonDao personDao){
            this.personDao = personDao;
        }

        public void update(int id, String name){
            personDao.update(new Person(id, name));
        }
    }

    /**
     * 真实的部分mock
     */
    @Test
    public void realPartialMock(){
        // 通过spy来调用真实的api
        List list = Mockito.spy(new ArrayList());
        Assert.assertEquals(0, list.size());
        A a = Mockito.mock(A.class);
        Mockito.when(a.doSomething(Mockito.anyInt())).thenCallRealMethod();
        Assert.assertEquals(999, a.doSomething(999));
    }

    private class A{
        public int doSomething(int i){
            return i;
        }
    }

    /**
     * 重置mock
     */
    @Test
    public void resetMock(){
        List list = Mockito.mock(List.class);
        Mockito.when(list.size()).thenReturn(10);
        list.add(1);
        Assert.assertEquals(10, list.size());

        // 重置mock, 清除所有的互动和预设.
        Mockito.reset(list);
        Assert.assertEquals(0, list.size());
    }
}
