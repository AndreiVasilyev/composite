package by.epam.jwdcomposite.parser;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.parser.impl.PunctuationParser;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static by.epam.jwdcomposite.composite.TextComponentType.*;
import static org.testng.Assert.assertEquals;

public class PunctuationParserTest {
    private BaseParser parser;
    private TextComponent punctuationComposite;

    @BeforeClass
    public void prepareData() {
        this.parser = new PunctuationParser();
    }

    @Test
    public void testCorrectPunctuationParsing() {
        String expectedValue = ".!!?";
        punctuationComposite = parser.parse(expectedValue);
        String actualValue = punctuationComposite.toString();
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testIsCorrectComponentTypeWhenParsing() {
        String value = ".!!?";
        punctuationComposite = parser.parse(value);
        long actualPunctuationsQuantity = punctuationComposite.getComponentsList()
                .stream()
                .filter(symbol -> symbol.getTextComponentType().equals(PUNCTUATION))
                .count();
        long expectedPunctuationsQuantity = 4;
        assertEquals(actualPunctuationsQuantity, expectedPunctuationsQuantity);
    }
}
