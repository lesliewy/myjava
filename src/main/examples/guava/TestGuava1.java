/**
 * 
 */
package examples.guava;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

/**
 * @author leslie
 *
 */
public class TestGuava1 {
	
	@Test
	public void test1(){
		// 构造List.
		List<Person> peoples = Lists.newArrayList(new Person("bowen", 27),
				new Person("bob", 20),
				new Person("Katy", 18),
				new Person("Logon", 24));
		
		//filter pattern 选取年龄大于20的. 使用Predicate来封装函数: public interface Predicate<T> 接收一个泛型.
		List<Person> oldPeoples = Lists.newArrayList(Collections2.filter(peoples, ageBiggerThan(20)));
		System.out.println("oldPeoples: " + oldPeoples);
		
		// 名字中包含b的.
		List<Person> namedPeoples = Lists.newArrayList(Collections2.filter(peoples, nameContains("b")));
		System.out.println("namedPeoples: " + namedPeoples);
		
		// 年龄大于20 且 名字中包含b的.
		List<Person> filteredPeoples = Lists.newArrayList(Collections2.filter(peoples, 
				Predicates.and(ageBiggerThan(20), nameContains("b"))));
		System.out.println("filteredPeoples: " + filteredPeoples);
		
		//Map pattern 获取所有人的名字.   使用Function来封装函数: public interface Function<F, T> 接受2个泛型，一个用于输入，一个用于返回.
		List<String> names = Lists.newArrayList(Collections2.transform(peoples, getName()));
		System.out.println("names: " + names);
		
		// reduce pattern 求所有人年龄的和.  Guava中没有提供，自己实现.
		
		// 年龄 >= 20的所有人的名称.
		List<String> filteredNames = Lists.newArrayList(Collections2.transform(Collections2.filter(peoples, ageBiggerThan(20)),
				getName()));
		System.out.println("filteredNames: " + filteredNames);
		// Fluent pattern 来修改上面的
		List<String> filteredNames1 = FluentIterable.from(peoples).filter(ageBiggerThan(20)).transform(getName()).toList();
		System.out.println("filteredNames1: " + filteredNames1);
		
	}

	private Function<Person, String> getName() {
		return new Function<Person, String>(){
			@Override
			public String apply(Person person) {
				return person.getName();
			}
		};
	}
	
	@Test
	public void test2(){
		
	}

	private Predicate<Person> nameContains(final String str) {
		return new Predicate<Person>(){
			@Override
			public boolean apply(Person person) {
				return person.getName().contains(str);
			}
		};
	}

	/**
	 * @return
	 */
	private Predicate<Person> ageBiggerThan(final int age) {
		return new Predicate<Person>(){
			@Override
			public boolean apply(Person person) {
				return person.getAge() > age;
			}
		};
	}
}

class Person {
	String name;
	int age;
	
	Person(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString(){
		return "name: " + name + " age: " + age;
	}
}