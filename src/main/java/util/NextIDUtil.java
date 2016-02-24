package util;

public class NextIDUtil {

	private static int nextIDUser;
	private static int nextIDGenre;
	private static int nextIDLink;

	/** Get number of next ID for new user */
	public static int getNextIDUser() {
		return nextIDUser;
	}

	/** Set number of next ID for new user */
	public static void setNextIDUser(int id) {
		nextIDUser = id;
	}

	/** Get number of next ID for new genre */
	public static int getNextIDGenre() {
		return nextIDGenre;
	}

	/** Set number of next ID for new genre */
	public static void setNextIDGenre(int id) {
		nextIDGenre = id;
	}

	/** Get number of next ID for new link */
	public static int getNextIDLink() {
		return nextIDLink;
	}
	
	/** Set number of next ID for new link */
	public static void setNextIDLink(int id) {
		nextIDLink = id;
	}
}
