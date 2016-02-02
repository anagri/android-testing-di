package co.creativev.bootcamp.got;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "GOT_APP";

//    private GoTListAdapter adapter;
    private GoTListOnlineAdapter adapter;
//    private GoTRecyclerOnlineAdapter goTRecyclerOnlineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
//        adapter = new GoTListAdapter(this, DatabaseHelper.getDatabaseHelper(this));
        adapter = new GoTListOnlineAdapter(this, ((GoTApplication) getApplication()).getGoTService());

//        goTRecyclerOnlineAdapter = new GoTRecyclerOnlineAdapter(this, ((GoTApplication) getApplication()).getGoTService());
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
//        recyclerView.setAdapter(goTRecyclerOnlineAdapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.got_cols)));

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            startActivity(new Intent(this, AddCharacterActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
