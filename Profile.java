import java.util.*;

//Profile class declaration
public class Profile {
	private ArrayList<Photo> photos;		//object array stores photo details for profile
	private ArrayList<Post> posts;			//stores posts details
	private ArrayList<Message> messages;		//stores messages details
	private ArrayList<String> contacts;		//stores contact details
	String username;		//stores username of profile
	String gender;			//stores gender of profile
	
	//Arraylist variables for all object arrays declared above 
	//ArrayList<Object> photosArr = new ArrayList<Object>();
	//ArrayList<Object> postsArr = new ArrayList<Object>();
	//ArrayList<Object> messagesArr = new ArrayList<Object>();
	//ArrayList<Object> contactsArr = new ArrayList<Object>();
	
	
	//Parameterized constructor to set the Profile details for new profile
	public Profile(String username, String gender) {
		super();
		this.username = username;
		this.gender = gender;
	}
	
	//get photos details
	public ArrayList<Photo> getPhotos() {
		if (photos!=null){
			return photos;
		}
		return null;
	}
	
	//sets photo details
	public void setPhotos(ArrayList<Photo> photos) {
		this.photos = photos;
	}
	
	//gets posts details
	public ArrayList<Post> getPosts() {
		return posts;
	}
	
	//sets posts details
	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}
	
	//gets the message details
	public ArrayList<Message> getMessages() {
		return messages;
	}
	
	//sets the message details
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
	//gets the contacts details
	public ArrayList<String> getContacts() {
		return contacts;
	}
	
	//sets the contact details
	public void setContacts(ArrayList<String> contacts) {
		this.contacts = contacts;
	}
	
	//gets the username of profile
	public String getUsername() {
		return username;
	}
	
	//sets the profile's username
	public void setUsername(String username) {
		this.username = username;
	}
	
	//gets the gender of the profile
	public String getGender() {
		return gender;
	}
	
	//sets the profile's gender
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	//method which takes strings from input file for parsing and stores the data to concerned arrays
	public void parseDataDump(ArrayList<String> lines) throws DataParseException{			//method to analyze lines from file.
		int j=0;
		//int k=0;
		//matchfound variable to check if the profile is present in lines or not
		//boolean matchFound = false;
		//breakpoint when a matchfound is found for a profile
		while(lines.iterator().hasNext()){
		//while (j<lines.size()){
			String fLine = lines.get(j);
			if (fLine.startsWith("PROFILE")){
				String pName = fLine.split(" ")[1];
				String pGender = fLine.split(" ")[2];
				if ((pName.equals(this.getUsername())) && (pGender.equals(this.getGender()))){
					j=j+1;
				}
				else{
					boolean matchFound = false;
					for (int k=j;k<lines.size();k++){
						String kLine = lines.get(k);
						if (kLine.startsWith("PROFILE")){
							String kName = kLine.split(" ")[1];
							String kGender = kLine.split(" ")[2];
							if ((kName.equals(this.getUsername())) && (kGender.equals(this.getGender()))){
								j=k+1;
								matchFound = true;
							}
						}
					}
					if (matchFound==false){
						break;
					}
				}
			}
			String aLine = lines.get(j);
		
			if (minePhoto(aLine) || mineContact(aLine) || minePost(aLine) || mineMessage(aLine)){
				System.out.println("Data added successfully");
			}
			else{
				throw new DataParseException("class DataParseException");
			}
			j++;
		}
		
	}
	
	//this method checks if the lines string is a photo or not 
	//if yes then call another method to add the photo to concerned object
	private boolean minePhoto(String line){
		//if condition to determine photo object from a string
		if ((line.contains(":/") || line.contains(":\\")) && (line.startsWith("POST#")==false)){
			String pLoc = line.split(" ")[0];
			String pname = "";
			//pname holds the name of photo
			if (line.contains(":/"))
			{
			pname = pLoc.substring(pLoc.lastIndexOf("/")+1);
			}
			else{
				pname = pLoc.substring(pLoc.lastIndexOf("\\")+1);
			}
			//psize holds the size of photo in integer 
			int psize = Integer.parseInt(line.split(" ")[1]);
			//creates a new photo object
			Photo addPhoto = new Photo(pname, psize, pLoc);
			//method called to add photo object ot corresponding array of profile
			this.inefficientAndDangerousAddObject(addPhoto);
			return true;
		}
		return false;
	}
	
	//method checks for contacts in the lines array
	//further processing is similar to minePhoto method 
	private boolean mineContact(String line){
		if (line.startsWith("CONTACT")){
			String addContact = line.replace("CONTACT ", "");
			this.inefficientAndDangerousAddObject(addContact);
			return true;
		}
		return false;
	}
	
	//method checks for posts in lines array
	//processing similar to photo objects
	private boolean minePost(String line){
		if (line.startsWith("POST")){
			String addPostPhoto = line.substring(line.lastIndexOf("#")+1);
			//if post contains a photo object then below if block is executed and processing similar to minePhoto method
			if (addPostPhoto.contains(":/")){
				this.minePhoto(addPostPhoto);
			}
			String postDate = line.split("#")[1];
			String postText = line.split("#")[2];
			Post addPost = new Post(postDate, postText);
			Object[] postPhoto = getPhotos().toArray();
			System.out.println(postPhoto.length);
			Photo addPhoto1 = (Photo) postPhoto[postPhoto.length - 1];
			addPost.setPhoto(addPhoto1);
			this.inefficientAndDangerousAddObject(addPost);
			return true;
		}
		return false;
	}
	
	//method to check for messages in lines array
	//processing again similar to above mine methods only
	private boolean mineMessage(String line){
		if (line.startsWith("MESSAGE")){
			String mcontact = line.split("#")[1];
			String mdate = line.split("#")[2];
			String mbody = line.split("#")[3];
			//this.mineContact(mcontact);
			Message addMsg = new Message(mcontact, mdate, mbody);
			this.inefficientAndDangerousAddObject(addMsg);
			this.inefficientAndDangerousAddObject(mcontact);
			return true;
		}
		return false;
	}
	
	//method takes input parameter from mine methods, then does processin if required and finally add the data to concerned arrays
	public void inefficientAndDangerousAddObject(Object object){
		//check if object receive is an instance of photo
		if (object instanceof Photo){
			//if yes, then typecse the object to Object
			Object aPhoto = (Object) Photo.class.cast(object);
			boolean photoPresent = false;
			if (photos!=null){
				for (int l=0;l<photos.size();l++){
					if (aPhoto.equals(photos.get(l))){
						photoPresent = true;
					}
				}
			}
			if (photoPresent == false){
				photos.add((Photo) aPhoto);
			}
			
			//this.photos = Profile.ListToArrayConversion(photosArr, photos);
			
		}
		
		//checks if the input object is an instance of string or not for contacts array
		if (object instanceof String){
			Object aContact = (Object) String.class.cast(object);
			//contactsArr.add(aContact);
			boolean contactPresent = false;
			if (contacts.equals(null)==false){
				for (int a=0;a<contacts.size();a++){
					if (aContact.equals(contacts.get(a))){
						contactPresent = true;
					}
				}
			}
			if (contactPresent ==false){
				contacts.add((String) aContact);
			}
			//this.contacts = Profile.ListToArrayConversion(contactsArr, contacts);
			//if (contactsArr == null){
				
			//}
		}
		
		//checks for object as post instance and if yes, then add data to arraylist of post
		if (object instanceof Post){
			Object aPost = (Object) Post.class.cast(object);
			posts.add((Post) aPost);
			//this.posts = Profile.ListToArrayConversion(posts, posts);
		}
		
		//method checks for object as message and if yes, then add data to messageArr arraylist
		if (object instanceof Message){
			Object aMsg = (Object) Message.class.cast(object);
			messages.add((Message) aMsg);
			//this.messages = Profile.ListToArrayConversion(messagesArr, messages);
		}
		
	}
	
	//method takes arraylist as an input parameter and returns the converted array as an output to caller
	//public static Object[] ListToArrayConversion(ArrayList<Object> convertlist, Object[] resArr){
		//Object[] resArr = convertList.toArray(new Object[convertList.size()]);
//		return convertlist.toArray();
//	}
	
}
