package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void orderTest() throws Exception {
        //given
        Member member = createMember();

        Book book = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order foundOrder = orderRepository.findOrder(orderId);

        // 상품 주문 시 상태는 ORDER
        assertEquals(OrderStatus.ORDER, foundOrder.getStatus());

        // 주문한 상품이 한 종류인지 확인
        assertEquals(1, foundOrder.getOrderItems().size());

        // 주문 가격이 제대로 계산되었는지 확인
        assertEquals(10000 * orderCount, foundOrder.getTotalPrice());

        // 주문 수량만큼 재고 감소하는지 확인
        assertEquals(8, book.getStockQuantity());

    }

    // 재고량보다 많이 주문했을 경우 테스트
    @Test
    public void orderedOverStockQuantityTest() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);

        int orderCount = 11;

        //when

        //then
        // 잘못된 수량 입력 시 Exception이 발생하는지 확인
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), item.getId(), orderCount);
        });

    }

    @Test
    public void cancelTest() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order foundOrder = orderRepository.findOrder(orderId);

        // 상품 주문 취소 시 상태는 CANCEL
        assertEquals(OrderStatus.CANCEL, foundOrder.getStatus());

        // 상품 주문 취소 시 재고가 증가하는지 확인
        assertEquals(10, book.getStockQuantity());
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("memberA");
        member.setAddress(new Address("서울", "강남", "12345"));
        em.persist(member);
        return member;
    }

}