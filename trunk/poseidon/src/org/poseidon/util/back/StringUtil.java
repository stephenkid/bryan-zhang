package org.poseidon.util.back;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtil
 * 
 * @author 何晓滨
 * @version 创建时间：Sep 1, 2008 3:00:29 PM
 */
public abstract class StringUtil {

    private static final char[] LetterAndNumber = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

    private static final char[] LetterOnly      = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z'            };

    private static final Random Random          = new Random();

    /**
     * 得到指定长度的含字母或数字的随机字符串（总是以字母开头）
     * @param length
     * @return 随机字符串
     */
    public static String getRandomTxt(int length) {
        StringBuilder sb = new StringBuilder(length);
        sb.append(LetterOnly[Random.nextInt(LetterOnly.length)]);
        for (int i = length - 1; i > 0; i--) {
            sb.append(LetterAndNumber[Random.nextInt(LetterAndNumber.length)]);
        }
        return sb.toString();
    }
    
    /**
     * 判断字符串 target 是否在 source 中出现
     */
    public static boolean isExits(String source,String target){
    	Pattern pat = null;
		Matcher mat = null;
		
		pat = Pattern.compile(target);
		mat = pat.matcher(source) ;
		return mat.find() ? true : false;
    }
    
    /**
     * 从字符串中找到符合正则表达式的字符串
     * @param text
     *            原字符串
     * @param pattern
     *            正则表达式
     * @return 符合正则的字符串
     */
    public static String getParseText(String text, Pattern pattern) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return null;
        }
    }
    
    public static boolean parseText(String text, Pattern pattern) {	
        return (getParseText(text, pattern) != null) ? true : false;
    }
    

	public static String encodeUTF8(String s) {
		try {
			return java.net.URLEncoder.encode(s, "UTF-8");
		}
		catch (java.io.UnsupportedEncodingException uee) {
			uee.printStackTrace();
			return null;
		}
	}
	
	/**
     * 判断字符串是否为空或null
     * @param text
     * @return
     */
    public static boolean isBlankOrNull(String text) {
        return (null==text||"".equals(text))?true:false;
       
    }
	
	public static void main(String[] args) {
		
		boolean f = isExits("/hd/ht/dss", "^/");
		System.out.println("f = "+f);
		
		int length = 20;
		if (args.length > 0) {
			try {
				length = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				// ignore
			}
		}
		System.out.println("length: " + length);
		System.out.println();
		for (int i = 10; i > 0; i--) {
			System.out.println(getRandomTxt(length));
		}
	}
}
