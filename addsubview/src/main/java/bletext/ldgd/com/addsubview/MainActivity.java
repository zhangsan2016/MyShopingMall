package bletext.ldgd.com.addsubview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import bletext.ldgd.com.addsubview.view.AddSubview;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddSubview addsubview = (AddSubview) this.findViewById(R.id.addsubview);

        addsubview.setOnNumberChangeListener(new AddSubview.OnNumberChangeListener() {
            @Override
            public void addNumber() {
                Toast.makeText(MainActivity.this, "iv_sub", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void subNumber() {
                Toast.makeText(MainActivity.this, "iv_add", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
