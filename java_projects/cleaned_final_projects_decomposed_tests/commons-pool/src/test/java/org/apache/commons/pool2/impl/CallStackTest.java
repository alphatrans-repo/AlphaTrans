/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.apache.commons.pool2.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CallStackTest {

    private final StringWriter writer = new StringWriter();

    @ParameterizedTest
    @MethodSource("data")
    public void testPrintClearedStackTraceIsNoOp(final CallStack stack) {
        stack.fillInStackTrace();
        stack.clear();
        stack.printStackTrace(new PrintWriter(writer));
        final String stackTrace = writer.toString();
        assertEquals("", stackTrace);
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testPrintFilledStackTrace(final CallStack stack) {
        stack.fillInStackTrace();
        stack.printStackTrace(new PrintWriter(writer));
        final String stackTrace = writer.toString();
        assertTrue(stackTrace.contains(getClass().getName()));
    }

    
}