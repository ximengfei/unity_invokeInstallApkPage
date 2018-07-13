package com.am1105.installapk;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends UnityPlayerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
//    public void click(View v) {
//    	InstallUtil.invokeAndroidInstallPage(this, "/storage/emulated/0/1.0.7.apk");
//    }
    
    public static boolean invokeAndroidInstallPage(String filePath) {
    	Log.e("MainActivity", "##################### Intent to install page ######################");
    	String apkpath = filePath;
    	final File file = new File(apkpath);
    	if (file.exists()) {
    		UnityPlayer.currentActivity.runOnUiThread(new Runnable()
            {
              public void run()
              {
    			try {
    				 // Log.e("MainActivity", UnityPlayer.currentActivity.getPackageName() + ".fileProvider");com.am1105.installapk.fileprovider
    				if(Build.VERSION.SDK_INT>=24) {
    		            Uri apkUri = FileProvider.getUriForFile(UnityPlayer.currentActivity, UnityPlayer.currentActivity.getPackageName() + ".fileprovider", file);
    		            Intent install = new Intent(Intent.ACTION_VIEW);
    		            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    		            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
    		            UnityPlayer.currentActivity.startActivity(install);
    		        } else{
    		            Intent install = new Intent(Intent.ACTION_VIEW);
    		            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
    		            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		            UnityPlayer.currentActivity.startActivity(install);
    		        }
    			
    			}
    			catch(Exception e) {
//    				Toast.makeText(ac, e.getMessage(), 1).show();
    				Log.e("MainActivity", e.getMessage());
    				//return false;
    			}
    			
              }
            });
    	return true;
    	} 
    	return false;
	}
}
