package com.example.Backend.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


}

/*
* extends 키워드는 클래스가 다른 클래스를 상속하거나 인터페이스가 다른 인터페이스를 상속할 때 사용됩니다.
* 반면에 implements 키워드는 클래스가 인터페이스를 구현할 때 사용됩니다. 인터페이스 간의 상속과 클래스가 인터페이스를 구현하는 것은 서로 다른 개
* */
