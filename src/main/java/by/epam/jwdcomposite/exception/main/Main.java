package by.epam.jwdcomposite.exception.main;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.parser.BaseParser;
import by.epam.jwdcomposite.parser.impl.TextParser;
import by.epam.jwdcomposite.service.TextService;
import by.epam.jwdcomposite.service.impl.TextServiceImpl;

import java.util.List;
import java.util.Map;

import static by.epam.jwdcomposite.composite.TextComponentType.*;

public class Main {
    public static void main(String[] args) {

        String text = "\tIt has survived - not only (five) centuries, but also the leap into electronic " +
                "typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) " +
                "with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and " +
                "more recently with desktop publishing software like Aldus PageMaker Faclon9 including " +
                "versions of Lorem Ipsum!\r\n" +
                "\tIt is a long a!=b established fact that a reader will be distracted by the readable " +
                "content of a page when looking at its layout. The point of using Ipsum is that it has a " +
                "more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), " +
                "content here's, making it look like readable English?\r\n" +
                "\tIt is a established fact that a reader will be of a page when looking at its layout…\r\n" +
                "\tBye бандерлоги.\r\n";
        TextService service = new TextServiceImpl();
        BaseParser parser = new TextParser();
        TextComposite composite = parser.parse(text);
        System.out.println("\nSource text:");
        System.out.println(composite);
        TextComponent sortedComposite = new TextComposite(TEXT, service.sortBySentencesQuantity(composite));
        System.out.println("\nSorted text:");
        System.out.println(sortedComposite);
        System.out.println("\nSentences with longest word:");
        List<TextComponent> longestList = service.findSentencesWithLongestWord(composite);
        longestList.forEach(System.out::println);
        System.out.println("\nSentences were words numbre>=20");
        TextComponent newText = service.removeSentencesByWordsQuantity(composite, 20);
        System.out.println(newText);
        System.out.println("Same words:");
        Map<String, Integer> words = service.findQuantityOfSameWords(composite);
        words.forEach((k, v) -> System.out.println(k + ":" + v));
        TextComponent sent = composite.getComponentsList().get(0).getComponentsList().get(0);
        System.out.println("\nSentence: " + sent);
        System.out.println("\nVowels in sentense: " + service.countVowelLetterQuantity((TextComposite) sent));
        System.out.println("\nConsonats in sentense: " + service.countConsonantsLetterQuantity((TextComposite) sent));
    }
}
