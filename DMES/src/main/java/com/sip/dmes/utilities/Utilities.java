package com.carvajal.mci.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Gustavo
 */
public class Utilities
{

    /**
     *
     * @param numero
     * @param digitos
     * @return
     */
    public static double Redondear(double numero, int digitos)
    {
        int cifras = (int) Math.pow(10, digitos);
        return Math.rint(numero * cifras) / cifras;
    }
    
    /**
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value)
    {
        boolean result = true;
        if(value != null)
        {
            value = value.replace(" ", "");
            if(value.length() > 0)
            {
                result = false;
            }
        }
        return result;
    }
    
    /**
     *
     * @param value
     * @return
     */
    public static boolean isNumeric(String value)
    {
        boolean result = false;
        String regex  = "[0-9]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if(matcher.matches())
        {
            result = true;
        }
        return result;
    }
    
    /**
     *
     * @param value
     * @return
     */
    public static boolean isAlphaNumeric(String value)
    {
        boolean result = false;
        String regex  = "[a-zA-Z0-9Ññ]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if(matcher.matches())
        {
            result = true;
        }
        return result;
    }
    
    public static boolean isAlphabetical(String value)
    {
        boolean result = false;
        String regex  = "[a-zA-ZÑñ\\t\\n\\x0b\\r\\f]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if(matcher.matches())
        {
            result = true;
        }
        return result;
    }
    
   
   public static boolean isEmail(String value)
   {
        boolean result = false;
        String regex  = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if(matcher.matches())
        {
            result = true;
        }
        return result;
    }
}
