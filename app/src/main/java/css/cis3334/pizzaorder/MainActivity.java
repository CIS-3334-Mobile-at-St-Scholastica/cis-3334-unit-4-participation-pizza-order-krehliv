package css.cis3334.pizzaorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements updateViewInterface {

    RadioButton rbSmall;
    RadioButton rbMedium;
    RadioButton rbLarge;
    Spinner spToppings;
    CheckBox chkbxCheese;
    CheckBox chkbxDelivery;
    TextView txtTotal;
    TextView txtStatus;
    PizzaOrderInterface pizzaOrderSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbSmall = (RadioButton) findViewById(R.id.radioButtonSmall);
        rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        rbLarge = (RadioButton) findViewById(R.id.radioButtonLarge);

        spToppings = (Spinner) findViewById(R.id.spToppings);

        chkbxCheese = (CheckBox) findViewById(R.id.checkBoxCheese);
        chkbxDelivery = (CheckBox) findViewById(R.id.checkBoxDeluvery);

        txtTotal = (TextView) findViewById(R.id.textViewTotal);
        txtStatus = (TextView) findViewById(R.id.textViewStatus);

        pizzaOrderSystem = new PizzaOrder(this);

    }

    @Override
    public void updateView(String orderStatus) {
        txtStatus.setText("Order Status: " + orderStatus);
    }

    public void onClickOrder(View view) {
        pizzaOrderSystem.setDelivery(chkbxDelivery.isChecked());
        String pizzaTopping = "";
        String pizzaSize = "";
        boolean extraCheese = false;

        if (rbLarge.isChecked()) {
            pizzaSize = "Large";
        }
        if (rbMedium.isChecked()) {
            pizzaSize = "Medium";
        }
        if (rbSmall.isChecked()) {
            pizzaSize = "Small";
        }

        pizzaTopping = spToppings.getSelectedItem().toString();

        if (chkbxCheese.isChecked()) {
            extraCheese = true;
        }

        String orderDescription = pizzaOrderSystem.OrderPizza(pizzaTopping, pizzaSize, extraCheese);

        if (chkbxDelivery.isChecked()) {
            orderDescription += " for delivery.";
        } else {
            orderDescription += " for pickup.";
        }

        //display a pop up message for a long period of time
        Toast.makeText(getApplicationContext(), "You have ordered a "+ orderDescription , Toast.LENGTH_LONG).show();
        txtTotal.setText("Total Due: " + pizzaOrderSystem.getTotalBill().toString());
    }


}
