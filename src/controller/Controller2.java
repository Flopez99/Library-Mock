package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.imageio.ImageIO;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Book;
import model.Library;
import model.Role;
import model.User;

public class Controller2 implements Initializable {
	private static User loggedUser;
	private HashMap<String, User> users;
	@FXML
	private TextField userName, password, logUser, phoneNumber, firstName, lastName, address;
	@FXML
	private ImageView profilePicture;

	
	public void createUser(ActionEvent event) throws IOException {
		String uName = userName.getText();
		String fName = firstName.getText();
		String lName = lastName.getText();
		String uAddress = address.getText();
		String pWord = password.getText();
		String pNumber = phoneNumber.getText();
		Role role = null;

		if (users.isEmpty()) {
			role = Role.ADMIN;
		} else {
			role = Role.PATRON;
		}

		User user = new User(fName, lName, uAddress, pNumber, uName, pWord, role, new LinkedList<Book>());

		user.setProfilePicture(selectPicture(event));
		System.out.println(user);
		users.put(uName, user);

		userName.clear();
		password.clear();
		phoneNumber.clear();
		firstName.clear();
		lastName.clear();
		address.clear();

		System.out.println(users);

		changeSceneLogOut(event);
	}

	public void logIn(ActionEvent event) throws IOException {
		String uName = userName.getText();
		String pWord = password.getText();

		if (users.containsKey(uName) && users.get(uName).getPassword().equals(pWord)) {
			loggedUser = users.get(uName);
			Library.setUser(loggedUser);
			changeSceneLogIn(event);
		} else {
			System.out.println("Error");
		}

	}
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
				profilePicture.setImage(image);
				return image;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				BufferedImage imageB = ImageIO.read(new File("RawData/defaultUser.jpg"));
				Image image = SwingFXUtils.toFXImage(imageB, null);
				profilePicture.setImage(image);
				return image;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return null;
		}
		return null;
	}

	public void changeSceneSignUp(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	public void changeSceneLogOut(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
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

	}

}
