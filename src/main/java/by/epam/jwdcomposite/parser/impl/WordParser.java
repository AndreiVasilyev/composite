package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.Symbol;
import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComponentType;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.parser.MainParser;

import java.util.Arrays;

public class WordParser implements MainParser {

    @Override
    public TextComposite parse(String sourceWord) {
        TextComposite composite = new TextComposite(TextComponentType.WORD);
        parseToComponents(sourceWord, composite);
        return composite;
    }
}
