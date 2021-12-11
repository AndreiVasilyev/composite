package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.Symbol;
import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.parser.BaseParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.jwdcomposite.composite.TextComponentType.*;
import static by.epam.jwdcomposite.util.TextRegexContainer.*;

public class SentenceParser implements BaseParser {

    private final BaseParser lexemeParser;
    private final BaseParser wordParser;
    private final BaseParser expressionParser;

    public SentenceParser() {
        this.wordParser = new WordParser();
        this.expressionParser = new ExpressionParser();
        this.lexemeParser = new LexemeParser();
    }

    @Override
    public TextComposite parse(String sourceSentence) {
        TextComposite composite = new TextComposite(SENTENCE);
        Pattern pattern = Pattern.compile(LEXEME_REGEX);
        Matcher matcher = pattern.matcher(sourceSentence);
        TextComponent component;
        while (matcher.find()) {
            String lexeme = matcher.group();
            if (lexeme.matches(WORD_REGEX)) {
                component = wordParser.parse(lexeme);
            } else if (lexeme.matches(EXPRESSION_REGEX)
                    || lexeme.matches(BIT_EXPRESSION_REGEX)) {
                component = expressionParser.parse(lexeme);
            } else if (lexeme.matches(SINGLE_PUNCTUATION_REGEX)) {
                component = new Symbol(PUNCTUATION, lexeme.charAt(0));
            } else {
                component = lexemeParser.parse(lexeme);
            }
            composite.add(component);
        }
        return composite;
    }
}
