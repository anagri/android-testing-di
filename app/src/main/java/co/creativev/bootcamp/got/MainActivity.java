package co.creativev.bootcamp.got;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static class GoTCharacter {
        public final String name;
        public final int resId;
        public final boolean alive;
        public final int fullResId;
        public final int houseResId;
        public final String house;
        public final String description;

        public GoTCharacter(String name, int resId, int fullResId, boolean alive, String house, int houseResId, String description) {
            this.name = name;
            this.resId = resId;
            this.alive = alive;
            this.fullResId = fullResId;
            this.houseResId = houseResId;
            this.house = house;
            this.description = description;
        }
    }

    public static final GoTCharacter[] GOT_CHARACTERS =
            {
                    new GoTCharacter("Arya Stark", R.drawable.arya, R.drawable.arya_full, true, "Stark", R.drawable.stark, "Arya Stark is the third child and second daughter of Lord Eddard Stark and Lady Catelyn Tully"),
                    new GoTCharacter("Bran Stark", R.drawable.bran, R.drawable.bran_full, true, "Stark", R.drawable.stark, "Brandon Stark, typically called Bran, is the second son of Lord Eddard Stark and Lady Catelyn Tully."),
                    new GoTCharacter("Brienne Tarth", R.drawable.brienne, R.drawable.brienne_full, true, "Stark", R.drawable.stark, "Brienne is sometimes called the Maid of Tarth and mocked as Brienne the Beauty."),
                    new GoTCharacter("Catelyn Stark", R.drawable.catelyn, R.drawable.catelyn_full, false, "Stark", R.drawable.stark, "Lady Catelyn Stark, also called Catelyn Tully, is the wife of Lord Eddard Stark and Lady of Winterfell."),
                    new GoTCharacter("Cercei Lannister", R.drawable.cercei, R.drawable.cercei_full, true, "Lannister", R.drawable.lannister, "Cersei Lannister is the eldest child of Tywin and Joanna Lannister by mere moments, and the twin sister of Jaime Lannister."),
                    new GoTCharacter("Daenerys Targaryen", R.drawable.daenerys, R.drawable.daenerys_full, true, "Targaryen", R.drawable.targaryen, "Princess Daenerys Targaryen, known as Daenerys Stormborn and Dany, is one of the last confirmed members of House Targaryen"),
                    new GoTCharacter("Davos Seaworth", R.drawable.davos, R.drawable.davos_full, true, "Baratheon", R.drawable.baratheon, "Ser Davos Seaworth, commonly called the Onion Knight, is the head of House Seaworth. He was once a smuggler."),
                    new GoTCharacter("Eddard Stark", R.drawable.eddard, R.drawable.eddard_full, false, "Stark", R.drawable.stark, "Eddard Stark, also affectionately called 'Ned', is the head of House Stark, Lord of Winterfell, and Warden of the North."),
                    new GoTCharacter("Hodor", R.drawable.hodor, R.drawable.hodor_full, true, "Stark", R.drawable.stark, "Hodor, the giant, simple-minded stableboy of Winterfell."),
                    new GoTCharacter("Jaime Lannister", R.drawable.jaime, R.drawable.jaime_full, true, "Lannister", R.drawable.lannister, "Ser Jaime Lannister, known as the Kingslayer, is a knight from House Lannister. He is the twin brother of Queen Cersei Lannister."),
                    new GoTCharacter("Jaqen Hagar", R.drawable.jaqen, R.drawable.jaqen_full, true, "Faceless Men", R.drawable.faceless, "Jaqen Hagar is the name of a sly Lorathi criminal who meets Arya Stark on her way to the Wall."),
                    new GoTCharacter("Joffrey Baratheon", R.drawable.joffrey, R.drawable.joffrey_full, false, "Baratheon", R.drawable.baratheon, "Prince Joffrey Baratheon is known to the Seven Kingdoms as the eldest son and heir of King Robert I Baratheon and Queen Cersei Lannister."),
                    new GoTCharacter("Jon Snow", R.drawable.jon, R.drawable.jon_full, false, "Stark", R.drawable.stark, "Jon Snow doesn't know anything"),
                    new GoTCharacter("Khal Drogo", R.drawable.khal, R.drawable.khal_full, false, "Dothraki", R.drawable.dothraki, "Drogo is a powerful khal or warlord of the fearsome Dothraki nomads."),
                    new GoTCharacter("Melisandre", R.drawable.melisandre, R.drawable.melisandre_full, true, "Baratheon", R.drawable.baratheon, "Melisandre is a priestess of R'hllor and a shadowbinder, hailing from the eastern city of Asshai. She has joined the entourage of Stannis Baratheon."),
                    new GoTCharacter("Petyr Baelish", R.drawable.petyr, R.drawable.petyr_full, true, "Lannister", R.drawable.lannister, "Petyr Baelish, sometimes called Littlefinger, is the head of House Baelish of the Fingers. Petyr wears a mockingbird as his personal crest instead of House Baelish's sigil, a titan's head."),
                    new GoTCharacter("Podrick Payne", R.drawable.podrick, R.drawable.podrick_full, true, "Lannister", R.drawable.lannister, "Podrick Payne is the squire of Tyrion Lannister. He is from a cadet branch of House Payne."),
                    new GoTCharacter("Grand Maester Pycelle", R.drawable.pycelle, R.drawable.pycelle_full, true, "Lannister", R.drawable.lannister, "Pycelle is a Grand Maester of the Citadel who has served in King's Landing and on the small council for over forty years."),
                    new GoTCharacter("Ramsay Bolton", R.drawable.ramsay, R.drawable.ramsay_full, true, "Bolton", R.drawable.bolton, "Ramsay Bolton (formerly Ramsay Snow) is the legitimized bastard son of Lord Roose Bolton. He is known as the Bastard of Bolton and the Bastard of the Dreadfort."),
                    new GoTCharacter("Renly Baratheon", R.drawable.renly, R.drawable.renly_full, false, "Baratheon", R.drawable.baratheon, "Renly Baratheon is the Lord of Storm's End and Lord Paramount of the Stormlands. The younger brother of King Robert I and Lord Stannis."),
                    new GoTCharacter("Robb Stark", R.drawable.robb, R.drawable.robb_full, false, "Stark", R.drawable.stark, "Robb Stark is the eldest son of Eddard Stark and Catelyn Tully and is the heir of House Stark to Winterfell and the north."),
                    new GoTCharacter("Robert Baratheon", R.drawable.robert, R.drawable.robert_full, false, "Baratheon", R.drawable.baratheon, "King Robert I Baratheon is the Lord of the Seven Kingdoms of Westeros and the head of House Baratheon of King's Landing"),
                    new GoTCharacter("Roose Bolton", R.drawable.roose, R.drawable.roose_full, true, "Bolton", R.drawable.bolton, "Roose Bolton is the Lord of the Dreadfort and head of House Bolton."),
                    new GoTCharacter("Sansa Stark", R.drawable.sansa, R.drawable.sansa_full, true, "Stark", R.drawable.stark, "Arya Stark is the third child and second daughter of Lord Eddard Stark and Lady Catelyn Tully."),
                    new GoTCharacter("Stannis Baratheon", R.drawable.stannis, R.drawable.stannis_full, false, "Baratheon", R.drawable.baratheon, "Stannis Baratheon is the head of House Baratheon of Dragonstone and the Lord of Dragonstone."),
                    new GoTCharacter("Tyrion Lannister", R.drawable.tyrion, R.drawable.tyrion_full, true, "Lannister", R.drawable.lannister, "Tyrion is a dwarf, with stubby legs, a jutting forehead, mismatched eyes of green and black, and a mixture of pale blond and black hair."),
                    new GoTCharacter("Tywin Lannister", R.drawable.tywin, R.drawable.tywin_full, false, "Lannister", R.drawable.lannister, "Tywin Lannister is Lord of Casterly Rock, Shield of Lannisport and Warden of the West. The head of House Lannister, Tywin is one of the most powerful lords in Westeros."),
                    new GoTCharacter("Varys", R.drawable.varys, R.drawable.varys_full, true, "Lannister", R.drawable.lannister, "Varys, called the Spider, is an enigmatic member of the small council and the master of whisperers, or spymaster, for the Iron Throne of the Seven Kingdoms.")
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(new GoTAdapter(this, GOT_CHARACTERS));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_INDEX, position % GOT_CHARACTERS.length);
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
