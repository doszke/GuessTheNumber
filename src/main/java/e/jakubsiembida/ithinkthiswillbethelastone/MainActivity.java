package e.jakubsiembida.ithinkthiswillbethelastone;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    static InputStream neuralNetInputStream;

    private TextView mTextMessage;

    private CanvasView canvasView;

    private DigitGuesser digitGuesser;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.clear:
                    canvasView.clearCanvas();
                    Toast.makeText(getApplicationContext(), "Canvas cleared", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.guess:
                    Toast.makeText(getApplicationContext(), String.valueOf(digitGuesser.guessDigit(canvasView.getBitmap())), Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        neuralNetInputStream = getResources().openRawResource(R.raw.my_mlp3);

        digitGuesser = new DigitGuesser();

        canvasView = (CanvasView) findViewById(R.id.signature_canvas);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /*
    public BottomNavigationView.OnNavigationItemSelectedListener getmOnNavigationItemSelectedListener() {

        BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String title = (String)menuItem.getTitle();

                switch (title) {
                    case "Clear":
                        Toast.makeText(getApplicationContext(), R.string.clear, Toast.LENGTH_SHORT).show();
                        break;
                    case "Guess":
                        Toast.makeText(getApplicationContext(), R.string.guess, Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        };

        return mOnNavigationItemSelectedListener;
    }
    */
}
