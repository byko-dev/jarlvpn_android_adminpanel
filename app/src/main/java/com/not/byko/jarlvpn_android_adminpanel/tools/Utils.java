package com.not.byko.jarlvpn_android_adminpanel.tools;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class Utils {

    public static void saveConfigAsFile(Context context, String fileName, String content, String childName){
        try {
            File root = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    childName);
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, fileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(content);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved " + root.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
