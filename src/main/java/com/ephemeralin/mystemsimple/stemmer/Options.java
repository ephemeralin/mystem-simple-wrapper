package com.ephemeralin.mystemsimple.stemmer;

import lombok.Getter;

/**
 * Stemmer options.
 */
@Getter
public class Options {
//    -n
    private boolean rowMode;
    //-c
    private boolean copyInputToOutput;
    //-w
    private boolean printOnlyDictionaryWords;
    //-l
    private boolean printWithoutOriginalWordForms;
    //-i
    private boolean printGramInfo;
    //-g
    private boolean glueGramInfoIfOneLemm;
    //-s
    private boolean printEndMarker;
    //-e
    private EncodingType encoding;
    //d
    private boolean contextualDisambiguation;
    //--eng-gr
    private boolean printEnglishGrammemes;
    //--filter-gram
    private boolean filterGrammemes;
    //--fixlist
    private String customDictionaryPath;
    //--format
    private OutputFormatType outputFormat;
    //--generate-all
    private boolean printAllSuggestions;
    //--weight
    private boolean printWeight;

    /**
     * Instantiates a new Stemmer options.
     */
    public Options() {
        this.rowMode = true;
        this.printEnglishGrammemes = true;
    }

    /**
     * Sets copy input to output.
     *
     * @param copyInputToOutput the copy input to output
     * @return the copy input to output
     */
    public Options setCopyInputToOutput(boolean copyInputToOutput) {
        this.copyInputToOutput = copyInputToOutput;
        return this;
    }

    /**
     * Sets print only dictionary words.
     *
     * @param printOnlyDictionaryWords the print only dictionary words
     * @return the print only dictionary words
     */
    public Options setPrintOnlyDictionaryWords(boolean printOnlyDictionaryWords) {
        this.printOnlyDictionaryWords = printOnlyDictionaryWords;
        return this;
    }


    /**
     * Sets print without original word forms.
     *
     * @param printWithoutOriginalWordForms the print without original word forms
     * @return the print without original word forms
     */
    public Options setPrintWithoutOriginalWordForms(boolean printWithoutOriginalWordForms) {
        this.printWithoutOriginalWordForms = printWithoutOriginalWordForms;
        return this;
    }

    /**
     * Sets print gram info.
     *
     * @param printGramInfo the print gram info
     * @return the print gram info
     */
    public Options setPrintGramInfo(boolean printGramInfo) {
        this.printGramInfo = printGramInfo;
        return this;
    }

    /**
     * Sets glue gram info if one lemm.
     *
     * @param glueGramInfoIfOneLemm the glue gram info if one lemm
     * @return the glue gram info if one lemm
     */
    public Options setGlueGramInfoIfOneLemm(boolean glueGramInfoIfOneLemm) {
        this.glueGramInfoIfOneLemm = glueGramInfoIfOneLemm;
        return this;
    }

    /**
     * Sets print end marker.
     *
     * @param printEndMarker the print end marker
     * @return the print end marker
     */
    public Options setPrintEndMarker(boolean printEndMarker) {
        this.printEndMarker = printEndMarker;
        return this;
    }

    /**
     * Sets encoding.
     *
     * @param encoding the encoding
     * @return the encoding
     */
    public Options setEncoding(EncodingType encoding) {
        this.encoding = encoding;
        return this;
    }

    /**
     * Sets contextual disambiguation.
     *
     * @param contextualDisambiguation the contextual disambiguation
     * @return the contextual disambiguation
     */
    public Options setContextualDisambiguation(boolean contextualDisambiguation) {
        this.contextualDisambiguation = contextualDisambiguation;
        return this;
    }

    /**
     * Sets filter grammemes.
     *
     * @param filterGrammemes the filter grammemes
     * @return the filter grammemes
     */
    public Options setFilterGrammemes(boolean filterGrammemes) {
        this.filterGrammemes = filterGrammemes;
        return this;
    }

    /**
     * Sets custom dictionary path.
     *
     * @param customDictionaryPath the custom dictionary path
     * @return the custom dictionary path
     */
    public Options setCustomDictionaryPath(String customDictionaryPath) {
        this.customDictionaryPath = customDictionaryPath;
        return this;
    }

    /**
     * Sets output format.
     *
     * @param outputFormat the output format
     * @return the output format
     */
    public Options setOutputFormat(OutputFormatType outputFormat) {
        this.outputFormat = outputFormat;
        return this;
    }

    /**
     * Sets print all suggestions.
     *
     * @param printAllSuggestions the print all suggestions
     * @return the print all suggestions
     */
    public Options setPrintAllSuggestions(boolean printAllSuggestions) {
        this.printAllSuggestions = printAllSuggestions;
        return this;
    }

    /**
     * Sets print weight.
     *
     * @param printWeight the print weight
     * @return the print weight
     */
    public Options setPrintWeight(boolean printWeight) {
        this.printWeight = printWeight;
        return this;
    }

    /**
     * The enum Encoding type.
     */
    public enum EncodingType {
        /**
         * Cp 866 encoding type.
         */
        CP866,
        /**
         * Cp 1251 encoding type.
         */
        CP1251,
        /**
         * Koi 8 r encoding type.
         */
        KOI8R,
        /**
         * Utf 8 encoding type.
         */
        UTF8
    }

    /**
     * The enum Output format type.
     */
    public enum OutputFormatType {
        /**
         * Text output format type.
         */
        TEXT,
        /**
         * Xml output format type.
         */
        XML,
        /**
         * Json output format type.
         */
        JSON
    }
}
