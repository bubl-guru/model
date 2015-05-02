/*
 * Copyright Vincent Blouin under the GPL License version 3
 */

package org.triple_brain.module.model.graph.edge;

import org.triple_brain.module.model.graph.GraphElementOperator;
import org.triple_brain.module.model.graph.vertex.Vertex;
import org.triple_brain.module.model.graph.vertex.VertexOperator;

public interface EdgeOperator extends Edge, GraphElementOperator{
    void inverse();
    VertexOperator sourceVertex();
    VertexOperator destinationVertex();
    VertexOperator otherVertex(Vertex vertex);
}
