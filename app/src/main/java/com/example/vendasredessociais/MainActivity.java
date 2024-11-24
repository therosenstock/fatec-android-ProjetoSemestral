package com.example.vendasredessociais;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        Fragment fragment = this.getFragment(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

    private Fragment getFragment(Bundle bundle) {
        System.out.println("Aquiiii");
        if (bundle == null) return new InicioFragment();
        String tipo = bundle.getString("tipo");

        System.out.println(tipo);
        if (tipo == null) return new InicioFragment();
        switch (tipo) {
            case "items":
                return new ItensFragment();
            case "produtos":
                return new ProdutosFragment();
            case "pedidos":
                return new PedidosFragment();
            case "listapedidos":
                return new ListarPedidosFragment();
            default:
                return new InicioFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, MainActivity.class);

        if (id == R.id.item_itens)
            bundle.putString("tipo", "items");
        else if (id == R.id.item_produtos)
            bundle.putString("tipo", "produtos");
        else if (id == R.id.item_pedidos)
            bundle.putString("tipo", "pedidos");
        else if (id == R.id.item_listapedidos)
            bundle.putString("tipo", "listapedidos");
        else
            return super.onOptionsItemSelected(item);

        intent.putExtras(bundle);
        this.startActivity(intent);
        this.finish();
        return true;
    }
}