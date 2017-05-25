import static org.junit.Assert.*; 

import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.Collections; 

import org.junit.Test; 


public class UnitTests { 

    @Test 
    public void testPhoto() { 
        Photo p = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        assertEquals("kitten.jpg",p.getName()); 
        assertEquals(234,p.getSize()); 
        assertEquals("C:\\MyPhotos\\kitten.jpg",p.getLocation()); 
    } 
     
    @Test 
    public void testPost() { 
        Post p = new Post("03/23/2016","It was as sunny day today!"); 
        assertEquals("03/23/2016",p.getDate()); 
        assertEquals("It was as sunny day today!",p.getText()); 
        assertEquals(null,p.getPhoto()); 
         
        Photo photo = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        p.addPhoto(photo); 
        assertEquals(photo,p.getPhoto()); 
    }     
     
    @Test 
    public void testMessage(){ 
        Message m = new Message("Jane Smith", "03/23/2016", "How did your class go?"); 
        assertEquals("Jane Smith", m.getContact()); 
        assertEquals("03/23/2016", m.getDate()); 
        assertEquals("How did your class go?", m.getBody()); 
    } 
     
    @Test 
    public void testNegativeAnalysis(){ 
        String text = "This professor is really boring and careless. I don't like her at all."; 
        ArrayList<String> result = Message.getNegativeKeywords(text); 
        assertEquals("boring", result.get(0)); 
        assertEquals("careless", result.get(1));         
    } 
     
    @Test 
    public void testPositiveAnalysis(){ 
        String text = "This professor is really compassionate and nice. I think she's good!"; 
        ArrayList<String> result = Message.getPositiveKeywords(text); 
        assertEquals("compassionate", result.get(0)); 
        assertEquals("good", result.get(1));     
        assertEquals("nice", result.get(2));             
    }     
     
    @Test 
    public void testProfile1(){ 
        Profile p = new Profile("John Smith","male"); 
        assertEquals("John Smith", p.getUsername()); 
        assertEquals("male", p.getGender()); 
    } 
     
    @Test 
    public void testProfile2(){ 
        Profile p = new Profile("John Smith","male");         
        //String[] lines = {"http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223"}; 
        ArrayList<String> lines = new ArrayList<String>(); 
        lines.add("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223"); 
        String exceptionType = "NONE";         
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
         
        assertEquals(223, p.getPhotos().get(0).getSize()); 
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", p.getPhotos().get(0).getLocation());     
        assertEquals("headshot.jpg", p.getPhotos().get(0).getName());     
        assertEquals(1,p.getPhotos().size()); 
        assertEquals("NONE",exceptionType);         
                 
    } 
     
    // do u see it??
    
    @Test 
    public void testProfile3(){ 
        Profile p = new Profile("John Smith","male");         
        //String[] lines = {"http://www.cs.gmu.edu/~kdobolyi/headshot.jpg223"}; 
        ArrayList<String> lines = new ArrayList<String>(); 
        lines.add("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg223"); 
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
         
        assertNotEquals("NONE",exceptionType); 
    }     

    @Test 
    public void testProfile4(){ 
        Profile p = new Profile("John Smith","male");         
        //String[] lines = {"CONTACT Jane Doe"}; 
        ArrayList<String> lines = new ArrayList<String>(); 
        lines.add("CONTACT Jane Doe");     
        String exceptionType = "NONE";         
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
         
        assertEquals("Jane Doe", p.getContacts().get(0)); 
        assertEquals("NONE",exceptionType);                     
    } 
     
