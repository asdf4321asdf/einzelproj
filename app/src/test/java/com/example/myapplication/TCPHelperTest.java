package com.example.myapplication;

import org.junit.Test;

import java.io.IOException;
import java.net.ConnectException;

import static junit.framework.TestCase.fail;

public class TCPHelperTest {
    @Test
    public void checkResponse() throws IOException {
        TCPHelper tcpHelper = new TCPHelper("se2-isys.aau.at", 53212);
        String result = tcpHelper.sendAndRetrieve("00000000");
        if (!result.contains("begonnen zu studieren"))
            fail("wrong response");
    }

    @Test(expected = ConnectException.class)
    public void checkHostNull() throws IOException {
        TCPHelper tcpHelper = new TCPHelper(null, 53212);
        tcpHelper.sendAndRetrieve("00000000");
    }
}
