package by.epam.jwdcomposite.util;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComposite;

import static by.epam.jwdcomposite.composite.TextComponentType.*;

public class DelimiterDetector {

    private static final String SPACE = " ";
    private static final String TAB = "\t";
    private static final String NEW_LINE = "\r\n";
    private static final String BACKSPACE = "\b";
    private static final String EMPTY = "";

    public static String componentPrefix(TextComponent composite) {
        return switch (composite.getTextComponentType()) {
            case PARAGRAPH -> TAB;
            default -> EMPTY;
        };
    }

    public static String componentPostfix(TextComponent composite) {
        return switch (composite.getTextComponentType()) {
            case PARAGRAPH -> BACKSPACE.concat(NEW_LINE);
            case LEXEME -> SPACE;
            default -> EMPTY;
        };
    }

    public static String componentPrefix(TextComponent composit, TextComponent component) {
        return switch (composit.getTextComponentType()) {
            case SENTENCE -> {
                yield switch (component.getTextComponentType()) {
                    case LEXEME -> {
                        TextComponent firstComponent = (component.getComponentsList().get(0));
                        if (PUNCTUATION.equals(firstComponent.getTextComponentType())
                                && '.' == firstComponent.toString().charAt(0)) {
                            yield BACKSPACE;
                        }
                        yield EMPTY;
                    }
                    default -> EMPTY;
                };
            }
            default -> EMPTY;
        };
    }

    public static String componentPostfix(TextComponent composit, TextComponent component) {
        return switch (composit.getTextComponentType()) {
            case SENTENCE -> {
                yield switch (component.getTextComponentType()) {
                    case WORD, EXPRESSION, PUNCTUATION -> SPACE;
                    default -> EMPTY;
                };
            }
            default -> EMPTY;
        };
    }
}
