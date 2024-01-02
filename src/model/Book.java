package model;

import java.io.Serializable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book implements Serializable{
	private SimpleStringProperty bookTitle, isbn, author, dueDate;
	private SimpleIntegerProperty copies;
	private String id;
	private static int idCounter = 1;
	private boolean isOverdue;
	private String offendingPatron;
	
	public Book(String bookTitle, String isbn, String author, int copies, String dueDate) {
		super();
		this.bookTitle = new SimpleStringProperty(bookTitle);
		this.isbn = new SimpleStringProperty(isbn);
		this.author = new SimpleStringProperty(author);
		this.copies = new SimpleIntegerProperty(copies);
		this.dueDate = new SimpleStringProperty(dueDate);
		isOverdue = false;
		offendingPatron = "";
		id = String.valueOf(idCounter++);
	}

	public boolean isOverdue() {
		return isOverdue;
	}

	public void setOverdue(boolean isOverdue) {
		this.isOverdue = isOverdue;
	}

	public String getDueDate() {
		return dueDate.get();
	}

	public void setDueDate(String dueDate) {
		this.dueDate = new SimpleStringProperty(dueDate);
	}

	public String getOffendingPatron() {
		return offendingPatron;
	}

	public void setOffendingPatron(String offendingPatron) {
		this.offendingPatron = offendingPatron;
	}

	public int getCopies() {
		return copies.get();
	}

	public void setCopies(int copies) {
		this.copies = new SimpleIntegerProperty(copies);

	}

	public String getBookTitle() {
		return bookTitle.get();
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = new SimpleStringProperty(bookTitle);
	}

	public String getIsbn() {
		return isbn.get();
	}

	public void setIsbn(String isbn) {
		this.isbn = new SimpleStringProperty(isbn);
		;
	}

	public String getAuthor() {
		return author.get();
	}

	public void setAuthor(String author) {
		this.author = new SimpleStringProperty(author);
		;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Book [bookTitle=" + bookTitle + ", isbn=" + isbn + ", authors=" + author + ", id=" + id + "]";
	}

}
