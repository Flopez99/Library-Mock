package model;

import java.util.HashMap;
import java.util.TreeMap;

public class StorageBag {
	private HashMap<String, User> users;
	private TreeMap<String, Book> booksByTitle;
	private TreeMap<String, Book> booksByIsbn;
	private TreeMap<String, Book> booksByAuthor;
	private TreeMap<String, Book> overDueBooks;

	public StorageBag(HashMap<String, User> users, TreeMap<String, Book> booksByTitle,
			TreeMap<String, Book> booksByIsbn, TreeMap<String, Book> booksByAuthor,
			TreeMap<String, Book> overDueBooks) {
		super();
		this.users = users;
		this.booksByTitle = booksByTitle;
		this.booksByIsbn = booksByIsbn;
		this.booksByAuthor = booksByAuthor;
		this.overDueBooks = overDueBooks;
	}

	public HashMap<String, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}

	public TreeMap<String, Book> getBooksByTitle() {
		return booksByTitle;
	}

	public void setBooksByTitle(TreeMap<String, Book> booksByTitle) {
		this.booksByTitle = booksByTitle;
	}

	public TreeMap<String, Book> getBooksByIsbn() {
		return booksByIsbn;
	}

	public void setBooksByIsbn(TreeMap<String, Book> booksByIsbn) {
		this.booksByIsbn = booksByIsbn;
	}

	public TreeMap<String, Book> getBooksByAuthor() {
		return booksByAuthor;
	}

	public void setBooksByAuthor(TreeMap<String, Book> booksByAuthor) {
		this.booksByAuthor = booksByAuthor;
	}

	public TreeMap<String, Book> getOverDueBooks() {
		return overDueBooks;
	}

	public void setOverDueBooks(TreeMap<String, Book> overDueBooks) {
		this.overDueBooks = overDueBooks;
	}

}
