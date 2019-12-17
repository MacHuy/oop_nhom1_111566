package game.controller;


import game.obj.Ball;
import game.obj.Bar;
import game.obj.DeathBar;
import game.obj.Enemy;
import game.obj.Gate;
import game.obj.Player;
import game.obj.Stage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BIG CAT
 */
public class Controller1 extends JPanel implements MouseListener, org.w3c.dom.events.MouseEvent, MouseMotionListener, ActionListener {

    private int next;
    private JFrame mainFrame;
    private JPanel contentPanel;
    private Image mshi = new ImageIcon("C:\\Users\\BIG CAT\\Desktop\\GameBall01\\GameBall\\GameBall\\src\\game\\controller\\01.jpg").getImage();
    public void setNext(int n) {
        this.next = n;
    }

    public int getNext() {
        return this.next;
    }
    private boolean play = false;// bắt đầu

    private boolean win = false;// kiểm tra thắng hay không
    private int m;
    int clickCount = 0;// đếm số lần nhấn chuột
    public int life = 10;
    private final Timer timer;
    private final int delay = 0;// biến chậm

    private int clickX;// vị trí click vào màn hình
    private int clickY;

    private double playXdir;// vecto chuyển động
    private double playYdir;

    public double playX;
    public double playY;

    Ball ball;
    Enemy enemy;
    Stage stage;
    Bar bar;
    DeathBar deathBar;
    Player player;
    Gate gate;

