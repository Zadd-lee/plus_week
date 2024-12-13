package com.example.demo.entity;

import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;


    Item item;
    User user1;
    User user2;

    @Test
    void createItem() {
        //given
        String name = "name";
        String description = "dis";
        item = new Item(name,
                description,
                user1,
                user2);

        //when
        itemRepository.save(item);

        //then
        List<Item> itemList = itemRepository.findByNameAndDescription(name, description);
        assertNotNull(itemList);

        assertNull(item);

        for (Item item1 : itemList) {
            assertNotNull(item1.getStatus());
        }

    }

    @BeforeEach
    void setUp() {

        user1 = new User("admin", "E", "N", "PASS");
        user2 = new User("user", "E", "N", "PASS");
        userRepository.save(user1);
        userRepository.save(user2);
    }

    @AfterEach
    void tearDown() {
        itemRepository.delete(item);

        userRepository.delete(user1);
        userRepository.delete(user2);

    }
}