package mk.ukim.finki.mpip.intents;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ExplicitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        this.initListeners();
    }

    private void initListeners() {
        EditText text = findViewById(R.id.explicit_text_value);
        Button submitButton = findViewById(R.id.submit_button);
        Button cancelButton = findViewById(R.id.cancel_button);

        submitButton.setOnClickListener(v -> {
            Editable textToSend = text.getText();
            if (textToSend.length() > 0) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("return_text", textToSend.toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(ExplicitActivity.this, "Please enter text", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}