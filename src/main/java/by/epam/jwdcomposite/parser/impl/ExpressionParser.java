package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.Symbol;
import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComponentType;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.util.TextRegexContainer;
import by.epam.jwdcomposite.parser.MainParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser implements MainParser {

    private MainParser wordParser;

    public ExpressionParser() {
        this.wordParser = new WordParser();
    }

    @Override
    public TextComposite parse(String sourceExpression) {
        TextComposite composite = new TextComposite(TextComponentType.EXPRESSION);
        TextComponent component;
        if (sourceExpression.matches(TextRegexContainer.BRACKETS_BLOCK_REGEX)) {
            char firstSymbol = sourceExpression.charAt(0);
            char lastSymbol = sourceExpression.charAt(sourceExpression.length() - 1);
            component = new Symbol(TextComponentType.SYMBOL, firstSymbol);
            composite.add(component);
            Pattern pattern = Pattern.compile(TextRegexContainer.BRACKETS_CONTENT_REGEX);
            Matcher matcher = pattern.matcher(sourceExpression);
            if (matcher.find()) {
                String expression = matcher.group();
                if (!expression.isBlank() && expression.matches(TextRegexContainer.WORD_REGEX)) {
                    component = wordParser.parse(expression);
                    composite.add(component);
                }
            }
            component = new Symbol(TextComponentType.SYMBOL, lastSymbol);
            composite.add(component);
        } else {
            if (!sourceExpression.matches(TextRegexContainer.BIT_EXPRESSION_REGEX)) {
                parseToComponents(sourceExpression, composite);
            }
        }
        return composite;
    }
}
