package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComponentType;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.entity.TextRegexContainer;
import by.epam.jwdcomposite.parser.TextParser;

import java.util.Arrays;
import java.util.stream.Stream;

public class ParagraphParser implements TextParser {

    private final TextParser nextParser;

    public ParagraphParser() {
        this.nextParser = new SentenceParser();
    }

    @Override
    public TextComposite parse(String sourceText) {
        TextComposite composite = new TextComposite(TextComponentType.TEXT);
        Arrays.stream((sourceText.split(TextRegexContainer.PARAGRAPH_DELIMITER_REGEX)))
                .forEach(paragraph -> composite.add(nextParser.parse(paragraph)));
        return composite;
    }
}
