package co.creativev.bootcamp.got;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

public class GoTCharacter implements Parcelable, BaseColumns {
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String THUMB_URL = "thumb_url";
    public static final String FULL_URL = "full_url";
    public static final String HOUSE = "house";
    public static final String ALIVE = "alive";
    public static final String HOUSE_RES_ID = "house_res_id";
    public static final String DESCRIPTION = "description";
    public static final String[] ALL_COLS = {_ID, FIRST_NAME, LAST_NAME, ALIVE, THUMB_URL, FULL_URL, HOUSE, HOUSE_RES_ID, DESCRIPTION};

    public final int id;
    public final String firstName;
    public final String lastName;
    public final String thumbUrl;
    public final boolean alive;
    public final String fullUrl;
    public final int houseResId;
    public final String house;
    public final String description;

    public GoTCharacter(String firstName, String lastName, String fullUrl, boolean alive, String house, int houseResId, String description, String thumbUrl) {
        this(0, firstName, lastName, thumbUrl, fullUrl, alive, house, houseResId, description);
    }

    public GoTCharacter(int id, String firstName, String lastName, String thumbUrl, String fullUrl, boolean alive, String house, int houseResId, String description) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.thumbUrl = thumbUrl;
        this.alive = alive;
        this.fullUrl = fullUrl;
        this.houseResId = houseResId;
        this.house = house;
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.thumbUrl);
        dest.writeByte(alive ? (byte) 1 : (byte) 0);
        dest.writeString(this.fullUrl);
        dest.writeInt(this.houseResId);
        dest.writeString(this.house);
        dest.writeString(this.description);
    }

    protected GoTCharacter(Parcel in) {
        this.id = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.thumbUrl = in.readString();
        this.alive = in.readByte() != 0;
        this.fullUrl = in.readString();
        this.houseResId = in.readInt();
        this.house = in.readString();
        this.description = in.readString();
    }

    public static final Creator<GoTCharacter> CREATOR = new Creator<GoTCharacter>() {
        public GoTCharacter createFromParcel(Parcel source) {
            return new GoTCharacter(source);
        }

        public GoTCharacter[] newArray(int size) {
            return new GoTCharacter[size];
        }
    };

}
