package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Book;
import model.Library;
import model.User;

public class Controller3 implements Initializable {
	private static User pickedUser;

	private HashMap<String, User> users;
	private TreeMap<String, Book> overDueBooks;

	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User, String> firstNColumn;
	@FXML
	private TableColumn<User, String> lastNColumn;
	@FXML
	private TableColumn<User, String> phoneColumn;
	@FXML
	private TableColumn<User, String> addressColumn;
	@FXML
	private TableColumn<User, Boolean> statusColumn;
	@FXML
	private TableColumn<User, String> usernameColumn;
	@FXML
	private TableColumn<Book, String> isbnColumn11;
	@FXML
	private TableColumn<Book, String> titleColumn11;
	@FXML
	private TableColumn<Book, String> authorColumn11;
	@FXML
	private TableColumn<Book, String> dueDateColumn11;
	@FXML
	private TableView<Book> bookSelection11;
	@FXML
	private TextField searchBox;

	ObservableList<Book> bookList1;
	ObservableList<User> userList;

	public void searchUser(ActionEvent e) {
		userList.clear();

		Map<String, User> result = null;
		result = (Library.getUsers().entrySet().stream().filter(map -> map.getKey().startsWith(searchBox.getText()))
				.collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue())));

		for (java.util.Map.Entry<String, User> entry : result.entrySet()) {
			userList.add(entry.getValue());

		}
		userTable.setItems(userList);

	}

	public void showOverdueBooks() {

		Map<String, Book> result = null;
		result = (overDueBooks.entrySet().stream()
				.collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue())));

		for (java.util.Map.Entry<String, Book> entry : result.entrySet()) {
			bookList1.add(entry.getValue());

		}
		bookSelection11.setItems(bookList1);
	}

	public void changeScenePatronInfo(MouseEvent e) throws IOException { // Changed Library to users
		User user = users.get(userTable.getSelectionModel().getSelectedItem().getUserName());
		if (user != null) {

			user.setBorrowHistory(users.get(user.getUserName()).getBorrowHistory());
			pickedUser = user;

			Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/PatronInfo.fxml"));
			Scene secondScene = new Scene(secondRoot);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(secondScene);
			window.show();
		}
	}

	public void changeScenePatronInfo1(MouseEvent e) throws IOException { // Changed Library to users
		User user = users.get(bookSelection11.getSelectionModel().getSelectedItem().getOffendingPatron());
		if (user != null) {

			user.setBorrowHistory(users.get(user.getUserName()).getBorrowHistory());
			pickedUser = user;

			Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/PatronInfo.fxml"));
			Scene secondScene = new Scene(secondRoot);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(secondScene);
			window.show();
		}
	}
	public void changeSceneLogIn(ActionEvent e) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/Admin.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}

	public void changeSceneViewUsers(ActionEvent e) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/UserSearch.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		users = Library.getUsers();
		userList = Library.getUserList();
		bookList1 = FXCollections.observableArrayList();
		overDueBooks = Library.getOverDueBooks();

		usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
		firstNColumn.setCellValueFactory(new PropertyValueFactory<User, String>("fName"));
		lastNColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lName"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<User, Boolean>("isActive"));

		titleColumn11.setCellValueFactory(new PropertyValueFactory<Book, String>("bookTitle"));
		isbnColumn11.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		authorColumn11.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		dueDateColumn11.setCellValueFactory(new PropertyValueFactory<Book, String>("dueDate"));

		
		bookSelection11.setItems(bookList1);
		showOverdueBooks();
		
	}

	public static User getPickedUser() {
		return pickedUser;
	}
}
