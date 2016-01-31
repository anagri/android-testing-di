package co.creativev.bootcamp.got;

public class AddCharacterPresenter {
    private final AddCharacterView view;
    private DatabaseHelper databaseHelper;

    public AddCharacterPresenter(AddCharacterView view, DatabaseHelper databaseHelper) {
        this.view = view;
        this.databaseHelper = databaseHelper;
    }

    public void addCharacter(String name, String imagePath, int selectedHouse) {
        if (validate(name, imagePath, selectedHouse)) return;
        addCharacterToDb(name, imagePath, selectedHouse);
    }

    private boolean validate(String name, String imagePath, int selectedHouse) {
        if (name.isEmpty()) {
            view.showNameError();
            return true;
        }
        if (imagePath == null) {
            view.showImageError();
            return true;
        }
        if (selectedHouse == -1) {
            view.showHouseError();
            return true;
        }
        return false;
    }

    private void addCharacterToDb(String name, String imagePath, int selectedHouse) {
        int houseResId = getHouseResId(selectedHouse);
        String[] names = name.split(" ");
        String firstName = names[0];
        String lastName;
        if (names.length > 1) {
            lastName = name.substring(name.indexOf(" ") + 1);
        } else {
            lastName = "Unknown";
        }
        long id = databaseHelper.insert(new GoTCharacter(firstName, lastName, imagePath, true, "New", houseResId, "Lorem", imagePath));
        if (id == -1) {
            view.showDatabaseError();
        } else {
            view.onAddCharacterSuccess();
        }
    }

    private int getHouseResId(int radioButtonId) {
        switch (radioButtonId) {
            case R.id.radio_baratheon:
                return R.drawable.baratheon;
            case R.id.radio_lannister:
                return R.drawable.lannister;
            case R.id.radio_stark:
                return R.drawable.stark;
            case R.id.radio_targaryen:
                return R.drawable.targaryen;
            default:
                throw new IllegalArgumentException("No icon found for radio button " + radioButtonId);
        }
    }
}
