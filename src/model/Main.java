package model;

import java.util.LinkedList;

import Helpers.Utilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static Library library;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		library = new Library();

		StorageBag bag = null;
		
		Utilities.fillUserMap(Library.getUsers());
		Utilities.fillBookMapbyTitle(Library.getBooksByTitle());
		Utilities.fillBookMapbyIsbn(Library.getBooksByIsbn());
		Utilities.fillBookMapbyAuthor(Library.getBooksByAuthor());
		
		LinkedList<Book> borrowHistory  = new LinkedList<Book>();
		
		Library.getUsers().put("Jane", new User("Jane", "Doe", "123 Somewhere St" , "631-345-1234", "Jane", "Doe", Role.ADMIN, borrowHistory));
		Library.getUsers().put("John", new User("John", "Doe", "321 Somewhere St" , "631-325-1434", "John", "Doe", Role.PATRON, borrowHistory));

		Library.fillHash();
		Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Big Library Nation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static Library getLibrary() {
		return library;
	}
}
