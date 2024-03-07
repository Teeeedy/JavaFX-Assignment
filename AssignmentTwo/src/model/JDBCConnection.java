package model;

import java.sql.*;
import java.util.ArrayList;

public class JDBCConnection {
	private static final String DB_URL = "jdbc:sqlite:database/analyticsDatabase.db";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL);
	}

	public boolean searchQuery(String query) {
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				con.close();
				return true;
			} else {
				con.close();
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insUpDelQuery(String query) {
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			st.executeUpdate(query);
			con.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public User getUser(String username, String password) {
		try {

			String query = "SELECT * FROM USERS WHERE password = \"" + password + "\" AND username =\"" + username
					+ "\";";

			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				username = rs.getString("username");
				password = rs.getString("password");
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				String VIP = rs.getString("VIP");

				User user = new User(fName, lName, username, password, VIP);
				con.close();
				return user;

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public Post getPost(String postID) {
		try {

			String query = "SELECT * FROM Posts WHERE postID = \"" + postID + "\";";

			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				postID = rs.getString("postID");
				String content = rs.getString("content");
				String author = rs.getString("author_username");
				String likes = rs.getString("likes");
				String shares = rs.getString("shares");
				String dateTime = rs.getString("date_time");

				Post newPost = new Post();
				boolean initialized = newPost.initializePost(postID, content, author, likes, shares, dateTime);

				if (initialized) {
					con.close();
					return newPost;
				} else {
					return null;
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}
	
	public ArrayList<Post> getTopNPostsFromAllUsers(int numPosts) {
		try {
			ArrayList<Post> postsCollection = new ArrayList<Post>();
			String query = "SELECT * FROM POSTS ORDER BY likes DESC LIMIT " + numPosts + ";";
			System.out.println(query);
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				String postID = rs.getString("postID");
				String content = rs.getString("content");
				String author = rs.getString("author_username");
				String likes = rs.getString("likes");
				String shares = rs.getString("shares");
				String dateTime = rs.getString("date_time");
				
				System.out.println(postID);

				Post newPost = new Post();
				newPost.initializePost(postID, content, author, likes, shares, dateTime);
				postsCollection.add(newPost);
			}
			
			return postsCollection;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public ArrayList<Post> getTopNPostsOfUser(int numPosts, String user) {
		try {
			ArrayList<Post> postsCollection = new ArrayList<Post>();
			String query = "SELECT * FROM POSTS WHERE author_username = \"" + user + "\"  ORDER BY likes DESC LIMIT " + numPosts + ";";
			System.out.println(query);
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				String postID = rs.getString("postID");
				String content = rs.getString("content");
				String author = rs.getString("author_username");
				String likes = rs.getString("likes");
				String shares = rs.getString("shares");
				String dateTime = rs.getString("date_time");
				
				System.out.println(postID);

				Post newPost = new Post();
				newPost.initializePost(postID, content, author, likes, shares, dateTime);
				postsCollection.add(newPost);
			}
			
			return postsCollection;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public ArrayList<Post> getAllPosts() {
		try {
			ArrayList<Post> postsCollection = new ArrayList<Post>();
			String query = "SELECT * FROM POSTS;";
			System.out.println(query);
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				String postID = rs.getString("postID");
				String content = rs.getString("content");
				String author = rs.getString("author_username");
				String likes = rs.getString("likes");
				String shares = rs.getString("shares");
				String dateTime = rs.getString("date_time");
				
				System.out.println(postID);

				Post newPost = new Post();
				newPost.initializePost(postID, content, author, likes, shares, dateTime);
				postsCollection.add(newPost);
			}
			
			return postsCollection;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public int getNumPostsInSharesRange(String start, String end) {
		int totalPosts = 0;
		try {
			Connection con = getConnection();
			String query = "SELECT COUNT(*) FROM POSTS WHERE shares >= " + start + " AND shares <= " + end + ";";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				totalPosts = rs.getInt("COUNT(*)");
			}
			return totalPosts;
			
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
