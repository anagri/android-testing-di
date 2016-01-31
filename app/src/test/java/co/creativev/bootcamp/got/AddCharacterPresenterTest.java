package co.creativev.bootcamp.got;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddCharacterPresenterTest {

    private AddCharacterView mockView;
    private AddCharacterPresenter presenter;

    @Before
    public void setUp() throws Exception {
        mockView = mock(AddCharacterView.class);
        presenter = new AddCharacterPresenter(mockView);
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
}