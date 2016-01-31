package co.creativev.bootcamp.got;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddCharacterPresenterTest {
    @Mock
    AddCharacterView mockView;
    @Mock
    DatabaseHelper databaseHelper;

    private AddCharacterPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new AddCharacterPresenter(mockView, databaseHelper);
    }

    @Test
    public void testShouldShowErrorIfNameNotProvided() throws Exception {
        presenter.addCharacter("", "file://tmp.jpg", 1);
        verify(mockView).showNameError();
    }

    @Test
    public void testShouldShowErrorIfImageNotProvided() throws Exception {
        presenter.addCharacter("Some Name", null, 1);
        verify(mockView).showImageError();
    }

    @Test
    public void testShouldShowErrorIfHouseNotProvided() throws Exception {
        presenter.addCharacter("Some Name", "file://tmp.jpg", -1);
        verify(mockView).showHouseError();
    }

    @Test
    public void testShouldCallDatabaseHelperOnSuccess() throws Exception {
        String imagePath = "file://tmp.jpg";
        String name = "Some Name";
        String firstName = "Some";
        String lastName = "Name";
        when(databaseHelper.insert(Matchers.<GoTCharacter>any())).thenReturn(1l);
        presenter.addCharacter(name, imagePath, R.id.radio_stark);
        verify(mockView).onAddCharacterSuccess();
        verify(databaseHelper).insert(eq(new GoTCharacter(firstName, lastName, imagePath, true, "New", R.drawable.stark, "Lorem", imagePath)));
    }

    @Test
    public void testShouldShowDatabaseErrorOnInsertError() throws Exception {
        String imagePath = "file://tmp.jpg";
        String name = "Some Name";
        when(databaseHelper.insert(Matchers.<GoTCharacter>any())).thenReturn(-1l);
        presenter.addCharacter(name, imagePath, R.id.radio_stark);
        verify(mockView).showDatabaseError();
    }
}