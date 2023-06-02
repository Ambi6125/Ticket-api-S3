package sem3.project.individual.business;

import sem3.project.individual.domain.login.tokens.AccessToken;


public interface AccessTokenEncoder
{
    String encode(AccessToken token);
}
