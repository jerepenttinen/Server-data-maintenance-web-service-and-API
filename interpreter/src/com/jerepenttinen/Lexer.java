package com.jerepenttinen;

public class Lexer {
    private final String input;
    private int position;
    private int readPosition;
    private char aChar;

    public Lexer(String input) {
        this.input = input;
        this.readChar();
    }

    public Token nextToken() {
        Token tok;
        skipWhitespace();
        var cs = String.valueOf(aChar);

        switch (aChar) {
            case '+' -> tok = new Token(TokenType.PLUS, cs);
            case '-' -> tok = new Token(TokenType.MINUS, cs);
            case '*' -> tok = new Token(TokenType.ASTERISK, cs);
            case '/' -> tok = new Token(TokenType.SLASH, cs);
            case '(' -> tok = new Token(TokenType.LPAREN, cs);
            case ')' -> tok = new Token(TokenType.RPAREN, cs);
            case '"' -> tok = new Token(TokenType.STRING, readString());
            case 0, '\n' -> tok = new Token(TokenType.EOL, "");
            default -> {
                if (isLetter(aChar)) {
                    var literal = readIdentifier();
                    tok = new Token(TokenType.lookupIdent(literal), literal);
                } else if (isDigit(aChar)) {
                    tok = new Token(TokenType.INT, readNumber());
                } else {
                    tok = new Token(TokenType.ILLEGAL, cs);
                }
            }
        }
        readChar();

        return tok;
    }

    private void readChar() {
        if (readPosition >= input.length()) {
            aChar = 0;
        } else {
            aChar = input.charAt(readPosition);
        }
        position = readPosition;
        readPosition++;
    }

    private void skipWhitespace() {
        while (aChar == ' ' || aChar == '\t' || aChar == '\n' || aChar == 'r') {
            readChar();
        }
    }

    private String readIdentifier() {
        int pos = position;
        while (isLetter(aChar)) {
            readChar();
        }
        return input.substring(pos, position);
    }

    private String readNumber() {
        int pos = position;
        while (isDigit(aChar)) {
            readChar();
        }
        return input.substring(pos, position);
    }

    private String readString() {
        int pos = position + 1;
        do {
            readChar();
        } while (aChar != '"' && aChar != 0);
        return input.substring(pos, position);
    }

    private boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private boolean isLetter(char c) {
        // [a-zA-Z]
        return 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z';
    }
}