package co.creativev.bootcamp.got;

import android.os.Parcel;
import android.os.Parcelable;

public class GoTCharacter implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.resId);
        dest.writeByte(alive ? (byte) 1 : (byte) 0);
        dest.writeInt(this.fullResId);
        dest.writeInt(this.houseResId);
        dest.writeString(this.house);
        dest.writeString(this.description);
    }

    protected GoTCharacter(Parcel in) {
        this.name = in.readString();
        this.resId = in.readInt();
        this.alive = in.readByte() != 0;
        this.fullResId = in.readInt();
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
