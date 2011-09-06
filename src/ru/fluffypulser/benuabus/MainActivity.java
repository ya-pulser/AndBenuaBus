package ru.fluffypulser.benuabus;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends Activity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Resources res = getResources();
        final String[] toV = res.getStringArray(R.array.fromOffice);
        final String[] toO = res.getStringArray(R.array.fromVokzal);

        final DataProvider d1 = new StaticDataProvider(toV, new ArrayAdapter<String>(this, R.layout.list_item, toV));
        final DataProvider d2 = new StaticDataProvider(toO, new ArrayAdapter<String>(this, R.layout.list_item, toO));

        final ListView lv = (ListView) this.findViewById(R.id.listView);
        lv.setAdapter(d1.getAdapter());
        lv.setTextFilterEnabled(true);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        move(lv, d2);

        final AdapterView.OnItemClickListener adaptee = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        };
        lv.setOnItemClickListener(adaptee);

        this.findViewById(R.id.cctglBtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                lv.setAdapter(lv.getAdapter() == d1.getAdapter() ? d2.getAdapter() : d1.getAdapter());
                move(lv, lv.getAdapter() == d1.getAdapter() ? d1 : d2);
            }
        });

        this.findViewById(R.id.btnClk).setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                move(lv, lv.getAdapter() == d1.getAdapter() ? d1 : d2);
            }
        });

    }

    private void move(final ListView lv, final DataProvider provider) {
        final String str = new SimpleDateFormat("HH:mm").format(new Date());
        Log.d("benuabus", "target is " + str);
        final int result = Arrays.binarySearch(provider.getData(), str);
        lv.setSelection(result >= 0 ? result : -1 * (result + 1));
    }

}
