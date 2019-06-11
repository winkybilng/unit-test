package tw.core;


import org.junit.Before;
import org.junit.Test;
import tw.core.generator.RandomIntGenerator;
import tw.validator.InputValidator;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {
    private RandomIntGenerator randomIntGenerator;

    @Before
    public void setUp() throws Exception {
        randomIntGenerator = new RandomIntGenerator();

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_IllegalArgumentException_when_digitmax_less_than_numbersOfNeed() {

        randomIntGenerator.generateNums(4, 5);
    }
    @Test
    public void should_return_right_number_when_digitmax_large_than_numbersOfNeed() {
        String result = randomIntGenerator.generateNums(9, 4);
        String[] re = result.split(" ");
        assertThat(re.length,is(4));


    }


}