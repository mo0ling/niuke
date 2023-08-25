package com.example.niuke;

import com.example.niuke.dao.TestDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
@ContextConfiguration(classes = NiukeApplication.class)
class NiukeApplicationTests implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        System.out.println(applicationContext);
        TestDao dao = applicationContext.getBean(TestDao.class);
        System.out.println(dao.test());
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;//当程序运行applicationContext就会传进来
    }
}
