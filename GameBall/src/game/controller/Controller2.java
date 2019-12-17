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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.w3c.dom.events.EventTarget;
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
public class Controller2 extends JPanel implements MouseListener, org.w3c.dom.events.MouseEvent, MouseMotionListener, ActionListener {

    private int time;//thời gian diễn ra trò chơi theo miliseconds

    private boolean turn = true;// lượt chơi(true là người 1, false là người 2;

    private boolean play1 = false;// biến choi của người 1 và người 2
    private boolean play2 = false;
    private Image mshi = new ImageIcon("C:\\Users\\BIG CAT\\Desktop\\GameBall01\\GameBall\\GameBall\\src\\game\\controller\\01.jpg").getImage();

    private boolean win1 = false;// kiểm tra thắng hay không
    private boolean win2 = false;

    private boolean checkClick = true;//kiểm tra xem có nhận sự kiện chuột tiếp không

    private final Timer timer1;

    private int clickX;// vị trí click vào màn hình
    private int clickY;

    private double play2Xdir;// vecto chuyển động
    private double play2Ydir;

    private double play1Xdir;// vecto chuyển động
    private double play1Ydir;

    double play1X;
    double play1Y;
    double play2X;
    double play2Y;

    Ball ball_1, ball_2;
    Enemy enemy_1, enemy_2;
    Stage stage;
    Bar bar;
    DeathBar deathBar;
    Player player_1, player_2;
    Gate gate;

