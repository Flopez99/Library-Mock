package Helpers;

import java.io.BufferedReader;
import java.io.File;
import static java.util.concurrent.TimeUnit.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;

import model.Author;
import model.Book;
import model.Library;
import model.Name;
import model.Role;
import model.User;

public class Utilities {

	public static Name[] emitName() {

		String firstNameFileName = "RawData/firstName.txt";
		String lastNameFileName = "RawData/lastName.txt";

		File firstNameFile = new File(firstNameFileName);
		File lastNameFile = new File(lastNameFileName);
		Scanner scanner1 = null;
		Scanner scanner2 = null;

		try {
			scanner1 = new Scanner(firstNameFile);
			scanner2 = new Scanner(lastNameFile);

		} catch (FileNotFoundException e) {
			System.out.println("ERROR:");
			System.out.println("Could not find file path.");
		}

		String[] firstNameArr = new String[2000];
		String[] lastNameArr = new String[2000];

		int i = 0;
		while (scanner1.hasNextLine() && scanner2.hasNextLine()) {
			firstNameArr[i] = scanner1.nextLine();
			lastNameArr[i] = scanner2.nextLine();
			i++;
		}

		Name[] nameArr = new Name[30001];
		int j = 0;
		while (j < nameArr.length) {
			Name name = new Name(firstNameArr[(int) (Math.random() * 2000)], lastNameArr[(int) (Math.random() * 2000)]);
			nameArr[j++] = name;

		}
		scanner1.close();
		scanner2.close();

		return nameArr;
	}

	public static String[][] emitTitleAndIsbn() {
		String[][] titleAndIsbn = new String[38639][2];

		String titleFileName = "RawData/title.txt";
		String isbnFileName = "RawData/isbn.txt";

		File titleFile = new File(titleFileName);
		File isbnFile = new File(isbnFileName);
		Scanner scanner1 = null;
		Scanner scanner2 = null;

		try {
			scanner1 = new Scanner(titleFile, "UTF-8");
			scanner2 = new Scanner(isbnFile, "UTF-8");

		} catch (FileNotFoundException e) {
			System.out.println("ERROR:");
			System.out.println("Could not find file path.");
		}

		int i = 0;

		while (scanner1.hasNextLine() && scanner2.hasNextLine()) {

			titleAndIsbn[i][0] = scanner2.nextLine();
			titleAndIsbn[i][1] = scanner1.nextLine();

			i++;
		}

		scanner1.close();
		scanner2.close();

		return titleAndIsbn;
	}

	public static void fillBookMapbyTitle(TreeMap<String, Book> bookTree) {
		int index = 0;
		String[][] titleAndIsbn = emitTitleAndIsbn();
		Name[] names = emitName();

		while (index < 10000) {
			Book book = new Book(titleAndIsbn[index][1], titleAndIsbn[index][0],
					names[index].getFirstName() + " " + names[index].getLastName(), 1, "");
			bookTree.put(book.getBookTitle(), book);
			index++;
		}

		System.out.println("Done!");
	}

	public static void fillBookMapbyIsbn(TreeMap<String, Book> bookTree) {
		int index = 0;
		String[][] titleAndIsbn = emitTitleAndIsbn();
		Name[] names = emitName();

		while (index < 10000) {
			Book book = new Book(titleAndIsbn[index][1], titleAndIsbn[index][0],
					names[index].getFirstName() + " " + names[index].getLastName(), 1, "");
			bookTree.put(book.getIsbn(), book);
			index++;
		}

		System.out.println("Done!");
	}

	public static void fillBookMapbyAuthor(TreeMap<String, Book> bookTree) {
		int index = 0;
		String[][] titleAndIsbn = emitTitleAndIsbn();
		Name[] names = emitName();

		while (index < 10000) {
			Book book = new Book(titleAndIsbn[index][1], titleAndIsbn[index][0],
					names[index].getFirstName() + " " + names[index].getLastName(), 1, "");
			
			bookTree.put(book.getAuthor(), book);
			index++;
		}

		System.out.println("Done!");
	}

	public static void fillUserMap(HashMap<String, User> userTree) {
		int index = 0;
		Random rand = new Random();
		Name[] names = emitName();
		String[] phoneNumbers = makePhoneNumbers();
		String userName = "";

		while (index <= 500) {

			String firstName = names[rand.nextInt(names.length)].getFirstName();
			String lastName = names[rand.nextInt(names.length)].getLastName();
			String phoneNumber = phoneNumbers[rand.nextInt(phoneNumbers.length)];
			userName = firstName + lastName.charAt(0) + phoneNumber.substring(3);
			LinkedList<Book> list = new LinkedList<Book>();
			
			User user = new User(firstName, lastName, "Address 123", phoneNumber, userName, "123", Role.PATRON, list);

			userTree.put(userName, user);

			index++;
		}
		System.out.println("Done");
		userTree.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + " " + entry.getValue());
		});
	}

	public static String[] makePhoneNumbers() {

		String[] phoneNumbers = new String[500];
		Random rand = new Random();
		String phoneNumber = "";

		for (int i = 0; i < phoneNumbers.length; i++) {
			int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
			int num2 = rand.nextInt(743);
			int num3 = rand.nextInt(10000);

			DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
			DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros

			phoneNumber = df3.format(num1) + "-" + df3.format(num2) + "-" + df4.format(num3);

			phoneNumbers[i] = phoneNumber;

		}
		return phoneNumbers;
	}

	@SuppressWarnings("unchecked")
	public static HashSet<String> spellCheckHash(HashSet<String> wordSet) {
		try {
			ObjectInputStream s = new ObjectInputStream(new FileInputStream("RawData/words.hashset"));
			wordSet = (HashSet<String>) s.readObject();
		} catch (Exception e) {
			System.out.println("No Spell Checking");
		}

		return wordSet;
	}

	public static String getDueDate() {
		Date dueDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dueDate);
		cal.add(Calendar.MINUTE, 5);
		dueDate = cal.getTime();

		SimpleDateFormat ft = new SimpleDateFormat("MM/dd/YYYY hh:mm:ss");
		String dDate = ft.format(dueDate);

		return dDate;
	}

	public static void fillOverdueBooks(TreeMap<String, Book> odBooks, LinkedList<Book> userBooks, User user) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY hh:mm:ss");
		Date currentTime = new Date(System.currentTimeMillis());
		String current = sdf.format(currentTime);

		for (Book temp : userBooks) {
			if (current.compareTo(temp.getDueDate()) >= 0) {
				temp.setOverdue(true);
				temp.setOffendingPatron(user.getUserName());
				odBooks.put(temp.getDueDate(), temp);

				System.out.println("Overdue");
			} else {
				System.out.println("Not overdue");

			}
		}

	}

	public static void writeBinary(Library library) throws IOException {
		FileOutputStream dos = new FileOutputStream("RawData/data.dat");
		ObjectOutputStream oos = new ObjectOutputStream(dos);
		oos.writeObject(library);
		oos.close();

		System.out.println("Done writing!");
	}

	@SuppressWarnings("static-access")
	public static void readBinary(Library library) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("RawData/data.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Library l = (Library) (ois.readObject());
		ois.close();

		library.setBookList(l.getBookList());
		library.setBooksByAuthor(l.getBooksByAuthor());
		library.setBooksByIsbn(l.getBooksByIsbn());
		library.setBooksByTitle(l.getBooksByTitle());
		library.setOverDueBooks(l.getOverDueBooks());
		library.setOverDueBooksList(l.getOverDueBooksList());
		library.setUserList(l.getUserList());
		library.setUsers(l.getUsers());

	}

}
