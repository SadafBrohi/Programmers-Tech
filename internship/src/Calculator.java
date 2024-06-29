import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    JFrame jFrame;

    public Calculator() {
        jFrame = new JFrame();

        jFrame.setSize(400, 400);
        jFrame.setTitle("Calculator");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);

        JLabel jl = new JLabel("Which calculator you want to use:");
        jl.setBounds(100, 100, 200, 30);

        JButton jb = new JButton("Simple Calculator");
        jb.setBounds(100, 150, 200, 30);

        JButton jbt = new JButton("Scientific Calculator");
        jbt.setBounds(100, 190, 200, 30);

        jFrame.add(jl);
        jFrame.add(jb);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SimpleCalculator(jFrame);
            }
        });
        jFrame.add(jbt);
        jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Scientific Calculator
                 new SimpleCalculator.ScientificCalculator(jFrame);
            }
        });

        jFrame.setVisible(true);
    }
}

class SimpleCalculator {
    private final JTextField textDisplay;
    private double total1 = 0.0;
    private double total2 = 0.0;
    private char math_operator;

    private void getOperator(String btnText) {
        math_operator = btnText.charAt(0);
        total1 = total1 + Double.parseDouble(textDisplay.getText());
        textDisplay.setText("");
    }

    SimpleCalculator(JFrame parentFrame) {
        parentFrame.setVisible(false);
        JFrame frame = new JFrame("Simple Calculator");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JButton btnBack = new JButton("<-");
        btnBack.setBounds(5, 5, 50, 20);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(btnBack);

        textDisplay = new JTextField(20);
        textDisplay.setBounds(50, 50, 300, 30);
        frame.add(textDisplay);

        JButton btnOne = new JButton("1");
        JButton btnTwo = new JButton("2");
        JButton btnThree = new JButton("3");
        JButton btnFour = new JButton("4");
        JButton btnFive = new JButton("5");
        JButton btnSix = new JButton("6");
        JButton btnSeven = new JButton("7");
        JButton btnEight = new JButton("8");
        JButton btnNine = new JButton("9");
        JButton btnZero = new JButton("0");
        JButton btnPoint = new JButton(".");
        JButton btnEquals = new JButton("=");
        JButton btnPlus = new JButton("+");
        JButton btnMinus = new JButton("-");
        JButton btnMultiply = new JButton("*");
        JButton btnDivide = new JButton("/");
        JButton btnClear = new JButton("C");


        btnOne.setBounds(50, 100, 50, 50);
        btnTwo.setBounds(110, 100, 50, 50);
        btnThree.setBounds(170, 100, 50, 50);
        btnPlus.setBounds(230, 100, 50, 50);

        btnFour.setBounds(50, 160, 50, 50);
        btnFive.setBounds(110, 160, 50, 50);
        btnSix.setBounds(170, 160, 50, 50);
        btnMinus.setBounds(230, 160, 50, 50);

        btnSeven.setBounds(50, 220, 50, 50);
        btnEight.setBounds(110, 220, 50, 50);
        btnNine.setBounds(170, 220, 50, 50);
        btnMultiply.setBounds(230, 220, 50, 50);

        btnZero.setBounds(50, 280, 110, 50);
        btnPoint.setBounds(170, 280, 50, 50);
        btnEquals.setBounds(230, 280, 50, 50);
        btnDivide.setBounds(290, 280, 50, 50);
        btnClear.setBounds(290, 100, 50, 170);

        frame.add(btnOne);
        frame.add(btnTwo);
        frame.add(btnThree);
        frame.add(btnFour);
        frame.add(btnFive);
        frame.add(btnSix);
        frame.add(btnSeven);
        frame.add(btnEight);
        frame.add(btnNine);
        frame.add(btnZero);
        frame.add(btnPoint);
        frame.add(btnEquals);
        frame.add(btnPlus);
        frame.add(btnMinus);
        frame.add(btnMultiply);
        frame.add(btnDivide);
        frame.add(btnClear);


        btnOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textDisplay.setText(textDisplay.getText() + btnOne.getText());
            }
        });

        btnTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textDisplay.setText(textDisplay.getText() + btnTwo.getText());
            }
        });

        btnThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textDisplay.setText(textDisplay.getText() + btnThree.getText());
            }
        });

        btnFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textDisplay.setText(textDisplay.getText() + btnFour.getText());
            }
        });

        btnFive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textDisplay.setText(textDisplay.getText() + btnFive.getText());
            }
        });

        btnSix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textDisplay.setText(textDisplay.getText() + btnSix.getText());
            }
        });

        btnSeven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textDisplay.setText(textDisplay.getText() + btnSeven.getText());
            }
        });

        btnEight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textDisplay.setText(textDisplay.getText() + btnEight.getText());
            }
        });

        btnNine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textDisplay.setText(textDisplay.getText() + btnNine.getText());
            }
        });

        btnZero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textDisplay.setText(textDisplay.getText() + btnZero.getText());
            }
        });

        btnPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textDisplay.getText().contains(".")) {
                    textDisplay.setText(textDisplay.getText() + btnPoint.getText());
                }
            }
        });

        btnEquals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (math_operator) {
                    case '+':
                        total2 = total1 + Double.parseDouble(textDisplay.getText());
                        break;
                    case '-':
                        total2 = total1 - Double.parseDouble(textDisplay.getText());
                        break;
                    case '*':
                        total2 = total1 * Double.parseDouble(textDisplay.getText());
                        break;
                    case '/':
                        total2 = total1 / Double.parseDouble(textDisplay.getText());
                        break;
                }
                textDisplay.setText(Double.toString(total2));
                total1 = 0;
            }
        });

        btnPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getOperator(btnPlus.getText());
            }
        });

        btnMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getOperator(btnMinus.getText());
            }
        });

        btnMultiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getOperator(btnMultiply.getText());
            }
        });

        btnDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getOperator(btnDivide.getText());
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total1 = 0;
                total2 = 0;
                textDisplay.setText("");
            }
        });

        frame.setVisible(true);
    }

    static class ScientificCalculator {

        private JTextField textDisplay;
        private double total1 = 0.0;
        private double total2 = 0.0;
        private char math_operator;

        private void getOperator(String btnText) {
            math_operator = btnText.charAt(0);
            total1 = total1 + Double.parseDouble(textDisplay.getText());
            textDisplay.setText("");
        }

       ScientificCalculator(JFrame parentFrame) {
            parentFrame.setVisible(false);

            JFrame frame = new JFrame("Scientific Calculator");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(null);

            JButton btnBack = new JButton("<-");
            btnBack.setBounds(5, 5, 50, 20);
            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
            frame.add(btnBack);

            textDisplay = new JTextField(20);
            textDisplay.setBounds(50, 50, 300, 30);
            frame.add(textDisplay);

            JButton btnOne = new JButton("1");
            JButton btnTwo = new JButton("2");
            JButton btnThree = new JButton("3");
            JButton btnFour = new JButton("4");
            JButton btnFive = new JButton("5");
            JButton btnSix = new JButton("6");
            JButton btnSeven = new JButton("7");
            JButton btnEight = new JButton("8");
            JButton btnNine = new JButton("9");
            JButton btnZero = new JButton("0");
            JButton btnPoint = new JButton(".");
            JButton btnEquals = new JButton("=");
            JButton btnPlus = new JButton("+");
            JButton btnMinus = new JButton("-");
            JButton btnMultiply = new JButton("*");
            JButton btnDivide = new JButton("/");
            JButton btnClear = new JButton("C");
            JButton factorial = new JButton("n!");
            JButton square = new JButton("x^2");
            JButton delete=new JButton("del");

            btnOne.setBounds(50, 100, 50, 50);
            btnTwo.setBounds(110, 100, 50, 50);
            btnThree.setBounds(170, 100, 50, 50);
            btnClear.setBounds(290, 100, 50, 50);
            delete.setBounds(230, 100, 55, 50);

            btnFour.setBounds(50, 160, 50, 50);
            btnFive.setBounds(110, 160, 50, 50);
            btnSix.setBounds(170, 160, 50, 50);
            btnMinus.setBounds(230, 160, 50, 50);
            square.setBounds(290, 160, 55, 50);

            btnSeven.setBounds(50, 220, 50, 50);
            btnEight.setBounds(110, 220, 50, 50);
            btnNine.setBounds(170, 220, 50, 50);
            btnMultiply.setBounds(230, 220, 50, 50);
            factorial.setBounds(290, 220, 50, 50);

            btnZero.setBounds(50, 280, 110, 50);
            btnPoint.setBounds(170, 280, 50, 50);
            btnEquals.setBounds(230, 280, 50, 50);
            btnDivide.setBounds(290, 280, 50, 50);

            frame.add(btnOne);
            frame.add(btnTwo);
            frame.add(btnThree);
            frame.add(btnFour);
            frame.add(btnFive);
            frame.add(btnSix);
            frame.add(btnSeven);
            frame.add(btnEight);
            frame.add(btnNine);
            frame.add(btnZero);
            frame.add(btnPoint);
            frame.add(btnEquals);
            frame.add(btnPlus);
            frame.add(btnMinus);
            frame.add(btnMultiply);
            frame.add(btnDivide);
            frame.add(btnClear);
            frame.add(factorial);
            frame.add(square);
            frame.add(delete);

            btnOne.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textDisplay.setText(textDisplay.getText() + btnOne.getText());
                }
            });

            btnTwo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textDisplay.setText(textDisplay.getText() + btnTwo.getText());
                }
            });

            btnThree.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textDisplay.setText(textDisplay.getText() + btnThree.getText());
                }
            });

            btnFour.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textDisplay.setText(textDisplay.getText() + btnFour.getText());
                }
            });

            btnFive.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textDisplay.setText(textDisplay.getText() + btnFive.getText());
                }
            });

            btnSix.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textDisplay.setText(textDisplay.getText() + btnSix.getText());
                }
            });

            btnSeven.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textDisplay.setText(textDisplay.getText() + btnSeven.getText());
                }
            });

            btnEight.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textDisplay.setText(textDisplay.getText() + btnEight.getText());
                }
            });

            btnNine.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textDisplay.setText(textDisplay.getText() + btnNine.getText());
                }
            });

            btnZero.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textDisplay.setText(textDisplay.getText() + btnZero.getText());
                }
            });

            btnPoint.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!textDisplay.getText().contains(".")) {
                        textDisplay.setText(textDisplay.getText() + btnPoint.getText());
                    }
                }
            });

            btnEquals.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (math_operator) {
                        case '+':
                            total2 = total1 + Double.parseDouble(textDisplay.getText());
                            break;
                        case '-':
                            total2 = total1 - Double.parseDouble(textDisplay.getText());
                            break;
                        case '*':
                            total2 = total1 * Double.parseDouble(textDisplay.getText());
                            break;
                        case '/':
                            total2 = total1 / Double.parseDouble(textDisplay.getText());
                            break;
                    }
                    textDisplay.setText(Double.toString(total2));
                    total1 = 0;
                }
            });

            btnPlus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getOperator(btnPlus.getText());
                }
            });

            btnMinus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getOperator(btnMinus.getText());
                }
            });

            btnMultiply.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getOperator(btnMultiply.getText());
                }
            });

            btnDivide.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getOperator(btnDivide.getText());
                }
            });

            btnClear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    total1 = 0;
                    total2 = 0;
                    textDisplay.setText("");
                }
            });
            factorial.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        // Get the number entered in the text field
                        int number = Integer.parseInt(textDisplay.getText());

                        // Calculate factorial
                        int result = 1;
                        for (int i = 2; i <= number; i++) {
                            result *= i;
                        }

                        // Display the factorial result
                        textDisplay.setText(String.valueOf(result));
                    } catch (NumberFormatException ex) {
                        // Handle non-integer inputs
                        textDisplay.setText("Invalid input");
                    }
                }
            });
            square.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        // Get the number entered in the text field
                        double number = Double.parseDouble(textDisplay.getText());

                        // Calculate the square
                        double square = number * number;

                        // Display the square result
                        textDisplay.setText(String.valueOf(square));
                    } catch (NumberFormatException ex) {
                        // Handle non-numeric inputs
                        textDisplay.setText("Invalid input");
                    }
                }
            });
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get the current text from the text field
                    String currentText = textDisplay.getText();
                    // Check if there's text to delete
                    if (currentText.length() > 0) {
                        // Remove the last character from the text
                        String newText = currentText.substring(0, currentText.length() - 1);
                        // Update the text field with the new text
                        textDisplay.setText(newText);
                    }
                }
            });
            frame.setVisible(true);
        }
       public static void main(String[] args) {

            Calculator calculator = new Calculator();
        }
    }
}
