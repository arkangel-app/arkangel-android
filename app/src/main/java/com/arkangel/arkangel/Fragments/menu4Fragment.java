package com.arkangel.arkangel.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkangel.arkangel.Clases.GetTest;
import com.arkangel.arkangel.Clases.Question;
import com.arkangel.arkangel.Clases.TestQuestion;
import com.arkangel.arkangel.R;

import androidx.fragment.app.Fragment;


public class menu4Fragment extends Fragment {

    private TextView seleccionado;

    public menu4Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.fragment_menu, container, false);
        GetTest getTest= new GetTest();
        Question question= new Question();
        question= getTest.getQuestion(4);

        TextView numPreguntaText = myview.findViewById(R.id.numPreguntaText);
        numPreguntaText.setText(question.getId_question());

        TextView pregunta = myview.findViewById(R.id.pregunta);
        pregunta.setText(question.getQuestion());

        ImageView desliza = myview.findViewById(R.id.desliza);
        desliza.setVisibility(View.GONE);

        seleccionado = myview.findViewById(R.id.seleccionado);

        ImageView cara1 = myview.findViewById(R.id.cara1);
        ImageView cara2 = myview.findViewById(R.id.cara2);
        ImageView cara3 = myview.findViewById(R.id.cara3);
        ImageView cara4 = myview.findViewById(R.id.cara4);
        ImageView cara5 = myview.findViewById(R.id.cara5);
        final TestQuestion tq=TestQuestion.getInstance();
        cara1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tq.r4="5";
                seleccionado.setText("Totalmente de acuerdo");
            }
        });
        cara2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tq.r4="4";
                seleccionado.setText("Algo de acuerdo");
            }
        });
        cara3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tq.r4="3";
                seleccionado.setText("Ni de acuerdo ni en desacuerdo");
            }
        });
        cara4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tq.r4="2";
                seleccionado.setText("Algo en desacuerdo");
            }
        });
        cara5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tq.r4="1";
                seleccionado.setText("Totalmente en desacuerdo");
            }
        });

        // Inflate the layout for this fragment
        return myview;
    }

}
