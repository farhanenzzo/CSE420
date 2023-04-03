package cse420.lab01;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class task01 {

    List<String> wordList;

    private List<String> keywords = new ArrayList<>(Arrays.asList("int", "if", "else", "float"));
    private List<String> digit = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4","5","6","7","8","9"));
    private List<String> mathOperator = new ArrayList<>(Arrays.asList("+", "-", "/", "*", "%", "="));
    private List<String> logicalOperator = new ArrayList<>(Arrays.asList(">", "<", ">=", "<=", "==", "!="));
    private List<String> others = new ArrayList<>(Arrays.asList(",", ";", "(", ")", "{", "}", "[", "]"));

    private Set<String> keywordSet = new HashSet<>();
    private Set<String> numberSet = new HashSet<>();
    private Set<String> identifierSet = new HashSet<>();
    private Set<String> mathOperatorSet = new HashSet<>();
    private Set<String> logicalOperatorSet = new HashSet<>();
    private Set<String> otherSet = new HashSet<>();

    public static void main(String[] args) throws IOException {

        task01 analyzer = new task01();
        String textPath = "src/cse420/lab01/input.txt";
        analyzer.readFile(textPath);

        for(String data: analyzer.wordList) {
            analyzer.matchKeyword(data);
            analyzer.matchIdentifiers_Others(data);
            analyzer.matchMathOperator(data);
            analyzer.matchLogicalOperator(data);
            analyzer.matchNumeric(data);
            analyzer.matchOthers(data);
        }

        /**
         * -------- Printing keywords --------------
         */
        System.out.print("Keywords: ");
        StringBuilder Keywords = new StringBuilder();
        for (String element : analyzer.keywordSet) {
            Keywords.append(element).append(", ");
        }
        if (Keywords.length() > 0) {
            Keywords.setLength(Keywords.length() - 2);
        }
        System.out.println(Keywords);

        /**
         * -------- Printing Identifiers --------------
         */

        System.out.print("Identifiers: ");
        StringBuilder Identifiers = new StringBuilder();
        for (String element : analyzer.identifierSet) {
            Identifiers.append(element).append(", ");
        }
        if (Identifiers.length() > 0) {
            Identifiers.setLength(Identifiers.length() - 2);
        }
        System.out.println(Identifiers);

        /**
         * -------- Printing Math Operators --------------
         */

        System.out.print("Math Operators: ");
        StringBuilder Operators = new StringBuilder();
        for (String element : analyzer.mathOperatorSet) {
            Operators.append(element).append(", ");
        }
        if (Operators.length() > 0) {
            Operators.setLength(Operators.length() - 2);
        }
        System.out.println(Operators);

        /**
         * -------- Printing Numerical Values --------------
         */

        System.out.print("Numerical Values: ");
        StringBuilder Numerical = new StringBuilder();
        for (String element : analyzer.numberSet) {
            Numerical.append(element).append(", ");
        }
        if (Numerical.length() > 0) {
            Numerical.setLength(Numerical.length() - 2);
        }
        System.out.println(Numerical);

        System.out.print("Logical Operator: ");
        StringBuilder Logical = new StringBuilder();
        for (String element : analyzer.logicalOperatorSet) {
            Logical.append(element).append(", ");
        }
        if (Logical.length() > 0) {
            Logical.setLength(Logical.length() - 2);
        }
        System.out.println(Logical);

        /**
         * -------- Printing other --------------
         */

        System.out.print("Others: ");
        for (String element : analyzer.otherSet) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    /**
     * -------- custom made func --------------
     */

    void matchKeyword(String data) {
        if (keywords.contains(data)) {
            keywordSet.add(data);
        }
    }

    void matchIdentifiers_Others(String data) {

        if(!keywords.contains(data) && data.matches("[(_a-z|A-Z);,]+")) {
//            System.out.println(data);

//            if(data.startsWith("(")) {
//                otherList.add("(");
//                identifierList.add(data.replace("(", ""));
//            }
            if(data.endsWith(")")) {
                otherSet.add(")");
                identifierSet.add(data.replace(")", ""));
            }
            if(data.endsWith(",") ) {
                otherSet.add(",");
                identifierSet.add(data.replace(",", ""));
            }
            if(data.endsWith(";") ) {
                otherSet.add(";");
                identifierSet.add(data.replace(";", ""));
            }
        }
    }

    void matchMathOperator(String data) {
        if(mathOperator.contains(data)) {
            mathOperatorSet.add(data);
        }
    }

    void matchLogicalOperator(String data) {
        if(logicalOperator.contains(data)) {
            logicalOperatorSet.add(data);
        }
    }

    void matchOthers(String data) {
        if(others.contains(data)) {
            otherSet.add(data);
        }
    }

    void matchNumeric(String data) {
        for(String dig: digit) {
            if(data.startsWith(dig)) {
                if(data.contains("") || data.contains(".") || data.contains(";")) {
                    numberSet.add(data.replace(";", ""));
                }
            }
        }
    }

    List<String> readFile(String path) throws IOException {
        wordList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;

        while ((line = reader.readLine()) != null) {
            for (String word : line.split("\\s+")) {
                wordList.add(word);
            }
        }
        return wordList;
    }
}

//Keywords: int, float, if, else
//Identifiers: a, b, c, d, e
//Math Operators: +, -, =
//Logical Operators: >
//Numerical Values: 5, 6, 2.0, 6.0
//Others: , ; ( ) { }