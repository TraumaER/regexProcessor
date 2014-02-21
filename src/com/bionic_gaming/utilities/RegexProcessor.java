package com.bionic_gaming.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.bionic_gaming.utilities.obj.RegexResult;
import com.bionic_gaming.utilities.obj.RegexResultPair;

/**
 * <p>
 * RegexProcessor is a class that handles most Regular Expression operations
 * that a user may need. Setting the regular expression allows the user to run
 * that expression against as many strings as they would like. Finding in either
 * individual strings or files a like. This can be useful if analysis is needed
 * on a file to find how many lines contain the certain expression or if you
 * want to know the line number and substring position of each match.
 * </p>
 * <p>
 * This utility is provided as is and will have improvements made where needed.
 * </p>
 * <p>
 * Please report any bugs you may find.
 * </p>
 * 
 * @author Adam Bannach
 * @version 0.1a 01/26/2014
 */
public class RegexProcessor {

	private String expression;
	private Pattern regexPattern;

	public RegexProcessor(String expression) {
		this.expression = expression;
		regexPattern = Pattern.compile(expression);
	}

	/**
	 * Returns the first match of the current regular expression in a
	 * RegexResult object.
	 * 
	 * @param stringToSearch
	 * @return RegexResult of first match in stringToSearch
	 * @throws Exception
	 */
	public RegexResult findInString(String stringToSearch) throws Exception {
		if (expression == null) {
			throw new Exception("Regular Expression not set on processor");
		}

		Matcher m = regexPattern.matcher(stringToSearch);

		if (m.find()) {
			return new RegexResult(m.start(), m.end(), stringToSearch);
		}

		return null;
	}

	/**
	 * Returns a list of all matches of the current regular expression in a
	 * RegexResult list.
	 * 
	 * @param stringToSearch
	 * @return RegexResult list of all matches in stringToSearch
	 * @throws Exception
	 */
	public List<RegexResult> findAllInString(String stringToSearch)
			throws Exception {
		if (expression == null) {
			throw new Exception("Regular Expression not set on processor");
		}

		List<RegexResult> resultList = new ArrayList<RegexResult>();

		Matcher m = regexPattern.matcher(stringToSearch);

		while (m.find()) {
			resultList.add(new RegexResult(m.start(), m.end(), stringToSearch));
		}

		return resultList;

	}

	/**
	 * Returns a list of RegexResultPair (line number, regexResult) for the
	 * first match on each line in a file.
	 * 
	 * @param fileToSearch
	 *            - full path to file to search
	 * @return RegexResultPair list
	 * @throws Exception
	 */
	public List<RegexResultPair> findInFile(String fileToSearch)
			throws Exception {

		List<RegexResultPair> resultList = new ArrayList<RegexResultPair>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileToSearch));

			String line;
			Long lineNumber = 0L;

			while ((line = br.readLine()) != null) {

				RegexResult currentRegexResult = findInString(line);

				if (currentRegexResult != null) {
					resultList.add(new RegexResultPair(lineNumber,
							currentRegexResult));
				}

				lineNumber++;
			}
			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultList;
	}

	/**
	 * Returns a list of RegexResultPair (line number, regexResult) for every
	 * match on each line in a file.
	 * 
	 * @param fileToSearch
	 *            - full path to file to search
	 * @return RegexResultPair list
	 * @throws Exception
	 */
	public List<RegexResultPair> findAllInFile(String fileToSearch)
			throws Exception {

		List<RegexResultPair> resultList = new ArrayList<RegexResultPair>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileToSearch));

			String line;
			Long lineNumber = 0L;

			while ((line = br.readLine()) != null) {

				List<RegexResult> currentRegexResultList = findAllInString(line);

				if (!currentRegexResultList.isEmpty()) {
					for (RegexResult r : currentRegexResultList) {
						resultList.add(new RegexResultPair(lineNumber, r));
					}
				}

				lineNumber++;
			}
			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultList;
	}

	/**
	 * Returns a list of RegexResultPair lists of the first match on each line
	 * in the files to search.
	 * 
	 * @param filesToSearch
	 *            - list of full file path names to search
	 * @return List of RegexResultPair lists; one per file even if it's empty.
	 * @throws Exception
	 */
	public List<List<RegexResultPair>> findInFiles(List<String> filesToSearch)
			throws Exception {

		List<List<RegexResultPair>> resultList = new ArrayList<List<RegexResultPair>>();

		for (String file : filesToSearch) {
			resultList.add(findInFile(file));
		}

		return resultList;
	}

	/**
	 * Returns a list of RegexResultPair lists of all matches on each line in
	 * the files to search.
	 * 
	 * @param filesToSearch
	 *            - list of full file path names to search
	 * @return List of RegexResultPair lists; one per file even if it's empty.
	 * @throws Exception
	 */
	public List<List<RegexResultPair>> findAllInFiles(List<String> filesToSearch)
			throws Exception {

		List<List<RegexResultPair>> resultList = new ArrayList<List<RegexResultPair>>();

		for (String file : filesToSearch) {
			resultList.add(findAllInFile(file));
		}

		return resultList;
	}

	/**
	 * Returns a map with filename as the key and list of the first match per
	 * line as RegexResultPairs as the value.
	 * 
	 * @param directory
	 *            - Directory to search.
	 * @param extensions
	 *            - List of file extensions to search in.
	 * @param isRecursive
	 *            - Boolean whether or not to Search in subDirectories
	 * @return Map<String, List<RegexResultPair>> resultMap
	 * @throws Exception
	 */
	public Map<String, List<RegexResultPair>> findInDirectoryWithExtensions(
			String directory, List<String> extensions, Boolean isRecursive)
			throws Exception {

		Map<String, List<RegexResultPair>> resultMap = new HashMap<String, List<RegexResultPair>>();

		String[] exts = extensions.toArray(new String[extensions.size()]);
		File dir = new File(directory);

		Collection<File> fileList = FileUtils.listFiles(dir, exts, isRecursive);
		List<File> directoryFiles = new ArrayList<File>(fileList);

		for (File f : directoryFiles) {
			resultMap.put(f.getCanonicalPath(), findInFile(f.toString()));
		}

		return resultMap;
	}

	/**
	 * Returns a map with filename as the key and list of all matches as
	 * RegexResultPairs as the value.
	 * 
	 * @param directory
	 *            - Directory to search.
	 * @param extensions
	 *            - List of file extensions to search in.
	 * @param isRecursive
	 *            - Boolean whether or not to Search in subDirectories
	 * @return Map<String, List<RegexResultPair>> resultMap
	 * @throws Exception
	 */
	public Map<String, List<RegexResultPair>> findAllInDirectoryWithExtensions(
			String directory, List<String> extensions, Boolean isRecursive)
			throws Exception {

		Map<String, List<RegexResultPair>> resultMap = new HashMap<String, List<RegexResultPair>>();

		String[] exts = extensions.toArray(new String[extensions.size()]);
		File dir = new File(directory);

		Collection<File> fileList = FileUtils.listFiles(dir, exts, isRecursive);
		List<File> directoryFiles = new ArrayList<File>(fileList);

		for (File f : directoryFiles) {
			resultMap.put(f.getCanonicalPath(), findAllInFile(f.toString()));
		}

		return resultMap;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
		regexPattern = Pattern.compile(expression);
	}

}
