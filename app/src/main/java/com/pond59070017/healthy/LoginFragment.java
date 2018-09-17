package com.pond59070017.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment  extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLoginBrn();
        initRegister();
    }

    void initRegister(){
        TextView _regis = (TextView) getView().findViewById(R.id.login_register);
        _regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
                Log.d("System", "Go to register");
            }
        });

    }

    void initLoginBrn(){
        Button _loginBtn = (Button) getView().findViewById(R.id.login_loginBtn);
        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _username = (EditText) getView().findViewById(R.id.login_user);
                EditText _passswod = (EditText) getView().findViewById(R.id.login_password);
                String _usernameStr = _username.getText().toString();
                String _passwordStr = _passswod.getText().toString();
                if (_usernameStr.isEmpty() && _passwordStr.isEmpty()){
                    Toast.makeText(getActivity(),"Please enter your username and password",Toast.LENGTH_SHORT).show();
                    Log.d("System", "[LoginFragment] user or password is empty");
                }else if (_usernameStr.toLowerCase().equals("admin")){
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new MenuFragment())
                            .addToBackStack(null)
                            .commit();
                    Log.d("System", "[LoginFragment] equals an admin");
                }else{
                    Toast.makeText(getActivity(),"Invalid your username or password\nPlease try again",Toast.LENGTH_SHORT).show();
                    Log.d("System", "[LoginFragment] Invalid username or password");
                }
            }
        });
    }
}
