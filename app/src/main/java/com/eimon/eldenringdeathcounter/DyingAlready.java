package com.eimon.eldenringdeathcounter;

import android.widget.TextView;
import android.widget.Toast;

/**
 * Deals with the counter
 * Increase - Decrease - Reset
 */
public class DyingAlready
{
    /**
     * Define things
     */
    private static TextView TvCounter = MainActivity.TvCounter;

    /**
     * Increase Counter
     */
    public static void ksanaPethana()
    {
        String counter = TvCounter.getText().toString();
        int c = getCounter(counter);

        c = c + 1;
        TvCounter.setText(Integer.toString(c));
    }

    /**
     * Decrease Counter
     */
    public static void oopsAkuro()
    {
        String counter = TvCounter.getText().toString();
        int c = getCounter(counter);

        if (c != 0)
        {
            c = c - 1;
        }
        else
        {
            Toast tost = Toast.makeText(MainActivity.co, "Don't Cheat!", Toast.LENGTH_SHORT);
            tost.show();
        }

        TvCounter.setText(Integer.toString(c));
    }

    /**
     * Parse the Counter from String to int
     * @param counter: String
     *
     * @return the int if no error, otherwise 0
     */
    private static int getCounter(String counter)
    {
        int c;

        try
        {
            c = Integer.parseInt(counter);
        }

        catch (Exception e)
        {
            System.err.println("Olo kai kapoia malakia tha egine");
            e.printStackTrace();
            return 0;
        }

        return c;
    }

    /**
     * Reset Counter back to 0
     */
    public static void backToZero()
    {
        TvCounter.setText("0");
    }
}
