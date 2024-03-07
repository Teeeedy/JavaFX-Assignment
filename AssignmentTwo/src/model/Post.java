package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import controller.IntegerInputLessThanZeroException;
import exceptions.IncorrectDateInputException;

public class Post {
	private String postID;
	private String content;
	private String author;
	private int likes;
	private int shares;
	private String dateTime;

	public boolean initializePost(String postID, String content, String author, String likes, String shares,
			String dateTime) {
		AlertBox alertBox = new AlertBox();
		try {
			this.setPostID(postID);
			this.setContent(content);
			this.setAuthor(author);
			this.setLikes(likes);
			this.setShares(shares);
			this.setDateTime(dateTime);
			return true;
		} catch (NumberFormatException e) {
			alertBox.display("Error", e.getMessage());
			return false;
		} catch (IntegerInputLessThanZeroException e) {
			alertBox.display("Error", e.getMessage());
			return false;
		} catch (IncorrectDateInputException e) {
			alertBox.display("Error", e.getMessage());
			return false;
		}
		
	}

//	Getters
	public String getPostID() {
		return this.postID;
	}

	public String getContent() {
		return this.content;
	}

	public String getAuthor() {
		return this.author;
	}

	public int getLikes() {
		return this.likes;
	}

	public int getShares() {
		return this.shares;
	}

	public String getDateTime() {
		return this.dateTime;
	}

//	Setters
	public void setPostID(String postID) throws IntegerInputLessThanZeroException, NumberFormatException {
		try {
			int postIDNum = Integer.parseInt(postID);
			this.postID = postID;

			if (postIDNum < 0) {
				throw new IntegerInputLessThanZeroException("Post ID must be greater than 0.");
			}

		} catch (NumberFormatException e) {
			throw new NumberFormatException("Post ID must be an integer greater than 0.");
		}

	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setLikes(String likes) throws NumberFormatException, IntegerInputLessThanZeroException {
		try {
			this.likes = Integer.parseInt(likes);
			
			if (this.likes < 0) {
				throw new IntegerInputLessThanZeroException("Likes cannot be less than zero");
			}

		} catch (NumberFormatException e) {
			throw new NumberFormatException("Likes must be an integer value.");
		}
	}

	public void setShares(String shares) throws NumberFormatException, IntegerInputLessThanZeroException {
		try {
			this.shares = Integer.parseInt(shares);
			
			if (this.shares < 0) {
				throw new IntegerInputLessThanZeroException("Shares cannot be less than zero");
			}

		} catch (NumberFormatException e) {
			throw new NumberFormatException("Shares must be an integer value.");
		}

	}

	public void setDateTime(String dateTime) throws IncorrectDateInputException {
		try {
			LocalDateTime dateObj = LocalDateTime.parse(dateTime,
					DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.ENGLISH));
			this.dateTime = dateTime;
		} catch (DateTimeParseException e) {
			throw new IncorrectDateInputException("Date must be written in the given format DD/MM/YYYY HH:MM.");
		}
	}

}
