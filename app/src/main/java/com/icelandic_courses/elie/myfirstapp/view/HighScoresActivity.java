package com.icelandic_courses.elie.myfirstapp.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.icelandic_courses.elie.myfirstapp.R;
import com.icelandic_courses.elie.myfirstapp.logic.GameMode;
import com.icelandic_courses.elie.myfirstapp.logic.time.TimedLogic;
import com.icelandic_courses.elie.myfirstapp.score.HighScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HighScoresActivity extends Activity {

    private ListView highScoreListView;
    private ArrayList<HighScore> highScores = new ArrayList<HighScore>();
    private HighScoreAdapter highScoreAdapter;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        highScoreListView = (ListView) findViewById(R.id.highScores);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        highScores.add(new HighScore(GameMode.CLASSIC.toString(), prefs.getInt(GameMode.CLASSIC.toString(), 0)));
        highScores.add(new HighScore(GameMode.MOVES.toString(), prefs.getInt(GameMode.MOVES.toString(), 0)));

        highScoreAdapter = new HighScoreAdapter(this, highScores);
        highScoreListView.setAdapter(highScoreAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_high_scores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class HighScoreAdapter extends ArrayAdapter<HighScore> {

        private final Context context;
        private final List<HighScore> values;

        public HighScoreAdapter(Context context, List<HighScore> objects) {
            super(context, -1, objects);
            this.context = context;
            this.values = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.high_scores_row, parent, false);

            TextView gameModeView = (TextView) rowView.findViewById(R.id.gameMode);
            gameModeView.setText(values.get(position).getGameMode());

            TextView gameScoreView = (TextView) rowView.findViewById(R.id.gameScore);
            gameScoreView.setText(String.valueOf(values.get(position).getHighScore()));

            return rowView;
        }
    }
}