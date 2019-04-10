package com.example.administrator.doanchu;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class GridViewAnswer extends BaseAdapter {
    char[] answerchar;
    Context context;
    MainActivity mainActivity;
    // Bạn nên tìm hiểu về cách Adapter cho GridView hay Listview
    // nó luôn như thế này , làm vài lần sẽ quen
    public GridViewAnswer(char[] answerchar, Context context,MainActivity mainActivity) {
        this.answerchar = answerchar;
        this.context = context;
        this.mainActivity = mainActivity;
    }

    @Override

    public int getCount() {
        return mainActivity.dodai_answer[mainActivity.kiemtra.get(mainActivity.stt)];
    }

    @Override
    public Object getItem(int i) {
        return answerchar[i];
    }

    @Override
    public long getItemId(int i) {
        return answerchar.length;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Button button;
        // tạo các button trống với số lượng kí tự của chuỗi result đã truyền vào
        if (view == null) {
            button = new Button(context);
            // căn lề
            button.setPadding(8, 8, 8, 8);
            // độ dài rộng cho Ô
            button.setLayoutParams(new GridView.LayoutParams(60, 60));
            button.setBackgroundColor(Color.DKGRAY);
            button.setTextColor(Color.YELLOW);
            button.setText(String.valueOf(answerchar[i]));


        } else {
            button = (Button) view;

        }
        return button;
    }
}
