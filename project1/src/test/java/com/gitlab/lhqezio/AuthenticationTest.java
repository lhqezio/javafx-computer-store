package com.gitlab.lhqezio;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.Test;

import com.gitlab.lhqezio.user.Auth;
import com.gitlab.lhqezio.util.CsvLoader;
import com.gitlab.lhqezio.util.DataLoader;

/**
 * Unit test for Authentication.
 * @author Fu Pei
 */

public class AuthenticationTest
{
    @Test
    public void authenticationTest()
    {
        DataLoader dataLoader = new CsvLoader(Paths.get("test_csv_files"));
        Auth auth = new Auth(dataLoader);

        assertEquals(0, auth.check("someone", "abc".toCharArray()));
        assertEquals(2, auth.check("someone", "acc".toCharArray()));
        assertEquals(1, auth.check("AAAAAAA", "abc".toCharArray()));

    }
}
