package co.creativev.bootcamp.got;

import android.graphics.Color;
import android.support.annotation.NonNull;

public class GoTCharacterViewModel {
    private final GoTCharacter goTCharacter;

    public GoTCharacterViewModel(GoTCharacter goTCharacter) {
        this.goTCharacter = goTCharacter;
    }

    @NonNull
    public String getTitle() {
        return goTCharacter.getFirstName() + " " + goTCharacter.getLastName();
    }

    public int getDescriptionColor() {
        return goTCharacter.isAlive() ? Color.GREEN : Color.RED;
    }

    public int getHouseResId() {
        return goTCharacter.getHouseResId();
    }

    @SuppressWarnings({"unused", "databinding"})
    public String getHouse() {
        return goTCharacter.getHouse();
    }

    @SuppressWarnings({"unused", "databinding"})
    public String getDescription() {
        return goTCharacter.getDescription();
    }
}
