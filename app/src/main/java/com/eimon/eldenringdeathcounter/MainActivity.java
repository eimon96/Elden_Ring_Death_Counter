package com.eimon.eldenringdeathcounter;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * @author eimon
 *
 * 'cause Elden Ring...
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    /**
     * Define things
     */
    public static TextView TvCounter;
    private Button BtDeath;
    private FloatingActionButton FbtUndo;
    public static Context co;

    /**
     * onCreate - initialize variables
     * Load Counter from file if exists
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setTheme(R.style.splashScreenTheme);
        super.onCreate(savedInstanceState);
        setTheme(R.style.EldenRingDeathCounter);
        setContentView(R.layout.activity_main);

        initialize();

        Load ld = new Load();
        new Thread(ld).start();
    }

    /**
     * Connect the variables with the views
     * Get context
     */
    private void initialize()
    {
        TvCounter = findViewById(R.id.TvDeath);

        BtDeath = findViewById(R.id.BtDeath);
        BtDeath.setOnClickListener(this);

        FbtUndo = findViewById(R.id.FbtUndo);
        FbtUndo.setOnClickListener(this);

        co = this.getApplicationContext();
    }

    /**
     * ActionBar stuff
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dot_menu, menu);
        setTitle(R.string.dying_already);
        return true;
    }

    /**
     * onSelect ActionBar Items
     *
     * Reset -> Reset Counter to 0 (ask first)
     *
     * Info -> Show Info (Who, What, How, Why, WHY)
     * JK just info
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.Reset)
        {
            ButAreYouSure();
            return true;
        }

        if (id == R.id.Info)
        {
//            Intent iAbout = new Intent(MainActivity.this, AboutActivity.class);
//            startActivity(iAbout);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * BtDeath -> Increase Counter
     * FbtUndo -> Decrease Counter
     *
     * @param v: view (which of the two buttons)
     */
    @Override
    public void onClick(View v)
    {
        if (v == BtDeath)
        {
            MediaPlayerClass.initPlayer(co);
            DyingAlready.ksanaPethana();
        }

        if (v == FbtUndo)
        {
            DyingAlready.oopsAkuro();
        }
    }

    /**
     * Dialog box for the reset option
     */
    private void ButAreYouSure()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setMessage(getResources().getString(R.string.dialog_text));
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Reset",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        DyingAlready.backToZero();
                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    /**
     * onStop - stop the sfx
     */
    @Override
    protected void onStop()
    {
        super.onStop();
        if (MediaPlayerClass.isPlaying())
        {
            MediaPlayerClass.ReleasePlayer();
        }
    }

    /**
     * onPause - save counter
     */
    @Override
    protected void onPause()
    {
        super.onPause();

        Save sv = new Save();
        new Thread(sv).start();
    }
}