package co.creativev.bootcamp.got;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static co.creativev.bootcamp.got.GoTApplication.LOG_TAG;

public class GoTListAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final DatabaseHelper databaseHelper;
    private final Context context;
    private Cursor cursor;

    public GoTListAdapter(Context context, DatabaseHelper databaseHelper) {
        this.context = context;
        this.databaseHelper = databaseHelper;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    @Override
    public GoTCharacter getItem(int position) {
        if (cursor == null) return null;
        cursor.moveToPosition(position);
        return new GoTCharacter(
                cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.FIRST_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.LAST_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.FULL_URL)),
                cursor.getInt(cursor.getColumnIndexOrThrow(GoTCharacter.ALIVE)) == 1,
                cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.HOUSE)),
                cursor.getInt(cursor.getColumnIndexOrThrow(GoTCharacter.HOUSE_RES_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.DESCRIPTION)),
                cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.THUMB_URL))
        );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_got, parent, false);
            TextView name = (TextView) convertView.findViewById(R.id.text);
            ImageView image = (ImageView) convertView.findViewById(R.id.image_character);
            convertView.setTag(new GotViewHolder(name, image));
        }
        GoTCharacter item = getItem(position);
        GotViewHolder tag = (GotViewHolder) convertView.getTag();
        tag.name.setText(item.getFirstName() + " " + item.getLastName());
        Picasso.with(context)
                .load(Uri.parse(item.getThumbUrl()))
                .placeholder(R.drawable.profile_placeholder)
                .error(R.drawable.profile_placeholder_error)
                .into(tag.image);
        return convertView;
    }

    public void onStart() {
        Log.d(LOG_TAG, "Adapter onStart");
        closeCursorIfOpen();
        cursor = databaseHelper.getCharacterCursor();
        notifyDataSetChanged();
    }

    public void onStop() {
        Log.d(LOG_TAG, "Adapter onStop");
        closeCursorIfOpen();
    }

    private void closeCursorIfOpen() {
        if (cursor != null && !cursor.isClosed()) cursor.close();
    }

    public static class GotViewHolder {
        private final TextView name;
        private final ImageView image;

        public GotViewHolder(TextView name, ImageView image) {
            this.name = name;
            this.image = image;
        }
    }
}


