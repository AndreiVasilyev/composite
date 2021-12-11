package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.parser.BaseParser;

import static by.epam.jwdcomposite.composite.TextComponentType.*;

public class WordParser implements BaseParser {

    @Override
    public TextComposite parse(String sourceWord) {
        TextComposite composite = new TextComposite(WORD);
        parseToComponents(sourceWord, composite);
        return composite;
    }
}
