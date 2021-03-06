This is a CLI based java application which will convert the given list of phone numbers into alpha-numeric representation.

### Application execution requirements:  

    JRE 1.8  
    JUNIT5(to run unit tests)

### Steps to execute:

   First compile the program using javac command.
    
      $ javac src/src/main/Entry/Executor.java  (compile all java files under src/src/main project folder)
      
      Note: Classes are already compiled from Eclipse into bin folder and updated to GitHub repo.
   
   Note: You need to compile the java files under the "src/src/main" project folder.
   
   Run the program using below java command from "bin" project folder:  
   
     $ java src.main.entry.Executor -d <path-to-dictionary-file> -m PhonePad -i <path-to-input-file>
     
   #### Alternatively:
     
     $ java -jar bin/PhoneNumberConvertor.jar -d <path-to-dictionary-file> -m PhonePad -i <path-to-input-file>
    
  #### CLI Options: 
  
       "-m" - used for providing NumberToletter converter strategy. It is a mandatory parameter. If invalid converter is mentioned then we would use "PhonePad".  
       "-i" - used for providing input file(test file) containing the phone numbers.  
       "-d" - used for providing dictionary files(test file) containing bag of words.  
 
 ### Technical Description:
 
This application uses Trie Data Structure to create a dictionary of words provided buy the user. Then the each phonumber is compared with dictionary using DFS appraoch(recursion) to print the different cobinations of alpah-numeric representation for the given phone number. There are no restrictions on the number of letters for a word stored in dictionary or number of digits in phone numbers.
 
 Note: In future we can convert the same application into a micro service based application with better security, observability, reliability and scalilibity.
 
