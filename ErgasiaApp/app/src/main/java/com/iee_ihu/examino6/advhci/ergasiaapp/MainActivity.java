package com.iee_ihu.examino6.advhci.ergasiaapp;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    public static ergasiaDatabase ergDB;
    private AppBarConfiguration mAppBarConfiguration;
    FragmentTransaction fragmentTransaction;
    public static FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.arxiki, R.id.erotimata, R.id.eisagogi, R.id.tropopoiisi, R.id.diagrafi, R.id.eisagogi2, R.id.tropopoiisi2, R.id.diagrafi2)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        ergDB = Room.databaseBuilder(getApplicationContext(), ergasiaDatabase.class, "Ergasiadb").allowMainThreadQueries().build();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }

    public void onRadioButtonClickedE(View view) {
        TextView titleAthlitis = findViewById(R.id.titleAthlitis);
        TextView titleOmada = findViewById(R.id.titleOmada);
        TextInputLayout onomaOmadas = findViewById(R.id.onomaOmadasT);
        TextInputLayout onomaGipedou = findViewById(R.id.onomaGipedouT);
        TextInputLayout poliOmadas = findViewById(R.id.poliOmadasT);
        TextInputLayout xoraOmadas = findViewById(R.id.xoraOmadasT);
        TextInputLayout etosIdrisis = findViewById(R.id.etosIdrisisT);
        TextInputLayout onomaAthliti = findViewById(R.id.onomaAthlitiT);
        TextInputLayout eponimoAthliti = findViewById(R.id.eponimoAT);
        TextInputLayout poliAthliti = findViewById(R.id.poliT);
        TextInputLayout xoraAthliti = findViewById(R.id.xoraAT);
        TextInputLayout etosGennisis = findViewById(R.id.etosAT);

        titleAthlitis.setVisibility(view.INVISIBLE);
        titleOmada.setVisibility(view.INVISIBLE);

        onomaOmadas.setVisibility(view.INVISIBLE);
        onomaGipedou.setVisibility(view.INVISIBLE);
        poliOmadas.setVisibility(view.INVISIBLE);
        xoraOmadas.setVisibility(view.INVISIBLE);
        etosIdrisis.setVisibility(view.INVISIBLE);

        onomaAthliti.setVisibility(view.INVISIBLE);
        eponimoAthliti.setVisibility(view.INVISIBLE);
        poliAthliti.setVisibility(view.INVISIBLE);
        xoraAthliti.setVisibility(view.INVISIBLE);
        etosGennisis.setVisibility(view.INVISIBLE);
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.omadiko:
                if (checked)
                    titleOmada.setVisibility(view.VISIBLE);

                onomaOmadas.setVisibility(view.VISIBLE);
                onomaGipedou.setVisibility(view.VISIBLE);
                poliOmadas.setVisibility(view.VISIBLE);
                xoraOmadas.setVisibility(view.VISIBLE);
                etosIdrisis.setVisibility(view.VISIBLE);
                break;
            case R.id.atomiko:
                if (checked)
                    titleAthlitis.setVisibility(view.VISIBLE);

                onomaAthliti.setVisibility(view.VISIBLE);
                eponimoAthliti.setVisibility(view.VISIBLE);
                poliAthliti.setVisibility(view.VISIBLE);
                xoraAthliti.setVisibility(view.VISIBLE);
                etosGennisis.setVisibility(view.VISIBLE);
                break;
        }
    }

    public void eisagogiOnClick(View view) {

        EditText onomaOmadas = findViewById(R.id.onomaOmadas);
        EditText onomaGipedou = findViewById(R.id.onomaGipedou);
        EditText poliOmadas = findViewById(R.id.poliOmadas);
        EditText xoraOmadas = findViewById(R.id.xoraOmadas);
        EditText etosIdrisis = findViewById(R.id.etosIdrisis);
        EditText onomaAthliti = findViewById(R.id.onomaAthliti);
        EditText eponimoAthliti = findViewById(R.id.eponimoA);
        EditText poliAthliti = findViewById(R.id.poli);
        EditText xoraAthliti = findViewById(R.id.xoraA);
        EditText etosGennisis = findViewById(R.id.etosA);
        EditText kodikosAthlimatos = findViewById(R.id.kodikosA);
        EditText onomaAthlimatos = findViewById(R.id.onomaA);
        RadioGroup radioGroupF = findViewById(R.id.radioGroupF);
        RadioGroup radioGroupE = findViewById(R.id.radioGroupE);

        TextInputLayout onomaOmadasT = findViewById(R.id.onomaOmadasT);
        TextInputLayout onomaGipedouT = findViewById(R.id.onomaGipedouT);
        TextInputLayout poliOmadasT = findViewById(R.id.poliOmadasT);
        TextInputLayout xoraOmadasT = findViewById(R.id.xoraOmadasT);
        TextInputLayout etosIdrisisT = findViewById(R.id.etosIdrisisT);
        TextInputLayout onomaAthlitiT = findViewById(R.id.onomaAthlitiT);
        TextInputLayout eponimoAthlitiT = findViewById(R.id.eponimoAT);
        TextInputLayout poliAthlitiT = findViewById(R.id.poliT);
        TextInputLayout xoraAthlitiT = findViewById(R.id.xoraAT);
        TextInputLayout etosGennisisT = findViewById(R.id.etosAT);
        TextView titleAthlitis = findViewById(R.id.titleAthlitis);
        TextView titleOmada = findViewById(R.id.titleOmada);

        int kodikosAthlimatosInt;
        if (kodikosAthlimatos.getText().toString().isEmpty())
            kodikosAthlimatosInt = 0;
        else {
            kodikosAthlimatosInt = parseInt(kodikosAthlimatos.getText().toString());
            if (kodikosAthlimatosInt < 1) {
                dialogkodikos();
                return;
            }
        }
        List<Integer> listIdAthlimatos = ergDB.mydaotemp().idAthlimatosQuery();


        for (int i = 0; i < listIdAthlimatos.size(); i++) {
            if (listIdAthlimatos.get(i) == kodikosAthlimatosInt) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    kodikosAthlimatos.setText("");
                    dialogSfalmaId();
                    return;
                }
            }
        }

        String onomaAthlimatosSt = onomaAthlimatos.getText().toString();

        String eidos = "";
        switch (radioGroupE.getCheckedRadioButtonId()) {
            case R.id.omadiko:
                eidos = "Ομαδικό";
                break;
            case R.id.atomiko:
                eidos = "Ατομικό";
                break;
        }
        String fyllo = "";
        switch (radioGroupF.getCheckedRadioButtonId()) {
            case R.id.andriko:
                fyllo = "Ανδρικό";
                break;
            case R.id.gynaikeio:
                fyllo = "Γυναικείο";
                break;
        }

        if (kodikosAthlimatosInt == 0 || onomaAthlimatosSt.isEmpty() || eidos.equals("") || fyllo.equals("")) {
            dialogAdeiaPedia();
            return;
        }

        if (eidos.equals("Ομαδικό")) {

            String onomaOmadasSt = onomaOmadas.getText().toString();
            String onomaGipedouSt = onomaGipedou.getText().toString();
            String poliOmadasSt = poliOmadas.getText().toString();
            String xoraOmadasSt = xoraOmadas.getText().toString();
            int etosOmadasInt;
            if (etosIdrisis.getText().toString().isEmpty())
                etosOmadasInt = 0;
            else
                etosOmadasInt = parseInt(etosIdrisis.getText().toString());

            if (onomaOmadasSt.isEmpty() || onomaGipedouSt.isEmpty() || poliOmadasSt.isEmpty() || xoraOmadasSt.isEmpty() || etosOmadasInt == 0) {
                dialogAdeiaPedia();
                return;
            }

            try {
                Athlima athlima = new Athlima();
                athlima.setIdAthlimatos(kodikosAthlimatosInt);
                athlima.setOnomaAthlimatos(onomaAthlimatosSt);
                athlima.setEidosAthlimatos(eidos);
                athlima.setFylloAthlimatos(fyllo);
                ergDB.mydaotemp().addAthlima(athlima);
                Omada omada = new Omada();
                omada.setOnomaOmadas(onomaOmadasSt);
                omada.setOnomaGipedou(onomaGipedouSt);
                omada.setPoliOmadas(poliOmadasSt);
                omada.setXoraOmadas(xoraOmadasSt);
                omada.setEtosOmadas(etosOmadasInt);
                omada.setIdAthlimatosF2(kodikosAthlimatosInt);
                ergDB.mydaotemp().addOmada(omada);
            } catch (Exception e) {
                dialogSfalma();
                return;
            }

        } else if (eidos.equals("Ατομικό")) {
            String onomaAthlitiSt = onomaAthliti.getText().toString();
            String eponimoAthlitiSt = eponimoAthliti.getText().toString();
            String poliAthlitiSt = poliAthliti.getText().toString();
            String xoraAthlitiSt = xoraAthliti.getText().toString();
            int etosAthlitiInt;
            if (etosGennisis.getText().toString().isEmpty())
                etosAthlitiInt = 0;
            else
                etosAthlitiInt = parseInt(etosGennisis.getText().toString());

            if (onomaAthlitiSt.isEmpty() || eponimoAthlitiSt.isEmpty() || poliAthlitiSt.isEmpty() || xoraAthlitiSt.isEmpty() || etosAthlitiInt == 0) {
                dialogAdeiaPedia();
                return;
            }

            try {
                Athlima athlima = new Athlima();
                athlima.setIdAthlimatos(kodikosAthlimatosInt);
                athlima.setOnomaAthlimatos(onomaAthlimatosSt);
                athlima.setEidosAthlimatos(eidos);
                athlima.setFylloAthlimatos(fyllo);
                ergDB.mydaotemp().addAthlima(athlima);
                Athlitis athlitis = new Athlitis();
                athlitis.setOnomaAthliti(onomaAthlitiSt);
                athlitis.setEponimoAthliti(eponimoAthlitiSt);
                athlitis.setPoliAthliti(poliAthlitiSt);
                athlitis.setXoraAthliti(xoraAthlitiSt);
                athlitis.setEtosAthliti(etosAthlitiInt);
                athlitis.setIdAthlimatosF(kodikosAthlimatosInt);
                ergDB.mydaotemp().addAthlitis(athlitis);
            } catch (Exception e) {
                dialogSfalma();
                return;
            }
        }

        dialogEisagogi();


        onomaOmadas.setText("");
        onomaGipedou.setText("");
        poliOmadas.setText("");
        xoraOmadas.setText("");
        etosIdrisis.setText("");
        onomaAthliti.setText("");
        eponimoAthliti.setText("");
        poliAthliti.setText("");
        xoraAthliti.setText("");
        etosGennisis.setText("");
        kodikosAthlimatos.setText("");
        onomaAthlimatos.setText("");
        radioGroupF.clearCheck();
        radioGroupE.clearCheck();
        titleAthlitis.setVisibility(view.INVISIBLE);
        titleOmada.setVisibility(view.INVISIBLE);
        onomaOmadasT.setVisibility(view.INVISIBLE);
        onomaGipedouT.setVisibility(view.INVISIBLE);
        poliOmadasT.setVisibility(view.INVISIBLE);
        xoraOmadasT.setVisibility(view.INVISIBLE);
        etosIdrisisT.setVisibility(view.INVISIBLE);
        onomaAthlitiT.setVisibility(view.INVISIBLE);
        eponimoAthlitiT.setVisibility(view.INVISIBLE);
        poliAthlitiT.setVisibility(view.INVISIBLE);
        xoraAthlitiT.setVisibility(view.INVISIBLE);
        etosGennisisT.setVisibility(view.INVISIBLE);
    }

    public void dialogkodikos() {
        AlertDialog alertDialogSf = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialogSf.setIcon(R.drawable.baseline_info_24);
        // Setting Dialog Title
        alertDialogSf.setTitle("Ειδοποίηση");

        // Setting Dialog Message
        alertDialogSf.setMessage("Εισάγετε κωδικό μεγαλύτερο του 0");

        // Setting OK Button
        alertDialogSf.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed

            }
        });

        // Showing Alert Message
        alertDialogSf.show();
    }

    public void dialogAdeiaPedia() {
        AlertDialog alertDialogSf = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialogSf.setIcon(R.drawable.baseline_info_24);
        // Setting Dialog Title
        alertDialogSf.setTitle("Ειδοποίηση");

        // Setting Dialog Message
        alertDialogSf.setMessage("Συμπληρώστε όλα τα πεδία");

        // Setting OK Button
        alertDialogSf.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed

            }
        });

        // Showing Alert Message
        alertDialogSf.show();
    }

    public void dialogSfalmaId() {
        AlertDialog alertDialogSf = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialogSf.setIcon(R.drawable.baseline_info_24);
        // Setting Dialog Title
        alertDialogSf.setTitle("Ειδοποίηση");

        // Setting Dialog Message
        alertDialogSf.setMessage("Ο κωδικός υπάρχει ήδη");

        // Setting OK Button
        alertDialogSf.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed

            }
        });

        // Showing Alert Message
        alertDialogSf.show();
    }

    boolean bGlobal;
    public void anazitisiT(View view) {

        EditText athlimaid = findViewById(R.id.athlimaid);
        TextView titleOmadaTR = findViewById(R.id.titleOmadaTR);
        TextInputLayout onomaOmadasTTR = findViewById(R.id.onomaOmadasTTR);
        TextInputLayout onomaGipedouTTR = findViewById(R.id.onomaGipedouTTR);
        TextInputLayout poliOmadasTTR = findViewById(R.id.poliOmadasTTR);
        TextInputLayout xoraOmadasTTR = findViewById(R.id.xoraOmadasTTR);
        TextInputLayout etosIdrisisTTR = findViewById(R.id.etosIdrisisTTR);
        TextView titleAthlitisTR = findViewById(R.id.titleAthlitisTR);
        TextInputLayout onomaAthlitiTTR = findViewById(R.id.onomaAthlitiTTR);
        TextInputLayout eponimoATTR = findViewById(R.id.eponimoATTR);
        TextInputLayout poliTTR = findViewById(R.id.poliTTR);
        TextInputLayout xoraATTR = findViewById(R.id.xoraATTR);
        TextInputLayout etosATTR = findViewById(R.id.etosATTR);
        Button buttonTropopoiisi = findViewById(R.id.buttonTropopoiisi);
        titleOmadaTR.setVisibility(view.INVISIBLE);
        onomaOmadasTTR.setVisibility(view.INVISIBLE);
        onomaGipedouTTR.setVisibility(view.INVISIBLE);
        poliOmadasTTR.setVisibility(view.INVISIBLE);
        xoraOmadasTTR.setVisibility(view.INVISIBLE);
        etosIdrisisTTR.setVisibility(view.INVISIBLE);
        titleAthlitisTR.setVisibility(view.INVISIBLE);
        onomaAthlitiTTR.setVisibility(view.INVISIBLE);
        eponimoATTR.setVisibility(view.INVISIBLE);
        poliTTR.setVisibility(view.INVISIBLE);
        xoraATTR.setVisibility(view.INVISIBLE);
        etosATTR.setVisibility(view.INVISIBLE);
        buttonTropopoiisi.setVisibility(view.INVISIBLE);

        int d;
        if (athlimaid.getText().toString().isEmpty()) {
            dialogAdeiaPedia();
            return;
        } else {
            d = Integer.parseInt(athlimaid.getText().toString());
            if (d < 1) {
                dialogkodikos();
                return;
            }
        }
        List<Integer> list = ergDB.mydaotemp().idAthlimatosQuery();
        boolean b = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == d) {
                b = true;
            }
        }
        if (b) {
            List<Integer> listOmadikou = ergDB.mydaotemp().idOmadikouAthlimatosQuery();
            boolean b2 = false;
            for (int i = 0; i < listOmadikou.size(); i++) {
                if (listOmadikou.get(i) == d) {
                    b2 = true;
                }
            }
            if (b2) {

                titleOmadaTR.setVisibility(view.VISIBLE);
                onomaOmadasTTR.setVisibility(view.VISIBLE);
                onomaGipedouTTR.setVisibility(view.VISIBLE);
                poliOmadasTTR.setVisibility(view.VISIBLE);
                xoraOmadasTTR.setVisibility(view.VISIBLE);
                etosIdrisisTTR.setVisibility(view.VISIBLE);
                buttonTropopoiisi.setVisibility(view.VISIBLE);
                bGlobal = true;
            } else {
                titleAthlitisTR.setVisibility(view.VISIBLE);
                onomaAthlitiTTR.setVisibility(view.VISIBLE);
                eponimoATTR.setVisibility(view.VISIBLE);
                poliTTR.setVisibility(view.VISIBLE);
                xoraATTR.setVisibility(view.VISIBLE);
                etosATTR.setVisibility(view.VISIBLE);
                buttonTropopoiisi.setVisibility(view.VISIBLE);
                bGlobal = false;
            }
        } else {
            dialogDenYparxei();
            return;
        }
    }


    public  void tropopoiisiOnClick(View view){


        EditText athlimaid = findViewById(R.id.athlimaid);
        TextView titleOmadaTR = findViewById(R.id.titleOmadaTR);
        TextInputLayout onomaOmadasTTR = findViewById(R.id.onomaOmadasTTR);
        EditText onomaOmadasTR = findViewById(R.id.onomaOmadasTR);
        TextInputLayout onomaGipedouTTR = findViewById(R.id.onomaGipedouTTR);
        EditText onomaGipedouTR = findViewById(R.id.onomaGipedouTR);
        TextInputLayout poliOmadasTTR = findViewById(R.id.poliOmadasTTR);
        EditText poliOmadasTR = findViewById(R.id.poliOmadasTR);
        TextInputLayout xoraOmadasTTR = findViewById(R.id.xoraOmadasTTR);
        EditText xoraOmadasTR = findViewById(R.id.xoraOmadasTR);
        TextInputLayout etosIdrisisTTR = findViewById(R.id.etosIdrisisTTR);
        EditText etosIdrisisTR = findViewById(R.id.etosIdrisisTR);
        TextView titleAthlitisTR = findViewById(R.id.titleAthlitisTR);
        TextInputLayout onomaAthlitiTTR = findViewById(R.id.onomaAthlitiTTR);
        EditText onomaAthlitiTR = findViewById(R.id.onomaAthlitiTR);
        TextInputLayout eponimoATTR = findViewById(R.id.eponimoATTR);
        EditText eponimoATR = findViewById(R.id.eponimoATR);
        TextInputLayout poliTTR = findViewById(R.id.poliTTR);
        EditText poliTR = findViewById(R.id.poliTR);
        TextInputLayout xoraATTR = findViewById(R.id.xoraATTR);
        EditText xoraATR = findViewById(R.id.xoraATR);
        TextInputLayout etosATTR = findViewById(R.id.etosATTR);
        EditText etosATR = findViewById(R.id.etosATR);


        int d;
        d = Integer.parseInt(athlimaid.getText().toString());
        if(bGlobal){
            String onomaOmadasTRSt = onomaOmadasTR.getText().toString();
            String onomaGipedouTRSt = onomaGipedouTR.getText().toString();
            String poliOmadasTRSt = poliOmadasTR.getText().toString();
            String xoraOmadasTRSt = xoraOmadasTR.getText().toString();
            int etosIdrisisTRInt;
            if(etosIdrisisTR.getText().toString().isEmpty()){
                etosIdrisisTRInt = 0;
            }
            else {
                etosIdrisisTRInt = Integer.parseInt(etosIdrisisTR.getText().toString());
            }
            if(onomaOmadasTRSt.isEmpty() || onomaGipedouTRSt.isEmpty() || poliOmadasTRSt.isEmpty() || xoraOmadasTRSt.isEmpty() || etosIdrisisTRInt == 0){
                dialogAdeiaPedia();
                return;
            }

            try{
                Omada omada = new Omada();
                omada.setOnomaOmadas(onomaOmadasTRSt);
                omada.setOnomaGipedou(onomaGipedouTRSt);
                omada.setPoliOmadas(poliOmadasTRSt);
                omada.setXoraOmadas(xoraOmadasTRSt);
                omada.setEtosOmadas(etosIdrisisTRInt);
                omada.setIdAthlimatosF2(d);
                ergDB.mydaotemp().updateOmada(omada);

            }catch (Exception e){
                dialogSfalma();
                return;
            }

        }
        else{

            String onomaAthlitiTRSt = onomaAthlitiTR.getText().toString();
            String eponimoATRSt = eponimoATR.getText().toString();
            String poliTRSt = poliTR.getText().toString();
            String xoraATRSt = xoraATR.getText().toString();
            int etosATRInt;
            if(etosATR.getText().toString().isEmpty()){
                etosATRInt = 0;
            }
            else {
                etosATRInt = Integer.parseInt(etosATR.getText().toString());
            }

            if(onomaAthlitiTRSt.isEmpty() || eponimoATRSt.isEmpty() || poliTRSt.isEmpty() || xoraATRSt.isEmpty() || etosATRInt == 0){
                dialogAdeiaPedia();
                return;
            }


            try{
                Athlitis athlitis = new Athlitis();
                athlitis.setOnomaAthliti(onomaAthlitiTRSt);
                athlitis.setEponimoAthliti(eponimoATRSt);
                athlitis.setPoliAthliti(poliTRSt);
                athlitis.setXoraAthliti(xoraATRSt);
                athlitis.setEtosAthliti(etosATRInt);
                athlitis.setIdAthlimatosF(d);
                ergDB.mydaotemp().updateAthlitis(athlitis);

            }catch (Exception e){
                dialogSfalma();
                return;
            }
        }


        dialogTropopoiisi();

        titleOmadaTR.setVisibility(view.INVISIBLE);
        onomaOmadasTTR.setVisibility(view.INVISIBLE);
        onomaOmadasTR.setText("");
        onomaGipedouTTR.setVisibility(view.INVISIBLE);
        onomaGipedouTR.setText("");
        poliOmadasTTR.setVisibility(view.INVISIBLE);
        poliOmadasTR.setText("");
        xoraOmadasTTR.setVisibility(view.INVISIBLE);
        xoraOmadasTR.setText("");
        etosIdrisisTTR.setVisibility(view.INVISIBLE);
        etosIdrisisTR.setText("");
        titleAthlitisTR.setVisibility(view.INVISIBLE);
        onomaAthlitiTTR.setVisibility(view.INVISIBLE);
        onomaAthlitiTR.setText("");
        eponimoATTR.setVisibility(view.INVISIBLE);
        eponimoATR.setText("");
        poliTTR.setVisibility(view.INVISIBLE);
        poliTR.setText("");
        xoraATTR.setVisibility(view.INVISIBLE);
        xoraATR.setText("");
        etosATTR.setVisibility(view.INVISIBLE);
        etosATR.setText("");
        athlimaid.setText("");
        Button button =findViewById(R.id.buttonTropopoiisi);
        button.setVisibility(view.INVISIBLE);
    }



    public void dialogSfalma(){
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialog1.setIcon(R.drawable.baseline_info_24);
        // Setting Dialog Title
        alertDialog1.setTitle("Ειδοποίηση");

        // Setting Dialog Message
        alertDialog1.setMessage("Προέκυψε σφάλμα, προσπαθήστε ξανά!");

        // Setting OK Button
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed

            }
        });

        // Showing Alert Message
        alertDialog1.show();
    }

    public  void  diagrafiOnClick(View view){
        EditText Athlima = findViewById(R.id.Athlima);
        int d;
        if(Athlima.getText().toString().isEmpty()){
            dialogAdeiaPedia();
            return;
        }
        else{
            d = Integer.parseInt(Athlima.getText().toString());
            if(d < 1){
                dialogkodikos();
                return;
            }
        }
        List<Integer> list = ergDB.mydaotemp().idAthlimatosQuery();
        boolean b = false;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) == d){
                b = true;
            }
        }
        if(b){
            try{
                Athlima athlima = new Athlima();
                athlima.setIdAthlimatos(d);

                ergDB.mydaotemp().deleteAthlima(athlima);
            }catch (Exception e){
                dialogSfalma();
                return;
            }
        }
        else{
            dialogDenYparxei();
            return;
        }


        dialogDiagrafi();

        Athlima.setText("");
    }

    public void dialogDenYparxei(){
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialog1.setIcon(R.drawable.baseline_info_24);
        // Setting Dialog Title
        alertDialog1.setTitle("Ειδοποίηση");

        // Setting Dialog Message
        alertDialog1.setMessage("Ο κωδικός δεν υπάρχει");

        // Setting OK Button
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed

            }
        });

        // Showing Alert Message
        alertDialog1.show();
    }

    public void dialogEisagogi() {
        // Creating alert Dialog with one Button

        AlertDialog alertDialog1 = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialog1.setIcon(R.drawable.baseline_info_24);
        // Setting Dialog Title
        alertDialog1.setTitle("Ειδοποίηση");

        // Setting Dialog Message
        alertDialog1.setMessage("Επιτυχής Εισαγωγή");

        // Setting OK Button
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed

            }
        });

        // Showing Alert Message
        alertDialog1.show();
    }

    public void dialogTropopoiisi() {
        // Creating alert Dialog with one Button

        AlertDialog alertDialog1 = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialog1.setIcon(R.drawable.baseline_info_24);
        // Setting Dialog Title
        alertDialog1.setTitle("Ειδοποίηση");

        // Setting Dialog Message
        alertDialog1.setMessage("Επιτυχής Τροποποίηση");

        // Setting OK Button
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed

            }
        });

        // Showing Alert Message
        alertDialog1.show();
    }

    public void dialogDiagrafi() {
        // Creating alert Dialog with one Button

        AlertDialog alertDialog1 = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialog1.setIcon(R.drawable.baseline_info_24);
        // Setting Dialog Title
        alertDialog1.setTitle("Ειδοποίηση");

        // Setting Dialog Message
        alertDialog1.setMessage("Επιτυχής Διαγραφή");

        // Setting OK Button
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed

            }
        });

        // Showing Alert Message
        alertDialog1.show();
    }

    public void dialogEisagogi2() {
        // Creating alert Dialog with one Button

        AlertDialog alertDialog1 = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialog1.setIcon(R.drawable.baseline_info_24);
        // Setting Dialog Title
        alertDialog1.setTitle("Ειδοποίηση");

        // Setting Dialog Message
        alertDialog1.setMessage("Επιτυχής Εισαγωγή");

        // Setting OK Button
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed

            }
        });

        // Showing Alert Message
        alertDialog1.show();
    }

    public void dialogEidopoiisi(){
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialog1.setIcon(R.drawable.baseline_info_24);
        // Setting Dialog Title
        alertDialog1.setTitle("Ειδοποίηση");

        // Setting Dialog Message
        alertDialog1.setMessage("Παρακαλώ εισάγετε μόνο τους αθλητές που είναι απαραίτητοι");

        // Setting OK Button
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed

            }
        });

        // Showing Alert Message
        alertDialog1.show();
    }

    public void onRadioButtonClickedE2(View view){
        TextInputLayout omada1T = findViewById(R.id.omada1T);
        TextInputLayout omada2T = findViewById(R.id.omada2T);
        TextInputLayout scoreT = findViewById(R.id.scoreT);
        TextInputLayout epidosiT = findViewById(R.id.epidosiT);
        TextInputLayout onomaAthlitiT = findViewById(R.id.onomaAthliti2T);
        TextInputLayout epidosi2T = findViewById(R.id.epidosi2T);
        TextInputLayout onomaAthliti2T = findViewById(R.id.onomaAthliti22T);
        TextInputLayout epidosi3T = findViewById(R.id.epidosi3T);
        TextInputLayout onomaAthliti3T = findViewById(R.id.onomaAthliti23T);
        TextInputLayout epidosi4T = findViewById(R.id.epidosi4T);
        TextInputLayout onomaAthliti4T = findViewById(R.id.onomaAthliti24T);
        TextInputLayout epidosi5T = findViewById(R.id.epidosi5T);
        TextInputLayout onomaAthliti5T = findViewById(R.id.onomaAthliti25T);
        TextInputLayout epidosi6T = findViewById(R.id.epidosi6T);
        TextInputLayout onomaAthliti6T = findViewById(R.id.onomaAthliti26T);
        TextInputLayout epidosi7T = findViewById(R.id.epidosi7T);
        TextInputLayout onomaAthliti7T = findViewById(R.id.onomaAthliti27T);
        TextInputLayout epidosi8T = findViewById(R.id.epidosi8T);
        TextInputLayout onomaAthliti8T = findViewById(R.id.onomaAthliti28T);
        TextInputLayout epidosi9T = findViewById(R.id.epidosi9T);
        TextInputLayout onomaAthliti9T = findViewById(R.id.onomaAthliti29T);
        TextInputLayout epidosi10T = findViewById(R.id.epidosi10T);
        TextInputLayout onomaAthliti10T = findViewById(R.id.onomaAthliti210T);

        omada1T.setVisibility(view.INVISIBLE);
        omada2T.setVisibility(view.INVISIBLE);
        scoreT.setVisibility(view.INVISIBLE);
        epidosiT.setVisibility(view.INVISIBLE);
        onomaAthlitiT.setVisibility(view.INVISIBLE);
        epidosi2T.setVisibility(view.INVISIBLE);
        onomaAthliti2T.setVisibility(view.INVISIBLE);
        epidosi3T.setVisibility(view.INVISIBLE);
        onomaAthliti3T.setVisibility(view.INVISIBLE);
        epidosi4T.setVisibility(view.INVISIBLE);
        onomaAthliti4T.setVisibility(view.INVISIBLE);
        epidosi5T.setVisibility(view.INVISIBLE);
        onomaAthliti5T.setVisibility(view.INVISIBLE);
        epidosi6T.setVisibility(view.INVISIBLE);
        onomaAthliti6T.setVisibility(view.INVISIBLE);
        epidosi7T.setVisibility(view.INVISIBLE);
        onomaAthliti7T.setVisibility(view.INVISIBLE);
        epidosi8T.setVisibility(view.INVISIBLE);
        onomaAthliti8T.setVisibility(view.INVISIBLE);
        epidosi9T.setVisibility(view.INVISIBLE);
        onomaAthliti9T.setVisibility(view.INVISIBLE);
        epidosi10T.setVisibility(view.INVISIBLE);
        onomaAthliti10T.setVisibility(view.INVISIBLE);
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.omadiko2:
                if (checked) {
                    omada1T.setVisibility(view.VISIBLE);
                    omada2T.setVisibility(view.VISIBLE);
                    scoreT.setVisibility(view.VISIBLE);
                }
                break;
            case R.id.atomiko2:
                if (checked) {
                    epidosiT.setVisibility(view.VISIBLE);
                    onomaAthlitiT.setVisibility(view.VISIBLE);
                    epidosi2T.setVisibility(view.VISIBLE);
                    onomaAthliti2T.setVisibility(view.VISIBLE);
                    epidosi3T.setVisibility(view.VISIBLE);
                    onomaAthliti3T.setVisibility(view.VISIBLE);
                    epidosi4T.setVisibility(view.VISIBLE);
                    onomaAthliti4T.setVisibility(view.VISIBLE);
                    epidosi5T.setVisibility(view.VISIBLE);
                    onomaAthliti5T.setVisibility(view.VISIBLE);
                    epidosi6T.setVisibility(view.VISIBLE);
                    onomaAthliti6T.setVisibility(view.VISIBLE);
                    epidosi7T.setVisibility(view.VISIBLE);
                    onomaAthliti7T.setVisibility(view.VISIBLE);
                    epidosi8T.setVisibility(view.VISIBLE);
                    onomaAthliti8T.setVisibility(view.VISIBLE);
                    epidosi9T.setVisibility(view.VISIBLE);
                    onomaAthliti9T.setVisibility(view.VISIBLE);
                    epidosi10T.setVisibility(view.VISIBLE);
                    onomaAthliti10T.setVisibility(view.VISIBLE);
                    dialogEidopoiisi();
                }
                break;
        }
    }


    public void eisagogi2OnClick(View view){
        RadioGroup radioGroup = findViewById(R.id.radioGroupE2);
        EditText imerominiaAgona = findViewById(R.id.imerAgona);
        EditText poliAgona = findViewById(R.id.poliAgona);
        EditText xoraAgona = findViewById(R.id.xoraAgona);
        EditText onomaAthlimatos = findViewById(R.id.onomaAthlimatos);
        EditText kodikosAgona = findViewById(R.id.kodikosAgona);

        int kodikosAgonaInt;
        if(kodikosAgona.getText().toString().isEmpty()){
            kodikosAgonaInt = 0;
        }
        else {
            kodikosAgonaInt = Integer.parseInt(kodikosAgona.getText().toString());
            if(kodikosAgonaInt < 1){
                dialogkodikos();
                return;
            }
        }
        try {
            DocumentReference documentReference = db.collection("Agonas").document("" + kodikosAgonaInt);
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            dialogSfalmaId();

                        } else {
                            String onomaAthlimatosSt = onomaAthlimatos.getText().toString();
                            String poliAgonaSt = poliAgona.getText().toString();
                            String xoraAgonaSt = xoraAgona.getText().toString();
                            String imerominiaAgonaSt = imerominiaAgona.getText().toString();
                            String eidos = "";
                            switch (radioGroup.getCheckedRadioButtonId()){
                                case R.id.omadiko2:
                                    eidos = "Ομαδικό";
                                    break;
                                case R.id.atomiko2:
                                    eidos = "Ατομικό";
                                    break;
                            }
                            if(kodikosAgonaInt == 0 || onomaAthlimatosSt.isEmpty() || poliAgonaSt.isEmpty() || xoraAgonaSt.isEmpty() || imerominiaAgonaSt.isEmpty() || eidos.equals("")){
                                dialogAdeiaPedia();
                                return;
                            }
                            if (eidos.equals("Ομαδικό")){
                                TextInputLayout omada1T = findViewById(R.id.omada1T);
                                TextInputLayout omada2T = findViewById(R.id.omada2T);
                                TextInputLayout scoreT = findViewById(R.id.scoreT);
                                EditText omada1 = findViewById(R.id.omada1);
                                EditText omada2 = findViewById(R.id.omada2);
                                EditText score = findViewById(R.id.score);

                                String omada1St = omada1.getText().toString();
                                String omada2St = omada2.getText().toString();
                                String scoreSt = score.getText().toString();
                                if (omada1St.isEmpty() || omada2St.isEmpty() || scoreSt.isEmpty()){
                                    dialogAdeiaPedia();
                                    return;
                                }
                                try{
                                    Agonas agonas = new Agonas();
                                    agonas.setOnoma(onomaAthlimatosSt);
                                    agonas.setPoliAgona(poliAgonaSt);
                                    agonas.setXoraAgona(xoraAgonaSt);
                                    agonas.setImerAgona(imerominiaAgonaSt);
                                    agonas.setOmada1(omada1St);
                                    agonas.setOmada2(omada2St);
                                    agonas.setScore(scoreSt);

                                    agonas.setOnomaAthliti1("n");
                                    agonas.setEpidosi1("n");
                                    agonas.setOnomaAthliti2("n");
                                    agonas.setEpidosi2("n");

                                    agonas.setOnomaAthliti3("n");
                                    agonas.setEpidosi3("n");

                                    agonas.setOnomaAthliti4("n");
                                    agonas.setEpidosi4("n");
                                    agonas.setOnomaAthliti5("n");
                                    agonas.setEpidosi5("n");

                                    agonas.setOnomaAthliti6("n");
                                    agonas.setEpidosi6("n");

                                    agonas.setOnomaAthliti7("n");
                                    agonas.setEpidosi7("n");

                                    agonas.setOnomaAthliti8("n");
                                    agonas.setEpidosi8("n");

                                    agonas.setOnomaAthliti9("n");
                                    agonas.setEpidosi9("n");

                                    agonas.setOnomaAthliti10("n");
                                    agonas.setEpidosi10("n");

                                    db.collection("Agonas").document(""+kodikosAgonaInt).set(agonas);
                                }catch (Exception e){
                                    dialogSfalma();
                                    return;
                                }

                                kodikosAgona.setText("");
                                onomaAthlimatos.setText("");
                                poliAgona.setText("");
                                xoraAgona.setText("");
                                imerominiaAgona.setText("");
                                radioGroup.clearCheck();
                                omada1T.setVisibility(view.INVISIBLE);
                                omada2T.setVisibility(view.INVISIBLE);
                                scoreT.setVisibility(view.INVISIBLE);
                                omada1.setText("");
                                omada2.setText("");
                                score.setText("");
                                dialogEisagogi2();
                            }
                            else {
                                TextInputLayout onomaAthliti2T = findViewById(R.id.onomaAthliti2T);
                                EditText onomaAthliti2 = findViewById(R.id.onomaAthliti2);
                                TextInputLayout epidosiT = findViewById(R.id.epidosiT);
                                EditText epidosi = findViewById(R.id.epidosi);
                                TextInputLayout epidosi2T = findViewById(R.id.epidosi2T);
                                TextInputLayout onomaAthliti22T = findViewById(R.id.onomaAthliti22T);
                                TextInputLayout epidosi3T = findViewById(R.id.epidosi3T);
                                TextInputLayout onomaAthliti3T = findViewById(R.id.onomaAthliti23T);
                                TextInputLayout epidosi4T = findViewById(R.id.epidosi4T);
                                TextInputLayout onomaAthliti4T = findViewById(R.id.onomaAthliti24T);
                                TextInputLayout epidosi5T = findViewById(R.id.epidosi5T);
                                TextInputLayout onomaAthliti5T = findViewById(R.id.onomaAthliti25T);
                                TextInputLayout epidosi6T = findViewById(R.id.epidosi6T);
                                TextInputLayout onomaAthliti6T = findViewById(R.id.onomaAthliti26T);
                                TextInputLayout epidosi7T = findViewById(R.id.epidosi7T);
                                TextInputLayout onomaAthliti7T = findViewById(R.id.onomaAthliti27T);
                                TextInputLayout epidosi8T = findViewById(R.id.epidosi8T);
                                TextInputLayout onomaAthliti8T = findViewById(R.id.onomaAthliti28T);
                                TextInputLayout epidosi9T = findViewById(R.id.epidosi9T);
                                TextInputLayout onomaAthliti9T = findViewById(R.id.onomaAthliti29T);
                                TextInputLayout epidosi10T = findViewById(R.id.epidosi10T);
                                TextInputLayout onomaAthliti10T = findViewById(R.id.onomaAthliti210T);

                                EditText onomaAthliti22 = findViewById(R.id.onomaAthliti22);
                                EditText epidosi2 = findViewById(R.id.epidosi2);
                                EditText onomaAthliti23 = findViewById(R.id.onomaAthliti23);
                                EditText epidosi3 = findViewById(R.id.epidosi3);
                                EditText onomaAthliti24 = findViewById(R.id.onomaAthliti24);
                                EditText epidosi4 = findViewById(R.id.epidosi4);
                                EditText onomaAthliti25 = findViewById(R.id.onomaAthliti25);
                                EditText epidosi5 = findViewById(R.id.epidosi5);
                                EditText onomaAthliti26 = findViewById(R.id.onomaAthliti26);
                                EditText epidosi6 = findViewById(R.id.epidosi6);
                                EditText onomaAthliti27 = findViewById(R.id.onomaAthliti27);
                                EditText epidosi7 = findViewById(R.id.epidosi7);
                                EditText onomaAthliti28 = findViewById(R.id.onomaAthliti28);
                                EditText epidosi8 = findViewById(R.id.epidosi8);
                                EditText onomaAthliti29 = findViewById(R.id.onomaAthliti29);
                                EditText epidosi9 = findViewById(R.id.epidosi9);
                                EditText onomaAthliti210 = findViewById(R.id.onomaAthliti210);
                                EditText epidosi10 = findViewById(R.id.epidosi10);

                                String onomaAthliti2St = onomaAthliti2.getText().toString();
                                String epidosiSt = epidosi.getText().toString();
                                if (epidosiSt.isEmpty() || onomaAthliti2St.isEmpty()){
                                    dialogAdeiaPedia();
                                    return;
                                }
                                String onomaAthliti22St = onomaAthliti22.getText().toString();
                                String epidosi2St = epidosi2.getText().toString();
                                String onomaAthliti23St = onomaAthliti23.getText().toString();
                                String epidosi3St = epidosi3.getText().toString();
                                String onomaAthliti24St = onomaAthliti24.getText().toString();
                                String epidosi4St = epidosi4.getText().toString();
                                String onomaAthliti25St = onomaAthliti25.getText().toString();
                                String epidosi5St = epidosi5.getText().toString();
                                String onomaAthliti26St = onomaAthliti26.getText().toString();
                                String epidosi6St = epidosi6.getText().toString();
                                String onomaAthliti27St = onomaAthliti27.getText().toString();
                                String epidosi7St = epidosi7.getText().toString();
                                String onomaAthliti28St = onomaAthliti28.getText().toString();
                                String epidosi8St = epidosi8.getText().toString();
                                String onomaAthliti29St = onomaAthliti29.getText().toString();
                                String epidosi9St = epidosi9.getText().toString();
                                String onomaAthliti210St = onomaAthliti210.getText().toString();
                                String epidosi10St = epidosi10.getText().toString();
                                try{
                                    Agonas agonas = new Agonas();
                                    agonas.setOnoma(onomaAthlimatosSt);
                                    agonas.setPoliAgona(poliAgonaSt);
                                    agonas.setXoraAgona(xoraAgonaSt);
                                    agonas.setImerAgona(imerominiaAgonaSt);
                                    agonas.setOnomaAthliti1(onomaAthliti2St);
                                    agonas.setEpidosi1(epidosiSt);
                                    agonas.setOmada1("n");
                                    agonas.setOmada2("n");
                                    agonas.setScore("n");
                                    if(!onomaAthliti22St.isEmpty() &&  !epidosi2St.isEmpty()){
                                        agonas.setOnomaAthliti2(onomaAthliti22St);
                                        agonas.setEpidosi2(epidosi2St);
                                    }
                                    else{
                                        agonas.setOnomaAthliti2("n");
                                        agonas.setEpidosi2("n");
                                    }
                                    if(!onomaAthliti23St.isEmpty() &&  !epidosi3St.isEmpty()){
                                        agonas.setOnomaAthliti3(onomaAthliti23St);
                                        agonas.setEpidosi3(epidosi3St);
                                    }
                                    else{
                                        agonas.setOnomaAthliti3("n");
                                        agonas.setEpidosi3("n");
                                    }
                                    if(!onomaAthliti24St.isEmpty() &&  !epidosi4St.isEmpty()){
                                        agonas.setOnomaAthliti4(onomaAthliti24St);
                                        agonas.setEpidosi4(epidosi4St);
                                    }
                                    else{
                                        agonas.setOnomaAthliti4("n");
                                        agonas.setEpidosi4("n");
                                    }
                                    if(!onomaAthliti25St.isEmpty() &&  !epidosi5St.isEmpty()){
                                        agonas.setOnomaAthliti5(onomaAthliti25St);
                                        agonas.setEpidosi5(epidosi5St);
                                    }
                                    else{
                                        agonas.setOnomaAthliti5("n");
                                        agonas.setEpidosi5("n");
                                    }
                                    if(!onomaAthliti26St.isEmpty() &&  !epidosi6St.isEmpty()){
                                        agonas.setOnomaAthliti6(onomaAthliti26St);
                                        agonas.setEpidosi6(epidosi6St);
                                    }
                                    else{
                                        agonas.setOnomaAthliti6("n");
                                        agonas.setEpidosi6("n");
                                    }
                                    if(!onomaAthliti27St.isEmpty() &&  !epidosi7St.isEmpty()){
                                        agonas.setOnomaAthliti7(onomaAthliti27St);
                                        agonas.setEpidosi7(epidosi7St);
                                    }
                                    else{
                                        agonas.setOnomaAthliti7("n");
                                        agonas.setEpidosi7("n");
                                    }
                                    if(!onomaAthliti28St.isEmpty() &&  !epidosi8St.isEmpty()){
                                        agonas.setOnomaAthliti8(onomaAthliti28St);
                                        agonas.setEpidosi8(epidosi8St);
                                    }
                                    else{
                                        agonas.setOnomaAthliti8("n");
                                        agonas.setEpidosi8("n");
                                    }
                                    if(!onomaAthliti29St.isEmpty() &&  !epidosi9St.isEmpty()){
                                        agonas.setOnomaAthliti9(onomaAthliti29St);
                                        agonas.setEpidosi9(epidosi9St);
                                    }
                                    else{
                                        agonas.setOnomaAthliti9("n");
                                        agonas.setEpidosi9("n");
                                    }
                                    if(!onomaAthliti210St.isEmpty() &&  !epidosi10St.isEmpty()){
                                        agonas.setOnomaAthliti10(onomaAthliti210St);
                                        agonas.setEpidosi10(epidosi10St);
                                    }
                                    else{
                                        agonas.setOnomaAthliti10("n");
                                        agonas.setEpidosi10("n");
                                    }
                                    db.collection("Agonas").document(""+kodikosAgonaInt).set(agonas);
                                }catch (Exception e){
                                    dialogSfalma();
                                    return;
                                }

                                kodikosAgona.setText("");
                                onomaAthlimatos.setText("");
                                epidosi.setText("");
                                onomaAthliti2.setText("");
                                epidosiT.setVisibility(view.INVISIBLE);
                                onomaAthliti2T.setVisibility(view.INVISIBLE);
                                epidosi2T.setVisibility(view.INVISIBLE);
                                onomaAthliti22T.setVisibility(view.INVISIBLE);
                                epidosi3T.setVisibility(view.INVISIBLE);
                                onomaAthliti3T.setVisibility(view.INVISIBLE);
                                epidosi4T.setVisibility(view.INVISIBLE);
                                onomaAthliti4T.setVisibility(view.INVISIBLE);
                                epidosi5T.setVisibility(view.INVISIBLE);
                                onomaAthliti5T.setVisibility(view.INVISIBLE);
                                epidosi6T.setVisibility(view.INVISIBLE);
                                onomaAthliti6T.setVisibility(view.INVISIBLE);
                                epidosi7T.setVisibility(view.INVISIBLE);
                                onomaAthliti7T.setVisibility(view.INVISIBLE);
                                epidosi8T.setVisibility(view.INVISIBLE);
                                onomaAthliti8T.setVisibility(view.INVISIBLE);
                                epidosi9T.setVisibility(view.INVISIBLE);
                                onomaAthliti9T.setVisibility(view.INVISIBLE);
                                epidosi10T.setVisibility(view.INVISIBLE);
                                onomaAthliti10T.setVisibility(view.INVISIBLE);
                                imerominiaAgona.setText("");
                                poliAgona.setText("");
                                xoraAgona.setText("");
                                onomaAthliti22.setText("");
                                epidosi2.setText("");
                                onomaAthliti23.setText("");
                                epidosi3.setText("");
                                onomaAthliti24.setText("");
                                epidosi4.setText("");
                                onomaAthliti25.setText("");
                                epidosi5.setText("");
                                onomaAthliti26.setText("");
                                epidosi6.setText("");
                                onomaAthliti27.setText("");
                                epidosi7.setText("");
                                onomaAthliti28.setText("");
                                epidosi8.setText("");
                                onomaAthliti29.setText("");
                                epidosi9.setText("");
                                onomaAthliti210.setText("");
                                epidosi10.setText("");
                                RadioGroup radioGroupE2 = findViewById(R.id.radioGroupE2);
                                radioGroupE2.clearCheck();
                                dialogEisagogi2();
                            }
                        }
                    } else {

                    }
                }
            });

        }catch(Exception e){
            dialogSfalma();
            return;
        }




    }

    public void dialogTropopoiisi2(){
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialog1.setIcon(R.drawable.baseline_info_24);
        // Setting Dialog Title
        alertDialog1.setTitle("Ειδοποίηση");

        // Setting Dialog Message
        alertDialog1.setMessage("Επιτυχής Τροποποίηση");

        // Setting OK Button
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed

            }
        });

        // Showing Alert Message
        alertDialog1.show();
    }

    boolean bGlobal2;
    int kodikosAgonaTRInt;
    public void anazitisiT2(View view){
        TextInputLayout imerAgonaTTR = findViewById(R.id.imerAgonaTTR);
        TextInputLayout xoraAgonaTTR = findViewById(R.id.xoraAgonaTTR);
        TextInputLayout poliAgonaTTR = findViewById(R.id.poliAgonaTTR);
        TextInputLayout onomaAthlimatosTTR = findViewById(R.id.onomaAthlimatosTTR);
        TextInputLayout omada1TTR = findViewById(R.id.omada1TTR);
        TextInputLayout omada2TTR = findViewById(R.id.omada2TTR);
        TextInputLayout scoreTTR = findViewById(R.id.scoreTTR);
        TextInputLayout onomaAthliti2TTR = findViewById(R.id.onomaAthliti2TTR);
        TextInputLayout epidosiTTR = findViewById(R.id.epidosiTTR);

        TextInputLayout onomaAthliti22TTR = findViewById(R.id.onomaAthliti22TTR);
        TextInputLayout epidosi2TTR = findViewById(R.id.epidosi2TTR);
        TextInputLayout onomaAthliti23TTR = findViewById(R.id.onomaAthliti23TTR);
        TextInputLayout epidosi3TTR = findViewById(R.id.epidosi3TTR);
        TextInputLayout onomaAthliti24TTR = findViewById(R.id.onomaAthliti24TTR);
        TextInputLayout epidosi4TTR = findViewById(R.id.epidosi4TTR);
        TextInputLayout onomaAthliti25TTR = findViewById(R.id.onomaAthliti25TTR);
        TextInputLayout epidosi5TTR = findViewById(R.id.epidosi5TTR);
        TextInputLayout onomaAthliti26TTR = findViewById(R.id.onomaAthliti26TTR);
        TextInputLayout epidosi6TTR = findViewById(R.id.epidosi6TTR);
        TextInputLayout onomaAthliti27TTR = findViewById(R.id.onomaAthliti27TTR);
        TextInputLayout epidosi7TTR = findViewById(R.id.epidosi7TTR);
        TextInputLayout onomaAthliti28TTR = findViewById(R.id.onomaAthliti28TTR);
        TextInputLayout epidosi8TTR = findViewById(R.id.epidosi8TTR);
        TextInputLayout onomaAthliti29TTR = findViewById(R.id.onomaAthliti29TTR);
        TextInputLayout epidosi9TTR = findViewById(R.id.epidosi9TTR);
        TextInputLayout onomaAthliti210TTR = findViewById(R.id.onomaAthliti210TTR);
        TextInputLayout epidosi10TTR = findViewById(R.id.epidosi10TTR);
        Button buttonTropopoiisi2 = findViewById(R.id.buttonTropopoiisi2);

        EditText kodikosAgonaTR = findViewById(R.id.kodikosAgonaTR);

        onomaAthlimatosTTR.setVisibility(view.INVISIBLE);
        imerAgonaTTR.setVisibility(view.INVISIBLE);
        poliAgonaTTR.setVisibility(view.INVISIBLE);
        xoraAgonaTTR.setVisibility(view.INVISIBLE);
        onomaAthliti2TTR.setVisibility(view.INVISIBLE);
        epidosiTTR.setVisibility(view.INVISIBLE);
        onomaAthliti22TTR.setVisibility(view.INVISIBLE);
        epidosi2TTR.setVisibility(view.INVISIBLE);
        onomaAthliti23TTR.setVisibility(view.INVISIBLE);
        epidosi3TTR.setVisibility(view.INVISIBLE);
        onomaAthliti24TTR.setVisibility(view.INVISIBLE);
        epidosi4TTR.setVisibility(view.INVISIBLE);
        onomaAthliti25TTR.setVisibility(view.INVISIBLE);
        epidosi5TTR.setVisibility(view.INVISIBLE);
        onomaAthliti26TTR.setVisibility(view.INVISIBLE);
        epidosi6TTR.setVisibility(view.INVISIBLE);
        onomaAthliti27TTR.setVisibility(view.INVISIBLE);
        epidosi7TTR.setVisibility(view.INVISIBLE);
        onomaAthliti28TTR.setVisibility(view.INVISIBLE);
        epidosi8TTR.setVisibility(view.INVISIBLE);
        onomaAthliti29TTR.setVisibility(view.INVISIBLE);
        epidosi9TTR.setVisibility(view.INVISIBLE);
        onomaAthliti210TTR.setVisibility(view.INVISIBLE);
        epidosi10TTR.setVisibility(view.INVISIBLE);
        buttonTropopoiisi2.setVisibility(view.INVISIBLE);
        omada1TTR.setVisibility(view.INVISIBLE);
        omada2TTR.setVisibility(view.INVISIBLE);
        scoreTTR.setVisibility(view.INVISIBLE);

        if(kodikosAgonaTR.getText().toString().isEmpty()){
            dialogAdeiaPedia();
            return;
        }
        else {
            kodikosAgonaTRInt = Integer.parseInt(kodikosAgonaTR.getText().toString());
            if (kodikosAgonaTRInt < 1){
                dialogkodikos();
                return;
            }
        }
        DocumentReference documentReference = db.collection("Agonas").document("" + kodikosAgonaTRInt);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        try {
                            String omada1St = document.getString("omada1");
                            if (omada1St.equals("n")){
                                dialogAthlitisEpidosi();
                                onomaAthlimatosTTR.setVisibility(view.VISIBLE);
                                imerAgonaTTR.setVisibility(view.VISIBLE);
                                poliAgonaTTR.setVisibility(view.VISIBLE);
                                xoraAgonaTTR.setVisibility(view.VISIBLE);
                                onomaAthliti2TTR.setVisibility(view.VISIBLE);
                                epidosiTTR.setVisibility(view.VISIBLE);
                                onomaAthliti22TTR.setVisibility(view.VISIBLE);
                                epidosi2TTR.setVisibility(view.VISIBLE);
                                onomaAthliti23TTR.setVisibility(view.VISIBLE);
                                epidosi3TTR.setVisibility(view.VISIBLE);
                                onomaAthliti24TTR.setVisibility(view.VISIBLE);
                                epidosi4TTR.setVisibility(view.VISIBLE);
                                onomaAthliti25TTR.setVisibility(view.VISIBLE);
                                epidosi5TTR.setVisibility(view.VISIBLE);
                                onomaAthliti26TTR.setVisibility(view.VISIBLE);
                                epidosi6TTR.setVisibility(view.VISIBLE);
                                onomaAthliti27TTR.setVisibility(view.VISIBLE);
                                epidosi7TTR.setVisibility(view.VISIBLE);
                                onomaAthliti28TTR.setVisibility(view.VISIBLE);
                                epidosi8TTR.setVisibility(view.VISIBLE);
                                onomaAthliti29TTR.setVisibility(view.VISIBLE);
                                epidosi9TTR.setVisibility(view.VISIBLE);
                                onomaAthliti210TTR.setVisibility(view.VISIBLE);
                                epidosi10TTR.setVisibility(view.VISIBLE);
                                buttonTropopoiisi2.setVisibility(view.VISIBLE);
                                bGlobal2 = false;
                            }
                            else{
                                onomaAthlimatosTTR.setVisibility(view.VISIBLE);
                                imerAgonaTTR.setVisibility(view.VISIBLE);
                                poliAgonaTTR.setVisibility(view.VISIBLE);
                                xoraAgonaTTR.setVisibility(view.VISIBLE);
                                omada1TTR.setVisibility(view.VISIBLE);
                                omada2TTR.setVisibility(view.VISIBLE);
                                scoreTTR.setVisibility(view.VISIBLE);
                                buttonTropopoiisi2.setVisibility(view.VISIBLE);
                                bGlobal2 = true;
                            }


                        }catch (Exception e){
                            dialogSfalma();
                            return;
                        }
                    }
                    else{
                        dialogDenYparxei();
                        kodikosAgonaTR.setText("");
                        return;
                    }

                }
            }
        });

    }

    public void dialogAdeiaPedia2(){
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialog1.setIcon(R.drawable.baseline_info_24);
        // Setting Dialog Title
        alertDialog1.setTitle("Ειδοποίηση");

        // Setting Dialog Message
        alertDialog1.setMessage("Συμπληρώστε τουλάχιστον ένα πεδίο");

        // Setting OK Button
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed

            }
        });

        // Showing Alert Message
        alertDialog1.show();
    }

    public void dialogAthlitisEpidosi() {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialog1.setIcon(R.drawable.baseline_info_24);
        // Setting Dialog Title
        alertDialog1.setTitle("Ειδοποίηση");

        // Setting Dialog Message
        alertDialog1.setMessage("Σε περίπτωση τροποποίησης Αθλητή, παρακαλώ συμπληρώστε και το Όνομα και την Επίδοση");

        // Setting OK Button
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed

            }
        });

        // Showing Alert Message
        alertDialog1.show();
    }

    public void tropopoiisi2OnClick(View view){
        TextInputLayout kodikosAgonaTTR = findViewById(R.id.kodikosAgonaTTR);
        EditText kodikosAgonaTR = findViewById(R.id.kodikosAgonaTR);
        TextInputLayout imerAgonaTTR = findViewById(R.id.imerAgonaTTR);
        EditText imerAgonaTR = findViewById(R.id.imerAgonaTR);
        TextInputLayout xoraAgonaTTR = findViewById(R.id.xoraAgonaTTR);
        EditText xoraAgonaTR = findViewById(R.id.xoraAgonaTR);
        TextInputLayout poliAgonaTTR = findViewById(R.id.poliAgonaTTR);
        EditText poliAgonaTR = findViewById(R.id.poliAgonaTR);
        TextInputLayout onomaAthlimatosTTR = findViewById(R.id.onomaAthlimatosTTR);
        EditText onomaAthlimatosTR = findViewById(R.id.onomaAthlimatosTR);
        TextInputLayout omada1TTR = findViewById(R.id.omada1TTR);
        EditText omada1TR = findViewById(R.id.omada1TR);
        TextInputLayout omada2TTR = findViewById(R.id.omada2TTR);
        EditText omada2TR = findViewById(R.id.omada2TR);
        TextInputLayout scoreTTR = findViewById(R.id.scoreTTR);
        EditText scoreTR = findViewById(R.id.scoreTR);
        TextInputLayout onomaAthliti2TTR = findViewById(R.id.onomaAthliti2TTR);
        EditText onomaAthliti2TR = findViewById(R.id.onomaAthliti2TR);
        TextInputLayout epidosiTTR = findViewById(R.id.epidosiTTR);
        EditText epidosiTR = findViewById(R.id.epidosiTR);
        TextInputLayout onomaAthliti22TTR = findViewById(R.id.onomaAthliti22TTR);
        TextInputLayout epidosi2TTR = findViewById(R.id.epidosi2TTR);
        TextInputLayout onomaAthliti23TTR = findViewById(R.id.onomaAthliti23TTR);
        TextInputLayout epidosi3TTR = findViewById(R.id.epidosi3TTR);
        TextInputLayout onomaAthliti24TTR = findViewById(R.id.onomaAthliti24TTR);
        TextInputLayout epidosi4TTR = findViewById(R.id.epidosi4TTR);
        TextInputLayout onomaAthliti25TTR = findViewById(R.id.onomaAthliti25TTR);
        TextInputLayout epidosi5TTR = findViewById(R.id.epidosi5TTR);
        TextInputLayout onomaAthliti26TTR = findViewById(R.id.onomaAthliti26TTR);
        TextInputLayout epidosi6TTR = findViewById(R.id.epidosi6TTR);
        TextInputLayout onomaAthliti27TTR = findViewById(R.id.onomaAthliti27TTR);
        TextInputLayout epidosi7TTR = findViewById(R.id.epidosi7TTR);
        TextInputLayout onomaAthliti28TTR = findViewById(R.id.onomaAthliti28TTR);
        TextInputLayout epidosi8TTR = findViewById(R.id.epidosi8TTR);
        TextInputLayout onomaAthliti29TTR = findViewById(R.id.onomaAthliti29TTR);
        TextInputLayout epidosi9TTR = findViewById(R.id.epidosi9TTR);
        TextInputLayout onomaAthliti210TTR = findViewById(R.id.onomaAthliti210TTR);
        TextInputLayout epidosi10TTR = findViewById(R.id.epidosi10TTR);
        EditText onomaAthliti22TR = findViewById(R.id.onomaAthliti22TR);
        EditText onomaAthliti23TR = findViewById(R.id.onomaAthliti23TR);
        EditText onomaAthliti24TR = findViewById(R.id.onomaAthliti24TR);
        EditText onomaAthliti25TR = findViewById(R.id.onomaAthliti25TR);
        EditText onomaAthliti26TR = findViewById(R.id.onomaAthliti26TR);
        EditText onomaAthliti27TR = findViewById(R.id.onomaAthliti27TR);
        EditText onomaAthliti28TR = findViewById(R.id.onomaAthliti28TR);
        EditText onomaAthliti29TR = findViewById(R.id.onomaAthliti29TR);
        EditText onomaAthliti210TR = findViewById(R.id.onomaAthliti210TR);
        EditText epidosi2TR = findViewById(R.id.epidosi2TR);
        EditText epidosi3TR = findViewById(R.id.epidosi3TR);
        EditText epidosi4TR = findViewById(R.id.epidosi4TR);
        EditText epidosi5TR = findViewById(R.id.epidosi5TR);
        EditText epidosi6TR = findViewById(R.id.epidosi6TR);
        EditText epidosi7TR = findViewById(R.id.epidosi7TR);
        EditText epidosi8TR = findViewById(R.id.epidosi8TR);
        EditText epidosi9TR = findViewById(R.id.epidosi9TR);
        EditText epidosi10TR = findViewById(R.id.epidosi10TR);
        Button buttonTropopoiisi2 = findViewById(R.id.buttonTropopoiisi2);


        String imerAgonaTRSt = imerAgonaTR.getText().toString();
        String xoraAgonaTRSt = xoraAgonaTR.getText().toString();
        String poliAgonaTRSt = poliAgonaTR.getText().toString();
        String onomaAthlimatosTRSt = onomaAthlimatosTR.getText().toString();
        if (bGlobal2){
            String omada1St = omada1TR.getText().toString();
            String omada2St = omada2TR.getText().toString();
            String scoreSt = scoreTR.getText().toString();

            if(onomaAthlimatosTRSt.isEmpty() && imerAgonaTRSt.isEmpty() && xoraAgonaTRSt.isEmpty() && poliAgonaTRSt.isEmpty() && omada1St.isEmpty() && omada2St.isEmpty() && scoreSt.isEmpty()){
                dialogAdeiaPedia2();
                return;
            }

            try {
                DocumentReference docref = db.collection("Agonas").document(""+kodikosAgonaTRInt);
                Map<String,Object> map = new HashMap<>();
                if(!onomaAthlimatosTRSt.isEmpty())
                    map.put("onoma", onomaAthlimatosTRSt);
                if(!imerAgonaTRSt.isEmpty())
                    map.put("imerAgona", imerAgonaTRSt);
                if(!xoraAgonaTRSt.isEmpty())
                    map.put("xoraAgona", xoraAgonaTRSt);
                if (!xoraAgonaTRSt.isEmpty())
                    map.put("poliAgona", xoraAgonaTRSt);
                if (!omada1St.isEmpty())
                    map.put("omada1",omada1St);
                if (!omada2St.isEmpty())
                    map.put("omada2",omada2St);
                if (!scoreSt.isEmpty())
                    map.put("score",scoreSt);

                docref.update(map);

            }catch (Exception e){
                dialogSfalma();
                return;
            }
            dialogTropopoiisi2();
            omada1TTR.setVisibility(view.INVISIBLE);
            omada1TR.setText("");
            omada2TTR.setVisibility(view.INVISIBLE);
            omada2TR.setText("");
            scoreTTR.setVisibility(view.INVISIBLE);
            scoreTR.setText("");
            imerAgonaTR.setText("");
            xoraAgonaTR.setText("");
            poliAgonaTR.setText("");
            kodikosAgonaTR.setText("");
            onomaAthlimatosTR.setText("");
            imerAgonaTTR.setVisibility(view.INVISIBLE);
            xoraAgonaTTR.setVisibility(view.INVISIBLE);
            poliAgonaTTR.setVisibility(view.INVISIBLE);
            onomaAthlimatosTTR.setVisibility(view.INVISIBLE);
            buttonTropopoiisi2.setVisibility(view.INVISIBLE);
        }
        else {
            String onomaAthliti2TRSt = onomaAthliti2TR.getText().toString();
            String epidosiTRSt = epidosiTR.getText().toString();
            String onomaAthliti22TRSt = onomaAthliti22TR.getText().toString();
            String epidosi2TRSt = epidosi2TR.getText().toString();
            String onomaAthliti23TRSt = onomaAthliti23TR.getText().toString();
            String epidosi3TRSt = epidosi3TR.getText().toString();
            String onomaAthliti24TRSt = onomaAthliti24TR.getText().toString();
            String epidosi4TRSt = epidosi4TR.getText().toString();
            String onomaAthliti25TRSt = onomaAthliti25TR.getText().toString();
            String epidosi5TRSt = epidosi5TR.getText().toString();
            String onomaAthliti26TRSt = onomaAthliti26TR.getText().toString();
            String epidosi6TRSt = epidosi6TR.getText().toString();
            String onomaAthliti27TRSt = onomaAthliti27TR.getText().toString();
            String epidosi7TRSt = epidosi7TR.getText().toString();
            String onomaAthliti28TRSt = onomaAthliti28TR.getText().toString();
            String epidosi8TRSt = epidosi8TR.getText().toString();
            String onomaAthliti29TRSt = onomaAthliti29TR.getText().toString();
            String epidosi9TRSt = epidosi9TR.getText().toString();
            String onomaAthliti210TRSt = onomaAthliti210TR.getText().toString();
            String epidosi10TRSt = epidosi10TR.getText().toString();

            if(onomaAthlimatosTRSt.isEmpty() && imerAgonaTRSt.isEmpty() && xoraAgonaTRSt.isEmpty() && poliAgonaTRSt.isEmpty() && onomaAthliti2TRSt.isEmpty() &&  epidosiTRSt.isEmpty() && onomaAthliti22TRSt.isEmpty() &&  epidosi2TRSt.isEmpty() && onomaAthliti23TRSt.isEmpty() &&  epidosi3TRSt.isEmpty() && onomaAthliti24TRSt.isEmpty() &&  epidosi4TRSt.isEmpty() && onomaAthliti25TRSt.isEmpty() &&  epidosi5TRSt.isEmpty() && onomaAthliti26TRSt.isEmpty() &&  epidosi6TRSt.isEmpty() && onomaAthliti27TRSt.isEmpty() &&  epidosi7TRSt.isEmpty() && onomaAthliti28TRSt.isEmpty() &&  epidosi8TRSt.isEmpty() && onomaAthliti29TRSt.isEmpty() &&  epidosi9TRSt.isEmpty() && onomaAthliti210TRSt.isEmpty() &&  epidosi10TRSt.isEmpty()){
                dialogAdeiaPedia2();
                return;
            }

            try{
                DocumentReference docref = db.collection("Agonas").document(""+kodikosAgonaTRInt);
                Map<String,Object> map = new HashMap<>();
                if(!onomaAthlimatosTRSt.isEmpty())
                    map.put("onoma", onomaAthlimatosTRSt);
                if(!imerAgonaTRSt.isEmpty())
                    map.put("imerAgona", imerAgonaTRSt);
                if(!xoraAgonaTRSt.isEmpty())
                    map.put("xoraAgona", xoraAgonaTRSt);
                if(!poliAgonaTRSt.isEmpty())
                    map.put("poliAgona", poliAgonaTRSt);

                if(!onomaAthliti2TRSt.isEmpty() &&  !epidosiTRSt.isEmpty()){
                    map.put("onomaAthliti1", onomaAthliti2TRSt);
                    map.put("epidosi1", epidosiTRSt);
                }

                if(!onomaAthliti22TRSt.isEmpty() &&  !epidosi2TRSt.isEmpty()){
                    map.put("onomaAthliti2", onomaAthliti22TRSt);
                    map.put("epidosi2", epidosi2TRSt);
                }

                if(!onomaAthliti23TRSt.isEmpty() &&  !epidosi3TRSt.isEmpty()){
                    map.put("onomaAthliti3", onomaAthliti23TRSt);
                    map.put("epidosi3", epidosi3TRSt);
                }

                if(!onomaAthliti24TRSt.isEmpty() &&  !epidosi4TRSt.isEmpty()){
                    map.put("onomaAthliti4", onomaAthliti24TRSt);
                    map.put("epidosi4", epidosi4TRSt);
                }

                if(!onomaAthliti25TRSt.isEmpty() &&  !epidosi5TRSt.isEmpty()){
                    map.put("onomaAthliti5", onomaAthliti25TRSt);
                    map.put("epidosi5", epidosi5TRSt);
                }

                if(!onomaAthliti26TRSt.isEmpty() &&  !epidosi6TRSt.isEmpty()){
                    map.put("onomaAthliti6", onomaAthliti26TRSt);
                    map.put("epidosi6", epidosi6TRSt);
                }

                if(!onomaAthliti27TRSt.isEmpty() &&  !epidosi7TRSt.isEmpty()){
                    map.put("onomaAthliti7", onomaAthliti27TRSt);
                    map.put("epidosi7", epidosi7TRSt);
                }

                if(!onomaAthliti28TRSt.isEmpty() &&  !epidosi8TRSt.isEmpty()){
                    map.put("onomaAthliti8", onomaAthliti28TRSt);
                    map.put("epidosi8", epidosi8TRSt);
                }

                if(!onomaAthliti29TRSt.isEmpty() &&  !epidosi9TRSt.isEmpty()){
                    map.put("onomaAthliti9", onomaAthliti29TRSt);
                    map.put("epidosi9", epidosi9TRSt);
                }

                if(!onomaAthliti210TRSt.isEmpty() &&  !epidosi10TRSt.isEmpty()){
                    map.put("onomaAthliti10", onomaAthliti210TRSt);
                    map.put("epidosi10", epidosi10TRSt);
                }


                docref.update(map);
            }catch (Exception e){
                dialogSfalma();
                return;
            }

            dialogTropopoiisi2();
            onomaAthliti2TTR.setVisibility(view.INVISIBLE);
            onomaAthliti2TR.setText("");
            epidosiTTR.setVisibility(view.INVISIBLE);
            epidosiTR.setText("");
            onomaAthliti22TTR.setVisibility(view.INVISIBLE);
            onomaAthliti22TR.setText("");
            epidosi2TTR.setVisibility(view.INVISIBLE);
            epidosi2TR.setText("");
            onomaAthliti23TTR.setVisibility(view.INVISIBLE);
            onomaAthliti23TR.setText("");
            epidosi3TTR.setVisibility(view.INVISIBLE);
            epidosi3TR.setText("");
            onomaAthliti24TTR.setVisibility(view.INVISIBLE);
            onomaAthliti24TR.setText("");
            epidosi4TTR.setVisibility(view.INVISIBLE);
            epidosi4TR.setText("");
            onomaAthliti25TTR.setVisibility(view.INVISIBLE);
            onomaAthliti25TR.setText("");
            epidosi5TTR.setVisibility(view.INVISIBLE);
            epidosi5TR.setText("");
            onomaAthliti26TTR.setVisibility(view.INVISIBLE);
            onomaAthliti26TR.setText("");
            epidosi6TTR.setVisibility(view.INVISIBLE);
            epidosi6TR.setText("");
            onomaAthliti27TTR.setVisibility(view.INVISIBLE);
            onomaAthliti27TR.setText("");
            epidosi7TTR.setVisibility(view.INVISIBLE);
            epidosi7TR.setText("");
            onomaAthliti28TTR.setVisibility(view.INVISIBLE);
            onomaAthliti28TR.setText("");
            epidosi8TTR.setVisibility(view.INVISIBLE);
            epidosi8TR.setText("");
            onomaAthliti29TTR.setVisibility(view.INVISIBLE);
            onomaAthliti29TR.setText("");
            epidosi9TTR.setVisibility(view.INVISIBLE);
            epidosi9TR.setText("");
            onomaAthliti210TTR.setVisibility(view.INVISIBLE);
            onomaAthliti210TR.setText("");
            epidosi10TTR.setVisibility(view.INVISIBLE);
            epidosi10TR.setText("");
            imerAgonaTR.setText("");
            xoraAgonaTR.setText("");
            poliAgonaTR.setText("");
            onomaAthlimatosTR.setText("");
            kodikosAgonaTR.setText("");
            imerAgonaTTR.setVisibility(view.INVISIBLE);
            xoraAgonaTTR.setVisibility(view.INVISIBLE);
            poliAgonaTTR.setVisibility(view.INVISIBLE);
            onomaAthlimatosTTR.setVisibility(view.INVISIBLE);
            onomaAthlimatosTR.setText("");
            buttonTropopoiisi2.setVisibility(view.INVISIBLE);

        }

    }

    public void dialogDiagrafi2(){
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialog1.setIcon(R.drawable.baseline_info_24);
        // Setting Dialog Title
        alertDialog1.setTitle("Ειδοποίηση");

        // Setting Dialog Message
        alertDialog1.setMessage("Επιτυχής Διαγραφή");

        // Setting OK Button
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed

            }
        });

        // Showing Alert Message
        alertDialog1.show();
    }
    public void diagrafi2OnClick(View view){
        EditText diagrafi3 = findViewById(R.id.diagrafi3);
        int diagrafi3Int;
        if(diagrafi3.getText().toString().isEmpty()){
            dialogAdeiaPedia();
            return;
        }
        else {
            diagrafi3Int = Integer.parseInt(diagrafi3.getText().toString());
            if (diagrafi3Int < 1){
                dialogkodikos();
                return;
            }
        }
        DocumentReference documentReference = db.collection("Agonas").document("" + diagrafi3Int);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        try {
                            db.collection("Agonas").document("" + diagrafi3Int).delete();
                            diagrafi3.setText("");
                            dialogDiagrafi2();
                        }catch (Exception e){
                            dialogSfalma();
                            return;
                        }
                    }
                    else{
                        dialogDenYparxei();
                        diagrafi3.setText("");
                        return;
                    }
                }
            }
        });

    }
}