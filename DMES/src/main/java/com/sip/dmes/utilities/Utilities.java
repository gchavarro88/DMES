package com.carvajal.mci.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Gustavo
 */
public class Utilities
{

    private static final char[] CONSTS_HEX =
    {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

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
        if (value != null)
        {
            value = value.replace(" ", "");
            if (value.length() > 0)
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
        String regex = "[0-9]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches())
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
        String regex = "[a-zA-Z0-9Ññ]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches())
        {
            result = true;
        }
        return result;
    }

    public static boolean isAlphabetical(String value)
    {
        boolean result = false;
        String regex = "[a-zA-ZÑñ\\t\\n\\x0b\\r\\f]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches())
        {
            result = true;
        }
        return result;
    }

    public static boolean isEmail(String value)
    {
        boolean result = false;
        String regex = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches())
        {
            result = true;
        }
        return result;
    }

    public static String encriptaEnMD5(String stringAEncriptar)
    {
        try
        {
            MessageDigest msgd = MessageDigest.getInstance("MD5");
            byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
            StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++)
            {
                int bajo = (int) (bytes[i] & 0x0f);
                int alto = (int) ((bytes[i] & 0xf0) >> 4);
                strbCadenaMD5.append(CONSTS_HEX[alto]);
                strbCadenaMD5.append(CONSTS_HEX[bajo]);
            }
            return strbCadenaMD5.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            return null;
        }
    }

}
