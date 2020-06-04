package com.dsource.idc.jellowintl.makemyboard.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.dsource.idc.jellowintl.R;
import com.dsource.idc.jellowintl.SpeechEngineBaseActivity;
import com.dsource.idc.jellowintl.makemyboard.dataproviders.data_models.BoardModel;
import com.dsource.idc.jellowintl.makemyboard.dataproviders.databases.BoardDatabase;
import com.dsource.idc.jellowintl.makemyboard.presenter_interfaces.IBasePresenter;
import com.dsource.idc.jellowintl.makemyboard.view_interfaces.IBaseView;

import static com.dsource.idc.jellowintl.makemyboard.utility.BoardConstants.BOARD_ID;

public abstract class BaseBoardActivity<V extends IBaseView, P extends IBasePresenter<V>, A extends RecyclerView.Adapter> extends SpeechEngineBaseActivity {

    public P mPresenter;
    public A mAdapter;
    public RecyclerView mRecyclerView;
    public BoardModel currentBoard;
    public Context mContext;
    private SparseArray<View> mViewList;

    public abstract int getLayoutId();

    public abstract A getAdapter();

    public abstract void initViewsAndEvents();

    public abstract P createPresenter();

    public abstract void setLayoutManager(RecyclerView recyclerView);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        mViewList = new SparseArray<>();

        mContext = this;

        getCurrentBoard();

        mPresenter = createPresenter();

        mPresenter.attachView((V) this);

        mAdapter = getAdapter();

        mRecyclerView = findViewById(R.id.recycler_view);

        setLayoutManager(mRecyclerView);

        mRecyclerView.setAdapter(mAdapter);

        initViewsAndEvents();
    }

    private void getCurrentBoard() {
        try {
            String boardId = "";

            if (getIntent().getExtras() != null)
                boardId = getIntent().getExtras().getString(BOARD_ID);
            BoardDatabase database = new BoardDatabase(getAppDatabase());
            currentBoard = database.getBoardById(boardId);
        } catch (NullPointerException e) {
            Toast.makeText(this, "Some error occurred", Toast.LENGTH_LONG).show();
        }

    }

    public void refreshBoard(){
        BoardDatabase database = new BoardDatabase(getAppDatabase());
        String boardId = getIntent().getExtras().getString(BOARD_ID);
        currentBoard.setBoardName(database.getBoardName(boardId));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public View getView(int resId) {
        if (mViewList.get(resId) == null)
            mViewList.append(resId, findViewById(resId));
        return mViewList.get(resId);
    }

    public void setVisibility(int resId, boolean isVisible) {
        if (getView(resId) != null)
            getView(resId).setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    public void disableView(int resId, boolean isDisabled) {
        if (getView(resId) != null) {
            if (isDisabled) {
                getView(resId).setEnabled(false);
                getView(resId).setAlpha(.6f);
            } else {
                getView(resId).setEnabled(true);
                getView(resId).setAlpha(1.0f);
            }
        }
    }

    public void setupToolBar(int stringResId){
        if(getSupportActionBar()!=null) {
            enableNavigationBack();
            setupActionBarTitle(View.VISIBLE, getString(R.string.home) + "/ " +
                    getString(R.string.my_boards) + "/ " +
                    currentBoard.getBoardName() + "/ " +
                    getString(stringResId));
            setNavigationUiConditionally();
        }
        findViewById(R.id.iv_action_bar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public int getNumberOfIconPerScreen() {
        switch (currentBoard.getGridSize()) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 8;
        }
        return 9;
    }


}
