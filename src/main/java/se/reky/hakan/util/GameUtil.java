package se.reky.hakan.util;

import se.reky.hakan.GameException;

public class GameUtil {

    public String toLowerCaseButFirstLetterCapitalized(String input) throws GameException {
        if (input == null || input.isEmpty()) {
            throw new GameException("Input can not be null");
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
