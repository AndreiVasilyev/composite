package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.Symbol;
import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.parser.BaseParser;

import static by.epam.jwdcomposite.composite.TextComponentType.*;


public class PunctuationParser implements BaseParser {
    @Override
    public TextComposite parse(String sourcePunctuation) {
        TextComposite composite = new TextComposite(PUNCTUATION);
        TextComponent component;
        for (char symbol : sourcePunctuation.toCharArray()) {
            component = new Symbol(PUNCTUATION, symbol);
            composite.add(component);
        }
        return composite;
    }

}

