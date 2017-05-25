//Photo class declaration
public class Photo {
	private int size;			//size stores the size of photo
	private String name;		//stores the name of photo
	private String location;	//stores the location of photo
	
	//Parameterized constructor to set the photo details
	public Photo(String name,int size, String location) {
		super();
		this.size = size;
		this.name = name;
		this.location = location;
	}

	//get method to get size of photo
	public int getSize() {
		return size;
	}

	//set method to set the size of photo object
	public void setSize(int size) {
		this.size = size;
	}

	//get method to get the name of the photo object
	public String getName() {
		return name;
	}

	//set method to set the name of photo object
	public void setName(String name) {
		this.name = name;
	}

	//get method to get location of photo object
	public String getLocation() {
		return location;
	}

	//set method to set the location of photo object
	public void setLocation(String location) {
		this.location = location;
	}

	//equals method compares new photo object with existing objects and returns true if match found
    @Override
	public boolean equals(Object o){
    	try{
    		if (o instanceof Post){
    			o = ((Post) o).getPhoto();
    		}
    		Photo comparePhoto = (Photo) Object.class.cast(o);
        	if((this.getName()).equals(comparePhoto.getName())){
        		if ((this.getLocation()).equals(comparePhoto.getLocation())){
        			if (this.getSize() == comparePhoto.getSize()){
        				return true;
        			}
        		}
        	}
    	}catch (Exception e){
    		return false;
    	}
    	
		return false;
	}
	
}
