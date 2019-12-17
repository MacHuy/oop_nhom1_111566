package game.obj;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BIG CAT
 */
public class Enemy {
     private int enemyX;
    private int enemyY;
    private Image a = new ImageIcon("C:\\Users\\BIG CAT\\Desktop\\GameBall01\\GameBall\\GameBall\\src\\images\\file.png").getImage();
    public Enemy(int x,int y){
    this.enemyX = x;
    this.enemyY = y;
    }

    public void setEnemyX(int enemyX) {
        this.enemyX = enemyX;
    }

    public void setEnemyY(int enemyY) {
        this.enemyY = enemyY;
    }

    public int getEnemyX() {
        return enemyX;
    }

    public int getEnemyY() {
        return enemyY;
    }
    public void draw(Graphics g){
//        g.setColor(Color.YELLOW);
//        g.fillRect(enemyX, enemyY, 20, 20);
    g.drawImage(a, enemyX, enemyY, 30   , 30    , null);
    }
}
