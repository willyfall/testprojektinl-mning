package se.reky.hakan.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.reky.hakan.GameException;

public class GameUtilTest {
    GameUtil gameUtil=new GameUtil();
    @Test
    public void throwGameExeptionTest(){
        Assertions.assertThrows(GameException.class,()->{gameUtil.toLowerCaseButFirstLetterCapitalized(null);});
    }
}
