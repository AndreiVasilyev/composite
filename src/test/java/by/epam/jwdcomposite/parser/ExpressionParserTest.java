package by.epam.jwdcomposite.parser;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.exception.UnknownComponentException;
import by.epam.jwdcomposite.parser.impl.ExpressionParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static by.epam.jwdcomposite.composite.TextComponentType.*;
import static org.testng.Assert.assertEquals;

public class ExpressionParserTest {
    private BaseParser parser;
    private TextComponent expressionComposite;

    @BeforeClass
    public void prepareData() {
        this.parser = new ExpressionParser();
    }

    @Test
    public void testCorrectBracketsBlockExpressionParsing() {
        String expectedValue = "(a*b=c)";
        expressionComposite = parser.parse(expectedValue);
        String actualValue = expressionComposite.toString();
        assertEquals(actualValue, expectedValue);
    }

    @Test(expectedExceptions = UnknownComponentException.class, expectedExceptionsMessageRegExp = "unknown expression detected.*")
    public void testIncorrectBracketsBlockExpressionParsing() {
        String expectedValue = "(a*b=c";
        expressionComposite = parser.parse(expectedValue);
    }

    @Test
    public void testCorrectBitExpressionParsing() {
        String expectedValue = "(7^5|1&2<<(2|5>>2&71))|1200";
        expressionComposite = parser.parse(expectedValue);
        String actualValue = expressionComposite.toString();
        assertEquals(actualValue, expectedValue);
    }

    @Test(expectedExceptions = UnknownComponentException.class, expectedExceptionsMessageRegExp = "unknown expression detected.*")
    public void testIncorrectBitExpressionParsing() {
        String expectedValue = "(7^5|1&2<<(2|5>>2&71))|f";
        expressionComposite = parser.parse(expectedValue);
    }

    @Test
    public void testSeparationWordWhenParsing() {
        String value = "(testWord)";
        expressionComposite = parser.parse(value);
        long actualWordQuantity = expressionComposite.getComponentsList()
                .stream()
                .filter(word -> word.getTextComponentType().equals(WORD))
                .count();
        long expectedWordQuantity = 1;
        assertEquals(actualWordQuantity, expectedWordQuantity);
    }

    @Test
    public void testSeparationBracketsWhenParsing() {
        String value = "(testWord)";
        expressionComposite = parser.parse(value);
        long actualBracketsQuantity = expressionComposite.getComponentsList()
                .stream()
                .filter(punctuation -> punctuation.getTextComponentType().equals(PUNCTUATION))
                .count();
        long expectedBracketsQuantity = 2;
        assertEquals(actualBracketsQuantity, expectedBracketsQuantity);
    }

    @Test
    public void testSeparationLettersWhenParsing() {
        String value = "(a*b=c)";
        expressionComposite = parser.parse(value);
        long actualLettersQuantity = expressionComposite.getComponentsList()
                .stream()
                .filter(symbol -> symbol.getTextComponentType().equals(LETTER))
                .count();
        long expectedLettersQuantity = 3;
        assertEquals(actualLettersQuantity, expectedLettersQuantity);
    }

    @Test
    public void testSeparationDigitsWhenParsing() {
        String value = "(2x+3y=12)";
        expressionComposite = parser.parse(value);
        long actualDigitsQuantity = expressionComposite.getComponentsList()
                .stream()
                .filter(symbol -> symbol.getTextComponentType().equals(DIGIT))
                .count();
        long expectedDigitsQuantity = 4;
        assertEquals(actualDigitsQuantity, expectedDigitsQuantity);
    }

    @Test
    public void testSeparationSymbolsWhenParsing() {
        String value = "(2x+3y=12)";
        expressionComposite = parser.parse(value);
        long actualSymbolsQuantity = expressionComposite.getComponentsList()
                .stream()
                .filter(symbol -> symbol.getTextComponentType().equals(SYMBOL))
                .count();
        long expectedSymbolsQuantity = 2;
        assertEquals(actualSymbolsQuantity, expectedSymbolsQuantity);
    }
}
