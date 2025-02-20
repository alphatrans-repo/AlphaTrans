/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.validator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * This contains a set of validation rules for a form/JavaBean. The information is contained in a
 * list of <code>Field</code> objects. Instances of this class are configured with a &lt;form&gt;
 * xml element.
 *
 * <p>The use of FastHashMap is deprecated and will be replaced in a future release.
 *
 * @version $Revision$
 */
public class Form implements Serializable {

    private static final long serialVersionUID = 6445211789563796371L;

    /** The name/key the set of validation rules is stored under. */
    protected String name = null;

    /**
     * List of <code>Field</code>s. Used to maintain the order they were added in although
     * individual <code>Field</code>s can be retrieved using <code>Map</code> of <code>Field</code>
     * s.
     */
    protected List<Field> lFields = new ArrayList<Field>();

    /**
     * Map of <code>Field</code>s keyed on their property value.
     *
     * @deprecated Subclasses should use getFieldMap() instead.
     */

    /**
     * The name/key of the form which this form extends from.
     *
     * @since Validator 1.2.0
     */
    protected String inherit = null;

    /**
     * Whether or not the this <code>Form</code> was processed for replacing variables in strings
     * with their values.
     */
    private boolean processed = false;

    /**
     * Gets the name/key of the set of validation rules.
     *
     * @return The name value
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name/key of the set of validation rules.
     *
     * @param name The new name value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Add a <code>Field</code> to the <code>Form</code>.
     *
     * @param f The field
     */

    /**
     * A <code>List</code> of <code>Field</code>s is returned as an unmodifiable <code>List</code>.
     *
     * @return The fields value
     */
    public List<Field> getFields() {
        return Collections.unmodifiableList(lFields);
    }

    /**
     * Returns the Field with the given name or null if this Form has no such field.
     *
     * @param fieldName The field name
     * @return The field value
     * @since Validator 1.1
     */

    /**
     * Returns true if this Form contains a Field with the given name.
     *
     * @param fieldName The field name
     * @return True if this form contains the field by the given name
     * @since Validator 1.1
     */

    /**
     * Merges the given form into this one. For any field in <code>depends</code> not present in
     * this form, include it. <code>depends</code> has precedence in the way the fields are ordered.
     *
     * @param depends the form we want to merge
     * @since Validator 1.2.0
     */

    /**
     * Processes all of the <code>Form</code>'s <code>Field</code>s.
     *
     * @param globalConstants A map of global constants
     * @param constants Local constants
     * @param forms Map of forms
     * @since Validator 1.2.0
     */

    /**
     * Returns a string representation of the object.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        StringBuilder results = new StringBuilder();

        results.append("Form: ");
        results.append(name);
        results.append("\n");

        for (Iterator<Field> i = lFields.iterator(); i.hasNext(); ) {
            results.append("\tField: \n");
            results.append(i.next());
            results.append("\n");
        }

        return results.toString();
    }

    /**
     * Validate all Fields in this Form on the given page and below.
     *
     * @param params A Map of parameter class names to parameter values to pass into validation
     *     methods.
     * @param actions A Map of validator names to ValidatorAction objects.
     * @param page Fields on pages higher than this will not be validated.
     * @return A ValidatorResults object containing all validation messages.
     * @throws ValidatorException
     */

    /**
     * Validate all Fields in this Form on the given page and below.
     *
     * @param params A Map of parameter class names to parameter values to pass into validation
     *     methods.
     * @param actions A Map of validator names to ValidatorAction objects.
     * @param page Fields on pages higher than this will not be validated.
     * @return A ValidatorResults object containing all validation messages.
     * @throws ValidatorException
     * @since 1.2.0
     */

    /**
     * Whether or not the this <code>Form</code> was processed for replacing variables in strings
     * with their values.
     *
     * @return The processed value
     * @since Validator 1.2.0
     */
    public boolean isProcessed() {
        return processed;
    }

    /**
     * Gets the name/key of the parent set of validation rules.
     *
     * @return The extends value
     * @since Validator 1.2.0
     */
    public String getExtends() {
        return inherit;
    }

    /**
     * Sets the name/key of the parent set of validation rules.
     *
     * @param inherit The new extends value
     * @since Validator 1.2.0
     */
    public void setExtends(String inherit) {
        this.inherit = inherit;
    }

    /**
     * Get extends flag.
     *
     * @return The extending value
     * @since Validator 1.2.0
     */
    public boolean isExtending() {
        return inherit != null;
    }

    /**
     * Returns a Map of String field keys to Field objects.
     *
     * @return The fieldMap value
     * @since Validator 1.2.0
     */
}
