package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // 저장시점에 item이 처음 저장되는 것이면 persist, 이미 저장되어있다면 merge(update와 유사)
    public void saveItem(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findItem(Long itemId) {
        return em.find(Item.class, itemId);
    }

    public List<Item> findAllItems() {
        return em.createQuery("SELECT i FROM Item i")
                .getResultList();
    }
}
