package by.epam.jwdcomposite.parser;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.exception.UnknownComponentException;
import by.epam.jwdcomposite.parser.impl.LexemeParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static by.epam.jwdcomposite.composite.TextComponentType.*;
import static org.testng.Assert.assertEquals;

public class LexemeParserTest {

    private BaseParser parser;
    private TextComponent lexemeComposite;

    @BeforeClass
    public void prepareData() {
        this.parser = new LexemeParser();
    }

    @Test
    public void testCorrectLexemeParsing() {
        String expectedValue = ".toString(a?b:c),";
        lexemeComposite = parser.parse(expectedValue);
        String actualValue = lexemeComposite.toString().trim();
        assertEquals(actualValue, expectedValue);
    }

    @Test(expectedExceptions = UnknownComponentException.class, expectedExceptionsMessageRegExp = "unknown lexeme detected.*")
    public void testIncorrectBracketsBlockExpressionParsing() {
        String expectedValue = "toString(a?b:c)֊,";
        lexemeComposite = parser.parse(expectedValue);
    }

    @Test
    public void testSeparationWordWhenParsing() {
        String value = "toString(a?b:c),";
        lexemeComposite = parser.parse(value);
        long actualWordQuantity = lexemeComposite.getComponentsList()
                .stream()
                .filter(word -> word.getTextComponentType().equals(WORD))
                .count();
        long expectedWordQuantity = 1;
        assertEquals(actualWordQuantity, expectedWordQuantity);
    }

    @Test
    public void testSeparationPunctuationWhenParsing() {
        String value = "#toString(a?b:c),";
        lexemeComposite = parser.parse(value);
        long actualPunctuationQuantity = lexemeComposite.getComponentsList()
                .stream()
                .filter(punctuation -> punctuation.getTextComponentType().equals(PUNCTUATION))
                .count();
        long expectedPunctuationQuantity = 2;
        assertEquals(actualPunctuationQuantity, expectedPunctuationQuantity);
    }

    @Test
    public void testSeparationExpressionWhenParsing() {
        String value = "toString(a?b:c),";
        lexemeComposite = parser.parse(value);
        long actualExpressionQuantity = lexemeComposite.getComponentsList()
                .stream()
                .filter(expression -> expression.getTextComponentType().equals(EXPRESSION))
                .count();
        long expectedExpressionQuantity = 1;
        assertEquals(actualExpressionQuantity, expectedExpressionQuantity);
    }
}
