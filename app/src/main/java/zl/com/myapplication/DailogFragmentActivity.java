package zl.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DailogFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailog_fragment);
    }

    public void Show(View view) {
        showEditDialog();
    }

    public void showEditDialog() {
        TDialogFragment editNameDialog = new TDialogFragment();
        editNameDialog.show(getSupportFragmentManager(), "EditNameDialog");
    }
}
