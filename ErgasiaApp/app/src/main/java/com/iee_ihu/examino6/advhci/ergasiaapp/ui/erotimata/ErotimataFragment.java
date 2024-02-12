package com.iee_ihu.examino6.advhci.ergasiaapp.ui.erotimata;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.iee_ihu.examino6.advhci.ergasiaapp.Agonas;
import com.iee_ihu.examino6.advhci.ergasiaapp.MainActivity;
import com.iee_ihu.examino6.advhci.ergasiaapp.Query1Type;
import com.iee_ihu.examino6.advhci.ergasiaapp.Query1Type2;
import com.iee_ihu.examino6.advhci.ergasiaapp.Query2Type;
import com.iee_ihu.examino6.advhci.ergasiaapp.R;
import com.iee_ihu.examino6.advhci.ergasiaapp.ui.diagrafi.DiagrafiViewModel;

import java.util.Arrays;
import java.util.List;

public class ErotimataFragment extends Fragment {

    private ErotimataViewModel erotimataViewModel;
    String st2 = "";
    String st3 = "";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        erotimataViewModel =
                new ViewModelProvider(this).get(ErotimataViewModel.class);
        View root = inflater.inflate(R.layout.erotimata_fragment, container, false);
        /*final TextView textView = root.findViewById(R.id.text_erotimata);
        erotimataViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        Spinner spinner = root.findViewById(R.id.spErotimata);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.spinnerList, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String st = "";
                st2 = "";
                st3 = "";
                TextView txtquery = root.findViewById(R.id.txtquery);
                txtquery.setText("");
                switch (position) {
                    case 0:
                        break;
                    case 1:

                        List<Query1Type> list = MainActivity.ergDB.mydaotemp().query1();
                        for(int i = 0; i < list.size(); i++) {
                            int idAthlimatosInt = list.get(i).getIdAthlimatos();
                            String onomaAthlimatosSt = list.get(i).getOnomaAthlimatos();
                            String fylloAthlimatosSt = list.get(i).getFylloAthlimatos();
                            String eidos = list.get(i).getEidosAthlimatos();
                            String onomaOmadasSt = list.get(i).getOnomaOmadas();
                            String poliOmadasSt = list.get(i).getPoliOmadas();
                            String xoraOmadasSt = list.get(i).getXoraOmadas();
                            String onomaGipedouSt = list.get(i).getOnomaGipedou();
                            int etosOmadasInt = list.get(i).getEtosOmadas();

                            st = st + " " + "Κωδικός Αθλήματος: " + idAthlimatosInt + " " + "Όνομα Αθλήματος: " + onomaAthlimatosSt + " " + "Φύλλο Αθλήματος: " + fylloAthlimatosSt + " " + "Είδος Αθλήματος: " + eidos + " " + "\n" + "Όνομα Ομάδας: " + onomaOmadasSt + " " + "Πόλη Ομάδας: " + poliOmadasSt + " " + "Χώρα Ομάδας: " + xoraOmadasSt + " " + "Όνομα Γηπέδου: " + onomaGipedouSt + " " + "Έτος Ίδρυσης: " + etosOmadasInt + "\n";
                        }
                        txtquery.setText(st);

                        List<Query1Type2> listType2 = MainActivity.ergDB.mydaotemp().query1type2();
                        for (int i = 0; i < listType2.size();i++){
                            int idAthlimatosInt = listType2.get(i).getIdAthlimatos();
                            String onomaAthlimatosSt = listType2.get(i).getOnomaAthlimatos();
                            String fylloAthlimatosSt = listType2.get(i).getFylloAthlimatos();
                            String eidos = listType2.get(i).getEidosAthlimatos();
                            String eponimoAthlitiSt = listType2.get(i).getEponimoAthliti();
                            String onomaAthlitiSt = listType2.get(i).getOnomaAthliti();
                            String poliAthlitiSt = listType2.get(i).getPoliAthliti();
                            String xoraAthlitiSt = listType2.get(i).getXoraAthliti();
                            int etosAthlitiInt = listType2.get(i).getEtosAthliti();
                            st = st + " " + "Κωδικός Αθλήματος: " + idAthlimatosInt + " " + "Όνομα Αθλήματος: " + onomaAthlimatosSt + " " + "Φύλλο Αθλήματος: " + fylloAthlimatosSt + " " + "Είδος Αθλήματος: " + eidos + " " + "Επώνυμο Αθλητή: " + eponimoAthlitiSt + " " + "Όνομα Αθλητή: " + onomaAthlitiSt + " " + "Πόλη Αθλητή: " + poliAthlitiSt + " " + "Χώρα Αθλητή: " + xoraAthlitiSt + " " + "Έτος Γέννησης: " + etosAthlitiInt + "\n";
                        }
                        txtquery.setText("\n" + st);

                        break;
                    case 2:
                        List<Query2Type> listQuery2 = MainActivity.ergDB.mydaotemp().query2();
                        for (int i = 0; i < listQuery2.size(); i++){
                            String eponimo = listQuery2.get(i).getEponimoAthliti();
                            String onoma = listQuery2.get(i).getOnomaAthliti();
                            st = st + " " + "Επώνυμο Αθλητή: " + eponimo + " " + "Όνομα Αθλητή: " + onoma + "\n";
                        }
                        txtquery.setText("\n" + st);
                        break;
                    case 3:
                        List<String> listQuery3 = MainActivity.ergDB.mydaotemp().query3();
                        for (int i = 0; i < listQuery3.size(); i++){
                            String onoma = listQuery3.get(i);
                            st = st + " " + "Όνομα Ομάδας: " + onoma + "\n";
                        }
                        txtquery.setText("\n" + st);
                        break;
                    case 4:
                        CollectionReference collectionReference = MainActivity.db.collection("Agonas");
                        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                           @Override
                           public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                               for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                   Agonas agonas = documentSnapshot.toObject(Agonas.class);
                                   String onoma = agonas.getOnoma();
                                   String imer = agonas.getImerAgona();
                                   String poli = agonas.getPoliAgona();
                                   String xora = agonas.getXoraAgona();
                                   String omada1 = agonas.getOmada1();
                                   String omada2 = agonas.getOmada2();
                                   String score = agonas.getScore();

                                   if(!omada1.equals("n") && !omada2.equals("n") && !score.equals("n")){
                                       st2 = st2 + " " + "Όνομα Αθλήματος: " + onoma + " " + "Ημερομηνία: " + imer + " " + "Πόλη: " + poli + " " + "Χώρα: " + xora + " "  + "Ομάδα 1: "  + omada1 + " " + "Ομάδα 2: " + omada2 + " " + "Σκορ: " + score + " " + "\n";
                                   }
                                   else{
                                       st2 = "\n" + st2 + " " + "Όνομα Αθλήματος: " + onoma + " " + "Ημερομηνία: " + imer + " " + "Πόλη: " + poli + " " + "Χώρα: " + xora + "\n";
                                       String onomaAthliti1 = agonas.getOnomaAthliti1();
                                       String epidosi1 = agonas.getEpidosi1();
                                       if(!onomaAthliti1.equals("n") && !epidosi1.equals("n")){
                                           st2 = st2 + " " + "Αθλητής 1: " + onomaAthliti1 + " " + "Επίδοση: " + epidosi1 + " " + "\n";
                                       }

                                       String onomaAthliti2 = agonas.getOnomaAthliti2();
                                       String epidosi2 = agonas.getEpidosi2();
                                       if(!onomaAthliti2.equals("n") && !epidosi2.equals("n")){
                                           st2 = st2 + " " + "Αθλητής 2: " + onomaAthliti2 + " " + "Επίδοση: " + epidosi2 + " " + "\n";
                                       }


                                       String onomaAthliti3 = agonas.getOnomaAthliti3();
                                       String epidosi3 = agonas.getEpidosi3();

                                       if(!onomaAthliti3.equals("n") && !epidosi3.equals("n")){
                                           st2 = st2 + " " + "Αθλητής 3: " + onomaAthliti3 + " " + "Επίδοση: " + epidosi3 + " " + "\n";
                                       }

                                       String onomaAthliti4 = agonas.getOnomaAthliti4();
                                       String epidosi4 = agonas.getEpidosi4();

                                       if(!onomaAthliti4.equals("n") && !epidosi4.equals("n")){
                                           st2 = st2 + " " + "Αθλητής 4: " + onomaAthliti4 + " " + "Επίδοση: " + epidosi4 + " " + "\n";
                                       }

                                       String onomaAthliti5 = agonas.getOnomaAthliti5();
                                       String epidosi5 = agonas.getEpidosi5();

                                       if(!onomaAthliti5.equals("n") && !epidosi5.equals("n")){
                                           st2 = st2 + " " + "Αθλητής 5: " + onomaAthliti5 + " " + "Επίδοση: " + epidosi5 + " " + "\n";
                                       }

                                       String onomaAthliti6 = agonas.getOnomaAthliti6();
                                       String epidosi6 = agonas.getEpidosi6();

                                       if(!onomaAthliti6.equals("n") && !epidosi6.equals("n")){
                                           st2 = st2 + " " + "Αθλητής 6: " + onomaAthliti6 + " " + "Επίδοση: " + epidosi6 + " " + "\n";
                                       }

                                       String onomaAthliti7 = agonas.getOnomaAthliti7();
                                       String epidosi7 = agonas.getEpidosi7();

                                       if(!onomaAthliti7.equals("n") && !epidosi7.equals("n")){
                                           st2 = st2 + " " + "Αθλητής 7: " + onomaAthliti7 + " " + "Επίδοση: " + epidosi7 + " " + "\n";
                                       }

                                       String onomaAthliti8 = agonas.getOnomaAthliti8();
                                       String epidosi8 = agonas.getEpidosi8();

                                       if(!onomaAthliti8.equals("n") && !epidosi8.equals("n")){
                                           st2 = st2 + " " + "Αθλητής 8: " + onomaAthliti8 + " " + "Επίδοση: " + epidosi8 + " " + "\n";
                                       }

                                       String onomaAthliti9 = agonas.getOnomaAthliti9();
                                       String epidosi9 = agonas.getEpidosi9();

                                       if(!onomaAthliti9.equals("n") && !epidosi9.equals("n")){
                                           st2 = st2 + " " + "Αθλητής 9: " + onomaAthliti9 + " " + "Επίδοση: " + epidosi9 + " " + "\n";
                                       }

                                       String onomaAthliti10 = agonas.getOnomaAthliti10();
                                       String epidosi10 = agonas.getEpidosi10();

                                       if(!onomaAthliti10.equals("n") && !epidosi10.equals("n")){
                                           st2 = st2 + " " + "Αθλητής 10: " + onomaAthliti10 + " " + "Επίδοση: " + epidosi10 + " " + "\n";
                                       }
                                   }

                               }


                               txtquery.setText("\n" + st2);

                           }
                       });

                        break;
                    case 5:
                        CollectionReference collectionReference2 = MainActivity.db.collection("Agonas");
                        collectionReference2.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Agonas agonas = documentSnapshot.toObject(Agonas.class);
                                    String onoma = agonas.getOnoma();
                                    String imer = agonas.getImerAgona();
                                    String poli = agonas.getPoliAgona();
                                    String xora = agonas.getXoraAgona();

                                    String omada1 = agonas.getOmada1();
                                    String omada2 = agonas.getOmada2();
                                    String score = agonas.getScore();

                                    if(omada1.equals("n") && omada2.equals("n") && score.equals("n")) {
                                        if (xora.equals("Ισπανία")) {
                                            st2 = "\n" + st2 + " " + "Όνομα Αθλήματος: " + onoma + " " + "Ημερομηνία: " + imer + " " + "Πόλη: " + poli + " " + "Χώρα: " + xora + "\n";
                                            String onomaAthliti1 = agonas.getOnomaAthliti1();
                                            String epidosi1 = agonas.getEpidosi1();
                                            if (!onomaAthliti1.equals("n") && !epidosi1.equals("n")) {
                                                st2 = st2 + " " + "Αθλητής 1: " + onomaAthliti1 + " " + "Επίδοση: " + epidosi1 + " " + "\n";
                                            }

                                            String onomaAthliti2 = agonas.getOnomaAthliti2();
                                            String epidosi2 = agonas.getEpidosi2();
                                            if (!onomaAthliti2.equals("n") && !epidosi2.equals("n")) {
                                                st2 = st2 + " " + "Αθλητής 2: " + onomaAthliti2 + " " + "Επίδοση: " + epidosi2 + " " + "\n";
                                            }


                                            String onomaAthliti3 = agonas.getOnomaAthliti3();
                                            String epidosi3 = agonas.getEpidosi3();

                                            if (!onomaAthliti3.equals("n") && !epidosi3.equals("n")) {
                                                st2 = st2 + " " + "Αθλητής 3: " + onomaAthliti3 + " " + "Επίδοση: " + epidosi3 + " " + "\n";
                                            }

                                            String onomaAthliti4 = agonas.getOnomaAthliti4();
                                            String epidosi4 = agonas.getEpidosi4();

                                            if (!onomaAthliti4.equals("n") && !epidosi4.equals("n")) {
                                                st2 = st2 + " " + "Αθλητής 4: " + onomaAthliti4 + " " + "Επίδοση: " + epidosi4 + " " + "\n";
                                            }

                                            String onomaAthliti5 = agonas.getOnomaAthliti5();
                                            String epidosi5 = agonas.getEpidosi5();

                                            if (!onomaAthliti5.equals("n") && !epidosi5.equals("n")) {
                                                st2 = st2 + " " + "Αθλητής 5: " + onomaAthliti5 + " " + "Επίδοση: " + epidosi5 + " " + "\n";
                                            }

                                            String onomaAthliti6 = agonas.getOnomaAthliti6();
                                            String epidosi6 = agonas.getEpidosi6();

                                            if (!onomaAthliti6.equals("n") && !epidosi6.equals("n")) {
                                                st2 = st2 + " " + "Αθλητής 6: " + onomaAthliti6 + " " + "Επίδοση: " + epidosi6 + " " + "\n";
                                            }

                                            String onomaAthliti7 = agonas.getOnomaAthliti7();
                                            String epidosi7 = agonas.getEpidosi7();

                                            if (!onomaAthliti7.equals("n") && !epidosi7.equals("n")) {
                                                st2 = st2 + " " + "Αθλητής 7: " + onomaAthliti7 + " " + "Επίδοση: " + epidosi7 + " " + "\n";
                                            }

                                            String onomaAthliti8 = agonas.getOnomaAthliti8();
                                            String epidosi8 = agonas.getEpidosi8();

                                            if (!onomaAthliti8.equals("n") && !epidosi8.equals("n")) {
                                                st2 = st2 + " " + "Αθλητής 8: " + onomaAthliti8 + " " + "Επίδοση: " + epidosi8 + " " + "\n";
                                            }

                                            String onomaAthliti9 = agonas.getOnomaAthliti9();
                                            String epidosi9 = agonas.getEpidosi9();

                                            if (!onomaAthliti9.equals("n") && !epidosi9.equals("n")) {
                                                st2 = st2 + " " + "Αθλητής 9: " + onomaAthliti9 + " " + "Επίδοση: " + epidosi9 + " " + "\n";
                                            }

                                            String onomaAthliti10 = agonas.getOnomaAthliti10();
                                            String epidosi10 = agonas.getEpidosi10();

                                            if (!onomaAthliti10.equals("n") && !epidosi10.equals("n")) {
                                                st2 = st2 + " " + "Αθλητής 10: " + onomaAthliti10 + " " + "Επίδοση: " + epidosi10 + " " + "\n";
                                            }

                                        }
                                    }
                                }


                                txtquery.setText("\n" + st2);

                            }
                        });
                        break;
                    case 6:
                        CollectionReference collectionReference3 = MainActivity.db.collection("Agonas");
                        collectionReference3.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Agonas agonas = documentSnapshot.toObject(Agonas.class);
                                    String onoma = agonas.getOnoma();
                                    String imer = agonas.getImerAgona();
                                    String poli = agonas.getPoliAgona();
                                    String xora = agonas.getXoraAgona();
                                    String omada1 = agonas.getOmada1();
                                    String omada2 = agonas.getOmada2();
                                    String score = agonas.getScore();

                                    if (!omada1.equals("n") && !omada2.equals("n") && !score.equals("n")) {
                                        if(onoma.equals("Ποδόσφαιρο") && poli.equals("Αθήνα")){
                                            st2 = st2 + " " + "Όνομα Αθλήματος: " + onoma + " " + "Ημερομηνία: " + imer + " " + "Πόλη: " + poli + " " + "Χώρα: " + xora + " "  + "Ομάδα 1: "  + omada1 + " " + "Ομάδα 2: " + omada2 + " " + "Σκορ: " + score + " " + "\n";
                                        }
                                    }
                                }
                                txtquery.setText("\n" + st2);
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }

}