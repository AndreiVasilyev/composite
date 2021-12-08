package by.epam.jwdcomposite.main;

import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.parser.MainParser;
import by.epam.jwdcomposite.parser.impl.TextParser;

public class Main {
    public static void main(String[] args) {

        String text="It has survived - not only (five) centuries, but also the leap into electronic " +
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

        MainParser parser=new TextParser();
        TextComposite composite=parser.parse(text);
        System.out.println("\n\n");
        System.out.println(composite);
    }
}
