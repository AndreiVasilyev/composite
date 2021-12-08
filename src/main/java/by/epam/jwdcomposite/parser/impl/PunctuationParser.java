package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.Symbol;
import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComponentType;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.parser.MainParser;

import java.awt.*;

public class PunctuationParser implements MainParser {
    @Override
    public TextComposite parse(String sourcePunctuation) {
        TextComposite composite = new TextComposite(TextComponentType.PUNCTUATION);
        TextComponent component;
        for (char symbol : sourcePunctuation.toCharArray()) {
            component = new Symbol(TextComponentType.PUNCTUATION, symbol);
            composite.add(component);
        }
        return composite;
    }

}

