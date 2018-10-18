package com.ephemeralin.mystemsimple;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The Word.
 */
@Data
public class Word implements Serializable {
    @SerializedName("text")
    private String original;
    @SerializedName("analysis")
    private Set<Lexeme> lexemes = new HashSet<Lexeme>();

    /**
     * The Lexeme.
     */
    @Data
    private static class Lexeme {
        @SerializedName("lex")
        private String value;
        @SerializedName("wt")
        private double weight;
        @SerializedName("qual")
        private LexemeType type;
    }

    /**
     * The Lexeme type.
     */
    @Getter
    public enum LexemeType {
        /**
         * The Dictionary.
         */
        //dictionary word
        Dictionary("dictionary"),
        /**
         * The Bastard.
         */
        //non-dictionary word
        Bastard("bastard");
        //todo: make working these types
//        //from "fast" dictionary
//        Sob,
//        //dictionary word + prefix
//        Prefixoid,
//        //unknown set of letters
//        Foundling,
//        //bad lemme
//        BadRequest,
//        //translated from English
//        FromEnglish,
//        //translated to English
//        ToEnglish,
//        //"translated" from transliterated word
//        Untranslit,
//        //lemme text was overridden
//        Overrode,
//        //fix-list word
//        Fix;

        /**
         * The Type.
         */
        private String type;

        LexemeType(String type) {
            this.type = type;
        }
    }
}
