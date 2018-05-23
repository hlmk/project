package cn.cht.springboottest.javaconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * 测试
 */
public class Main {


    public static void main(String[] args) {
        //通过java配置来初始化spring容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        //获取spring容器中的bean对象
        UserService userService = context.getBean(UserService.class);

        //调用对象中的方法
        List<User> users = userService.queryUserList();
        for(User user : users){
            System.out.println(user.getUsername() + ", " + user.getPassword() + ", " + user.getAge());
        }

        //销毁容器
        context.destroy();
    }

}
