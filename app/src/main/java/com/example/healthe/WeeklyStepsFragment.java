package com.example.healthe;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.annotations.Line;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.text.DateFormat.getDateInstance;
import static java.text.DateFormat.getTimeInstance;

public class WeeklyStepsFragment extends Fragment {

    private GoogleApiClient googleApiClient;
    private AnyChartView anyChartView;
    private Cartesian chart;
    private List<DataEntry> chartData = new ArrayList<>();


    private class ViewWeekStepCountTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            if(chart == null) {
                chart = AnyChart.column();
            }
            displayLastWeeksData();
            return null;
        }

        private void displayLastWeeksData() {
            chartData.clear();
            Calendar cal = Calendar.getInstance();
            Date now = new Date();
            cal.setTime(now);
            long endTime = cal.getTimeInMillis();
            cal.add(Calendar.WEEK_OF_YEAR, -1);
            long startTime = cal.getTimeInMillis();

            java.text.DateFormat dateFormat = getDateInstance();
            Log.e("History", "Range Start: " + dateFormat.format(startTime));
            Log.e("History", "Range End: " + dateFormat.format(endTime));

            DataReadRequest readRequest = new DataReadRequest.Builder()
                    .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
                    .bucketByTime(1, TimeUnit.DAYS)
                    .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                    .build();

            DataReadResult dataReadResult = Fitness.HistoryApi.readData(googleApiClient, readRequest).await(1, TimeUnit.MINUTES);

            if (dataReadResult.getBuckets().size() > 0) {
                Log.e("History", "Number of buckets: " + dataReadResult.getBuckets().size());
                for (Bucket bucket : dataReadResult.getBuckets()) {
                    List<DataSet> dataSets = bucket.getDataSets();
                    for (DataSet dataSet : dataSets) {
                        dumpDataSet(dataSet);
                    }
                }
            }
            else if (dataReadResult.getDataSets().size() > 0) {
                Log.e("History", "Number of returned DataSets: " + dataReadResult.getDataSets().size());
                for (DataSet dataSet : dataReadResult.getDataSets()) {
                    dumpDataSet(dataSet);
                }
            }
        }

        @Override
        protected void onPostExecute(Void result) {
            onWeeklyStepsData();
        }

        private void dumpDataSet(DataSet dataSet) {
            Log.i("GoogleFIT", "Data returned for Data type: " + dataSet.getDataType().getName());
            DateFormat dateFormat = getDateInstance();

            for (DataPoint dp : dataSet.getDataPoints()) {
                Log.i("GoogleFIT", "Data point:");
                Log.i("GoogleFIT", "\tType: " + dp.getDataType().getName());
                Log.i("GoogleFIT", "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
                Log.i("GoogleFIT", "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)));
                for (Field field : dp.getDataType().getFields()) {
                    chartData.add(new ValueDataEntry(dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)), dp.getValue(field).asInt()));
                    Log.i("GoogleFIT", "\tField: " + field.getName() + " Value: " + dp.getValue(field));
                }
            }
        }
    }

    public WeeklyStepsFragment(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }

    protected void onWeeklyStepsData() {
        chart.data(chartData);
        anyChartView.setChart(chart);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View thisFragment = inflater.inflate(R.layout.weeklysteps, container, false);
        anyChartView = thisFragment.findViewById(R.id.weekly_line_chart);
        return thisFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        ViewWeekStepCountTask task = new ViewWeekStepCountTask();
        task.execute();
    }
}