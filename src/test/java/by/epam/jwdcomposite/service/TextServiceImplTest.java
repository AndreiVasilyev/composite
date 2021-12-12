package by.epam.jwdcomposite.service;


import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.parser.BaseParser;
import by.epam.jwdcomposite.parser.impl.TextParser;
import by.epam.jwdcomposite.service.impl.TextServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static by.epam.jwdcomposite.composite.TextComponentType.*;

public class TextServiceImplTest {

    private final String SOURCE_TEXT = "\tIt has survived "
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

    private TextService service;
    BaseParser parser;
    TextComposite composite;

    @BeforeClass
    public void prepareData() {
        this.service = new TextServiceImpl();
        parser = new TextParser();
        composite = parser.parse(SOURCE_TEXT);
    }

    @Test
    public void testsSortBySentencesQuantity() {
        String expectedText = "\tIt is a established fact that "
                .concat("a reader will be of a page when looking at its layout…\r\n")
                .concat("\tBye бандерлоги.\r\n\tIt has survived ")
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
                .concat("it look like readable English?\r\n");

        List<TextComponent> sortedParagraphs = service.sortBySentencesQuantity(composite);
        TextComposite sortedText = new TextComposite(TEXT, sortedParagraphs);
        String actualText = sortedText.toString().replaceAll(" \b", "");
        assertEquals(actualText, expectedText);
    }

    @Test
    public void testFindSentencesWithLongestWord() {
        String expectedSentences = "The point of using Ipsum is that it has a "
                .concat("more-or-less normal distribution ob.toString(a?b:c), ")
                .concat("as opposed to using (Content here), content here's, making ")
                .concat("it look like readable English?");
        List<TextComponent> foundSentences = service.findSentencesWithLongestWord(composite);
        String actualSentences = foundSentences
                .stream()
                .map(sentence -> sentence.toString())
                .collect(Collectors.joining())
                .replaceAll(" \b", "")
                .trim();
        assertEquals(actualSentences, expectedSentences);
    }

    @Test
    public void testRemoveSentencesByWordsQuantity() {
        int minWordsCount = 20;
        String expectedText = "It was - popularised in the “Динамо” (Рига) "
                .concat("with the release of Letraset sheets.toString() ")
                .concat("containing Lorem Ipsum passages, and more recently with desktop ")
                .concat("publishing software like Aldus PageMaker Faclon9 including ")
                .concat("versions of Lorem Ipsum!\r\n")
                .concat("\tIt is a long a!=b established fact that a reader will be ")
                .concat("distracted by the readable content of a page when looking ")
                .concat("at its layout. The point of using Ipsum is that it has a ")
                .concat("more-or-less normal distribution ob.toString(a?b:c), ")
                .concat("as opposed to using (Content here), content here's, making ")
                .concat("it look like readable English?");
        TextComposite removedText = service.removeSentencesByWordsQuantity(composite, 20);
        String actualText = removedText.toString().replaceAll("( \b)", "").trim();
        assertEquals(actualText, expectedText);
    }

    @Test
    public void testCorrectFoundQuantityOfSameWords() {
        int expectedCountOfIpsum = 3;
        Map<String, Integer> sameWords = service.findQuantityOfSameWords(composite);
        int actualCountOfIpsum = sameWords.get("Ipsum");
        assertEquals(actualCountOfIpsum, expectedCountOfIpsum);
    }

    @Test
    public void testFoundQuantityOfSameWords() {
        int expectedFoundWords = 28;
        Map<String, Integer> sameWords = service.findQuantityOfSameWords(composite);
        int actualFoundWords = sameWords.size();
        assertEquals(actualFoundWords, expectedFoundWords);
    }

    @Test
    public void testCountVowelLetterQuantity() {
        int expectedVowelLetterQuantity = 42;
        TextComposite sentence = (TextComposite) composite.getComponentsList().get(0).getComponentsList().get(0);
        int actualVowelLetterQuantity = service.countVowelLetterQuantity(sentence);
        assertEquals(actualVowelLetterQuantity, expectedVowelLetterQuantity);
    }

    @Test
    public void testCountConsonantsLetterQuantity() {
        int expectedConsonantsLetterQuantity = 59;
        TextComposite sentence = (TextComposite) composite.getComponentsList().get(0).getComponentsList().get(0);
        int actualConsonantsLetterQuantity = service.countConsonantsLetterQuantity(sentence);
        assertEquals(actualConsonantsLetterQuantity, expectedConsonantsLetterQuantity);
    }
}
