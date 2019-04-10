package com.example.administrator.doanchu;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

    public class GridView_Banphim extends BaseAdapter {
        Context context;
        String[] kitu;
        MainActivity mainActivity;


        int dem=0;
 // Giống như GridViewAnswer thì ta làm 1 adapter extends từ BaseAdapter( cơ bản)
        // Ta trả về số lượng bàn kí tự từ a đến z để người dùng click vào và thiết lập sự kiện cho nó
        public GridView_Banphim(Context context, String[] kitu,MainActivity mainActivity) {
            this.context = context;
            this.kitu = kitu;
            this.mainActivity = mainActivity;

        }

        @Override
        public int getCount() {
            return  kitu.length;
        }

        @Override
        public Object getItem(int position) {
            return  kitu[position];
        }

        @Override
        public long getItemId(int position) {
            return  kitu.length;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Button button ;
            if(convertView==null)
            {    button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(75,75));
                button.setBackgroundColor(Color.DKGRAY);
                button.setTextColor(Color.YELLOW);
                button.setPadding(5,5,5,5);
                button.setText(kitu[position]);

            }else { button= (Button)convertView;}


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
              // so sánh kí tự đó đúng hay sai .
                    char compare = kitu[position].charAt(0);
                    dem = 0;
                    // gọi số lần sai từ activity main
                    mainActivity.solansai = 0 ;
                    for (int i = 0; i < mainActivity.answer.length; i++) {
                        // Gắn các từ đó vào trong đáp án nếu đúng

                        if (compare == mainActivity.answer[i]) {
                            // Class Commom gồm để t có thể dễ dàng truy
                            Commom.submit_answer[i] = compare;
                            mainActivity.soundPool.play(mainActivity.id_chienthang,10,10,1,0,1);



                        }
                        // nếu sai
                        else {
                            // ta hình dung nếu độ dài kí tự là 6 nếu nó đúng 1
                            // lần trở lên thì  hàm else solansai trừ đi số lần đó ra sẽ dưới 6
                            // nếu ko hàm if so sánh ko có kí tự nào đúng thì else sẽ là 6 , và lúc đó sẽ kí tự đó sẽ sai .
                            // ta cho solansai +=1 mỗi lần hàm for đếm vì nó đếm nhiều lần
                            // so sánh với độ dài kí tự đúng từ activity main nếu = thì kí tự đó sai .
                            int dodai=mainActivity.dodai_answer[mainActivity.kiemtra.get(mainActivity.stt)];
                            mainActivity.solansai+=1;
                            if(mainActivity.solansai==dodai)
                            {
                                //xử lý khi kí tự sai
                                // soung pool được dùng với id thất bai
                                mainActivity.soundPool.play(mainActivity.id_thatbai,10,10,1,0,1);
                                // số câu sai tăng lên
                                mainActivity.socausai += 1 ;
                                Toast.makeText(mainActivity,"Sai Rồi", Toast.LENGTH_SHORT).show();
                                // Nếu 3 lần sẽ thua
                                if(  mainActivity.socausai==3)
                                {
                                    // Hàm xử lí thua ở activity main được gọi
                                    // Để chuyển tới activity chiển thắng xử lí điểm
                                    mainActivity.kiemtrasolansai();
                                }
                            }

                        }
                    }




                    GridViewAnswer adapteranswer = new GridViewAnswer(Commom.submit_answer, context,mainActivity);
                   mainActivity.gridView_answer.setAdapter(adapteranswer);
                    adapteranswer.notifyDataSetChanged();

                }}

            );

            return  button;

        }
    }