package co.creativev.bootcamp.got;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class AddCharacterPresenterTest {
    @Test
    public void testShouldInsertValidCharacterIntoDatabase() throws Exception {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        DatabaseHelper databaseHelper = DatabaseHelper.getDatabaseHelper(targetContext);
        databaseHelper.reset();

        AddCharacterView mockView = mock(AddCharacterView.class);
        AddCharacterPresenter addCharacterPresenter = new AddCharacterPresenter(mockView, databaseHelper);
        String imagePath = "file://test_user.jpg";
        addCharacterPresenter.addCharacter("Test User", imagePath, R.id.radio_stark);
        int count = databaseHelper.getCount();
        assertThat(count, is(29));
        Cursor cursor = databaseHelper.getCharacterCursor();
        cursor.moveToPosition(28);
        String firstName = cursor.getString(cursor.getColumnIndex(GoTCharacter.FIRST_NAME));
        String lastName = cursor.getString(cursor.getColumnIndex(GoTCharacter.LAST_NAME));
        String image = cursor.getString(cursor.getColumnIndex(GoTCharacter.FULL_URL));
        int houseId = cursor.getInt(cursor.getColumnIndex(GoTCharacter.HOUSE_RES_ID));
        cursor.close();
        assertThat(firstName, is("Test"));
        assertThat(lastName, is("User"));
        assertThat(image, is(imagePath));
        assertThat(houseId, is(R.drawable.stark));
        verify(mockView).onAddCharacterSuccess();
    }
}