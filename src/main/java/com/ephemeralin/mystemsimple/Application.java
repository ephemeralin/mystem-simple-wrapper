package com.ephemeralin.mystemsimple;

import com.ephemeralin.mystemsimple.stemmer.Stemmer;
import com.ephemeralin.mystemsimple.stemmer.Options;

import java.util.List;

/**
 * The Application.
 */
public class Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //todo: setPrintGramInfo, setPrintEnglishGrammemes - add grammatical analysis
        //todo: setContextualDisambiguation - it seems that doesn't work
        //todo: setFilterGrammemes - check correct behavior
        Options options = new Options();
        options
                .setOutputFormat(Options.OutputFormatType.JSON)
                .setPrintWeight(true)
                .setPrintAllSuggestions(true)
                .setPrintOnlyDictionaryWords(false)
                .setCopyInputToOutput(false)
                .setPrintWithoutOriginalWordForms(false)
                .setPrintGramInfo(true)
                .setGlueGramInfoIfOneLemm(false)
                .setPrintEndMarker(false)
                .setEncoding(Options.EncodingType.UTF8)
                .setContextualDisambiguation(false)
                .setPrintEnglishGrammemes(false)
                .setOutputFormat(Options.OutputFormatType.JSON);
        Stemmer stemmer = new Stemmer(options);
        List<Word> results = stemmer.stem("Глокая куздра штеко будланула бокра и курдячит бокрёнка.");
        int aa = 1;
    }
}
