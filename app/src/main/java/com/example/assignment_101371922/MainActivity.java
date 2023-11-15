package com.example.assignment_101371922;

// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextHoursWorked;
    private EditText editTextHourlyRate;
    private TextView textPay;
    private TextView textOvertimePay;
    private TextView textTotalPay;
    private TextView textTax;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {

            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextHoursWorked = findViewById(R.id.editTextHoursWorked);
        editTextHourlyRate = findViewById(R.id.editTextHourlyRate);
        textPay = findViewById(R.id.textPay);
        textOvertimePay = findViewById(R.id.textOvertimePay);
        textTotalPay = findViewById(R.id.textTotalPay);
        textTax = findViewById(R.id.textTax);

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePayAndTax();
            }
        });
    }

    private void calculatePayAndTax() {
        double hoursWorked = Double.parseDouble(editTextHoursWorked.getText().toString());
        double hourlyRate = Double.parseDouble(editTextHourlyRate.getText().toString());

        double pay;
        double overtimePay;
        double totalPay;
        double tax;

        if (hoursWorked <= 40) {
            pay = hoursWorked * hourlyRate;
            overtimePay = 0;
        } else {
            pay = (hoursWorked - 40) * hourlyRate * 1.5 + 40 * hourlyRate;
            overtimePay = (hoursWorked - 40) * hourlyRate * 1.5;
        }

        tax = pay * 0.18;
        totalPay = pay - tax;

   
        textPay.setText("Pay: $" + pay);
        textOvertimePay.setText("Overtime Pay: $" + overtimePay);
        textTotalPay.setText("Total Pay: $" + totalPay);
        textTax.setText("Tax: $" + tax);
    }
}
