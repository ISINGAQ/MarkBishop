package com.example.schoolboard;

import static com.example.schoolboard.NetworkService.BASE_URL;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.schoolboard.ApiResponse.Event;
import com.example.schoolboard.ApiResponse.ShopList;
import com.example.schoolboard.ApiResponse.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopListFragment extends Fragment {

    ArrayList<ShopList> shopLists;
    ShopAdapter shopAdapter;
    RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShopListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopListFragment newInstance(String param1, String param2) {
        ShopListFragment fragment = new ShopListFragment();
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
        View view = inflater.inflate(R.layout.fragment_shop_list, container, false);
        // Inflate the layout for this fragment
        TextView currency = view.findViewById(R.id.shopCurrencyView);
        currency.setText("Ваш текущий баланс: " + String.valueOf(User.getCurrentPoints()));
        shopLists = new ArrayList<>();
        recyclerView = view.findViewById(R.id.shopList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        shopAdapter = new ShopAdapter(getActivity(),shopLists,this::onItemClick);
        recyclerView.setAdapter(shopAdapter);
        NetworkService.getInstance().getBackService().getAllShops().enqueue(new Callback<ArrayList<ShopList>>() {
            @Override
            public void onResponse(Call<ArrayList<ShopList>> call, Response<ArrayList<ShopList>> response) {
                if(response.isSuccessful()){
                    shopLists.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ShopList>> call, Throwable t) {

            }
        });
        return view;
    }
    public void onItemClick(int position){
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse(BASE_URL+achievements.get(position).getFile()));
//        startActivity(intent);
    }
}