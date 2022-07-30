package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.saveItem(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item foundItem = itemRepository.findItem(itemId);
        foundItem.setName(name);
        foundItem.setPrice(price);
        foundItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findAllItems() {
        return itemRepository.findAllItems();
    }

    public Item findItem(Long itemId) {
        return itemRepository.findItem(itemId);
    }


}
