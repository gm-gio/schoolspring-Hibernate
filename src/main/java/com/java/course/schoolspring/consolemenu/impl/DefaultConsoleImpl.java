package com.java.course.schoolspring.consolemenu.impl;

import com.java.course.schoolspring.consolemenu.Console;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Component
public class DefaultConsoleImpl implements Console {
    private final Scanner scanner;
    private final PrintStream out;

    public DefaultConsoleImpl() {
        this(System.in, System.out);
    }

    public DefaultConsoleImpl(InputStream in, PrintStream out) {

        this.scanner = new Scanner(in);
        this.out = out;
    }

    @Override
    public void println(String message) {
        out.println(message);
    }

    @Override
    public int nextInt() {
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
}
