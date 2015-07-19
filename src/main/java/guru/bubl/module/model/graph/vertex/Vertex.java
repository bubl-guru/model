/*
 * Copyright Vincent Blouin under the GPL License version 3
 */

package guru.bubl.module.model.graph.vertex;

import guru.bubl.module.model.PublicPrivateElement;
import guru.bubl.module.model.graph.edge.Edge;
import guru.bubl.module.model.graph.GraphElement;
import guru.bubl.module.model.suggestion.SuggestionPojo;

import java.net.URI;
import java.util.Map;

public interface Vertex extends GraphElement, PublicPrivateElement {
    Integer getNumberOfConnectedEdges();
    Map<URI, SuggestionPojo> getSuggestions();
    Map<URI, ?extends Vertex> getIncludedVertices();
    Map<URI, ?extends Edge> getIncludedEdges();
}

