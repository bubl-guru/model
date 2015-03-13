/*
 * Copyright Vincent Blouin under the Mozilla Public License 1.1
 */

package org.triple_brain.module.model.graph;

import java.net.URI;

public interface IdentificationFactory {
    IdentificationOperator withUri(URI uri);
}