package br.usp.ime.choreos.vv;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoapEnvelopeHelper {
	
	private static final String REGEX_MATCH = ">\\?<";

	public static String generate(String xml, List<String> parameters) throws IllegalArgumentException  {

		for(int i = 0; i < parameters.size(); i++){
			// Tag for the current argument
			if(xml.indexOf(">?<") < 0){
				IllegalArgumentException noMoreParameters = new IllegalArgumentException (
						"Number of parameters exceeds number of parameters in XML envelope. Parameters expected: "
								+ (i - 1)
								+ " parameters given: "
								+ parameters.size());
				throw noMoreParameters;
			}
			xml = xml.replaceFirst(REGEX_MATCH,  ">"  + parameters.get(i) + "<");
			System.out.println(xml);
		}
		
		if(xml.indexOf(">?<") >= 0){
			IllegalArgumentException lessParameters = new  IllegalArgumentException(
					"Number of parameters less than number of parameters in XML envelope. "
					+ "Parameters given: "
					+ parameters.size());
			throw lessParameters;
			
		}
		
		return xml;
	}
	
	public static String generate(String xml, String... parameters) throws Exception {
		List<String> paramList = Arrays.asList(parameters);
		
		return generate(xml, paramList);
	}
	
	public static String getCleanResponse(String xml){
		String patternStr = ":Body>\\s*?<.*?>(.*)</.*?>\\s*?</.*?:Body>";
		// Compile and use regular expression
		Pattern pattern = Pattern.compile(patternStr, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(xml);
		boolean matchFound = matcher.find();
		
		if(matchFound){
			if(matcher.groupCount() > 0){
				return matcher.group(1).trim();
			}
		}

		return xml;
	}
	
}
