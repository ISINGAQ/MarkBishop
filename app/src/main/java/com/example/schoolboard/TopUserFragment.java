package com.example.schoolboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schoolboard.ApiResponse.Event;
import com.example.schoolboard.ApiResponse.ShopList;
import com.example.schoolboard.ApiResponse.TopUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopUserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<TopUser> topUsers;
    TopUserAdapter topUserAdapter;
    RecyclerView recyclerView;

    public TopUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopUserFragment newInstance(String param1, String param2) {
        TopUserFragment fragment = new TopUserFragment();
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
        View view = inflater.inflate(R.layout.fragment_top_user, container, false);
        topUsers = new ArrayList<>();
        recyclerView = view.findViewById(R.id.topUsers);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        topUserAdapter = new TopUserAdapter(getActivity(),topUsers,this::onItemClick);
        recyclerView.setAdapter(topUserAdapter);
        NetworkService.getInstance().getBackService().getTop10().enqueue(new Callback<ArrayList<TopUser>>() {
            @Override
            public void onResponse(Call<ArrayList<TopUser>> call, Response<ArrayList<TopUser>> response) {
                topUsers.addAll(response.body());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<TopUser>> call, Throwable t) {

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    public void onItemClick(int position){
//        Intent intent = new Intent(getActivity(), ShowEventActivity.class);
//        Event s = events.get(position);
//        intent.putExtra("extra",s);
//        startActivity(intent);
    }
}