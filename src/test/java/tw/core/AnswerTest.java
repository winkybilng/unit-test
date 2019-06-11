package tw.core;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.mockito.Mockito.mock;

import org.mockito.Mockito;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.core.model.GuessResult;


/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {
    private RandomIntGenerator randomIntGenerator;
    private AnswerGenerator answerGenerator;
    private Game game;

    @Before
    public void setUp() throws Exception {
        randomIntGenerator = mock(RandomIntGenerator.class);

    }

    @Test
    public void should_return_0A0B_when_no_number_is_correct() throws OutOfRangeAnswerException {
        //given
        Mockito.when(randomIntGenerator.generateNums(9,4)).thenReturn("1 2 3 4");
        answerGenerator = new AnswerGenerator(randomIntGenerator);
        game = new Game(answerGenerator);
        Answer inputAnswer = Answer.createAnswer("5 6 7 8");

        //when
        game.guess(inputAnswer);
        GuessResult guessResult = game.guess(inputAnswer);

        //then
        assertThat(guessResult.getResult(), is( "0A0B"));

    }
    @Test
    public void should_return_1A0B_when_one_number_position_is_correct() throws OutOfRangeAnswerException {
        //given
        Mockito.when(randomIntGenerator.generateNums(9,4)).thenReturn("5 2 3 4");
        answerGenerator = new AnswerGenerator(randomIntGenerator);
        game = new Game(answerGenerator);
        Answer inputAnswer = Answer.createAnswer("5 6 7 8");

        //when
        game.guess(inputAnswer);
        GuessResult guessResult = game.guess(inputAnswer);

        //then
        assertThat(guessResult.getResult(), is( "1A0B"));

    }

    @Test
    public void should_return_0A1B_when_one_number_size_is_correct() throws OutOfRangeAnswerException {
        //given
        Mockito.when(randomIntGenerator.generateNums(9,4)).thenReturn("2 5 3 4");
        answerGenerator = new AnswerGenerator(randomIntGenerator);
        game = new Game(answerGenerator);
        Answer inputAnswer = Answer.createAnswer("5 6 7 8");

        //when
        game.guess(inputAnswer);
        GuessResult guessResult = game.guess(inputAnswer);

        //then
        assertThat(guessResult.getResult(), is( "0A1B"));

    }

    @Test
    public void should_return_4A0B_when_all_number_position_size_is_correct() throws OutOfRangeAnswerException {
        //given
        Mockito.when(randomIntGenerator.generateNums(9,4)).thenReturn("5 6 7 8");
        answerGenerator = new AnswerGenerator(randomIntGenerator);
        game = new Game(answerGenerator);
        Answer inputAnswer = Answer.createAnswer("5 6 7 8");

        //when
        game.guess(inputAnswer);
        GuessResult guessResult = game.guess(inputAnswer);

        //then
        assertThat(guessResult.getResult(), is( "4A0B"));

    }






}