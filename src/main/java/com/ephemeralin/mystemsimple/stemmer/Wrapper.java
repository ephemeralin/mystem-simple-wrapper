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

    /**
     * Instantiates a new Wrapper.
     */
    Wrapper() {

    }


    /**
     * Analyze list.
     *
     * @param commands the commands
     * @param input    the input
     * @return the list
     */
    public List<String> analyze(List<String> commands, String input) {
        List<String> result = new ArrayList<String>();
        try {
            Process process = Runtime.getRuntime().exec(commands.toArray(new String[commands.size()]));
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
        return result;
    }
}
