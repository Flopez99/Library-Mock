package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;

import Helpers.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Library implements Serializable {

	private static HashMap<String, User> users;
	private static TreeMap<String, Book> booksByTitle;
	private static TreeMap<String, Book> booksByIsbn;
	private static TreeMap<String, Book> booksByAuthor;
	private static TreeMap<String, Book> overDueBooks;

	private static HashSet<String> spellHash;

	private static ObservableList<Book> bookList;
	private static ObservableList<User> userList;
	private static ObservableList<Book> overDueBooksList;

	private static User user;
	private static User pickedUser;

	public Library() {
		users = new HashMap<String, User>(50000);
		booksByTitle = new TreeMap<String, Book>();
		booksByIsbn = new TreeMap<String, Book>();
		booksByAuthor = new TreeMap<String, Book>();
		spellHash = new HashSet<String>();
		overDueBooks = new TreeMap<String, Book>();

		bookList = FXCollections.observableArrayList();
		userList = FXCollections.observableArrayList();
		overDueBooksList = FXCollections.observableArrayList();
	}

	public static ObservableList<Book> getOverDueBooksList() {
		return overDueBooksList;
	}

	public static void setOverDueBooksList(ObservableList<Book> overDueBooksList) {
		Library.overDueBooksList = overDueBooksList;
	}

	public static TreeMap<String, Book> getOverDueBooks() {
		return overDueBooks;
	}

	public static void setOverDueBooks(TreeMap<String, Book> overDueBooks) {
		Library.overDueBooks = overDueBooks;
	}

	public static HashMap<String, User> getUsers() {
		return users;
	}

	public static void fillHash() {
		spellHash = Utilities.spellCheckHash(spellHash);
	}

	public static HashSet<String> getSpellHash() {
		return spellHash;
	}

	public static TreeMap<String, Book> getBooksByTitle() {
		Book b1 = new Book("Adventures of pajero", "1356734", "Ivan", 3, "");
		Book b2 = new Book("Adventures of flinn", "235621", "Nelson", 2, "");

		Book b3 = new Book("Adventures of mia", "1235334", "Drago", 1, "");

		booksByTitle.put(b1.getBookTitle(), b1);
		booksByTitle.put(b2.getBookTitle(), b2);
		booksByTitle.put(b3.getBookTitle(), b3);

		return booksByTitle;
	}

	public static TreeMap<String, Book> getBooksByIsbn() {
		Book b1 = new Book("Adventures of pajero", "1356734", "Ivan", 3, "");
		Book b2 = new Book("Adventures of flinn", "235621", "Nelson", 2, "");
		Book b3 = new Book("Adventures of mia", "1235334", "Drago", 1, "");

		booksByIsbn.put(b1.getIsbn(), b1);
		booksByIsbn.put(b2.getIsbn(), b2);
		booksByIsbn.put(b3.getIsbn(), b3);

		return booksByIsbn;
	}

	public static TreeMap<String, Book> getBooksByAuthor() {
		Book b1 = new Book("Adventures of pajero", "1356734", "Ivan", 3, "");
		Book b2 = new Book("Adventures of flinn", "235621", "Nelson", 2, "");
		Book b3 = new Book("Adventures of mia", "1235334", "Drago", 1, "");

		booksByAuthor.put(b1.getAuthor(), b1);
		booksByAuthor.put(b2.getAuthor(), b2);
		booksByAuthor.put(b3.getAuthor(), b3);

		return booksByAuthor;
	}

	public static ObservableList<Book> getBookList() {

		return bookList;
	}

	public static ObservableList<User> getUserList() {
		return userList;
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Library.user = user;
	}

	public static User getPickedUser() {
		return pickedUser;
	}

	public static void setPickedUser(User pickedUser) {
		Library.pickedUser = pickedUser;
	}

	public static void setUsers(HashMap<String, User> users) {
		Library.users = users;
	}

	public static void setBooksByTitle(TreeMap<String, Book> booksByTitle) {
		Library.booksByTitle = booksByTitle;
	}

	public static void setBooksByIsbn(TreeMap<String, Book> booksByIsbn) {
		Library.booksByIsbn = booksByIsbn;
	}

	public static void setBooksByAuthor(TreeMap<String, Book> booksByAuthor) {
		Library.booksByAuthor = booksByAuthor;
	}

	public static void setSpellHash(HashSet<String> spellHash) {
		Library.spellHash = spellHash;
	}

	public static void setBookList(ObservableList<Book> bookList) {
		Library.bookList = bookList;
	}

	public static void setUserList(ObservableList<User> userList) {
		Library.userList = userList;
	}
	
}
