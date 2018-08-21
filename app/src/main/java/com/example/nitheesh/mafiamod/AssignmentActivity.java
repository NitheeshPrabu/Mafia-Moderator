package com.example.nitheesh.mafiamod;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssignmentActivity extends AppCompatActivity {
    private static final String ROLES_KEY = "roles";
    private ArrayList<Role> roles;
    private ArrayList<String> playersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.nitheesh.mafiamod.R.layout.activity_assignment);

        ListView roleList = (ListView) findViewById(com.example.nitheesh.mafiamod.R.id.roleList);
        TextView playerTotalText = (TextView) findViewById(com.example.nitheesh.mafiamod.R.id.playersTotalText);

        Bundle extras = getIntent().getExtras();
        playersList = extras.getStringArrayList("playerList");

        playerTotalText.setText(String.format("%d",playersList.size()));

        if (savedInstanceState != null) {
            //noinspection unchecked
            roles = (ArrayList<Role>) savedInstanceState.getSerializable(ROLES_KEY);
        }
        if (roles == null) {
            roles = RoleLoader.loadRoles(getAssets());
        }
        final RoleItemAdapter roleItemAdapter = new RoleItemAdapter(this, roles, playerTotalText);
        roleList.setAdapter(roleItemAdapter);
        findViewById(com.example.nitheesh.mafiamod.R.id.startButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Role> players = createPlayerList(roleItemAdapter.getRoles());
                Collections.shuffle(players);
                if (players.isEmpty()) {
                    Toast.makeText(AssignmentActivity.this, com.example.nitheesh.mafiamod.R.string.no_players_message, Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(AssignmentActivity.this, RoleActivity.class);
                Bundle extras = new Bundle();
                extras.putSerializable(RoleActivity.PLAYERS_KEY, players);
                extras.putSerializable(RoleActivity.PLAYERNAMES_KEY, playersList);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        findViewById(com.example.nitheesh.mafiamod.R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roleItemAdapter.reset();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ROLES_KEY, roles);
    }

    private ArrayList<Role> createPlayerList(List<Role> roles) {
        ArrayList<Role> players = new ArrayList<>();
        for (Role role : roles) {
            for (int i = 0; i < role.getPlayers(); i++) {
                players.add(role);
            }
        }
        return players;
    }
}
