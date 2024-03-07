package model;

public class User {
	private String username;
	private String password;
	private String fName;
	private String lName;
	private String vip;

	public User(String fName, String lName, String username, String password, String vip) {
		this.fName = fName;
		this.lName = lName;
		this.username = username;
		this.password = password;
		this.vip = vip;
	}

	// getters
	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getFName() {
		return this.fName;
	}

	public String getLName() {
		return this.lName;
	}

	public String getVIP() {
		return this.vip;
	}

	// setters
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public void setVIP(String vip) {
		this.vip = vip;
	}

	public Post retrievePost(String postID) {
		boolean found = this.searchPost(postID);

		if (found) {
			DatabaseQuery database = new DatabaseQuery();
			String query = "SELECT * FROM Posts WHERE postID = \"" + postID + "\";";
			Post post = database.getPost(postID);
			return post;
		} else {
			return null;
		}
	}

	public boolean removePost(String postID) {
		boolean found = this.searchPost(postID);

		if (found) {
			DatabaseQuery database = new DatabaseQuery();
			String query = "DELETE FROM Posts WHERE postID = \"" + postID + "\";";
			boolean removed = database.insUpDelQuery(query);
			return removed;
		} else {
			return false;
		}

	}

	public boolean addPost(Post post) {
		boolean found = this.searchPost(post.getPostID());

		if (!found) {
			DatabaseQuery database = new DatabaseQuery();
			String query = "INSERT INTO Posts VALUES (\"" + post.getPostID() + "\", \"" + post.getContent() + "\", \""
					+ post.getAuthor() + "\", \"" + post.getLikes() + "\", \"" + post.getShares() + "\", \""
					+ post.getDateTime() + "\");";
			System.out.println(query);
			boolean added = database.insUpDelQuery(query);
			return added;
		} else {
			return false;
		}

	}

	public boolean searchPost(String postID) {
		DatabaseQuery database = new DatabaseQuery();
		String query = "SELECT * FROM Posts WHERE postID = \"" + postID + "\";";
		return database.searchQuery("SELECT * FROM Posts WHERE postID = \"" + postID + "\";");
	}


}
