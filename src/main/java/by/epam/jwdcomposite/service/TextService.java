package by.epam.jwdcomposite.service;

import by.epam.jwdcomposite.composite.TextComponent;
import by.epam.jwdcomposite.composite.TextComposite;

import java.util.List;
import java.util.Map;

public interface TextService {
    List<TextComponent> sortBySentencesQuantity(TextComposite text);

    List<TextComponent> findSentencesWithLongestWord(TextComposite text);

    List<TextComponent> removeSentencesByWordsQuantity(TextComposite text, int wordsQuantity);

    Map<String, Integer> findQuantityOfSameWords(TextComposite text);

    int countVowelLetterQuantity(TextComposite sentence);

    int countConsonantsLetterQuantity(TextComposite sentence);
}
