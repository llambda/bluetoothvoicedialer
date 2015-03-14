package org.gman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

public class LauncherShortcuts extends Activity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        final Intent intent = getIntent();
        final String action = intent.getAction();

        if (Intent.ACTION_CREATE_SHORTCUT.equals(action)) {
            setupShortcut();
            finish();
            return;
        }
    }
  
    private void setupShortcut() {
        Intent shortcutIntent = new Intent();
        shortcutIntent.setAction(Intent.ACTION_VOICE_COMMAND);
        shortcutIntent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.shortcut_name));
        Parcelable iconResource = Intent.ShortcutIconResource.fromContext(
                this,  R.drawable.phonebt);
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);

        setResult(RESULT_OK, intent);
    }
}
