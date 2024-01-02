package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Book;
import model.Library;
import model.Role;
import model.User;

public class Controller6 implements Initializable {
	private HashMap<String, User> users;
	@FXML
	TextField bookTitle, isbn, authorName, copies;
	
	@FXML Label bookMade;
	private TreeMap<String, Book> booksByTitle;
	private TreeMap<String, Book> booksByIsbn;
	private TreeMap<String, Book> booksByAuthor;
	private User loggedUser;
	
	
	public void addBooks(ActionEvent event) throws IOException {
		String title = bookTitle.getText();
		String bIsbn = isbn.getText();
		String authorN = authorName.getText();

		int copy = Integer.parseInt(copies.getText());

		Book book = new Book(title, bIsbn, authorN, copy, "");
		booksByTitle.put(title, book);
		booksByIsbn.put(bIsbn, book);
		booksByAuthor.put(authorN, book);
		
		bookTitle.clear();
		isbn.clear();
		authorName.clear();
		copies.clear();
		bookMade.setText("The Book: " + title + " was registered");
	}
	public void changeSceneLogIn(ActionEvent e) throws IOException {
		Parent secondRoot = null;
		if (loggedUser.getRole().equals(Role.ADMIN)) {
			secondRoot = FXMLLoader.load(getClass().getResource("/view/Admin.fxml"));
		} else {
			secondRoot = FXMLLoader.load(getClass().getResource("/view/PatronView.fxml"));

		}
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		users = Library.getUsers();
		loggedUser = users.get(Library.getUser().getUserName());
		booksByTitle = Library.getBooksByTitle();
		booksByIsbn = Library.getBooksByIsbn();
		booksByAuthor = Library.getBooksByAuthor();
	}
}
