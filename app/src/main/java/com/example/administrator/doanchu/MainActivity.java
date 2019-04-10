package com.example.administrator.doanchu;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.administrator.doanchu.MainActivity;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public GridView gridView_answer;
    public int solansai = 0 ;
    public  int socausai= 0 ;
    public GridView gridViewbanphim;
    Button buttonsubmit;
    ImageView imagequestion;
    public SoundPool soundPool;
    int[] image_list = {R.drawable.bag,R.drawable.bike,R.drawable.book,R.drawable.car,R.drawable.cat
            ,R.drawable.chair,R.drawable.chicken,R.drawable.clock,R.drawable.dish,R.drawable.dog,R.drawable.fan,
            R.drawable.fish,R.drawable.house,R.drawable.light,R.drawable.motor,R.drawable.pan,R.drawable.paper
            ,R.drawable.pencil,R.drawable.phone,R.drawable.pig,R.drawable.plane,R.drawable.road
            ,R.drawable.ruler,R.drawable.school,R.drawable.shirt,R.drawable.shoe,R.drawable.table,R.drawable.tree};
    public char[] answer;
    String correct_answer;
    public  int id_chienthang,id_thatbai;
    int count = 120;
    public ArrayList<Integer> kiemtra;

    public GridViewAnswer adapteranswer;
    public GridView_Banphim adapter_banphim;
    int stt=0;
    // Tạo 1 chuỗi String gồm những từ đúng để có thể lấy ra so sánh với đáp án đúng
    public String[] correct = {"bag","bike","book","car","cat","chair","chicken",
            "clock","dish","dog","fan","fish","house","light","motor","pan","paper","pencil"
            ,"phone","pig","plane","road","ruler","school","shirt","shoe","table","tree"};
    // 1 chuỗi độ dài dùng cho ham for
    public int dodai_answer[] = {3,4,4,3,3,5,7,5,4,3,3,4,5,5,5,3,5,6,5,3,5,4,5,6,5,4,5,4};
 // Chuỗi string các kí tự dùng cho sự kiện click vào button trong Gridview_Banphim( Tạo các nút button)
    public static String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public int score = 0;
    MediaPlayer mediaPlayerground;
    ImageView back ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        back = (ImageView) findViewById(R.id.back_game);
        // soundpool tạo ra âm thanh ngắn , cụ thể là âm thanh thua và thắng
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,1);
        // 2 id thắng và thua , cần dùng thì gọi
        id_chienthang = soundPool.load(this,R.raw.good,1);
        id_thatbai = soundPool.load(this,R.raw.ohno,1);
        // hàm này dùng để Random các câu hỏi 1 cách ngẫu nhiên .
        kiemtra = new ArrayList<Integer>();
        for (int i = 0; i < correct.length; i++) {
            kiemtra.add(i);
            // xáo trộn thứ tự
            Collections.shuffle(kiemtra);
        }
 // Media player tạo các âm thanh dài , cụ thể là âm thanh game
        mediaPlayerground = new MediaPlayer();
        mediaPlayerground = MediaPlayer.create(MainActivity.this,R.raw.game_chinhthuc);
        mediaPlayerground.setLooping(true);
        mediaPlayerground.start();
// Liên kết với activity các thành phần có trong game
        init();
        // gọi capnhat ở lần chạy đầu tiên
        capnhat();
        // Sự kiện cho nút thoát ra
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển về màn hình menu
                Intent intent = new Intent(MainActivity.this,Activity_menu.class);
                startActivity(intent);

            }
        });
        // Sự kiện kiểm tra các tự đã đúng và đủ chưa , nếu đủ và đúng rồi thì sang câu hỏi mới
        // Bạn có thể dùng Handler để cập nhật cho đoạn này cũng được
        buttonsubmit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (score ==20) {
                    mediaPlayerground.stop();

                    Intent intent = new Intent(MainActivity.this, Activity_Chienthang.class);
                    intent.putExtra("Score_game", score);
                    startActivity(intent);
                }

                if (Commom.submit_answer == null) {

                } else {
                    String result = "";
                    // kiểm tra xem đáp án có đúng ko !
                    for (int i = 0; i < dodai_answer[kiemtra.get(stt)]; i++) {
                        result += String.valueOf(Commom.submit_answer[i]);
                    }
                    // Nếu đúng thì cập nhật lại câu hỏi mới bằng phương thức setuplist !
                    if (result.equals(correct_answer)) {

                        Toast.makeText(MainActivity.this, "You Very Good !", Toast.LENGTH_SHORT).show();
                        score += 1;
                        stt+=1;
                        capnhat();

                    }
                }
            }
            //

        });
    }

    private void capnhat() {
        for (int i = 0; i < Commom.submit_answer.length; i++) {
            Commom.submit_answer[i] = ' ';
        }
        imagequestion.setImageResource(image_list[kiemtra.get(stt)]);
        correct_answer = correct[kiemtra.get(stt)];
        answer = correct_answer.toCharArray();
        adapter_banphim = new GridView_Banphim(MainActivity.this, alphabet, MainActivity.this);
        adapteranswer = new GridViewAnswer(setupnullist(), this, MainActivity.this);
        gridViewbanphim.setAdapter(adapter_banphim);
        gridView_answer.setAdapter(adapteranswer);
        adapter_banphim.notifyDataSetChanged();






    }
    public void kiemtrasolansai(){
        // nếu sai đứng số lần thì chuyển sang màn hình thua và gắn thêm dữ liệu để truyền tới màn hình thua
        mediaPlayerground.stop();
        Intent intent = new Intent(MainActivity.this, Activity_Chienthang.class);
        intent.putExtra("Score_game", score);
        startActivity(intent);

    }
    private void init() {
        imagequestion = (ImageView) findViewById(R.id.imagequestion);
        gridView_answer=(GridView)findViewById(R.id.gridviewanswer);
        gridViewbanphim=(GridView)findViewById(R.id.gridviewbanphim);
        buttonsubmit=(Button)findViewById(R.id.buttonsubmit);

    }
    private char[] setupnullist() {
        // tạo ra 1 chuỗi kí tự ' ' với độ dài bằng Kết quả đúng
        // Chuyền chuỗi này vào GrindView để GridView có thể tạo ra số ô đáp án bằng độ dài các kí tự có trong đáp án
        // Ví dụ từ Cat : tạo ra 3 ô
        char result[] = new char[dodai_answer[kiemtra.get(stt)]];
        // result = correct_answer.toCharArray();
        for(int i=0;i<dodai_answer[kiemtra.get(stt)];i++)
        {result[i] =' ';}
        // trả về 1 chuỗi result gồm các ô trống ;
        return  result;
    }
    // Sự kiện nút thoát thoát trên điện thoại
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Do you want to exit ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                        startActivity(intent);
                        finish();
                        System.exit(0);
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                    }
                });
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }}