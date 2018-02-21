package hu.petrik.betegszervapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class UjBetegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uj_beteg);
        final EditText sanya = (EditText)findViewById(R.id.ujbeteg_nev) ;
        final EditText geza = (EditText)findViewById(R.id.ujbeteg_tipus) ;
        final EditText bela = (EditText)findViewById(R.id.ujbeteg_taj) ;
        final Spinner petya = (Spinner) findViewById(R.id.ujbeteg_szerv);
        Button ujbeteg = (Button)findViewById(R.id.ujbeteg_gomb);
        ujbeteg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Komponensektől kérdezzük le a beszúrandó értékeket

                final  String nev = sanya.getText().toString();
                final String taj = bela.getText().toString();
                final  String tipus = geza.getText().toString();
                final String szerv = petya.getSelectedItem().toString();

                // ...

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        DbHelper helper = DbHelper.getInstance(UjBetegActivity.this);
                        SQLiteDatabase db = helper.getWritableDatabase();

                        // TODO: Adatbázisba szúrjuk be
                        ContentValues cv = new ContentValues();
                        cv.put("nev",nev);
                        cv.put("taj",taj);
                        cv.put("szerv",szerv);
                        cv.put("tipus",tipus);
                        cv.putNull("szerv_id");
                        db.insert("beteg",null,cv);
                        db.close();
                        UjBetegActivity.this.finish();
                    }
                });
            }
        });
    }
}
