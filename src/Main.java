import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main extends JFrame implements ActionListener {
    private boolean playerXTurn = true;

    private JButton button00, button01, button02;
    private JButton button10, button11, button12;
    private JButton button20, button21, button22;

    public Main() {

        JButton exit = new JButton("Обратно в меню");
        getContentPane().add(exit);
        exit.setBounds(150,400,150,20);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


        setSize(new Dimension(500, 500));
        setLayout(null);
        setLocationRelativeTo(null);

        button00 = createButton();
        button01 = createButton();
        button02 = createButton();
        button10 = createButton();
        button11 = createButton();
        button12 = createButton();
        button20 = createButton();
        button21 = createButton();
        button22 = createButton();

        add(button00);
        add(button01);
        add(button02);
        add(button10);
        add(button11);
        add(button12);
        add(button20);
        add(button21);
        add(button22);

        button00.setBounds(80,80,100,100);
        button01.setBounds(180,80,100,100);
        button02.setBounds(280,80,100,100);
        button10.setBounds(80,180,100,100);
        button11.setBounds(180,180,100,100);
        button12.setBounds(280,180,100,100);
        button20.setBounds(80,280,100,100);
        button21.setBounds(180,280,100,100);
        button22.setBounds(280,280,100,100);

        button00.setFocusPainted(false);
        button01.setFocusPainted(false);
        button02.setFocusPainted(false);
        button10.setFocusPainted(false);
        button11.setFocusPainted(false);
        button12.setFocusPainted(false);
        button20.setFocusPainted(false);
        button21.setFocusPainted(false);
        button22.setFocusPainted(false);

        setVisible(true);

        JLabel x_o_label = new JLabel("");
        x_o_label.setBounds(80,-50,400,200);
        x_o_label.setText("Крестики, нолики");
        getContentPane().add(x_o_label);
        x_o_label.setFont(new Font("Ariel",Font.PLAIN,30));

    }

    private JButton createButton() {
        JButton button = new JButton("");
        button.setFont(new Font("Arial", Font.PLAIN, 60));
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (!clickedButton.getText().equals("") || !playerXTurn) {
            return;
        }

        clickedButton.setText("X");

        if (checkForWin()) {
            JOptionPane.showMessageDialog(this, "Игрок X выиграл!");
            resetGame();
            return;
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "Ничья!");
            resetGame();
            return;
        }

        playerXTurn = false;
        computerMove();
    }

    private void computerMove() {
        Random rand = new Random();

        while (true) {
            int i = rand.nextInt(3);
            int j = rand.nextInt(3);
            JButton button = getButton(i, j);
            if (button.getText().equals("")) {
                button.setText("O");
                break;
            }
        }

        if (checkForWin()) {
            JOptionPane.showMessageDialog(this, "Компьютер O выиграл!");
            resetGame();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "Ничья!");
            resetGame();
        }

        playerXTurn = true;
    }

    private JButton getButton(int row, int col) {
        switch (row) {
            case 0:
                switch (col) {
                    case 0: return button00;
                    case 1: return button01;
                    case 2: return button02;
                }
                break;
            case 1:
                switch (col) {
                    case 0: return button10;
                    case 1: return button11;
                    case 2: return button12;
                }
                break;
            case 2:
                switch (col) {
                    case 0: return button20;
                    case 1: return button21;
                    case 2: return button22;
                }
                break;
        }
        return null; // Это не должно произойти
    }

    private boolean checkForWin() {
        // Проверка строк и столбцов
        for (int i = 0; i < 3; i++) {
            if (checkLine(getButton(i, 0), getButton(i, 1), getButton(i, 2)) ||
                    checkLine(getButton(0, i), getButton(1, i), getButton(2, i))) {
                return true;
            }
        }

        // Проверка диагоналей
        if (checkLine(button00, button11, button22) || checkLine(button02, button11, button20)) {
            return true;
        }

        return false;
    }  private boolean checkLine(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals("") && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (getButton(i, j).getText().equals("")) {
                    return false;
                }
            }
        }

        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                getButton(i, j).setText("");
            }
        }

        playerXTurn = true;


    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Браузер игр");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500,500));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);



        JMenuBar menuBar = new JMenuBar();
        JMenu vidmenu = new JMenu("Цвет страницы");
        JMenuItem black_vid = new JMenuItem("Черная страница");
        JMenuItem white_vid = new JMenuItem("белая страница");
        JMenuItem pink_vid = new JMenuItem("розовая страница");
        JMenuItem yellow_vid = new JMenuItem("желтая страница");
        JMenuItem standart_vid = new JMenuItem("стандартная страница");

        black_vid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().setBackground(Color.BLACK);
            }
        });

        white_vid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().setBackground(Color.white);
            }
        });

        pink_vid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().setBackground(Color.pink);
            }
        });

        yellow_vid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().setBackground(Color.yellow);
            }
        });

        standart_vid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().setBackground(Color.white);
            }
        });

        vidmenu.add(black_vid);
        vidmenu.add(white_vid);
        vidmenu.add(pink_vid);
        vidmenu.add(yellow_vid);
        vidmenu.add(standart_vid);
        menuBar.add(vidmenu);


        JMenu options = new JMenu("Настройки");
        JMenuItem exit_options = new JMenuItem("Выход из приложения");
        JMenuItem vesekran_options = new JMenuItem("На весь экран");
        JMenuItem help_options = new JMenuItem("Написать в поддержку");

        exit_options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        vesekran_options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                JLabel ves_ekran = new JLabel("Функция \"на весь экран\", плохо работает");
                frame.getContentPane().add(ves_ekran);
                ves_ekran.setBounds(700,700,400,200);
                frame.setUndecorated(true);


            }
        });

        help_options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame = new JFrame("Поддержка");
                jFrame.setVisible(true);
                jFrame.setSize(new Dimension(400,400));
                jFrame.setLocationRelativeTo(null);
                JLabel label = new JLabel("");
                label.setText("Поддержки нет");
                label.setFont(new Font("Ariel", Font.BOLD, 50));
                jFrame.getContentPane().add(label);
            }
        });

        options.add(exit_options);
        options.add(vesekran_options);
        options.add(help_options);
        menuBar.add(options);

        frame.setJMenuBar(menuBar);

        JButton register_button = new JButton("Регистрация");
        register_button.setFocusPainted(false);
        register_button.setBorder(null);
        register_button.setOpaque(true);
        frame.getContentPane().add(register_button);
        register_button.setBounds(330,0,150,20);

        register_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame reg = new JFrame("Регистрация");
                reg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                reg.setSize(new Dimension(500,500));
                reg.setLocationRelativeTo(null);
                reg.setLayout(null);
                reg.setVisible(true);
                frame.setVisible(false);

                JTextArea login = new JTextArea("");
                JTextArea password = new JTextArea("");
                reg.getContentPane().add(login);
                reg.getContentPane().add(password);
                login.setBounds(130,150,200,15);
                password.setBounds(130,170,200,15);

                JLabel label = new JLabel("Регистрация");
                reg.getContentPane().add(label);
                label.setFont(new Font("Ariel", Font.BOLD, 29));
                label.setBounds(130,120,200,25);

                JLabel label1 = new JLabel("Логин");
                reg.getContentPane().add(label1);
                label1.setBounds(90,145,200,25);

                JLabel label2 = new JLabel("Пароль");
                reg.getContentPane().add(label2);
                label2.setBounds(80,165,200,25);

                JButton confirm_button = new JButton("Подтвердить");
                confirm_button.setFocusPainted(false);
                reg.getContentPane().add(confirm_button);
                confirm_button.setBounds(130,190,200,20);

                confirm_button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try (FileWriter writer = new FileWriter("notes3.txt", false)) {
                            String Login = login.getText();
                            String Password = password.getText();
                            writer.write("Логин " + Login);
                            writer.append("\n");
                            writer.append("Пароль " + Password);
                            writer.flush();
                        } catch (IOException ex) {
                            System.out.println("anlack");
                        }
                    }
                });

                JButton exit = new JButton("Обратно в меню");
                exit.setFocusPainted(false);
                reg.getContentPane().add(exit);
                exit.setBounds(150,230,150,20);
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        reg.setVisible(false);
                        frame.setVisible(true);
                    }
                });
            }
        });

        JButton vxod_button = new JButton("Вход");
        frame.getContentPane().add(vxod_button);
        vxod_button.setFocusPainted(false);
        vxod_button.setBorder(null);
        vxod_button.setOpaque(true);
        vxod_button.setBounds(330,20,150,20);

        vxod_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame reg = new JFrame("Вход");
                reg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                reg.setSize(new Dimension(500,500));
                reg.setLocationRelativeTo(null);
                reg.setLayout(null);
                reg.setVisible(true);
                frame.setVisible(false);

                JTextArea login1 = new JTextArea("");
                JTextArea password1 = new JTextArea("");
                reg.getContentPane().add(login1);
                reg.getContentPane().add(password1);
                login1.setBounds(130,150,200,15);
                password1.setBounds(130,170,200,15);

                JLabel label = new JLabel("Вход");
                reg.getContentPane().add(label);
                label.setFont(new Font("Ariel", Font.BOLD, 29));
                label.setBounds(130,120,200,25);

                JLabel label1 = new JLabel("Логин");
                reg.getContentPane().add(label1);
                label1.setBounds(90,145,200,25);

                JLabel label2 = new JLabel("Пароль");
                reg.getContentPane().add(label2);
                label2.setBounds(80,165,200,25);

                JButton confirm_button = new JButton("Подтвердить");
                reg.getContentPane().add(confirm_button);
                confirm_button.setBounds(130,190,200,20);
                confirm_button.setFocusPainted(false);

                confirm_button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                    }
                });

                JButton exit = new JButton("Обратно в меню");
                reg.getContentPane().add(exit);
                exit.setBounds(150,230,150,20);
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        reg.setVisible(false);
                        frame.setVisible(true);
                    }
                });
            }
        });

