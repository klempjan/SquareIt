package test.janklempar.cz.squareit;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.UrlQuerySanitizer;
import android.support.annotation.Size;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

import static android.webkit.WebSettings.*;

/**
 * Created by Jan Klempar on 14.11.2017.
 */

public class ClientField extends LinearLayout{

    private Button addButtonCurrentSection;
    private int clientId;
    private int idCounter = 1000000;

    private int totalValueItems = 0;

    private int itemCount = 0;

    public ClientField(Context context) {
        super(context);

        clientId = ++idCounter;

        final LinearLayout.LayoutParams superParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);

     //   EditText editText = new EditText(this.getContext())


        this.setLayoutParams(superParams);
        this.setOrientation(LinearLayout.VERTICAL);
        this.setBackgroundColor((new Random(255)).nextInt()); // poresit pozadi dle navrhu


      //TextView textView = ;
       // int textView1Id = addTextView(25f,"12",Color.rgb(200,0,0), idCounter++);
       // int textView2Id = addTextView(25f,"13",Color.rgb(0,200,0), idCounter++);
       // int textView3Id = addTextView(25f,"14",Color.rgb(0,0,200), idCounter++);

        int addButtonId = this.addButton("+",25f,30,30,idCounter++);

        addButtonCurrentSection = (Button)findViewById(addButtonId) ;
        addButtonCurrentSection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final int lastIdInputId = addTextView(25f,"",Color.rgb(0,200,200),idCounter++);

               // int lastIdInputId = idCounter-1;
          // editText.requestFocus();
//           InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//           inputMethodManager.toggleSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);

                // custom dialog
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Novy Zaznam <test>");

                // set the custom dialog components - text, image and button
               // TextView text = (TextView) dialog.findViewById(R.id.text);
                //text.setText("Android custom dialog example!");
                ImageView image = (ImageView) dialog.findViewById(R.id.imageView);
                image.setImageResource(R.mipmap.ic_launcher);
                final EditText mainDialogInput = (EditText) dialog.findViewById(R.id.dialogInputEditText);

                final TextView newTextView = (TextView) findViewById(lastIdInputId);
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newText = String.valueOf(mainDialogInput.getText());
                        dialog.dismiss();


                        if(newText.length() > 0) {
                            newTextView.setText(newText);
                        } else {
                            newTextView.setText("0");
                        }

                    }
                });
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        String newText = String.valueOf(mainDialogInput.getText());
                        if(newText.length() > 0) {
                            newTextView.setText(newText);
                        } else {
                            newTextView.setText("0");
                        }
                    }
                });

                dialog.show();
                //newTextView.setText();

            }
        });

    }

    public int addTextView(float size, String text, int barva, int newId){
        TextView textView = new TextView(this.getContext());
        textView.setTextSize(size);
        textView.setText(text);
        textView.setId(newId);
        textView.setBackgroundColor(barva);

        this.addView(textView,this.getChildCount()-1);
        setItemCount(this.getChildCount()-1); //aktualizovat pocet ve sloupcu

        return textView.getId(); // return id
    }

    public void recalculateItemPrices () {
        int localTotal = 0;

        for(int i = 0; i < this.getItemCount(); i++) {
            View childView = this.getChildAt(i);
            if(childView instanceof TextView) {
                localTotal += Integer.parseInt(((TextView) childView).getText().toString());
            }
        }

        this.setTotalValueItems(localTotal);

    }

    public int addButton(String text, float textSize, int pixelWidth, int pixelHeight, int newId){
        Button buttonToAdd = new Button(this.getContext());
        buttonToAdd.setId(newId);
        buttonToAdd.setTextSize(textSize);
        buttonToAdd.setText(text);
        buttonToAdd.setWidth(pixelWidth);
        buttonToAdd.setHeight(pixelHeight);
        //setAddClientEntry(buttonToAdd);

        this.addView(buttonToAdd);
        return buttonToAdd.getId();
        //TODO: Doplnit listener na nove vznikly button
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getTotalValueItems() {
        return totalValueItems;
    }

    public void setTotalValueItems(int totalValueItems) {
        this.totalValueItems = totalValueItems;
    }
}
