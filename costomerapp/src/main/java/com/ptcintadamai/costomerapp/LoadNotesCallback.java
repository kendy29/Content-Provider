package com.ptcintadamai.costomerapp;


import java.util.ArrayList;

interface LoadNotesCallback {
    void preExecute();
    void postExecute(ArrayList<note> notes);

}

