package com.bionic_gaming.utilities.obj;

/**
 * <p>
 * RegexResultPair is a class that is used by RegexProcessor. It contains a line
 * number and a regexResult. This class is used in RegexProcessor when
 * processing files.
 * </p>
 * 
 * @author Adam
 * @version 0.1a 01/26/2014
 */
public class RegexResultPair {
	Long lineNumber;
	RegexResult regexResult;

	/**
	 * <p>
	 * Creates a new RegexResultPair and sets the line number and RegexResult
	 * for this pair.
	 * </p>
	 * 
	 * @param lineNumber
	 *            - Long
	 * @param regexResult
	 *            - RegexResult
	 */
	public RegexResultPair(Long lineNumber, RegexResult regexResult) {
		this.lineNumber = lineNumber;
		this.regexResult = regexResult;
	}

	/**
	 * <p>
	 * Gets the lineNumber for this RegexResultPair.
	 * </p>
	 * 
	 * @return Long
	 */
	public Long getLineNumber() {
		return lineNumber;
	}

	/**
	 * <p>
	 * Sets the lineNumber for this RegexResultPair.
	 * </p>
	 * <p>
	 * <strong>--Warning--</strong> If this is set manually you lose the
	 * lineNumber that was set by the RegexProcessor
	 * </p>
	 * 
	 * @param lineNumber
	 *            - Long
	 */
	public void setLineNumber(Long lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * <p>
	 * Gets the RegexResult for this RegexResultPair.
	 * </p>
	 * 
	 * @return RegexResult
	 */
	public RegexResult getRegexResult() {
		return regexResult;
	}

	/**
	 * <p>
	 * Sets the RegexResult for this RegexResultPair.
	 * </p>
	 * <p>
	 * <strong>--Warning--</strong> If this is set manually you lose the
	 * RegexResult that was set by the RegexProcessor.
	 * </p>
	 * 
	 * @param regexResult
	 *            - RegexResult
	 */
	public void setRegexResult(RegexResult regexResult) {
		this.regexResult = regexResult;
	}

	@Override
	public String toString() {
		return "Line Number: " + lineNumber + "; " + regexResult.toString();
	}
}
