package by.epam.jwdcomposite.main;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComponentType;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.parser.BaseParser;
import by.epam.jwdcomposite.parser.impl.TextParser;
import by.epam.jwdcomposite.service.TextService;
import by.epam.jwdcomposite.service.impl.TextServiceImpl;

import java.util.List;
import java.util.Map;

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

        BaseParser parser = new TextParser();
        TextComposite composite = parser.parse(text);
        System.out.println("\n\n");
        System.out.println(composite);
        TextService service = new TextServiceImpl();
        TextComponent sortedtComposit = new TextComposite(TextComponentType.TEXT, service.sortBySentencesQuantity(composite));
        System.out.println("\n\n");
        System.out.println(sortedtComposit);
        System.out.println("\n\nSentences with longest word\n");
        List<TextComponent> longestList = service.findSentencesWithLongestWord(composite);
        longestList.forEach(System.out::println);
        System.out.println("\n Sentences were words numbre>=20");
        List<TextComponent> sent = service.removeSentencesByWordsQuantity(composite, 20);
        sent.forEach(System.out::println);
        System.out.println("\n\n");
        Map<String, Integer> words = service.findQuantityOfSameWords(composite);
        words.forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println("\nsentence: " + sent.get(0));
        System.out.println("\nVowels in sentense: " + service.countVowelLetterQuantity((TextComposite) sent.get(0)));
        System.out.println("\nConsonats in sentense: " + service.countConsonantsLetterQuantity((TextComposite) sent.get(0)));
    }
}
