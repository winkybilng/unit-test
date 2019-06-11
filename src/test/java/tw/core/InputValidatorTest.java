package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.generator.RandomIntGenerator;
import tw.validator.InputValidator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {
    private InputValidator inputValidator;

    @Before
    public void setUp() throws Exception {
        inputValidator = new InputValidator();

    }

    @Test
    public void should_return_false_when_input_not_4_numbers() {
        String input = "1 2 3 4 5";
        assertThat(inputValidator.validate(input), is( false));
    }

    @Test
    public void should_return_false_when_input_same_numbers() {
        String input = "1 1 2 3";
        assertThat(inputValidator.validate(input), is( false));
    }
    @Test
    public void should_return_false_when_input_numbers_not_between_0_and_9() {
        String input = "1 10 2 3";
        assertThat(inputValidator.validate(input), is( false));
    }

    @Test
    public void should_return_true_when_input_numbers_is_right() {
        String input = "1 4 2 3";
        assertThat(inputValidator.validate(input), is( true));
    }

}
