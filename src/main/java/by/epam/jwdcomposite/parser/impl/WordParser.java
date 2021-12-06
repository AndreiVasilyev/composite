package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComponentType;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.entity.TextRegexContainer;
import by.epam.jwdcomposite.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements TextParser {

    private TextParser nextParser;

    @Override
    public TextComposite parse(String sourceText) {
        TextComposite composite = new TextComposite(TextComponentType.LEXEME);
        Pattern pattern = Pattern.compile(TextRegexContainer.WORD_REGEX);
        Matcher matcher = pattern.matcher(sourceText);
       /*
        while (matcher.find()) {
            System.out.println(matcher.group());
            TextComponent component = nextParser.parse(matcher.group());
            composite.add(component);
        }
      */
        return null;
    }
}
