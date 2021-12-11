package by.epam.jwdcomposite.parser;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.parser.impl.SentenceParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static by.epam.jwdcomposite.composite.TextComponentType.*;
import static org.testng.Assert.assertEquals;

public class SentenceParserTest {

    private BaseParser parser;
    private TextComponent sentenceComposite;
    private final String EXPECTED_SENTENCE = "It was - popularised in the “Динамо” (Рига) "
            .concat("with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and ")
            .concat("more recently with desktop publishing software like Aldus PageMaker Faclon9 including ")
            .concat("versions of Lorem Ipsum!");

    @BeforeClass
    public void prepareData() {
        this.parser = new SentenceParser();
    }

    @Test
    public void testCorrectSentenceParsing() {
        sentenceComposite = parser.parse(EXPECTED_SENTENCE);
        String actualValue = sentenceComposite.toString().trim().replaceAll(" \b", "");
        assertEquals(actualValue, EXPECTED_SENTENCE);
    }


    @Test
    public void testSeparationWordWhenParsing() {
        sentenceComposite = parser.parse(EXPECTED_SENTENCE);
        long actualWordQuantity = sentenceComposite.getComponentsList()
                .stream()
                .filter(lexeme -> lexeme.getTextComponentType().equals(WORD))
                .count();
        long expectedWordQuantity = 29;
        assertEquals(actualWordQuantity, expectedWordQuantity);
    }

    @Test
    public void testSeparationPunctuationWhenParsing() {
        sentenceComposite = parser.parse(EXPECTED_SENTENCE);
        long actualPunctuationQuantity = sentenceComposite.getComponentsList()
                .stream()
                .filter(lexeme -> lexeme.getTextComponentType().equals(PUNCTUATION))
                .count();
        long expectedPunctuationQuantity = 1;
        assertEquals(actualPunctuationQuantity, expectedPunctuationQuantity);
    }

    @Test
    public void testSeparationExpressionWhenParsing() {
        sentenceComposite = parser.parse(EXPECTED_SENTENCE);
        long actualExpressionQuantity = sentenceComposite.getComponentsList()
                .stream()
                .filter(lexeme -> lexeme.getTextComponentType().equals(EXPRESSION))
                .count();
        long expectedExpressionQuantity = 1;
        assertEquals(actualExpressionQuantity, expectedExpressionQuantity);
    }

    @Test
    public void testSeparationLexemeWhenParsing() {
        sentenceComposite = parser.parse(EXPECTED_SENTENCE);
        long actualLexemeQuantity = sentenceComposite.getComponentsList()
                .stream()
                .filter(lexeme -> lexeme.getTextComponentType().equals(LEXEME))
                .count();
        long expectedLexemeQuantity = 4;
        assertEquals(actualLexemeQuantity, expectedLexemeQuantity);
    }
}
