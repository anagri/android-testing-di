package co.creativev.bootcamp.got;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddCharacterActivity extends AppCompatActivity {

    public static final int CHOOSE_IMAGE = 100;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_character);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Add GoT Character");
        imageView = (ImageView) findViewById(R.id.image_character);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(galleryIntent, CHOOSE_IMAGE);
            }
        });
        final RadioGroup radioGroupHouse = (RadioGroup) findViewById(R.id.radio_group_house);
        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputCharacter = (EditText) findViewById(R.id.text_character_name);
                String name = inputCharacter.getText().toString();
                if (name.isEmpty()) {
                    inputCharacter.setError("Cannot be empty");
                    return;
                }
                String imagePath = (String) imageView.getTag();
                if (imagePath == null) {
                    new AlertDialog.Builder(AddCharacterActivity.this)
                            .setTitle("Error")
                            .setMessage("Image is not selected")
                            .setCancelable(true)
                            .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                    return;
                }
                int selectedHouse = radioGroupHouse.getCheckedRadioButtonId();
                if (selectedHouse == -1) {
                    new AlertDialog.Builder(AddCharacterActivity.this)
                            .setTitle("Error")
                            .setMessage("House is not selected")
                            .setCancelable(true)
                            .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                    return;
                }
                int houseResId = getHouseResId(selectedHouse);
                DatabaseHelper databaseHelper = DatabaseHelper.getDatabaseHelper(AddCharacterActivity.this);
                String[] names = name.split(" ");
                String firstName = names[0];
                String lastName;
                if (names.length > 1) {
                    lastName = name.substring(name.indexOf(" "));
                } else {
                    lastName = "Unknown";
                }
                long id = databaseHelper.insert(new GoTCharacter(firstName, lastName, imagePath, true, "New", houseResId, "Lorem", imagePath));
                if (id == -1) {
                    Log.e(MainActivity.LOG_TAG, "Error while inserting data");
                } else {
                    Toast.makeText(AddCharacterActivity.this, "Inserted new character", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public int getHouseResId(int radioButtonId) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Log.d(MainActivity.LOG_TAG, "Received " + data);

            Uri selectedImage = data.getData();
            String picturePath;
            String tag;
            if ("content".equals(selectedImage.getScheme())) {
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                picturePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
                cursor.close();
                tag = "file://" + picturePath;
            } else {
                picturePath = selectedImage.getPath();
                tag = "file:///" + picturePath;
            }
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            imageView.setTag(tag);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
