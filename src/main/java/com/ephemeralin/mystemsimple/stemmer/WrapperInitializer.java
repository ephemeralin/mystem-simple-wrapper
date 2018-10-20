package com.ephemeralin.mystemsimple.stemmer;

import lombok.extern.log4j.Log4j2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The Options initializer.
 */
@Log4j2
class WrapperInitializer {
    private Properties properties;


    /**
     * Instantiates a new Wrapper initializer.
     */
    WrapperInitializer() {
        try {
            InputStream is = getClass().getResourceAsStream("/properties.properties");
            this.properties = new Properties();
            this.properties.load(is);
        } catch (Exception e) {
            log.error("Properties file was not found!", e);
        }
    }

    /**
     * Gets path to mystem binary file.
     *
     * @return the path
     */
    String getPath() {
        return properties.getProperty("path");
    }

    /**
     * Init commands list.
     *
     * @param options the options
     * @return the list of commands
     */
    public List<String> initCommands(Options options) throws InvalidOptionsException {
        if (options.getOutputFormat() != Options.OutputFormatType.JSON) {
            throw new InvalidOptionsException("Can't use method stem with data in text of xml formats");
        }

        List<String> commands = new ArrayList<String>();
        commands.add(getPath());
        try {
            setFlagCommands(options, commands);
        } catch (InvalidOptionsException e) {
            log.error(e.getMessage());
        }
        setEncodingCommand(options.getEncoding(), commands);
        setCustomDictionaryPathCommand(options.getCustomDictionaryPath(), commands);
        setOutputFormatCommand(options.getOutputFormat(), commands);

        return commands;
    }

    void setFlagCommands(Options options, List<String> commands) throws InvalidOptionsException {
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



    void setEncodingCommand(Options.EncodingType encodingType, List<String> commands) {
        if (encodingType != null) {
            commands.add("-e");
            switch (encodingType) {
                case CP866: commands.add("cp866"); break;
                case UTF8: commands.add("utf-8"); break;
                case KOI8R: commands.add("koi8-r"); break;
                case CP1251: commands.add("cp1251"); break;
                default: commands.add("utf-8");
            }
        }
    }

    void setCustomDictionaryPathCommand(String path, List<String> commands) {
        if (path != null && !path.isEmpty()) {
            commands.add("--fixlist");
            commands.add(path);
        }
    }

    void setOutputFormatCommand(Options.OutputFormatType outputFormat, List<String> commands) {
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
    static class InvalidOptionsException extends Exception {
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
