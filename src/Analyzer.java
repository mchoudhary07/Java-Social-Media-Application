import java.util.*;

//Analyzer class
public class Analyzer {
	private Profile[] profiles = new Profile[100]; // Array of Profile class
													// with length 100
	private int count;//dfdfsdfds

	// Static Array List to store the instances for Profile class
	static ArrayList<Profile> profilesArr = new ArrayList<Profile>();//fdhfdshffskjf

	// Parse method which would take the strings from array and analyze them and
	// finally store them to concerned arrays
	public void parse(ArrayList<String> fileLines) throws DataParseException {

		this.setCount(0);//dfdfsdfsd
		// for loop to go through each and every line of the lines array
		for (int i = 0; i < fileLines.size(); i++) {
			// if checks for the string i.e. if it starts with PROFILE keyword
			if (fileLines.get(i).startsWith("PROFILE")) {
				// uname and gender stores the data extracted from string for
				// username and gender
				String uname = fileLines.get(i).split(" ")[1];
				String gender = fileLines.get(i).split(" ")[2];
				// Create a new object of Profile class and using data fields
				// uname, gender
				Profile addProfile = new Profile(uname, gender);
				addProfile.parseDataDump(fileLines);
				// gets the size of profiles array list and initializes to count
				// checks if count >=100 then goto checkpoint first: above
				profiles[this.getCount()] = addProfile;
				this.setCount(this.getCount() + 1);
			}
		}
	}

	// Getter method to get the profile data
	public Profile[] getProfiles() {///fdkfhdskfhdksfkd
		return profiles;
	}

	// Setter method which sets the profile data
	public void setProfiles(Profile[] profiles) {//dfhdhfjdshfdj
		this.profiles = profiles;
	}

	// Get method to return the count of profiles
	public int getCount() {
		return count;
	}

	// Set method to set the count
	public void setCount(int count) {
		this.count = count;
	}

	// Method used to analyze profiles based on the keywords used in the
	// messages and posts
	public List<String> analyzeProfiles() {
		// New arraylist object to store data of negative profiles
		ArrayList<Profile> negativeProfileList = new ArrayList<Profile>();
		ArrayList<String> negativeProfileUsernameList = new ArrayList<String>();
		// for to loop through all the profiles
		for (int b = 0; b < this.getCount(); b++) {
			Profile p = profiles[b];
			boolean isNegativeProfile = false;
			for (Object m : p.getMessages()) {
				Message message = (Message) Object.class.cast(m);
				String body = message.getBody();
				ArrayList<Message> negativeMessages = new ArrayList<Message>();
				ArrayList<String> negKeywords = new ArrayList<String>();
				negKeywords = Message.getNegativeKeywords(body);
				if (negKeywords != null) {
					negativeMessages.add(message);
					isNegativeProfile = true;
				}
			}
			if (isNegativeProfile) {
				negativeProfileList.add(p);
				negativeProfileUsernameList.add(p.getUsername());
			}

		}
		// Returns the list of negative profiles
		return negativeProfileUsernameList;
	}
}
