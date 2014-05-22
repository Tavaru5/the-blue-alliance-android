package com.thebluealliance.androidclient.background.event;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.thebluealliance.androidclient.Constants;
import com.thebluealliance.androidclient.R;
import com.thebluealliance.androidclient.activities.NavigationDrawerActivity;
import com.thebluealliance.androidclient.activities.RefreshableHostActivity;
import com.thebluealliance.androidclient.comparators.MatchSortByPlayOrderComparator;
import com.thebluealliance.androidclient.comparators.TeamSortByOPRComparator;
import com.thebluealliance.androidclient.datafeed.DataManager;
import com.thebluealliance.androidclient.datatypes.APIResponse;
import com.thebluealliance.androidclient.models.Event;
import com.thebluealliance.androidclient.models.Match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * File created by phil on 4/22/14.
 */
public class PopulateEventInfo extends AsyncTask<String, String, APIResponse.CODE> {

    private Fragment mFragment;
    private RefreshableHostActivity activity;
    View last, next;
    LinearLayout nextLayout, lastLayout, topTeams, topOpr;
    TextView eventName, eventDate, eventLoc, ranks, stats;
    String eventKey;
    Event event;
    private boolean showLastMatch, showNextMatch, showRanks, showStats;

    public PopulateEventInfo(Fragment f) {
        mFragment = f;
        activity = (RefreshableHostActivity) mFragment.getActivity();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showLastMatch = showNextMatch = showRanks = showStats = false;
    }

    @Override
    protected APIResponse.CODE doInBackground(String... params) {
        eventKey = params[0];

        View view = mFragment.getView();
        if (view != null && mFragment.getActivity() != null) {
            eventName = (TextView) view.findViewById(R.id.event_name);
            eventDate = (TextView) view.findViewById(R.id.event_date);
            eventLoc = (TextView) view.findViewById(R.id.event_location);
            nextLayout = (LinearLayout) view.findViewById(R.id.event_next_match_container);
            lastLayout = (LinearLayout) view.findViewById(R.id.event_last_match_container);
            topTeams = (LinearLayout) view.findViewById(R.id.event_top_teams_container);
            topOpr = (LinearLayout) view.findViewById(R.id.top_opr_container);

            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            try {
                APIResponse<Event> response = DataManager.getEvent(activity, eventKey);
                event = response.getData();
                //return response.getCode();
            } catch (DataManager.NoDataException e) {
                Log.w(Constants.LOG_TAG, "unable to load event info");
                return APIResponse.CODE.NODATA;
            }

            if (event.hasStarted()) {
                //event has started (may or may not have finished).
                //show the ranks and stats
                showRanks = showStats = true;
                ranks = new TextView(activity);
                try {
                    APIResponse<ArrayList<JsonArray>> rankResponse = DataManager.getEventRankings(activity, eventKey);
                    ArrayList<JsonArray> rankList = rankResponse.getData();
                    String rankString = "";
                    for (int i = 1; i < Math.min(6, rankList.size()); i++) {
                        rankString += ((i) + ". " + rankList.get(i).get(1).getAsString()) + "\n";
                    }
                    ranks.setText(rankString);
                } catch (DataManager.NoDataException e) {
                    Log.w(Constants.LOG_TAG, "Unable to load event rankings");
                    return APIResponse.CODE.NODATA;
                }

                stats = new TextView(activity);
                try {
                    APIResponse<JsonObject> statsResponse = DataManager.getEventStats(activity, eventKey);
                    ArrayList<Map.Entry<String, JsonElement>> opr = new ArrayList<>();
                    if(statsResponse.getData().has("oprs")) {
                        opr.addAll(statsResponse.getData().get("oprs").getAsJsonObject().entrySet());
                        Collections.sort(opr, new TeamSortByOPRComparator());
                        String statsString = "";
                        for (int i = 0; i < Math.min(5, opr.size()); i++) {
                            statsString += ((i + 1) + ". " + opr.get(i).getKey() + "\n");
                        }
                        stats.setText(statsString);
                    }
                } catch (DataManager.NoDataException e) {
                    Log.w(Constants.LOG_TAG, "unable to load event stats");
                    return APIResponse.CODE.NODATA;
                }
            }

            if (event.isHappeningNow()) {
                //show the next/last matches, if applicable
                try {
                    APIResponse<ArrayList<Match>> matchResult = DataManager.getMatchList(activity, eventKey);
                    ArrayList<Match> matches = matchResult.getData();
                    Collections.sort(matches, new MatchSortByPlayOrderComparator());
                    Match nextMatch = Match.getNextMatchPlayed(matches);
                    Match lastMatch = Match.getLastMatchPlayed(matches);

                    if (nextMatch != null) {
                        showNextMatch = true;
                        next = nextMatch.render().getView(activity, inflater, null);
                    }
                    if (lastMatch != null) {
                        showLastMatch = true;
                        last = lastMatch.render().getView(activity, inflater, null);
                    }
                } catch (DataManager.NoDataException e) {
                    Log.w(Constants.LOG_TAG, "unable to load match list");
                    return APIResponse.CODE.NODATA;
                }
            }

            view.findViewById(R.id.event_location_container).setTag("geo:0,0?q=" + event.getLocation().replace(" ", "+"));
            view.findViewById(R.id.event_website_button).setTag(!event.getWebsite().isEmpty()?event.getWebsite():"https://www.google.com/search?q="+event.getEventName());
            view.findViewById(R.id.event_twitter_button).setTag("https://twitter.com/search?q=%23"+event.getEventKey());
            view.findViewById(R.id.event_youtube_button).setTag("https://www.youtube.com/results?search_query="+event.getEventKey());
            view.findViewById(R.id.event_cd_button).setTag("http://www.chiefdelphi.com/media/photos/tags/"+event.getEventKey());
        }
        return APIResponse.CODE.NODATA;
    }

