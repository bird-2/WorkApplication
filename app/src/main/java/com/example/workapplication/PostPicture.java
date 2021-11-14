package com.example.workapplication;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.workapplication.ui.WelcomeActivity;

public class PostPicture extends AppCompatActivity {
    private Button choose;
    private Button submit;
    private EditText editText;
    private String picPath;
    private String words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        choose = findViewById(R.id.bnt_choosePic);
        submit = findViewById(R.id.bnt_submit);
        editText = findViewById(R.id.et_context);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 6);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                words = editText.getText().toString();
                Intent intent = getIntent();
                intent.putExtra("path", picPath);
                intent.putExtra("works", words);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 6 && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                final String picturePath = cursor.getString(columnIndex);
                cursor.close();
                picPath = picturePath;

                //---------------------妹妹完成--------------------------------
                //将获取的图片显示出来(图片为地址)
            }
        }
    }
}
