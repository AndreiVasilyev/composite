package by.epam.jwdcomposite.entity;

public final class TextRegexContainer {

    public static final String PARAGRAPH_DELIMITER_REGEX="\\r\\n\\t";
    public static final String SENTENCE_REGEX="\\p{Upper}.*?[.!?…](?=\\s|$)";
    public static final String LEXEME_REGEX="((?<=^|\\s).+?(?=\\s|$|\\.\\w))|(\\..+?(?=\\s|$))";
    public static final String WORD_REGEX="";
}
