package com.bionic_gaming.utilities.obj;

/**
 * <p>
 * RegexResult is a class that contains the start position, end position, and
 * original string of a match made by RegexProcessor. This class can be used
 * separately if needed.
 * </p>
 * 
 * @author Adam Bannach
 * @version 0.1a 01/26/2014
 * 
 */
public class RegexResult {
	int start;
	int end;
	String originalString;

	/**
	 * <p>
	 * Creates an empty RegexResult object.
	 * </p>
	 */
	public RegexResult() {

	}

	/**
	 * <p>
	 * Creates a new RegexResult objec with substring start and end positions.
	 * This constructor does not store the original string so getSubstring will
	 * return nothing unless originalString is set
	 * </p>
	 * 
	 * @param start
	 *            - int
	 * @param end
	 *            - int
	 */
	public RegexResult(int start, int end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * <p>
	 * Creates a new RegexResult object with substring start and end positions.
	 * Also stores the original string the result is from
	 * </p>
	 * 
	 * @param start
	 *            - int
	 * @param end
	 *            - int
	 * @param originalString
	 *            - String
	 */
	public RegexResult(int start, int end, String originalString) {
		this.start = start;
		this.end = end;
		this.originalString = originalString;
	}

	/**
	 * Gets the start position of the substring of this RegexResult.
	 * 
	 * @return int start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * <p>
	 * Sets the start position of the substring of this RegexResult.
	 * </p>
	 * <p>
	 * <strong>--Warning--</strong> If this is manually changed you lose the
	 * result that the RegexProcessor found.
	 * </p>
	 * 
	 * @param start
	 *            - int
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * Gets the end position of the substring of this RegexResult.
	 * 
	 * @return int start
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * <p>
	 * Sets the end position of the substring of this RegexResult.
	 * </p>
	 * <p>
	 * --Warning-- If this is manually changed you lose the result that the
	 * RegexProcessor found.
	 * </p>
	 * 
	 * @param end
	 *            - int
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * <p>
	 * Gets the full string that this RegexResult was found in.
	 * </p>
	 * 
	 * @return String - originalString
	 */
	public String getOriginalString() {
		return originalString;
	}

	/**
	 * <p>
	 * Set the full string that this RegexResult was found in
	 * </p>
	 * <p>
	 * <strong>--Warning--</strong> If this is manually changed you lose the
	 * original string that this RegexResult came from, and the getSubString()
	 * method will not return the correct value.
	 * </p>
	 * 
	 * @param originalString
	 *            - String
	 */
	public void setOriginalString(String originalString) {
		this.originalString = originalString;
	}

	/**
	 * <p>
	 * Returns the substring from the originalString using the start and end
	 * positions
	 * </p>
	 * <p>
	 * <strong>--Warning--</strong> If originalString is not set this method
	 * will return an empty string.
	 * </p>
	 * 
	 * @return String - substring of originalString found by RegexProcessor.
	 */
	public String getSubString() {
		if (originalString == null
				|| originalString.trim().equalsIgnoreCase("")) {
			return "";
		} else {
			return this.originalString.substring(this.start, this.end);
		}
	}

	@Override
	public String toString() {
		return "Start Pos: " + start + "; End Pos: " + end + "; Substring: "
				+ getSubString() + "; OriginalString: " + originalString;
	}
}
