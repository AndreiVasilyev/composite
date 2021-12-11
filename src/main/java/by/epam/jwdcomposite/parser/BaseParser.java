package by.epam.jwdcomposite.parser;

import by.epam.jwdcomposite.composite.Symbol;
import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComponentType;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.exception.UnknownComponentException;

public interface BaseParser {
    TextComposite parse(String sourceText);

    default void parseToComponents(String sourceValue, TextComposite composite) {
        TextComponent component;
        for (char symbol : sourceValue.toCharArray()) {
            if (Character.isAlphabetic(symbol)) {
                component = new Symbol(TextComponentType.LETTER, symbol);
            } else if (Character.isDigit(symbol)) {
                component = new Symbol(TextComponentType.DIGIT, symbol);
            } else {
                component = new Symbol(TextComponentType.SYMBOL, symbol);
            }
            composite.add(component);
        }
    }
}
