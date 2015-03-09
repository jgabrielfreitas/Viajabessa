package br.com.mobi.viajabessa.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.mobi.viajabessa.R;
import br.com.mobi.viajabessa.itens.CreditCardFormat;
import br.com.mobi.viajabessa.itens.DateMaskFormat;

public class PurchaseConfirmActivity extends ActionBarActivity implements View.OnClickListener {

    EditText creditCardEditText;
    EditText dateEditText;
    Bundle mBundle;
    TextView purchaseValueTextView;
    TextView textView2;
    Button buyFinishPurchaseButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_confirm);

        mBundle = getIntent().getExtras();

        instanceViews();
    }

    private void instanceViews() {

        creditCardEditText = (EditText) findViewById(R.id.creditCardEditText);
        dateEditText       = (EditText) findViewById(R.id.dateEditText);
        textView2          = (TextView) findViewById(R.id.textView2);
        purchaseValueTextView   = (TextView) findViewById(R.id.purchaseValueTextView);
        buyFinishPurchaseButton = (Button) findViewById(R.id.buyFinishPurchaseButton);

        // put mask in creditcard edittext
        CreditCardFormat creditMask = new CreditCardFormat();
        creditMask.text = creditCardEditText;
        creditCardEditText.addTextChangedListener(creditMask);

        // put mask in date edittext
        TextWatcher dateMask = DateMaskFormat.insert("##/##", dateEditText);
        dateEditText.addTextChangedListener(dateMask);

        textView2.setTypeface(null, Typeface.BOLD);
        purchaseValueTextView.setTypeface(null, Typeface.BOLD);
        buyFinishPurchaseButton.setTypeface(null, Typeface.BOLD);

        purchaseValueTextView.setText(mBundle.getString("value"));

        buyFinishPurchaseButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        Toast.makeText(this, "Pagamento realizado com sucesso! Obrigado por usar a Viajabessa.\nBoa viagem!", Toast.LENGTH_LONG).show();
        finish();
    }
}
