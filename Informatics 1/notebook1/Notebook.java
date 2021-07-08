import java.util.ArrayList;

/**
 * A class to maintain an arbitrarily long list of notes.
 * Notes are numbered for external reference by a human user.
 * In this version, note numbers start at 0.
 * 
 * @author David J. Barnes and Michael Kolling.
 * @version 2008.03.30
 */
public class Notebook
{
    // Storage for an arbitrary number of notes.
    private ArrayList<String> notes;

    /**
     * Perform any initialization that is required for the
     * notebook.
     */
    public Notebook()
    {
        notes = new ArrayList<String>();
    }

    /**
     * Store a new note into the notebook.
     * @param note The note to be stored.
     */
    public void storeNote(String note)
    {
        notes.add(note);
    }

    /**
     * @return The number of notes currently in the notebook.
     */
    public int numberOfNotes()
    {
        return notes.size();
    }

    /**
     * Show a note.
     * @param noteNumber The number of the note to be shown.
     */
    public void showNote(int noteNumber)
    {
        if(noteNumber < 0) {
            // This is not a valid note number, so do nothing.
        }
        else if(noteNumber < numberOfNotes()) {
            // This is a valid note number, so we can print it.
            System.out.println(notes.get(noteNumber));
        }
        else {
            // This is not a valid note number, so do nothing.
        }
    }
    
    /**
     * List all notes from notes
     */
    public void listAllNotes() {      
        for (int i = 0; i < notes.size(); i ++) {

            System.out.println( i + ":" + notes.get(i));
            
        }
    }
    
    /**
     * Remove the note with the given index.
     * @param index the position inside the arraylist that you want to remove.
     */
    public void removeNote( int index) {
    
        if ( index >= 0 && index < notes.size()) {
            notes.remove(index);
        } else {
            System.out.println("The number that was entered is not valid.");
        }
   
    }
    
    /**
     * Search and output all results based on the given searchterm.
     * @param searchterm the term that you want to search.
     */
    public void searchNote(String searchterm) {
     
        boolean foundNote = false;
        int i = 0;
        
        System.out.println("-----------------------------" + "\n" + "Start searching for: " + searchterm + "\n");
        
        for (String note : notes) {
        
            if (note.contains(searchterm)) {
                foundNote = true;
                System.out.println(i + ":" + note);
            }
        
            i++;
        }
        
        if (!foundNote) {
            System.out.println("Search term not found.");
        }
    }
    
    /**
     * Same like searchNote but working with a while loop.
     * @param searchterm the term that you want to search.
     */
    public void searchNoteWhile(String searchterm) {
     
        boolean foundNote = false;
        int i = 0;
        String note;
        int listSize = notes.size();
        
        System.out.println("-----------------------------" + "\n" + "Start searching for: " + searchterm + "\n");
        
        while (i <= listSize -1){
        
            note = notes.get(i);
                     
            if (note.contains(searchterm)) {
                foundNote = true;
                System.out.println(i + ":" + note);
            }
            
            i++;
        }

        if (!foundNote) {
            System.out.println("Search term not found.");
        }
    }   
}
