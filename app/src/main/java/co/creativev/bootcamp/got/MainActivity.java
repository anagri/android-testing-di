package co.creativev.bootcamp.got;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public static final String[] GOT_CHARACTERS = new String[]{
            "Arya Stark",
            "Bran Stark",
            "Brienne Tarth",
            "Catelyn Stark",
            "Cercei Lannister",
            "Daenerys Targaryen",
            "Davos Seaworth",
            "Eddard Stark",
            "Hodor",
            "Jaime Lannister",
            "Jaqen Hagar",
            "Joffrey Baratheon",
            "Jon Snow",
            "Khal Drogo",
            "Mance Rayder",
            "Melisandre",
            "Petyr Baelish",
            "Podrick Payne",
            "Pycelle",
            "Ramsay Bolton",
            "Renly Baratheon",
            "Robb Stark",
            "Robert Baratheon",
            "Roose Bolton",
            "Sansa Stark",
            "Stannis Baratheon",
            "Tyrion Lannister",
            "Tywin Lannister",
            "Varys"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ListView) findViewById(R.id.list)).setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, GOT_CHARACTERS));
    }
}
