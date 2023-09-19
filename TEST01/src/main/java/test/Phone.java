package test;

public interface Phone {
	void call(String name);
	// public abstract void call(String name); 위와 아래는 같음
	
	// 인터페이스에 달리는 기본적인 키워드 (생략 가능하다)
	// public : 부모의 공개범위가 자식의 공개범위보다 커야 함 (따라서 기본적으로 붙어있는 것임)
	// abstract
}
