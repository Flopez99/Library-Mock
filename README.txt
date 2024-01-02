Data Structures Used and Purpose:

1. User Storage: For holding all of my users I used a HashMap with storing the user's userName as the key.
				 This provides me with very fast user look up, and I can modify the user freely by having its userName.
				 Only problem is having to predict how many users will ultimately be in the system, I set the number at 50000 to reduce collisions.
				 
2. Book Storage: For holding all of my books I used three different TreeMaps, having each field (Title, Isbn, Last Name) as a key so it provides really
				 good performance when searching by the according key. When inserting all of the books, if the book title or author last name is the same,
				 a number will be added to it in order to keep the key unique. Only problem with this is having to change the same book in all three trees
				 when there is a change in any of the books.
		
3. Dictionary Spell Check: For the spell check I used a HashSet so I can store all the words in the dictionary since sets
						   are designed to match the mathematical model of a set of numbers. All of the words are unique,
						   which is why it is good to use a simple method which will check if the word is contained inside,
						   if it doesn't it will say that the word was incorrectly spelled since it is not in the HashSet dictionary.
						 
						 
4. User Borrowing History: For the user borrowing history I implemented a TreeMap which stores the time the book was checked out as the key
						   and the book as the value. This is good because it can store large amounts of data which a borrowing history usually contains.
						   

5. Books In Possession Of User: For the books that a user has currently checked out, I used a linked list since it is usually a small amount of data 
								so it would not take a long time to traverse in order to find a desired book. Since the traversal is fast, it's also 
								good to allow for the user to return the book they want to return.
								


6. All Over Due Books: For all the overdue books I implemented a TreeMap that gets updated every time a user has a book that is currently over due.
					   This is a good data structure to use since many users might have over due books at the same time. 



