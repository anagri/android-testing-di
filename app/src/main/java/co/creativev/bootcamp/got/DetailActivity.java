package co.creativev.bootcamp.got;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_CHARACTER = "character";

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        GoTCharacter goTCharacter = getIntent().getParcelableExtra(EXTRA_CHARACTER);
        setTitle(goTCharacter.getFirstName() + " " + goTCharacter.getLastName());
        GoTCharacterViewModel goTCharacterViewModel = new GoTCharacterViewModel(goTCharacter);
        setTitle(goTCharacterViewModel.getTitle());
        Picasso.with(this)
                .load(Uri.parse(goTCharacter.getFullUrl()))
                .placeholder(R.drawable.profile_placeholder_full)
                .error(R.drawable.profile_placeholder_error_full)
                .into((ImageView) findViewById(R.id.image_character));
        ((ImageView) findViewById(R.id.image_house)).setImageResource(goTCharacter.getHouseResId());
        ((TextView) findViewById(R.id.text_house_name)).setText(goTCharacter.getHouse());
        TextView characterDetails = (TextView) findViewById(R.id.text_character_story);
        characterDetails.setText(goTCharacter.getDescription());
        int color = goTCharacterViewModel.getDescriptionColor();
        characterDetails.setTextColor(color);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

