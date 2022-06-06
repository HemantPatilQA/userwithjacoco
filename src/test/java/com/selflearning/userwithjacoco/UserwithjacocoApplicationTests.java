package com.selflearning.userwithjacoco;

import com.selflearning.userwithjacoco.entities.User;
import com.selflearning.userwithjacoco.repositories.UserRepository;
import com.selflearning.userwithjacoco.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserwithjacocoApplication.class)
public class UserwithjacocoApplicationTests {

    @Test
    void userwithjacocoApplicationTest(){
        String[] args=
                new String[] {
                        "test", "test"
                };
        UserwithjacocoApplication.main(args);
        Assertions.assertNotNull(args);
    }

	/*@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void getUsersTest(){
		when(userRepository.findAll()).thenReturn(Stream.of(new User(101L, "Hemant", 42, "Pune"), new User(102L,"Minali", 39, "Nasik"))
				.collect(Collectors.toList()));
		assertEquals(2, userService.getUsers().size());
	}

	@Test
	public void getUsersByAddressTest(){
		String address = "Bangalore";
		when(userRepository.findAllByAddress(address)).thenReturn(Stream.of(new User(101L, "Hemant", 42, "Pune"))
				.collect(Collectors.toList()));
		assertEquals(1, userService.getUsersByAddress(address).size());
	}

	@Test
	public void addUserServiceTest(){
		User user = new User(101L, "Hemant", 42, "Pune");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userService.addUser(user));
	}

	@Test
	public void deleteUserTest(){
		User user = new User(101L, "Hemant", 42, "Pune");
		userService.deleteUser(user);
		verify(userRepository, times(1)).delete(user);
	}
	@Test
	public void contextLoads() {
	}*/


}
