package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComponentType;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.util.TextRegexContainer;
import by.epam.jwdcomposite.parser.MainParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements MainParser {

    private final MainParser sentenceParser;

    public ParagraphParser() {
        this.sentenceParser = new SentenceParser();
    }

    @Override
    public TextComposite parse(String sourceParagraph) {
        TextComposite composite = new TextComposite(TextComponentType.PARAGRAPH);
        Pattern pattern = Pattern.compile(TextRegexContainer.SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(sourceParagraph);
        while (matcher.find()) {
            TextComponent sentence = sentenceParser.parse(matcher.group());
            composite.add(sentence);
        }
        return composite;
    }
}
