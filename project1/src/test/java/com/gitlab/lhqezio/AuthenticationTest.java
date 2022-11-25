package com.gitlab.lhqezio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gitlab.lhqezio.user.Auth;

/**
 * Unit test for simple App.
 */
public class AuthenticationTest
{
    @Test
    public void authenticationTest()
    {
        Auth auth = new Auth();

        assertEquals(0, auth.check("someone", "abc".toCharArray()));
        assertEquals(2, auth.check("someone", "acc".toCharArray()));
        assertEquals(1, auth.check("AAAAAAA", "abc".toCharArray()));

    }
}
