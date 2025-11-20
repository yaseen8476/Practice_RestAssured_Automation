package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonUtilities {

    public static String getJsonFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }


        public static String readJsonFile(String filePath) throws Exception {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        }
    
    // Main processor
    public static String getProcessedJson(String filePath) {
        String rawJson = getJsonFromFile(filePath);
        rawJson = processRandomString(rawJson);
        rawJson = processRandomNumber(rawJson);
        rawJson = processCurrentDate(rawJson);
        rawJson = processRandomDate(rawJson);
        rawJson = processRandomPhone(rawJson);
        return rawJson;
    }

    // Handle ${__RandomString(n)}
    private static String processRandomString(String json) {
        Pattern pattern = Pattern.compile("\\$\\{__RandomString\\((\\d+)\\)}");
        Matcher matcher = pattern.matcher(json);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            int length = Integer.parseInt(matcher.group(1));
            String random = getRandomString(length);
            matcher.appendReplacement(sb, random);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    // Handle ${__RandomNumber(min,max)}
    private static String processRandomNumber(String json) {
        Pattern pattern = Pattern.compile("\\$\\{__RandomNumber\\((\\d+),(\\d+)\\)}");
        Matcher matcher = pattern.matcher(json);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            int min = Integer.parseInt(matcher.group(1));
            int max = Integer.parseInt(matcher.group(2));
            String random = String.valueOf(getRandomNumber(min, max));
            matcher.appendReplacement(sb, random);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    // Handle ${__CurrentDate(format)}
    private static String processCurrentDate(String json) {
        Pattern pattern = Pattern.compile("\\$\\{__CurrentDate\\((.*?)\\)}");
        Matcher matcher = pattern.matcher(json);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String format = matcher.group(1);
            String currentDate = new SimpleDateFormat(format).format(new Date());
            matcher.appendReplacement(sb, currentDate);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    // Handle ${__RandomDate(start,end,format)}
    private static String processRandomDate(String json) {
        Pattern pattern = Pattern.compile("\\$\\{__RandomDate\\((.*?),(.*?),(.*?)\\)}");
        Matcher matcher = pattern.matcher(json);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String startDateStr = matcher.group(1).trim();
            String endDateStr = matcher.group(2).trim();
            String format = matcher.group(3).trim();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = sdf.parse(startDateStr);
                Date endDate = sdf.parse(endDateStr);
                long randomMillis = startDate.getTime() + 
                                    (long) (Math.random() * (endDate.getTime() - startDate.getTime()));
                String randomDate = new SimpleDateFormat(format).format(new Date(randomMillis));
                matcher.appendReplacement(sb, randomDate);
            } catch (ParseException e) {
                throw new RuntimeException("Invalid date format in placeholder", e);
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

 // Handle ${__RandomPhone(digits)}
    private static String processRandomPhone(String json) {
        Pattern pattern = Pattern.compile("\\$\\{__RandomPhone\\((\\d+)\\)}");
        Matcher matcher = pattern.matcher(json);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            int digits = Integer.parseInt(matcher.group(1));
            String phone = getRandomPhone(digits);
            matcher.appendReplacement(sb, phone);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    // Generate random phone number of given digits
    public static String getRandomPhone(int digits) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < digits; i++) {
            sb.append(random.nextInt(10)); // append 0–9
        }
        return sb.toString();
    }
   
    
    // Helpers
    public static String getRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private static int getRandomNumber(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }


	public static String getProcessedJsonString1(String rawPayload) {
		// TODO Auto-generated method stub
		return null;
	}
	

	    private static final String RANDOM_STRING_PATTERN = "\\$\\{__RandomString\\((\\d+)\\)\\}";
	    private static final String RANDOM_PHONE_PATTERN = "\\$\\{__RandomPhone\\((\\d+)\\)\\}";

	    // ✅ Generate random string (letters + numbers)
	    private static String generateRandomString(int length) {
	        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        StringBuilder sb = new StringBuilder();
	        Random rand = new Random();
	        for (int i = 0; i < length; i++) {
	            sb.append(chars.charAt(rand.nextInt(chars.length())));
	        }
	        return sb.toString();
	    }

	    // ✅ Generate random phone number (numeric only)
	    private static String generateRandomPhone(int length) {
	        StringBuilder sb = new StringBuilder();
	        Random rand = new Random();
	        for (int i = 0; i < length; i++) {
	            sb.append(rand.nextInt(10));
	        }
	        return sb.toString();
	    }

	    // ✅ Process random strings in JSON
	    private static String processRandomStrings(String json) {
	        Pattern pattern = Pattern.compile(RANDOM_STRING_PATTERN);
	        Matcher matcher = pattern.matcher(json);
	        StringBuffer sb = new StringBuffer();
	        while (matcher.find()) {
	            int length = Integer.parseInt(matcher.group(1));
	            matcher.appendReplacement(sb, generateRandomString(length));
	        }
	        matcher.appendTail(sb);
	        return sb.toString();
	    }

	    // ✅ Process random phone numbers in JSON
	    private static String processRandomPhones(String json) {
	        Pattern pattern = Pattern.compile(RANDOM_PHONE_PATTERN);
	        Matcher matcher = pattern.matcher(json);
	        StringBuffer sb = new StringBuffer();
	        while (matcher.find()) {
	            int length = Integer.parseInt(matcher.group(1));
	            matcher.appendReplacement(sb, generateRandomPhone(length));
	        }
	        matcher.appendTail(sb);
	        return sb.toString();
	    }

	    // ✅ Process inline payload string
	    public static String getProcessedJsonString(String rawJson) {
	        String processedJson = processRandomStrings(rawJson);
	        processedJson = processRandomPhones(processedJson);
	        return processedJson;
	    }

    
	
    
}
