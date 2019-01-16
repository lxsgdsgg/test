package com.bashiju.quartz.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreLauncher {
	private static Logger logger =  LoggerFactory.getLogger(CoreLauncher.class);
	 
    /**
     * @param args
     */
    public static void main(String[] args) {
        getLocalip();
        logger.info("开始启动asset");
 
		//第一种   通过其他容器启动如 tomcat ，jboss等 以web服务的形式启动
		
		
		
		// 第二种  通过自定义main函数
	/*	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:dubbo-provider.xml");
		
		context.start();
		
		synchronized (CoreLauncher.class) {
            while (true) {
                try {
                	CoreLauncher.class.wait();
                } catch (Throwable e) {
                }
            }
        }
		*/
		
		//第三种  通过dubbo.container.main  要在classpath目录下配置 dubbo.properties 相关配置
		com.alibaba.dubbo.container.Main.main(args);
		
		
    }
 
    private static void getLocalip() {
        try {
            System.out.println("服务暴露的ip: "
                    + java.net.InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
