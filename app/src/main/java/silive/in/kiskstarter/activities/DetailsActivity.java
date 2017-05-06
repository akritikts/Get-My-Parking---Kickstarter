package silive.in.kiskstarter.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import silive.in.kiskstarter.R;

public class DetailsActivity extends AppCompatActivity {
    TextView pro_name, pro_pld, pro_backers, pro_by, pro_fund, pro_location;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        bundle = getIntent().getBundleExtra("DETAILS");
        pro_name = (TextView)findViewById(R.id.pro_name);
        pro_name.setText(bundle.getString("Name"));
        pro_pld = (TextView)findViewById(R.id.pro_pld);
        pro_pld.setText("Amount pleged : "+bundle.getString("Pled"));
        pro_backers = (TextView)findViewById(R.id.pro_backers);
        pro_backers.setText("Backers : "+bundle.getString("Backer"));
        pro_by = (TextView)findViewById(R.id.pro_by);
        pro_by.setText("By : "+bundle.getString("Days"));
        pro_fund = (TextView)findViewById(R.id.pro_fund);
        pro_fund.setText("Percentage fund : "+bundle.getString("Fund"));
        pro_location = (TextView)findViewById(R.id.pro_location);
        pro_location.setText("Location : "+bundle.getString("Loc"));


    }
}
