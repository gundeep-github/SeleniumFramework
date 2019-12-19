//package com.tests.utils;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Map;
//
//import org.joda.time.DateTime;
//import org.joda.time.DateTimeConstants;
//
//import com.kronos.exception.KronosCoreCommonException;
//import com.kronos.utils.common.ContextConstant;
//import com.kronos.utils.datetime.DateGeneratorUtilities;
//import com.kronos.utils.datetime.DateParserUtilities;
//
///**
// * Date methods for adding and subtraction of dates to support date
// * modification. 
// * Input for these methods will come from text files, so they should receive and
// * return simple types.
// */
//public class DateParser {
//
//	private int startDayOfWeek;
//	private SimpleDateFormat sdf;
//	private String currentFormat;
//	private DateParserUtilities dateParserUtil;
//	private DateGeneratorUtilities dateGenerator;
//
//	/**
//	 * Default constructor
//	 */
//	public DateParser() {
//		this.dateParserUtil = new DateParserUtilities();
//		this.dateGenerator = new DateGeneratorUtilities();
//		currentFormat = ContextConstant.DEFAULT_UI_DATE_FORMAT;
//		sdf = new SimpleDateFormat(currentFormat);
//		startDayOfWeek = DateTimeConstants.SUNDAY;
//	}
//
//	/**
//	 * Constructor with one Map parameter, the map contains the date format
//	 * 
//	 * @param options: Map
//	 */
//	public DateParser(Map<String, String> options) {
//		this.dateParserUtil = new DateParserUtilities();
//		this.dateGenerator = new DateGeneratorUtilities();
//		currentFormat = options.containsKey(ContextConstant.DATE_FORMAT) ? options.get(ContextConstant.DATE_FORMAT) : ContextConstant.DEFAULT_UI_DATE_FORMAT;
//		sdf = new SimpleDateFormat(currentFormat);
//		startDayOfWeek = options.containsKey(ContextConstant.START_DAY_OF_WEEK) ? Integer.parseInt(options.get(ContextConstant.START_DAY_OF_WEEK))
//				: DateTimeConstants.SUNDAY;
//	}
//	
//	/**
//	 * Constructor with two parameters, Date date, Map parameter.
//	 * This constructor can only be used for testing purpose NEVER use it for
//	 * any other usage
//	 * 
//	 * @param date: Date 
//	 * @param options: Map
//	 */
//	public DateParser(Date date, Map<String, String> options) {
//		dateParserUtil = new DateParserUtilities(date);
//		dateGenerator = new DateGeneratorUtilities(date);
//		currentFormat = options.containsKey(ContextConstant.DATE_FORMAT) ? options.get(ContextConstant.DATE_FORMAT) : ContextConstant.DEFAULT_UI_DATE_FORMAT;
//		sdf = new SimpleDateFormat(currentFormat);
//		startDayOfWeek = options.containsKey(ContextConstant.START_DAY_OF_WEEK) ? Integer.parseInt(options.get(ContextConstant.START_DAY_OF_WEEK))
//				: DateTimeConstants.SUNDAY;
//		
//	}
//
//	/**
//	 * Parse the date pattern with only one parameter a string of date expression,
//	 * this expression is the pattern of date, for example: today/day before yesterday/monday/thursday/-1y+5M-1dYesterda/+10MFriday
//	 * 
//	 * @param dateExpression: String 
//	 * @return date in String
//	 * @throws KronosCoreCommonException
//	 * 		: customized kronos core common exception
//	 */
//	public String parseDatePattern(String dateExpression) throws KronosCoreCommonException {
//		if (!dateParserUtil.isValidPattern(dateExpression))
//			throw new KronosCoreCommonException("You date expression does not follow any pattern we have defined.");
//		try {
//			return parse(dateExpression, sdf.toPattern(), dateParserUtil.getCurrentDate(), dateGenerator);
//		} catch (Exception e) {
//			throw new KronosCoreCommonException(e.getMessage(),e);
//		}
//	}
//
//	/**
//	 * Parse the date pattern with a string of date expression and a date, 
//	 * this date is the given date and will be transfered to be a Date format and pass to parse function,
//	 * the expression is the pattern of date, for example: today/day before yesterday/monday/thursday/-1y+5M-1dYesterda/+10MFriday
//	 * 
//	 * @param date: String 
//	 * @param dateExpression: String 
//	 * @return date in String
//	 * @throws KronosCoreCommonException
//	 * 		: customized kronos core common exception
//	 */
//	public String parseDatePattern(String date, String dateExpression) throws KronosCoreCommonException {
//		if (!dateParserUtil.isValidPattern(dateExpression))
//			throw new KronosCoreCommonException("You date expression does not follow any pattern we have defined!");
//		try {
//			//generate a date based on the date given by the user
//			Date givenDate = new SimpleDateFormat(sdf.toPattern()).parse(date);
//			return parse(dateExpression, sdf.toPattern(), dateParserUtil.getDate(givenDate),
//					new DateGeneratorUtilities(givenDate));
//		} catch (ParseException e) {
//			throw new KronosCoreCommonException(e.getMessage());
//		}
//	}
//
//	/**
//	 * Parse the date pattern with a date expression, a date and a format, expression is the date pattern,
//	 * the format will be a parameter of parse function of transfer the date, for example:  today/day before yesterday/monday/thursday/-1y+5M-1dYesterda/+10MFriday
//	 * 
//	 * 
//	 * @param date: String 
//	 * @param dateExpression: String 
//	 * @param format : String
//	 * @return date in String
//	 * @throws KronosCoreCommonException
//	 * 		: customized kronos core common exception
//	 */
//	public String parseDatePattern(String date, String dateExpression, String format) throws KronosCoreCommonException {
//		if (!dateParserUtil.isValidPattern(dateExpression))
//			throw new KronosCoreCommonException("You date expression does not follow any pattern we have defined");
//		try {
//			//generate a date based on the date given by the user
//			Date givenDate = new SimpleDateFormat(sdf.toPattern()).parse(date);
//			return parse(dateExpression, format, dateParserUtil.getDate(givenDate), new DateGeneratorUtilities(givenDate));
//		} catch (ParseException e) {
//			throw new KronosCoreCommonException(e.getMessage());
//		}
//	}
//
//	/**
//	 * check if the date is weekend or not
//	 * 
//	 * @param date: String 
//	 * @return true or false in boolean
//	 * @throws KronosCoreCommonException
//	 * 		: customized kronos core common exception
//	 */
//	public boolean isWeekEnd(String date) throws KronosCoreCommonException {
//		try {
//			Date givenDate = new SimpleDateFormat(sdf.toPattern()).parse(date);
//			int weekDay = dateParserUtil.getDayOfWeek(givenDate);
//			return weekDay == DateTimeConstants.SUNDAY || weekDay == DateTimeConstants.SATURDAY;
//		} catch (ParseException e) {
//			throw new KronosCoreCommonException(e.getMessage());
//		}
//	}
//	
//	/**
//     * Compares two date strings for ordering.
//     *
//     * @param dateString0:   one date string
//     * @param dateString1:   the other date string
//     * @return  the value 0 if the dates are equal;
//     *          a value less than 0 if the first date is before the second;
//     *          and a value greater than 0 if the first date is before the second.
//     *          If a date string cannot be parsed, the strings are compared lexicographically.
//	 * @throws KronosCoreCommonException 
//	 * 		: customized kronos core common exception
//     */
//    public int compareDateAsString(String dateString0, String dateString1) throws KronosCoreCommonException {
//        try {
//            return sdf.parse(dateString0).compareTo(sdf.parse(dateString1));
//        } catch (ParseException e) {
//			throw new KronosCoreCommonException(e.getMessage());
//        }
//    }
//    
//    /**
//     *  Validate date pattern.
//     * 
//     * @param dateExpression: String
//     * @return true or false in boolean
//     */
//    public boolean isValidatePattern(String dateExpression){
//    	return dateParserUtil.isValidPattern(dateExpression);
//    }
//    
//    /**
//     * Convert date format
//     * 
//     * @param date
//     * @param desireFormat
//     * @return
//     * @throws KronosCoreCommonException
//     */
//	public String convertDateFormat(String date, String desireFormat) throws KronosCoreCommonException {
//		return convertDateFormat(date, ContextConstant.DEFAULT_UI_DATE_FORMAT, desireFormat);
//	}
//    
//    /**
//     * Convert date format
//     * 
//     * @param date
//     * @param originalFormat
//     * @param desireFormat
//     * @return
//     * @throws KronosCoreCommonException
//     */
//    public String convertDateFormat(String date, String originalFormat, String desireFormat) throws KronosCoreCommonException{
//    	DateFormat inputDateFormat = new SimpleDateFormat(originalFormat);
//    	DateFormat outputDateFormat = new SimpleDateFormat(desireFormat);    	
//    	Date dateToConvert = null;
//    	try {
//			dateToConvert = inputDateFormat.parse(date);
//		} catch (ParseException e) {
//			throw new KronosCoreCommonException(e.getMessage());
//		}
//    	
//    	return outputDateFormat.format(dateToConvert);
//    }
//
//    /**
//     * Get the current format the dateParser is holding
//     * @return
//     */
//	public String getCurrentFormat() {
//		return currentFormat;
//	}
//
//	/**
//	 * Parse date
//	 * 
//	 * @param dateExpression: String
//	 * @param format: String
//	 * @param dt: DateTime
//	 * @param dg: DateGeneratorUtilities 
//	 * @return parse result in String
//	 * @throws ParseException
//	 * @throws KronosCoreCommonException
//	 * 		: customized kronos core common exception
//	 */
//	private String parse(String dateExpression, String format, DateTime dt, DateGeneratorUtilities dg)
//			throws ParseException, KronosCoreCommonException {
//		DateTime rs = dt;
//		if (dateParserUtil.isWeekDayPatternPresents(dateExpression)) {
//			rs = dateParserUtil.calculateWeekDayPattern(dg, startDayOfWeek, dateExpression);
//		}
//		rs = dateParserUtil.calculateDatePattern(rs, dateExpression);
//		String arbitrary = dateParserUtil.calculateArbitraryText(dateExpression);
//
//		return arbitrary.trim().isEmpty() ? rs.toString(format) : rs.toString(format) + arbitrary;
//	}
//}
