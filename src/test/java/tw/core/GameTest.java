package tw.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.core.model.GuessResult;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {
    private Game game;
    private RandomIntGenerator randomIntGenerator;
    private AnswerGenerator answerGenerator;



    @Before
    public void setUp() throws Exception {
        Answer actualAnswer = Answer.createAnswer("2 4 5 6");
        answerGenerator = mock(AnswerGenerator.class);
        Mockito.when(answerGenerator.generate()).thenReturn(actualAnswer);
        game = new Game(answerGenerator);


    }



    @Test
    public void should_record_guess_history() {

        GuessResult guessResult1 = game.guess(Answer.createAnswer("2 3 4 5"));
        GuessResult guessResult2 = game.guess(Answer.createAnswer("1 2 3 5"));
        GuessResult guessResult3 = game.guess(Answer.createAnswer("1 2 3 4"));
        List<GuessResult> guessResults  = game.guessHistory();

        assertThat(guessResults.size(),is(3));
        assertThat(guessResults.get(0), is(guessResult1));
        assertThat(guessResults.get(1), is(guessResult2));
        assertThat(guessResults.get(2), is(guessResult3));


    }

    @Test
    public void should_return_success_when_guess_number_less_or_equal_6_and_guess_right() {
        game.guess(Answer.createAnswer("2 3 4 5"));
        game.guess(Answer.createAnswer("1 2 3 5"));
        game.guess(Answer.createAnswer("2 4 5 6"));

        assertThat(game.checkStatus(),is("success"));


    }

    @Test
    public void should_return_fail_when_guess_number_is_6_and_guess_wrong() {
        game.guess(Answer.createAnswer("2 3 4 5"));
        game.guess(Answer.createAnswer("1 2 3 5"));
        game.guess(Answer.createAnswer("2 4 5 7"));
        game.guess(Answer.createAnswer("3 4 5 7"));
        game.guess(Answer.createAnswer("8 4 5 7"));
        game.guess(Answer.createAnswer("9 4 5 7"));

        assertThat(game.checkStatus(),is("fail"));

    }

    @Test
    public void should_return_fail_when_guess_number_is_more_than_6() {
        game.guess(Answer.createAnswer("2 3 4 5"));
        game.guess(Answer.createAnswer("1 2 3 5"));
        game.guess(Answer.createAnswer("2 4 5 7"));
        game.guess(Answer.createAnswer("3 4 5 7"));
        game.guess(Answer.createAnswer("8 4 5 7"));
        game.guess(Answer.createAnswer("9 4 5 7"));
        game.guess(Answer.createAnswer("2 4 5 6"));


        assertThat(game.checkStatus(),is("fail"));

    }




}
