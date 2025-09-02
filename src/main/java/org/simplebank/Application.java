package org.simplebank;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {
    public static void main(String [] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        Interface.start();


    }
}
