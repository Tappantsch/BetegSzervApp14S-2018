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

public class Szerv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szerv);
        final EditText sanya = (EditText)findViewById(R.id.ujszerv) ;

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Komponensektől kérdezzük le a beszúrandó értékeket

                final  String tipus = sanya.getText().toString();

                // ...

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        DbHelper helper = DbHelper.getInstance(Szerv.this);
                        SQLiteDatabase db = helper.getWritableDatabase();

                        // TODO: Adatbázisba szúrjuk be
                        ContentValues cv = new ContentValues();
                        cv.put("tipus",tipus);

                        db.insert("szerv",null,cv);
                        db.close();
                      Szerv.this.finish();
                    }
                });
            }
        });
    }
}
