/*
 * Copyright (c) 2015-2020, Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tribuo.data.columnar;

import org.tribuo.Example;
import org.tribuo.Feature;

/**
 * A Feature with extra bookkeeping for use inside the columnar package.
 * <p>
 * {@link Example}s may destroy and recreate Feature instances.
 */
public class ColumnarFeature extends Feature {
    private static final long serialVersionUID = 1L;

    public static final String CONJUNCTION = "CONJ";

    public static final String JOINER = "@";

    private final String fieldName;

    private final String firstFieldName;

    private final String secondFieldName;

    public ColumnarFeature(String fieldName, String name, double value) {
        super(generateFeatureName(fieldName,name), value);
        this.fieldName = fieldName;
        this.firstFieldName = "";
        this.secondFieldName = "";
    }

    public ColumnarFeature(String firstFieldName, String secondFieldName, String name, double value) {
        super(generateFeatureName(firstFieldName,secondFieldName,name),value);
        this.fieldName = CONJUNCTION;
        this.firstFieldName = firstFieldName;
        this.secondFieldName = secondFieldName;
    }

    /**
     * Generates a feature name based on the field name.
     * <p>
     * Uses {@link ColumnarFeature#JOINER} to join the strings.
     * @param fieldName The field name.
     * @param name The name of the extracted feature.
     * @return The new feature name.
     */
    public static String generateFeatureName(String fieldName, String name) {
        return fieldName + JOINER + name;
    }

    /**
     * Generates a feature name used for conjunction features.
     * <p>
     * Uses {@link ColumnarFeature#JOINER} to join the strings and {@link ColumnarFeature#CONJUNCTION} to prepend the name.
     * @param firstFieldName The name of the first field.
     * @param secondFieldName The name of the second field.
     * @param name The name of the extracted feature.
     * @return The new feature name.
     */
    public static String generateFeatureName(String firstFieldName, String secondFieldName, String name) {
        return CONJUNCTION + "[" + firstFieldName + "," + secondFieldName + "]" + JOINER + name;
    }

    /**
     * Gets the field name. Returns {@link ColumnarFeature#CONJUNCTION} if it's a conjunction.
     * @return The field name.
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * If it's a conjunction feature, return the first field name.
     * Otherwise return an empty String.
     * @return The first field name, or an empty string.
     */
    public String getFirstFieldName() {
        return firstFieldName;
    }

    /**
     * If it's a conjunction feature, return the second field name.
     * Otherwise return an empty String.
     * @return The second field name, or an empty string.
     */
    public String getSecondFieldName() {
        return secondFieldName;
    }
}