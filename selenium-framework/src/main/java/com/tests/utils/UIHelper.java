package com.tests.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UIHelper
{

    /*
     * Contains Function which are helpful
     * in implementing the framework and fetching the data efficiently
     */
    
    public static int readLines(String relativePath)
    {
        BufferedReader reader = null;
        @SuppressWarnings("unused")
        String line = null;
        int count = 0;

        if ("".equals(relativePath))
        {
            String message = "relativePath can't be empty.";
            //logger.info(message);
        }

        try
        {
            reader = new BufferedReader(
                new InputStreamReader(UIHelper.class.getClassLoader().getResourceAsStream(relativePath)));

            while ((line = reader.readLine()) != null)
            {
                count++;
            }
            reader.close();
        }
        catch (Exception e)
        {
           // logger.error("IOException occured when get absolute path of " + relativePath, e);
        }
        return count - 1;
    }

}
