package com.example.mobile1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class NewExamActivity extends AppCompatActivity {

    private static final String FILE_NAME = "exam.txt"  ;

    RecyclerView recyclerView;
    SP sp;
    sinavAdapter adapter;
    ArrayList<sorular> soruListesi = new ArrayList<>();
    ArrayList<sorular> sinavListesi = new ArrayList<>();
    Context context=this;
    Button btnKayit;
    int kulid;
    Uri uri;
    TextView secilen, toplam;
    int sec=0;
    int myNum;

    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exam);

        sp=new SP();
        btnKayit = (Button)findViewById(R.id.sKaydet);

        secilen = (TextView) findViewById(R.id.textView37) ;
        toplam = (TextView) findViewById(R.id.textView39) ;

        recyclerView = findViewById(R.id.recview2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        try {
            fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String sure = sp.getStringValue(context, "sure");
        String puan = sp.getStringValue(context, "puan");
        String zorluk = sp.getStringValue(context, "zorluk");
        String sayi = sp.getStringValue(context, "sayi");


        toplam.setText(sayi);
        secilen.setText("0");
        myNum = Integer.parseInt(sayi);

        try {
            fos.write("Sure:  ".getBytes());
            fos.write(sure.getBytes());
            fos.write("\n".getBytes());
            fos.write("Soru Puanı  :".getBytes());
            fos.write(puan.getBytes());
            fos.write("\n".getBytes());
            fos.write("\n".getBytes());
            fos.write("--------------------------------------------".getBytes());
            fos.write("\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }




        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Sinavim", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS sorular (sid INTEGER PRIMARY KEY, soru VARCHAR, dogru VARCHAR, y1 VARCHAR, y2 VARCHAR, y3 VARCHAR, y4 VARCHAR, uid INT)");
            Cursor cursor = database.rawQuery("SELECT * FROM sorular", null);

            while (cursor.moveToNext()) {
                String s = cursor.getString(1);
                String d = cursor.getString(2);
                int i = cursor.getInt(0);
                String yy1 = cursor.getString(3);
                String yy2 = cursor.getString(4);
                String yy3 = cursor.getString(5);
                String yy4 = cursor.getString(6);
                if(cursor.getInt(7)==sp.getIntValue(context, "userid"))
                    soruListesi.add(new sorular(s, d,  yy1, yy2, yy3, yy4, i));
            }


            adapter = new sinavAdapter(soruListesi, this);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter.setOnItemClickListener(new sinavAdapter.onItemClickListener() {

            @Override
            public void onItemClick(sorular soru) {
                if(myNum<=sec){
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewExamActivity.this);

                    builder.setTitle("Seçilen Soru Sayısı Aşıldı!");
                    builder.setMessage("Toplamda "+sayi+" soruluk sınav hazırlanacaktı. Belirlenen sayıya ulaşıldı. Yine de devam edilsin mi?");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Hayır", null);
                    builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                int zor = Integer.parseInt(zorluk);
                                int number;

                                Random random = new Random();

                                number = random.nextInt(zor) + 1;

                                fos.write("Soru:\n".getBytes());
                                fos.write(soru.getSoru().getBytes());
                                fos.write("\n".getBytes());
                                fos.write("Şıklar:\n".getBytes());

                                int i=1;

                                if(number==i) {
                                    fos.write(soru.getCevap().getBytes());
                                    fos.write("\n".getBytes());
                                    i++;
                                }else if(i<=zor) {
                                    fos.write(soru.getY1().getBytes());
                                    fos.write("\n".getBytes());
                                    i++;
                                }

                                if(number==i) {
                                    fos.write(soru.getCevap().getBytes());
                                    fos.write("\n".getBytes());
                                    i++;
                                }else if(i<=zor) {
                                    fos.write(soru.getY2().getBytes());
                                    fos.write("\n".getBytes());
                                    i++;
                                }

                                if(number==i) {
                                    fos.write(soru.getCevap().getBytes());
                                    fos.write("\n".getBytes());
                                    i++;
                                }else if(i<=zor) {
                                    fos.write(soru.getY3().getBytes());
                                    fos.write("\n".getBytes());
                                    i++;
                                }

                                if(number==i) {
                                    fos.write(soru.getCevap().getBytes());
                                    fos.write("\n".getBytes());
                                    i++;
                                }else if(i<=zor) {
                                    fos.write(soru.getY4().getBytes());
                                    fos.write("\n".getBytes());
                                    i++;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(getApplicationContext(), "İlgili Soru \n" + getFilesDir() + "/" + FILE_NAME + "\nUzantılı Dosyaya Eklendi.",Toast.LENGTH_SHORT).show();
                            sec++;
                            secilen.setText(String.valueOf(sec));


                        }
                    });
                    builder.show();
                }else{
                    try {
                        int zor = Integer.parseInt(zorluk);
                        int number;

                        Random random = new Random();

                        number = random.nextInt(zor) + 1;

                        fos.write("Soru:\n".getBytes());
                        fos.write(soru.getSoru().getBytes());
                        fos.write("\n".getBytes());
                        fos.write("Şıklar:\n".getBytes());

                        int i=1;

                        if(number==i) {
                            fos.write(soru.getCevap().getBytes());
                            fos.write("\n".getBytes());
                            i++;
                        }else if(i<=zor) {
                            fos.write(soru.getY1().getBytes());
                            fos.write("\n".getBytes());
                            i++;
                        }

                        if(number==i) {
                            fos.write(soru.getCevap().getBytes());
                            fos.write("\n".getBytes());
                            i++;
                        }else if(i<=zor) {
                            fos.write(soru.getY2().getBytes());
                            fos.write("\n".getBytes());
                            i++;
                        }

                        if(number==i) {
                            fos.write(soru.getCevap().getBytes());
                            fos.write("\n".getBytes());
                            i++;
                        }else if(i<=zor) {
                            fos.write(soru.getY3().getBytes());
                            fos.write("\n".getBytes());
                            i++;
                        }

                        if(number==i) {
                            fos.write(soru.getCevap().getBytes());
                            fos.write("\n".getBytes());
                            i++;
                        }else if(i<=zor) {
                            fos.write(soru.getY4().getBytes());
                            fos.write("\n".getBytes());
                            i++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    Toast.makeText(getApplicationContext(), "İlgili Soru \n" + getFilesDir() + "/" + FILE_NAME + "\nUzantılı Dosyaya Eklendi.",Toast.LENGTH_SHORT).show();
                    sec++;

                    secilen.setText(String.valueOf(sec));

                }

            }
        });



        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fos != null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }}


                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);

                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.setType("*/*");
                startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));

            }
        });

        }



    }
