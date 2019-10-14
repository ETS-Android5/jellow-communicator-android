package com.dsource.idc.jellowintl.makemyboard.databases;

import com.dsource.idc.jellowintl.makemyboard.icon_select_module.presenters.iDataPresenter;
import com.dsource.idc.jellowintl.makemyboard.models.BoardModel;
import com.dsource.idc.jellowintl.models.AppDatabase;

import java.util.ArrayList;

/**
 * Created by Ayaz Alam on 31/05/2018.
 */
public class BoardDatabase{


    private AppDatabase database;

    public BoardDatabase(AppDatabase context) {
        this.database = context;
    }

    public void addBoardToDatabase(final BoardModel board) {
        database.boardDao().insertBoard(board);
    }

    public BoardModel getBoardById(String boardID) {
        return database.boardDao().getBoardById(boardID);
    }

    public void getAllBoards(final iDataPresenter<ArrayList<BoardModel>> presenter) {
        presenter.onSuccess(new ArrayList<>(database.boardDao().getAllBoards()));
    }
    public void getAllBoards(final String langCode, final iDataPresenter<ArrayList<BoardModel>> presenter) {
        presenter.onSuccess(new ArrayList<>(database.boardDao().getAllBoards(langCode)));
    }
    public void updateBoardIntoDatabase(BoardModel board) {
        database.boardDao().updateBoard(board);
    }

    public void deleteBoard(BoardModel boardID) {
        database.boardDao().deleteBoard(boardID);
    }
}