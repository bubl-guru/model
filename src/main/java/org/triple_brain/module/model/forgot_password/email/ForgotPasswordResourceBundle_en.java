/*
 * Copyright Vincent Blouin under the GPL License version 3
 */

package org.triple_brain.module.model.forgot_password.email;

import java.util.ListResourceBundle;

public class ForgotPasswordResourceBundle_en extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }
    static final Object[][] contents = {
            {"subject", "bubl.guru password reset"},
            {"body", "Follow this link to reset your password"}
    };
}
