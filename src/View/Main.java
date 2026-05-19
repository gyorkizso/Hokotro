package View;

import Model.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static Prototype prototype;

    static PrintStream outStream;

    static List<String> state;

    public static void reset() {
        new Skeleton();
        prototype = new Prototype();
        state = new ArrayList<>();
    }

    public static void main(String[] args) {
        reset();

        JFrame window = new JFrame();

        window.add(new Map());

        window.setSize(1000, 1000);
        window.setVisible(true);
    }
}