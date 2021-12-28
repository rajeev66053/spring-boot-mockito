package com.java.spring.mockito.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.java.spring.mockito.api.model.User;
import com.java.spring.mockito.api.repository.UserRepository;
import com.java.spring.mockito.api.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootMockitoApplicationTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void getUsersTest() {
        when(userRepository.findAll()).thenReturn(Stream
                .of(new User(376, "Danile", 31, "USA"), new User(958, "Huy", 35, "UK")).collect(Collectors.toList()));
        assertEquals(2, userService.getUsers().size());
    }

    @Test
    public void getUserbyAddressTest() {
        String address = "Bangalore";
        when(userRepository.findByAddress(address))
                .thenReturn(Stream.of(new User(376, "Danile", 31, "USA")).collect(Collectors.toList()));
        assertEquals(1, userService.getUserbyAddress(address).size());
    }

    @Test
    public void saveUserTest() {
        User user = new User(999, "Pranya", 33, "Pune");
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.addUser(user));
    }

    @Test
    public void deleteUserTest() {
        User user = new User(999, "Pranya", 33, "Pune");
        userService.deleteUser(user);
        verify(userRepository, times(1)).delete(user);
    }

}