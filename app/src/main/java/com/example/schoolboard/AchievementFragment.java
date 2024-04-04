package com.example.schoolboard;

import static com.example.schoolboard.NetworkService.BASE_URL;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schoolboard.ApiResponse.AchievementGrade;
import com.example.schoolboard.ApiResponse.AchievementType;
import com.example.schoolboard.ApiResponse.Achievements;
import com.example.schoolboard.ApiResponse.Event;
import com.example.schoolboard.ApiResponse.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AchievementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AchievementFragment extends Fragment implements AchievementAdapter.OnItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<Achievements> achievements;
    AchievementAdapter achievementAdapter;
    RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AchievementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AchievementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AchievementFragment newInstance(String param1, String param2) {
        AchievementFragment fragment = new AchievementFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievement, container, false);
        achievements = new ArrayList<>();
        recyclerView = view.findViewById(R.id.achievementList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        achievementAdapter = new AchievementAdapter(getActivity(),achievements,this);
        recyclerView.setAdapter(achievementAdapter);
        NetworkService.getInstance().getBackService().getAllAchievementsByUser(User.getId()).enqueue(new Callback<ArrayList<Achievements>>() {
            @Override
            public void onResponse(Call<ArrayList<Achievements>> call, Response<ArrayList<Achievements>> response) {
                achievements.addAll(response.body());
                int count = 0;
                for(int i = 0; i < achievements.size(); i++){
                    int finalI = i;
                    NetworkService.getInstance().getBackService().getAchievementType(achievements.get(i).getAchievementTypeId()).enqueue(new Callback<AchievementType>() {
                        @Override
                        public void onResponse(Call<AchievementType> call, Response<AchievementType> response) {
                            achievements.get(finalI).achievementType = response.body();
                            System.out.println("Bingo2");
                            Log.d("Test1: ", achievements.get(finalI).achievementType.getTitle());
                        }

                        @Override
                        public void onFailure(Call<AchievementType> call, Throwable t) {

                        }
                    });
                    NetworkService.getInstance().getBackService().getAchievementGrade(achievements.get(i).getAchievementGradeId()).enqueue(new Callback<AchievementGrade>() {
                        @Override
                        public void onResponse(Call<AchievementGrade> call, Response<AchievementGrade> response) {
                            achievements.get(finalI).achievementGrade = response.body();
                            System.out.println("Bingo1");

                            Log.d("Test1: ", achievements.get(finalI).achievementGrade.getTitle());
                        }

                        @Override
                        public void onFailure(Call<AchievementGrade> call, Throwable t) {

                        }
                    });
//                    Log.d("Test: ",
//                            achievements.get(finalI).achievementType.getTitle());
//                    Log.d("Test2: ", achievements.get(finalI).achievementGrade.getTitle());
                }
                System.out.println("Bingo3");
                System.out.println("Bingo4");
            }

            @Override
            public void onFailure(Call<ArrayList<Achievements>> call, Throwable t) {

            }
        });
        return view;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        }, 1500);
    }

    public void onItemClick(int position){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(BASE_URL+achievements.get(position).getFile()));
        startActivity(intent);
    }
}