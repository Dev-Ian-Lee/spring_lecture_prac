package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 10);

        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item foundItem = itemRepository.findById(item.getId());
        assertThat(foundItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        Item item = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item);
        itemRepository.save(item2);

        // when
        List<Item> result = itemRepository.findAll();


        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item, item2);
    }

    @Test
    void updateItem() {
        // given
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        // when
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        // then
        Item foundItem = itemRepository.findById(itemId);

        assertThat(foundItem.getItemName()).isEqualTo("item1");
        assertThat(foundItem.getPrice()).isEqualTo(10000);
        assertThat(foundItem.getQuantity()).isEqualTo(10);
    }
}