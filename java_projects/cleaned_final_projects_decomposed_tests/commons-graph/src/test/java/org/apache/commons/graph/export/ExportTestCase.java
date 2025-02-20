package org.apache.commons.graph.export;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import static org.apache.commons.graph.CommonsGraph.export;
import static org.apache.commons.graph.CommonsGraph.newUndirectedMutableGraph;

import org.apache.commons.graph.builder.AbstractGraphConnection;
import org.apache.commons.graph.model.BaseLabeledVertex;
import org.apache.commons.graph.model.BaseLabeledWeightedEdge;
import org.apache.commons.graph.model.UndirectedMutableGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExportTestCase {

    private UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledWeightedEdge<Double>> actual;

    @Before
    public void setUp() {
        actual =
                newUndirectedMutableGraph(
                        new AbstractGraphConnection<
                                BaseLabeledVertex, BaseLabeledWeightedEdge<Double>>() {

                            public void connect0() {
                                BaseLabeledVertex start = addVertex(new BaseLabeledVertex("start"));
                                BaseLabeledVertex a = addVertex(new BaseLabeledVertex("a"));
                                BaseLabeledVertex b = addVertex(new BaseLabeledVertex("b"));
                                BaseLabeledVertex goal = addVertex(new BaseLabeledVertex("goal"));

                                addEdge(new BaseLabeledWeightedEdge<Double>("start <-> a", 1.5D))
                                        .from(start)
                                        .to(a);
                                addEdge(new BaseLabeledWeightedEdge<Double>("a <-> b", 2D))
                                        .from(a)
                                        .to(b);
                                addEdge(new BaseLabeledWeightedEdge<Double>("a <-> goal", 2D))
                                        .from(a)
                                        .to(goal);
                                addEdge(new BaseLabeledWeightedEdge<Double>("b <-> goal", 2D))
                                        .from(b)
                                        .to(goal);
                            }
                        });
    }

    @After
    public void tearDown() {
        actual = null;
    }

    @Test
    public void testShouldPrintDotFormat_test0_decomposed() throws Exception {
        export(actual);
    }

    @Test
    public void testShouldPrintDotFormat_test1_decomposed() throws Exception {
        export(actual);
        export(actual).withName("DotFormatGraph");
    }

    @Test
    public void testShouldPrintDotFormat_test2_decomposed() throws Exception {
        export(actual);
        export(actual).withName("DotFormatGraph");
        export(actual).withName("DotFormatGraph").usingDotNotation();
    }

    @Test
    public void testShouldPrintDotFormat_test3_decomposed() throws Exception {
        export(actual);
        export(actual).withName("DotFormatGraph");
        export(actual).withName("DotFormatGraph").usingDotNotation();
        export(actual).withName("DotFormatGraph").usingDotNotation().withVertexLabels(new VertexLabelMapper());
    }

    @Test
    public void testShouldPrintDotFormat_test4_decomposed() throws Exception {
        export(actual);
        export(actual).withName("DotFormatGraph");
        export(actual).withName("DotFormatGraph").usingDotNotation();
        export(actual).withName("DotFormatGraph").usingDotNotation().withVertexLabels(new VertexLabelMapper());
        export(actual).withName("DotFormatGraph").usingDotNotation().withVertexLabels(new VertexLabelMapper()).withEdgeWeights(new EdgeWeightMapper());
    }

    @Test
    public void testShouldPrintDotFormat_test5_decomposed() throws Exception {
        export(actual);
        export(actual).withName("DotFormatGraph");
        export(actual).withName("DotFormatGraph").usingDotNotation();
        export(actual).withName("DotFormatGraph").usingDotNotation().withVertexLabels(new VertexLabelMapper());
        export(actual).withName("DotFormatGraph").usingDotNotation().withVertexLabels(new VertexLabelMapper()).withEdgeWeights(new EdgeWeightMapper());
        export(actual).withName("DotFormatGraph").usingDotNotation().withVertexLabels(new VertexLabelMapper()).withEdgeWeights(new EdgeWeightMapper()).withEdgeLabels(new EdgeLabelMapper());
    }

    @Test
    public void testShouldPrintDotFormat_test6_decomposed() throws Exception {
        export(actual);
        export(actual).withName("DotFormatGraph");
        export(actual).withName("DotFormatGraph").usingDotNotation();
        export(actual).withName("DotFormatGraph").usingDotNotation().withVertexLabels(new VertexLabelMapper());
        export(actual).withName("DotFormatGraph").usingDotNotation().withVertexLabels(new VertexLabelMapper()).withEdgeWeights(new EdgeWeightMapper());
        export(actual).withName("DotFormatGraph").usingDotNotation().withVertexLabels(new VertexLabelMapper()).withEdgeWeights(new EdgeWeightMapper()).withEdgeLabels(new EdgeLabelMapper());
        export(actual)
                .withName("DotFormatGraph")
                .usingDotNotation()
                .withVertexLabels(new VertexLabelMapper())
                .withEdgeWeights(new EdgeWeightMapper())
                .withEdgeLabels(new EdgeLabelMapper())
                .to1(System.out);
    }
}