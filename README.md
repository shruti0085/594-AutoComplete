<img src="https://github.com/shruti0085/594-AutoComplete/blob/master/Media/shield-only-RGB-4k.png" align="left" width="60" height="60"> <img src="https://github.com/shruti0085/594-AutoComplete/blob/master/Media/icon.png" align="right" />


# Autocomplete 
BATSMo autocompletor - The "Becca Alex Tierra Shruti"- Model<br>


### Running The Program
0. Make sure you have Latest JDK (Java Compiler) installed on your machine.  Run `javac -version` in your command line to ensure you have the latest version.  If you do not have Javac skip to Step 3.
1. Add the zipped contents to a local folder and `cd` into your working directory
2. Run `java AutocompleteGUI.java <NameOfTextFile.txt>` in command line... that's it!  The GUI should pop up! Example: %java AutocompleteGUI StarWarsEpisodeV.txt
3. If you would rather use an IDE or do not have javac installed, make sure you have Eclipse IDE installed on your machine, have a JUnit 4 or 5 testing set up in your build path, and have a working internet connection.
4. Find `run` in the top ribbon of Eclipse and select `run configurations`.  In the next pop-up window go the tab that says `arguments` and in program arguments type the name of the file you choose to train on. 
   - We'll get you started.  Try some of these very entertaining .txt files we have included:
   
      > Star Wars Episode IV (StarWarsEpisodeIV.txt)
      
      > Star Wars Episode V (StarWarsEpisodeV.txt)
      
      > Fifty Shades of Grey (50shades.txt)
      
      > The Fault in our Stars (fault.txt)
      
      > The Catcher in the Rye (catcher.txt)

5. Using the GUI: Please use mouse click to select the words from the suggested ones. If you press "enter" instead, previously typed string will disappear. Note that GUI was implemented by the third party which was allowed by Dr. Fouh.
      
6. (Optional) Run Unit tests. You can do this before step 1 or at any point, to make sure things are functioning properly. Simply run the `AutocompleteTest.java` from the IDE.

## Implementation Details

### Autocomplete Algorithm
Our autocomplete algorithm is based largely off of the frequency with which a given word shows up in a Data Set and all the possible words that follow a prefix entered by the user. More broadly, a word's likelihood of being suggested by our BATSMobile autocompletor as an acceptable successor to user input is defined by whether or not there are enough successors given it's frequency in the data following a prefix and if our Trie Tree Data Structure can fill in suggestions for all the possible words if there's not enough suggestions.  
