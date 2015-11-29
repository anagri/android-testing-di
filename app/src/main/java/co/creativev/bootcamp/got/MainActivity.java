package co.creativev.bootcamp.got;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Log.d("GOT_APP", "Total rows in db " + databaseHelper.getCount());
        setContentView(R.layout.activity_main);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(new GoTAdapter(this, DatabaseHelper.GOT_CHARACTERS));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_CHARACTER, DatabaseHelper.GOT_CHARACTERS[position % DatabaseHelper.GOT_CHARACTERS.length]);
                startActivity(intent);
            }
        });
    }

    public static class GoTAdapter extends BaseAdapter {
        private final GoTCharacter[] characters;
        private final LayoutInflater inflater;

        public GoTAdapter(Context context, GoTCharacter[] characters) {
            this.characters = characters;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return characters.length * 100;
        }

        @Override
        public GoTCharacter getItem(int position) {
            return characters[position % characters.length];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_item_got, parent, false);
                TextView name = (TextView) convertView.findViewById(R.id.text);
                ImageView image = (ImageView) convertView.findViewById(R.id.image_character);
                convertView.setTag(new GotViewHolder(name, image));
            }
            GoTCharacter item = getItem(position);
            GotViewHolder tag = (GotViewHolder) convertView.getTag();
            tag.name.setText(item.name);
            tag.image.setImageResource(item.resId);
            return convertView;
        }
    }

    public static class GotViewHolder {
        private final TextView name;
        private final ImageView image;

        public GotViewHolder(TextView name, ImageView image) {
            this.name = name;
            this.image = image;
        }
    }
}
