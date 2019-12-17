package game.obj;


import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BIG CAT
 */
public class Ball {
        public int X;
    public int Y;
    public Ball(int x,int y){
        this.X = x;
        this.Y = y;
    }
    public void draw(Graphics g){
      g.setColor(Color.white);
      g.drawOval(X, Y, 20, 20);
      g.setColor(Color.green);
      g.fillOval(X+5, Y+5, 10, 10);
      
    }

    public void setX(int playX) {
        this.X = playX;
    }

    public void setY(int playY) {
        this.Y = playY;
    }
}
