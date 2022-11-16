package com.gitlab.lhqezio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;

import org.junit.Test;

import com.gitlab.lhqezio.util.CSV_Util;
import com.gitlab.lhqezio.user.Auth;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void authenticationTest()
    {
        Auth Auth_ = new Auth();

        assertEquals(0, Auth_.check("someone", "abc".toCharArray()));
        assertEquals(2, Auth_.check("someone", "acc".toCharArray()));
        assertEquals(1, Auth_.check("AAAAAAA", "abc".toCharArray()));

    }
}
