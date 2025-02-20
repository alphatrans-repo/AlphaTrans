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

package org.apache.commons.exec.environment;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.exec.OS;
import org.junit.jupiter.api.Test;

/**
 * Tests {@link EnvironmentUtils}.
 */
public class EnvironmentUtilsTest {

    /**
     * Accessing environment variables is case-sensitive or not depending on the operating system but the values of the environment variable are always
     * case-sensitive. So make sure that this assumption holds on all operating systems.
     *
     * @throws Exception the test failed
     */
    

    /**
     * Test to access the environment variables of the current process. Please note that this test does not run on java-gjc.
     *
     * @throws IOException the test failed
     */
    

    /**
     * On Windows platforms test that accessing environment variables can be done in a case-insensitive way, e.g. "PATH", "Path" and "path" would reference the
     * same environment variable.
     *
     * @throws IOException the test failed
     */
    

    /**
     * Tests the behavior of the EnvironmentUtils.toStrings() when using a {@code null} environment.
     */
    

    /**
     * Tests the behavior of the EnvironmentUtils.toStrings() when using a {@code null} key given to the map.
     */
    

    /**
     * Tests the behavior of the EnvironmentUtils.toStrings() when using a {@code null} value given to the map.
     */

    @Test
    public void testCaseInsensitiveVariableLookup_test0_decomposed() throws Exception {
        final Map<String, String> procEnvironment = EnvironmentUtils.getProcEnvironment();
    }

    @Test
    public void testCaseInsensitiveVariableLookup_test1_decomposed() throws Exception {
        final Map<String, String> procEnvironment = EnvironmentUtils.getProcEnvironment();
        EnvironmentUtils.addVariableToEnvironment(procEnvironment, "foo=bAr");
    }

    @Test
    public void testCaseInsensitiveVariableLookup_test2_decomposed() throws Exception {
        final Map<String, String> procEnvironment = EnvironmentUtils.getProcEnvironment();
        EnvironmentUtils.addVariableToEnvironment(procEnvironment, "foo=bAr");
        assertEquals("bAr", procEnvironment.get("foo"));
    }

    @Test
    public void testGetProcEnvironment_test0_decomposed() throws IOException {
        final Map<String, String> procEnvironment = EnvironmentUtils.getProcEnvironment();
    }

    @Test
    public void testGetProcEnvironment_test1_decomposed() throws IOException {
        final Map<String, String> procEnvironment = EnvironmentUtils.getProcEnvironment();
        assertFalse(procEnvironment.isEmpty(), "Expecting non-zero environment size");
    }

    @Test
    public void testGetProcEnvironment_test2_decomposed() throws IOException {
        final Map<String, String> procEnvironment = EnvironmentUtils.getProcEnvironment();
        assertFalse(procEnvironment.isEmpty(), "Expecting non-zero environment size");
        final String[] envArgs = EnvironmentUtils.toStrings(procEnvironment);
    }

    @Test
    public void testGetProcEnvironment_test3_decomposed() throws IOException {
        final Map<String, String> procEnvironment = EnvironmentUtils.getProcEnvironment();
        assertFalse(procEnvironment.isEmpty(), "Expecting non-zero environment size");
        final String[] envArgs = EnvironmentUtils.toStrings(procEnvironment);
        for (int i = 0; i < envArgs.length; i++) {
            assertNotNull(envArgs[i], "Entry " + i + " should not be null");
            assertFalse(envArgs[i].isEmpty(), "Entry " + i + " should not be empty");
            // System.out.println(envArgs[i]);
        }
    }

    @Test
    public void testGetProcEnvironmentCaseInsensitiveLookup_test0_decomposed() throws IOException {
        if (!OS.isFamilyWindows()) {
            return;
        }
    }

    @Test
    public void testGetProcEnvironmentCaseInsensitiveLookup_test1_decomposed() throws IOException {
        if (!OS.isFamilyWindows()) {
            return;
        }
        final Map<String, String> procEnvironment = EnvironmentUtils.getProcEnvironment();
    }

