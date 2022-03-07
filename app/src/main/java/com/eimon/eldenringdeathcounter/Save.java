package com.eimon.eldenringdeathcounter;

import android.content.Context;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Save the counter locally to the device (access by this app only)
 */
public class Save implements Runnable
{
    private static final Context co = MainActivity.co;
    private static final String filename = "counterSaveFile";
    private static String toWrite;

    /**
     * Run into different thread
     */
    @Override
    public void run()
    {
        saveCounter();
    }

    /**
     * Save
     */
    private static void saveCounter()
    {
        toWrite = MainActivity.TvCounter.getText().toString();
        try
        {
            FileOutputStream fos = co.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(toWrite.getBytes(StandardCharsets.UTF_8));
            fos.close();
        }
        catch (Exception e)
        {
            System.err.println("No save for you.");
            e.printStackTrace();
        }
    }

}
