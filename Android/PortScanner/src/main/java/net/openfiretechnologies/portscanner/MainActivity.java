package net.openfiretechnologies.portscanner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    //Our Elements
    EditText etHost, etStartPort, etEndPort;
    TextView tvResults;
    Button bStart;
    CheckBox cbScanAll;
    //determine if we scan
    boolean scanning = false;
    //Our Toast for displaying information, yummy
    Toast toast;
    //Our static context
    Context currentInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Save our context statically, just in case, you know..
        currentInstance = this;

        //Initializing the Layout Elements
        etHost = (EditText) findViewById(R.id.etHost);
        etStartPort = (EditText) findViewById(R.id.etStartPort);
        etEndPort = (EditText) findViewById(R.id.etEndPort);

        tvResults = (TextView) findViewById(R.id.tvResults);
        bStart = (Button) findViewById(R.id.bStart);
        cbScanAll = (CheckBox) findViewById(R.id.cbScanAll);

        //Setting a onClickListener on the start button
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!scanning) {
                    startScan();
                }
            }
        });

    }

    private void startScan() {
        tvResults.setText("");
        switch (validateInput()) {
            case 1:
                if (toast != null) toast.cancel();
                toast = Toast.makeText(currentInstance, "Ports are not valid!", Toast.LENGTH_LONG);
                toast.show();
                break;

            case 2:
                if (toast != null) toast.cancel();
                toast = Toast.makeText(currentInstance, "Host is not valid!", Toast.LENGTH_LONG);
                toast.show();
                break;

            case 3:
                if (toast != null) toast.cancel();
                toast = Toast.makeText(currentInstance, "Please fill in all fields!", Toast.LENGTH_LONG);
                toast.show();
                break;

            case 0:
                //All is valid, start the scan!
                //Pass the context, scanAll, start and endPort into Constructor and the URL into the asynctask
                new PortScanner(currentInstance,
                        cbScanAll.isChecked(),
                        (cbScanAll.isChecked() ? 1 : Integer.parseInt(etStartPort.getText().toString())),
                        (cbScanAll.isChecked() ? 1 : Integer.parseInt(etEndPort.getText().toString())))
                        .execute(etHost.getText().toString());
                break;
        }
    }

    private int validateInput() {
        //Ensure fields are not empty.
        //We could use "isEmpty()" but as it requires api 9 and our minimum api is 8, we cant use it
        if (!(etHost.getText().toString().equals(""))) {

            //Host field is not empty, good. now lets check if ALL PORTS is checked
            if (!cbScanAll.isChecked()) {

                //All ports is not checked, so we need to validate the inpur for the ports
                if (!(etStartPort.getText().toString().equals("")) && !(etEndPort.getText().toString().equals(""))) {

                    //a "hacky" way to tertermine, if ports are really integers
                    //Using crashes for our needs, cool eh? :P
                    try {
                        Integer.parseInt(etStartPort.getText().toString());
                        Integer.parseInt(etEndPort.getText().toString());
                    } catch (Exception e) {
                        return 1;
                    }
                } else {
                    //One of the fields is empty!
                    return 3;
                }
            }
        } else {
            return 2;
        }

        //If all is ok, all is valid and we return 0
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void updateResult(String s) {
        scanning = false;
        tvResults.setText(s);
    }
}
