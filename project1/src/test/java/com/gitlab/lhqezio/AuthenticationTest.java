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
        Auth Auth_ = new Auth();

        assertEquals(0, Auth_.check("someone", "abc".toCharArray()));
        assertEquals(2, Auth_.check("someone", "acc".toCharArray()));
        assertEquals(1, Auth_.check("AAAAAAA", "abc".toCharArray()));

    }
}
