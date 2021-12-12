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

public class ExpressionParser implements BaseParser {

    private static final Logger log = LogManager.getLogger();

    private BaseParser wordParser;

    public ExpressionParser() {
        this.wordParser = new WordParser();
    }

    @Override
    public TextComposite parse(String sourceExpression) {
        TextComposite composite = new TextComposite(EXPRESSION);
        TextComponent component;
        if (sourceExpression.matches(BRACKETS_BLOCK_REGEX)) {
            char firstSymbol = sourceExpression.charAt(0);
            char lastSymbol = sourceExpression.charAt(sourceExpression.length() - 1);
            component = new Symbol(PUNCTUATION, firstSymbol);
            composite.add(component);
            Pattern pattern = Pattern.compile(BRACKETS_CONTENT_REGEX);
            Matcher matcher = pattern.matcher(sourceExpression);
            if (matcher.find()) {
                String expression = matcher.group();
                if (!expression.isBlank() && expression.matches(WORD_REGEX)) {
                    component = wordParser.parse(expression);
                    composite.add(component);
                } else if (!expression.isBlank()) {
                    parseToComponents(expression, composite);
                }
            }
            component = new Symbol(PUNCTUATION, lastSymbol);
            composite.add(component);
        } else if (sourceExpression.matches(BIT_EXPRESSION_REGEX)) {
            //change on special parser
            parseToComponents(sourceExpression, composite);
        } else if (sourceExpression.matches(EXPRESSION_WITHOUT_BRACKETS_REGEX)) {
            parseToComponents(sourceExpression, composite);
        } else {
            log.error("unknown expression detected: {}", sourceExpression);
            throw new UnknownComponentException("unknown expression detected:" + sourceExpression);
        }
        return composite;
    }
}
