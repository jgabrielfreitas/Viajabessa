package br.com.mobi.viajabessa.itens;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by JGabrielFreitas on 26/02/2015 - 14:55.
 */
public class CreditCardFormat implements TextWatcher{

    String a;
    int keyDel;
    public EditText text;

    public void onTextChanged(CharSequence s, int start, int before, int count) {

        boolean flag = true;
        String eachBlock[] = text.getText().toString().split(" ");
        for (int i = 0; i < eachBlock.length; i++) {
            if (eachBlock[i].length() > 4) {
                flag = false;
            }
        }
        if (flag) {

            text.setOnKeyListener(new View.OnKeyListener() {

                public boolean onKey(View v, int keyCode, KeyEvent event) {

                    if (keyCode == KeyEvent.KEYCODE_DEL)
                        keyDel = 1;
                    return false;
                }
            });

            if (keyDel == 0) {

                if (((text.getText().length() + 1) % 5) == 0) {

                    if (text.getText().toString().split(" ").length <= 3) {
                        text.setText(text.getText() + " ");
                        text.setSelection(text.getText().length());
                    }
                }
                a = text.getText().toString();
            } else {
                a = text.getText().toString();
                keyDel = 0;
            }

        } else {
            text.setText(a);
        }

    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    public void afterTextChanged(Editable s) {}
}
