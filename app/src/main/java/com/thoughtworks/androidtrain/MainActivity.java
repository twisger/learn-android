package com.thoughtworks.androidtrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_SELECT_PHONE_NUMBER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

    }
    public void initUI() {
        findViewById(R.id.button1).setOnClickListener(v -> {
            Intent intent = new Intent(this, ConstraintActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.login_button).setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.pick_contact).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, REQUEST_SELECT_PHONE_NUMBER);
            }
        });

        findViewById(R.id.fragment).setOnClickListener(v -> {
            startActivity(new Intent(this, MyFragmentActivity.class));
        });

        findViewById(R.id.recycler_view).setOnClickListener(v -> {
            startActivity(new Intent(this, RecyclerViewActivity.class));
        });

        findViewById(R.id.thread).setOnClickListener(v -> {
            startActivity(new Intent(this, ThreadActivity.class));
        });

        findViewById(R.id.handler).setOnClickListener(v -> {
            startActivity(new Intent(this, HandlerActivity.class));
        });

        findViewById(R.id.rxjava).setOnClickListener(v -> {
            startActivity(new Intent(this, RxJavaActivity.class));
        });

        findViewById(R.id.sp).setOnClickListener(v -> {
            startActivity(new Intent(this, SpActivity.class));
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_PHONE_NUMBER && resultCode == RESULT_OK) {
            // Get the URI and query the content provider for the phone number
            Uri contactUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
            // If the cursor returned is valid, get the phone number
            if (cursor != null && cursor.moveToFirst()) {
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberIndex);
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                cursor.close();
                Toast.makeText(this, name + " " + number, Toast.LENGTH_LONG).show();
            }

        }
    }
}
