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
public class Bar {
    
    public int[][] a;
   
    Rectangle[] b;
    public int numOfBouncedBars;//so luong thanh nay
    private Image d = new ImageIcon("C:\\Users\\BIG CAT\\Desktop\\GameBall01\\GameBall\\GameBall\\src\\images\\doc.png").getImage();
    private Image n = new ImageIcon("C:\\Users\\BIG CAT\\Desktop\\GameBall01\\GameBall\\GameBall\\src\\images\\ngang.png").getImage();
    public Bar(int b[][]){
       this.a = b;
       numOfBouncedBars = a[0][0];
       
        vRectangles(a);
    }
    public void setA(int[][] a) {
        this.a = a;
    }
    public int[][] getA() {
        return a;
    }
    public void draw(Graphics g){
         g.setColor(Color.magenta);
        for(int i = 3; i < numOfBouncedBars*2 + 3;i = i+2){
        if(a[i+1][0]<a[i+1][1]){
                g.drawImage(d,a[i][0], a[i][1],a[i+1][0],a[i+1][1], null);
            }
            else{
                g.drawImage(n,a[i][0], a[i][1],a[i+1][0],a[i+1][1], null);
            }
        }
        
    }
    public Rectangle[] vRectangles(int [][]a){
        int n = 0;
        b = new Rectangle[numOfBouncedBars ];
        for(int i = 3; i < (numOfBouncedBars)*2 + 3;i = i+2){
           b[n] = new Rectangle(a[i][0], a[i][1],a[i+1][0],a[i+1][1]);
           n++;
        }
        return b;
        
    }

    public Rectangle getB(int x) {
        return b[x];
    }

    public int getNumOfBouncedBars() {
        return numOfBouncedBars;
    }

   

    public void setB(Rectangle[] b) {
        this.b = b;
    }

    public void setNumOfBouncedBars(int numOfBouncedBars) {
        this.numOfBouncedBars = numOfBouncedBars;
    }

    
    
   
    
}
