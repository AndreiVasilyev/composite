package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.Symbol;
import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.exception.UnknownComponentException;
import by.epam.jwdcomposite.parser.BaseParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.jwdcomposite.composite.TextComponentType.*;
import static by.epam.jwdcomposite.util.TextRegexContainer.*;

public class LexemeParser implements BaseParser {

    private static final Logger log = LogManager.getLogger();

    private BaseParser wordParser;
    private BaseParser expressionParser;
    private BaseParser punctuationParser;

    public LexemeParser() {
        this.wordParser = new WordParser();
        this.expressionParser = new ExpressionParser();
        this.punctuationParser = new PunctuationParser();
    }

    @Override
    public TextComposite parse(String sourceLexeme) {
        TextComposite composite = new TextComposite(LEXEME);
        TextComponent component;
        Pattern pattern = Pattern.compile(PUNCTUATION_REGEX);
        Matcher matcher = pattern.matcher(sourceLexeme);
        if (matcher.find()) {
            String beforePunctuation = matcher.group(1);
            String lexemeBody = matcher.group(2);
            String afterPunctuation = matcher.group(3);
            if (!beforePunctuation.isBlank()) {
                if (beforePunctuation.length() > 1) {
                    component = punctuationParser.parse(beforePunctuation);
                } else {
                    component = new Symbol(PUNCTUATION, beforePunctuation.charAt(0));
                }
                composite.add(component);
            }
            if (!lexemeBody.isBlank()) {
                if (lexemeBody.matches(WORD_REGEX)) {
                    component = wordParser.parse(lexemeBody);
                    composite.add(component);
                } else if (lexemeBody.matches(EXPRESSION_REGEX)) {
                    component = expressionParser.parse(lexemeBody);
                    composite.add(component);
                } else {
                    Pattern separateLexemePattern = Pattern.compile(SEPARATE_LEXEME_REGEX);
                    Matcher separateLexemeMatcher = separateLexemePattern.matcher(lexemeBody);
                    while (separateLexemeMatcher.find()) {
                        String beforeWord = separateLexemeMatcher.group(1);
                        String expression = separateLexemeMatcher.group(2);
                        String afterWord = separateLexemeMatcher.group(3);
                        if (!beforeWord.isBlank()) {
                            component = wordParser.parse(beforeWord);
                            composite.add(component);
                        }
                        if (!expression.isBlank()) {
                            component = expressionParser.parse(expression);
                            composite.add(component);
                        }
                        if (!afterWord.isBlank()) {
                            component = wordParser.parse(afterWord);
                            composite.add(component);
                        }
                    }
                }
            }
            if (!afterPunctuation.isBlank()) {
                if (afterPunctuation.length() > 1) {
                    component = punctuationParser.parse(afterPunctuation);
                } else {
                    component = new Symbol(PUNCTUATION, afterPunctuation.charAt(0));
                }

                composite.add(component);
            }
        } else {
            log.error("unknown lexeme detected: {}", sourceLexeme);
            throw new UnknownComponentException("unknown lexeme detected:" + sourceLexeme);
        }
        return composite;
    }
}
