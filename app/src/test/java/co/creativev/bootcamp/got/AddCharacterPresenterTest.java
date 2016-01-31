package co.creativev.bootcamp.got;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddCharacterPresenterTest {
    @Mock
    AddCharacterView mockView;
    private AddCharacterPresenter presenter;

    @Before
    public void setUp() throws Exception {
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