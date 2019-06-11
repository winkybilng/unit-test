package tw.core.generator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tw.core.exception.OutOfRangeAnswerException;

import static javafx.beans.binding.Bindings.when;
import static org.mockito.Mockito.mock;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {
    private RandomIntGenerator randomIntGenerator;
    private AnswerGenerator answerGenerator;
    @Before
    public void setUp() throws Exception {
        randomIntGenerator = mock(RandomIntGenerator.class);

    }

    @Test(expected = OutOfRangeAnswerException.class)
    public void should_throw_OutOfRangeAnswerException_which_is_not_between_0_and_9() throws OutOfRangeAnswerException {
        randomIntGenerator = mock(RandomIntGenerator.class);
        Mockito.when(randomIntGenerator.generateNums(9,4)).thenReturn("1 2 3 10");
        answerGenerator = new AnswerGenerator(randomIntGenerator);
        answerGenerator.generate();
    }

    @Test(expected = OutOfRangeAnswerException.class)
    public void should_throw_OutOfRangeAnswerException_which_is_not_4_different_number() throws OutOfRangeAnswerException {
        randomIntGenerator = mock(RandomIntGenerator.class);
        Mockito.when(randomIntGenerator.generateNums(9,4)).thenReturn("1 1 2 5");
        answerGenerator = new AnswerGenerator(randomIntGenerator);
        answerGenerator.generate();
    }


}

