package co.creativev.bootcamp.got;

import android.graphics.Color;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GoTCharacterViewModelTest {
    @Test
    public void testShouldConcatenateNameAsFirstAndLastNameWithSpace() throws Exception {
        GoTCharacterViewModel viewModel = new GoTCharacterViewModel(new GoTCharacter("Test", "User", null, false, null, 0, null, null));
        assertThat(viewModel.getTitle(), is("Test User"));
    }

    @Test
    public void testShouldHaveGreenColorForAliveCharacter() throws Exception {
        GoTCharacterViewModel viewModel = new GoTCharacterViewModel(new GoTCharacter(null, null, null, true, null, 0, null, null));
        assertThat(viewModel.getDescriptionColor(), is(Color.GREEN));
    }

    @Test
    public void testShouldHaveRedColorForDeadCharacter() throws Exception {
        GoTCharacterViewModel viewModel = new GoTCharacterViewModel(new GoTCharacter(null, null, null, false, null, 0, null, null));
        assertThat(viewModel.getDescriptionColor(), is(Color.RED));
    }
}