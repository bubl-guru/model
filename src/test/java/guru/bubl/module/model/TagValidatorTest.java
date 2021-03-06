/*
 * Copyright Vincent Blouin under the GPL License version 3
 */

package guru.bubl.module.model;

import com.google.gson.Gson;
import guru.bubl.module.model.graph.tag.Tag;
import guru.bubl.module.model.validator.TagValidator;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import guru.bubl.module.model.graph.tag.TagPojo;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TagValidatorTest {

    private Gson gson = new Gson();

    private enum fields{
        externalResourceUri
    }

    @Test
    public void no_errors_when_external_uri_is_present()throws Exception{
        JSONObject friendlyResource = new JSONObject().put(
                fields.externalResourceUri.name(),
                "http://triple-brain.org/asviojasoviasjvoisjv"
        );
        assertFalse(validationWithJsonReturnsMessage(
                friendlyResource,
                TagValidator.TagError.EXTERNAL_URI_MANDATORY
        ));
    }

    @Test
    public void external_resource_uri_is_mandatory(){
        assertTrue(validationWithJsonReturnsMessage(
                new JSONObject(),
                TagValidator.TagError.EXTERNAL_URI_MANDATORY
        ));
    }

    @Test
    public void uri_has_to_be_valid()throws Exception{
        JSONObject friendlyResource = new JSONObject()
                .put(
                        fields.externalResourceUri.name(),
                        ""
                );
        assertTrue(validationWithJsonReturnsMessage(
                friendlyResource,
                TagValidator.TagError.EMPTY_EXTERNAL_URI
        ));
    }

    @Test
    public void can_have_relative_uris()throws Exception{
        JSONObject json = new JSONObject()
                .put(
                        fields.externalResourceUri.name(),
                        "/service/users/vince/graph/edge/c30bbev5-0c1f-4b08-b600-fb6040abb7eq/identification"
                );
        TagValidator validator = new TagValidator();
        Tag identification = gson.fromJson(
                json.toString(),
                TagPojo.class
        );
        assertTrue(
                validator.validate(
                        identification
                ).isEmpty()
        );
    }

    private boolean validationWithJsonReturnsMessage(
            JSONObject json,
            TagValidator.TagError message
    ){
        Set<TagValidator.TagError> errors ;
        errors = new TagValidator().validate(
                identificationFromJson(json)
        );
        return errors.contains(message);
    }

    private Tag identificationFromJson(JSONObject jsonObject){
        return gson.fromJson(
                jsonObject.toString(),
                TagPojo.class
        );
    }
}
