package by.epam.jwdcomposite.parser.impl;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComponentType;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.entity.TextRegexContainer;
import by.epam.jwdcomposite.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements TextParser {

    private final TextParser nextParser;

    public LexemeParser(){
        this.nextParser=new WordParser();
    }

    @Override
    public TextComposite parse(String sourceText) {
        TextComposite composite=new TextComposite(TextComponentType.SENTENCE);
        Pattern pattern = Pattern.compile(TextRegexContainer.LEXEME_REGEX);
        Matcher matcher = pattern.matcher(sourceText);
        while (matcher.find()) {
            TextComponent component = nextParser.parse(matcher.group());
            composite.add(component);
        }
       return composite;
    }
}
