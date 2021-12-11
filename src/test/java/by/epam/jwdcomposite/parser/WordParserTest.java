package by.epam.jwdcomposite.parser;

import by.epam.jwdcomposite.composite.TextComponent;

import by.epam.jwdcomposite.parser.impl.WordParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static by.epam.jwdcomposite.composite.TextComponentType.*;

public class WordParserTest {

    private BaseParser parser;
    private TextComponent wordComposite;

    @BeforeClass
    public void prepareData() {
        this.parser = new WordParser();
    }

    @Test
    public void testCorrectWordParsing() {
        String expectedValue = "testWord";
        wordComposite = parser.parse(expectedValue);
        String actualValue = wordComposite.toString();
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testIsCorrectComponentTypeWhenParsing() {
        String value = "testWord";
        wordComposite = parser.parse(value);
        long actualLettersQuantity = wordComposite.getComponentsList()
                .stream()
                .filter(symbol -> symbol.getTextComponentType().equals(LETTER))
                .count();
        long expectedLettersQuantity = 8;
        assertEquals(actualLettersQuantity, expectedLettersQuantity);
    }

    @Test
    public void testSeparationDigitsWhenParsing() {
        String value = "5test-1Word8";
        wordComposite = parser.parse(value);
        long actualDigitsQuantity = wordComposite.getComponentsList()
                .stream()
                .filter(symbol -> symbol.getTextComponentType().equals(DIGIT))
                .count();
        long expectedDigitsQuantity = 3;
        assertEquals(actualDigitsQuantity, expectedDigitsQuantity);
    }

    @Test
    public void testSeparationSymbolsWhenParsing() {
        String value = "1test-Word's";
        wordComposite = parser.parse(value);
        long actualSymbolsQuantity = wordComposite.getComponentsList()
                .stream()
                .filter(symbol -> symbol.getTextComponentType().equals(SYMBOL))
                .count();
        long expectedSymbolsQuantity = 2;
        assertEquals(actualSymbolsQuantity, expectedSymbolsQuantity);
    }
}
