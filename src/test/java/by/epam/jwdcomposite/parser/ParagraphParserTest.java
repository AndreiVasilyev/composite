package by.epam.jwdcomposite.parser;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.parser.impl.ParagraphParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static by.epam.jwdcomposite.composite.TextComponentType.*;
import static org.testng.Assert.assertEquals;

public class ParagraphParserTest {
    private BaseParser parser;
    private TextComponent paragraphComposite;
    private final String EXPECTED_PARAGRAPH = "It has survived "
            .concat("- not only (five) centuries, but also the leap into electronic ")
            .concat("typesetting, remaining essentially unchanged. ")
            .concat("It was - popularised in the “Динамо” (Рига) ")
            .concat("with the release of Letraset sheets.toString() ")
            .concat("containing Lorem Ipsum passages, and more recently with desktop ")
            .concat("publishing software like Aldus PageMaker Faclon9 including ")
            .concat("versions of Lorem Ipsum!");

    @BeforeClass
    public void prepareData() {
        this.parser = new ParagraphParser();
    }

    @Test
    public void testCorrectParagraphParsing() {
        paragraphComposite = parser.parse(EXPECTED_PARAGRAPH);
        String actualValue = paragraphComposite.toString().trim().replaceAll(" \b", "");
        assertEquals(actualValue, EXPECTED_PARAGRAPH);
    }

    @Test
    public void testSeparationSentenceWhenParsing() {
        paragraphComposite = parser.parse(EXPECTED_PARAGRAPH);
        long actualWordQuantity = paragraphComposite.getComponentsList()
                .stream()
                .filter(sentence -> sentence.getTextComponentType().equals(SENTENCE))
                .count();
        long expectedWordQuantity = 2;
        assertEquals(actualWordQuantity, expectedWordQuantity);
    }
}
