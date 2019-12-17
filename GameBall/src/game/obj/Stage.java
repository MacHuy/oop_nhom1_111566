package game.obj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BIG CAT
 */
public class Stage {

    public int gate[][];// cổng ra trước cổng vào sau
    public int gate1[][] = {{900, 30}, {20, 60}, {100, 400}, {20, 60}};
    public int gate2[][] = {{830, 350}, {60, 20}, {375, 250}, {60, 20}};
    public int gate3[][] = {{830, 350}, {60, 20}, {375, 250}, {60, 20}};
    public int gate4[][] = {{820, 170}, {60, 20}, {500, 300}, {60, 20}};
    public int gate5[][] ={{525,150},{20,60},{525,350},{20,100}};
    public int point1[][] = {{2, 1}, {200, 300}, {700, 300}, {515, 250}, {30, 250}, {315, 150}, {400, 30}, {850, 150}, {20, 150}};
    public int point2[][] = {{4, 2}, {100, 250}, {950, 520}, {50, 50}, {100, 30}, {300, 200}, {20, 200}, {800, 500}, {150, 10}, {400, 400}, {100, 20},{970,100},{30,300},{500,30},{200,30}};
    public int point3[][] = {{5, 3}, {50, 520}, {950, 130}, {120, 30}, {250, 20}, {200, 250}, {20, 250}, {300, 530}, {200, 20}, {540, 30}, {200, 20}, {650, 500}, {180, 20}, {545, 275}, {100, 20}, {770, 30}, {200, 20}, {275, 1020}, {20, 250}};
    public int point[][];
    public int point4[][] = {{3, 4}, {100, 475}, {900, 70}, {160, 150}, {150, 30}, {650, 450}, {150, 30}, {700, 50}, {30, 120}, {10, 30}, {20, 150}, {10, 420}, {20, 150}, {1000, 30}, {20, 150}, {1000, 420}, {20, 150}};
    public int point5[][] ={{3,4},{100, 475}, {900, 70}, {160, 150}, {150, 30},{650, 450}, {150, 30}, {700, 50}, {30, 180},{10,30},{150,20},{10,550},{150,20},{890,30},{150,20},{890,550},{150,20}};
    public Stage(int stage) {
        switch (stage) {
            case 1:
                point = point1;
                gate = gate1;
                break;
            case 2:
                point = point2;
                gate=gate2;
                break;
            case 3:
                point = point3;
                gate = gate3;
                break;
            case 4:
                point = point4;
                gate = gate4;
                break;
            case 5:
                point = point5;
                gate = gate5;
            default:
                break;
        }
    }

    public int getPoint(int x, int y) {
        return point[x][y];
    }

    public int[][] getPoint() {
        return point;
    }

    public void setPoint(int[][] point) {
        this.point = point;
    }

    public int[][] getGate() {
        return gate;
    }

    public void setGate(int[][] gate) {
        this.gate = gate;
    }

}
