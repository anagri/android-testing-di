package co.creativev.bootcamp.got;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        MainActivity.GoTCharacter gotCharacter = MainActivity.GOT_CHARACTERS[0];
        ((ImageView) findViewById(R.id.image_character)).setImageResource(gotCharacter.fullResId);
        ((TextView) findViewById(R.id.text_name)).setText(gotCharacter.name);
        ((ImageView) findViewById(R.id.image_house)).setImageResource(gotCharacter.houseResId);
        ((TextView) findViewById(R.id.text_house_name)).setText(gotCharacter.house);
        ((TextView) findViewById(R.id.text_character_story)).setText(gotCharacter.description);
    }
}
