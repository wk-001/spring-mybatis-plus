package test;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        IPage<User> page = userService.page(new Page<User>(0, 2));
        List<User> list = page.getRecords();
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

    //全表删除测试
    @Test
    public void delAll(){
        userService.remove(null);
    }

    //测试乐观锁,版本号与数据库一样才进行更新操作
    @Test
    public void happyLock(){
        User user = new User();
        user.setId(5l);
        user.setName("bb");
        user.setAge(24);
        user.setEmail("1234fasd@qq.com");
        user.setVersion(1);
        userService.updateById(user);
    }

    //逻辑删除测试；使用mp自带方法删除和查找都会附带逻辑删除功能 (自己写的xml不会)
    @Test
    public void logicDelete(){
        userService.removeById(4);
        userService.getById(4);     //自带方法查不到被逻辑删除的数据
    }

    //自定义方法
    @Test
    public void custom(){
        User userById = userService.getUserById(1);
        System.out.println("userById = " + userById);
    }
}
