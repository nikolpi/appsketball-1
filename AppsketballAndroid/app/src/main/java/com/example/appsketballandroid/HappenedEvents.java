package com.example.appsketballandroid;

import androidx.appcompat.app.AppCompatActivity;

public class HappenedEvents extends AppCompatActivity {

    public  int SHOT_1_IN=0, SHOT_1_OUT=0,SHOT_2_IN=0,SHOT_2_OUT=0,SHOT_3_IN=0,SHOT_3_OUT=0
                    ,ASSIST=0,BLOCK=0,FOUL=0,MISTAKE=0,STEAL=0,REBOUND=0;


    public int getSHOT_1_IN() {
        return SHOT_1_IN;
    }

    public void setSHOT_1_IN(int SHOT_1_IN) {
        this.SHOT_1_IN = SHOT_1_IN;
    }

    public int getSHOT_1_OUT() {
        return SHOT_1_OUT;
    }

    public void setSHOT_1_OUT(int SHOT_1_OUT) {
        this.SHOT_1_OUT = SHOT_1_OUT;
    }

    public int getSHOT_2_IN() {
        return SHOT_2_IN;
    }

    public void setSHOT_2_IN(int SHOT_2_IN) {
        this.SHOT_2_IN = SHOT_2_IN;
    }

    public int getSHOT_2_OUT() {
        return SHOT_2_OUT;
    }

    public void setSHOT_2_OUT(int SHOT_2_OUT) {
        this.SHOT_2_OUT = SHOT_2_OUT;
    }

    public int getSHOT_3_IN() {
        return SHOT_3_IN;
    }

    public void setSHOT_3_IN(int SHOT_3_IN) {
        this.SHOT_3_IN = SHOT_3_IN;
    }

    public int getSHOT_3_OUT() {
        return SHOT_3_OUT;
    }

    public void setSHOT_3_OUT(int SHOT_3_OUT) {
        this.SHOT_3_OUT = SHOT_3_OUT;
    }

    public int getASSIST() {
        return ASSIST;
    }

    public void setASSIST(int ASSIST) {
        this.ASSIST = ASSIST;
    }

    public int getBLOCK() {
        return BLOCK;
    }

    public void setBLOCK(int BLOCK) {
        this.BLOCK = BLOCK;
    }

    public int getSTEAL() {
        return STEAL;
    }

    public void setSTEAL(int STEAL) {
        this.STEAL = STEAL;
    }

    public int getMISTAKE() {
        return MISTAKE;
    }

    public void setMISTAKE(int MISTAKE) {
        this.MISTAKE = MISTAKE;
    }

    public int getFOUL() {
        return FOUL;
    }

    public void setFOUL(int FOUL) {
        this.FOUL = FOUL;
    }

    public int getREBOUND() {
        return REBOUND;
    }

    public void setREBOUND(int REBOUND) {
        this.REBOUND = REBOUND;
    }

    @Override
    public String toString() {
        return "HappenedEvents{" +
                "SHOT_1_IN=" + SHOT_1_IN +
                ", SHOT_1_OUT=" + SHOT_1_OUT +
                ", SHOT_2_IN=" + SHOT_2_IN +
                ", SHOT_2_OUT=" + SHOT_2_OUT +
                ", SHOT_3_IN=" + SHOT_3_IN +
                ", SHOT_3_OUT=" + SHOT_3_OUT +
                ", ASSIST=" + ASSIST +
                ", BLOCK=" + BLOCK +
                ", FOUL=" + FOUL +
                ", MISTAKE=" + MISTAKE +
                ", STEAL=" + STEAL +
                ", REBOUND=" + REBOUND +
                '}';
    }
}
