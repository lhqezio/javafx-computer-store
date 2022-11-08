package com.gitlab.lhqezio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;

import org.junit.Test;

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
        Path usersCsv = (new File(App.gitIgnoreDir_, "users.csv")).toPath();
        LoginChecker loginChecker_ = new LoginChecker(usersCsv);

        assertEquals(0, loginChecker_.check("someone", "abc".toCharArray()));
        assertEquals(2, loginChecker_.check("someone", "acc".toCharArray()));
        assertEquals(1, loginChecker_.check("AAAAAAA", "abc".toCharArray()));

    }
}
