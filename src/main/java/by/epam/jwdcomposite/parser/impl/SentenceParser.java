package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.Symbol;
import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComponentType;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.util.TextRegexContainer;
import by.epam.jwdcomposite.parser.MainParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements MainParser {

    private final MainParser lexemeParser;
    private final MainParser wordParser;
    private final MainParser expressionParser;

    public SentenceParser() {
        this.wordParser = new WordParser();
        this.expressionParser = new ExpressionParser();
        this.lexemeParser = new LexemeParser();
    }

    @Override
    public TextComposite parse(String sourceSentence) {
        TextComposite composite = new TextComposite(TextComponentType.SENTENCE);
        Pattern pattern = Pattern.compile(TextRegexContainer.LEXEME_REGEX);
        Matcher matcher = pattern.matcher(sourceSentence);
        TextComponent component;
        while (matcher.find()) {
            String lexeme = matcher.group();
            if (lexeme.matches(TextRegexContainer.WORD_REGEX)) {
                component = wordParser.parse(lexeme);
            } else if (lexeme.matches(TextRegexContainer.EXPRESSION_REGEX) || lexeme.matches(TextRegexContainer.BIT_EXPRESSION_REGEX)) {
                component = expressionParser.parse(lexeme);
            } else if (lexeme.matches(TextRegexContainer.SINGLE_PUNCTUATION_REGEX)) {
                component = new Symbol(TextComponentType.PUNCTUATION, lexeme.charAt(0));
            } else {
                component = lexemeParser.parse(lexeme);
            }
            composite.add(component);
        }
        return composite;
    }
}
