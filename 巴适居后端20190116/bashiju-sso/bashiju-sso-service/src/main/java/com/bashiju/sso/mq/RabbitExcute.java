package com.bashiju.sso.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitExcute  implements MessageListener {

		public void updateInfo(String msg) {
			System.out.println("ssss"+msg);		
		}

		@Override
		public void onMessage(Message message) {
			System.out.println("mmmmmmm"+message);
			
		}
}
