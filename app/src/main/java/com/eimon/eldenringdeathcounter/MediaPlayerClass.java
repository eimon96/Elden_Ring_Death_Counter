package com.eimon.eldenringdeathcounter;

import android.content.Context;
import android.media.AudioAttributes;
import android.net.Uri;
import android.media.MediaPlayer;

/**
 * Play the SFX (You Died sound)
 */
public class MediaPlayerClass
{
    private static MediaPlayer mediaPlayer;
    private static Context mdContext;
    private static Boolean running = false;

    /**
     * Initialize MediaPlayer
     * @param mContext
     */
    public static void initPlayer(Context mContext)
    {
        mdContext = mContext;

        try
        {
            Uri mUri = Uri.parse("android.resource://com.eimon.eldenringdeathcounter/" + R.raw.elden_ring_you_died_sfx);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioAttributes(
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            );
            mediaPlayer.setDataSource(mContext, mUri);
            mediaPlayer.prepare();
            mediaPlayer.start();
            running = true;
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
                /**
                 * Complete
                 * @param mp: mediaplayer
                 */
                @Override
                public void onCompletion(MediaPlayer mp)
                {
                    ReleasePlayer();
                    running = false;
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Pause
     */
    public static void PausePlayer()
    {
        try
        {
            mediaPlayer.pause();
            running = false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Resume
     */
    public static void ResumePlayer()
    {
        try
        {
            mediaPlayer.start();
            running = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Release
     */
    public static void ReleasePlayer()
    {
        try
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            running = false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Checks if SFX is playing
     * @return true or false (da!)
     */
    public static boolean isPlaying()
    {
        if (running)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}