package com.pond59070017.healthy.Weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pond59070017.healthy.R;

public class WeightFromFragment extends Fragment {

    FirebaseFirestore _firestore;
    FirebaseAuth _auth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _firestore = FirebaseFirestore.getInstance();
        _auth = FirebaseAuth.getInstance();
    }

    void initSaveButton(){
        Button _btn = getView().findViewById(R.id.weight_form_save_btn);
        _btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _date = getView().findViewById(R.id.weight_form_date);
                EditText _weight = getView().findViewById(R.id.weight_form_weight);

                String _dateString = _date.getText().toString();
                String _weightString = _weight.getText().toString();
                String _uid = _auth.getCurrentUser().getUid();

                Weight weight = new Weight(
                        _dateString,
                        Integer.valueOf(_weightString),
                        "up"
                );

                _firestore.collection("myfitness")
                        .document(_uid)
                        .collection("weight")
                        .document(_dateString)
                        .set(_date).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });
    }
}
