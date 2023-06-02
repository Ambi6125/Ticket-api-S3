package sem3.project.individual.business;

import sem3.project.individual.domain.login.tokens.AccessToken;

/**
 * This class is able to decode access tokens.
 */
public interface AccessTokenDecoder
{
    AccessToken decode(String encodedToken);
}
