package game.display;

import game.controller.Controller1;
import game.controller.Controller2;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GameFrame {    
    private JFrame mainFrame;
    private JPanel contentPanel;

    public GameFrame() {
        prepareGUI();
    }
    private void prepareGUI() {
        mainFrame = new JFrame("Bounce Ball");
        mainFrame.setSize(1050, 650);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        JMenuBar menuBar = new JMenuBar();
        JMenu mFile1 = new JMenu("NORMAL");
        JMenu mFile2 = new JMenu("PK");
        JMenuItem man11 = new JMenuItem("Màn 1");
        JMenuItem man12 = new JMenuItem("Màn 2");
        JMenuItem man13 = new JMenuItem("Màn 3");
        JMenuItem man14 = new JMenuItem("Màn 4");
        JMenuItem man15 = new JMenuItem("Màn 5");
        JMenuItem man21 = new JMenuItem("Màn 1");
        JMenuItem man22 = new JMenuItem("Màn 2");
        JMenuItem man23 = new JMenuItem("Màn 3");
        JMenuItem man24 = new JMenuItem("Màn 4");
        JMenuItem man25 = new JMenuItem("Màn 5");
        
        mFile1.add(man11);
        mFile1.add(man12);
        mFile1.add(man13);
        mFile1.add(man14);
        mFile1.add(man15);
        mFile2.add(man21);
        mFile2.add(man22);
        mFile2.add(man23);
        mFile2.add(man24);
        mFile2.add(man25);

        menuBar.add(mFile1);
        menuBar.add(mFile2);
        mainFrame.setJMenuBar(menuBar);
        contentPanel = new JPanel(new BorderLayout());
        mainFrame.setContentPane(contentPanel);
        JLabel guides = new JLabel();
        JLabel guides1 = new JLabel();
        JLabel guides2 = new JLabel();
        guides.setBounds(400, 50, 500, 50);
        guides.setText("Hướng dẫn chơi game:");
        guides1.setBounds(350, 150, 500, 50);
        guides1.setText("Chế độ 1: Nảy thanh-Chế độ 2: Nảy tường");
        guides2.setBounds(400, 200, 500, 50);
        guides2.setText("");
        contentPanel.add(guides);
        contentPanel.add(guides1);
        contentPanel.add(guides2);
        Controller1 controller11 = new Controller1(1,mainFrame,contentPanel);
        
        man11.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                mainFrame.getContentPane().removeAll();
                contentPanel.add(new Controller1(1,mainFrame,contentPanel));
                mainFrame.setVisible(true);

            }
        });
        man12.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                mainFrame.getContentPane().removeAll();
                contentPanel.add(new Controller1(2,mainFrame,contentPanel));
                mainFrame.setVisible(true);
            }
        });
        man13.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                mainFrame.getContentPane().removeAll();
                contentPanel.add(new Controller1(3,mainFrame,contentPanel));
                mainFrame.setVisible(true);
            }
        });
        man14.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                mainFrame.getContentPane().removeAll();
                contentPanel.add(new Controller1(4,mainFrame,contentPanel));
                mainFrame.setVisible(true);
            }
        });
        man15.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                mainFrame.getContentPane().removeAll();
                contentPanel.add(new Controller1(5,mainFrame,contentPanel));
                mainFrame.setVisible(true);
            }
        });
        man21.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                mainFrame.getContentPane().removeAll();
                contentPanel.add(new Controller2(1));
                mainFrame.setVisible(true);
            }
        });
        man22.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                mainFrame.getContentPane().removeAll();
                contentPanel.add(new Controller2(2));
                mainFrame.setVisible(true);
            }
        });
        man23.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                mainFrame.getContentPane().removeAll();
                contentPanel.add(new Controller2(3));
                mainFrame.setVisible(true);
            }
        });
        man24.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                mainFrame.getContentPane().removeAll();
                contentPanel.add(new Controller2(4));
                mainFrame.setVisible(true);
            }
        });
        man25.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                mainFrame.getContentPane().removeAll();
                contentPanel.add(new Controller2(5));
                mainFrame.setVisible(true);
            }
        });
      
        mainFrame.setVisible(true);
    }
}
