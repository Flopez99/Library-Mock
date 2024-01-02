package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.TreeMap;

import Helpers.Utilities;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class User implements Serializable {
	private SimpleStringProperty userName;
	private SimpleStringProperty fName;
	private SimpleStringProperty lName;
	private SimpleStringProperty address;
	private SimpleStringProperty phone;
	private boolean isActive;
	private Role role;
	private String password;
	private LinkedList<Book> borrowHistory;
	private TreeMap<String, Book> borrowHistoryMap;
	private Image profilePicture;

	private LinkedList<Book> ownedBook;

	public User(String fName, String lName, String address, String pNumber, String userName, String password, Role role,
			LinkedList<Book> borrowHistory) {
		this.userName = new SimpleStringProperty(userName);
		this.fName = new SimpleStringProperty(fName);
		this.lName = new SimpleStringProperty(lName);
		this.address = new SimpleStringProperty(address);
		this.phone = new SimpleStringProperty(pNumber);
		this.role = role;
		this.password = password;
		isActive = true;
		this.borrowHistory = borrowHistory;
		ownedBook = new LinkedList<Book>();
		profilePicture = null;
	}

	public Image getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(Image profilePicture) {
		this.profilePicture = profilePicture;
	}

	public TreeMap<String, Book> getBorrowHistoryMap() {
		return borrowHistoryMap;
	}

	public void setBorrowHistoryMap(TreeMap<String, Book> borrowHistoryMap) {
		this.borrowHistoryMap = borrowHistoryMap;
	}

	public LinkedList<Book> getOwnedBook() {
		return ownedBook;
	}

	public void setOwnedBook(LinkedList<Book> ownedBook) {
		this.ownedBook = ownedBook;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public void borrowBook(Book book) {
		Book temp = new Book(book.getBookTitle(), book.getIsbn(), book.getAuthor(), book.getCopies(), "");
		temp.setDueDate(Utilities.getDueDate());
		borrowHistory.add(temp);
		ownedBook.add(temp);
	}

	public void returnBook(Book book) {
		ownedBook.remove(book);
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LinkedList<Book> getBorrowHistory() {
		return borrowHistory;
	}

	public void setBorrowHistory(LinkedList<Book> borrowHistory) {
		this.borrowHistory = borrowHistory;
	}

	public String getFName() {
		return fName.get();
	}

	public void setFName(String fName) {
		this.fName = new SimpleStringProperty(fName);
	}

	public String getLName() {
		return lName.get();
	}

	public void setLName(String lName) {
		this.lName = new SimpleStringProperty(lName);
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String pNumber) {
		this.phone = new SimpleStringProperty(pNumber);
	}

	public void setUserName(String userName) {
		this.userName = new SimpleStringProperty(userName);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName.get();
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", fName=" + fName + ", lName=" + lName
				+ ", address=" + address + ", pNumber=" + phone + "]";
	}

}
