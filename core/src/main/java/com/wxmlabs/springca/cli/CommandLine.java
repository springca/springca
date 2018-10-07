package com.wxmlabs.springca.cli;

public class CommandLine {
    public static void main(String[] args) {
        if (args.length > 0) {
            String cmd = args[0];
            switch (cmd){
                case "init":
                    new InitCommand().execute();
            }
        }
    }
}
