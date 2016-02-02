package co.creativev.bootcamp.got;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "GOT_APP";

//    private GoTAdapter adapter;
    private GoTOnlineAdapter goTOnlineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
//        adapter = new GoTAdapter(this, DatabaseHelper.getDatabaseHelper(this));
        goTOnlineAdapter = new GoTOnlineAdapter(this, ((GoTApplication) getApplication()).getGoTService());
        recyclerView.setAdapter(goTOnlineAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.got_cols)));
    }

    @Override
    protected void onStart() {
        super.onStart();
//        adapter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        adapter.onStop();
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

    public static class GoTAdapter extends RecyclerView.Adapter<GotViewHolder> {
        private final LayoutInflater inflater;
        private final Context context;
        private final DatabaseHelper databaseHelper;
        private Cursor cursor;

        public GoTAdapter(Context context, DatabaseHelper databaseHelper) {
            this.context = context;
            this.databaseHelper = databaseHelper;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public GotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.list_item_got, parent, false);
            return new GotViewHolder(view, ((TextView) view.findViewById(R.id.text)), ((ImageView) view.findViewById(R.id.image_character)));
        }

        @Override
        public void onBindViewHolder(GotViewHolder holder, int position) {
            holder.bindItem(context, getItem(position));
        }

        @Override
        public int getItemCount() {
            return cursor == null || cursor.isClosed() ? 0 : cursor.getCount();
        }

        public GoTCharacter getItem(int position) {
            if (cursor == null) return null;
            cursor.moveToPosition(position);
            return new GoTCharacter(
                    cursor.getInt(cursor.getColumnIndexOrThrow(GoTCharacter._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.FIRST_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.LAST_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.THUMB_URL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.FULL_URL)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(GoTCharacter.ALIVE)) == 1,
                    cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.HOUSE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(GoTCharacter.HOUSE_RES_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.DESCRIPTION))
            );
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).id;
        }

        public void onStart() {
            Log.d(LOG_TAG, "Adapter onStart");
            if (cursor == null || cursor.isClosed())
                cursor = databaseHelper.getCharacterCursor();
            notifyItemRangeChanged(0, cursor.getCount());
        }

        public void onStop() {
            Log.d(LOG_TAG, "Adapter onStop");
            closeCursorIfOpen();
        }

        private void closeCursorIfOpen() {
            if (cursor != null && !cursor.isClosed()) cursor.close();
        }
    }

    public static class GotViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView image;

        public GotViewHolder(View view, TextView name, ImageView image) {
            super(view);
            this.name = name;
            this.image = image;
        }

        public void bindItem(final Context context, final GoTCharacter gotCharacter) {
            this.image.setVisibility(View.VISIBLE);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_CHARACTER, gotCharacter);
                    context.startActivity(intent);
                }
            });
            this.name.setText(gotCharacter.getFirstName() + " " + gotCharacter.getLastName());
            Picasso.with(context)
                    .load(Uri.parse(gotCharacter.getThumbUrl()))
                    .placeholder(R.drawable.profile_placeholder)
                    .error(R.drawable.profile_placeholder_error)
                    .into(this.image);
        }

        public void loadMoreView(View.OnClickListener onClickListener) {
            this.image.setVisibility(View.INVISIBLE);
            this.name.setText("Load More");
            this.itemView.setOnClickListener(onClickListener);
        }
    }
}
