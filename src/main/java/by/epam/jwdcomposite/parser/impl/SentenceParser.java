package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComponentType;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.entity.TextRegexContainer;
import by.epam.jwdcomposite.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements TextParser {

    private final TextParser nextParser;

    public SentenceParser() {
        this.nextParser = new LexemeParser();
    }

    @Override
    public TextComposite parse(String sourceText) {
        TextComposite composite = new TextComposite(TextComponentType.PARAGRAPH);
        Pattern pattern = Pattern.compile(TextRegexContainer.SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(sourceText);
        while (matcher.find()) {
            TextComponent component = nextParser.parse(matcher.group());
            composite.add(component);
        }
        return composite;
    }
}
