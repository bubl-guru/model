/*
 * Copyright Vincent Blouin under the Mozilla Public License 1.1
 */

package org.triple_brain.module.model;

import com.google.inject.AbstractModule;
import org.triple_brain.module.model.forget_password.email.ForgetPasswordEmail;
import org.triple_brain.module.model.forget_password.email.ForgetPasswordEmailImpl;

import javax.inject.Singleton;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ModelModule extends AbstractModule{

    private Boolean isTesting;

    public static ModelModule forTesting(){
        return new ModelModule(
                true
        );
    }

    public ModelModule(){
        this(false);
    }

    public ModelModule(Boolean isTesting){
        this.isTesting = isTesting;
    }

    @Override
    protected void configure() {
        if(isTesting){
            testBinding();
        }else{
            nonTestBinding();
        }
        bind(UserNameGenerator.class).to(UserNameGeneratorImpl.class).in(Singleton.class);
    }

    private void testBinding(){
        bind(ForgetPasswordEmail.class).toInstance(new ForgetPasswordEmail() {
            @Override
            public Message send(User user, String resetUrl) {
                return new MimeMessage(
                        Session.getDefaultInstance(
                                new Properties(),
                                null
                        )
                );
            }
        });
    }

    private void nonTestBinding(){
        bind(ForgetPasswordEmail.class).to(ForgetPasswordEmailImpl.class).in(Singleton.class);
    }
}
