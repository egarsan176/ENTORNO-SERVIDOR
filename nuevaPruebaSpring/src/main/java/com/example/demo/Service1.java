package com.example.demo;

import org.springframework.stereotype.Component;

@Component			//con component decimos que Service1 será un bean. De este modo no es necesario el AppConfig
public class Service1 {

		void doStmth() {
			System.out.println("Hi from Service1");
		}
}


