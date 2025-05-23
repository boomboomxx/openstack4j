package org.openstack4j.model.identity.v3;

import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.ResourceEntity;

public interface Authentication extends ModelEntity {

    Identity getIdentity();

    Scope getScope();

    public interface Identity {

        Password getPassword();

        Token getToken();

        ApplicationCredential getApplicationCredential();
        List<String> getMethods();

        public interface Password {

            User getUser();

            public interface User extends ResourceEntity {

                Domain getDomain();

                String getPassword();

                public interface Domain extends ResourceEntity {
                }
            }
        }

        public interface Token {

            String getId();

        }

        interface ApplicationCredential extends ResourceEntity {

            String getSecret();

            Boolean isRestricted();

            User getUser();

            interface User extends ResourceEntity {

            }

        }

    }

    public interface Scope {

        Project getProject();

        Domain getDomain();

        public interface Project extends ResourceEntity {

            Domain getDomain();
        }

        public interface Domain extends ResourceEntity {
        }

    }

}
