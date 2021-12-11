package by.epam.jwdcomposite.service.impl;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComposite;
import by.epam.jwdcomposite.service.TextService;

import static by.epam.jwdcomposite.util.TextRegexContainer.*;
import static by.epam.jwdcomposite.composite.TextComponentType.*;

import java.util.*;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    @Override
    public List<TextComponent> sortBySentencesQuantity(TextComposite text) {
        return text.getComponentsList()
                .stream()
                .sorted(Comparator.comparing(paragraph -> paragraph.getComponentsList().stream().count()))
                .toList();
    }

    @Override
    public List<TextComponent> findSentencesWithLongestWord(TextComposite text) {
        String longestWord = getAllWords(text)
                .stream()
                .max(Comparator.comparing(o -> o.toString().length()))
                .orElse(new TextComposite(WORD)).toString();
        List<TextComponent> result = text.getComponentsList()
                .stream()
                .flatMap(paragraph -> paragraph.getComponentsList()
                        .stream())
                .filter(sentence -> sentence.toString().contains(longestWord))
                .toList();
        System.out.println("longest word: " + longestWord);
        return result;
    }

    @Override
    public List<TextComponent> removeSentencesByWordsQuantity(TextComposite text, int wordsQuantity) {
        return text.getComponentsList()
                .stream()
                .flatMap(paragraph -> paragraph.getComponentsList().stream())
                .<TextComponent>mapMulti((sentence, consumer) -> {
                    if (getWordsOfSentence((TextComposite) sentence).size() >= wordsQuantity) {
                        consumer.accept(sentence);
                    }
                }).toList();
    }

    @Override
    public Map<String, Integer> findQuantityOfSameWords(TextComposite text) {
        Map<String, Integer> result = new HashMap<>();
        for (TextComponent component : getAllWords(text)) {
            String word = component.toString();
            if (result.get(word) == null) {
                result.put(word.toString(), 1);
            } else {
                result.put(word, result.get(word) + 1);
            }
        }
        return result.entrySet()
                .stream()
                .filter(s -> s.getValue() > 1).collect(Collectors.toMap(
                        entry -> entry.getKey(), entry -> entry.getValue()));
    }

    @Override
    public int countVowelLetterQuantity(TextComposite sentence) {
        String vowelLetters = "AEIOUYaeiouyАУОЫИЭЯЮЁЕауоыиэяюёе";
        long result = getWordsOfSentence(sentence)
                .stream()
                .flatMap((word -> word.getComponentsList().stream()))
                .filter(symbol -> symbol.toString().matches(VOWEL_LETTERS_REGEX))
                .count();
        return (int) result;
    }

    @Override
    public int countConsonantsLetterQuantity(TextComposite sentence) {
        long result = getWordsOfSentence(sentence)
                .stream()
                .flatMap((word -> word.getComponentsList().stream()))
                .filter(symbol -> symbol.toString().matches(CONSONANT_LETTERS_REGEX))
                .count();
        return (int) result;
    }

    private List<TextComponent> getAllWords(TextComposite text) {
        return text.getComponentsList()
                .stream()
                .flatMap(paragraph -> paragraph.getComponentsList()
                        .stream()
                        .flatMap(sentence -> getWordsOfSentence((TextComposite) sentence)
                                .stream()
                        )).toList();
    }

    private List<TextComponent> getWordsOfSentence(TextComposite sentence) {
        return sentence.getComponentsList()
                .stream().filter(lexeme -> !lexeme.getTextComponentType().equals(PUNCTUATION))
                .<TextComponent>mapMulti((lexeme, consumer) -> {
                    if (!lexeme.getTextComponentType().equals(WORD)) {
                        consumer.accept(lexeme.getComponentsList()
                                .stream()
                                .<TextComponent>mapMulti((expression, wordConsumer) -> {
                                    if (expression.getTextComponentType().equals(EXPRESSION)) {
                                        expression.getComponentsList()
                                                .stream()
                                                .filter(word -> word.getTextComponentType().equals(WORD))
                                                .forEach(wordConsumer::accept);
                                    } else if (expression.getTextComponentType().equals(WORD)) {
                                        wordConsumer.accept(expression);
                                    }
                                })
                                .findFirst().orElse(new TextComposite(WORD)));
                    } else {
                        consumer.accept(lexeme);
                    }
                }).toList();
    }
}
