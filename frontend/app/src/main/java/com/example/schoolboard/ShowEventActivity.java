package com.example.schoolboard;

import static com.example.schoolboard.NetworkService.BASE_URL;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Highlights;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolboard.ApiResponse.ApiError;
import com.example.schoolboard.ApiResponse.Event;
import com.example.schoolboard.ApiResponse.RecordResponse;
import com.example.schoolboard.ApiResponse.User;
import com.example.schoolboard.ApiResponse.UserJWT;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowEventActivity extends AppCompatActivity {

    final String NEW_FORMAT_DATE = "dd.MM.yyyy";

    final String NEW_FORMAT_TIME = "HH:mm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_event);
        Intent i = getIntent();
        Event event = (Event) i.getParcelableExtra("extra");
        ImageView imageView = findViewById(R.id.eventShowImageView);
        TextView title = findViewById(R.id.eventTitleTextView);
        TextView content = findViewById(R.id.eventContentTextView);
        TextView statement = findViewById(R.id.eventStatementTextView);
        TextView dateOfEvent = findViewById(R.id.eventDateTextView);
        TextView time = findViewById(R.id.eventTimeTextView);
        TextView organ = findViewById(R.id.eventOrganTextView);
        TextView grade = findViewById(R.id.eventGradeTextView);
        TextView length = findViewById(R.id.eventLengthTextView);
        TextView address = findViewById(R.id.eventAddressTextView);
        Button sumbit = findViewById(R.id.eventSubmitButton);

        title.setText(event.getTitle());
        content.setText(event.getContent());
        Picasso.with(getApplicationContext()).load(BASE_URL+event.getImage()).into(imageView);
        imageView.setVisibility(View.VISIBLE);

        Locale locale = new Locale("ru", "RU");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", locale);
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Yakutsk"));
        Date date = null;
        try {
            date = formatter.parse(event.getDateOfEvent());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        formatter.applyPattern(NEW_FORMAT_DATE);
        String formattedDateString = formatter.format(date);
        formatter.applyPattern(NEW_FORMAT_TIME);
        String formattedTimeString = formatter.format(date);
        dateOfEvent.setText(formattedDateString);
        time.setText(formattedTimeString);
        length.setText(event.getLength());
        grade.setText(event.getGradeStart() + " - " + event.getGradeEnd());
        address.setText(event.getAddress());

        organ.setText(event.getOrganization());


        if(!event.getStatement().equals("null")){
            statement.setText("ПОЛОЖЕНИЕ");

            statement.setVisibility(View.VISIBLE);
        }

        NetworkService.getInstance().getBackService().getOneRecord(User.id, event.id).enqueue(new Callback<RecordResponse>() {
            @Override
            public void onResponse(Call<RecordResponse> call, Response<RecordResponse> response) {
                if(response.isSuccessful()){
                    if(event.isInternal()){
                        sumbit.setVisibility(View.VISIBLE);
                        sumbit.setEnabled(false);
                        sumbit.setText("Вы уже подали заявку");
                        System.out.println("Bungalo10");
                    }
                }else{
                    if(event.isInternal()){
                        sumbit.setVisibility(View.VISIBLE);
                        sumbit.setBackgroundResource(R.drawable.gradient);
                    }
                }

            }

            @Override
            public void onFailure(Call<RecordResponse> call, Throwable t) {

            }
        });
        statement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Download_PDF_View_Intent(BASE_URL+event.getStatement());
            }
        });

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
//                    NetworkService.getInstance().getBackService().sendRecord(User.id, event.id).execute();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
                NetworkService.getInstance().getBackService().sendRecord(User.id, event.id).enqueue(new Callback<RecordResponse>() {
                    @Override
                    public void onResponse(Call<RecordResponse> call, Response<RecordResponse> response) {
                        if(response.isSuccessful()){
                            RecordResponse recordResponse = response.body();
                            sumbit.setEnabled(false);
                            sumbit.setBackgroundResource(R.drawable.event_button_bg);
                            sumbit.setText("Ваша заявка принята");
                        }else{
                            try {
                                Gson gson = new Gson();
                                ApiError message=gson.fromJson(response.errorBody().charStream(),ApiError.class);
                                Toast.makeText(getApplicationContext(), message.message(), Toast.LENGTH_LONG).show();
                                sumbit.setEnabled(false);
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<RecordResponse> call, Throwable t) {

                    }
                });
            }
        });

    }
    private void Download_PDF_View_Intent(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}