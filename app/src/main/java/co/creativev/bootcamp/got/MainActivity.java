package co.creativev.bootcamp.got;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static class GoTCharacter {
        public final String name;
        public final int resId;

        public GoTCharacter(String name, int resId) {
            this.name = name;
            this.resId = resId;
        }
    }

    public static final GoTCharacter[] GOT_CHARACTERS = new GoTCharacter[]
            {
                    new GoTCharacter("Arya Stark", R.drawable.arya),
                    new GoTCharacter("Bran Stark", R.drawable.bran),
                    new GoTCharacter("Brienne Tarth", R.drawable.brienne),
                    new GoTCharacter("Catelyn Stark", R.drawable.catelyn),
                    new GoTCharacter("Cercei Lannister", R.drawable.cercei),
                    new GoTCharacter("Daenerys Targaryen", R.drawable.daenerys),
                    new GoTCharacter("Davos Seaworth", R.drawable.davos),
                    new GoTCharacter("Eddard Stark", R.drawable.eddard),
                    new GoTCharacter("Hodor", R.drawable.hodor),
                    new GoTCharacter("Jaime Lannister", R.drawable.jaime),
                    new GoTCharacter("Jaqen Hagar", R.drawable.jaqen),
                    new GoTCharacter("Joffrey Baratheon", R.drawable.joffrey),
                    new GoTCharacter("Jon Snow", R.drawable.jon),
                    new GoTCharacter("Khal Drogo", R.drawable.khal),
                    new GoTCharacter("Mance Rayder", R.drawable.mance),
                    new GoTCharacter("Melisandre", R.drawable.melisandre),
                    new GoTCharacter("Petyr Baelish", R.drawable.petyr),
                    new GoTCharacter("Podrick Payne", R.drawable.podrick),
                    new GoTCharacter("Pycelle", R.drawable.pycelle),
                    new GoTCharacter("Ramsay Bolton", R.drawable.ramsay),
                    new GoTCharacter("Renly Baratheon", R.drawable.renly),
                    new GoTCharacter("Robb Stark", R.drawable.robb),
                    new GoTCharacter("Robert Baratheon", R.drawable.robert),
                    new GoTCharacter("Roose Bolton", R.drawable.roose),
                    new GoTCharacter("Sansa Stark", R.drawable.sansa),
                    new GoTCharacter("Stannis Baratheon", R.drawable.stannis),
                    new GoTCharacter("Tyrion Lannister", R.drawable.tyrion),
                    new GoTCharacter("Tywin Lannister", R.drawable.tywin),
                    new GoTCharacter("Varys", R.drawable.varys)
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ListView) findViewById(R.id.list)).setAdapter(new GoTAdapter(this, GOT_CHARACTERS));
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
                ImageView image = (ImageView) convertView.findViewById(R.id.image);
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
