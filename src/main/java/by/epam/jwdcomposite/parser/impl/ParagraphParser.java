package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.parser.BaseParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.jwdcomposite.composite.TextComponentType.*;
import static by.epam.jwdcomposite.util.TextRegexContainer.*;

public class ParagraphParser implements BaseParser {

    private final BaseParser sentenceParser;

    public ParagraphParser() {
        this.sentenceParser = new SentenceParser();
    }

    @Override
    public TextComposite parse(String sourceParagraph) {
        TextComposite composite = new TextComposite(PARAGRAPH);
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(sourceParagraph);
        while (matcher.find()) {
            TextComponent sentence = sentenceParser.parse(matcher.group());
            composite.add(sentence);
        }
        return composite;
    }
}
