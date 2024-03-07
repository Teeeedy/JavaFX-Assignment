package tests;

import org.junit.jupiter.api.Test;

import model.DatabaseQuery;
import model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;

// Tests are ran with values from database
// Post 382
// User Wai Yan Lin
public class DatabaseQueryTests {
	DatabaseQuery database = new DatabaseQuery();

	@Test
	public void searchQuery_False_WhenIDNotFound() {
		assertFalse(database.searchQuery("SELECT * FROM Posts WHERE postID = 12343215;"));
	}

	@Test
	public void searchQuery_True_WhenIDFound() {
		assertTrue(database.searchQuery("SELECT * FROM Posts WHERE postID = 382;"));
	}

	@Test
	public void getUser_null_WhenUserNotFound() {
		assertEquals(null, database.getUser("fewagdsadxc", "dfgsafewagwea"));
	}

	@Test
	public void getUser_user_WhenUserFound() {
		assertEquals("Wai Yan", database.getUser("fewa", "fewa").getFName());
	}

	@Test
	public void getTopNPostsFromAllUsers_HighestPost() {
		assertEquals(13589, database.getTopNPostsFromAllUsers(1).get(0).getShares());
	}

	@Test
	public void getTopPostsOfUser_NoneFound() {
		assertEquals(0, database.getTopNPostsOfUser(132, "fewagadsgrewa").size());
	}
	
	@Test
	public void getPost_NullIfNotFound() {
		assertEquals(null, database.getPost("432153"));
	}
	
	@Test
	public void getPost_ReturnPostIfFound() {
		assertEquals("382", database.getPost("382").getPostID());
	}
	
	@Test
	public void getNumPostsInSharesRange() {
		assertEquals(0, database.getNumPostsInSharesRange("43720113402", "473802151123"));
	}
	
	

}