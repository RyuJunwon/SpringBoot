package com.ryu.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import test.Config;
import test.Member;

//@SpringBootApplication
public class Test01Application {

	public static void main(String[] args) {
		// SpringApplication.run(Test01Application.class, args);
		// 위의 어노테이션과 SpringApplicaiton은 웹 어플리케이션을 사용하는 것이기 때문에 지금 실습에선 사용하지 않으므로 주석 처리함
		
		// 1. 스프링 IoC 컨테이너 구동시키기
		// 1-2. 컨테이너를 구동시킬 때 설정 파일이 필요하다
		
		// 스프링에서는 applicationContext.xml 사용했지만 부트은 방금 우리가 만든 클래스 파일을 넣어서 사용 (Config.class)
		// (즉, 스프링에서는 .xml / 부트에서는 .java)
		ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
	
		// 스프링 IoC 컨테이너는 팩토리 패턴을 기반으로 동작하고 있다
		
		Member member1 = (Member)ac.getBean("member1"); // output이 Object 이므로 다운캐스팅 필요함
		member1.print();

		Member member2 = ac.getBean("apple", Member.class); // 아까 설정한 이름으로 불러오고 다운 캐스팅까지 되어 나오는 방법
		member2.print();
		
		// 2. 싱글톤 유지되는지 확인해보자
		if(member1 == member2) {
			System.out.println("둘은 동일한 객체입니다");
		}
		else {
			System.out.println("둘은 다른 객체입니다");
		}
		
		// 현재 상황
		// 부트방식을 전부 활용한 것은 아님
		// 개발자가 (직접 new를 작성한 상황) member 객체를 2개 등록 --> 개발자가 유도한대로 싱글톤 유지가 안됨 [ 싱글톤이 깨짐 ]
	}

}
