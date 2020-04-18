package br.unicamp.ft.c155041.moracmg;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";

    FirebaseUser user;
    Usuario usuario = new Usuario();
    DatabaseHandler dbHandler = new DatabaseHandler();

    private AppBarConfiguration mAppBarConfiguration;
    private TextView text_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_historico, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Meu codigo começa aqui:
        text_home = findViewById(R.id.text_home);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            Log.d(TAG, "user.getInstance:failure");
            printToast("Por favor faça login.");
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        if (user != null ){
            Log.d(TAG, "user.getInstance:successful");
            Log.d(TAG, "user: " + user);
            String email = user.getEmail();
            Log.d(TAG, "email: " + email);


            String ra = usuario.calculaRA(email);
            Log.d(TAG, "ra: " + ra);


            //TODO Bug do Paralelismo: Aqui precisa resolver o bug do paralelismo pra continuar
            /*
            * Fluxo:
            * Main activity recebe usuário do banco
            * se tiver com os dados obrigatórios preenchidos, libera o acesso ao app
            * se tiver com os dados obrigatorios em branco, é redirecionado pra Editar Perfil
            *
            * */
            this.usuario = dbHandler.getUserFromDatabase(ra);
            Log.d(TAG, "usuario.email: " + usuario.getEmail());

        }
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
    //Metodo que controla o menu do canto superior direito
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_sair) { //Selecao da opção sair no menu lateral
            //printToast("Pressionou sair");
            Log.d(TAG,"onOptionsItemSelected:action_sair");

            FirebaseAuth.getInstance().signOut();
            Log.d(TAG,"FirebaseAuth:signOut");

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            Log.d(TAG, "starting:LoginActivity");
        }

        return super.onOptionsItemSelected(item);
    }

    public void printToast(String msg){
        Toast.makeText(this, msg,Toast.LENGTH_LONG).show();
    }
}
