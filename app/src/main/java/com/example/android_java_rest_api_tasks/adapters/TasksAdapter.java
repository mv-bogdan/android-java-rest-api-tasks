package com.example.android_java_rest_api_tasks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_java_rest_api_tasks.R;
import com.example.android_java_rest_api_tasks.models.TaskModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ApiHolder> {
//
//    private OnClickListener onClickListener;
//
//    private List<TaskModel> tasksList;
//
//    private Context context;
//
//    public TasksAdapter(List<TaskModel> tasksList, Context context) {
//        this.tasksList = tasksList;
//        this.context = context;
//    }
//
//    public TasksAdapter() {
//
//    }
//
//    @SuppressLint("ViewHolder")
//    public View getView(final int position, View convertView, ViewGroup parent) {
//
//        final TasksModel model = getRealmResults().get(position);
//
//        if (model.getStatus() == 0) {
//            convertView = inflater.inflate(R.layout.active_task_layout, parent, false);
//            RealmViewActiveTaskHolder viewHolder = new RealmViewActiveTaskHolder(convertView);
//
//            viewHolder.title.setText(model.getTitle());
//            viewHolder.date.setText(DateUtils.formatDateTime(context,
//                    model.getDateComplete(),
//                    DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
//                            | DateUtils.FORMAT_SHOW_TIME));
//            viewHolder.buttonComplete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    new RealmController(context).changeStatusComplete(getRealmResults().get(position).getId());
//                }
//            });
//            viewHolder.TaskLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onClickListener.onTaskLayoutClick(model.getId(), model.getTitle(), model.getDateComplete(), false);
//                }
//            });
//        } else {
//            convertView = inflater.inflate(R.layout.completed_task_layout, parent, false);
//            RealmViewCompletedTaskHolder viewHolder = new RealmViewCompletedTaskHolder(convertView);
//
//            viewHolder.title.setText(model.getTitle());
//            viewHolder.date.setText(DateUtils.formatDateTime(context,
//                    model.getDateComplete(),
//                    DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
//                            | DateUtils.FORMAT_SHOW_TIME));
//            String t = getFormattedDateFromTimestamp(model.getCompletedIn());
//            viewHolder.completedIn.setText(t);
//            viewHolder.TaskLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onClickListener.onTaskLayoutClick(model.getId(), model.getTitle(), model.getDateComplete(), true);
//                }
//            });
//        }
//        return convertView;
//    }
//    public RealmResults<TasksModel> getRealmResults() {
//        return realmResults;
//    }
//
//    public class RealmViewActiveTaskHolder {
//
//        @BindView(R.id.title)
//        TextView title;
//
//        @BindView(R.id.dateComplete)
//        TextView date;
//
//        @BindView(R.id.TaskLayout)
//        LinearLayout TaskLayout;
//
//        @BindView(R.id.buttonComplete)
//        Button buttonComplete;
//
//        public RealmViewActiveTaskHolder(final View itemView) {
//            ButterKnife.bind(this, itemView);
//        }
//    }
//
//    public class RealmViewCompletedTaskHolder {
//
//        @BindView(R.id.title)
//        TextView title;
//
//        @BindView(R.id.dateComplete)
//        TextView date;
//
//        @BindView(R.id.TaskLayout)
//        LinearLayout TaskLayout;
//
//        @BindView(R.id.completed_in_text_view)
//        TextView completedIn;
//
//        public RealmViewCompletedTaskHolder(final View itemView) {
//            ButterKnife.bind(this, itemView);
//        }
//    }
//
//    public void setOnClickListener(OnClickListener onClickListener) {
//        this.onClickListener = onClickListener;
//    }
//
//    public void onChangeCurrentBottomMenu() {
//        super.notifyDataSetChanged();
//    }
//
//    public static String getFormattedDateFromTimestamp(long timestampInMilliSeconds)
//    {
//        Date date = new Date();
//        date.setTime(timestampInMilliSeconds);
//        String myFormat = "dd.MM.yy h:mm a"; //In which you need put here
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//        return sdf.format(date);
//
//    }
//
//    public interface OnClickListener {
//        void onTaskLayoutClick(int id, String title, long dateComplete, boolean isCompleted);
//    }
//}

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ApiHolder> {

    private List<TaskModel> tasksList;

    private Context context;

    public TasksAdapter(List<TaskModel> tasksList, Context context) {
        this.tasksList = tasksList;
        this.context = context;
    }

    public TasksAdapter() {

    }

    @Override
    public TasksAdapter.ApiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.active_task_layout, parent,false);
        ApiHolder mh = new ApiHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ApiHolder holder, int position) {

        final TaskModel item = tasksList.get(position);

        holder.title.setText(item.getTitle());
        holder.date.setText(item.getDateComplete());

    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    public class ApiHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.dateComplete)
        TextView date;

        @BindView(R.id.TaskLayout)
        LinearLayout TaskLayout;

        @BindView(R.id.buttonComplete)
        Button buttonComplete;

        public ApiHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
