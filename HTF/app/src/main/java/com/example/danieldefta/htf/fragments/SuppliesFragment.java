package com.example.danieldefta.htf.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.danieldefta.htf.R;
import com.example.danieldefta.htf.models.Supply;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class SuppliesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_ACCESS_TOKEN = "accessToken";
    private static final String API_URL = "https://htf2018.now.sh/";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private String accessToken;
    private OnListFragmentInteractionListener mListener;

    private MySupplyRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SuppliesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SuppliesFragment newInstance(int columnCount, String accessToken) {
        SuppliesFragment fragment = new SuppliesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putString(ARG_ACCESS_TOKEN, accessToken);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            accessToken = getArguments().getString(ARG_ACCESS_TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_supply_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MySupplyRecyclerViewAdapter(new ArrayList<Supply>(), mListener);
            recyclerView.setAdapter(adapter);
        }
        getAllSupplies();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Supply item);
    }


    private void getAllSupplies() {
        final Request.Builder reqBuilder = new Request.Builder()
                .get()
                .url(API_URL + "supplies");
            if (accessToken == null) {
                Toast.makeText(getContext(), "Token not found. Log in first.", Toast.LENGTH_SHORT).show();
            }
            reqBuilder.addHeader("Authorization", "Bearer " + accessToken);

        OkHttpClient client = new OkHttpClient();
        Request request = reqBuilder.build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), "API call success!", Toast.LENGTH_SHORT).show();
                            String jsonData = null;
                            try {
                                jsonData = response.body().string();
                                JSONArray Jarray = new JSONArray(jsonData);

                                for (int i = 0 ; i < Jarray.length(); i++) {

                                    JSONObject obj = Jarray.getJSONObject(i);
                                    Supply s = new Supply();

                                    s.setAuthor(obj.getString("author"));
                                    s.setLat(obj.getInt("lat"));
                                    s.setLng(obj.getInt("lng"));
                                    s.setImage(obj.getString("image"));
                                    adapter.addItem(s);
                                }



                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getContext(), "API call failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
