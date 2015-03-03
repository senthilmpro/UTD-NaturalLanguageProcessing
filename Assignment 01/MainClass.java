import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;

/*
 * author : SENTHIL KUMAR MUTHUVE
 * UTD ID : 2021172515
 */
public class MainClass {

	public static void main(String[] args) throws Exception {

		String myFilePath = "demo.txt";
		if(args.length > 0 )
		{
			myFilePath = args[0].toString();	
		}
		String fileString = getFileString(myFilePath);
		doRegEx(fileString);
	}
	
	
	/*
	 * PROCESS THE STRING USING REGEX
	 */
	public static void doRegEx(String fileString)
	{
		String strYear = "([0-9]{4})|(’(\\d{2}))";
		String month = "(Jan(uary)|Feb(ruary)|Mar(ch)|Apr(il)|May|Jun(e)|Jul(y)|Aug(ust)|Sep(tember)|Oct(ober)|Nov(ember)|Dec(ember))";
		String date = "([0-3]?[0-9])(st|nd|rd|th)?";
		String monthoryear = "("+ month +"|"+ strYear + ")?(\\s)?";
		
		/*
		 * RegEx patterns
		 */
		String sampleMatch = month+"(\\s)"+date+"(,)*(\\s)*(\\S)*\\d{4}";
		String sampleMatch2 = monthoryear + date + "(\\s)?"+ "((of|morning)(\\s))+"+ monthoryear+"((, )?(\\d{4}))?";
		
		
		String[] matchStrings = { sampleMatch, sampleMatch2};
		for(String strFinal : matchStrings)
		{
			Pattern myPattern = Pattern.compile(strFinal);
			Matcher myMatcher = myPattern.matcher(fileString);
			
			while(myMatcher.find())
				System.out.println(myMatcher.group().toString().trim());
		}
		
	}
	
	/*
	 * GET FILE CONTENTS AND STORE IT IN STRING
	 */
	static String getFileString(String filePathName) throws Exception
	{
		List<String> lines = Files.readAllLines(Paths.get(filePathName), Charset.defaultCharset());
		StringBuilder sb = new StringBuilder();
		for(String line : lines)
		{
			sb.append(line).append(" ");
		}
		return sb.toString();
	}

}
