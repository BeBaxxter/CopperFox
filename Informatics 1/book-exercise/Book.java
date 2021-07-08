/**
 * A class that maintains information on a book.
 * This might form part of a larger application such
 * as a library system, for instance.
 *
 * @author Alexander Jäger, Lucas Carmohn 
 * @version 0.1 (23.11.20)
 */
class Book
{
    // The fields.
    private String author;
    private String title;
    private String refNumber;
    
    private int pages;

    /**
     * Set the author and title fields when this object
     * is constructed.
     */
    public Book(String bookAuthor, String bookTitle, int bookPages)
    {
        author = bookAuthor;
        title = bookTitle;
        pages = bookPages;
        refNumber = "";
    }

    /**
     * Returns the author of the current object.
     */
    public String getAuthor()
    {
        return author;
    } 
    
    /**
     * Returns the title of the current object.
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Returns the pages of the current object.
     */
    public int getPages()
    {
        return pages;
    }
    
    /**
     * Returns the reference Number of the current object.
     */
    public String getRefNr()
    {
        return refNumber;
    }
    
    /**
     * Set refNumber with input String ref.
     */
    public void setRefNumber (String ref)
    {
        
        if(ref.length() >= 3)
        {
            refNumber = ref;
        } else 
        {
            System.out.println("[ERROR:] Please insert at lest 3 characters!");
        }  
    }
   
    /**
     * Prints out the author of the current object.
     */
    public void printAuthor()
    {
        System.out.println("Author: " + author);
    } 
    
    /**
     * Prints out the title of the current object.
     */
    public void printTitle()
    {
        System.out.println("Title: " + title);
    }    
   
    /**
     * Prints out the pages of the current object.
     */
    public void printPages()
    {
        System.out.println("Pages: " + pages);
    }  
    
    
    /**
    * Prints out the reference number of the current object.
    */
    public void printrRefNumber()
    {
            
        if(refNumber.length() > 0)
        {
            System.out.println("Reference number: " + refNumber);
        }
    } 
    
    /**
     * Prints out all the Details of the current object.
     */
    public void printDetails()
    {
        System.out.println("Details of current Book:");
        System.out.println("########################");
        printAuthor();
        printTitle();
        printPages();
        printrRefNumber();
        System.out.println("########################");
        
    }
}   
