package com.dsource.idc.jellowboard.makemyboard;

import android.support.annotation.Keep;

import com.dsource.idc.jellowboard.makemyboard.UtilityClasses.IconModel;

import java.io.Serializable;

/**
 * This class is for storing the instance of the Board object
 */

public class Board implements Serializable {
    @Keep
    public String boardTitle;
    @Keep
    public String boardID;
    private byte[] boardIcon;
    private boolean boardCompleted=false;
    private boolean boardIconsSelected=false;
    private IconModel boardIconModel;
    private int gridSize=3;
    private boolean passedAddEditIconScreen=false;

    @Keep
    public Board(String Uid, String boardTitle, byte[] boardIcon) {
        this.boardTitle = boardTitle;
        this.boardID = Uid;
        this.boardIcon=boardIcon;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardID() {
        return boardID;
    }

    public void setBoardID(String boardID) {
        this.boardID = boardID;
    }

    public byte[] getBoardIcon() {
        return boardIcon;
    }

    public void setBoardIcon(byte[] boardIcon) {
        this.boardIcon = boardIcon;
    }

    public void setBoardCompleted()
    {
        boardCompleted=true;
    }
    public boolean getBoardCompleteStatus()
    {
        return boardCompleted;
    }
    public boolean isBoardIconsSelected() {
        return boardIconsSelected;
    }
    public boolean isAddEditIconScreenPassed()
    {
        return passedAddEditIconScreen;
    }
    public void setAddEditIconScreenPassed()
    {
         passedAddEditIconScreen=true;
    }


    private void setBoardIconsSelected(boolean boardIconsSelected) {
        this.boardIconsSelected = boardIconsSelected;
    }
    @Keep
    public IconModel getBoardIconModel() {
        return boardIconModel;
    }
    @Keep
    public void setBoardIconModel(IconModel boardIconModel) {
        this.boardIconModel = boardIconModel;
        if(boardIconModel.getChildren().size()>0)
        setBoardIconsSelected(true);
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public int getGridSize() {
        return gridSize;
    }
}