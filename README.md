<img src="https://github.com/shruti0085/594-AutoComplete/blob/master/Media/shield-only-RGB-4k.png" align="left" width="60" hieght="60"> <img src="https://github.com/shruti0085/594-AutoComplete/blob/master/Media/icon.png" align="right" />


# BATSMobile "The Becca Alex Tierra Shruti - Model"

### Running The Program
0. Make sure you have Latest JDK installed on your machine.
1. Add the zipped contents on this repo above to a local folder (go to top right Clone-or-Download button in green to download zip file).  `cd` into your working directory
2. Run `Javac AutocompleteGUI.java <NameOfTextFile.txt>` in command line... that's it!  The GUI should pop up!
3. If you would rather use an IDE, make sure you have Eclipse IDE installed on your machine, have a JUnit 4 testing set up in your build path, and have a working internet connection.
4. Find `run` in the top ribbon of Eclipse and select `run configurations`.  In the next pop-up window go the tab that says `arguments` and in program arguments type the name of the file you choose to train on. 
   - We'll get you started.  Try some of these very entertaining .txt files we added to our repo:
   
      > [Star Wars Episode IV](https://github.com/shruti0085/594-AutoComplete/blob/master/StarWarsEpisodeIV.txt)
      
      > [Star Wars Episode V](https://github.com/shruti0085/594-AutoComplete/blob/master/StarWarsEpisodeV.txt)
      
      > [The Fault in our Stars!](https://github.com/shruti0085/594-AutoComplete/blob/master/fault.txt)
      
      > [Catcher in the Rye](https://github.com/shruti0085/594-AutoComplete/blob/master/catcher.txt)
      
4. (Optional) Run Unit tests. You can do this before step 1 or at any point, to make sure things are functioning properly. Simply run the `AutocompleteTest.java` from the IDE.

## Implementation Details

### Autocomplete Algorithm
Our autocomplete algorithm is based largely off of the frequency with which a given word shows up in a Data Set and all the possible words that follow a prefix entered by the user. More broadly, a word's likelihood of being suggested by our BATSMobile as an acceptable successor to a user input is definined by whether or not there are enough successors given it's frequency in the data set following a prefix and if our Trie Tree Data Structure can fill in suggestions for all the possible words if there's not enough suggestions.  

## Design
Our package involves several classes, whose relationship and summary can be found in the UML diagram below. Further details also follow.

### UML Diagram 

Feel free to scroll to view whole diagram or download PDF by [clicking here](https://github.com/shruti0085/594-AutoComplete/blob/master/Media/UML_Diagram.pdf)

![BATSMO UML](https://github.com/shruti0085/594-AutoComplete/blob/master/Media/Autocomplete%20UML_cropped.png)
