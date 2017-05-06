package silive.in.kiskstarter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import silive.in.kiskstarter.R;

/**
 * Created by akriti on 6/5/17.
 */

public class ProjectListAdapter extends BaseAdapter {
    public Context context;
    public static ArrayList<String> name_of_project = new ArrayList<>();
    public static ArrayList<String> backers_of_project = new ArrayList<>();
    public static ArrayList<String> days_to_go = new ArrayList<>();
    public static ArrayList<String> pleadage_project = new ArrayList<>();

    public ProjectListAdapter(Context context,ArrayList<String> name,ArrayList<String> backer,ArrayList<String> no_days,ArrayList<String> plad) {
        this.context = context;
        name_of_project = name;
        backers_of_project = backer;
        days_to_go = no_days;
        pleadage_project = plad;
    }


    private class Holder{
        TextView project_name, pleadage, backers, no_of_days;
    }
    @Override
    public int getCount() {
        return name_of_project.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.project_list_adapter,null);
        Holder holder = new Holder();
        holder.project_name = (TextView) convertView.findViewById(R.id.project_name);
        holder.backers = (TextView) convertView.findViewById(R.id.backers);
        holder.pleadage = (TextView) convertView.findViewById(R.id.pleadage);
        holder.no_of_days = (TextView) convertView.findViewById(R.id.no_of_days);
        holder.project_name.setText(name_of_project.get(position));
        holder.backers.setText("Backers : "+backers_of_project.get(position));
        holder.no_of_days.setText("By : "+days_to_go.get(position));
        holder.pleadage.setText("Pleadge : "+pleadage_project.get(position));

        return convertView;
    }
}
