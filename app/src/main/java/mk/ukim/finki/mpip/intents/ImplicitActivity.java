package mk.ukim.finki.mpip.intents;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ImplicitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);
        this.setComponentText();
    }

    private void setComponentText() {
        TextView textView = findViewById(R.id.text_display);
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities(mainIntent, 0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pkgAppsList.size(); i++) {
            sb.append(pkgAppsList.get(i).activityInfo.name).append("\n");
        }
        textView.setText(sb.toString());
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}