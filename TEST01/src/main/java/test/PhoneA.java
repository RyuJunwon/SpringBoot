package test;

public class PhoneA implements Phone {

	@Override
	public void call(String name) { // 메소드를 강제한다 : 오버라이딩
		System.out.println("PhoneA : " + name + "이(가) 전화 거는중 ...");
	}
}
