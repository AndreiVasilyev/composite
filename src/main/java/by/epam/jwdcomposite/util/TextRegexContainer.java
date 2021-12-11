package by.epam.jwdcomposite.util;

public final class TextRegexContainer {

    public static final String PARAGRAPH_DELIMITER_REGEX = "\\r\\n\\t";
    public static final String SENTENCE_REGEX = "\\p{Upper}.*?[.!?…](?=\\s|$)";
    public static final String LEXEME_REGEX = "(\\(.+ .+\\)\\p{Punct})|((?<=^|\\s).+?(?=\\s|$|\\.\\w))|(\\..+?(?=\\s|$))";
    public static final String WORD_REGEX = "^([\\wа-яА-я]+[-']?)*[\\wа-яА-Я]$";
    public static final String BIT_EXPRESSION_REGEX = "^[\\d~(][\\d~\\&\\|()^\\<\\>]+[\\d)]$";
    public static final String EXPRESSION_REGEX = "(^\\(.*\\)$)|(^[a-zA-Z].*[=\\?\\s].*[a-zA-Z]$)";
    public static final String EXPRESSION_WITHOUT_BRACKETS_REGEX = "(^[a-zA-Z].*[=\\?\\s].*[a-zA-Z]$)";
    public static final String PUNCTUATION_REGEX = "([\\p{Punct}|…|\\“&&[^(]]*)(.*[\\wа-яА-Я|-|\\)])([\\p{Punct}|…|”]*)$";
    public static final String SINGLE_PUNCTUATION_REGEX = "[-\\*\\#\\+=\\&\\|\\\\\\/]";
    public static final String SEPARATE_LEXEME_REGEX = "(\\w*)?(\\([^\\(\\)]*\\))(\\w*)";
    public static final String BRACKETS_BLOCK_REGEX = "^\\(.*\\)$";
    public static final String BRACKETS_CONTENT_REGEX = "(?<=\\().*?(?=\\))";
    public static final String VOWEL_LETTERS_REGEX = "[AEIOUYaeiouyАУОЫИЭЯЮЁЕауоыиэяюёе]";
    public static final String CONSONANT_LETTERS_REGEX = "[a-zA-zа-яА-я&&[^AEIOUYaeiouyАУОЫИЭЯЮЁЕауоыиэяюёе]]";

    private TextRegexContainer() {
    }
}
