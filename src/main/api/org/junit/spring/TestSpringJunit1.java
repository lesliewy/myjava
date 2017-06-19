/**
 * Project Name:MyJava  
 * File Name:TestSpringJunit1.java  
 * Package Name:api.org.spring.junit  
 * Date:Jan 1, 20134:10:28 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.org.junit.spring;


/**
 * ClassName: TestSpringJunit1 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Jan 1, 2013 4:10:28 PM <br/>  
 *  
 * @author Leslie  
 * @version   
 * @since version 1.0  
 */
/**
 * 1, spring Junit测试中如果要使用spring自动注入特性, 必须添加 @RunWith  和 @ContextConfiguration 
 *     locations 只需指定 productDao需要的配置文件就行:  locations = { "classpath:conf/spring/rps-impl.xml","classpath:conf/spring/rps-da.xml","classpath:conf/spring/rps-res.xml" }
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:conf/spring/*.xml" })
public class TestSpringJunit1 {
    
    @Autowired
    ProductDao productDao;
    
    public void testFindProductById(){
        productDao.findProductById(22L);
    }
}
