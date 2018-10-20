package com.ephemeralin.mystemsimple.stemmer;

import com.ephemeralin.mystemsimple.JsonParser;
import com.ephemeralin.mystemsimple.Word;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;


/**
 * The Stemmer.
 */
@Log4j2
@Data
@AllArgsConstructor
public class Stemmer {
    private Options options;

    /**
     * Stem text and return raw strings.
     *
     * @param inputText the input text
     * @return the list of raw stemmed strings
     */
    List<String> rawStem(String inputText) {
        List<String> commands = new ArrayList<String>();
        try {
            commands = new WrapperInitializer().initCommands(options);
        } catch (WrapperInitializer.InvalidOptionsException e) {
            log.error("Error while trying to analyze data", e);
        }
        List<String> analyze = new Wrapper().analyze(commands, inputText);
        return analyze;
    }

    /**
     * Stem text and return objects.
     *
     * @param inputText the input text
     * @return the list of objects
     */
    public List<Word> stem(String inputText) {
        List<Word> results = new ArrayList<Word>();
        List<String> rawResults = rawStem(inputText);
        for (String json : rawResults) {
            results.add(JsonParser.getInstance().parse(json));
        }
        return results;
    }
}
