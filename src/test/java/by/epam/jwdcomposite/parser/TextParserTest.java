package by.epam.jwdcomposite.parser;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.parser.impl.TextParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static by.epam.jwdcomposite.composite.TextComponentType.PARAGRAPH;
import static org.testng.Assert.assertEquals;

public class TextParserTest {

    private BaseParser parser;
    private TextComponent textComposite;
    private final String EXPECTED_TEXT = "\tIt has survived "
            .concat("- not only (five) centuries, but also the leap into electronic ")
            .concat("typesetting, remaining essentially unchanged. ")
            .concat("It was - popularised in the “Динамо” (Рига) ")
            .concat("with the release of Letraset sheets.toString() ")
            .concat("containing Lorem Ipsum passages, and more recently with desktop ")
            .concat("publishing software like Aldus PageMaker Faclon9 including ")
            .concat("versions of Lorem Ipsum!\r\n")
            .concat("\tIt is a long a!=b established fact that a reader will be ")
            .concat("distracted by the readable content of a page when looking ")
            .concat("at its layout. The point of using Ipsum is that it has a ")
            .concat("more-or-less normal distribution ob.toString(a?b:c), ")
            .concat("as opposed to using (Content here), content here's, making ")
            .concat("it look like readable English?\r\n")
            .concat("\tIt is a established fact that a reader will be of a page ")
            .concat("when looking at its layout…\r\n\tBye бандерлоги.\r\n");

    @BeforeClass
    public void prepareData() {
        this.parser = new TextParser();
    }

    @Test
    public void testCorrectParagraphParsing() {
        textComposite = parser.parse(EXPECTED_TEXT);
        String actualValue = textComposite.toString().replaceAll(" \b", "");
        assertEquals(actualValue, EXPECTED_TEXT);
    }

    @Test
    public void testSeparationParagraphWhenParsing() {
        textComposite = parser.parse(EXPECTED_TEXT);
        long actualParagraphQuantity = textComposite.getComponentsList()
                .stream()
                .filter(paragraph -> paragraph.getTextComponentType().equals(PARAGRAPH))
                .count();
        long expectedParagraphQuantity = 4;
        assertEquals(actualParagraphQuantity, expectedParagraphQuantity);
    }
}
