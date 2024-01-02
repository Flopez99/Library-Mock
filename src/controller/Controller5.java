package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Book;
import model.Library;
import model.Role;
import model.User;

public class Controller5 implements Initializable {
	private HashMap<String, User> users;
	private User loggedUser;
	ObservableList<Book> bookList;
	ObservableList<Book> bookList1;

	@FXML
	private TextField firstName1, lastName1, phoneNumber1, address1, userName1;
	@FXML
	private PasswordField password1;
	@FXML
	private ImageView profilePicture1;
	@FXML
	private TableView<Book> bookSelection2;
	@FXML
	private TableColumn<Book, String> isbnColumn2;
	@FXML
	private TableColumn<Book, String> titleColumn2;
	@FXML
	private TableColumn<Book, String> authorColumn2;
	@FXML
	private TableColumn<Book, String> dueDateColumn2;
	@FXML
	private TableView<Book> bookSelection11;
	@FXML
	private TableColumn<Book, String> isbnColumn11;
	@FXML
	private TableColumn<Book, String> titleColumn11;
	@FXML
	private TableColumn<Book, String> authorColumn11;
	@FXML
	private TableColumn<Book, String> dueDateColumn11;
	@FXML
	private Label selectedBook;
	@FXML
	private ImageView profilePicture;

	public void returnBookSelected(ActionEvent e) throws IOException {

		bookList1.addListener(new ListChangeListener<Book>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Book> pChange) {
				while (pChange.next()) {

				}
			}
		});
		selectedBook
				.setText("You Last Returned: " + bookSelection11.getSelectionModel().getSelectedItem().getBookTitle());
		loggedUser.returnBook(bookSelection11.getSelectionModel().getSelectedItem());
		bookList1.remove(bookSelection11.getSelectionModel().getSelectedItem());
	}

	public void updateAccount(ActionEvent e) throws IOException {
		loggedUser.setFName(firstName1.getText());
		loggedUser.setLName(lastName1.getText());
		loggedUser.setPhone(phoneNumber1.getText());
		loggedUser.setAddress(address1.getText());
		loggedUser.setPassword(userName1.getText());
		//loggedUser.setProfilePicture(selectPicture(e));
		
		

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

	@FXML
	public Image selectPicture(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		FileChooser fc = new FileChooser();

		fc.setInitialDirectory(new File("C:\\Users\\Facundo Lopez\\Pictures\\Final_Project"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("JPG Files", "*.jpg"));

		File selectedFile = fc.showOpenDialog(stage);

		if (selectedFile != null) {

			try {
				BufferedImage bufferedImage = ImageIO.read(selectedFile);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);

				profilePicture1.setImage(image);
				return image;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("error");
		}
		return null;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		users = Library.getUsers();
		loggedUser = users.get(Library.getUser().getUserName());

		bookList = FXCollections.observableArrayList(loggedUser.getBorrowHistory());
		bookList1 = FXCollections.observableArrayList(loggedUser.getOwnedBook());

		firstName1.setText(loggedUser.getFName());
		lastName1.setText(loggedUser.getLName());
		phoneNumber1.setText(loggedUser.getPhone());
		address1.setText(loggedUser.getAddress());
		userName1.setText(loggedUser.getUserName());
		password1.setText(loggedUser.getPassword());

		titleColumn2.setCellValueFactory(new PropertyValueFactory<Book, String>("bookTitle"));
		isbnColumn2.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		authorColumn2.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		dueDateColumn2.setCellValueFactory(new PropertyValueFactory<Book, String>("dueDate"));

		titleColumn11.setCellValueFactory(new PropertyValueFactory<Book, String>("bookTitle"));
		isbnColumn11.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		authorColumn11.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		dueDateColumn11.setCellValueFactory(new PropertyValueFactory<Book, String>("dueDate"));

		bookSelection2.setItems(bookList);
		bookSelection11.setItems(bookList1);
		bookSelection11.setRowFactory(tv -> new TableRow<Book>() {
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

	}

}