    @Test
    public void testGetProcEnvironmentCaseInsensitiveLookup_test2_decomposed() throws IOException {
        if (!OS.isFamilyWindows()) {
            return;
        }
        final Map<String, String> procEnvironment = EnvironmentUtils.getProcEnvironment();
        for (final Entry<String, String> entry : procEnvironment.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            assertEquals(value, procEnvironment.get(key.toLowerCase(Locale.ENGLISH)));
            assertEquals(value, procEnvironment.get(key.toUpperCase(Locale.ENGLISH)));
        }
    }

    @Test
    public void testGetProcEnvironmentCaseInsensitiveLookup_test3_decomposed() throws IOException {
        if (!OS.isFamilyWindows()) {
            return;
        }
        final Map<String, String> procEnvironment = EnvironmentUtils.getProcEnvironment();
        for (final Entry<String, String> entry : procEnvironment.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            assertEquals(value, procEnvironment.get(key.toLowerCase(Locale.ENGLISH)));
            assertEquals(value, procEnvironment.get(key.toUpperCase(Locale.ENGLISH)));
        }
        EnvironmentUtils.addVariableToEnvironment(procEnvironment, "foo=bar");
    }

    @Test
    public void testGetProcEnvironmentCaseInsensitiveLookup_test4_decomposed() throws IOException {
        if (!OS.isFamilyWindows()) {
            return;
        }
        final Map<String, String> procEnvironment = EnvironmentUtils.getProcEnvironment();
        for (final Entry<String, String> entry : procEnvironment.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            assertEquals(value, procEnvironment.get(key.toLowerCase(Locale.ENGLISH)));
            assertEquals(value, procEnvironment.get(key.toUpperCase(Locale.ENGLISH)));
        }
        EnvironmentUtils.addVariableToEnvironment(procEnvironment, "foo=bar");
        assertEquals("bar", procEnvironment.get("FOO"));
        assertEquals("bar", procEnvironment.get("Foo"));
        assertEquals("bar", procEnvironment.get("foo"));
    }

    @Test
    public void testToStrings_test0_decomposed()  {
        assertNull(EnvironmentUtils.toStrings(null));
    }

    @Test
    public void testToStrings_test1_decomposed()  {
        assertNull(EnvironmentUtils.toStrings(null));
        final Map<String, String> env = new HashMap<>();
        assertArrayEquals(new String[0], EnvironmentUtils.toStrings(env));
    }

    @Test
    public void testToStrings_test2_decomposed()  {
        assertNull(EnvironmentUtils.toStrings(null));
        final Map<String, String> env = new HashMap<>();
        assertArrayEquals(new String[0], EnvironmentUtils.toStrings(env));
        env.put("foo2", "bar2");
        env.put("foo", "bar");
        final String[] envStrings = EnvironmentUtils.toStrings(env);
    }

    @Test
    public void testToStrings_test3_decomposed()  {
        assertNull(EnvironmentUtils.toStrings(null));
        final Map<String, String> env = new HashMap<>();
        assertArrayEquals(new String[0], EnvironmentUtils.toStrings(env));
        env.put("foo2", "bar2");
        env.put("foo", "bar");
        final String[] envStrings = EnvironmentUtils.toStrings(env);
        final String[] expected = { "foo2=bar2", "foo=bar" };
        Arrays.sort(expected);
        Arrays.sort(envStrings);
        assertArrayEquals(expected, envStrings);
    }

    @Test
    public void testToStringWithNullKey_test0_decomposed()  {
        final Map<String, String> env = new HashMap<>();
        env.put(null, "TheNullKey");
        final String[] strings = EnvironmentUtils.toStrings(env);
    }

    @Test
    public void testToStringWithNullKey_test1_decomposed()  {
        final Map<String, String> env = new HashMap<>();
        env.put(null, "TheNullKey");
        final String[] strings = EnvironmentUtils.toStrings(env);
        assertEquals(1, strings.length);
        assertEquals("=TheNullKey", strings[0]);
    }

    @Test
    public void testToStringWithNullValue_test0_decomposed()  {
        final Map<String, String> env = new HashMap<>();
        env.put("key", null);
        final String[] strings = EnvironmentUtils.toStrings(env);
    }

    @Test
    public void testToStringWithNullValue_test1_decomposed()  {
        final Map<String, String> env = new HashMap<>();
        env.put("key", null);
        final String[] strings = EnvironmentUtils.toStrings(env);
        assertEquals(1, strings.length);
        assertEquals("key=", strings[0]);
    }
}