    public Controller2(int m) {// quản lý chơi game
        stage = new Stage(m);
        ball_1 = new Ball(stage.getPoint(1, 0), stage.getPoint(1, 1));
        enemy_1 = new Enemy(stage.getPoint(2, 0), stage.getPoint(2, 1));
        ball_2 = new Ball(stage.getPoint(2, 0), stage.getPoint(2, 1));
        enemy_2 = new Enemy(stage.getPoint(1, 0), stage.getPoint(1, 1));
        bar = new Bar(stage.getPoint());
        deathBar = new DeathBar(stage.getPoint());
        if (stage.getGate() == null) {
            gate = null;
        } else {
            gate = new Gate(stage.getGate());
        }
        player_1 = new Player(stage.getPoint(1, 0), stage.getPoint(1, 1));
        player_2 = new Player(stage.getPoint(2, 0), stage.getPoint(2, 1));
        play1X = stage.getPoint(1, 0);
        play1Y = stage.getPoint(1, 1);
        play2X = stage.getPoint(2, 0);
        play2Y = stage.getPoint(2, 1);
        addMouseMotionListener(this);
        addMouseListener((MouseListener) this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer1 = new Timer(0, this);
        timer1.start();// vị trí gắn bát đầu
    }

    public void paint(Graphics g) {
//        g.setColor(Color.gray);
//        g.fillRect(0, 0, 1050, 600);
        g.drawImage(mshi, 0, 0, 1050, 600, null);

        ball_1.draw(g);
        player_1.draw(g);
        bar.draw(g);

        deathBar.draw(g);
        ball_2.draw(g);
        player_2.draw(g);
        enemy_2.draw(g);
        enemy_1.draw(g);
        if (gate != null) {
            gate.draw(g);
        }
        g.drawString("thời gian lượt bắn:", 800, 15);
        g.drawString(Integer.toString(time / 500), 925, 15);
        if (win1 == true) {
            timer1.stop();
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString(" P1-Win", 300, 500);
        }
        if (win2 == true) {
            timer1.stop();
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("P2- Win", 300, 500);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer1.start();
        if (checkClick) {
            time++;
        }
        if (time == 5000) {
            time = 0;
            if (turn) {
                turn = false;
            } else {
                turn = true;
            }
        }
        if (play1) {
            Rectangle n1 = new Rectangle((int) play1X, (int) play1Y, 20, 20);
            Rectangle d1 = new Rectangle(stage.getPoint(2, 0), stage.getPoint(2, 1), 20, 20);
            for (int i = 0; i < stage.getPoint(0, 0) * 2; i = i + 2) {
                if (n1.intersects(bar.getB(i / 2))) {
                    if (play1X < bar.getA()[i + 3][0] || play1X > bar.getA()[i + 4][0] + bar.getA()[i + 3][0]) {
                        play1Xdir = -play1Xdir;
                    } else {
                        play1Ydir = -play1Ydir;
                    }
                    break;
                }
            }
            if (gate != null) {
                if (n1.intersects(gate.getgB(1))) {
                    play1X = gate.getAx(0);
                    play1Y = gate.getAy(0);
                    ball_1.setX((int) (play1X));
                    ball_1.setY((int) (play1Y));
                }
            }
            for (int i = 0; i < stage.getPoint(0, 1); i++) {
                if (n1.intersects(deathBar.getDb(i))) {
                    play1Xdir = 0;
                    play1Ydir = 0;
                    play1X = stage.getPoint(1, 0);
                    play1Y = stage.getPoint(1, 1);
                    play1 = false;
                    turn = false;
                    checkClick = true;
                    time = 0;
                    break;
                }
            }
            if (n1.intersects(d1)) {
                win1 = true;
                play1Xdir = 0;
                play1Ydir = 0;
                play1 = false;
            }
            if (play1X < 0 || play1X > 1030) {
                play1Xdir = -play1Xdir;
//                                        life--;

            }
            if (play1Y < 0 || play1Y > 550) {
                play1Ydir = -play1Ydir;
//                                        life--;

            }
            play1X += play1Xdir;// chuyển động
            play1Y += play1Ydir;

            ball_1.setX((int) (play1X));
            ball_1.setY((int) (play1Y));
        }

        if (play2) {
            Rectangle n2 = new Rectangle((int) play2X, (int) play2Y, 20, 20);
            Rectangle d2 = new Rectangle(stage.getPoint(1, 0), stage.getPoint(1, 1), 20, 20);
            for (int i = 0; i < stage.getPoint(0, 0) * 2; i = i + 2) {
                if (n2.intersects(bar.getB(i / 2))) {
                    if (play2X < bar.getA()[i + 3][0] || play2X > bar.getA()[i + 4][0] + bar.getA()[i + 3][0]) {
                        play2Xdir = -play2Xdir;
                    } else {
                        play2Ydir = -play2Ydir;
                    }
                    break;
                }
            }
            if (gate != null) {
                if (n2.intersects(gate.getgB(1))) {
                    play2X = gate.getAx(0);
                    play2Y = gate.getAy(0);
                    ball_2.setX((int) (play2X));
                    ball_2.setY((int) (play2Y));
                }
            }
            for (int i = 0; i < stage.getPoint(0, 1); i++) {
                if (n2.intersects(deathBar.getDb(i))) {
                    play2Xdir = 0;
                    play2Ydir = 0;
                    play2X = stage.getPoint(2, 0);
                    play2Y = stage.getPoint(2, 1);
                    play2 = false;
                    turn = true;
                    checkClick = true;
                    time = 0;
                    break;
                }
            }
            if (n2.intersects(d2)) {
                win2 = true;
                play2Xdir = 0;
                play2Ydir = 0;
                play2 = false;
            }
            if (play2X < 0 || play2X > 1030) {
                play2Xdir = -play2Xdir;

            }
            if (play2Y < 0 || play2Y > 550) {
                play2Ydir = -play2Ydir;

            }

            play2X += play2Xdir;// chuyển động
            play2Y += play2Ydir;

            ball_2.setX((int) (play2X));
            ball_2.setY((int) (play2Y));
        }

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (checkClick && turn) {
            checkClick = false;
            this.clickX = e.getX();
            this.clickY = e.getY();
            double a = clickX - play1X;
            double b = clickY - play1Y;
            play1Xdir = a / Math.sqrt(a * a + b * b);
            play1Ydir = b / Math.sqrt(a * a + b * b);
            play1 = true;
        }
        if (checkClick && turn == false) {
            checkClick = false;
            this.clickX = e.getX();
            this.clickY = e.getY();
            double a = clickX - play2X;
            double b = clickY - play2Y;
            play2Xdir = a / Math.sqrt(a * a + b * b);
            play2Ydir = b / Math.sqrt(a * a + b * b);
            play2 = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void preventDefault() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initEvent(String eventTypeArg, boolean canBubbleArg, boolean cancelableArg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
