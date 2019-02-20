//Accessory class for photo objects to place in ArrayList
public class Photo {
	/**
	 * Member variables, int for photo ID and String for photo title
	 */
	int id;
	String title;
	
	/**
	 * Constructor
	 * @param id
	 * @param title
	 */
	public Photo(int id, String title) {
		this.id = id;
		this.title = title;
	}
	/**
	 * Getter for ID
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Setter for ID
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Getter for title
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Setter for title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Formatted toString to deliver output as indicated in the requirements document
	 */
	@Override
	public String toString() {
		return "[" + id + "]"+" "+title+"\n";
	}
	
}
