package mk.ukim.finki.mpip.intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";
    static final int SET_EXPLICIT_TEXT_FIELD = 0;
    static final int PICK_IMAGE = 1;
    static final String IMPLICIT_ACTION = "android.intent.action.IMPLICIT_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initListeners();
    }

    private void initListeners() {
        Button explicitIntentButton = findViewById(R.id.button_explicit);
        Button implicitIntentButton = findViewById(R.id.button_implicit);
        Button sendEmailButton = findViewById(R.id.button_send_email);
        Button showImageButton = findViewById(R.id.button_show_image);

        explicitIntentButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExplicitActivity.class);
            startActivityForResult(intent, SET_EXPLICIT_TEXT_FIELD);
        });

        sendEmailButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"Oliver.Cvetkovski@students.finki.ukim.mk"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "MPiP Send Title");
            intent.putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity");

            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Error sending email", Toast.LENGTH_SHORT).show();
            }
        });

        implicitIntentButton.setOnClickListener(v -> {
            Intent intent = new Intent(IMPLICIT_ACTION);
            startActivity(intent);
        });

        showImageButton.setOnClickListener(v -> pickImageFromGallery());
    }

    private void setText(String textToDisplay){
        TextView textView = findViewById(R.id.result_display);
        textView.setText(textToDisplay);
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SET_EXPLICIT_TEXT_FIELD) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                Bundle extras = data.getExtras();
                String returnText = extras.getString("return_text");
                setText("Result OK: " + returnText);
            } else if (resultCode == RESULT_CANCELED) {
                setText("Result cancelled by User");
            } else {
                setText("Something went wrong");
            }
        }

        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                final Uri imageUri = data.getData();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(imageUri, "image/*");
                startActivity(intent);
            }
        }
    }
}