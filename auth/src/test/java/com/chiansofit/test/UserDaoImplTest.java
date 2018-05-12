package com.chiansofit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.chiansofit.dao.impl.UserDaoImpl;
import com.chiansofit.entity.User;

public class UserDaoImplTest {
	private UserDaoImpl udao = new UserDaoImpl();

	@Test
	public void testInsertUser() {
		User user = new User();
		user.setId("2");
		user.setUsername("admin");
		user.setPassword("admin");
		udao.insertUser(user);
	}

	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUserById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllUser() {
		List<User> ulist = udao.findAllUser();
		System.out.println(ulist.size());
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

}
