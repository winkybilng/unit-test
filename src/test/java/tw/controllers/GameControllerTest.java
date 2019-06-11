package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.generator.AnswerGenerator;
import tw.views.GameView;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {


    private GameView gameView;
    private InputCommand inputCommand;
    private AnswerGenerator answerGenerator;

    private Game game;
    private Answer answer;
    private Answer input;
    private GameController gameController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        answer = Answer.createAnswer("1 2 3 4");
        input = Answer.createAnswer("1 2 5 6");
        answerGenerator = mock(AnswerGenerator.class);
        Mockito.when(answerGenerator.generate()).thenReturn(answer);
        game = new Game(answerGenerator);
        gameView = mock(GameView.class);
    }

    @Test
    public void should_display_guess_history_and_status_message_when_guess_number() throws IOException {
        //given
        inputCommand = mock(InputCommand.class);
        Mockito.when(inputCommand.input()).thenReturn(input);
        gameController = new GameController(game, gameView);

        //when
        gameController.play(inputCommand);

        //then
        Mockito.verify(gameView,times(6)).showGuessHistory(anyList());
        Mockito.verify(gameView).showGameStatus(any());

    }

    @Test
    public void should_end_game_and_display_sucessful_message_when_number_is_correct_in_first_round() throws IOException {
        //given
        inputCommand = mock(InputCommand.class);
        when(inputCommand.input()).thenReturn(answer);
        gameController = new GameController(game, gameView);

        //when
        gameController.play(inputCommand);

        //then
        verify(inputCommand, times(1)).input();
        verify(gameView).showGameStatus("success");
    }

    @Test
    public void should_end_game_and_display_failure_message_once_times_reach_max_times() throws IOException {
        //given
        inputCommand = mock(InputCommand.class);
        when(inputCommand.input()).thenReturn(input);
        gameController = new GameController(game, gameView);

        //when
        gameController.play(inputCommand);

        //then
        verify(inputCommand, times(6)).input();
        verify(gameView).showGameStatus("fail");
    }

}