// секция игр

        JLabel play_cotegory = new JLabel("Выберите игру");
        frame.getContentPane().add(play_cotegory);
        play_cotegory.setBounds(30,80,200,20);
        play_cotegory.setFont(new Font("Italic", Font.BOLD, 15));

// камень ножницы бумага

        JButton paper_rock_cut = new JButton("Камень ножницы бумага");
        frame.getContentPane().add(paper_rock_cut);
        paper_rock_cut.setBounds(30,100,200,20);

        paper_rock_cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                String[] play = {"Ножницы", "Бумагу", "Камень"};
                JFrame paper_cut_rock1 = new JFrame("sd");
                JLabel label = new JLabel("");
                JLabel label_text = new JLabel();
                paper_cut_rock1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                paper_cut_rock1.setLayout(null);
                paper_cut_rock1.setSize(new Dimension(500, 500));

                JButton rock = new JButton("Камень");
                rock.setBounds(125, 142, 200, 20);
                paper_cut_rock1.getContentPane().add(rock);
                paper_cut_rock1.setLocationRelativeTo(null);
                rock.setBackground(Color.lightGray);

                JButton paper = new JButton("Бумага");
                paper.setBounds(125, 165, 200, 20);
                paper_cut_rock1.getContentPane().add(paper);
                paper.setBackground(Color.lightGray);

                JButton cut = new JButton("Ножницы");
                cut.setBounds(125, 188, 200, 20);
                paper_cut_rock1.getContentPane().add(cut);
                cut.setBackground(Color.lightGray);

                cut.setFocusPainted(false);
                rock.setFocusPainted(false);
                paper.setFocusPainted(false);


                paper_cut_rock1.getContentPane().add(label);
                paper_cut_rock1.getContentPane().add(label_text);
                label_text.setText("Бот загадал Камень/Ножницы/Бумага,\n отгадайте, что он загадал");
                label_text.setBounds(55, 30, 400, 200);
                label.setOpaque(false);

                paper.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int index_play = random.nextInt(play.length);
                        String final_index = play[index_play];
                        System.out.println(final_index);
                        String c = play[1];
                        if (c == final_index) {
                            label.setBounds(125, 120, 250, 200);
                            label.setText("Вы угадали, " + "Бот загадал: " + final_index);
                        } else {
                            label.setBounds(125, 120, 250, 200);
                            label.setText("Вы не угадали, " + "Бот загадал: " + final_index);
                        }
                    }
                });
                rock.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int index_play = random.nextInt(play.length);
                        String final_index = play[index_play];
                        System.out.println(final_index);
                        String c = play[2];
                        if (c == final_index) {
                            label.setBounds(125, 120, 250, 200);
                            label.setText("Вы угадали, " + "Бот загадал: " + final_index);
                        } else {
                            label.setBounds(125, 120, 250, 200);
                            label.setText("Вы не угадали, " + "Бот загадал: " + final_index);
                        }
                    }
                });
                cut.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int index_play = random.nextInt(play.length);
                        String final_index = play[index_play];
                        System.out.println(final_index);
                        String c = play[0];
                        if (c == final_index) {
                            label.setBounds(125, 120, 250, 200);
                            label.setText("Вы угадали, " + "Бот загадал: " + final_index);
                        } else {
                            label.setBounds(125, 120, 250, 200);
                            label.setText("Вы не угадали, " + "Бот загадал: " + final_index);
                        }
                    }
                });
                JButton exit = new JButton("Обратно в меню");
                paper_cut_rock1.getContentPane().add(exit);
                exit.setFocusPainted(false);
                exit.setBounds(150,230,150,20);
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        paper_cut_rock1.setVisible(false);
                        frame.setVisible(true);
                    }
                });
                paper_cut_rock1.setVisible(true);
            }
        });

        // Угадайка

        JButton randoms = new JButton("Угадайка");
        randoms.setFocusPainted(false);
        frame.getContentPane().add(randoms);
        randoms.setBounds(30, 120, 200,20);

        randoms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ugadaika_frame = new JFrame("Угадайка");
                JLabel label = new JLabel("");
                JTextArea textArea = new JTextArea();
                JLabel label_text = new JLabel();
                ugadaika_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ugadaika_frame.setLayout(null);
                ugadaika_frame.setSize(new Dimension(500,500));
                ugadaika_frame.setVisible(true);
                JButton button = new JButton("Подтверждение");
                button.setFocusPainted(false);
                button.setBounds(125,120,200,20);
                ugadaika_frame.getContentPane().add(button);
                ugadaika_frame.setLocationRelativeTo(null);
                textArea.setBounds(125,100,200,20);
                ugadaika_frame.getContentPane().add(textArea);
                ugadaika_frame.getContentPane().add(label);
                ugadaika_frame.getContentPane().add(label_text);
                label_text.setText("Введите число");
                label_text.setBounds(125, -10,200,200);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Random random = new Random();
                        int ss = random.nextInt(10);
                        System.out.println(ss);

                        int a = Integer.parseInt(textArea.getText());
                        System.out.println(a);
                        if (ss == a){
                            label.setText("Вы победили");
                            label.setBounds(125,50,200,200);
                        }
                        else {
                            label.setText("Число было: " + ss + "\n" + ", а вы ввели " + a);
                            label.setBounds(125,50,200,200);
                        }
                    }
                });
                JButton exit = new JButton("Обратно в меню");
                ugadaika_frame.getContentPane().add(exit);
                exit.setFocusPainted(false);
                exit.setBounds(150,250,150,20);
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ugadaika_frame.setVisible(false);
                        frame.setVisible(true);
                    }
                });
            }
        });

        JButton x_o_button = new JButton("Крестики, нолики");
        frame.getContentPane().add(x_o_button);
        x_o_button.setBounds(30,140,200,20);
        x_o_button.setFocusPainted(false);


x_o_button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        new Main();
    }
});


        frame.setVisible(true);
    }
}