package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.TextComponentType;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.util.TextRegexContainer;
import by.epam.jwdcomposite.parser.MainParser;

import java.util.Arrays;

public class TextParser implements MainParser {

    private final MainParser paragraphParser;

    public TextParser() {
        this.paragraphParser = new ParagraphParser();
    }

    @Override
    public TextComposite parse(String sourceText) {
        TextComposite composite = new TextComposite(TextComponentType.TEXT);
        Arrays.stream((sourceText.split(TextRegexContainer.PARAGRAPH_DELIMITER_REGEX)))
                .forEach(paragraph -> composite.add(paragraphParser.parse(paragraph)));
        return composite;
    }
}
