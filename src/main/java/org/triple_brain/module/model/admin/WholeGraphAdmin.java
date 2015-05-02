/*
 * Copyright Vincent Blouin under the GPL License version 3
 */

package org.triple_brain.module.model.admin;

import org.triple_brain.module.model.WholeGraph;
import org.triple_brain.module.model.graph.vertex.VertexInSubGraphOperator;
import org.triple_brain.module.model.graph.vertex.VertexOperator;

import java.util.Iterator;

public class WholeGraphAdmin {
    private WholeGraph wholeGraph;
    public WholeGraphAdmin(WholeGraph wholeGraph){
        this.wholeGraph = wholeGraph;
    }
    public void refreshNumberOfConnectedEdges(){
        Iterator<VertexInSubGraphOperator> vertexIt = wholeGraph.getAllVertices();
        while(vertexIt.hasNext()){
            VertexOperator vertex = vertexIt.next();
            vertex.setNumberOfConnectedEdges(
                    vertex.connectedEdges().size()
            );
        }
    }
}
