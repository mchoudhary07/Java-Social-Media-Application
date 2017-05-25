//Post class declaration
public class Post {
	private String date;      //date string to store the date of post
	private String text;		//text string stores the text of post
	private Photo photo;		//photo object stores the photo details if present in the post
	
	//Parameterized constructor to set date and text for a post
	public Post(String date, String text) {
		super();
		this.date = date;
		this.text = text;
	}

	//Get method to retrieve the date of post
	public String getDate() {
		return date;
	}

	//set method to set the date of the post
	public void setDate(String date) {
		this.date = date;
	}

	//Get method to retrieve the text of the post
	public String getText() {
		return text;
	}

	//Set method to set the text of the post
	public void setText(String text) {
		this.text = text;
	}

	//Get method to retrieve the photo in post if present
	public Photo getPhoto() {
		return photo;
	}

	//Set method to set the photo in post if present
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	//addPhoto works same as the set method for the photo
	public void addPhoto(Photo photo){
		this.setPhoto(photo);
	}

}