    public Controller1(int m, JFrame mainFrame, JPanel contentPanel) {// quản lý chơi game
        this.m = m;
        this.mainFrame = mainFrame;
        this.contentPanel = contentPanel;
        stage = new Stage(m);
        ball = new Ball(stage.getPoint(1, 0), stage.getPoint(1, 1));
        enemy = new Enemy(stage.getPoint(2, 0), stage.getPoint(2, 1));
        bar = new Bar(stage.getPoint());
        deathBar = new DeathBar(stage.getPoint());
        if (stage.getGate() == null) {
            gate = null;
        } else {
            gate = new Gate(stage.getGate());
        }
        player = new Player(stage.getPoint(1, 0), stage.getPoint(1, 1));
        playX = stage.getPoint(1, 0);
        playY = stage.getPoint(1, 1);
        addMouseMotionListener(this);
        addMouseListener((MouseListener) this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();// vị trí gắn bát đầu
    }

    @Override
    public void paint(Graphics g) {
        
//        g.setColor(Color.gray);
//        g.fillRect(0, 0, 1050, 600);
        g.drawImage(mshi, 0, 0, 1050, 600, null);
        enemy.draw(g);
        ball.draw(g);
        bar.draw(g);
        player.draw(g);
        deathBar.draw(g);
        if (gate != null) {
            gate.draw(g);
        }
        g.drawString("Số lần bắn còn lại:", 800, 15);
        g.drawString(Integer.toString(life), 925, 15);
        if (life == 0 && win == false) {
            timer.stop();
            life=10;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            Object[] options1 = {"Next Level",
                "Replay",
                "Exit Game"};
            int n1 = JOptionPane.showOptionDialog(null, "You lose", "Sorry", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options1,
                    options1[2]);
            if (n1 == JOptionPane.YES_OPTION && m < 5) {
                mainFrame.getContentPane().removeAll();
                contentPanel.add(new Controller1(m + 1, mainFrame, contentPanel));
                mainFrame.setVisible(true);
            }
            if (n1 == JOptionPane.NO_OPTION ) {
                mainFrame.getContentPane().removeAll();
                contentPanel.add(new Controller1(m, mainFrame, contentPanel));
                mainFrame.setVisible(true);
            }
            if (n1 == JOptionPane.CANCEL_OPTION ) {
                System.exit(0);
            }
             
            
        }
        if (win == true) {
            play = false;
            timer.stop();
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            Object[] options = {"Next Level",
                "Replay",
                "Exit Game"};
            int n = JOptionPane.showOptionDialog(null, "You Win", "Congratulations", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);
            if (n == JOptionPane.YES_OPTION && m < 5) {
                mainFrame.getContentPane().removeAll();
                contentPanel.add(new Controller1(m + 1, mainFrame, contentPanel));
                mainFrame.setVisible(true);
            }
            if (n == JOptionPane.NO_OPTION ) {
                mainFrame.getContentPane().removeAll();
                contentPanel.add(new Controller1(m, mainFrame, contentPanel));
                mainFrame.setVisible(true);
            }
            if (n == JOptionPane.CANCEL_OPTION ) {
                System.exit(0);
            }
            win = false;
            timer.start();

        }

        if (play == false && win == false) {

            playX = stage.getPoint(1, 0);
            playY = stage.getPoint(1, 1);
            ball.setX((int) (playX));
            ball.setY((int) (playY));
            playXdir = 0;
            playYdir = 0;
        }
        g.dispose();
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            // xử lý va chạm
            Rectangle n1 = new Rectangle((int) playX, (int) playY, 20, 20);
            Rectangle d1 = new Rectangle(stage.getPoint(2, 0), stage.getPoint(2, 1), 20, 20);
            for (int i = 0; i < stage.getPoint(0, 1); i++) {
                if (n1.intersects(deathBar.getDb(i))) {
                    playXdir = 0;
                    playYdir = 0;
                    play = false;
                    life--;
                    break;
                }
            }
            if (gate != null) {
                if (n1.intersects(gate.getgB(1))) {
                    playX = gate.getAx(0);
                    playY = gate.getAy(0);
                    ball.setX((int) (playX));
                    ball.setY((int) (playY));
                }

            }
            for (int i = 0; i < stage.getPoint(0, 0) * 2; i = i + 2) {
                if (n1.intersects(bar.getB(i / 2))) {
                    if (playX < bar.getA()[i + 3][0] || playX > bar.getA()[i + 4][0] + bar.getA()[i + 3][0]) {
                        playXdir = -playXdir;
                    } else {
                        playYdir = -playYdir;
                    }
                    break;
                }
            }
            if (n1.intersects(d1)) {
                win = true;
                playXdir = 0;
                playYdir = 0;
                play = false;
            }
            if (playX < 0 || playX > 1030) {
                playXdir = -playXdir;
//                                        life--;

            }
            if (playY < 0 || playY > 550) {
                playYdir = -playYdir;
//                                        life--;

            }
            // chuyển động
            playX += playXdir;
            playY += playYdir;

            ball.setX((int) (playX));
            ball.setY((int) (playY));
        }

        repaint();
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (clickCount == 0 && win == false) {
            clickCount++;
            this.clickX = e.getX();
            this.clickY = e.getY();
            double a = clickX - playX;
            double b = clickY - playY;
            playXdir = a / Math.sqrt(a * a + b * b);
            playYdir = b / Math.sqrt(a * a + b * b);
            play = true;
        }
        if (clickCount < 10 && play == false && win == false) {
            clickCount++;
            this.clickX = e.getX();
            this.clickY = e.getY();
            double a = clickX - playX;
            double b = clickY - playY;
            playXdir = a / Math.sqrt(a * a + b * b);
            playYdir = b / Math.sqrt(a * a + b * b);
            play = true;
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }

    @Override
    public int getScreenX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getScreenY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getClientX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getClientY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getCtrlKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getShiftKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getAltKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getMetaKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public short getButton() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventTarget getRelatedTarget() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initMouseEvent(String typeArg, boolean canBubbleArg, boolean cancelableArg, AbstractView viewArg, int detailArg, int screenXArg, int screenYArg, int clientXArg, int clientYArg, boolean ctrlKeyArg, boolean altKeyArg, boolean shiftKeyArg, boolean metaKeyArg, short buttonArg, EventTarget relatedTargetArg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractView getView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getDetail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initUIEvent(String typeArg, boolean canBubbleArg, boolean cancelableArg, AbstractView viewArg, int detailArg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventTarget getTarget() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventTarget getCurrentTarget() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public short getEventPhase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getBubbles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getCancelable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getTimeStamp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stopPropagation() {
    }

    @Override
    public void preventDefault() {
    }

    @Override
    public void initEvent(String eventTypeArg, boolean canBubbleArg, boolean cancelableArg) {
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
    }

}

