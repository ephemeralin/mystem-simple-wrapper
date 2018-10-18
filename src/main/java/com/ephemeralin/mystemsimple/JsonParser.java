package com.ephemeralin.mystemsimple;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * The type Json parser.
 */
public class JsonParser {

    private Gson gson;

    private JsonParser() {
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(Word.LexemeType.class, new LexemeTypeDeserializer());
        gson = builder.create();

    }

    /**
     * The Singleton holder.
     */
    public static class SingletonHolder {
        /**
         * The constant INSTANCE.
         */
        static final JsonParser INSTANCE = new JsonParser();
    }

    /**
     * Gets INSTANCE.
     *
     * @return the INSTANCE
     */
    public static JsonParser getInstance() {
        return JsonParser.SingletonHolder.INSTANCE;
    }

    /**
     * Parse a word.
     *
     * @param json the json
     * @return the word
     */
    public Word parse(String json) {
        return gson.fromJson(json, Word.class);
    }

    /**
     * The Lexeme type deserializer.
     */
    public static class LexemeTypeDeserializer implements JsonDeserializer<Word.LexemeType> {

        /**
         * Deserialize rules for Word.LexemeType.
         * @param jsonElement Element
         * @param type type
         * @param jsonDeserializationContext context
         * @return deserealized object
         * @throws JsonParseException ex
         */
        public Word.LexemeType deserialize(JsonElement jsonElement, Type type,
                                           JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            Word.LexemeType[] lexemeTypes = Word.LexemeType.values();
            for (Word.LexemeType lexemeType : lexemeTypes) {
                if (lexemeType.getType().equals(jsonElement.getAsString())) {
                    return lexemeType;
                }
            }
            return null;
        }
    }

}