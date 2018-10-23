package com.example.hilal.uts;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCM_KITA";
    @Override
    public void onNewToken(String s){
        super.onNewToken(s);
        Log.e("NEW_TOKEN", s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "Pengirim: " + remoteMessage.getFrom());
        if(remoteMessage.getData().size() > 0){
            Log.d(TAG, "Pesan : " + remoteMessage.getData().get("body"));
        }

        MyNotificationManager.getInstance(this).displayNotification(
                remoteMessage.getData().get("body"),
                remoteMessage.getData().get("tittle")
        );
    }
}

// cNQARUm_krg:APA91bEh9z_9lmYkurcg_0LFxZd-FDzuKst9WPaXrO2iLrnqAmKLUcGMtj53mbecNgujzDff2VC_0p3b9IsMuNlxEuRRpCpUXkHwkpgkMIKnxT5I1G2CPeF4K2Ew3soZX6W2tzK08y4l
