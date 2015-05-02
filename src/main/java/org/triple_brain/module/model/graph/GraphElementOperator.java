/*
 * Copyright Vincent Blouin under the GPL License version 3
 */

package org.triple_brain.module.model.graph;

public interface GraphElementOperator extends GraphElement, IdentificationOperator {
    void remove();
    void removeIdentification(Identification type);
    IdentificationPojo addType(Identification type);
    IdentificationPojo addSameAs(Identification friendlyResource);
    IdentificationPojo addGenericIdentification(Identification friendlyResource);
}
