package test;

import com.wk.pojo.User;
import com.wk.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration    //获取web的IOC
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springMVC.xml"})
public class CodeTest {

    @Autowired
    WebApplicationContext context;


    //虚拟mvc，获取到处理后的结果
    MockMvc mockMvc;

    @Resource
    private UserService userService;

    //使用MockMvc需要进行初始化
    @Before
    public void initMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //发送请求测试controller方法
    @Test
    public void testPage() throws Exception {
        //模拟请求拿到返回值
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/user/getUser")       //这里路径必须要加"/"
                        .param("id", "1"))
                .andReturn();
        String str = result.getResponse().getContentAsString();
        System.out.println("str = " + str);
    }

    @Test
    public void list(){
        List<User> list = userService.list();
        list.forEach(System.out::println);
    }

    @Test
    public void addTest(){
        User user = new User();
        user.setName("22");
        user.setAge(22);
        user.setEmail("1224@qq.com");
        userService.save(user);
        //MyBatisPlus完成插入数据操作后自动将主键返回到对象中
        System.out.println("user.getId() = " + user.getId());
    }
}
