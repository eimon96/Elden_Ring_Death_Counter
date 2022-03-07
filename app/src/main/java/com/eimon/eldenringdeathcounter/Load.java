package com.eimon.eldenringdeathcounter;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Load the counter
 */
public class Load implements Runnable
{
    private static final Context co = MainActivity.co;
    private static final String filename = "counterSaveFile";

    /**
     * Run into different thread
     */
    @Override
    public void run()
    {
        try
        {
            loadCounter();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Load
     * @throws FileNotFoundException as well as many more exceptions
     */
    private static void loadCounter() throws FileNotFoundException
    {
        FileInputStream fis = co.openFileInput(filename);

        InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader))
        {
            String line = reader.readLine();
            while (line != null)
            {
                stringBuilder.append(line);
                line = reader.readLine();
            }
            MainActivity.TvCounter.setText(stringBuilder.toString());
        }
        catch (Exception e)
        {
            System.err.println("Pffffffffffffff");
            MainActivity.TvCounter.setText("0");
            e.printStackTrace();
        }
    }
}
