import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Calculator extends JFrame {
    JFrame jFrame = new JFrame("Calculator");

    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    String[] buttonValues = {
            "AC", "()", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ",", "←", "="
    };

    String[] rightSymbols = {"÷", "×", "-", "+"};
    String[] topSymbols = {"AC", "()", "%"};
    String oneSymbol = "=";

    String A = "0";
    String operator = null;
    String B = null;

    final Color C_TOP_RIGHT = new Color(215, 227, 248);   // ανοιχτό μπλε
    final Color C_DIGIT     = new Color(222, 225, 230);   // ανοιχτό γκρι
    final Color C_EQUAL     = new Color(106, 88, 132);    // μωβ
    final Color FG_DARK     = Color.BLACK;
    final Color FG_LIGHT    = Color.WHITE;
    final Color C_GRID      = new Color(176, 182, 194);

    Calculator() {
        jFrame.setSize(360, 540);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());

        displayLabel.setBackground(Color.WHITE);
        displayLabel.setForeground(Color.BLACK);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.setPreferredSize(new Dimension(0, 540 / 2)); // μισό ύψος
        displayLabel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        displayPanel.add(displayLabel);
        jFrame.add(displayPanel, BorderLayout.NORTH);

        buttonsPanel.setLayout(new GridLayout(5, 4, 1, 1));
        buttonsPanel.setBackground(Color.GRAY);
        jFrame.add(buttonsPanel, BorderLayout.CENTER);

        for (int i = 0; i < buttonValues.length; i++) {
            JButton jButton = new JButton();
            String buttonValue = buttonValues[i];
            jButton.setFont(new Font("Arial", Font.PLAIN, 30));
            jButton.setText(buttonValue);
            jButton.setOpaque(true);
            jButton.setBorderPainted(true);
            jButton.setBorder(new javax.swing.border.MatteBorder(1, 1, 1, 1, C_GRID));


            if (Arrays.asList(topSymbols).contains(buttonValue) || Arrays.asList(rightSymbols).contains(buttonValue)) {
                jButton.setBackground(C_TOP_RIGHT);
                jButton.setForeground(FG_DARK);
            } else if (oneSymbol.equals(buttonValue)) {
                jButton.setBackground(C_EQUAL);
                jButton.setForeground(FG_LIGHT);
            } else {
                jButton.setBackground(C_DIGIT);
                jButton.setForeground(FG_DARK);
            }


            buttonsPanel.add(jButton);

            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    String t = displayLabel.getText();


                    if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                        String op = buttonValue;


                        if (t.isEmpty()) {
                            if ("-".equals(op)) displayLabel.setText("-");
                            return;
                        }


                        if (endsWithOperator(t)) {
                            displayLabel.setText(replaceLastOperator(t, op));
                        } else {
                            displayLabel.setText(t + " " + op + " ");
                        }

                        operator = op;
                        return;
                    }


                    else if (Arrays.asList(topSymbols).contains(buttonValue)) {

                        if ("AC".equals(buttonValue)) {
                            clearAll();
                            displayLabel.setText(""); // κενό
                            return;

                        } else if ("()".equals(buttonValue)) {
                            // απλή εναλλαγή για εμφάνιση (αγνοείται στο =)
                            int opens = countChar(t, '(');
                            int closes = countChar(t, ')');
                            if (opens <= closes) displayLabel.setText(t + "(");
                            else                 displayLabel.setText(t + ")");
                            return;

                        } else if ("%".equals(buttonValue)) {
                            if (t.isEmpty()) return;
                            int[] r = currentNumberRange(t);
                            String cur = t.substring(r[0], r[1]);
                            if (cur.isEmpty() || "-".equals(cur)) return;

                            double n = parseNumber(cur);
                            String repl = removeZeroDemical(n / 100.0).replace('.', ',');
                            String out = t.substring(0, r[0]) + repl + t.substring(r[1]);
                            displayLabel.setText(out);
                            return;
                        }
                    }


                    else if (oneSymbol.equals(buttonValue)) {
                        String expr = t.trim();
                        if (expr.isEmpty()) return;

                        // αν καταλήγει σε τελεστή, βγάλε τον
                        if (endsWithOperator(expr)) {
                            expr = expr.substring(0, expr.length() - 1).trim();
                        }


                        expr = expr.replace("(", "").replace(")", "");

                        double res = evalLeftToRight(expr);
                        String out = removeZeroDemical(res).replace('.', ',');
                        displayLabel.setText(out);


                        A = out;
                        operator = null;
                        B = null;
                        return;
                    }


                    else {

                        if (",".equals(buttonValue)) {
                            String current = currentNumber(t);
                            if (t.isEmpty()) {
                                displayLabel.setText("0,");
                            } else if (!current.contains(",")) {
                                displayLabel.setText(t + ",");
                            }
                            return;
                        }
                        // backspace
                        else if ("←".equals(buttonValue)) {
                            if (!t.isEmpty()) {
                                // αν τελειώνει με space (μετά από τελεστή), σβήσε space
                                if (Character.isWhitespace(t.charAt(t.length() - 1))) {
                                    displayLabel.setText(t.substring(0, t.length() - 1));
                                } else {
                                    displayLabel.setText(t.substring(0, t.length() - 1));
                                }
                            }
                            return;
                        }

                        else {
                            displayLabel.setText(t + buttonValue);

                        }
                    }
                }
            });
        }


        jFrame.setVisible(true);
    }

    // ===== Helpers  =====

    void clearAll() {
        A = "";
        operator = null;
        B = null;
    }

    String removeZeroDemical(double numDisplay) {
        if (numDisplay % 1 == 0) {
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }

    private boolean endsWithOperator(String s) {
        s = s.trim();
        return s.endsWith("+") || s.endsWith("-") || s.endsWith("×") || s.endsWith("÷");
    }

    private String replaceLastOperator(String s, String newOp) {
        String trimmed = s.trim();
        int lastSpace = trimmed.lastIndexOf(' ');
        if (lastSpace >= 0) {
            String head = trimmed.substring(0, lastSpace).trim();
            return (head.isEmpty() ? "" : head + " ") + newOp + " ";
        } else {
            return newOp + " ";
        }
    }

    private int countChar(String s, char c) {
        int k = 0;
        for (int i = 0; i < s.length(); i++) if (s.charAt(i) == c) k++;
        return k;
    }

    private String currentNumber(String s) {
        int[] r = currentNumberRange(s);
        return s.substring(r[0], r[1]);
    }


    private int[] currentNumberRange(String s) {
        if (s.isEmpty()) return new int[]{0, 0};
        int end = s.length();


        while (end > 0 && Character.isWhitespace(s.charAt(end - 1))) end--;

        int start = end;
        while (start > 0) {
            char ch = s.charAt(start - 1);
            if (ch == ' ') break;
            if (ch == '+' || ch == '×' || ch == '÷') break;
            // το '-' μπορεί να είναι πρόσημο: αν πριν έχει space ή είναι αρχή, σταμάτα
            if (ch == '-') {
                if (start - 1 == 0) { start--; break; }
                if (start - 2 >= 0 && s.charAt(start - 2) == ' ') { start--; break; }
                break;
            }
            start--;
        }
        return new int[]{start, end};
    }

    private double parseNumber(String numText) {
        String normalized = numText.replace(',', '.');
        if (normalized.equals("-")) return 0.0;
        return Double.parseDouble(normalized);
    }

    // αξιολόγηση αριστερά→δεξιά (χωρίς προτεραιότητες/παρενθέσεις)
    private double evalLeftToRight(String expr) {
        String cleaned = expr.replace(',', '.').trim().replaceAll("\\s+", " ");
        if (cleaned.isEmpty()) return 0.0;

        String[] tokens = cleaned.split(" ");
        double acc = Double.parseDouble(tokens[0]);

        for (int i = 1; i < tokens.length - 1; i += 2) {
            String op = tokens[i];
            double val = Double.parseDouble(tokens[i + 1]);
            switch (op) {
                case "+" -> acc += val;
                case "-" -> acc -= val;
                case "×" -> acc *= val;
                case "÷" -> acc /= val;
            }
        }
        return acc;
    }
}

