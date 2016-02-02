package co.creativev.bootcamp.got;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GoTOnlineAdapter extends RecyclerView.Adapter<MainActivity.GotViewHolder> {
    private final List<GoTCharacter> characters;
    private final LayoutInflater inflater;
    private final Context context;
    private final GoTService goTService;
    private int page = 0;

    public GoTOnlineAdapter(Context context, GoTService goTService) {
        this.context = context;
        this.goTService = goTService;
        characters = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MainActivity.GotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_got, parent, false);
        return new MainActivity.GotViewHolder(view, ((TextView) view.findViewById(R.id.text)), ((ImageView) view.findViewById(R.id.image_character)));
    }

    @Override
    public void onBindViewHolder(MainActivity.GotViewHolder holder, int position) {
        if (position == getItemCount() - 1) {
            holder.loadMoreView(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadMore();
                }
            });
        } else {
            holder.bindItem(context, characters.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return characters.size() + 1;
    }

    public void loadMore() {
        final ProgressDialog dialog = ProgressDialog.show(context, null, "Loading", true);
        new AsyncTask<Integer, Void, List<GoTCharacter>>() {
            @Override
            protected List<GoTCharacter> doInBackground(Integer... params) {
                try {
                    return goTService.getCharacters(params[0]);
                } catch (Exception e) {
                    Log.e(GoTApplication.LOG_TAG, "Error while fetching data", e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<GoTCharacter> goTCharacter) {
                dialog.dismiss();
                if (goTCharacter == null) {
                    Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                    return;
                }
                characters.addAll(goTCharacter);
                page++;
                notifyDataSetChanged();
            }
        }.execute(page + 1);
    }
}
