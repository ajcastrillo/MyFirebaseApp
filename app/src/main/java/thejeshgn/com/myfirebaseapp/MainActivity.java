package thejeshgn.com.myfirebaseapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;

import thejeshgn.com.myfirebaseapp.model.Persona;
import thejeshgn.com.myfirebaseapp.model.Prueba;
import thejeshgn.com.myfirebaseapp.model.Servicio;

public class MainActivity extends AppCompatActivity {

    private List<Persona> ListPersona = new ArrayList<Persona>();
    private List<Servicio> ListServicio = new ArrayList<Servicio>();
    ArrayAdapter<Persona> arrayAdapterPersona;
    ArrayAdapter<Servicio> arrayAdapterServicio;

    EditText nomP, appP,correoP,passwordP;
    ListView listV_personas;
    ListView listV_servicio;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Persona personaSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomP =findViewById(R.id.txt_nombrePersona);
        appP =findViewById(R.id.txt_appPersona);
        correoP =findViewById(R.id.txt_correoPersona);
        passwordP =findViewById(R.id.txt_passwordPersona);

        listV_personas = findViewById(R.id.lv_datosPersona);

        // inicializar firebase
        inicializarFirebase();
        // ----------
        listarDatos();

        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                personaSelected =(Persona)adapterView.getItemAtPosition(i);
                nomP.setText(personaSelected.getNombre());
                appP.setText(personaSelected.getApellido());
                correoP.setText(personaSelected.getCorreo());
                passwordP.setText(personaSelected.getPassword());
            }
        });


    }

    // listado de los datos de personas
    private void listarDatos() {
        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ListPersona.clear();
                ListServicio.clear();
                for (DataSnapshot objSnaptahot : dataSnapshot.getChildren()){
                    Servicio ser = objSnaptahot.getValue(Servicio.class);

                    //Persona p = objSnaptahot.getValue(Persona.class);
                    //ListPersona.add(p);
                    ListServicio.add(ser);

                    //arrayAdapterPersona = new ArrayAdapter<Persona>(MainActivity.this, android.R.layout.simple_list_item_1, ListPersona);
                    arrayAdapterServicio = new ArrayAdapter<Servicio>(MainActivity.this, android.R.layout.simple_list_item_1, ListServicio);
                    listV_personas.setAdapter(arrayAdapterServicio);
                    //listV_personas.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
//        este codigo toca agregarlo de manera global en todo el proyecto para eso se crea una clase aparte
//        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    // se vincula el menu en nuestro layout activity main
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // seleccion de una opción en el menú
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String nombre = nomP.getText().toString();
        String apellido = appP.getText().toString();
        String correo = correoP.getText().toString();
        String password = passwordP.getText().toString();

        switch (item.getItemId()){
            case R.id.icon_add:{
                if(nombre.equals("") || apellido.equals("") || correo.equals("") || password.equals("")){
                    validacion();

                } else {
                    Persona p = new Persona();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setApellido(apellido);
                    p.setCorreo(correo);
                    p.setPassword(password);
                    //se van a crear los nodos

                    Servicio s = new Servicio();
                    s.setMAT("");

                    List<Integer> lista = new ArrayList<Integer>();
                    lista.add(3);
                    lista.add(3);
                    lista.add(3);
                    List<String> listavalores = new ArrayList<String>();

                    Map<String, Object> result1 = new HashMap<String, Object>();

                    List<Double> listaTa = new ArrayList<Double>();
                    listaTa.add(25.35);
                    listaTa.add(22.56);
                    listaTa.add(21.89);

                    result1.put("Ta",listaTa);

                    List<Double> listaPa = new ArrayList<Double>();
                    listaPa.add(906.321);
                    listaPa.add(905.6);
                    listaPa.add(906.32);

                    result1.put("Pa",listaPa);

                    Map<String, Object> result2 = new HashMap<String, Object>();

                    List<Double> listaTa2 = new ArrayList<Double>();
                    listaTa2.add(24.35);
                    listaTa2.add(25.56);
                    listaTa2.add(26.89);

                    result2.put("Ta",listaTa2);

                    List<Double> listaPa2 = new ArrayList<Double>();
                    listaPa2.add(926.321);
                    listaPa2.add(935.6);
                    listaPa2.add(916.32);
                    result2.put("Pa",listaPa2);

                    Map<String, Object> result3 = new HashMap<String, Object>();

                    List<Double> listaTa3 = new ArrayList<Double>();
                    listaTa3.add(23.35);
                    listaTa3.add(23.56);
                    listaTa3.add(26.89);

                    result3.put("Ta",listaTa3);

                    List<Double> listaPa3 = new ArrayList<Double>();
                    listaPa3.add(936.321);
                    listaPa3.add(933.6);
                    listaPa3.add(936.32);
                    result3.put("Pa",listaPa3);

                    Map<String, Object> result = new HashMap<String, Object>();


                    result.put("SERV", "VERIF00090819");
                    result.put("MAT", "123654789ANX");
                    result.put("SN", "I47211005606");
                    result.put("prueba", lista);
                    result.put("punto1", result1);
                    result.put("punto2", result2);
                    result.put("punto3", result3);

              //      result.put("author", author);
                //    result.put("title", title);
                  //  result.put("body", body);
                  //  result.put("starCount", starCount);
                  //  result.put("stars", stars);
                   // databaseReference.child("Persona").child(p.getUid()).setValue(p);

                    Servicio se = new Servicio();
                    se.setMAT("VERIF00090819");
                    se.setSERV("123654789ANX");
                    se.setSN("I47211005606");
                    Prueba obj1 = new Prueba();
                    obj1.setCaudal("caudal1");
                    obj1.setRepeticiones(3);
                    Prueba obj2 = new Prueba();
                    obj2.setCaudal("caudal2");
                    obj2.setRepeticiones(2);
                    Prueba obj3 = new Prueba();
                    obj3.setCaudal("caudal3");
                    obj3.setRepeticiones(3);
                    List<Prueba> objprueba = new ArrayList<Prueba>();
                    objprueba.add(obj1);
                    objprueba.add(obj2);
                    objprueba.add(obj3);
                    se.setListaPrueba(objprueba);
                    se.setUID(UUID.randomUUID().toString());

                    databaseReference.child("Persona").child(se.getUID()).setValue(se);
                    Toast.makeText(this,"Agregado", Toast.LENGTH_LONG).show();
                   limpiarCajas();

                }
                break;
            }
            case R.id.icon_save:{
                Persona p = new Persona();
                p.setUid(personaSelected.getUid());
                // para ignorar espacios en blanco
                p.setNombre(nomP.getText().toString().trim());
                p.setApellido(appP.getText().toString().trim());
                p.setCorreo(correoP.getText().toString().trim());
                p.setPassword(passwordP.getText().toString().trim());
                databaseReference.child("Persona").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Actualizado", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            case R.id.icon_delete:{
                Persona p = new Persona();
                p.setUid(personaSelected.getUid());
                databaseReference.child("Persona").child(p.getUid()).removeValue();
                Toast.makeText(this,"Eliminado", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            default:break;

        }
        return super.onOptionsItemSelected(item);

    }

    private void limpiarCajas() {
        nomP.setText("");
        correoP.setText("");
        appP.setText("");
        passwordP.setText("");
    }


//    validar si el formulario de creación esta correcto
    private void validacion() {
        String nombre = nomP.getText().toString();
        String apellido = appP.getText().toString();
        String correo = correoP.getText().toString();
        String password = passwordP.getText().toString();
        if(nombre.equals("")){
            nomP.setError("Required");
        }else if(apellido.equals("")){
            appP.setError("Required");
        }else if(correo.equals("")){
            correoP.setError("Required");
        }else if(password.equals("")){
            passwordP.setError("Required");
        }
    }
}