    @Test 
    public void testProfile5(){ 
        Profile p = new Profile("John Smith","male");                 
        //String[] lines = {"POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223"}; 
        ArrayList<String> lines = new ArrayList<String>(); 
        lines.add("POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223");             
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
     
        assertEquals("NONE",exceptionType);             
        assertEquals("12/23/15", p.getPosts().get(0).getDate()); 
        assertEquals("my message", p.getPosts().get(0).getText());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", p.getPosts().get(0).getPhoto().getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", p.getPhotos().get(0).getLocation());             

    }         
     
    @Test 
    public void testProfile6(){ 
        Profile p = new Profile("John Smith","male");                 
        //String[] lines = {"POST 12/23/15 my message http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223"}; 
        ArrayList<String> lines = new ArrayList<String>(); 
        lines.add("POST 12/23/15 my message http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223");                 
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
     
        assertNotEquals("NONE",exceptionType);                     

    } 
     
    @Test 
    public void testProfile7(){ 
        Profile p = new Profile("John Smith","male");                 
        //String[] lines = {"MESSAGE#Jane Doe#12/23/15#my message is short"}; 
        ArrayList<String> lines = new ArrayList<String>(); 
        lines.add("MESSAGE#Jane Doe#12/23/15#my message is short");                 
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
     
        assertEquals("NONE",exceptionType);             
        assertEquals("Jane Doe", p.getMessages().get(0).getContact());         
        assertEquals("12/23/15", p.getMessages().get(0).getDate()); 
        assertEquals("my message is short", p.getMessages().get(0).getBody());         
        assertEquals("Jane Doe", p.getMessages().get(0).getContact());         

    }         
     
    /*@Test 
    public void testProfile8(){ 
        Profile p = new Profile("John Smith","male");     
        Photo photo = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        p.inefficientAndDangerousAddObject(photo); 
        assertEquals(photo,(Photo)p.getPhotos()[0]); 
    } 
     
    @Test 
    public void testProfile9(){ 
        Profile p = new Profile("John Smith","male");     
        Post post = new Post("03/23/2016","It was as sunny day today!"); 
        p.inefficientAndDangerousAddObject(post); 
        assertEquals(post,(Post)p.getPosts()[0]); 
    }     
     
    @Test 
    public void testProfile10(){ 
        Profile p = new Profile("John Smith","male");     
        Message m = new Message("Jane Smith", "03/23/2016", "How did your class go?"); 
        p.inefficientAndDangerousAddObject(m); 
        assertEquals(m,(Message)p.getMessages()[0]); 
    }     
     
    @Test 
    public void testProfile11(){ 
        Profile p = new Profile("John Smith","male");     
        String contact = "Jane"; 
        p.inefficientAndDangerousAddObject(contact); 
        assertEquals(contact,(String)p.getContacts()[0]); 
    }        */ 
     
    @Test 
    public void testProfile12(){ 
        Profile p = new Profile("John Smith","male");     
        //String[] lines = {"Nothing"}; 
        ArrayList<String> lines = new ArrayList<String>(); 
        lines.add("Nothing");             
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
     
        assertEquals("class DataParseException",exceptionType);     
    }         
     
    @Test 
    public void testAnalyzer(){ 
        Analyzer a = new Analyzer(); 
         
        assertEquals(100,a.getProfiles().length); 
        //String[] lines = { 
        ArrayList<String> lines = new ArrayList<String>(); 
        lines.add("PROFILE JaneSmith female"); 
        lines.add("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223"); 
        lines.add("MESSAGE#Jane Doe#12/23/15#my message is short"); 
        lines.add("POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot2.jpg 223"); 
        lines.add("http://www.google.com/headshot.jpg 2456"); 
        lines.add("CONTACT Joe Dupont"); 
        lines.add("MESSAGE#Sam Smith#11/13/15#this is another message"); 
        lines.add("POST#12/24/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg 223"); 
                 
        lines.add("PROFILE JohnDoe male"); 
        lines.add("POST#12/24/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot4.jpg 223");             
        lines.add("http://www.cs.gmu.edu/~kdobolyi/clouds.jpg 223"); 
        lines.add("MESSAGE#Johhny Doe#12/23/15#my message is also short"); 
        lines.add("POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg 223"); 
        lines.add("MESSAGE#Sally Fields#11/13/15#this is another message to someone else"); 
        lines.add("http://www.google.com/headshot1.jpg 2456"); 
        lines.add("http://www.google.com/headshot11.jpg 2456");                     

        try { 
            a.parse(lines); 
        } catch (DataParseException e) { 
            fail("unexpected exception"); 
        } 
         
        Profile one = a.getProfiles()[0]; 
        Profile two = a.getProfiles()[1]; 
         
        assertEquals(100,a.getProfiles().length); 
        System.out.println(one); 
        assertEquals("JaneSmith", one.getUsername()); 
        assertEquals("female", one.getGender());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", one.getPhotos().get(0).getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot2.jpg", one.getPhotos().get(1).getLocation()); 
        assertEquals("http://www.google.com/headshot.jpg", one.getPhotos().get(2).getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg", one.getPhotos().get(3).getLocation()); 
        assertEquals(4,one.getPhotos().size());     
        assertEquals("my message is short", one.getMessages().get(0).getBody());             
        assertEquals("this is another message", one.getMessages().get(1).getBody());     
        assertEquals(2,one.getMessages().size());             
        assertEquals("Jane Doe", one.getContacts().get(0));             
        assertEquals("Joe Dupont", one.getContacts().get(1));             
        assertEquals("Sam Smith", one.getContacts().get(2));         
        assertEquals("12/23/15", one.getPosts().get(0).getDate());     
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot2.jpg", one.getPosts().get(0).getPhoto().getLocation());     
        assertEquals("12/24/15", one.getPosts().get(1).getDate());     
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg", one.getPosts().get(1).getPhoto().getLocation());     
         
        System.out.println(two); 
        assertEquals("JohnDoe", two.getUsername()); 
        assertEquals("male", two.getGender());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot4.jpg", two.getPhotos().get(0).getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/clouds.jpg", two.getPhotos().get(1).getLocation()); 
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg", two.getPhotos().get(2).getLocation());         
        assertEquals("http://www.google.com/headshot1.jpg", two.getPhotos().get(3).getLocation()); 
        assertEquals("http://www.google.com/headshot11.jpg", two.getPhotos().get(4).getLocation());         
        assertEquals(5,two.getPhotos().size());     
        assertEquals("my message is also short", two.getMessages().get(0).getBody());             
        assertEquals("this is another message to someone else", two.getMessages().get(1).getBody());     
        assertEquals(2,one.getMessages().size());             
        assertEquals("Johhny Doe", two.getContacts().get(0));             
        assertEquals("Sally Fields", two.getContacts().get(1));     
        assertEquals("12/24/15", two.getPosts().get(0).getDate());     
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot4.jpg", two.getPosts().get(0).getPhoto().getLocation());     
        assertEquals("12/23/15", two.getPosts().get(1).getDate());     
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg", two.getPosts().get(1).getPhoto().getLocation());         
    } 
     
    @Test 
    public void testEquals1(){ 
        Photo p1 = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        Photo p2 = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
         
        assertEquals(true,p1.equals(p2)); 
        p2.setSize(100); 
        assertEquals(false, p1.equals(p2)); 
    } 
     
    @Test 
    public void testEquals2(){ 
        Photo p1 = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
         
        assertEquals(false,p1.equals(null)); 
    }     
     
    @Test 
    public void testEquals3(){ 
        Photo p1 = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        Photo p2 = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        Post p = new Post("03/23/2016","It was as sunny day today!"); 
        p.addPhoto(p2); 
        assertEquals(true,p1.equals(p2)); 
        p2.setSize(100); 
        assertEquals(false, p2.equals(p1)); 
    } 
     
    @Test 
    public void testEquals4(){ 
        Photo p1 = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        Message m = new Message("Jane Smith", "03/23/2016", "How did your class go?"); 
         
        assertEquals(false, p1.equals(m)); 
    }     
     
    @Test 
    public void testSort(){ 
        Photo p1 = new Photo("kitten.jpg", 235, "C:\\MyPhotos\\kitten.jpg"); 
        Photo p2 = new Photo("kitten.jpg", 231, "C:\\MyPhotos\\kitten.jpg");         
        Photo p3 = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        Photo p4 = new Photo("kitten.jpg", 233, "C:\\MyPhotos\\kitten.jpg"); 
         
        ArrayList<Photo> list = new ArrayList<Photo>(); 
        list.add(p1); 
        list.add(p2); 
        list.add(p3); 
        list.add(p4);         
         
        Collections.sort(list); 
        assertEquals(p2,list.get(0)); 
        assertEquals(p4,list.get(1)); 
        assertEquals(p3,list.get(2)); 
        assertEquals(p1,list.get(3));         
    } 
    public static StringBuffer systemCall(String cmd){ 
        StringBuffer result = new StringBuffer(); 
        try 
        { 
            System.err.println("doing "+ cmd); 
            Runtime run = Runtime.getRuntime(); 
            Process pr = run.exec(cmd); 
            pr.waitFor(); 
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getErrorStream())); 
            String line = null; 
             
            while ((line=buf.readLine())!=null)  
                result.append(line+"\n"); 
            buf.close(); 
            buf = new BufferedReader(new InputStreamReader(pr.getInputStream())); 
            while ((line=buf.readLine())!=null)  
                result.append(line+"\n");     
            buf.close(); 
            pr.getOutputStream().close(); 
            pr.destroy(); 
        } 
        catch (Exception e) 
        { 
            StackTraceElement[] elts = e.getStackTrace(); 
            for(int i=0; i < elts.length; i++) 
                result.append(elts[i].toString()+"\n"); 
        } 
        return result; 
    }         
     
    @Test 
    public void testStyle(){ 
        assertEquals("passed", checkPrivacy("Analyzer.java", "Profile[]", "profiles", "Analyzer: All appropriate attributes are private and commented."));
        assertEquals("passed", checkPrivacy("Analyzer.java", "int", "count", "Analyzer: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Message.java", "String", "contact", "Message: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Message.java", "String", "date", "Message: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Message.java", "String", "body", "Message: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Photo.java", "int", "size", "Photo: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Photo.java", "String", "name", "Photo: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Photo.java", "String", "location", "Photo: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Post.java", "String", "text", "Post: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Post.java", "String", "date", "Post: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Post.java", "Photo", "photo", "Post: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Profile.java", "ArrayList<Photo>", "photos", "Profile: All appropriate attributes are private and commented, and of correct type."));
        assertEquals("passed", checkPrivacy("Profile.java", "ArrayList<String>", "contacts", "Profile: All appropriate attributes are private and commented, and of correct type."));
        assertEquals("passed", checkPrivacy("Profile.java", "ArrayList<Post>", "posts", "Profile: All appropriate attributes are private and commented, and of correct type."));
        assertEquals("passed", checkPrivacy("Profile.java", "ArrayList<Message>", "messages", "Profile: All appropriate attributes are private and commented, and of correct type."));
    } 
     
    @Test 
    public void testReuse(){ 
        assertEquals("passed", checkReuse("Profile.java", "private","boolean", "minePhoto","minePost","Profile: The minePhoto method is called in minePost, rather than rewriting this code.", true));
        assertEquals("passed", checkReuse("Profile.java", "private","boolean", "mineContact","mineMessage","Profile: The mineContact method is called in mineMessage, rather than rewriting this code.", true));
//        assertEquals("passed", checkUse("Profile.java", "equals","inefficientAndDangerousAddObject","Profile: The equals method is called in inefficientAndDangerousAddObject."));

        assertEquals("passed", checkReuse("Analyzer.java", "public", "void", "parseDataDump","parse","Analyzer: The parseDataDump method is called, rather than rewriting this code.", true));
        assertEquals("passed", checkReuse("Analyzer.java", "public", "void","mineContact","parse","Analyzer: The parseDataDump method is called, rather than rewriting this code.", false));
        assertEquals("passed", checkReuse("Analyzer.java", "public", "void","minePost","parse","Analyzer: The parseDataDump method is called, rather than rewriting this code.", false));
        assertEquals("passed", checkReuse("Analyzer.java", "public", "void","mineMessage","parse","Analyzer: The parseDataDump method is called, rather than rewriting this code.", false));
        assertEquals("passed", checkReuse("Analyzer.java", "public", "void","minePhoto","parse","Analyzer: The parseDataDump method is called, rather than rewriting this code.", false));    
    } 
     
    @Test 
    public void testEquals(){ 
        Photo p1 = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        Photo p2 = new Photo(null, -1, null); 
         
        assertEquals(false, p1.equals(p2)); 
        assertEquals(false, p1.equals("kitten")); 
        assertEquals(false, p1.equals(null)); 
        Photo p = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        assertEquals(true, p1.equals(p));         
        p = new Photo("kitten1.jpg", 234, "C:\\MyPhotos\\kitten1.jpg");         
        assertEquals(false, p1.equals(p));         
         
        Post post = new Post("03/23/2016","It was as sunny day today!"); 
        assertEquals(false, p1.equals(post));         
        post.addPhoto(p1); 
        assertEquals(true, p1.equals(post));     
    } 
     
    @Test 
    public void testCompareTo(){ 
        Comparable p1 = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        Comparable p2 = new Photo(null, -1, null); 
         
        assertEquals(1, p1.compareTo(p2)); 
        assertEquals(1, p1.compareTo("kitten")); 
        assertEquals(1, p1.compareTo(null)); 
        Photo p = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        assertEquals(0, p1.compareTo(p));         
        p = new Photo("kitten1.jpg", 235, "C:\\MyPhotos\\kitten1.jpg");         
        assertEquals(-1, p1.compareTo(p));             
         
        Post post = new Post("03/23/2016","It was as sunny day today!"); 
        assertEquals(1, p1.compareTo(post));         
        post.addPhoto((Photo)p1); 
        assertEquals(0, p1.compareTo(post));             
    }     
     
    @Test 
    public void testIterator(){ 
        assertEquals("passed", checkIterator("Profile.java","hasNext","parseDataDump","Analyzer: The parseDataDump method does not use an Iterator."));
                 
    } 
    @Test 
    public void testGenerics(){ 
        assertEquals("passed", checkUse("Profile.java","inefficientAndDangerousAddObject","minePhoto","Analyzer: The parseDataDump method is called, rather than rewriting this code."));
        assertEquals("passed", checkUse("Profile.java","inefficientAndDangerousAddObject","mineContact","Analyzer: The parseDataDump method is called, rather than rewriting this code."));
        assertEquals("passed", checkUse("Profile.java","inefficientAndDangerousAddObject","minePost","Analyzer: The parseDataDump method is called, rather than rewriting this code."));
        assertEquals("passed", checkUse("Profile.java","inefficientAndDangerousAddObject","mineMessage","Analyzer: The parseDataDump method is called, rather than rewriting this code."));
    } 
     
    private String checkIterator(String className, String method1, String method2, String errorMsg){ 
        ArrayList<String> body = pullMethodBody(className, "(\\s*)public(\\s+)void(\\s+)"+method2+"(\\s*)\\(.*\\)(\\s*)(.*)"); 
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%OOOOOO%%%%%%%%%%%%%%%%%"+body.size()); 
        for (int i = 0; i < body.size() ; i++){ 
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%OOOOOO%%%%%%%%%%%%%%%%%"+body.get(i)); 
            if (body.get(i).matches(".*"+method1+"\\((.*)")){ 
                return "passed"; 
            } 
        } 
        return errorMsg;         
    } 
     
    @Test 
    public void testInheritance(){ 

        assertEquals("passed", checkOverride("Photo.java", "compareTo", "Photo: The @Override tag is used in all the appropriate places.")); 
        assertEquals("passed", checkOverride("Photo.java", "equals", "Photo: The @Override tag is used in all the appropriate places.")); 
//        assertEquals("passed", checkOverride("Profile.java", "toString", "Profile: The @Override tag is used in all the appropriate places.")); 
     
    } 
     
    private String checkUse(String className, String method1, String method2, String errorMsg){ 
        ArrayList<String> body = pullMethodBody(className, "(\\s*)private(\\s+)boolean(\\s+)"+method2+"(\\s*)\\(.*\\)(\\s*)\\{(.*)"); 
        for (int i = 0; i < body.size() ; i++){ 
            //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+body.get(i)); 
            if (body.get(i).matches("(\\s*)"+method1+"\\((.*);")){ 
                return errorMsg; 
            } 
        } 
        return "passed";         
    }     
     
    private String checkReuse(String className, String visibility, String returnType, String method1, String method2, String errorMsg, boolean flag){ 
        ArrayList<String> body = pullMethodBody(className, "(\\s*)"+visibility+"(\\s+)"+returnType+"(\\s+)"+method2+"(\\s*)\\(.*\\)(.*)\\{(.*)"); 
        boolean found = false; 
        for (int i = 0; i < body.size() ; i++){ 
            //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+body.get(i)); 
            if (body.get(i).matches(".*"+method1+"\\(.*")){ 
                //System.out.println("%%%%%%%%%%%%%%MATCHED%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+body.get(i));
                found = true; 
                if (flag) 
                    return "passed"; 
            } 
        } 
         
        if (!flag) 
            if (!found) 
                return "passed"; 
        return errorMsg;         
    } 
     
    private String checkSuperCtor(String className, String errorMsg){ 
        ArrayList<String> body = pullMethodBody(className+".java", "(\\s*)public(\\s*)"+className+"(\\s*)\\([a-zA-Z0-9 ,\\[\\]]*\\)(\\s*)\\{(.*)"); 
        for (int i = 0; i < body.size() ; i++){ 
            System.out.println(body.get(i)); 
            if (body.get(i).matches("(\\s*)super\\((.*);")) 
                return "passed"; 
        } 
        return errorMsg;         
    }     
     
    private String checkOverride(String filename, String method, String errorMsg){ 
        String line = null; 
        try{ 
            FileReader fReader = new FileReader( filename); 
            BufferedReader bReader = new BufferedReader(fReader); 

            while((line = bReader.readLine()) != null){ 
                line = line.trim(); 
                if(!line.isEmpty()){ 
                    if (line.contains("@Override")){ 
                        while((line = bReader.readLine()).trim().isEmpty()){ 
                            //spin until next line found 
                        } 
                        if (line.contains("public")) 
                            return "passed"; 
                    } 
                } 
            } 
            bReader.close(); 
        }catch (Exception e){  
            return "NOT MET: Couldn't open "+ filename; 
        } 
        return errorMsg;             
    } 
     
    private String checkPrivacy(String filename, String type, String attribute, String errorMsg){ 
        String line = null; 
        try{ 
            FileReader fReader = new FileReader(filename); 
            BufferedReader bReader = new BufferedReader(fReader); 

            while((line = bReader.readLine()) != null){ 
                //System.out.println("(line.contains (//) || line.contains(/*))" + (line.contains ("//") || line.contains("/*"))); 
                line = line.trim(); 
                if(!line.isEmpty()) 
                    if (line.contains("private") && line.contains(type) &&  
                            line.contains(attribute) && line.contains(";") && 
                            (line.contains ("//") || line.contains("/*"))) 
                            return "passed"; 
            } 
            bReader.close(); 
        }catch (Exception e){  
            return "NOT MET: Couldn't open "+ filename; 
        } 
        return errorMsg; 
    }     
     
    private String checkPropagate(String filename, String errorMsg){ 
        String line = null; 
        try{ 
            FileReader fReader = new FileReader(filename); 
            BufferedReader bReader = new BufferedReader(fReader); 

            while((line = bReader.readLine()) != null){ 
                line = line.trim(); 
                if(!line.isEmpty()){ 
                    if (line.contains("instanceof") || line.contains(".getClass().equals") || line.contains(".getClass() =")) 
                        return errorMsg; 
                } 
            } 
            bReader.close(); 
        }catch (Exception e){  
            return "NOT MET: Couldn't open WhiteBloodCell.java"; 
        } 
        return "passed";             
    } 
     
    private ArrayList<String> pullMethodBody(String filename, String methodRegex){ 
        ArrayList<String> body = new ArrayList<String>(); 
        String line = null; 
        int brackets = 0; 
        boolean found = false; 
        int bodyBrackets = 0; 
        try{ 
            FileReader fReader = new FileReader(filename); 
            BufferedReader bReader = new BufferedReader(fReader); 

            while((line = bReader.readLine()) != null){ 
                System.out.println(line); 
                line = line.trim(); 
                if(!line.isEmpty()){ 
                    //System.out.println("SEARCHING METHOD*************"); 
                    if (line.matches(methodRegex) && line.contains("{")){ 
                        System.out.println("FOUND METHOD*************"); 
                        bodyBrackets = brackets;                         
                        brackets++; 
                        found = true; 
                    } 
                    else if (line.contains("{")) 
                        brackets++; 
                    if (line.contains("}")) 
                        brackets--;         
                     
                    if (found){ 
                        body.add(line); 
                        //System.out.println("BRACKETS************* " + brackets); 
                        //System.out.println("BODYBRACKETS************* " + bodyBrackets); 
                    }                     
                     
                    if (found && brackets == bodyBrackets ) 
                        return body; 
                } 
            } 
            bReader.close(); 
        }catch (Exception e){  
            return null; 
        } 
        return null;             
    }         
     
}  