package cn.Test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * mapper以及service层次的测试模板,被自定义测试继承
 * @author liuqiao
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
	{ "classpath:/spring-*.xml"})
@Transactional  
public class MyTest {
	
}
