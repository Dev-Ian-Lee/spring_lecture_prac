package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

// order가 DB 예약어로 쓰이는 경우가 있어 table 이름을 orders로 지정
@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    // Member 객체의 pk인 id
    @Column(name = "MEMBER_ID")
    private Long memberId;
    private LocalDateTime orderDate;
    
    // Enum에 대한 어노테이션(EnumType 반드시 STRING으로 설정)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
