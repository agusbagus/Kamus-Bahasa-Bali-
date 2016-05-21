package com.example.user.kamusbahasa;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    private SQLiteDatabase db = null;
    private Cursor kamusCursor = null;
    private EditText txtIndonesia;
    private EditText txtBali;
    private EditText txtKeterangan;
    private DataKamus dataKamus = null;
    public static final String INDONESIA = "indonesia";
    public static final String BALI = "bali";
    public static final String KETERANGAN = "keterangan";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataKamus = new DataKamus(this);
        db = dataKamus.getWritableDatabase();
        dataKamus.createTable(db);
        dataKamus.generateData(db);

        setContentView(R.layout.activity_main);
        txtIndonesia = (EditText) findViewById(R.id.txtIndonesia);
        txtBali = (EditText) findViewById(R.id.txtBali);
        txtKeterangan = (EditText) findViewById(R.id.txtKeterangan);

    }

    public void getTerjemahan(View view) {
        String result = "";
        String result2 = "";

        String baliword = txtIndonesia.getText().toString();
        kamusCursor = db.rawQuery("SELECT ID, INDONESIA, BALI, KETERANGAN"
                + "FROM kamus where INDONESIA='" + baliword
                + "' ORDER BY INDONESIA", null);

        if (kamusCursor.moveToFirst()) {
            result = kamusCursor.getString(2);
            for (; !kamusCursor.isAfterLast(); kamusCursor.moveToNext()) {
                result = kamusCursor.getString(2);
            }
        }

        if (kamusCursor.moveToFirst()) {
            result2 = kamusCursor.getString(3);
            for (; !kamusCursor.isAfterLast(); kamusCursor.moveToNext()) {
                result2 = kamusCursor.getString(3);
            }
        }

        if (result.equals("")) {
            result = "KATA TIDAK DITEMUKAN";
        }
        if (result2.equals("")) {
            result = "KATA TIDAK DITEMUKAN";
        }
        txtBali.setText(result);
        txtKeterangan.setText(result2);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        kamusCursor.close();
        db.close();
    }

}
