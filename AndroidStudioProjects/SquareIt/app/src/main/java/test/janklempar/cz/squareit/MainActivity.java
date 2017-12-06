package test.janklempar.cz.squareit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Console;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private LinearLayout mLayout;
    private LinearLayout clientsLayout;
    private Button mButton;
    private Button squareItButton;
    private int totalPrice;
    private int price;
    private ArrayList<ClientField> clients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int totalPrice = 0;
        clients = new ArrayList<ClientField>();

        mLayout = (LinearLayout) findViewById(R.id.activity_main);

        mButton = (Button) findViewById(R.id.addClientButton);
        mButton.setOnClickListener(onClick());

        squareItButton = (Button) findViewById(R.id.squareitButton);
        squareItButton.setOnClickListener(onClickSquareIt());

        clientsLayout = (LinearLayout) findViewById(R.id.clientLayout);


    }

    private View.OnClickListener onClickSquareIt(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masterTotal = calculateTotal();
                int size = clients.size();
                int average = 0;
                if(size!=0) {
                    average = masterTotal / size;
                }
                squareItButton.setText("SQUARE IT! TARGET=" + String.valueOf(average));
                //TODO: Seradit clienty podle toho, kolik utratili
                //najit kombinace a zvolit tu s nejmene operacemi


            }
        };
    }


    private View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientsLayout.addView( createNewClientView());
            }
        };
    }

    private LinearLayout createNewClientView() {

        ClientField newField = new ClientField(this);

        clients.add(newField);

        return newField;
    }

    public int calculateTotal () {
        price = 0;

        for(int i = 0; i< this.clients.size(); i++) {
            ClientField currentClient = (ClientField) this.clients.get(i);
            currentClient.recalculateItemPrices();

            price += currentClient.getTotalValueItems();

        }
        return price;
    }
}
