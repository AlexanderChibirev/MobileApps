package com.example.alexander.todolist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.alexander.todolist.R;
import com.example.alexander.todolist.mvp.models.Task;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import static com.example.alexander.todolist.R.id.spinnerPriority;

public class TaskRVAdepter extends RecyclerView.Adapter<TaskRVAdepter.TaskViewHolder> implements RealmChangeListener {


    private RealmResults<Task> mTasks;

    public TaskRVAdepter(RealmResults<Task> tasks) {
        mTasks = tasks;
        mTasks.addChangeListener(this);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_model, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        //Task task = mTasks.get(position);
        holder.itemTitle.setText("sad");
        holder.itemDescription.setText("asd");
        holder.itemDate.setText("asd");
        holder.itemCheckBox.setChecked(true);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                holder.view.getContext(),
                R.array.priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.itemSpinner.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public void onChange(Object element) {
        notifyDataSetChanged();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        TextView itemDescription;
        TextView itemDate;

        View view;
        CheckBox itemCheckBox;
        Spinner itemSpinner;

        TaskViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            itemDescription  = (TextView) itemView.findViewById(R.id.itemDescription);
            itemDate  = (TextView) itemView.findViewById(R.id.itemDate);
            itemCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            itemSpinner = (Spinner) itemView.findViewById(spinnerPriority);
        }
    }

}
