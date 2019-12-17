package com.tests.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class CSVReader {
	
	static final Logger logger = Logger.getLogger(CSVReader.class);
	
	private static Map<String, List<String>> contentCache = new HashMap<>();
	
	private static final String DATE_TYPE_START_WITH="{{{";
	private static final String DATE_TYPE_END_WITH="}}}";
	
	//private static final DateParser DATE_PARSER = new DateParser();
	
	private static final String IS_DATE_TYPE_PATTERN="(\\{{3})(.*)(\\}{3})";
	
	private static final String ID_COLUMN = "Key";
	
	private CSVReader(){
		
	}
	
	/**
	 * Read the whole content of given csv file 
	 * 
	 * @param fileName: String
	 * @return The whole content of given csv file
	 * @throws KronosCoreCommonException
	 * 		: customized kronos core common exception
	 */
	public static List<Map<String, String>> readCSV(String fileName) {
		
		int csvRowCount = 0;
		try {
			// total row count without header
			csvRowCount = readLines(fileName).size() - 1;
		} catch (Exception e) {
			String message = "Exception occured when read csv file: " + fileName;
			logger.error(message, e);
			
		}
		
		return readCSV(fileName, 1, csvRowCount);
	}
	
	/**
	 * Check if it is a date pattern 
	 * 
	 * @param columnValue: String
	 * @return true or false
	 */
	private static boolean isDatePattern(String columnValue) {

		int from = columnValue.indexOf(DATE_TYPE_START_WITH);
		int to = columnValue.indexOf(DATE_TYPE_END_WITH);

		return (from >= 0) && (to > from);
	}
	
	/**
	 * Get the inner date expression
	 * 
	 * @param columnValue: String
	 * @return the expression in String
	 */
	private static String getInnnerDateExpresssion(String columnValue) {

		Pattern datePattern = Pattern.compile(IS_DATE_TYPE_PATTERN);

		Matcher matcher = datePattern.matcher(columnValue);

		matcher.find();

		return matcher.group(2).replaceAll(" ", "");

	}
	
	/**
	 * Read lines from relative path
	 * 
	 * @param relativePath file name
	 * @return List of lines
	 */
	private static List<String> readLines(String relativePath) {
		if (contentCache.containsKey(relativePath)) {
			return contentCache.get(relativePath);
		}
		
		BufferedReader reader = null;
		String line = null;
		List<String> lines = new ArrayList<>();
		
		if ("".equals(relativePath)) {
			String message = "relativePath can't be empty.";
			logger.info(message);
		}
		
		try {
			reader = new BufferedReader(new InputStreamReader(CSVReader.class.getClassLoader().getResourceAsStream(relativePath)));
			
			while ((line =  reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();
		} catch (Exception e) {
			logger.error("IOException occured when get absolute path of " + relativePath, e);
			//throw new KronosCoreCommonException("IOException occured when get absolute path of " + relativePath, e);
		} 
		contentCache.put(relativePath, lines);
		return lines;
	}
	
	/**
	 * Read the line content from startRow to endRow
	 * 
	 * @param fileName: String
	 * @param startRow: int
	 * @param endRow: int
	 * @return The line content from startRow to endRow
	 * @throws KronosCoreCommonException
	 * 		: customized kronos core common exception
	 */
	public static List<Map<String, String>> readCSV(String fileName, int startRow, int endRow) //throws KronosCoreCommonException 
	{
		List<Map<String, String>> content = new ArrayList<>();
		
		if(startRow < 0 || endRow < 0 || (startRow > endRow)) {
			String message = "Parameter error, startRow is: " + startRow + ", endRow is:" + endRow;
			logger.error(message);
			//throw new KronosCoreCommonException(message);
		}
		
		for(int i = startRow; i <= endRow; i++) {
			content.add(readCSV(fileName, i));
		}
		
		return content;
	}
		
	/**
	 * Read the single line map of given csv file by given row number
	 * 
	 * @param fileName: String
	 * @param row row number of csv file, start from 1
	 * @return Map which its keyset contains headers and valueset contains given row's values.
	 * @throws KronosCoreCommonException
	 * 		: customized kronos core common exception
	 */
	public static Map<String, String> readCSV(String fileName, int row) //throws KronosCoreCommonException 
	{
		Map<String, String> lineMap = null;
		String line = null;
		
		if(row < 0) {
			String message = "Row can not be negative, row: " + row;
			logger.error(message);
			//throw new KronosCoreCommonException(message);
		}
		
		List<String> lines = readLines(fileName);
		
		String[] header = lines.get(0).split(",", -1);
		
		if(row >= lines.size()) {
			String message = "Only " + lines.size() + " in csv file: " + fileName + ", but finding row: " + row;
			logger.error(message);
			//throw new KronosCoreCommonException(message);
		}
		line = lines.get(row);
		if(line == null) {
			String message = "Row: " + row + " does not exist in csv file: " + fileName;
			logger.error(message);
			//throw new KronosCoreCommonException(message);
		}
		
		String[] columns = line.split(",", -1);
		if (header.length != columns.length) {
			String message = "CSV file: " + fileName + " is not valid, header column equals "
					+ header.length + ", row column equals " + columns.length + " on row: " + row;
			logger.error(message);
			//throw new KronosCoreCommonException(message);
		}
		
		lineMap = new LinkedHashMap<>();
		for(int i = 0; i < header.length; i++) {
			String value = replaceTwoVerticalLineToComma(columns[i]);
			/*
			if (isDatePattern(value)) {
				String innerDateExp = getInnnerDateExpresssion(value);
				lineMap.put(header[i], value.replace(DATE_TYPE_START_WITH + innerDateExp + DATE_TYPE_END_WITH , DATE_PARSER.parseDatePattern(innerDateExp)));
			}*/ //else {
				lineMap.put(header[i], value);
			//}
		}
		
		return lineMap;
	}
	
	/**
	 * Read the single line map of given csv file by given row number
	 * 
	 * @param fileName: String
	 * @param row row number of csv file, start from 1
	 * @return Map which its keyset contains headers and valueset contains given row's values.
	 * @throws KronosCoreCommonException
	 * 		: customized kronos core common exception
	 */
	public static Map<String, Object> readPayloadTemplate(String fileName, int row) //throws KronosCoreCommonException 
	{
		return convertMap( readCSV(fileName,row));
	}
	
	/**
	 * Read single line map of csv file by given id value.
	 * 
	 * @param fileName csv file name.
	 * @param idValue value to be find in "Key" column.
	 * @return Map<String, String> of the row content, <b>null</b> if not found.
	 * @throws KronosCoreCommonException Core Common Exception
	 */
	public static Map<String, String> readCSV(String fileName, String idValue) //throws KronosCoreCommonException 
	{ 
		String errMsg = "";

		if (fileName == null || idValue == null) {
			errMsg = "Parameter error, fileName is: " + fileName + ", idValue: " + idValue;
			logger.info(errMsg);
			//throw new KronosCoreCommonException(errMsg);
		}
		
		List<Map<String, String>> rows = readCSV(fileName);
		if (rows.isEmpty()) {
			errMsg = "CSV file's content is empty, fileName is: " + fileName;
			logger.info(errMsg);
			//throw new KronosCoreCommonException(errMsg);
		}
		
		Map<String, String> rowFound = null;
		
		for(Map<String, String> row : rows) {
			if(idValue.equals(row.get(ID_COLUMN))) {
				rowFound = row;
				break;
			}
		}
		
		if (rowFound == null) {
			errMsg = idValue + " not found as a Key in " + "CSV file: " + fileName;
			logger.info(errMsg);
			//throw new KronosCoreCommonException(errMsg);
		}
		return rowFound;
	}
	
	/**
	 * Read multi line map of csv file by given id value.
	 * 
	 * @param fileName csv file name.
	 * @param idValue value to find in "Key" column.
	 * @return List<Map<String, String>> of the row content
	 * @throws KronosCoreCommonException
	 */
	public static List<Map<String, String>> readCSVRows(String fileName, String idValue)
			//throws KronosCoreCommonException 
	{
		return readCSVRows(fileName, idValue, true);
	}

	/**
	 * Read multi line map of csv file by given id value, with option to have it use
	 * exact match. If exact match if false, it will use contains.
	 * 
	 * @param fileName csv file name.
	 * @param idValue value to find in "Key" column.
	 * @param matchByExactId boolean to find by exact id match or containing an id
	 * @return List<Map<String, String>> of the row content
	 * @throws KronosCoreCommonException
	 */
	public static List<Map<String, String>> readCSVRows(String fileName, String idValue, boolean matchByExactId)
			//throws KronosCoreCommonException 
	{
		String errMsg = "";

		if (fileName == null || idValue == null) {
			errMsg = "Parameter error, fileName is: " + fileName + ", idValue: " + idValue;
			logger.info(errMsg);
			//throw new KronosCoreCommonException(errMsg);
		}

		List<Map<String, String>> rows = readCSV(fileName);
		if (rows.isEmpty()) {
			errMsg = "CSV file's content is empty, fileName is: " + fileName;
			logger.info(errMsg);
			//throw new KronosCoreCommonException(errMsg);
		}

		List<Map<String, String>> reqRows = new ArrayList<Map<String, String>>();
		for (Map<String, String> row : rows) {
			if (matchByExactId) {
				if (idValue.equals(row.get(ID_COLUMN)))
					reqRows.add(row);
			} else {
				if (row.get(ID_COLUMN).contains(idValue))
					reqRows.add(row);
			}
		}

		String findBy = matchByExactId ? "equals" : "contains";
		if (reqRows.size() == 0)
			logger.info(String.format("Cannot find rows where the key %s the id %s in %s.", findBy, idValue, fileName));

		return reqRows;
	}
	
	/**
	 * Read single line map of csv file by given id value.
	 * 
	 * @param fileName csv file name.
	 * @param idValue value to be find in "Key" column.
	 * @return Map<String, Object> of the row content, <b>null</b> if not found.
	 * @throws KronosCoreCommonException Core Common Exception
	 */
	public static Map<String, Object> readPayloadTemplate(String fileName, String idValue) //throws KronosCoreCommonException 
	{ 
		return convertMap( readCSV(fileName,idValue));
	}
	
	/**
	 * Read the CSV file for all the rows
	 * @param fileName: String
	 * @return List<Map<String,Object>> CSV data for all the rows
	 * @throws KronosCoreCommonException
	 * 		: customized kronos core common exception
	 */
	public static List<Map<String, Object>> readPayloadTemplate(String fileName) //throws KronosCoreCommonException 
	{
		List<Map<String, String>> temp = readCSV(fileName);
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		temp.forEach(k -> result.add(convertMap(k)));
		return result;
	}
	

	/**
	 * Read the CSV file from startRow to endRow
	 * 
	 * @param fileName: String
	 * @param startRow: int
	 * @param endRow: int
	 * @return List<Map<String,Object>> CSV data from startRow to endRow 
	 * @throws KronosCoreCommonException
	 * 		: customized kronos core common exception
	 */
	public static List<Map<String, Object>> readPayloadTemplate(String fileName, int startRow, int endRow) //throws KronosCoreCommonException 
	{
		List<Map<String, String>> temp = readCSV(fileName, startRow, endRow);
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		temp.forEach(k -> result.add(convertMap(k)));
		return result;
	}

	/**
	 * Converts a map of String,String type to map of String, Object type
	 * @param map: Map of String,String
	 * @return Map of String,Object
	 */
	private static Map<String, Object> convertMap(Map<String, String> map)
	{
		Map<String, Object> result = new HashMap<String,Object>();
		map.keySet().stream().forEach(k -> result.put(k, map.get(k)));
		return result;
	}
	
	
	/**
	 * Replace two vertical line with comma.
	 * @param value String
	 * @return String 
	 */
	private static String replaceTwoVerticalLineToComma(String value) {
		return value.replaceAll("\\|\\|", ",");
	}
	
	
}
