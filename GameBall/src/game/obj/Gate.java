package game.obj;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
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
public class Gate {
    
    public int[][] a;
    private Image v = new ImageIcon("C:\\Users\\BIG CAT\\Desktop\\GameBall01\\GameBall\\GameBall\\src\\images\\vao.png").getImage();
    private Image r = new ImageIcon("C:\\Users\\BIG CAT\\Desktop\\GameBall01\\GameBall\\GameBall\\src\\images\\ra.png").getImage();
    Rectangle[] gB;
    public int numOfGate;//so luong thanh nay
    
    public Gate(int b[][]){
       this.a = b;
       numOfGate = 2;
       
        vRectangles(a);
    }
    public void setA(int[][] a) {
        this.a = a;
    }
    public int getAx(int i) {
        return a[i*2][0];
    }
    public int getAy(int i){
        return a[i*2][1];
    }
    public void draw(Graphics g){
           g.drawImage(r, a[0][0], a[0][1],a[1][0],a[1][1], null);
           g.drawImage(v, a[2][0], a[2][1],a[3][0],a[3][1], null);
//         g.setColor(Color.blue);
//         g.fillRect(a[0][0], a[0][1],a[1][0],a[1][1]);
//         g.setColor(Color.green);
//         g.fillRect(a[2][0], a[2][1],a[3][0],a[3][1]);
        
    }
    public Rectangle[] vRectangles(int [][]a){
        int n = 0;
        gB = new Rectangle[2];
        gB[0] = new Rectangle(a[0][0], a[0][1],a[1][0],a[1][1]);
        gB[1] = new Rectangle(a[2][0], a[2][1],a[3][0],a[3][1]);
        
        return gB;
        
    }

    public Rectangle getgB(int i) {
        return gB[i];
    }

    public void setgB(Rectangle[] gB) {
        this.gB = gB;
    }
    
    

    

    
    
   
    
}
