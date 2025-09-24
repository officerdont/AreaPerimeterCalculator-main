package com.example.areaperimetercalculator;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //rectangle and square
    //area = length and width
    //perimeter = length and width

    //circle
    //area = radius
    //circumference = radius

    //triangle
    //area = base and height
    //perimeter = side a, side b, and side c
    EditText et_j_length;
    EditText et_j_width;
    ConstraintLayout cons_j_squareRectView;
    Spinner sp_j_shapes;
    TextView tv_j_areaPerimeter;
    ArrayAdapter<String> spinnerAdapter;

    //for circle container
    EditText et_j_radius;
    TextView tv_j_areaPerimeterCircle;
    ConstraintLayout cont_j_circleView;

    // for triangle container
    EditText et_j_SideA;
    EditText et_j_SideB;
    EditText et_j_SideC;
    EditText et_j_Height;
    TextView tv_j_computedtriangle;
    ConstraintLayout cont_j_triangle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //main
        sp_j_shapes = findViewById(R.id.sp_v_shapes);

        //Square and Rectangle
        et_j_length = findViewById(R.id.et_v_length);
        et_j_width  = findViewById(R.id.et_v_width);
        cons_j_squareRectView = findViewById(R.id.cont_v_squareRectangle);
        tv_j_areaPerimeter = findViewById(R.id.tv_v_computedValues);

        //Circle
        et_j_radius = findViewById(R.id.et_v_radius);
        tv_j_areaPerimeterCircle = findViewById(R.id.tv_v_computedAreaPerimeterCircle);
        cont_j_circleView = findViewById(R.id.cont_v_circle);

        //triangle
        et_j_SideA = findViewById(R.id.et_v_SideA);
        et_j_SideB = findViewById(R.id.et_v_SideB);
        et_j_SideC = findViewById(R.id.et_v_SideC);
        et_j_Height = findViewById(R.id.et_v_Height);
        tv_j_computedtriangle = findViewById(R.id.tv_v_computedtriangle);
        cont_j_triangle = findViewById(R.id.cont_v_triangle);


        //Because we are making a simple drop down menu (spinner) that will only contain
        //strings as options.  We can use a string array with the built-in array adapter
        //to populate the spinner.

        //we will use this to populate our spinner
        String[] shapes = new String[]{"Square", "Rectangle", "Circle", "Triangle"};

        //adapter is what links the java code with the xml for the spinner
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,shapes);

        sp_j_shapes.setAdapter(spinnerAdapter);

        setupSpinnerChangeListener();
        textChangeListenerSquareRect();
        textChangeListenerRadius();
        textChangeListenerTriangleSides();
    }

    public void setupSpinnerChangeListener()
    {
        sp_j_shapes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //0-3 indexes
                if(position == 0)
                {
                    //square
                    cons_j_squareRectView.setVisibility(View.VISIBLE);
                    //no showing for circle and triangle
                    hideConstraintView(cont_j_circleView);
                }
                else if(position == 1)
                {
                    //rectangle
                    cons_j_squareRectView.setVisibility(View.VISIBLE);

                    //no showing for circle and triangle
                    hideConstraintView(cont_j_circleView);
                }
                else if(position == 2)
                {
                    //Circle
                    cont_j_circleView.setVisibility(View.VISIBLE);
                    //no showing for rectangle and triangle

                    hideConstraintView(cons_j_squareRectView);
                    hideConstraintView(cont_j_triangle);
                }
                else if (position == 3)
                {
                    //Triangle
                    cont_j_triangle.setVisibility(View.VISIBLE);

                    //no showing for circle and rectangle
                    hideConstraintView(cons_j_squareRectView);
                    hideConstraintView(cont_j_circleView);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void hideConstraintView(ConstraintLayout cl)
    {
        cl.setVisibility(View.INVISIBLE);
    }

    public void textChangeListenerSquareRect()
    {
        et_j_width.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setAreaAndPerimeterSquareRect(et_j_length.getText().toString(),et_j_width.getText().toString());
            }
        });

        et_j_length.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setAreaAndPerimeterSquareRect(et_j_length.getText().toString(),et_j_width.getText().toString());
            }
        });
    }

    public void textChangeListenerRadius()
    {
        et_j_radius.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                setAreaPerimeterCircle(et_j_radius.getText().toString());

            }
        });
    }
    public void textChangeListenerTriangleSides()
    {
        et_j_SideA.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s)
            {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //setAreaAndPerimeterSquareRect(et_j_length.getText().toString(),et_j_width.getText().toString());
                setTriangleSides(et_j_SideA.getText().toString(),et_j_SideB.getText().toString(),et_j_SideC.getText().toString(),et_j_Height.getText().toString());
            }
        });
        et_j_SideB.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setTriangleSides(et_j_SideA.getText().toString(),et_j_SideB.getText().toString(),et_j_SideC.getText().toString(),et_j_Height.getText().toString());
            }
        });
        et_j_SideC.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setTriangleSides(et_j_SideA.getText().toString(),et_j_SideB.getText().toString(),et_j_SideC.getText().toString(),et_j_Height.getText().toString());
            }
        });
        et_j_Height.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setTriangleSides(et_j_SideA.getText().toString(),et_j_SideB.getText().toString(),et_j_SideC.getText().toString(),et_j_Height.getText().toString());
            }
        });
    }

    public void setAreaAndPerimeterSquareRect(String lengthS, String widthS)
    {

        if(lengthS.isEmpty() || widthS.isEmpty())
        {
            return;
        }
        //convert the string to an integer so we can do math.
        double length = Double.parseDouble(lengthS);
        double width = Double.parseDouble(widthS);

        double area = length * width;

        double perimeter = length + length + width + width;

        tv_j_areaPerimeter.setText("area = " + area + "\nPerimeter = " + perimeter);
    }

    public void setAreaPerimeterCircle(String radiusS)
    {
        if(radiusS.isEmpty())
        {
            return;
        }

        double radius = Double.parseDouble(radiusS);
        double pi = Math.PI;

        double area = pi * Math.pow(radius, 2);

        double perimeter = 2 * pi * radius;

        tv_j_areaPerimeterCircle.setText("area = " + area + "\nPerimeter = " + perimeter);
    }
    public void setTriangleSides(String SideA,String SideB,String SideC,String Height)
    {

        if(SideA.isEmpty() || SideB.isEmpty() || SideC.isEmpty() || Height.isEmpty())
        {

            return;
        }
        double Sidea = Double.parseDouble(SideA);
        double Sideb = Double.parseDouble(SideB);
        double Sidec = Double.parseDouble(SideC);
        double height =  Double.parseDouble(Height);

        double perimeter = Sidea + Sideb + Sidec;
        double area = .5 * Sidec * height;

       tv_j_computedtriangle.setText("area = " + area + "\nPerimeter = " + perimeter);

    }
}