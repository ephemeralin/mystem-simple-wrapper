package com.ephemeralin.mystemsimple.stemmer;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * The Wrapper initializer test.
 */
@Log4j2
public class WrapperInitializerTest {
    /**
     * Init commands test.
     */
    @Test
    public void initCommandsTest() {
        List<String> commands = new ArrayList<String>();
        WrapperInitializer wrapperInitializer = new WrapperInitializer();
        Options options = new Options();
        options
                .setCopyInputToOutput(true)
                .setPrintOnlyDictionaryWords(true)
                .setPrintWithoutOriginalWordForms(true)
                .setPrintGramInfo(true)
                .setGlueGramInfoIfOneLemm(true)
                .setPrintEndMarker(true)
                .setEncoding(Options.EncodingType.UTF8)
                .setContextualDisambiguation(true)
                .setOutputFormat(Options.OutputFormatType.JSON)
                .setPrintWeight(true)
                .setPrintAllSuggestions(true);
        try {
            commands = wrapperInitializer.initCommands(options);
        } catch (WrapperInitializer.InvalidOptionsException e) {
            log.error("Error when testing initCommands (FAILED)", e);
        }
        String expectedCommands =
                "[/Applications/mystem, -n, -c, -w, -l, -i, -g, -s, -d, --eng-gr, --generate-all, --weight, -e, utf-8, --format, json]";
        assertThat(commands.toString(), is(expectedCommands));
    }

    /**
     * Init commands almost empty test.
     */
    @Test
    public void initCommandsAlmostEmptyTest() {
        List<String> commands = new ArrayList<String>();
        WrapperInitializer wrapperInitializer = new WrapperInitializer();
        Options options = new Options();
        options
                .setOutputFormat(Options.OutputFormatType.JSON);
        try {
            commands = wrapperInitializer.initCommands(options);
        } catch (WrapperInitializer.InvalidOptionsException e) {
            log.error("Error when testing initCommands (FAILED)", e);
        }
        String expectedCommands =
                "[/Applications/mystem, -n, --eng-gr, --format, json]";
        assertThat(commands.toString(), is(expectedCommands));
    }

    /**
     * Init commands throwing exception test.
     *
     * @throws WrapperInitializer.InvalidOptionsException the invalid options exception
     */
    @Test(expected = WrapperInitializer.InvalidOptionsException.class)
    public void initCommandsThrowExceptionTest() throws WrapperInitializer.InvalidOptionsException {
        WrapperInitializer wrapperInitializer = new WrapperInitializer();
        Options options = new Options();
        options
                .setOutputFormat(Options.OutputFormatType.TEXT);
        wrapperInitializer.initCommands(options);
    }

    /**
     * Sets flag commands test.
     */
    @Test
    public void setFlagCommandsTest() {
        Options options = new Options();
        options
                .setCopyInputToOutput(true)
                .setPrintOnlyDictionaryWords(true)
                .setPrintGramInfo(true)
                .setGlueGramInfoIfOneLemm(true)
                .setPrintEndMarker(true)
                .setContextualDisambiguation(true)
                .setPrintWithoutOriginalWordForms(true)
                .setPrintAllSuggestions(true)
                .setPrintWeight(true);

        List<String> commands = new ArrayList<String>();
        WrapperInitializer wrapperInitializer = new WrapperInitializer();
        try {
            wrapperInitializer.setFlagCommands(options, commands);
        } catch (WrapperInitializer.InvalidOptionsException e) {
            log.error("Error when testing setFlagCommands (FAILED)", e);
        }
        String expectedCommands = "[-n, -c, -w, -l, -i, -g, -s, -d, --eng-gr, --generate-all, --weight]";
        assertThat(commands.toString(), is(expectedCommands));
    }

    /**
     * Sets flag commands test.
     *
     * @throws WrapperInitializer.InvalidOptionsException the invalid options exception
     */
    @Test(expected = WrapperInitializer.InvalidOptionsException.class)
    public void setFlagCommandGlueGramInfoIfOneLemmThrowsExceptionTest() throws WrapperInitializer.InvalidOptionsException {
        Options options = new Options();
        options
                .setPrintGramInfo(false)
                .setGlueGramInfoIfOneLemm(true);

        new WrapperInitializer().setFlagCommands(options, new ArrayList<String>());
    }

    /**
     * Sets flag commands test.
     *
     * @throws WrapperInitializer.InvalidOptionsException the invalid options exception
     */
    @Test(expected = WrapperInitializer.InvalidOptionsException.class)
    public void setFlagCommandPrintEndMarkerThrowsExceptionTest() throws WrapperInitializer.InvalidOptionsException {
        Options options = new Options();
        options
                .setCopyInputToOutput(false)
                .setPrintEndMarker(true);
        new WrapperInitializer().setFlagCommands(options, new ArrayList<String>());
    }

    /**
     * Sets encoding command.
     */
    @Test
    public void setEncodingCommand() {
        WrapperInitializer wrapperInitializer = new WrapperInitializer();

        List<String> commands = new ArrayList<String>();
        wrapperInitializer.setEncodingCommand(Options.EncodingType.UTF8, commands);
        assertThat(commands.toString(), is("[-e, utf-8]"));
        commands.clear();

        wrapperInitializer.setEncodingCommand(Options.EncodingType.CP866, commands);
        assertThat(commands.toString(), is("[-e, cp866]"));
        commands.clear();

        wrapperInitializer.setEncodingCommand(Options.EncodingType.CP1251, commands);
        assertThat(commands.toString(), is("[-e, cp1251]"));
        commands.clear();

        wrapperInitializer.setEncodingCommand(Options.EncodingType.KOI8R, commands);
        assertThat(commands.toString(), is("[-e, koi8-r]"));
    }

    /**
     * Sets custom dictionary path command test.
     */
    @Test
    public void setOutputFormatCommandTest() {
        WrapperInitializer wrapperInitializer = new WrapperInitializer();

        List<String> commands = new ArrayList<String>();
        wrapperInitializer.setOutputFormatCommand(Options.OutputFormatType.JSON, commands);
        assertThat(commands.toString(), is("[--format, json]"));
        commands.clear();

        wrapperInitializer.setOutputFormatCommand(Options.OutputFormatType.TEXT, commands);
        assertThat(commands.toString(), is("[--format, text]"));
        commands.clear();

        wrapperInitializer.setOutputFormatCommand(Options.OutputFormatType.XML, commands);
        assertThat(commands.toString(), is("[--format, xml]"));
    }

    /**
     * Sets custom dictionary path command test.
     */
    @Test
    public void setCustomDictionaryPathCommandTest() {
        WrapperInitializer wrapperInitializer = new WrapperInitializer();

        List<String> commands = new ArrayList<String>();
        wrapperInitializer.setCustomDictionaryPathCommand("/path/to/dict/", commands);
        assertThat(commands.toString(), is("[--fixlist, /path/to/dict/]"));
        commands.clear();
    }
}