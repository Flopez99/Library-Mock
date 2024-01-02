package controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;

import Helpers.Utilities;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Book;
import model.Library;
import model.Main;
import model.StorageBag;
import model.User;

public class Controller1 implements Initializable {
	private HashMap<String, User> users;
	private TreeMap<String, Book> overDueBooks;
	private TreeMap<String, Book> booksByTitle;
	private TreeMap<String, Book> booksByIsbn;
	private TreeMap<String, Book> booksByAuthor;
	private ObservableList<Book> bookList;
	private  HashSet<String> wordSet;

	private User loggedUser;

	@FXML
	private TextField userName, password, logUser, phoneNumber, firstName, lastName, address, bookSearch; 
	@FXML
	private ContextMenu cm;
	@FXML
	private ImageView profilePicture;
	@FXML
	private ComboBox<String> searchBox;
	@FXML
	private TextArea bookResult;
	@FXML
	private TableView<Book> bookSelection;
	@FXML
	private TableColumn<Book, String> isbnColumn;
	@FXML
	private TableColumn<Book, String> titleColumn;
	@FXML
	private TableColumn<Book, String> authorColumn;
	@FXML
	private TableColumn<Book, String> copiesColumn;
	@FXML
	private Label spellCheckAlert;

	public void rentBook(ActionEvent event) throws IOException {
		Alert a = new Alert(AlertType.NONE);
		a.setAlertType(AlertType.INFORMATION);
		a.setTitle("No More Copies Alert");

		Book book = bookSelection.getSelectionModel().getSelectedItem();
		if (book.getCopies() > 0) {

			loggedUser.borrowBook(book);
			
			booksByTitle.get(book.getBookTitle()).setCopies(booksByTitle.get(book.getBookTitle()).getCopies() - 1);
			booksByIsbn.get(book.getIsbn()).setCopies(booksByIsbn.get(book.getIsbn()).getCopies() - 1);
			//booksByAuthor.get(book.getAuthor()).setCopies(booksByAuthor.get(book.getAuthor()).getCopies() - 1);
			
			bookSelection.refresh();
		} else {
			a.setHeaderText("Book Not Available");
			a.setContentText("We apologize, the book: " + book.getBookTitle() + " is not available");
			a.show();
		}

	}

	@FXML
	private void checkWord(KeyEvent keyEvent) throws IOException {
		String word = bookSearch.getText();
		System.out.println(word);

		if (searchBox.getValue().equals("TITLE")) {
			if (keyEvent.getCode() == KeyCode.SPACE) {
				List<String> words = Collections.list(new StringTokenizer(word, " ")).stream()
						.map(token -> (String) token).collect(Collectors.toList());

				spellCheck(words);
			}
		}
	}

	public void searchBook(ActionEvent event) {
		bookList.clear();

		Map<String, Book> result = null;
		switch (searchBox.getValue()) {
		case "TITLE":
			result = (booksByTitle.entrySet().stream().filter(map -> map.getKey().startsWith(bookSearch.getText()))
					.collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue())));

			for (java.util.Map.Entry<String, Book> entry : result.entrySet()) {
				bookList.add(entry.getValue());

			}
			bookSelection.setItems(bookList);

			break;

		case "ISBN":
			result = (booksByIsbn.entrySet().stream().filter(map -> map.getKey().startsWith(bookSearch.getText()))
					.collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue())));

			for (java.util.Map.Entry<String, Book> entry : result.entrySet()) {
				bookList.add(entry.getValue());

			}
			bookSelection.setItems(bookList);

			break;
		case "AUTHOR":
			result = (booksByAuthor.entrySet().stream().filter(map -> map.getKey().startsWith(bookSearch.getText()))
					.collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue())));

			for (java.util.Map.Entry<String, Book> entry : result.entrySet()) {
				bookList.add(entry.getValue());
			}
			bookSelection.setItems(bookList);
		}
	}

	public void spellCheck(List<String> s) throws IOException {
		for (String string : s) {
			if (!wordSet.contains(string)) {
				spellCheckAlert.setText("A word is mispelled");
			}

			if (wordSet.contains(string))
				spellCheckAlert.setText(" ");

		}
	}

	public void closeAndSave() throws IOException {
		StorageBag bag = new StorageBag(users, booksByAuthor, booksByAuthor, booksByAuthor, booksByAuthor);
		
		Utilities.writeBinary(Main.getLibrary());
		
		System.exit(0);
	}
	
	
	public void alertUser() {
		Alert a = new Alert(AlertType.NONE);
		a.setAlertType(AlertType.WARNING);
		a.setTitle("OVERDUE BOOK(S) WARNING");

		for (Book temp : loggedUser.getOwnedBook()) {
			if (temp.isOverdue()) {
				a.setContentText("Having an overdue book can result in deactivation/termination");
				a.show();

			} else {
				System.out.println("Not overdue");
			}
		}
	}

	public void changeSceneLogOut(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	public void changeSceneViewUsers(ActionEvent e) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/UserSearch.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}

	public void changeSceneCreateBook(ActionEvent e) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/createBook.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}

	public void changeSceneUpdateAccount(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/updateAccount.fxml"));
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		wordSet = Library.getSpellHash();
		users = Library.getUsers();
		booksByTitle = Library.getBooksByTitle();
		booksByIsbn = Library.getBooksByIsbn();
		booksByAuthor = Library.getBooksByAuthor();
		overDueBooks = Library.getOverDueBooks();
		
		loggedUser = users.get(Library.getUser().getUserName());

		Utilities.fillOverdueBooks(overDueBooks, loggedUser.getOwnedBook(), loggedUser);
		
		alertUser();

		searchBox.getItems().clear();
		searchBox.getItems().addAll("TITLE", "ISBN", "AUTHOR");
		searchBox.getSelectionModel().select("TITLE");

		titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("bookTitle"));
		isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		copiesColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("copies"));

		bookList = Library.getBookList();
	}
}
