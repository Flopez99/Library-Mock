package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Book;
import model.Library;
import model.Role;
import model.User;

public class Controller4 implements Initializable {
	private HashMap<String, User> users;
	private User user;

	@FXML
	private ImageView userProfilePicture;
	@FXML
	private Label firstNameLabel, lastNameLabel, phoneLabel, addressLabel, roleLabel, statusLabel;
	@FXML
	private TextField userNameField;

	@FXML
	private TableView<Book> bookSelection;
	@FXML
	private TableColumn<Book, String> isbnColumn;
	@FXML
	private TableColumn<Book, String> titleColumn;
	@FXML
	private TableColumn<Book, String> authorColumn;
	@FXML
	private TableColumn<Book, String> dueDateColumn;

	ObservableList<Book> bookList;

	@FXML
	public void deleteUserAccount(ActionEvent event) throws IOException {
		users.remove(user.getUserName());
		changeSceneViewUsers(event);
	}

	@FXML
	public void disableUserAccount(ActionEvent event) {
		user.setIsActive(false);
	}

	@FXML
	public void promoteUserAccount(ActionEvent event) {
		user.setRole(Role.ADMIN);
	}

	public void changeSceneViewUsers(ActionEvent e) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/UserSearch.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}

	public void displayUserInfo() {

		firstNameLabel.setText(user.getFName());
		lastNameLabel.setText(user.getLName());
		phoneLabel.setText(user.getPhone());
		addressLabel.setText(user.getAddress());
		userNameField.setText(user.getUserName());
		roleLabel.setText(user.getRole() + "");
		if (user.getIsActive()) {
			statusLabel.setText("Active");
		} else {
			statusLabel.setText("Suspended");
		}
		userProfilePicture.setImage(user.getProfilePicture());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		users = Library.getUsers();
		user = users.get(Controller3.getPickedUser().getUserName());
		bookList = FXCollections.observableArrayList(user.getBorrowHistory());
		
		//bookList = FXCollections.observableArrayList(Library.getUsers().get(user.getUserName()).getBorrowHistory());		
		
		titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("bookTitle"));
		isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		dueDateColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("dueDate"));

		bookSelection.setItems(bookList);
		bookSelection.setRowFactory(tv -> new TableRow<Book>() {
			@Override
			protected void updateItem(Book item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null)
					setStyle("");
				else if (!item.isOverdue())
					setStyle("");
				else if (item.isOverdue())
					setStyle("-fx-background-color: #ffd7d1;");
				else
					setStyle("");
			}
		});
		displayUserInfo();

	}

}
