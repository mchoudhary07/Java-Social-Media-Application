import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Message class declaration
public class Message {
	private String contact; //stores the contact details of message
	private String date; // stores the date of message
	private String body; // stores the body i.e. content of message
	// Negative and Positive words stores the list of word that would be
	// determine the negative profile objects
	public static final String[] NEGATIVE_WORDS = { "terrible", "strange", "mad", "furious", "aggressive", "aloof",
			"arrogant", "belligerent", "big-headed", "bitchy", "boastful", "bone-idle", "boring", "bossy", "callous",
			"cantankerous", "careless", "changeable", "clinging", "compulsive", "conservative", "cowardly", "cruel",
			"cunning", "cynical", "deceitful", "detached", "dishonest", "dogmatic", "domineering", "finicky", "foolish",
			"foolhardy", "fussy", "greedy", "grumpy", "gullible", "harsh", "impatient", "impolite", "impulsive",
			"inconsiderate", "inconsistent", "indecisive", "indiscreet", "inflexible", "interfering", "intolerant",
			"irresponsible", "jealous", "lazy", "Machiavellian", "materialistic", "mean", "miserly", "moody",
			"narrow-minded", "nasty", "naughty", "nervous", "obsessive", "obstinate", "overcritical", "overemotional",
			"parsimonious", "patronizing", "perverse", "pessimistic", "pompous", "possessive", "pusillanimous",
			"quarrelsome", "quick-tempered", "resentful", "rude", "ruthless", "sarcastic", "secretive", "selfish",
			"self-centred", "self-indulgent", "silly", "sneaky", "stingy", "stubborn", "stupid", "superficial",
			"tactless", "timid", "touchy", "thoughtless", "truculent", "unkind", "unpredictable", "unreliable",
			"untidy", "untrustworthy", "vague", "vain", "vengeful", "vulgar", "weak-willed" };
	public static final String[] POSITIVE_WORDS = { "adaptable", "adventurous", "affable", "affectionate", "agreeable",
			"ambitious", "amiable", "amicable", "amusing", "brave", "bright", "broad-minded", "calm", "careful",
			"charming", "communicative", "compassionate", "conscientious", "considerate", "convivial", "courageous",
			"courteous", "creative", "decisive", "determined", "diligent", "diplomatic", "discreet", "dynamic",
			"easygoing", "emotional", "energetic", "enthusiastic", "exuberant", "fair-minded", "faithful", "fearless",
			"forceful", "frank", "friendly", "funny", "generous", "gentle", "good", "gregarious", "hard-working",
			"helpful", "honest", "humorous", "imaginative", "impartial", "independent", "intellectual", "intelligent",
			"intuitive", "inventive", "kind", "loving", "loyal", "modest", "neat", "nice", "optimistic", "passionate",
			"patient", "persistent", "pioneering", "philosophical", "placid", "plucky", "polite", "powerful",
			"practical", "pro-active", "quick-witted", "quiet", "rational", "reliable", "reserved", "resourceful",
			"romantic", "self-confident", "self-disciplined", "sensible", "sensitive", "shy", "sincere", "sociable",
			"straightforward", "sympathetic", "thoughtful", "tidy", "tough", "unassuming", "understanding", "versatile",
			"warmhearted", "willing", "witty" };

	// Parameterized constructor to set the message details
	public Message(String contact, String date, String body) {
		super();
		this.contact = contact;
		this.date = date;
		this.body = body;
	}

	// get method to get contact details
	public String getContact() {
		return contact;
	}

	// set method to set contact details
	public void setContact(String contact) {
		this.contact = contact;
	}

	// get method to get message date
	public String getDate() {
		return date;
	}

	// set method to set message date
	public void setDate(String date) {
		this.date = date;
	}

	// get method to get the body of message
	public String getBody() {
		return body;
	}

	// set method to set the body of message
	public void setBody(String body) {
		this.body = body;
	}

	// method returns the negative words list
	public static String[] getNegativeWords() {
		return NEGATIVE_WORDS;
	}

	// method returns the positive words list
	public static String[] getPositiveWords() {
		return POSITIVE_WORDS;
	}

	public static ArrayList<String> getNegativeKeywords(String body) {

		ArrayList<String> neagtiveKeywords = new ArrayList<String>();

		for (String word : NEGATIVE_WORDS)
			if (body.contains(word))
				neagtiveKeywords.add(word);
		return neagtiveKeywords;
	}

	public static ArrayList<String> getPositiveKeywords(String body) {

		ArrayList<String> positiveKeywords = new ArrayList<String>();

		for (String word : POSITIVE_WORDS)
			if (body.contains(word))
				positiveKeywords.add(word);

		return positiveKeywords;
	}
}
