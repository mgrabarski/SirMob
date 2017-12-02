package mateusz.grabarski.sirmobt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mateusz.grabarski.sirmobt.adapters.SecondViewAdapter;
import mateusz.grabarski.sirmobt.utils.DataGenerator;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.activity_second_rv)
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        list.setLayoutManager(new LinearLayoutManager(this));

        SecondViewAdapter adapter = new SecondViewAdapter(DataGenerator.getItemsForSecondView());
        list.setAdapter(adapter);
    }
}
