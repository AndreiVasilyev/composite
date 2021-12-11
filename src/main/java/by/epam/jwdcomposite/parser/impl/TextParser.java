package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.parser.BaseParser;

import java.util.Arrays;

import static by.epam.jwdcomposite.composite.TextComponentType.*;
import static by.epam.jwdcomposite.util.TextRegexContainer.*;

public class TextParser implements BaseParser {

    private final BaseParser paragraphParser;

    public TextParser() {
        this.paragraphParser = new ParagraphParser();
    }

    @Override
    public TextComposite parse(String sourceText) {
        TextComposite composite = new TextComposite(TEXT);
        Arrays.stream((sourceText.split(PARAGRAPH_DELIMITER_REGEX)))
                .forEach(paragraph -> composite.add(paragraphParser.parse(paragraph)));
        return composite;
    }
}
