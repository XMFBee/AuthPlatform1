package com.gs.authenticator;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;

import java.util.Collection;
/**
 * Created by Administrator on 2017/5/3.
 */
public class MyModularRealmAuthenticator extends ModularRealmAuthenticator{
    @Override
    protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms, AuthenticationToken token) {
        AuthenticationStrategy strategy = getAuthenticationStrategy();
        AuthenticationInfo aggregate = strategy.beforeAllAttempts(realms, token);
        for (Realm realm : realms) {
            aggregate = strategy.beforeAttempt(realm, token, aggregate);
            if (realm.supports(token)) {
                AuthenticationInfo info = null;
                Throwable t = null;
                try {
                    info = realm.getAuthenticationInfo(token);
                } catch (Throwable throwable) {
                    t = throwable;
                }
                aggregate = strategy.afterAttempt(realm, token, info, aggregate, t);
                if (aggregate != null && !CollectionUtils.isEmpty(aggregate.getPrincipals())) {
                    return aggregate;
                }
            } else {

            }
        }
        aggregate = strategy.afterAllAttempts(token, aggregate);
        return aggregate;
    }
}
