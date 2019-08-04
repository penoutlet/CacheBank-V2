package com.firstTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Menus.MainMenu;
import items.Account;
import items.Actions;

public class NewUserTest {
//	@Test
//	
//	public void createNewUser() {
//		assertEquals("create new user", new Account("sam","sam",10000,"sam","sam","sam","sam"), MainMenu.createNewUser());
//		
//	}
	
	@Test
	public void transfer() {
		Account a = new Account("sam","sam",10000,"sam","sam","sam","sam");
		Account t = new Account("spa","sam",10000,"sam","sam","sam","sam");
		Account e = new Account("sam","sam",6000,"sam","sam","sam","sam");
		assertEquals("transfer", e, Actions.transfer(a, 4000, t));
	}
}
