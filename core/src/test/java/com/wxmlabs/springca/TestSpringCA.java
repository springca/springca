package com.wxmlabs.springca;

import com.wxmlabs.springca.cli.CommandLine;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class TestSpringCA {
    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        CommandLine.main(new String[]{"init"});
    }
}
