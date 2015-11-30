package co.creativev.bootcamp.got;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String GOT_DB = "got.db";
    public static final int VERSION = 2;
    public static final String SERVER_URL = "https://s3-ap-southeast-1.amazonaws.com/android-bootcamp-assets/";

    public static final GoTCharacter[] GOT_CHARACTERS =
            {
                    new GoTCharacter("Arya Stark", SERVER_URL + "arya.jpg", SERVER_URL + "arya_full.jpg", true, "Stark", SERVER_URL + "stark.jpg", "Arya Stark is the third child and second daughter of Lord Eddard Stark and Lady Catelyn Tully"),
                    new GoTCharacter("Bran Stark", SERVER_URL + "bran.jpg", SERVER_URL + "bran_full.jpg", true, "Stark", SERVER_URL + "stark.jpg", "Brandon Stark, typically called Bran, is the second son of Lord Eddard Stark and Lady Catelyn Tully."),
                    new GoTCharacter("Brienne Tarth", SERVER_URL + "brienne.jpg", SERVER_URL + "brienne_full.jpg", true, "Stark", SERVER_URL + "stark.jpg", "Brienne is sometimes called the Maid of Tarth and mocked as Brienne the Beauty."),
                    new GoTCharacter("Catelyn Stark", SERVER_URL + "catelyn.jpg", SERVER_URL + "catelyn_full.jpg", false, "Stark", SERVER_URL + "stark.jpg", "Lady Catelyn Stark, also called Catelyn Tully, is the wife of Lord Eddard Stark and Lady of Winterfell."),
                    new GoTCharacter("Cercei Lannister", SERVER_URL + "cercei.jpg", SERVER_URL + "cercei_full.jpg", true, "Lannister", SERVER_URL + "lannister.jpg", "Cersei Lannister is the eldest child of Tywin and Joanna Lannister by mere moments, and the twin sister of Jaime Lannister."),
                    new GoTCharacter("Daenerys Targaryen", SERVER_URL + "daenerys.jpg", SERVER_URL + "daenerys_full.jpg", true, "Targaryen", SERVER_URL + "targaryen.jpg", "Princess Daenerys Targaryen, known as Daenerys Stormborn and Dany, is one of the last confirmed members of House Targaryen"),
                    new GoTCharacter("Davos Seaworth", SERVER_URL + "davos.jpg", SERVER_URL + "davos_full.jpg", true, "Baratheon", SERVER_URL + "baratheon.jpg", "Ser Davos Seaworth, commonly called the Onion Knight, is the head of House Seaworth. He was once a smuggler."),
                    new GoTCharacter("Eddard Stark", SERVER_URL + "eddard.jpg", SERVER_URL + "eddard_full.jpg", false, "Stark", SERVER_URL + "stark.jpg", "Eddard Stark, also affectionately called 'Ned', is the head of House Stark, Lord of Winterfell, and Warden of the North."),
                    new GoTCharacter("Hodor", SERVER_URL + "hodor.jpg", SERVER_URL + "hodor_full.jpg", true, "Stark", SERVER_URL + "stark.jpg", "Hodor, the giant, simple-minded stableboy of Winterfell."),
                    new GoTCharacter("Jaime Lannister", SERVER_URL + "jaime.jpg", SERVER_URL + "jaime_full.jpg", true, "Lannister", SERVER_URL + "lannister.jpg", "Ser Jaime Lannister, known as the Kingslayer, is a knight from House Lannister. He is the twin brother of Queen Cersei Lannister."),
                    new GoTCharacter("Jaqen Hagar", SERVER_URL + "jaqen.jpg", SERVER_URL + "jaqen_full.jpg", true, "Faceless Men", SERVER_URL + "faceless.jpg", "Jaqen Hagar is the name of a sly Lorathi criminal who meets Arya Stark on her way to the Wall."),
                    new GoTCharacter("Joffrey Baratheon", SERVER_URL + "joffrey.jpg", SERVER_URL + "joffrey_full.jpg", false, "Baratheon", SERVER_URL + "baratheon.jpg", "Prince Joffrey Baratheon is known to the Seven Kingdoms as the eldest son and heir of King Robert I Baratheon and Queen Cersei Lannister."),
                    new GoTCharacter("Jon Snow", SERVER_URL + "jon.jpg", SERVER_URL + "jon_full.jpg", false, "Stark", SERVER_URL + "stark.jpg", "Jon Snow doesn't know anything"),
                    new GoTCharacter("Khal Drogo", SERVER_URL + "khal.jpg", SERVER_URL + "khal_full.jpg", false, "Dothraki", SERVER_URL + "dothraki.jpg", "Drogo is a powerful khal or warlord of the fearsome Dothraki nomads."),
                    new GoTCharacter("Melisandre", SERVER_URL + "melisandre.jpg", SERVER_URL + "melisandre_full.jpg", true, "Baratheon", SERVER_URL + "baratheon.jpg", "Melisandre is a priestess of R'hllor and a shadowbinder, hailing from the eastern city of Asshai. She has joined the entourage of Stannis Baratheon."),
                    new GoTCharacter("Petyr Baelish", SERVER_URL + "petyr.jpg", SERVER_URL + "petyr_full.jpg", true, "Lannister", SERVER_URL + "lannister.jpg", "Petyr Baelish, sometimes called Littlefinger, is the head of House Baelish of the Fingers. Petyr wears a mockingbird as his personal crest instead of House Baelish's sigil, a titan's head."),
                    new GoTCharacter("Podrick Payne", SERVER_URL + "podrick.jpg", SERVER_URL + "podrick_full.jpg", true, "Lannister", SERVER_URL + "lannister.jpg", "Podrick Payne is the squire of Tyrion Lannister. He is from a cadet branch of House Payne."),
                    new GoTCharacter("Grand Maester Pycelle", SERVER_URL + "pycelle.jpg", SERVER_URL + "pycelle_full.jpg", true, "Lannister", SERVER_URL + "lannister.jpg", "Pycelle is a Grand Maester of the Citadel who has served in King's Landing and on the small council for over forty years."),
                    new GoTCharacter("Ramsay Bolton", SERVER_URL + "ramsay.jpg", SERVER_URL + "ramsay_full.jpg", true, "Bolton", SERVER_URL + "bolton.jpg", "Ramsay Bolton (formerly Ramsay Snow) is the legitimized bastard son of Lord Roose Bolton. He is known as the Bastard of Bolton and the Bastard of the Dreadfort."),
                    new GoTCharacter("Renly Baratheon", SERVER_URL + "renly.jpg", SERVER_URL + "renly_full.jpg", false, "Baratheon", SERVER_URL + "baratheon.jpg", "Renly Baratheon is the Lord of Storm's End and Lord Paramount of the Stormlands. The younger brother of King Robert I and Lord Stannis."),
                    new GoTCharacter("Robb Stark", SERVER_URL + "robb.jpg", SERVER_URL + "robb_full.jpg", false, "Stark", SERVER_URL + "stark.jpg", "Robb Stark is the eldest son of Eddard Stark and Catelyn Tully and is the heir of House Stark to Winterfell and the north."),
                    new GoTCharacter("Robert Baratheon", SERVER_URL + "robert.jpg", SERVER_URL + "robert_full.jpg", false, "Baratheon", SERVER_URL + "baratheon.jpg", "King Robert I Baratheon is the Lord of the Seven Kingdoms of Westeros and the head of House Baratheon of King's Landing"),
                    new GoTCharacter("Roose Bolton", SERVER_URL + "roose.jpg", SERVER_URL + "roose_full.jpg", true, "Bolton", SERVER_URL + "bolton.jpg", "Roose Bolton is the Lord of the Dreadfort and head of House Bolton."),
                    new GoTCharacter("Sansa Stark", SERVER_URL + "sansa.jpg", SERVER_URL + "sansa_full.jpg", true, "Stark", SERVER_URL + "stark.jpg", "Arya Stark is the third child and second daughter of Lord Eddard Stark and Lady Catelyn Tully."),
                    new GoTCharacter("Stannis Baratheon", SERVER_URL + "stannis.jpg", SERVER_URL + "stannis_full.jpg", false, "Baratheon", SERVER_URL + "baratheon.jpg", "Stannis Baratheon is the head of House Baratheon of Dragonstone and the Lord of Dragonstone."),
                    new GoTCharacter("Tyrion Lannister", SERVER_URL + "tyrion.jpg", SERVER_URL + "tyrion_full.jpg", true, "Lannister", SERVER_URL + "lannister.jpg", "Tyrion is a dwarf, with stubby legs, a jutting forehead, mismatched eyes of green and black, and a mixture of pale blond and black hair."),
                    new GoTCharacter("Tywin Lannister", SERVER_URL + "tywin.jpg", SERVER_URL + "tywin_full.jpg", false, "Lannister", SERVER_URL + "lannister.jpg", "Tywin Lannister is Lord of Casterly Rock, Shield of Lannisport and Warden of the West. The head of House Lannister, Tywin is one of the most powerful lords in Westeros."),
                    new GoTCharacter("Varys", SERVER_URL + "varys.jpg", SERVER_URL + "varys_full.jpg", true, "Lannister", SERVER_URL + "lannister.jpg", "Varys, called the Spider, is an enigmatic member of the small council and the master of whisperers, or spymaster, for the Iron Throne of the Seven Kingdoms.")
            };
    public static final String GOT_TABLE = "got_characters";
    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context, GOT_DB, null, VERSION);
    }

    public static DatabaseHelper getDatabaseHelper(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        db.execSQL("CREATE TABLE " + GOT_TABLE + "(" +
                GoTCharacter._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                GoTCharacter.NAME + " TEXT," +
                GoTCharacter.THUMB_URL + " TEXT," +
                GoTCharacter.FULL_URL + " TEXT," +
                GoTCharacter.HOUSE + " TEXT," +
                GoTCharacter.HOUSE_URL + " TEXT," +
                GoTCharacter.DESCRIPTION + " TEXT);");
        try {
            for (GoTCharacter gotCharacter : GOT_CHARACTERS) {
                ContentValues values = new ContentValues();
                values.put(GoTCharacter.NAME, gotCharacter.name);
                values.put(GoTCharacter.THUMB_URL, gotCharacter.thumbUrl);
                values.put(GoTCharacter.FULL_URL, gotCharacter.fullUrl);
                values.put(GoTCharacter.HOUSE, gotCharacter.house);
                values.put(GoTCharacter.HOUSE_URL, gotCharacter.houseUrl);
                values.put(GoTCharacter.DESCRIPTION, gotCharacter.description);
                db.insert(GOT_TABLE, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GOT_TABLE + ";");
        onCreate(db);
    }

    public int getCount() {
        return (int) DatabaseUtils.longForQuery(getReadableDatabase(), "SELECT COUNT(*) from " + GOT_TABLE, null);
    }

    public Cursor getCharacterCursor() {
        return getReadableDatabase().query(GOT_TABLE, GoTCharacter.ALL_COLS, null, null, null, null, null);
    }
}
