package org.triple_brain.module.model.json.graph;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.triple_brain.module.model.graph.Vertex;
import org.triple_brain.module.model.json.SuggestionJsonFields;
import org.triple_brain.module.model.suggestion.Suggestion;

import java.util.List;

/**
 * Copyright Mozilla Public License 1.1
 */
public class VertexJsonFields extends GraphElementJsonFields{
    public static final String ID = "uri";
    public static final String LABEL = "label";
    public static final String NOTE = "note";
    public static final String IS_FRONTIER_VERTEX_WITH_HIDDEN_VERTICES = "is_frontier_vertex_with_hidden_vertices";
    public static final String NUMBER_OF_HIDDEN_CONNECTED_VERTICES = "number_of_hidden_connected_vertices";
    public static final String NAME_OF_HIDDEN_PROPERTIES = "name_of_hidden_properties";
    public static final String SUGGESTIONS = "suggestions";
    public static final String IS_PUBLIC = "is_public";

    public static JSONObject toJson(Vertex vertex) {
        try {
            JSONObject jsonVertex = new JSONObject()
                    .put(ID, vertex.uri())
                    .put(
                            LABEL,
                            vertex.label().trim().isEmpty() ?
                                    Vertex.EMPTY_LABEL :
                                    vertex.label())
                    .put(
                            NOTE,
                            vertex.note()
                    )
                    .put(
                            SUGGESTIONS, jsonSuggestions(vertex))
                    .put(
                            TYPES, jsonAdditionalTypes(vertex)
                    )
                    .put(
                            SAME_AS, jsonSameAs(vertex)
                    )
                    .put(
                            IS_PUBLIC,
                            vertex.isPublic()
                    );
            List<String> hiddenConnectedEdgesLabel = vertex.hiddenConnectedEdgesLabel();
            if (!hiddenConnectedEdgesLabel.isEmpty()) {
                jsonVertex.put(VertexJsonFields.IS_FRONTIER_VERTEX_WITH_HIDDEN_VERTICES, true);
                Integer numberOfHiddenConnectedVertices = hiddenConnectedEdgesLabel.size();
                jsonVertex.put(VertexJsonFields.NUMBER_OF_HIDDEN_CONNECTED_VERTICES, numberOfHiddenConnectedVertices);
                jsonVertex.put(NAME_OF_HIDDEN_PROPERTIES, new JSONArray(hiddenConnectedEdgesLabel));
            }
            return jsonVertex;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static JSONArray jsonSuggestions(Vertex vertex) {
        JSONArray suggestions = new JSONArray();
        for (Suggestion suggestion : vertex.suggestions()) {
            suggestions.put(
                    SuggestionJsonFields.toJson(
                            suggestion
                    )
            );
        }
        return suggestions;
    }
}
