package study.security.authoritytest.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

@Getter
@Setter
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    /**
     * Creates a new instance
     *
     * @param authentication the {@link Authentication} to use. Cannot be null.
     */
    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    private Object filterObject;
    private Object returnObject;

    @Override
    public Object getThis() {
        return null;
    }
}
