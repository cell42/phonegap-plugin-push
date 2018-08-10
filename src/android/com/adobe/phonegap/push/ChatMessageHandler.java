package com.adobe.phonegap.push;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ChatMessageHandler extends Activity implements PushConstants {
    private static final String TAG = "PushTestMessageHandler";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Getting bundle");
        super.onCreate(savedInstanceState);

        Bundle receivedData = getIntent().getExtras();
        Bundle extras = new Bundle();

        if (receivedData != null) {
            Log.d(TAG, "bundle exists!");
            extras.putString("type", receivedData.getString("type", "NewChatMessage"));
            extras.putString("origin", receivedData.getString("origin", "Chat"));
            extras.putString("tipId", receivedData.getString("tipId"));
            extras.putString("messageText", receivedData.getString("messageText"));
        }
        extras.putBoolean("foreground", false);

        Intent intent = new Intent(this, PushHandlerActivity.class);

        intent.putExtras(extras);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(PUSH_BUNDLE, extras);
        intent.putExtra(START_IN_BACKGROUND, false);
        intent.putExtra(FOREGROUND, true);

        startActivity(intent);

        finish();
    }
}
