/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.commons.exec;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * An example based on the tutorial where the user can safely play with
 * <ul>
 * <li>blocking or non-blocking print jobs
 * <li>with print job timeouts to trigger the {@code ExecuteWatchdog}
 * <li>with the {@code exitValue} returned from the print script
 * </ul>
 */
public class TutorialTest {

    private final class PrintResultHandler extends DefaultExecuteResultHandler {

        private ExecuteWatchdog watchdog;

        // public PrintResultHandler(final ExecuteWatchdog watchdog) {
        //     this.watchdog = watchdog;
        // }

        public PrintResultHandler(int constructorId, final int exitValue, final ExecuteWatchdog watchdog) {
            if (constructorId == 1) {
                this.watchdog = watchdog;
            } else {
                super.onProcessComplete(exitValue);
            }
        }

        @Override
        public void onProcessComplete(final int exitValue) {
            super.onProcessComplete(exitValue);
            System.out.println("[resultHandler] The document was successfully printed ...");
        }

        @Override
        public void onProcessFailed(final ExecuteException e) {
            super.onProcessFailed(e);
            if (watchdog != null && watchdog.killedProcess()) {
                System.err.println("[resultHandler] The print process timed out");
            } else {
                System.err.println("[resultHandler] The print process failed to do : " + e.getMessage());
            }
        }
    }

    /** The directory to pick up the test scripts */
    private final File testDir = new File("src/test/scripts");

    /** Simulates a PDF print job */
    private final File acroRd32Script = TestUtil.resolveScriptForOS(testDir + "/acrord32");

    /**
     * Simulate printing a PDF document.
     *
     * @param file              the file to print
     * @param printJobTimeout   the printJobTimeout (ms) before the watchdog terminates the print process
     * @param printInBackground printing done in the background or blocking
     * @return a print result handler (implementing a future)
     * @throws IOException the test failed
     */
    public PrintResultHandler print(final File file, final Duration printJobTimeout, final boolean printInBackground) throws IOException {

        int exitValue;
        ExecuteWatchdog watchdog = null;
        PrintResultHandler resultHandler;

        // build up the command line to using a 'java.io.File'
        final Map<String, File> map = new HashMap<>();
        map.put("file", file);
        final CommandLine commandLine = new CommandLine(1, null, acroRd32Script, null);
        commandLine.addArgument0("/p");
        commandLine.addArgument0("/h");
        commandLine.addArgument0("${file}");
        commandLine.setSubstitutionMap(map);

        // create the executor and consider the exitValue '1' as success
        final Executor executor = DefaultExecutor.builder().get();
        executor.setExitValue(1);

        // create a watchdog if requested
        if (printJobTimeout.toMillis() > 0) {
            watchdog = ExecuteWatchdog.builder().setTimeout(printJobTimeout).get();
            executor.setWatchdog(watchdog);
        }

        // pass a "ExecuteResultHandler" when doing background printing
        if (printInBackground) {
            System.out.println("[print] Executing non-blocking print job  ...");
            resultHandler = new PrintResultHandler(1, 0, watchdog);
            executor.execute1(commandLine, resultHandler);
        } else {
            System.out.println("[print] Executing blocking print job  ...");
            exitValue = executor.execute0(commandLine);
            resultHandler = new PrintResultHandler(0, exitValue, null);
        }

        return resultHandler;
    }

    @Test
    public void testTutorialExample_test0_decomposed() throws Exception {
        final Duration printJobTimeout = Duration.ofSeconds(15);
        final boolean printInBackground = false;
        final File pdfFile = new File("/Documents and Settings/foo.pdf");
        PrintResultHandler printResult;
        try {
            // printing takes around 10 seconds
            System.out.println("[main] Preparing print job ...");
            printResult = print(pdfFile, printJobTimeout, printInBackground);
            System.out.println("[main] Successfully sent the print job ...");
        } catch (final Exception e) {
            e.printStackTrace();
            fail("[main] Printing of the following document failed : " + pdfFile.getAbsolutePath());
            throw e;
        }
    }

    @Test
    public void testTutorialExample_test1_decomposed() throws Exception {
        final Duration printJobTimeout = Duration.ofSeconds(15);
        final boolean printInBackground = false;
        final File pdfFile = new File("/Documents and Settings/foo.pdf");
        PrintResultHandler printResult;
        try {
            // printing takes around 10 seconds
            System.out.println("[main] Preparing print job ...");
            printResult = print(pdfFile, printJobTimeout, printInBackground);
            System.out.println("[main] Successfully sent the print job ...");
        } catch (final Exception e) {
            e.printStackTrace();
            fail("[main] Printing of the following document failed : " + pdfFile.getAbsolutePath());
            throw e;
        }
        System.out.println("[main] Test is exiting but waiting for the print job to finish...");
        printResult.waitFor0();
    }

    @Test
    public void testTutorialExample_test2_decomposed() throws Exception {
        final Duration printJobTimeout = Duration.ofSeconds(15);
        final boolean printInBackground = false;
        final File pdfFile = new File("/Documents and Settings/foo.pdf");
        PrintResultHandler printResult;
        try {
            // printing takes around 10 seconds
            System.out.println("[main] Preparing print job ...");
            printResult = print(pdfFile, printJobTimeout, printInBackground);
            System.out.println("[main] Successfully sent the print job ...");
        } catch (final Exception e) {
            e.printStackTrace();
            fail("[main] Printing of the following document failed : " + pdfFile.getAbsolutePath());
            throw e;
        }
        System.out.println("[main] Test is exiting but waiting for the print job to finish...");
        printResult.waitFor0();
        System.out.println("[main] The print job has finished ...");
    }
}