package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

// 총 2건의 주문 데이터를 DB에 저장시키기 위한 초기화 클래스
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit() {
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            Book book = createBook("JPA1", 10000, 100);
            em.persist(book);

            Book book2 = createBook("JPA2", 20000, 100);
            em.persist(book2);

            OrderItem orderItem = OrderItem.createOrderItem(book, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem, orderItem2);
            em.persist(order);

        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        public void dbInit2() {
            Member member = createMember("userB", "인천", "2", "2222");
            em.persist(member);

            Book book = createBook("SPRING1", 20000, 200);
            em.persist(book);

            Book book2 = createBook("SPRING2", 40000, 300);
            em.persist(book2);

            OrderItem orderItem = OrderItem.createOrderItem(book, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = createDelivery(member);

            Order order = Order.createOrder(member, delivery, orderItem, orderItem2);
            em.persist(order);

        }

    }
}

