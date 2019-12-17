package game.obj;


import java.awt.BasicStroke;
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
public class DeathBar {
    
    public int[][] a;
   
    Rectangle[] dB;
   private Image d = new ImageIcon("C:\\Users\\BIG CAT\\Desktop\\GameBall01\\GameBall\\GameBall\\src\\images\\docD.png").getImage();
    private Image n = new ImageIcon("C:\\Users\\BIG CAT\\Desktop\\GameBall01\\GameBall\\GameBall\\src\\images\\ngangD.png").getImage();
    public int numOfDeathBars;//so luong thanh chet
    public DeathBar(int b[][]){
       this.a = b;
       
       numOfDeathBars = a[0][1];
        vRectangles(a);
    }
    public void setA(int[][] a) {
        this.a = a;
    }
    public int[][] getA() {
        return a;
    }
    public void draw(Graphics g){
        
      
        for(int i = 3 + a[0][0]*2; i < a[0][0]*2 + 3 + numOfDeathBars*2; i=i+2){
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
        dB = new Rectangle[numOfDeathBars];
        for(int i = 3 + a[0][0] * 2; i < (a[0][0]+numOfDeathBars)*2 + 3;i = i+2){
           dB[n] = new Rectangle(a[i][0], a[i][1],a[i+1][0],a[i+1][1]);
           n++;
        }
        return dB;
        
    }

    public Rectangle getDb(int x) {
        return dB[x];
    }

   

    public int getNumOfDeathBars() {
        return numOfDeathBars;
    }

    public void setDb(Rectangle[] dB) {
        this.dB = dB;
    }

   
 

    public void setNumOfDeathBars(int numOfDeathBars) {
        this.numOfDeathBars = numOfDeathBars;
    }
    
   
    
}
