package com.ephemeralin.mystemsimple;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Json parser.
 */
public class JsonParser {

    private Gson gson;
//    private Map<String, GramType> gramTypes;

    private JsonParser() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Word.LexemeType.class, new LexemeTypeDeserializer());
        builder.registerTypeAdapter(new TypeToken<Set<GramType>>() {
                                        }.getType(),
                                            new GrammemeTypesListDeserializer());
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
        try {
            return gson.fromJson(json, Word.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * The Lexeme type deserializer.
     */
    private class LexemeTypeDeserializer implements JsonDeserializer<Word.LexemeType> {
        /**
         * Deserialize rules for Word.LexemeType.
         *
         * @param json    Element
         * @param type    type
         * @param context context
         * @return deserealized object
         * @throws JsonParseException ex
         */
        public Word.LexemeType deserialize(JsonElement json, Type type,
                                           JsonDeserializationContext context) throws JsonParseException {
            Word.LexemeType[] lexemeTypes = Word.LexemeType.values();
            for (Word.LexemeType lexemeType : lexemeTypes) {
                if (lexemeType.getType().equals(json.getAsString())) {
                    return lexemeType;
                }
            }
            return null;
        }
    }

    /**
     * The type Grammeme types list deserializer.
     */
    private class GrammemeTypesListDeserializer implements JsonDeserializer<Set<GramType>> {
        /**
         * Deserialize rules for Word.LexemeType.
         *
         * @param json    Element
         * @param type    type
         * @param context context
         * @return deserealized object
         * @throws JsonParseException ex
         */
        public Set<GramType> deserialize(JsonElement json, Type type,
                                         JsonDeserializationContext context) throws JsonParseException {
            HashSet<GramType> types = new HashSet<>();
            for (String t : json.getAsString().split("[,=]")) {
                GramType gramType = GramType.COLLECTED.get(t);
                if (gramType != null) {
                    types.add(gramType);
                }
            }
            return types;
        }
    }
}