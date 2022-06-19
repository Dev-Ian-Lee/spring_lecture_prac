package hello.core.singleton;

public class SingletonService {

    // 클래스 내부에 자신의 인스턴스를 static으로 하나만 생성
    private static final SingletonService instance = new SingletonService();

    // 외부에서 사용할 수 있도록 getter 생성(return값이 static이니까 메소드도 static)
    public static SingletonService getInstance() {
        return instance;
    }

    // 외부에서 new 연산자를 사용할 수 없도록, private 생성자 정의
    private SingletonService() {

    }
}
