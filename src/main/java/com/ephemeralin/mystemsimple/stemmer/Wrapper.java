package com.ephemeralin.mystemsimple.stemmer;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * The type Wrapper.
 */
@Log4j2
class Wrapper {
    private Properties properties;

    /**
     * Instantiates a new Wrapper.
     */
    private Wrapper() {
        try {
            InputStream is = getClass().getResourceAsStream("/properties.properties");
            this.properties = new Properties();
            this.properties.load(is);
        } catch (Exception e) {
            log.error("Properties file was not found!", e);
        }
    }

    /**
     * The type Singleton holder.
     */
    public static class SingletonHolder {
        /**
         * The constant INSTANCE.
         */
        static final Wrapper INSTANCE = new Wrapper();
    }

    /**
     * Gets INSTANCE.
     *
     * @return the INSTANCE
     */
    public static Wrapper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Analyze list.
     *
     * @param options the options
     * @param input   the input
     * @return the list
     * @throws InvalidOptionsException the invalid options exception
     */
    public List<String> analyze(StemmerOptions options, String input) throws InvalidOptionsException {
        List<String> result = new ArrayList<String>();
        if (options.getOutputFormat() != StemmerOptions.OutputFormatType.JSON) {
            throw new InvalidOptionsException("Can't use method stem with data in text of xml formats");
        } else {
            String[] commands = initCommands(options);
            try {
                Process process = Runtime.getRuntime().exec(commands);
                PrintWriter pw = new PrintWriter(process.getOutputStream());
                pw.write(input);
                pw.close();

                InputStream in = process.getInputStream();
                Scanner sc = new Scanner(in);
                while (sc.hasNextLine()) {
                    result.add(sc.nextLine().trim());
                }
                sc.close();

            } catch (IOException e) {
                log.error("Error when trying to run myStem binary!", e);
            }
        }
        return result;
    }

    private String[] initCommands(StemmerOptions options) {
        List<String> commands = new ArrayList<String>();
        commands.add(properties.getProperty("path"));
        try {
            setFlagCommands(options, commands);
        } catch (InvalidOptionsException e) {
            log.error(e.getMessage());
        }
        setEncodingCommand(options.getEncoding(), commands);
        setCustomDictionaryPathCommand(options.getCustomDictionaryPath(), commands);
        setOutputFormatCommand(options.getOutputFormat(), commands);

        return commands.toArray(new String[commands.size()]);
    }

    private void setFlagCommands(StemmerOptions options, List<String> commands) throws InvalidOptionsException {
        if (options.isRowMode()) {
            commands.add("-n");
        }
        if (options.isCopyInputToOutput()) {
            commands.add("-c");
        }
        if (options.isPrintOnlyDictionaryWords()) {
            commands.add("-w");
        }
        if (options.isPrintWithoutOriginalWordForms()) {
            commands.add("-l");
        }
        if (options.isPrintGramInfo()) {
            commands.add("-i");
        }
        if (options.isGlueGramInfoIfOneLemm()) {
            if (options.isPrintGramInfo()) {
                commands.add("-g");
            } else {
                throw new InvalidOptionsException("Option GlueGramInfoIfOneLemm can be set if only PrintGramInfo is set!" +
                        " You will probably have an unexpected result.");
            }
        }
        if (options.isPrintEndMarker()) {
            if (options.isCopyInputToOutput()) {
                commands.add("-s");
            } else {
                throw new InvalidOptionsException("Option PrintEndMarker can be set if only CopyInputToOutput is set!" +
                        " You will probably have an unexpected result.");
            }
        }
        if (options.isContextualDisambiguation()) {
            commands.add("-d");
        }
        if (options.isPrintEnglishGrammemes()) {
            commands.add("--eng-gr");
        }
        if (options.isFilterGrammemes()) {
            commands.add("--filter-gram");
        }
        if (options.isPrintAllSuggestions()) {
            commands.add("--generate-all");
        }
        if (options.isPrintWeight()) {
            commands.add("--weight");
        }
    }

    private void setEncodingCommand(StemmerOptions.EncodingType encodingType, List<String> commands) {
        if (encodingType != null) {
            commands.add("-e");
            switch (encodingType) {
                case CP866: commands.add("cp866"); break;
                case UTF8: commands.add("utf-8"); break;
                case KOI8R: commands.add("koi8-r,"); break;
                case CP1251: commands.add("cp1251"); break;
                default: commands.add("utf-8");
            }
        }
    }

    private void setCustomDictionaryPathCommand(String path, List<String> commands) {
        if (path != null && !path.isEmpty()) {
            commands.add("--fixlist");
            commands.add(path);
        }
    }

    private void setOutputFormatCommand(StemmerOptions.OutputFormatType outputFormat, List<String> commands) {
        if (outputFormat != null) {
            commands.add("--format");
            switch (outputFormat) {
                case JSON: commands.add("json"); break;
                case XML: commands.add("xml"); break;
                case TEXT: commands.add("text"); break;
                default: commands.add("json");
            }
        }
    }

    /**
     * The Invalid options exception.
     */
    public static class InvalidOptionsException extends Exception {
        /**
         * Instantiates a new Invalid options exception.
         *
         * @param message the message
         */
        InvalidOptionsException(String message) {
            super(message);
        }

    }
}
