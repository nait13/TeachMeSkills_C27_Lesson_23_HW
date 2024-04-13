package org.example.model;

public class Line {
    private String line;

    public Line(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "Line{" +
                "line='" + line + '\'' +
                '}';
    }
}
