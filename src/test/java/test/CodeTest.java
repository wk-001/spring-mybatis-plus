package test;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import java.util.*;

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

    //分页查询
    @Test
    public void list(){
        //参数：当前页 每页显示几条
        Page<User> page = new Page<>(0, 2);
        //查询结果会放到page对象中
        IPage<User> pages = userService.page(page);
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
        /*------------------分页信息--------------------------*/
        System.out.println("数据总数 = " + page.getTotal());
        System.out.println("当前页码 = " + page.getCurrent());
        System.out.println("总页数 = " + page.getPages());
        System.out.println("每页数据条数 = " + page.getSize());
        System.out.println("是否有上一页 = " + page.hasPrevious());
        System.out.println("是否有下一页 = " + page.hasNext());
        System.out.println("page中的数据 = " + page.getRecords());
    }
    //添加测试
    @Test
    public void addTest(){
        /*User user = new User("张三",20,"123@qq.com",1,1);
        userService.save(user); //添加一个对象
        //MyBatisPlus完成插入数据操作后自动将主键返回到对象中
        System.out.println("user.getId() = " + user.getId());*/

        List<User> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User(null,"张三"+i,20+i,"123"+i+"@qq.com",1,1);
            list.add(user);
        }
        //批量添加
        userService.saveBatch(list);
        /*批量添加后的id自动将主键返回到对象中
        for (User user : list) {
            System.out.println("user = " + user.getId());
        }*/
    }

    //修改测试
    @Test
    public void updTest(){
        User user = new User(5,"李四",28,"123.163.com",1,1);
        //userService.updateById(user);   //根据ID修改
        /**
         * 条件构造器
         */
        Wrapper<User> wrapper = new UpdateWrapper<User>()
                .eq("name","张三0")
                /*.eq(StringUtils.isNotEmpty("name"),"name","张三0") 判断传入的name不为空才加入最后生成的sql中
                或者.eq(name!=null,"name","张三0")
                * 相当于xml中的<if test="name!=null"> name = #{name}</if>*/
                .lt("id","20");
        userService.update(user,wrapper);
    }

    //删除测试
    @Test
    public void delTest(){
       // userService.removeById(1l);     //根据ID删除
       /* List<Long> list = new ArrayList<>();
        list.add(5l);
        list.add(6l);
        list.add(7l);
        userService.removeByIds(list); */     //批量删除

        /*Map<String,Object> map = new HashMap<>();
        map.put("age",21);
        userService.removeByMap(map);*/     //根据map进行删除 多个map之间是and的关系

        //条件构造器 删除ID在10到20之间 age大于30 name中有12的
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .between("id",10,20)
                .gt("age",30)
                .like("name",12);
        userService.remove(wrapper);
    }

    //查询测试
    @Test
    public void queryTest(){

        //User user = userService.getById(10l);//根据ID查询一个对象
        /* 根据ID查询多个对象
        List<Long> list = new ArrayList<>();
        list.add(9l);
        list.add(10l);
        list.add(7l);
        Collection<User> users = userService.listByIds(list);
        for (User user1 : users) {
            System.out.println("user1 = " + user1);
        }*/

        /*  根据map查询
        Map<String,Object> map = new HashMap<>();
        map.put("age",22);
        Collection<User> users = userService.listByMap(map);
        for (User user1 : users) {
            System.out.println("user1 = " + user1);
        }*/

        int count = userService.count();//查询所有条数
        System.out.println("count = " + count);
        Wrapper<User> tWrapper = new QueryWrapper<User>().eq("name","张三3");
        int count1 = userService.count(tWrapper);
        System.out.println("count1 = " + count1);
    }

    //全表删除测试 如果配置了SQL阻断解析器 那么该方法不会生效
    @Test
    public void delAll(){
        userService.remove(null);
    }

    //测试乐观锁,版本号与数据库一样才进行更新操作
    @Test
    public void happyLock(){
        User user = new User();
        user.setId(5);
        user.setName("bb");
        user.setAge(24);
        user.setEmail("1234fasd@qq.com");
        user.setVersion(1);
        //userService.updateById(user);     根据id修改数据
        Wrapper<User> updWrapper = new UpdateWrapper<>();
        userService.update(user,updWrapper);
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
        User userById = userService.getUserById(6);
        System.out.println("userById = " + userById);
    }
}