    @Override
    protected void onPostExecute(APIResponse.CODE c) {
        super.onPostExecute(c);
        // If the activity is a NavigationDrawerActivity, set the action bar title using this method
        // so that it properly handles changing the title when the nav drawer is opened or closed.
        if(activity instanceof NavigationDrawerActivity) {
            ((NavigationDrawerActivity) activity).setActionBarTitle(event.getEventName());
        } else {
            activity.getActionBar().setTitle(event.getEventName());
        }
        if (event != null && mFragment.getActivity() != null) {
            eventName.setText(event.getEventName());
            eventDate.setText(event.getDateString());
            eventLoc.setText(event.getLocation());
            if (showNextMatch) {
                nextLayout.setVisibility(View.VISIBLE);
                if(nextLayout.getChildCount() > 1) {
                    nextLayout.removeViewAt(1);
                }
                nextLayout.addView(next);
            }
            if (showLastMatch) {
                lastLayout.setVisibility(View.VISIBLE);
                if(lastLayout.getChildCount() > 1) {
                    lastLayout.removeViewAt(1);
                }
                lastLayout.addView(last);
            }
            if (showRanks) {
                topTeams.setVisibility(View.VISIBLE);
                if(topTeams.getChildCount() > 1) {
                    topTeams.removeViewAt(1);
                }
                topTeams.addView(ranks);
            }

            if (showStats) {
                topOpr.setVisibility(View.VISIBLE);
                if(topOpr.getChildCount() > 1) {
                    topOpr.removeViewAt(1);
                }
                topOpr.addView(stats);
            }

            if (c == APIResponse.CODE.OFFLINECACHE /* && event is current */) {
                //TODO only show warning for currently competing event (there's likely missing data)
                activity.showWarningMessage(activity.getString(R.string.warning_using_cached_data));
            }

            if (mFragment.getView() != null) {
                mFragment.getView().findViewById(R.id.progress).setVisibility(View.GONE);
                mFragment.getView().findViewById(R.id.event_info_container).setVisibility(View.VISIBLE);
            }
        }
    }
}